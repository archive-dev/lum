package lum.compiler;

import java.lang.classfile.CodeBuilder;
import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        Consumer<CodeBuilder> c = cb -> {
            cb.with(null);
        };
    }
}
