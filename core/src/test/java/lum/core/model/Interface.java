package lum.core.model;

interface Interface {
    void interfaceMethod();

    default void defaultMethod() {
        System.out.println("default");
    }
}
