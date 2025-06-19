package lum.core.impl;

class MethodModelProcessor {
    private final TypeProcessor typeProcessor;
    private final GenericsProcessor genericsProcessor;

    public MethodModelProcessor(TypeProcessor typeProcessor, GenericsProcessor genericsProcessor) {
        this.typeProcessor = typeProcessor;
        this.genericsProcessor = genericsProcessor;
    }
}
