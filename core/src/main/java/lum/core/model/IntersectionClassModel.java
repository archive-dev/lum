package lum.core.model;

import lum.core.util.TypeModelList;
import lum.core.util.Utils;
import org.jetbrains.annotations.Nullable;

import java.lang.constant.ClassDesc;
import java.lang.reflect.AccessFlag;
import java.util.*;

class IntersectionClassModel extends ClassModel {
    static IntersectionClassModel of(ClassModel[] interfaces) {
        return new IntersectionClassModel(interfaces);
    }

    static IntersectionClassModel of(TypeModel[] interfaces) {
        return new IntersectionClassModel(Arrays.stream(interfaces).map(TypeModel::model).toArray(ClassModel[]::new));
    }

    private final ClassModel[] interfaces;

    IntersectionClassModel(ClassModel[] interfaces) {
        this.interfaces = interfaces;
    }

    @Override
    public String name() {
        return String.join(" & ", Arrays.stream(interfaces()).map(ClassModel::fullName).toList());
    }

    @Override
    public String pkg() {
        return null;
    }

    @Override
    public ClassModel superClass() {
        return null;
    }

    @Override
    void setSuperClass(ClassModel value) {}

    @Override
    public ClassModel[] interfaces() {
        return interfaces;
    }

    @Override
    public boolean isSubclassOf(ClassModel other) {
        for (var iface : interfaces()) {
            if (!iface.isSubclassOf(other))
                return false;
        }

        return true;
    }

    @Override
    public boolean isAssignableFrom(ClassModel other) {
        for (var iface : interfaces()) {
            if (!other.isSubclassOf(iface))
                return false;
        }

        return true;
    }

    @Override
    public boolean isInterface() {
        return Arrays.stream(interfaces()).allMatch(ClassModel::isInterface);
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    public TypeModel typeModel(int arrayDimensions) {
        return new IntersectionTypeModel(this, arrayDimensions);
    }

    @Override
    public ClassDesc classDesc() {
        return interfaces()[0].classDesc();
    }

    @Override
    public ClassDesc classDesc(int arrayDimensions) {
        return interfaces()[0].classDesc(arrayDimensions);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ClassModel) obj;
        return Objects.equals(this.name(), that.name()) &&
                Objects.equals(this.pkg(), that.pkg()) &&
                Objects.equals(this.superClass(), that.superClass()) &&
                Arrays.equals(this.interfaces(), that.interfaces()) &&
                Objects.equals(this.accessFlags(), that.accessFlags()) &&
                Arrays.equals(this.genericArguments(), that.genericArguments());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName(), superClass(), Arrays.hashCode(interfaces()), accessFlags(), Arrays.hashCode(genericArguments()));
    }

    @Override
    public @Nullable MethodModel getMethod(String name, TypeModel... parameters) {
        return getMethod(name, List.of(parameters));
    }

    @Override
    protected MethodModel getMethodFromClassHierarchy(ClassModel startClass, String name, TypeModel... parameters) {
        return null;
    }

    @Override
    protected MethodModel getMethodFromInterfaces(String name, TypeModel... parameters) {
        return null;
    }

    @Override
    public MethodModel getMethod(String name, List<TypeModel> parameters) {
        return Arrays.stream(methods())
                .filter(m -> m.name().equals(name))
                .filter(m -> new TypeModelList(parameters).isAssignableFrom(Arrays.stream(m.parameters()).map(ParameterModel::type).toList()))
                .findFirst().orElse(null);
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    @Override
    public MethodModel[] methods() {
        return Arrays.stream(interfaces()).map(ClassModel::methods).collect(HashSet::new,
                (set, arr) -> {
                    set.addAll(List.of(arr));
                },
                (left, right) -> {
                    if (left.size() > right.size()) {
                        left.addAll(right);
                    } else {
                        right.addAll(left);
                    }
                }).toArray(MethodModel[]::new);
    }

    @Override
    public FieldModel getField(String name) {
        return Arrays.stream(fields())
                .filter(m -> m.name().equals(name))
                .findFirst().orElse(null);
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    @Override
    public FieldModel[] fields() {
        return Arrays.stream(interfaces()).map(ClassModel::fields).collect(HashSet::new,
                (set, arr) -> {
                    set.addAll(List.of(arr));
                },
                (left, right) -> {
                    if (left.size() > right.size()) {
                        left.addAll(right);
                    } else {
                        right.addAll(left);
                    }
                }).toArray(FieldModel[]::new);
    }

    @Override
    public Set<AccessFlag> accessFlags() {
        return Set.of(AccessFlag.PUBLIC, AccessFlag.FINAL);
    }

    @Override
    public ClassModel[] annotations() {
        return Utils.EMPTY_CLASS_MODELS;
    }

    @Override
    public GenericArgument[] genericArguments() {
        return Utils.EMPTY_GENERIC_ARGUMENTS;
    }
}
