package lum.core.impl.model;

import lum.antlr4.LumParser;
import lum.core.model.*;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.AccessFlag;
import java.util.Arrays;
import java.util.Optional;

public record MethodModelImpl(
        Optional<ClassModel> owner,
        AttributeModel[] attributes,
        AccessFlag[] accessFlags,
        String name,
        Optional<TypeParameter[]> typeParameters,
        ParameterModel[] parameters,
        TypeModel returnType,
        ClassModel[] exceptions,
        Optional<LumParser.CodeBlockContext> codeBlock,
        AttributeParser<?>[] attributeParsers
) implements MethodModel {
    @Override
    public @NotNull String toString() {
        return "MethodModelImpl{" +
                "model=" + owner().map(ClassModel::className).orElse("null") +
                ", attributes=" + Arrays.toString(attributes) +
                ", accessFlags=" + Arrays.toString(accessFlags) +
                ", name='" + name + '\'' +
                ", parameters=" + Arrays.toString(parameters) +
                ", returnType=" + returnType +
                ", exceptions=" + Arrays.toString(exceptions) +
                '}';
    }
}
