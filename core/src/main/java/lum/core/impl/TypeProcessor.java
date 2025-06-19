package lum.core.impl;

import lum.antlr4.LumParser;
import lum.core.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Optional;

// one per member (beacuse of generics processor)
class TypeProcessor {
    private static final Logger log = LoggerFactory.getLogger(TypeProcessor.class);
    private final ImportsModel imports;
    final GenericsProcessor genericsProcessor;

    public TypeProcessor(TypeProcessor parent, GenericsProcessor genericsProcessor) {
        this.imports = parent.imports;
        this.genericsProcessor = genericsProcessor;
    }

    public TypeProcessor(TypeProcessor parent) {
        this.imports = parent.imports;
        this.genericsProcessor = parent.genericsProcessor;
    }

    public TypeProcessor(ImportsModel imports, GenericsProcessor genericsProcessor) {
        this.imports = imports;
        this.genericsProcessor = genericsProcessor;
    }

    public TypeProcessor(ImportsModel imports) {
        this.imports = imports;
        this.genericsProcessor = null;
    }

    public TypeModel getType(LumParser.TypeContext type) {
        switch (type) {
            case LumParser.PlainTypeContext plain -> {
                Optional<ClassModel> classModel = imports.getModel(plain.IDENTIFIER().getText());
                if (classModel.isEmpty()) {
                    if (genericsProcessor == null)
                        //noinspection DataFlowIssue
                        return classModel.map(ClassModel::asTypeModel).orElseThrow();
                    classModel = genericsProcessor.getTypeParameter(plain.IDENTIFIER().getText()).map(TypeParameter::bound).map(TypeModel::model);
                }
                Optional<TypeParameter[]> typeParameters = classModel.map(ClassModel::typeParameters).orElseThrow();
                if (typeParameters.isEmpty())
                    return classModel.map(ClassModel::asTypeModel).orElseThrow();
                if (plain.generic() == null) {
                    log.warn("Provided type contains type parameters, but no type arguments were provided");
                    return classModel.map(ClassModel::asTypeModel).orElseThrow();
                }
                if (genericsProcessor == null)
                    return classModel.map(ClassModel::asTypeModel).orElseThrow();
                return classModel
                        .map(m ->
                                m.asTypeModel(
                                        0,
                                        genericsProcessor
                                                .processGenericArguments(typeParameters.get(), plain.generic())
                                                .orElseThrow())
                        ).orElseThrow();
            }
            case LumParser.ArrayTypeContext array -> {
                var t = getType(array.type());
                return t.asArray(
                        Optional.ofNullable(array.ARRAY_OP()).orElse(Collections.emptyList()).size() +
                                Optional.ofNullable(array.LBRACK()).orElse(Collections.emptyList()).size()
                );
            }
            case LumParser.NullableTypeContext nullable -> {
                return ClassModelFactory.of(Optional.class).orElseThrow().asTypeModel(getType(nullable.type()));
            }
            case LumParser.UnionTypeContext union -> {
                return new UnionTypeModelImpl(union.type().stream().map(this::getType).toArray(TypeModel[]::new));
            }
            case LumParser.IntersectionTypeContext intersection -> {
                return new IntersectionTypeModelImpl(intersection.type().stream().map(this::getType).toArray(TypeModel[]::new));
            }
            case null, default -> {

            }
        }

        return null;
    }

    public ClassModel getModel(LumParser.TypeContext type) {
        return getType(type).model();
    }
}
