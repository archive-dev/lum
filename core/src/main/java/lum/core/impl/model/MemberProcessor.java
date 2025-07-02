package lum.core.impl.model;

import lum.antlr4.LumParser;
import lum.core.model.ClassModel;
import lum.core.model.Member;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class MemberProcessor {
    private final TypeProcessor typeProcessor;


    public MemberProcessor(TypeProcessor typeProcessor) {
        this.typeProcessor = typeProcessor;
    }

    public Set<Member> processMembers(ClassModel model, List<LumParser.DeclarationContext> ctxs) {
        Set<Member> members = new HashSet<>();
        for (var ctx : ctxs) {
            if (ctx.member() != null)
                members.addAll(MemberParser.parseMember(model, typeProcessor, ctx.member()));
        }

        return members;
    }
}
