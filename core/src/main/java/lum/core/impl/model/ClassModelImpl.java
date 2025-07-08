package lum.core.impl.model;

import lum.antlr4.LumParser;
import lum.core.model.*;
import lum.core.util.Utils;
import lum.lang.struct.Either;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AccessFlag;
import java.util.*;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
final class ClassModelImpl implements ClassModel {
    private static final Map<ClassModel, Member[]> allMembers = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(ClassModelImpl.class);
    private final AttributeModel[] attributes;
    private final AccessFlag[] accessFlags;
    private final String name;
    private final Optional<TypeParameter[]> typeParameters;
    private Optional<ClassModel> superClass;
    private final ClassModel[] interfaces;
    private final Member[] members;

    final Either<Class<?>, LumParser.TypeDeclarationContext> clazzOrCtx;

    public ClassModelImpl(
            AttributeModel[] attributes,
            AccessFlag[] accessFlags,
            String name,
            Optional<TypeParameter[]> typeParameters,
            Optional<ClassModel> superClass,
            ClassModel[] interfaces,
            Member[] members,
            Class<?> clazz
    ) {
        this(attributes, accessFlags, name, typeParameters, superClass, interfaces, members, new Either.Left<>(clazz));
    }

    public ClassModelImpl(
            AttributeModel[] attributes,
            AccessFlag[] accessFlags,
            String name,
            Optional<TypeParameter[]> typeParameters,
            Optional<ClassModel> superClass,
            ClassModel[] interfaces,
            Member[] members,
            LumParser.TypeDeclarationContext ctx
    ) {
        this(attributes, accessFlags, name, typeParameters, superClass, interfaces, members, new Either.Right<>(ctx));
    }

    private ClassModelImpl(
            AttributeModel[] attributes,
            AccessFlag[] accessFlags,
            String name,
            Optional<TypeParameter[]> typeParameters,
            Optional<ClassModel> superClass,
            ClassModel[] interfaces,
            Member[] members,
            Either<Class<?>, LumParser.TypeDeclarationContext> clazzOrCtx
    ) {
        this.attributes = attributes;
        this.accessFlags = accessFlags;
        this.name = name;
        this.typeParameters = typeParameters;
        this.superClass = superClass;
        this.interfaces = interfaces;
        this.members = members;
        this.clazzOrCtx = clazzOrCtx;
    }

    @Override
    public Member[] allMembers() {
        if (allMembers.containsKey(this))
            return allMembers.get(this);

        if (superClass().isEmpty())
            return members();

        Member[] superAllMembers = superClass().get().allMembers();
        var members = new Member[
                superAllMembers.length + members().length
                        + Arrays.stream(interfaces())
                        .reduce(0,
                                (n, m) -> n + m.allMembers().length,
                                Integer::sum)
                ];

        System.arraycopy(superAllMembers, 0, members, 0, superAllMembers.length);
        System.arraycopy(members(), 0, members, superAllMembers.length, members().length);
        for (var inter : interfaces()) {
            int len = inter.allMembers().length;
            System.arraycopy(inter.allMembers(), 0, members, superAllMembers.length + len, len);
        }

        ClassModelImpl.allMembers.put(this, members);

        return members;
    }

    @Override
    public Optional<MethodModel> getMethod(String name, TypeModel... parameterTypes) {
        return Arrays.stream(allMembers())
                .filter(Objects::nonNull)
                .filter(m -> m instanceof MethodModel)
                .filter(m -> m.name().equals(name))
                .map(m -> ((MethodModel) m))
                .filter(method ->
                        areSubtypesOf(
                                Arrays.stream(method.parameters())
                                            .map(ParameterModel::type)
                                            .toArray(TypeModel[]::new),
                                parameterTypes
                        )
                ).findFirst();
    }

    private static boolean areSubtypesOf(TypeModel[] types, TypeModel[] subtypes) {
        if (types.length != subtypes.length) return false;

        for (int i = 0; i < types.length; i++) {
            var type = types[i];
            var subtype = subtypes[i];

            if (subtype.arrayDimensions() != type.arrayDimensions())
                return false;
            if (!subtype.model().isSubclassOf(type.model()))
                return false;
        }

        return true;
    }

    @Override
    public Optional<FieldModel> getField(String name) {
        return Arrays.stream(allMembers())
                .filter(m -> m instanceof FieldModel)
                .filter(m -> m.name().equals(name))
                .map(m -> ((FieldModel) m))
                .findFirst();
    }

