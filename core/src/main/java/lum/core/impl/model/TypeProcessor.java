package lum.core.impl.model;

import lum.antlr4.LumParser;
import lum.core.model.*;
import lum.core.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Optional;

// one per member (beacuse of generics processor)
public final class TypeProcessor {
    private static final Logger log = LoggerFactory.getLogger(TypeProcessor.class);
    private final Member member;
    private final ImportsModel imports;
    final GenericsProcessor genericsProcessor;

    public TypeProcessor(Member member, TypeProcessor parent, GenericsProcessor genericsProcessor) {
        this.member = member;
        this.imports = parent.imports;
        this.genericsProcessor = genericsProcessor;
    }

    public TypeProcessor(Member member, TypeProcessor parent) {
        this.member = member;
        this.imports = parent.imports;
        this.genericsProcessor = parent.genericsProcessor;
    }

    public TypeProcessor(Member member, ImportsModel imports, GenericsProcessor genericsProcessor) {
        this.member = member;
        this.imports = imports;
        this.genericsProcessor = genericsProcessor;
    }

    public TypeProcessor(Member member, ImportsModel imports) {
        this.member = member;
        this.imports = imports;
        this.genericsProcessor = null;
    }

    public TypeProcessor(ImportsModel imports) {
        this(null, imports);
    }


    public TypeProcessor(TypeProcessor parent, GenericsProcessor genericsProcessor) {
        this(null, parent, genericsProcessor);
    }

    public TypeProcessor(TypeProcessor parent) {
        this(null, parent);
    }

    public TypeProcessor(ImportsModel imports, GenericsProcessor genericsProcessor) {
        this(null, imports, genericsProcessor);
    }

    public Optional<TypeModel> getType(String typeAlias) {
        typeAlias = typeAlias.replaceAll("]", "");

        int arrayDimensions = Utils.count("\\[", typeAlias);
        typeAlias = typeAlias.replaceAll("\\[", "");

        if (genericsProcessor != null) {
            var generic = genericsProcessor.getTypeParameter(typeAlias).map(TypeParameter::bound).map(t -> t.asArray(arrayDimensions));
            if (generic.isPresent())
                return generic;
        }

        return imports.getType(typeAlias).map(t -> t.asArray(arrayDimensions));
    }

    public Optional<TypeModel> getType(LumParser.TypeContext type) {
        switch (type) {
            case LumParser.PlainTypeContext plain -> {
                Optional<TypeModel> typeModel = imports.getType(plain.IDENTIFIER().getText());
                if (typeModel.isEmpty()) {
                    if (genericsProcessor == null)
                        throw new IllegalStateException("Type not found: " + type.getText());
                    typeModel = genericsProcessor.getTypeParameter(
                                plain.IDENTIFIER().getText()
                            ).map(tp -> tp.asGenericTypeModel(this.member));
                }
                Optional<TypeParameter[]> typeParameters = typeModel
                        .map(TypeModel::model)
                        .map(ClassModel::typeParameters).orElseThrow();
                if (typeParameters.isEmpty())
                    return typeModel;
                if (plain.generic() == null) {
                    log.warn("Provided type contains type parameters, but no type arguments were provided");
                    return typeModel;
                }
                if (genericsProcessor == null)
                    return typeModel;
                return typeModel.map(tm -> tm.withTypeArguments(
                        genericsProcessor.processGenericArguments(
                                typeParameters.get(),
                                plain.generic()).orElseThrow()
                ));
            }
            case LumParser.ArrayTypeContext array -> {
                Optional<TypeModel> t = getType(array.type());
                return t.map(typeModel -> typeModel.asArray(
                        Optional.ofNullable(array.ARRAY_OP()).orElse(Collections.emptyList()).size() +
                                Optional.ofNullable(array.LBRACK()).orElse(Collections.emptyList()).size()
                ));
            }
            case LumParser.NullableTypeContext nullable -> {
                return ClassModel.of(Optional.class).map(optional -> optional.asTypeModel(getType(nullable.type()).orElseThrow()));
            }
            case LumParser.UnionTypeContext union -> {
                return Optional.of(new UnionTypeModelImpl(union.type().stream().map(this::getType).map(Optional::orElseThrow).toArray(TypeModel[]::new)));
            }
            case LumParser.IntersectionTypeContext intersection -> {
                return Optional.of(new IntersectionTypeModelImpl(intersection.type().stream().map(this::getType).map(Optional::orElseThrow).toArray(TypeModel[]::new)));
            }
            case null, default -> {
                return Optional.empty();
            }
        }
    }

    public Optional<ClassModel> getModel(LumParser.TypeContext type) {
        return getType(type).map(TypeModel::model);
    }
}
