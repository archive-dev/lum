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
    private static final HashMap<ClassModel, ClassDesc> primitiveDescriptors = new HashMap<>();
    static {
        primitiveDescriptors.put(ClassModel.of(void.class), ConstantDescs.CD_void);
        primitiveDescriptors.put(ClassModel.of(byte.class), ConstantDescs.CD_byte);
        primitiveDescriptors.put(ClassModel.of(boolean.class), ConstantDescs.CD_boolean);
        primitiveDescriptors.put(ClassModel.of(int.class), ConstantDescs.CD_int);
        primitiveDescriptors.put(ClassModel.of(long.class), ConstantDescs.CD_long);
        primitiveDescriptors.put(ClassModel.of(short.class), ConstantDescs.CD_short);
        primitiveDescriptors.put(ClassModel.of(float.class), ConstantDescs.CD_float);
        primitiveDescriptors.put(ClassModel.of(double.class), ConstantDescs.CD_double);
        primitiveDescriptors.put(ClassModel.of(char.class), ConstantDescs.CD_char);
//        primitiveDescriptors.put("void", "V");
//        primitiveDescriptors.put("void", "V");
    }

    public PrimitiveTypeModelImpl(ClassModel model, int arrayDimensions) {
        this(model, arrayDimensions, EMPTY_GENERIC_PARAMETERS);
    }

    @Override
    public TypeModel asArray(int dimensions) {
        return new PrimitiveTypeModelImpl(model(), arrayDimensions()+dimensions);
    }

    @Override
    public TypeModel asComponent() {
        if (!isArray())
            return this;
        return new PrimitiveTypeModelImpl(model(), arrayDimensions()-1);
    }

    @Override
    public ClassDesc classDesc() {
        ClassDesc classDesc = primitiveDescriptors.get(model());
        if (arrayDimensions() > 0)
            return classDesc.arrayType(arrayDimensions());
        return classDesc;
    }

    @Override
    public TypeKind typeKind() {
        return TypeKind.fromDescriptor(classDesc().descriptorString()).asLoadable();
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