    @Override
    public TypeModel asTypeModel(int arrayDimensions, TypeArgument... typeArguments) {
        if (typeParameters().isEmpty() && typeArguments.length > 0)
            log.warn("Class {} does not contain any TypeParameter`s, typeArguments will be ignored", name());

        TypeModel[] typeArgs = Arrays.stream(typeArguments).map(TypeArgument::type).toArray(TypeModel[]::new);
        if (TypeModelPool.containsTypeModel(this, arrayDimensions, typeArgs))
            return TypeModelPool.getTypeModel(this, arrayDimensions, typeArgs).orElseThrow();

        return TypeModelPool.addTypeModel(new TypeModelImpl(
                this, arrayDimensions, typeParameters().isEmpty() ? Optional.empty() : Optional.of(typeArguments)
        ));
    }

    @Override
    public TypeModel asTypeModel(int arrayDimensions, TypeModel... typeArguments){
        if (typeParameters().isEmpty())
            throw new IllegalStateException("Provided class has no type parameters, but %d type arguments were provided"
                    .formatted(typeArguments.length)
            );

        if (TypeModelPool.containsTypeModel(this, arrayDimensions, typeArguments))
            return TypeModelPool.getTypeModel(this, arrayDimensions, typeArguments).orElseThrow();

        TypeParameter[] typeParameters = typeParameters().get();
        TypeArgument[] args = new TypeArgument[typeArguments.length];
        for (int i = 0; i < typeArguments.length; i++) {
            var param = typeParameters[i];
            var arg = typeArguments[i];

            args[i] = new TypeArgumentImpl(param, arg);
        }

        return asTypeModel(arrayDimensions, args);
    }

    @Override
    public boolean isSubclassOf(ClassModel other) {
        if (this.equals(other)) {
            return true;
        }

        // Check superclass hierarchy
        Optional<ClassModel> currentSuper = this.superClass();
        while (currentSuper.isPresent()) {
            if (currentSuper.get().equals(other)) {
                return true;
            }
            currentSuper = currentSuper.get().superClass();
        }

        // Check interfaces (recursively)
        for (ClassModel iface : this.interfaces()) {
            if (iface.isSubclassOf(other)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Optional<ClassModel> owner() {
        return Optional.empty();
    }

    @Override
    public @NotNull String toString() {
        return "ClassModelImpl{" +
                "attributes=" + Arrays.toString(attributes) +
                ", accessFlags=" + Arrays.toString(accessFlags) +
                ", name='" + name + '\'' +
                ", superClass=" + superClass +
                ", interfaces=" + Arrays.toString(interfaces) +
                ", members=" + Arrays.toString(members) +
                '}';
    }

    public String toPrettyString() {
        String s = """
                %s:
                Attributes: %s
                AccessFlags: %s
                Superclass: %s
                Interfaces: {
                    %s
                }
                Members: {
                    %s
                }
                """;
        return s.formatted(
                name(),
                Arrays.toString(attributes()),
                Arrays.toString(accessFlags()),
                superClass().isPresent() ? superClass().get().name() : "",
                String.join("\n\t", Arrays.stream(interfaces()).map(ClassModel::name).toArray(String[]::new)),
                String.join("\n\t", Arrays.stream(members()).map(
                        m -> switch (m) {
                            case MethodModel method -> "Method: " + method.name();
                            case FieldModel field -> "Field: " + field.name();
                            case ClassModel clazz -> "Class: " + clazz.name();
                            default -> throw new IllegalStateException("Unexpected value: " + m);
                        }
                ).toList())
        );
    }

    @Override
    public boolean isInterface() {
        return clazzOrCtx.fold(
                Class::isInterface,
                ctx -> ctx instanceof LumParser.InterfaceDeclarationContext
        );
    }

    @Override
    public AttributeModel[] attributes() {
        return attributes;
    }

    @Override
    public AccessFlag[] accessFlags() {
        return accessFlags;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public Optional<TypeParameter[]> typeParameters() {
        return typeParameters;
    }

    @Override
    public Optional<ClassModel> superClass() {
        return superClass;
    }

    @Override
    public ClassModel[] interfaces() {
        return interfaces;
    }

    @Override
    public Member[] members() {
        return members;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ClassModelImpl) obj;
        return Arrays.equals(this.attributes, that.attributes) &&
                Arrays.equals(this.accessFlags, that.accessFlags) &&
                Objects.equals(this.name, that.name) &&
                Objects.equals(this.typeParameters, that.typeParameters) &&
                Objects.equals(this.superClass, that.superClass) &&
                Arrays.equals(this.interfaces, that.interfaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(attributes), Arrays.hashCode(accessFlags), name, typeParameters, superClass, Arrays.hashCode(interfaces), Objects.hashCode(members));
    }

    void setSuperClass(Optional<ClassModel> classModel) {
        superClass = classModel;
    }
}
