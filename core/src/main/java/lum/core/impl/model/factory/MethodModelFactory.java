package lum.core.impl.model.factory;

import lum.core.impl.model.MethodModelImpl;
import lum.core.impl.model.ParameterModelImpl;
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

/**
 * Factory for creating MethodModel instances from Java reflection objects.
 */
public class MethodModelFactory {
    
    /**
     * Creates a MethodModel from a Java Method.
     */
    public MethodModel createFromMethod(Method method) {
        //noinspection OptionalGetWithoutIsPresent
        return new MethodModelImpl(
                ClassModel.of(method.getDeclaringClass()),
                Utils.EMPTY_ATTRIBUTE_MODELS,
                method.accessFlags().toArray(AccessFlag[]::new),
                method.getName(),
                Optional.empty(),
                createParameterModels(method.getParameters()),
                ClassModel.of(method.getReturnType()).orElse(ClassModel.of(void.class).get()).asTypeModel(),
                Utils.EMPTY_CLASS_MODELS,
                Optional.empty(),
                Utils.EMPTY_ATTRIBUTE_PARSERS
        );
    }
    
    /**
     * Creates a MethodModel from a Java Constructor.
     */
    public MethodModel createFromConstructor(Constructor<?> constructor) {
        //noinspection OptionalGetWithoutIsPresent
        return new MethodModelImpl(
                ClassModel.of(constructor.getDeclaringClass()),
                Utils.EMPTY_ATTRIBUTE_MODELS,
                constructor.accessFlags().toArray(AccessFlag[]::new),
                "<init>",
                Optional.empty(),
                createParameterModels(constructor.getParameters()),
                ClassModel.of(void.class).get().asTypeModel(),
                Utils.EMPTY_CLASS_MODELS,
                Optional.empty(),
                Utils.EMPTY_ATTRIBUTE_PARSERS
        );
    }
    
    private ParameterModel[] createParameterModels(Parameter... parameters) {
        return Arrays.stream(parameters)
                .map(p -> new ParameterModelImpl(
                        Utils.EMPTY_ATTRIBUTE_MODELS,
                        p.getName(),
                        TypeModel.of(p.getType()).orElseThrow()
                ))
                .toArray(ParameterModel[]::new);
    }
}