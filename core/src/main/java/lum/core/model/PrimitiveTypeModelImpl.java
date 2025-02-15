package lum.core.model;

import java.lang.classfile.TypeKind;
import java.lang.constant.ClassDesc;
import java.lang.constant.ConstantDescs;
import java.util.HashMap;

import static lum.core.util.Utils.EMPTY_GENERIC_PARAMETERS;

public record PrimitiveTypeModelImpl(
        ClassModel model,
        int arrayDimensions,
        GenericParameter[] genericParameters
) implements TypeModel {
    private static final HashMap<String, ClassDesc> primitiveDescriptors = new HashMap<>();
    static {
        primitiveDescriptors.put("void", ConstantDescs.CD_void);
        primitiveDescriptors.put("byte", ConstantDescs.CD_byte);
        primitiveDescriptors.put("boolean", ConstantDescs.CD_boolean);
        primitiveDescriptors.put("int", ConstantDescs.CD_int);
        primitiveDescriptors.put("long", ConstantDescs.CD_long);
        primitiveDescriptors.put("short", ConstantDescs.CD_short);
        primitiveDescriptors.put("float", ConstantDescs.CD_float);
        primitiveDescriptors.put("double", ConstantDescs.CD_double);
        primitiveDescriptors.put("char", ConstantDescs.CD_char);
//        primitiveDescriptors.put("void", "V");
//        primitiveDescriptors.put("void", "V");
    }

    public PrimitiveTypeModelImpl(ClassModel model, int arrayDimensions) {
        this(model, arrayDimensions, EMPTY_GENERIC_PARAMETERS);
    }

    @Override
    public TypeModel asArray(int dimensions) {
        return new TypeModelImpl(model(), arrayDimensions()+dimensions);
    }

    @Override
    public ClassDesc classDesc() {
        ClassDesc classDesc = primitiveDescriptors.get(model().name());
        if (arrayDimensions() > 0)
            return classDesc.arrayType(arrayDimensions());
        return classDesc;
    }

    @Override
    public TypeKind typeKind() {
        return TypeKind.fromDescriptor(classDesc().descriptorString());
    }

    @Override
    public String toString() {
        return model().name() + "[]".repeat(arrayDimensions());
    }

    @Override
    public boolean isPrimitive() {
        return true;
    }
}
