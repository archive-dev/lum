package lum.core.util;

import lum.core.model.ClassModel;
import lum.core.model.TypeModel;

import java.util.*;

public final class TypeModelList extends ArrayList<TypeModel> {
    public TypeModelList(SequencedCollection<TypeModel> arguments) {
        super(arguments);
    }

    public TypeModelList(TypeModel[] arguments) {
        super(List.of(arguments));
    }

    private final WeakHashMap<List<TypeModel>, Boolean> isAssignableFromCache = new WeakHashMap<>();

    public boolean isAssignableFrom(List<TypeModel> other) {
        if (isAssignableFromCache.containsKey(other))
            return isAssignableFromCache.get(other);

        if (other.size() != this.size()) {
            isAssignableFromCache.put(other, false);
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!other.get(i).model().isSubclassOf(this.get(i).model())) {
                isAssignableFromCache.put(other, false);
                return false;
            }
        }
        isAssignableFromCache.put(other, true);
        return true;
    }

    /**
     * Computes the common ancestor of all TypeModel elements in this list.
     * If the list is empty, returns TypeModel.OBJECT.
     *
     * The computation is done by converting each TypeModel to its underlying
     * Class object and determining the lowest common superclass using reflection.
     *
     * @return a TypeModel representing the common ancestor of all types in the list
     */
    public TypeModel getCommonAncestor() {
        if (this.isEmpty()) {
            return TypeModel.OBJECT;
        }

        // Retrieve the underlying class of the first type.
        ClassModel commonAncestor = this.get(0).model();

        // Iterate through all remaining types and find the common ancestor.
        for (int i = 1; i < this.size(); i++) {
            ClassModel currentClass = this.get(i).model();
            commonAncestor = findCommonAncestor(commonAncestor, currentClass);
            // If we reached Object, it's the only possible common ancestor.
            if (commonAncestor.equals(ClassModel.of(Object.class))) {
                break;
            }
        }

        return commonAncestor.typeModel();
    }

    /**
     * Helper method to compute the lowest common ancestor (LCA) of two classes.
     * This method builds the chain of superclasses for both classes and then
     * determines the first common class in the chain of the second class.
     *
     * @param a the first class
     * @param b the second class
     * @return the lowest common ancestor class
     */
    private static ClassModel findCommonAncestor(ClassModel a, ClassModel b) {
        Set<ClassModel> ancestors = new HashSet<>();
        // Build the set of ancestors for class a.
        for (ClassModel current = a; current != null; current = current.superClass()) {
            ancestors.add(current);
        }
        // Traverse the ancestry chain of b and return the first common ancestor.
        for (ClassModel current = b; current != null; current = current.superClass()) {
            if (ancestors.contains(current)) {
                return current;
            }
        }
        // Fallback, though this should never occur as Object is common to all classes.
        return ClassModel.of(Object.class);
    }
}
