package lum.core.impl.model;

import lum.core.model.*;

import java.lang.reflect.AccessFlag;
import java.util.*;

/**
 * Represents an intersection of multiple class models, typically used in bounded type parameters
 * like {@code <T extends Class & Interface1 & Interface2>}.
 * The first-class model of intersection is considered the primary type (usually a class),
 * and the rest are typically interfaces.
 */
public final class IntersectionClassModelImpl implements ClassModel.IntersectionClassModel {
    private final ClassModel[] classModels;
    private final String name;

    public IntersectionClassModelImpl(ClassModel... classModels) {
        if (classModels == null || classModels.length == 0) {
            throw new IllegalArgumentException("Intersection class must have at least one class model");
        }
        this.classModels = classModels.clone();
        
        // Create a composite name from all constituent types
        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < classModels.length; i++) {
            if (i > 0) nameBuilder.append(" & ");
            nameBuilder.append(classModels[i].name());
        }
        this.name = nameBuilder.toString();
    }

    public ClassModel[] getClassModels() {
        return classModels.clone();
    }

    /**
     * Returns the primary class model (first one), typically the class type
     */
    public ClassModel getPrimaryClassModel() {
        return classModels[0];
    }

    @Override
    public AttributeModel[] attributes() {
        // Combine attributes from all constituent class models
        List<AttributeModel> allAttributes = new ArrayList<>();
        for (ClassModel classModel : classModels) {
            allAttributes.addAll(Arrays.asList(classModel.attributes()));
        }
        return allAttributes.toArray(new AttributeModel[0]);
    }

    @Override
    public AccessFlag[] accessFlags() {
        // Use access flags from the primary class model
        return getPrimaryClassModel().accessFlags();
    }

    @Override
    public Optional<ClassModel> owner() {
        // Use owner from the primary class model
        return getPrimaryClassModel().owner();
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Optional<TypeParameter[]> typeParameters() {
        // Use type parameters from the primary class model
        return getPrimaryClassModel().typeParameters();
    }

    @Override
    public Optional<ClassModel> superClass() {
        // Use superclass from the primary class model
        return getPrimaryClassModel().superClass();
    }

    @Override
    public ClassModel[] interfaces() {
        // Combine all interfaces from all constituent class models
        List<ClassModel> allInterfaces = new ArrayList<>();
        for (ClassModel classModel : classModels) {
            allInterfaces.addAll(Arrays.asList(classModel.interfaces()));
        }
        // Also add non-primary class models as they are typically interfaces
        allInterfaces.addAll(Arrays.asList(classModels).subList(1, classModels.length));
        return allInterfaces.toArray(new ClassModel[0]);
    }

    @Override
    public Member[] members() {
        // Combine members from all constituent class models
        List<Member> allMembers = new ArrayList<>();
        for (ClassModel classModel : classModels) {
            allMembers.addAll(Arrays.asList(classModel.members()));
        }
        return allMembers.toArray(new Member[0]);
    }

    @Override
    public Member[] allMembers() {
        // Combine all members from all constituent class models
        List<Member> allMembers = new ArrayList<>();
        for (ClassModel classModel : classModels) {
            allMembers.addAll(Arrays.asList(classModel.allMembers()));
        }
        return allMembers.toArray(new Member[0]);
    }

    @Override
    public Optional<MethodModel> getMethod(String name, TypeModel... parameterTypes) {
        // Search for method in all constituent class models
        for (ClassModel classModel : classModels) {
            Optional<MethodModel> method = classModel.getMethod(name, parameterTypes);
            if (method.isPresent()) {
                return method;
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<FieldModel> getField(String name) {
        // Search for field in all constituent class models
        for (ClassModel classModel : classModels) {
            Optional<FieldModel> field = classModel.getField(name);
            if (field.isPresent()) {
                return field;
            }
        }
        return Optional.empty();
    }

    @Override
    public TypeModel asTypeModel(int arrayDimensions, TypeArgument... typeArguments) {
        TypeModel[] typeArgs = Arrays.stream(typeArguments).map(TypeArgument::type).toArray(TypeModel[]::new);
        if (TypeModelPool.containsTypeModel(this, arrayDimensions, typeArgs))
            return TypeModelPool.getTypeModel(this, arrayDimensions, typeArgs).orElseThrow();

        // Create an intersection type model from all constituent class models
        TypeModel[] typeModels = new TypeModel[classModels.length];
        for (int i = 0; i < classModels.length; i++) {
            typeModels[i] = classModels[i].asTypeModel(0, typeArguments);
        }
        return TypeModelPool.addTypeModel(new IntersectionTypeModelImpl(arrayDimensions, typeModels));
    }

    @Override
    public TypeModel asTypeModel(int arrayDimensions, TypeModel... typeArguments) {
        if (TypeModelPool.containsTypeModel(this, arrayDimensions, typeArguments))
            return TypeModelPool.getTypeModel(this, arrayDimensions, typeArguments).orElseThrow();

        // Create an intersection type model from all constituent class models
        TypeModel[] typeModels = new TypeModel[classModels.length];
        for (int i = 0; i < classModels.length; i++) {
            typeModels[i] = classModels[i].asTypeModel(0, typeArguments);
        }
        return TypeModelPool.addTypeModel(new IntersectionTypeModelImpl(arrayDimensions, typeModels));
    }

    @Override
    public boolean isSubclassOf(ClassModel other) {
        // An intersection type is a subclass of another type if any of its constituent types
        // is a subclass of the other type
        for (ClassModel classModel : classModels) {
            if (classModel.isSubclassOf(other)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if this intersection class model is satisfied by the given class model.
     * A class model satisfies an intersection if it's a subclass of all constituent class models.
     */
    public boolean isSatisfiedBy(ClassModel classModel) {
        for (ClassModel requiredClass : classModels) {
            if (!classModel.isSubclassOf(requiredClass)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toPrettyString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IntersectionClassModelImpl:\n");
        sb.append("  Constituent Types:\n");
        for (int i = 0; i < classModels.length; i++) {
            sb.append("    ").append(i + 1).append(". ").append(classModels[i].name()).append("\n");
        }
        sb.append("  Combined Members: ").append(members().length).append("\n");
        sb.append("  Combined Interfaces: ").append(interfaces().length).append("\n");
        return sb.toString();
    }

    @Override
    public boolean isInterface() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IntersectionClassModelImpl other)) return false;
        return Arrays.equals(classModels, other.classModels);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(classModels);
    }

    @Override
    public String toString() {
        return "IntersectionClassModelImpl{" +
                "name='" + name + '\'' +
                ", classModels=" + Arrays.toString(classModels) +
                '}';
    }
}
