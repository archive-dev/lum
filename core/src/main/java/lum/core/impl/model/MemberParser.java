package lum.core.impl.model;

import lum.antlr4.LumParser;
import lum.core.model.ClassModel;
import lum.core.model.Member;

import java.util.HashSet;
import java.util.Set;

final class MemberParser {
    public static Set<Member> parseMember(ClassModel owner, TypeProcessor typeProcessor, LumParser.MemberContext ctx) {
        HashSet<Member> members = new HashSet<>();

        switch (ctx) {
            case LumParser.VariableDeclarationMemberContext var -> members.addAll(FieldModelParser.buildFieldModels(owner, typeProcessor, var));
            case LumParser.FuncMemberContext func -> members.add(MethodModelParser.buildMethodModel(owner, typeProcessor, func));
            case LumParser.OperatorMemberContext operator -> members.add(MethodModelParser.buildMethodModel(owner, typeProcessor, operator));
            case LumParser.ConstructorMemberContext constructor -> members.add(MethodModelParser.buildMethodModel(owner, typeProcessor, constructor));
            default -> throw new IllegalStateException("Unexpected value: " + ctx);
        }

        return members;
    }

    public static Set<Member> parseMember(TypeProcessor typeProcessor, LumParser.MemberContext ctx) {
        return parseMember(null, typeProcessor, ctx);
    }
}
