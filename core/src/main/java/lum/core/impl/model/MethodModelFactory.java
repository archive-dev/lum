package lum.core.impl.model;

import lum.core.model.ClassModel;
import lum.core.model.MethodModel;
import lum.core.model.ParameterModel;
import lum.core.model.TypeModel;
import lum.core.util.Utils;

import java.lang.reflect.AccessFlag;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Optional;

final class MethodModelFactory {
    private MethodModelFactory() {}

    public static MethodModel of(Method method) {
        //noinspection OptionalGetWithoutIsPresent
        return new MethodModelImpl(
                ClassModel.of(method.getDeclaringClass()),
                Utils.EMPTY_ATTRIBUTE_MODELS,
                method.accessFlags().toArray(AccessFlag[]::new),
                method.getName(),
                Optional.empty(),
                processParameterModels(method.getParameters()),
                ClassModel.of(method.getReturnType()).orElse(ClassModel.of(void.class).get()).asTypeModel(),
                Utils.EMPTY_CLASS_MODELS,
                Optional.empty(),
                Utils.EMPTY_ATTRIBUTE_PARSERS
        );
    }

    public static MethodModel of(Constructor<?> method) {
        //noinspection OptionalGetWithoutIsPresent
        return new MethodModelImpl(
                ClassModel.of(method.getDeclaringClass()),
                Utils.EMPTY_ATTRIBUTE_MODELS,
                method.accessFlags().toArray(AccessFlag[]::new),
                "<init>",
                Optional.empty(),
                processParameterModels(method.getParameters()),
                ClassModel.of(void.class).get().asTypeModel(),
                Utils.EMPTY_CLASS_MODELS,
                Optional.empty(),
                Utils.EMPTY_ATTRIBUTE_PARSERS
        );
    }

    private static ParameterModel[] processParameterModels(Parameter... parameters) {
        return Arrays.stream(parameters)
                .map(p -> new ParameterModelImpl(
                        Utils.EMPTY_ATTRIBUTE_MODELS,
                        p.getName(),
                        TypeModel.of(p.getType()).orElseThrow()
                ))
                .toArray(ParameterModel[]::new);
    }
}
