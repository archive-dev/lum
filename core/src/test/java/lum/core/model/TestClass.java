package lum.core.model;

import java.io.File;

@SuppressWarnings("unused")
class TestClass extends SuperClass implements Interface {
    private int privateField;
    protected String protectedField;
    public File publicField;
    float packagePrivateField;

    static final int CONSTANT_FIELD = 42;
    volatile boolean volatileField;
    transient long transientField;

    public static void main(String[] args) {
    }

    private void privateMethod() {
    }

    protected void protectedMethod() {
    }

    public void publicMethod() {
    }

    void packagePrivateMethod() {
    }

    public File getFileMethod() {
        return null;
    }

    public void methodWithParameters(int a, float b, File file) {
    }

    @Override
    public void interfaceMethod() {

    }
}
