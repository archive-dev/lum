package lum.core.impl;

import lum.core.model.*;
import lum.core.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AccessFlag;
import java.util.Optional;

public record PrimitiveClassModelImpl(
        String name
) implements ClassModel {

    private static final Logger log = LoggerFactory.getLogger(PrimitiveClassModelImpl.class);

    @Override
    public Optional<ClassModel> superClass() {
        return Optional.empty();
    }

    @Override
    public ClassModel[] interfaces() {
        return Utils.EMPTY_CLASS_MODELS;
    }

    @Override
    public Member[] members() {
        return Utils.EMPTY_MEMBERS;
    }

    @Override
    public Member[] allMembers() {
        return new Member[0];
    }

    @Override
    public Optional<MethodModel> getMethod(String name, TypeModel[] parameterTypes) {
        return Optional.empty();
    }

    @Override
    public Optional<FieldModel> getField(String name) {
        return Optional.empty();
    }

    @Override
    public TypeModel asTypeModel(int arrayDimensions, TypeArgument... typeArguments) {
        if (TypeModelPool.containsTypeModel(this, arrayDimensions))
            return TypeModelPool.getTypeModel(this, arrayDimensions).orElseThrow();

        if (typeArguments.length > 0)
            checkTypeArgs();
        return TypeModelPool.addTypeModel(new PrimitiveTypeModelImpl(this, arrayDimensions));
    }

    @Override
    public TypeModel asTypeModel(int arrayDimensions, TypeModel... typeArguments) {
        if (TypeModelPool.containsTypeModel(this, arrayDimensions))
            return TypeModelPool.getTypeModel(this, arrayDimensions).orElseThrow();

        if (typeArguments.length > 0)
            checkTypeArgs();
        return TypeModelPool.addTypeModel(new PrimitiveTypeModelImpl(this, arrayDimensions));
    }

    @Override
    public boolean isSubclassOf(ClassModel other) {
        return other.equals(ClassModelFactory.of(Object.class).orElseThrow());
    }

    private void checkTypeArgs() {
        log.warn("Class {} does not contain any TypeParameter`s since it's primitive, typeArguments will be ignored", name());
    }

    @Override
    public String toPrettyString() {
        return name();
    }

    @Override
    public AccessFlag[] accessFlags() {
        return Utils.DEFAULT_ACCESS_FLAGS;
    }

    @Override
    public AttributeModel[] attributes() {
        return Utils.EMPTY_ATTRIBUTE_MODELS;
    }

    @Override
    public Optional<ClassModel> owner() {
        return Optional.empty();
    }

    @Override
    public Optional<TypeParameter[]> typeParameters() {
        return Optional.empty();
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }
}
