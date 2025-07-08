package lum.core.impl.model;

import lum.core.model.*;

import java.lang.constant.ClassDesc;
import java.util.*;

public final class UnionTypeModelImpl implements TypeModel.UnionTypeModel {
    private final TypeModel[] types;
    private final int arrayDimensions;

    public UnionTypeModelImpl(TypeModel... types) {
        this(0, types);
    }

    public UnionTypeModelImpl(int arrayDimensions, TypeModel... types) {
        if (types == null || types.length == 0) {
            throw new IllegalArgumentException("Union type must have at least one type");
        }
        this.types = types.clone();
        this.arrayDimensions = arrayDimensions;
    }

    @Override
    public TypeModel[] getTypes() {
        return types.clone();
    }

    @Override
    public TypeModel lubTypeModel() {
        return findLeastCommonSupertype().asTypeModel();
    }

    @Override
    public ClassModel model() {
        return findLeastCommonSupertype();
    }

    @Override
    public int arrayDimensions() {
        return arrayDimensions;
    }

    @Override
    public Optional<TypeArgument[]> genericArguments() {
        // Union types themselves don't have generic arguments
        // Individual constituent types may have them
        return Optional.empty();
    }

    @Override
    public TypeModel asArray(int arrayDimensions) {
        return new UnionTypeModelImpl(this.arrayDimensions + arrayDimensions, types);
    }

    @Override
    public ClassDesc classDesc() {
        ClassModel lcs = findLeastCommonSupertype();
        ClassDesc desc = ClassDesc.of(lcs.name());
        if (arrayDimensions() == 0) {
            return desc;
        }
        return desc.arrayType(arrayDimensions());
    }

    /**
     * Finds the least common supertype of all types in this union.
     * Uses a meet-in-the-middle approach by finding all supertypes of each type
     * and then finding the most specific type that is a supertype of all.
     */
    private ClassModel findLeastCommonSupertype() {
        if (types.length == 1) {
            return types[0].model();
        }

        // Get all class models from the union types
        ClassModel[] classModels = Arrays.stream(types)
                .map(TypeModel::model)
                .toArray(ClassModel[]::new);

        // Find the intersection of all supertype hierarchies
        Set<ClassModel> commonSupertypes = getAllSupertypes(classModels[0]);

        for (int i = 1; i < classModels.length; i++) {
            Set<ClassModel> currentSupertypes = getAllSupertypes(classModels[i]);
            commonSupertypes.retainAll(currentSupertypes);
        }

        // Find the most specific (lowest in hierarchy) common supertype
        return findMostSpecificType(commonSupertypes);
    }

    /**
     * Gets all supertypes of a given class model, including the class itself,
     * its superclass hierarchy, and all implemented interfaces.
     */
    private Set<ClassModel> getAllSupertypes(ClassModel classModel) {
        Set<ClassModel> supertypes = new HashSet<>();
        Queue<ClassModel> toProcess = new LinkedList<>();
        toProcess.add(classModel);

        while (!toProcess.isEmpty()) {
            ClassModel current = toProcess.poll();
            if (supertypes.contains(current)) {
                continue; // Avoid cycles
            }

            supertypes.add(current);

            // Add superclass to processing queue
            current.superClass().ifPresent(toProcess::add);

            // Add all interfaces to processing queue
            Collections.addAll(toProcess, current.interfaces());
        }

        return supertypes;
    }

    /**
     * Finds the most specific type from a set of types.
     * A type is more specific if it's a subtype of other types in the set.
     */
    private ClassModel findMostSpecificType(Set<ClassModel> types) {
        if (types.isEmpty()) {
            // Fallback to Object if no common supertype found
            return ClassModel.of(Object.class).orElseThrow();
        }

        // Find the type that is a subtype of all others (most specific)
        for (ClassModel candidate : types) {
            boolean isMostSpecific = true;

            for (ClassModel other : types) {
                if (candidate != other && !candidate.isSubclassOf(other)) {
                    isMostSpecific = false;
                    break;
                }
            }

            if (isMostSpecific) {
                return candidate;
            }
        }

        // If no single most specific type found, return Object as fallback
        return ClassModel.of(Object.class).orElseThrow();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UnionTypeModelImpl other)) return false;
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
            if (i > 0) sb.append(" | ");
            sb.append(types[i].toString());
        }
        sb.append("[]".repeat(Math.max(0, arrayDimensions)));
        return sb.toString();
    }
}
