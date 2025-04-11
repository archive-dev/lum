package lum.core.model;

import java.lang.classfile.TypeKind;
import java.lang.constant.ClassDesc;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public interface TypeModel extends Parametrized {
    TypeModel VOID    = TypeModel.of(void.class);
    TypeModel BOOLEAN = TypeModel.of(boolean.class);
    TypeModel BYTE    = TypeModel.of(byte.class);
    TypeModel SHORT   = TypeModel.of(short.class);
    TypeModel CHAR    = TypeModel.of(char.class);
    TypeModel INT     = TypeModel.of(int.class);
    TypeModel LONG    = TypeModel.of(long.class);
    TypeModel DOUBLE  = TypeModel.of(double.class);
    TypeModel FLOAT   = TypeModel.of(float.class);
    TypeModel STRING  = TypeModel.of(String.class);
    TypeModel OBJECT  = TypeModel.of(Object.class);

    TypeModel asArray(int dimensions);

    TypeModel asComponent();

    TypeModel asNonArray();

    ClassDesc classDesc();

    TypeKind typeKind();

    ClassModel model();

    int arrayDimensions();

    static TypeModel intersectionOf(TypeModel[] types) {
        return IntersectionClassModel.of(types).typeModel();
    }

    /// Calculates the Least Upper Bound (LUB) for a given set of types.
    /// This implementation prioritizes finding a single most specific common class or interface.
    /// If multiple most specific interfaces remain (and no single most specific class/interface),
    /// it falls back to finding the most specific common _ancestor class_.
    ///
    /// Note: This is a simplified implementation and might not cover all edge cases
    /// perfectly according to JLS LUB rules (e.g., complex generics, primitive types, arrays).
    /// It focuses on the class/interface hierarchy.
    ///
    /// @param types Varargs array of TypeModel objects.
    /// @return The TypeModel object representing the Least Upper Bound.
    /// @throws IllegalArgumentException if types is null, empty, or contains null elements.
    /// @see <a href="https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.10.4">JLS 4.10.4</a>
    /// @see #getLeastUpperBound(TypeModel...)
    static TypeModel unionOf(TypeModel... types) {
        return getLeastUpperBound(types);
    }


    /// Calculates the Least Upper Bound (LUB) for a given set of reference type classes.
    /// This implementation prioritizes finding a single most specific common class or interface.
    /// If multiple most specific interfaces remain (and no single most specific class/interface),
    /// it falls back to finding the most specific common *ancestor class*.
    ///
    /// Note: This is a simplified implementation and might not cover all edge cases
    /// perfectly according to JLS LUB rules (e.g., complex generics, primitive types, arrays).
    /// It focuses on the class/interface hierarchy.
    ///
    /// @param classes Varargs array of TypeModel objects.
    /// @return The TypeModel object representing the Least Upper Bound.
    /// @throws IllegalArgumentException if classes is null, empty, or contains null elements.
    /// @see <a href="https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.10.4">JLS 4.10.4</a>
    private static TypeModel getLeastUpperBound(TypeModel... classes) {
        int arrayDimensions = getArrayDimensions(classes);

        // --- Input Validation ---
        if (classes == null || classes.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty.");
        }
        if (classes.length == 1) {
            return classes[0]; // LUB of one class is the class itself
        }

        List<TypeModel> classList = new ArrayList<>();
        for (int i = 0; i < classes.length; i++) {
            if (classes[i] == null) {
                throw new IllegalArgumentException("Input array cannot contain null elements (at index " + i + ").");
            }
            // Basic handling for primitives - treat them as their boxed types for hierarchy analysis
            // A more robust solution might handle primitive widening etc.
            classList.add(classes[i].isPrimitive() ? getWrapperClass(classes[i]) : classes[i]);
        }

        // --- Step 1 & 2: Find Common Ancestors ---
        Set<TypeModel> commonAncestors = null;

        for (TypeModel clazz : classList) {
            Set<TypeModel> ancestors = getAllAncestors(clazz);
            if (commonAncestors == null) {
                commonAncestors = new HashSet<>(ancestors);
            } else {
                commonAncestors.retainAll(ancestors);
                if (commonAncestors.isEmpty()) {
                    // Should not happen if Object.class is always included,
                    // but as a safeguard:
                    return TypeModel.OBJECT.asArray(arrayDimensions);
                }
            }
        }

        if (commonAncestors == null || commonAncestors.isEmpty()) {
            // If input was valid, Object.class should always be common
            return TypeModel.OBJECT.asArray(arrayDimensions);
        }


        // --- Step 3: Find the Most Specific Ancestors ---
        Set<TypeModel> mostSpecificAncestors = new HashSet<>(commonAncestors);

        // Remove any ancestor A if there is another ancestor B such that A is assignable from B (B is more specific)
        List<TypeModel> commonList = new ArrayList<>(commonAncestors); // Use list for stable iteration? Not strictly needed here.
        for (TypeModel candidateA : commonList) {
            for (TypeModel candidateB : commonList) {
                if (candidateA != candidateB && candidateA.model().isAssignableFrom(candidateB.model())) {
                    // candidateA is less specific than candidateB, remove candidateA
                    mostSpecificAncestors.remove(candidateA);
                    break; // No need to compare candidateA further
                }
            }
        }

        // --- Step 4: Determine Final LUB ---
        if (mostSpecificAncestors.isEmpty()) {
            // Should theoretically not happen if Object is always there
            return TypeModel.OBJECT.asArray(arrayDimensions);
        }

        if (mostSpecificAncestors.size() == 1) {
            // Found a single most specific common ancestor (class or interface)
            return mostSpecificAncestors.iterator().next().asArray(arrayDimensions);
        } else {
            // Multiple most specific ancestors remain - they must be interfaces.
            // Fallback: find the most specific *class* among all common ancestors.
            Set<TypeModel> commonAncestorClasses = commonAncestors.stream()
                    .filter(c -> !c.model().isInterface())
                    .collect(Collectors.toSet());

            if (commonAncestorClasses.isEmpty()) {
                // This case is odd - implies only common interfaces and Object?
                // Should not happen if Object is always an ancestor class.
                return TypeModel.OBJECT.asArray(arrayDimensions);
            }

            Set<TypeModel> mostSpecificClasses = new HashSet<>(commonAncestorClasses);
            List<TypeModel> commonClassList = new ArrayList<>(commonAncestorClasses);
            for (TypeModel candidateA : commonClassList) {
                for (TypeModel candidateB : commonClassList) {
                    if (candidateA != candidateB && candidateA.model().isAssignableFrom(candidateB.model())) {
                        mostSpecificClasses.remove(candidateA);
                        break;
                    }
                }
            }

            // There should be only one most specific common class (could be Object.class)
            if (mostSpecificClasses.size() == 1) {
                return mostSpecificClasses.iterator().next().asArray(arrayDimensions);
            } else {
                // If even after filtering classes, multiple remain (e.g. error in logic or weird hierarchy?),
                // returning Object is the safest fallback.
                // This might happen in complex scenarios not fully handled here.
                System.err.println("Warning: Could not determine single most specific common class among: " + mostSpecificClasses + ". Falling back to Object.class");
                return TypeModel.OBJECT.asArray(arrayDimensions);
            }
        }
    }

    /**
     * Gets all ancestors (superclasses and interfaces) of a given class, including the class itself.
     *
     * @param clazz The class to analyze.
     * @return A Set containing the class and all its unique ancestors.
     */
    private static Set<TypeModel> getAllAncestors(TypeModel clazz) {
        Set<TypeModel> ancestors = new LinkedHashSet<>(); // Use LinkedHashSet to maintain some order (optional)
        Queue<TypeModel> toProcess = new LinkedList<>();

        ancestors.add(clazz);
        toProcess.add(clazz);

        while (!toProcess.isEmpty()) {
            TypeModel current = toProcess.poll();

            // Add direct superclass
            TypeModel superClass = current.model().superClass() == null ? null : current.model().superClass().typeModel();
            if (superClass != null && ancestors.add(superClass)) {
                toProcess.add(superClass);
            }

            // Add direct interfaces
            for (ClassModel iface : current.model().interfaces()) {
                if (ancestors.add(iface.typeModel())) {
                    toProcess.add(iface.typeModel()); // Also process super-interfaces
                }
            }
        }
        return ancestors;
    }

    /** Helper to get wrapper class for primitive types */
    private static TypeModel getWrapperClass(TypeModel primitive) {
        if (primitive.equals(TypeModel.INT)) return TypeModel.of(Integer.class);
        if (primitive.equals(TypeModel.LONG)) return TypeModel.of(Long.class);
        if (primitive.equals(TypeModel.DOUBLE)) return TypeModel.of(Double.class);
        if (primitive.equals(TypeModel.FLOAT)) return TypeModel.of(Float.class);
        if (primitive.equals(TypeModel.BOOLEAN)) return TypeModel.of(Boolean.class);
        if (primitive.equals(TypeModel.BYTE)) return TypeModel.of(Byte.class);
        if (primitive.equals(TypeModel.CHAR)) return TypeModel.of(Character.class);
        if (primitive.equals(TypeModel.SHORT)) return TypeModel.of(Short.class);
        if (primitive.equals(TypeModel.VOID)) return TypeModel.of(Void.class); // Though LUB with void is tricky
        return primitive; // Should not happen if called only for primitives
    }

    private static int getArrayDimensions(TypeModel... types) {
        // Reduce operation will fail only if a type array is empty
        return Arrays.stream(types).map(TypeModel::arrayDimensions).min(Integer::compare).orElse(0);
    }

    default GenericTypeModel asGeneric(String genericName) {
        return new GenericTypeModel(genericName, model(), arrayDimensions(), genericArguments());
    }

    default TypeModel withGenericArguments(GenericArgument[] arguments) {
        return new TypeModelImpl(model(), arrayDimensions(), arguments);
    }

    static TypeModel of(Object value) {
        return switch (value) {
            case Boolean _ -> TypeModel.BOOLEAN;
            case Byte _ -> TypeModel.BYTE;
            case Short _ -> TypeModel.SHORT;
            case Character _ -> TypeModel.CHAR;
            case Integer _ -> TypeModel.INT;
            case Long _ -> TypeModel.LONG;
            case Double _ -> TypeModel.DOUBLE;
            case Float _ -> TypeModel.FLOAT;
            case String _ -> TypeModel.STRING;
            case null -> TypeModel.OBJECT;
            default -> TypeModel.of(value.getClass());
        };
    }

    static TypeModel of(TypeKind typeKind) {
        return switch (typeKind) {
            case BYTE -> TypeModel.BYTE;
            case SHORT -> TypeModel.SHORT;
            case INT -> TypeModel.INT;
            case FLOAT -> TypeModel.FLOAT;
            case LONG -> TypeModel.LONG;
            case DOUBLE -> TypeModel.DOUBLE;
            case REFERENCE -> TypeModel.OBJECT;
            case CHAR -> TypeModel.CHAR;
            case BOOLEAN -> TypeModel.BOOLEAN;
            case VOID -> TypeModel.VOID;
            case null -> null;
        };
    }

    static TypeModel of(Class<?> clazz) {
        return ModelCache.getTypeModel(clazz);
    }

    static TypeModel of(String className) {
        try {
            return of(Class.forName(className));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static TypeModel of(Type type) {
        var name = type.getTypeName();
        name = name.replaceAll("<.*>", "");
        return of(name);
    }

    default boolean isArray() {
        return arrayDimensions()!=0;
    }

    default boolean isPrimitive() {
        return false;
    }

}
