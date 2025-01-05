package lum.core.model;

import lum.core.util.Utils;
import lum.core.parser.LumParser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.AccessFlag;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public final class ClassModel {
    private String name;
    private final HashSet<FieldModel> fields;
    private final HashSet<MethodModel> methods;
    private final List<AttributeModel> attributes;
    private ClassModel superClass;
    private final HashSet<ClassModel> interfaces;
    private final List<GenericParameter> typeParameters;
    private AccessFlag[] accessFlags;
    private boolean isInterface;

    private final int arrayDepth;

    ClassModel(
            String name,
            HashSet<FieldModel> fields,
            HashSet<MethodModel> methods,
            List<AttributeModel> attributes,
            ClassModel superClass,
            HashSet<ClassModel> interfaces,
            List<GenericParameter> typeParameters,
            AccessFlag[] accessFlags,
            boolean isInterface,
            int arrayDepth
    ) {
        this.name = name;
        this.fields = fields;
        this.methods = methods;
        this.attributes = attributes;
        this.superClass = superClass;
        this.interfaces = interfaces;
        this.typeParameters = typeParameters;
        this.accessFlags = accessFlags;
        this.isInterface = isInterface;
        this.arrayDepth = arrayDepth;
    }

    static ClassModel from(Class<?> clazz) {
        if (clazz == null) return null;
        if (ClassModelPool.contains(clazz))
            return ClassModelPool.get(clazz);
        int arrayDepth = Utils.getArrayDepth(clazz);
        Class<?> componentType = Utils.getComponentType(clazz);
        final ClassModel classModel = new ClassModel(
                null,
                new HashSet<>(),
                new HashSet<>(),
                new ArrayList<>(),
                from(componentType.getSuperclass()),
                new HashSet<>(),
                new ArrayList<>(),
                null,
                false,
                arrayDepth
        );
        ClassModelPool.put(clazz, classModel);

        // Get class name
        String className = componentType.getName();

        // Get fields
        HashSet<FieldModel> fields = Arrays.stream(componentType.getDeclaredFields())
                .map(FieldModel::from)
                .collect(Collectors.toCollection(HashSet::new));

        // Get methods
        HashSet<MethodModel> methods = Arrays.stream(componentType.getDeclaredMethods())
                .map(MethodModel::from)
                .collect(Collectors.toCollection(HashSet::new));

        // Get attributes
        List<AttributeModel> annotations = Arrays.stream(componentType.getAnnotations())
                .map(AttributeModel::from)
                .toList();

        // Get superclass
        ClassModel superClass = ClassModel.from(componentType.getSuperclass());

        // Get interfaces
        HashSet<ClassModel> interfaces = Arrays.stream(componentType.getInterfaces())
                .map(ClassModel::from)
                .collect(Collectors.toCollection(HashSet::new));

        // Get type parameters
        List<GenericParameter> typeParameters = List.of();
//        List<String> typeParameters = Arrays.stream(clazz.getTypeParameters())
//                .map(TypeVariable::getName)
//                .collect(Collectors.toList());

        // Get access flags (you might need to implement a conversion from java.lang.reflect.Modifier)
//        AccessFlags accessFlags = convertModifiersToAccessFlags(clazz.getModifiers());
        AccessFlag[] accessFlags = clazz.accessFlags().toArray(AccessFlag[]::new);

        // Check if interface
        boolean isInterface = componentType.isInterface();

        classModel.name = className;
        classModel.fields.addAll(fields);
        classModel.methods.addAll(methods);
        classModel.attributes.addAll(annotations);
        classModel.superClass = superClass;
        classModel.interfaces.addAll(interfaces);
        classModel.typeParameters.addAll(typeParameters);
        classModel.accessFlags = accessFlags;
        classModel.isInterface = isInterface;

        return classModel;
    }

    static ClassModel from(ClassPath classPath) {
        switch (classPath.extension()) {
            case LUM -> {
                return switch (classPath.getAnyContext()) {
                    case LumParser.ClassDeclarationContext classDecl -> ClassModel.from(classDecl);
                    case LumParser.InterfaceDeclarationContext interfaceDecl -> ClassModel.from(interfaceDecl);
                    case LumParser.AnnotationDeclarationContext annotationDecl -> ClassModel.from(annotationDecl);
                    default -> throw new IllegalStateException();
                };
            }
            case CLASS -> { // test this
                try (URLClassLoader cl = new URLClassLoader(new URL[]{Path.of("").toUri().toURL()},ClassLoader.getSystemClassLoader())) {
                    return ClassModel.from(cl.loadClass(classPath.path().toString().replace(File.separator, ".") + "." + classPath.className()));
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            // case JAVA -> throw new UnsupportedOperationException();
            default -> throw new UnsupportedOperationException();
        }
    }

    static ClassModel from(LumParser.ClassDeclarationContext ctx) {
        String name = ctx.IDENTIFIER().getText();

        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());

        return new ClassModel(
                name,
                new HashSet<>(),
                new HashSet<>(),
                new ArrayList<>(),
                ClassModel.from(Object.class),
                new HashSet<>(),
                new ArrayList<>(),
                accessFlags,
                false,
                0
        );
    }

    static ClassModel from(LumParser.InterfaceDeclarationContext ctx) {
        String name = ctx.IDENTIFIER().getText();

        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());

        return new ClassModel(
                name,
                new HashSet<>(),
                new HashSet<>(),
                new ArrayList<>(),
                ClassModel.from(Object.class),
                new HashSet<>(),
                new ArrayList<>(),
                accessFlags,
                true,
                0
        );
    }

    static ClassModel from(LumParser.AnnotationDeclarationContext ctx) {
        String name = ctx.IDENTIFIER().getText();

        var accessFlags = Utils.getAccessFlags(ctx.access(), ctx.modifier());

        return new ClassModel(
                name,
                new HashSet<>(),
                new HashSet<>(),
                new ArrayList<>(),
                ClassModel.from(Object.class),
                new HashSet<>(),
                new ArrayList<>(),
                accessFlags,
                true,
                0
        );
    }

    public String name() {
        return name;
    }

    public HashSet<FieldModel> fields() {
        return fields;
    }

    public HashSet<MethodModel> methods() {
        return methods;
    }

    public List<AttributeModel> attributes() {
        return attributes;
    }

    public ClassModel superClass() {
        return superClass;
    }

    public HashSet<ClassModel> interfaces() {
        return interfaces;
    }

    public List<GenericParameter> typeParameters() {
        return typeParameters;
    }

    public AccessFlag[] accessFlags() {
        return accessFlags;
    }

    public boolean isInterface() {
        return isInterface;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ClassModel) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.fields, that.fields) &&
                Objects.equals(this.methods, that.methods) &&
                Objects.equals(this.attributes, that.attributes) &&
                Objects.equals(this.superClass, that.superClass) &&
                Objects.equals(this.interfaces, that.interfaces) &&
                Objects.equals(this.typeParameters, that.typeParameters) &&
                Arrays.equals(this.accessFlags, that.accessFlags) &&
                this.isInterface == that.isInterface &&
                this.arrayDepth == that.arrayDepth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, typeParameters, isInterface, arrayDepth);
    }

    @Override
    public String toString() {
        return "ClassModel[" +
                "name=" + name + ", " +
                "fields=" + fields + ", " +
                "methods=" + methods + ", " +
                "attributes=" + attributes + ", " +
                "superClass=" + superClass + ", " +
                "interfaces=" + interfaces + ", " +
                "typeParameters=" + typeParameters + ", " +
                "accessFlags=" + accessFlags + ", " +
                "isInterface=" + isInterface + ']';
    }
}
