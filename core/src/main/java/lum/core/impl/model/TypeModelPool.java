package lum.core.impl.model;

import lum.core.model.ClassModel;
import lum.core.model.TypeArgument;
import lum.core.model.TypeModel;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

final class TypeModelPool {
    private TypeModelPool() {}

    private static final Map<ClassModel, Map<Integer, TypeModel>> nonGenericTypeModelPool = new HashMap<>();
    private static final Map<ClassModel, Map<Integer, Map<TypeModel[], TypeModel>>> genericTypeModelPool = new HashMap<>();

    static TypeModel addTypeModel(TypeModel model) {
        if (model.genericArguments().isPresent() && model.genericArguments().get().length != 0) {
            genericTypeModelPool.computeIfAbsent(model.model(), k -> new HashMap<>())
                    .computeIfAbsent(model.arrayDimensions(), k -> new HashMap<>())
                    .putIfAbsent(Arrays.stream(model.genericArguments().get()).map(TypeArgument::type).toArray(TypeModel[]::new), model);
        } else {
            nonGenericTypeModelPool.computeIfAbsent(model.model(), k -> new HashMap<>())
                    .putIfAbsent(model.arrayDimensions(), model);
        }

        return model;
    }

    static Optional<TypeModel> getTypeModel(ClassModel model, int arrayDimensions, TypeModel... typeArgs) {
        if (typeArgs == null || typeArgs.length == 0) {
            Map<Integer, TypeModel> integerTypeModelMap;
            if (nonGenericTypeModelPool.containsKey(model) &&
                    (integerTypeModelMap = nonGenericTypeModelPool.get(model)).containsKey(arrayDimensions))
                return Optional.ofNullable(integerTypeModelMap.get(arrayDimensions));
        } else {
            Map<Integer, Map<TypeModel[], TypeModel>> integerMapMap;
            Map<TypeModel[], TypeModel> typeModelMap;
            if (genericTypeModelPool.containsKey(model) &&
                    (integerMapMap = genericTypeModelPool.get(model)).containsKey(arrayDimensions) &&
                    (typeModelMap = integerMapMap.get(arrayDimensions)).containsKey(typeArgs))
                return Optional.ofNullable(typeModelMap.get(typeArgs));
        }

        return Optional.empty();
    }

    static boolean containsTypeModel(ClassModel model, int arrayDimensions, TypeModel... typeArgs) {
        if (typeArgs == null || typeArgs.length == 0) {
            return nonGenericTypeModelPool.containsKey(model) &&
                    nonGenericTypeModelPool.get(model).containsKey(arrayDimensions);
        } else {
            Map<Integer, Map<TypeModel[], TypeModel>> integerMapMap;
            return genericTypeModelPool.containsKey(model) &&
                    (integerMapMap = genericTypeModelPool.get(model)).containsKey(arrayDimensions) &&
                    integerMapMap.get(arrayDimensions).containsKey(typeArgs);
        }
    }
}
