package lum.core.model;

import java.lang.reflect.AccessFlag;
import java.util.Set;

public interface Accessible {
    Set<AccessFlag> accessFlags();
    default int intAccessFlags() {
        return accessFlags().stream().reduce(0, (iFlag, flag) -> iFlag | flag.mask(), (f,f2) -> f | f2);
    }

    default boolean isStatic() {
        return accessFlags().contains(AccessFlag.STATIC);
    }

    default boolean isPrivate() {
        return accessFlags().contains(AccessFlag.PRIVATE);
    }

    default boolean isProtected() {
        return accessFlags().contains(AccessFlag.PROTECTED);
    }

    default boolean isPublic() {
        return accessFlags().contains(AccessFlag.PUBLIC);
    }

    default boolean isFinal() {
        return accessFlags().contains(AccessFlag.FINAL);
    }

    default boolean isAbstract() {
        return accessFlags().contains(AccessFlag.ABSTRACT);
    }

    default boolean isVolatile() {
        return accessFlags().contains(AccessFlag.VOLATILE);
    }

    default boolean isTransient() {
        return accessFlags().contains(AccessFlag.TRANSIENT);
    }
}
