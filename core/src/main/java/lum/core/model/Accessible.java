package lum.core.model;

import java.lang.reflect.AccessFlag;

public interface Accessible {
    AccessFlag[] accessFlags();

    default boolean isStatic() {
        for (var flag :
                accessFlags()) {
            if (flag == AccessFlag.STATIC)
                return true;
        }
        return false;
    }

    default boolean isPrivate() {
        for (var flag :
                accessFlags()) {
            if (flag == AccessFlag.PRIVATE)
                return true;
        }
        return false;
    }

    default boolean isPublic() {
        for (var flag :
                accessFlags()) {
            if (flag == AccessFlag.PUBLIC)
                return true;
        }
        return false;
    }

    default boolean isProtected() {
        for (var flag :
                accessFlags()) {
            if (flag == AccessFlag.PROTECTED)
                return true;
        }
        return false;
    }

    default boolean isAbstract() {
        for (var flag :
                accessFlags()) {
            if (flag == AccessFlag.ABSTRACT)
                return true;
        }
        return false;
    }

    default boolean isFinal() {
        for (var flag :
                accessFlags()) {
            if (flag == AccessFlag.FINAL)
                return true;
        }
        return false;
    }
}
