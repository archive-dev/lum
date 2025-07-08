package lum.core.impl.model;

import lum.core.model.ClassModel;
import lum.core.model.TypeArgument;
import lum.core.model.TypeModel;

import java.lang.constant.ClassDesc;
import java.util.*;

public final class IntersectionTypeModelImpl implements TypeModel.IntersectionTypeModel {
    private final TypeModel[] types;
    private final int arrayDimensions;

    public IntersectionTypeModelImpl(TypeModel... types) {
        this(0, types);
    }

    public IntersectionTypeModelImpl(int arrayDimensions, TypeModel... types) {
        if (types == null || types.length == 0) {
            throw new IllegalArgumentException("Intersection type must have at least one type");
        }
        this.types = types.clone();
        this.arrayDimensions = arrayDimensions;
    }

    @Override
    public TypeModel primaryTypeModel() {
        return types[0];
    }

    @Override
    public TypeModel[] getTypes() {
        return types.clone();
    }

    @Override
    public ClassModel model() {
        // For intersection types, we return the first type as the primary type
        // This is typically the class type in bounded type parameters like <T extends Class & Interface>
        // Filters out array types since TypeModel::model always returns ClassModel of a component of an array type
        return new IntersectionClassModelImpl(Arrays.stream(getTypes()).filter(m -> !m.isArray()).map(TypeModel::model).toArray(ClassModel[]::new));
    }

    @Override
    public int arrayDimensions() {
        return arrayDimensions;
    }

    @Override
    public Optional<TypeArgument[]> genericArguments() {
        // Intersection types themselves don't have generic arguments
        // Individual constituent types may have them
        return Optional.empty();
    }

    @Override
    public TypeModel asArray(int arrayDimensions) {
        return new IntersectionTypeModelImpl(this.arrayDimensions + arrayDimensions, types);
    }

    @Override
    public ClassDesc classDesc() {
        // Use the primary type (first type) for the class descriptor
        ClassModel primaryType = types[0].model();
        ClassDesc desc = ClassDesc.of(primaryType.name());
        if (arrayDimensions() == 0) {
            return desc;
        }
        return desc.arrayType(arrayDimensions());
    }

    /**
     * Checks if this intersection type is satisfied by the given type.
     * A type satisfies an intersection if it's a subtype of all constituent types.
     */
    @Override
    public boolean isSatisfiedBy(TypeModel type) {
        for (TypeModel requiredType : types) {
            if (!type.model().isSubclassOf(requiredType.model())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IntersectionTypeModelImpl other)) return false;
        return arrayDimensions == other.arrayDimensions &&
                Arrays.equals(types, other.types);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(types) * 31 + arrayDimensions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < types.length; i++) {
            if (i > 0) sb.append(" & ");
            sb.append(types[i].toString());
        }
        sb.append("[]".repeat(Math.max(0, arrayDimensions)));
        return sb.toString();
    }
}
