package lum.core.impl;

import lum.antlr4.LumParser;
import lum.core.model.*;
import lum.core.util.Utils;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.AccessFlag;
import java.util.*;

final class ClassModelParser {
    private ClassModelParser() {}

    private static Map<ImportsModel, TypeProcessor> typeProcessorsCache = new HashMap<>();

    public static Optional<TypeProcessor> getTypeProcessor(ImportsModel imports) {
        return Optional.ofNullable(typeProcessorsCache.get(imports));
    }

    public static Optional<Member[]> buildMemberModels(ImportsModel imports, LumParser.FileContext ctx) {
        if (ctx.declaration() == null) return Optional.empty();
        Set<Member> members = new HashSet<>();
        TypeProcessor typeProcessor = typeProcessorsCache.computeIfAbsent(imports, TypeProcessor::new);

        // todo somehow connect this to XModelProcessor
        // parse classes first and everything else second
        for (var decl : ctx.declaration()) {
            if (decl.typeDeclaration() != null)
                members.add(buildClassModel(decl.typeDeclaration()));
            else if (decl.member() != null) {
                members.addAll(MemberParser.parseMember(typeProcessor, decl.member()));
            }
        }

        return Optional.ofNullable(members.toArray(Member[]::new));
    }

    private static ClassModel buildClassModel(LumParser.TypeDeclarationContext ctx) {
        AccessFlag[] accessFlags;
        switch (ctx) {
            case LumParser.ClassDeclarationContext clazz -> {
                accessFlags = Utils.getAccessFlags(clazz.access(), clazz.modifier()).toArray(AccessFlag[]::new);
                return buildGenericType(clazz.class_().genericTypeDeclaration(), accessFlags);
            }
            case LumParser.InterfaceDeclarationContext inter -> {
                accessFlags = Utils.getAccessFlags(inter.access(), inter.modifier()).toArray(AccessFlag[]::new);
                return buildGenericType(inter.interface_().genericTypeDeclaration(), accessFlags);
            }
            case LumParser.EnumDeclarationContext enum_ -> {
                accessFlags = Utils.getAccessFlags(enum_.access(), enum_.modifier()).toArray(AccessFlag[]::new);
                return buildNonGenericType(enum_.enum_().nonGenericTypeDeclaration(), accessFlags);
            }
            case LumParser.AnnotationDeclarationContext annotation -> {
                accessFlags = Utils.getAccessFlags(annotation.access(), annotation.modifier()).toArray(AccessFlag[]::new);
                return buildNonGenericType(annotation.annotation().nonGenericTypeDeclaration(), accessFlags);
            }
            default -> throw new IllegalStateException("Unexpected value: " + ctx);
        }
        // todo: divided building and processing
    }

    private static ClassModel buildGenericType(LumParser.GenericTypeDeclarationContext ctx, AccessFlag[] accessFlags) {
        String className = ctx.IDENTIFIER().getText();
        ClassModel[] interfaces = buildInterfacesArray(ctx.inheritance());

        Optional<TypeParameter[]> typeParameters;
        if (ctx.generic() == null)
            typeParameters = Optional.empty();
        else {
            typeParameters = Optional.of(new TypeParameter[ctx.generic().genericBound().size()]);
        }

        int memberCount = countDeclarations(ctx.declaration());

        return new ClassModelImpl(
                Utils.EMPTY_ATTRIBUTE_MODELS,
                accessFlags,
                className,
                typeParameters,
                Optional.empty(),
                interfaces,
                memberCount == 0 ? Utils.EMPTY_MEMBERS : new Member[memberCount],
                Optional.of((LumParser.TypeDeclarationContext) ctx.getParent().getParent())
        );
    }

    private static ClassModel buildNonGenericType(LumParser.NonGenericTypeDeclarationContext ctx, AccessFlag[] accessFlags) {
        String className = ctx.IDENTIFIER().getText();
        ClassModel[] interfaces = buildInterfacesArray(ctx.inheritance());

        int memberCount = countDeclarations(ctx.declaration());

        return new ClassModelImpl(
                Utils.EMPTY_ATTRIBUTE_MODELS,
                accessFlags,
                className,
                Optional.empty(),
                Optional.empty(),
                interfaces,
                memberCount == 0 ? Utils.EMPTY_MEMBERS : new Member[memberCount],
                Optional.of((LumParser.TypeDeclarationContext) ctx.getParent().getParent())
        );
    }

    private static ClassModel[] buildInterfacesArray(LumParser.InheritanceContext ctx) {
        ClassModel[] interfaces = Utils.EMPTY_CLASS_MODELS;
        if (ctx != null && ctx.implements_() != null)
            interfaces = new ClassModel[ctx.implements_().type().size()];
        return interfaces;
    }

    private static int countDeclarations(List<LumParser.DeclarationContext> ctxs) {
        int count = 0;
        for (var decl : ctxs) {
            if (decl.typeDeclaration() != null)
                count++;
            else if (decl.member() != null) {
                if (!(decl.member() instanceof LumParser.VariableDeclarationMemberContext var))
                    count++;
                else {
                    count += var.variableDeclaration().variable().size();
                }
            }
        }
        return count;
    }

}
