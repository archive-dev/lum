package lum.compiler.codegen;

import java.lang.reflect.AccessFlag;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public interface Accessible {
    Accessible access(AccessFlag flag);
    Accessible access(AccessFlag... flags);
}
