package lum.compiler.bytecode;

import java.lang.reflect.AccessFlag;

public interface Accessible {
    Accessible access(AccessFlag flag);
    Accessible access(AccessFlag... flags);
}
