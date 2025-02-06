package lum.core.model;

import java.lang.reflect.AccessFlag;
import java.util.List;

public interface Accessible {
    List<AccessFlag> accessFlags();
    default int intAccessFlags() {
        return accessFlags().stream().reduce(0, (iFlag, flag) -> iFlag | flag.mask(), (f,f2) -> f | f2);
    }
}
