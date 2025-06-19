package lum.core.impl;

import lum.antlr4.LumParser;
import lum.core.model.ClassModel;
import lum.core.model.Member;
import lum.core.model.TypeModel;
import lum.core.util.Utils;

import java.lang.reflect.AccessFlag;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

final class FieldModelParser {
    private FieldModelParser() {}

    static Set<Member> buildFieldModels(ClassModel owner, TypeProcessor typeProcessor, LumParser.VariableDeclarationMemberContext ctx) {
        Set<Member> members = new HashSet<>();
        Set<AccessFlag> flagSet = Utils.getAccessFlags(ctx.access(), ctx.modifier());
        AccessFlag[] accessFlags = flagSet.toArray(AccessFlag[]::new);
        for (var var : ctx.variableDeclaration().variable()) {
            String fieldName = var.IDENTIFIER().getText();
            TypeModel type = typeProcessor.getType(var.type());

            members.add(new FieldModelImpl(
                    Optional.ofNullable(owner),
                    Utils.EMPTY_ATTRIBUTE_MODELS,
                    accessFlags,
                    fieldName,
                    type
            ));

            if (var.getter() != null) {
                var getter = var.getter().getFirst();
                var getterFlags = new HashSet<>(flagSet);
                getterFlags.removeAll(Set.of(AccessFlag.PUBLIC, AccessFlag.PRIVATE, AccessFlag.PROTECTED));
                getterFlags.addAll(Utils.getAccessFlags(getter.access(), null));

                //noinspection OptionalGetWithoutIsPresent
                members.add(new MethodModelImpl(
                        Optional.ofNullable(owner),
                        Utils.EMPTY_ATTRIBUTE_MODELS,
                        getterFlags.toArray(AccessFlag[]::new),
                        "get" + Utils.toTitled(fieldName),
                        Optional.empty(),
                        Utils.EMPTY_PARAMETERS,
                        ClassModelFactory.of(void.class).get().asTypeModel(),
                        Utils.EMPTY_CLASS_MODELS
                ));
            }
            if (var.setter() == null) continue;

            for (var setter : var.setter()) {
                var setterFlags = new HashSet<>(flagSet);
                setterFlags.removeAll(Set.of(AccessFlag.PUBLIC, AccessFlag.PRIVATE, AccessFlag.PROTECTED));
                setterFlags.addAll(Utils.getAccessFlags(setter.access(), null));

                //noinspection OptionalGetWithoutIsPresent
                members.add(new MethodModelImpl(
                        Optional.ofNullable(owner),
                        Utils.EMPTY_ATTRIBUTE_MODELS,
                        setterFlags.toArray(AccessFlag[]::new),
                        "set" + Utils.toTitled(fieldName),
                        Optional.empty(),
                        Utils.EMPTY_PARAMETERS,
                        ClassModelFactory.of(void.class).get().asTypeModel(),
                        Utils.EMPTY_CLASS_MODELS
                ));
            }
        }

        return members;
    }
}
