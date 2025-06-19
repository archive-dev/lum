package lum.core.impl;

import lum.core.model.MethodModel;
import lum.core.model.ParameterModel;
import lum.core.util.Utils;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Optional;

final class MethodModelFactory {
    private MethodModelFactory() {}

    public static MethodModel of(Method method) {
        //noinspection OptionalGetWithoutIsPresent
        return new MethodModelImpl(
                ClassModelFactory.of(method.getDeclaringClass()),
                Utils.EMPTY_ATTRIBUTE_MODELS,
                method.accessFlags().toArray(AccessFlag[]::new),
                method.getName(),
                Optional.empty(),
                new ParameterModel[method.getParameterCount()],
                ClassModelFactory.of(method.getReturnType()).orElse(ClassModelFactory.of(void.class).get()).asTypeModel(),
                Utils.EMPTY_CLASS_MODELS
        );
    }

    public static MethodModel of(Constructor<?> method) {
        //noinspection OptionalGetWithoutIsPresent
        return new MethodModelImpl(
                ClassModelFactory.of(method.getDeclaringClass()),
                Utils.EMPTY_ATTRIBUTE_MODELS,
                method.accessFlags().toArray(AccessFlag[]::new),
                "<init>",
                Optional.empty(),
                new ParameterModel[method.getParameterCount()],
                ClassModelFactory.of(void.class).get().asTypeModel(),
                Utils.EMPTY_CLASS_MODELS
        );
    }
}
