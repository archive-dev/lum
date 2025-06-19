package lum.core.impl;

import lum.core.model.ClassModel;
import lum.core.model.TypeArgument;
import lum.core.model.TypeModel;

import java.lang.constant.ClassDesc;
import java.lang.constant.ConstantDescs;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

record PrimitiveTypeModelImpl(
        ClassModel model,
        int arrayDimensions
) implements TypeModel.PrimitiveTypeModel {
    private static Map<String, ClassDesc> descMap = new HashMap<>();
    static {
        descMap.put("int", ConstantDescs.CD_int);
        descMap.put("long", ConstantDescs.CD_long);
        descMap.put("double", ConstantDescs.CD_double);
        descMap.put("float", ConstantDescs.CD_float);
        descMap.put("short", ConstantDescs.CD_short);
        descMap.put("byte", ConstantDescs.CD_byte);
        descMap.put("boolean", ConstantDescs.CD_boolean);
        descMap.put("char", ConstantDescs.CD_char);
        descMap.put("void", ConstantDescs.CD_void);
    }

    @Override
    public TypeModel asArray(int arrayDimensions) {
        return new PrimitiveTypeModelImpl(model(), arrayDimensions() + arrayDimensions);
    }

    @Override
    public ClassDesc classDesc() {
        ClassDesc desc = descMap.get(model.className());
        if (arrayDimensions() == 0)
            return desc;
        return desc.arrayType(arrayDimensions());
    }

    @Override
    public Optional<TypeArgument[]> genericArguments() {
        return Optional.empty();
    }
}
