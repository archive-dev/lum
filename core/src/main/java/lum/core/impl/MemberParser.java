package lum.core.impl;

import lum.antlr4.LumParser;
import lum.core.model.Member;

import java.util.HashSet;
import java.util.Set;

final class MemberParser {
    public static Set<Member> parseMember(TypeProcessor typeProcessor, LumParser.MemberContext ctx) {
        HashSet<Member> members = new HashSet<>();

        switch (ctx) {
            case LumParser.VariableDeclarationMemberContext var -> {
                members.addAll(FieldModelParser.buildFieldModels(null, typeProcessor, var));
            }
            case LumParser.FuncMemberContext func -> {
                members.add(MethodModelParser.buildMethodModel(null, typeProcessor, func));
            }
            case LumParser.OperatorMemberContext operator -> {
                members.add(MethodModelParser.buildMethodModel(null, typeProcessor, operator));
            }
            case LumParser.ConstructorMemberContext constructor -> {
                members.add(MethodModelParser.buildMethodModel(null, typeProcessor, constructor));
            }
            default -> throw new IllegalStateException("Unexpected value: " + ctx);
        }

        return members;
    }
}
