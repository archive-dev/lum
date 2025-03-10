package lum.core.model;

import lum.core.parsing.ParserFactory;

import lum.core.parsing.antlr4.LumParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ParserFactoryFindDeclarationContextTest {


    // Returns class declaration context when matching class name is found in block
    @Test
    public void test_returns_class_declaration_when_name_matches() {
        LumParser.BlockContext block = mock(LumParser.BlockContext.class);
        LumParser.StatementContext stmt = mock(LumParser.StatementContext.class);
        LumParser.DeclarationContext decl = mock(LumParser.DeclarationContext.class);
        LumParser.ClassDeclarationContext classDecl = mock(LumParser.ClassDeclarationContext.class);
        TerminalNode identifier = mock(TerminalNode.class);

        when(block.statement()).thenReturn(Collections.singletonList(stmt));
        when(stmt.declaration()).thenReturn(decl);
        when(decl.getChild(0)).thenReturn(classDecl);
        when(classDecl.IDENTIFIER()).thenReturn(identifier);
        when(identifier.getText()).thenReturn("TestClass");

        ParseTree result = ParserFactory.findDeclarationContext(block, "TestClass");

        assertSame(classDecl, result);
    }

    // Returns interface declaration context when matching interface name is found in block
    @Test
    public void test_returns_interface_declaration_when_name_matches() {
        LumParser.BlockContext block = mock(LumParser.BlockContext.class);
        LumParser.StatementContext stmt = mock(LumParser.StatementContext.class);
        LumParser.DeclarationContext decl = mock(LumParser.DeclarationContext.class);
        LumParser.InterfaceDeclarationContext interfaceDecl = mock(LumParser.InterfaceDeclarationContext.class);
        TerminalNode identifier = mock(TerminalNode.class);

        when(block.statement()).thenReturn(Collections.singletonList(stmt));
        when(stmt.declaration()).thenReturn(decl);
        when(decl.getChild(0)).thenReturn(interfaceDecl);
        when(interfaceDecl.IDENTIFIER()).thenReturn(identifier);
        when(identifier.getText()).thenReturn("TestInterface");

        ParseTree result = ParserFactory.findDeclarationContext(block, "TestInterface");

        assertSame(interfaceDecl, result);
    }

    // Returns annotation declaration context when matching annotation name is found in block
    @Test
    public void test_returns_annotation_declaration_when_name_matches() {
        LumParser.BlockContext block = mock(LumParser.BlockContext.class);
        LumParser.StatementContext stmt = mock(LumParser.StatementContext.class);
        LumParser.DeclarationContext decl = mock(LumParser.DeclarationContext.class);
        LumParser.AnnotationDeclarationContext annotationDecl = mock(LumParser.AnnotationDeclarationContext.class);
        TerminalNode identifier = mock(TerminalNode.class);

        when(block.statement()).thenReturn(Collections.singletonList(stmt));
        when(stmt.declaration()).thenReturn(decl);
        when(decl.getChild(0)).thenReturn(annotationDecl);
        when(annotationDecl.IDENTIFIER()).thenReturn(identifier);
        when(identifier.getText()).thenReturn("TestAnnotation");

        ParseTree result = ParserFactory.findDeclarationContext(block, "TestAnnotation");

        assertSame(annotationDecl, result);
    }

    // Recursively searches nested blocks for matching declarations
    @Test
    public void test_searches_nested_blocks_recursively() {
        LumParser.BlockContext outerBlock = mock(LumParser.BlockContext.class);
        LumParser.BlockContext innerBlock = mock(LumParser.BlockContext.class);
        LumParser.StatementContext outerStmt = mock(LumParser.StatementContext.class);
        LumParser.StatementContext innerStmt = mock(LumParser.StatementContext.class);
        LumParser.DeclarationContext outerDecl = mock(LumParser.DeclarationContext.class);
        LumParser.DeclarationContext innerDecl = mock(LumParser.DeclarationContext.class);
        LumParser.ClassDeclarationContext outerClass = mock(LumParser.ClassDeclarationContext.class);
        LumParser.ClassDeclarationContext innerClass = mock(LumParser.ClassDeclarationContext.class);
        TerminalNode outerIdentifier = mock(TerminalNode.class);
        TerminalNode innerIdentifier = mock(TerminalNode.class);

        when(outerBlock.statement()).thenReturn(Collections.singletonList(outerStmt));
        when(outerStmt.declaration()).thenReturn(outerDecl);
        when(outerDecl.getChild(0)).thenReturn(outerClass);
        when(outerClass.IDENTIFIER()).thenReturn(outerIdentifier);
        when(outerIdentifier.getText()).thenReturn("OuterClass");
        when(outerClass.block()).thenReturn(innerBlock);

        when(innerBlock.statement()).thenReturn(Collections.singletonList(innerStmt));
        when(innerStmt.declaration()).thenReturn(innerDecl);
        when(innerDecl.getChild(0)).thenReturn(innerClass);
        when(innerClass.IDENTIFIER()).thenReturn(innerIdentifier);
        when(innerIdentifier.getText()).thenReturn("InnerClass");

        ParseTree result = ParserFactory.findDeclarationContext(outerBlock, "InnerClass");

        assertSame(innerClass, result);
    }

    // Handle null BlockContext input
    @Test
    public void test_handles_null_block_context() {
        ParseTree result = ParserFactory.findDeclarationContext(null, "TestClass");
        assertNull(result);
    }

    // Handle empty block with no statements
    @Test
    public void test_handles_empty_block() {
        LumParser.BlockContext block = mock(LumParser.BlockContext.class);
        when(block.statement()).thenReturn(new ArrayList<>());

        ParseTree result = ParserFactory.findDeclarationContext(block, "TestClass");

        assertNull(result);
    }

    // Handle block with no declarations
    @Test
    public void test_handles_block_with_no_declarations() {
        LumParser.BlockContext block = mock(LumParser.BlockContext.class);
        LumParser.StatementContext stmt = mock(LumParser.StatementContext.class);

        when(block.statement()).thenReturn(Collections.singletonList(stmt));
        when(stmt.declaration()).thenReturn(null);

        ParseTree result = ParserFactory.findDeclarationContext(block, "TestClass");

        assertNull(result);
    }

    // Handle block with only non-declaration statements
    @Test
    public void test_handles_block_with_only_non_declaration_statements() {
        LumParser.BlockContext block = mock(LumParser.BlockContext.class);
        LumParser.StatementContext stmt1 = mock(LumParser.StatementContext.class);
        LumParser.StatementContext stmt2 = mock(LumParser.StatementContext.class);

        when(block.statement()).thenReturn(Arrays.asList(stmt1, stmt2));
        when(stmt1.declaration()).thenReturn(null);
        when(stmt2.declaration()).thenReturn(null);

        ParseTree result = ParserFactory.findDeclarationContext(block, "TestClass");

        assertNull(result);
    }
}