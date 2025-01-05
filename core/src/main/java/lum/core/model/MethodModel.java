package lum.core.model;

import lum.core.util.Utils;
import lum.core.parser.LumParser;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record MethodModel(
        ClassModel owner,
        String name,
        ClassModel returnType,
        List<Parameter> parameters,
        List<ClassModel> exceptions,
        AccessFlag[] accessFlags,
        List<AttributeModel> attributes,
        LumFile file
) {
    public static MethodModel from(Method method) {
        ClassModel owner = ClassModelPool.get(method.getDeclaringClass());
        String name = method.getName();
        ClassModel returnType = ClassModelPool.get(method.getReturnType());
        ArrayList<Parameter> parameters
                = new ArrayList<>(Arrays.stream(method.getParameters()).map(Parameter::from).toList());
        ArrayList<ClassModel> exceptions
                = new ArrayList<>(Arrays.stream(method.getExceptionTypes()).map(ClassModelPool::get).toList());
        AccessFlag[] accessFlags = method.accessFlags().toArray(AccessFlag[]::new);
        List<AttributeModel> attributes = List.of();
        return new MethodModel(
                owner,
                name,
                returnType,
                parameters,
                exceptions,
                accessFlags,
                attributes,
                null
        );
    }

    public static MethodModel from(ClassModel owner, LumParser.FunctionDeclarationContext ctx) {
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());

        String name = ctx.IDENTIFIER().getText();

        ClassModel returnType = owner.file().imports().getClassModel(ctx.type());

        ArrayList<Parameter> parameters = new ArrayList<>(ctx.parameterList().parameter().stream().map(p -> Parameter.from(owner.file(), p)).toList());

        return new MethodModel(
                owner,
                name,
                returnType,
                parameters,
                new ArrayList<>(),
                accessFlags,
                new ArrayList<>(),
                owner.file()
        );
    }

    public static MethodModel from(ClassModel owner, LumParser.OperatorDeclarationContext ctx) {
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());

        String name = ctx.operator().getText();

        ClassModel returnType = owner.file().imports().getClassModel(ctx.type());

        ArrayList<Parameter> parameters = new ArrayList<>(ctx.parameterList().parameter().stream().map(p -> Parameter.from(owner.file(), p)).toList());

        return new MethodModel(
                owner,
                name,
                returnType,
                parameters,
                new ArrayList<>(),
                accessFlags,
                new ArrayList<>(),
                owner.file()
        );
    }

    public static MethodModel from(ClassModel owner, LumParser.ConstructorDeclarationContext ctx) {
        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());

        String name = "<init>";

        return new MethodModel(
                owner,
                name,
                ClassModelPool.get(Object.class),
                new ArrayList<>(),
                new ArrayList<>(),
                accessFlags,
                new ArrayList<>(),
                owner.file()
        );
    }

    @Override
    public String toString() {
        return "MethodModel{" +
                "owner=" + owner.name() +
                ", name='" + name + '\'' +
                ", returnType=" + returnType.name() +
                ", parameters=" + parameters.stream().map(Parameter::name).toList() +
                ", exceptions=" + exceptions.stream().map(ClassModel::name).toList() +
                ", accessFlags=" + Arrays.toString(accessFlags) +
                ", attributes=" + attributes +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                owner().name(),
                name(),
                returnType().name(),
                parameters().stream().map(Parameter::name).toList().hashCode(),
                exceptions().stream().map(ClassModel::name).toList().hashCode(),
                Arrays.hashCode(accessFlags()),
                attributes(),
                file() != null ? file().path() : null
        );
    }
}
