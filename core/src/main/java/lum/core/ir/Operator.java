package lum.core.ir;

public sealed interface Operator {
    enum BinaryOperator implements Operator {
        PLUS("add"),
        MINUS("subtract"),
        MUL("multiply"),
        DIVIDE("divide"),
        DIV("div"),
        MOD("mod"),
        SHL("shl"),
        SHR("shr"),
        OR("or"),
        AND("and"),
        XOR("xor"),
        GT("greaterThan"),
        LT("lessThan"),
        GE("greaterEquals"),
        LE("lessEquals"),
        EQ("equals"),
        IS_INSTANCE("isInstance"),
        IN("contains"),
        AT("at");

        private final String methodName;

        BinaryOperator(String methodName) {
            this.methodName = methodName;
        }

        public String methodName() {
            return methodName;
        }
    }

    enum UnaryOperator implements Operator {
        PLUS("positive"),
        MINUS("negate"),
        INC("increment"),
        DEC("decrement"),
        NOT("not"),
        TILDA("tilda"),
        ARRAY_ACCESS("get", "set");

        private final String prefixMethodName,
                             postfixMethodName;

        UnaryOperator(String prefixMethodName, String postfixMethodName) {
            this.prefixMethodName = prefixMethodName;
            this.postfixMethodName = postfixMethodName;
        }

        UnaryOperator(String prefixMethodName) {
            this.prefixMethodName = prefixMethodName;
            this.postfixMethodName = prefixMethodName;
        }

        public String prefixMethodName() {
            return prefixMethodName;
        }

        public String postfixMethodName() {
            return postfixMethodName;
        }
    }
}