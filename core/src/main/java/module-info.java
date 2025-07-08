module lum.core.main {
    exports lum.core.ir;
    exports lum.core.model;

    opens lum.core.impl.ir;
    opens lum.core.impl.model;

    requires lum.runtime.main;
    requires transitive lum.grammar.main;

    requires org.antlr.antlr4.runtime;
    requires org.jetbrains.annotations;
    requires org.slf4j;
}