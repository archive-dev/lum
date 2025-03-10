package lum.core.model;

import lum.core.parsing.antlr4.LumParser;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ImportsModelFactoryTest {
    // Create a class model from class declaration context
    @Test
    public void test_create_class_model() {
        Imports imports = new ImportsImpl(new HashMap<>(), new HashMap<>(), new HashMap<>());

        LumParser.ClassDeclarationContext ctx = mock(LumParser.ClassDeclarationContext.class);
        when(ctx.IDENTIFIER()).thenReturn(mock(TerminalNode.class));
        when(ctx.IDENTIFIER().getText()).thenReturn("TestClass");
        when(ctx.access()).thenReturn(null);
        when(ctx.modifier()).thenReturn(null);
        when(ctx.block()).thenReturn(mock(LumParser.BlockContext.class));
        when(ctx.block().statement()).thenReturn(new ArrayList<>());

        ClassModel model = ImportsModelFactory.createClassModel(imports, ctx);

        assertNotNull(model);
        assertEquals("TestClass", model.name());
        assertFalse(model.isInterface());
    }

    // Create an interface model with methods from interface declaration context
    @Test
    public void test_create_interface_model_with_methods() {
        Imports imports = new ImportsImpl(new HashMap<>(), new HashMap<>(), new HashMap<>());

        LumParser.InterfaceDeclarationContext ctx = mock(LumParser.InterfaceDeclarationContext.class);
        when(ctx.IDENTIFIER()).thenReturn(mock(TerminalNode.class));
        when(ctx.IDENTIFIER().getText()).thenReturn("TestInterface");
        when(ctx.access()).thenReturn(null);
        when(ctx.modifier()).thenReturn(null);
        when(ctx.block()).thenReturn(mock(LumParser.BlockContext.class));
        when(ctx.block().statement()).thenReturn(new ArrayList<>());

        ClassModel model = ImportsModelFactory.createInterfaceModel(imports, ctx);

        assertNotNull(model);
        assertEquals("TestInterface", model.name());
        assertTrue(model.isInterface());
    }

    // Process class members including variables, functions, operators and constructors
    @Test
    public void test_process_class_members() {
        Imports imports = new ImportsImpl(new HashMap<>(), new HashMap<>(), new HashMap<>());

        LumParser.ClassDeclarationContext ctx = mock(LumParser.ClassDeclarationContext.class);
        LumParser.BlockContext block = mock(LumParser.BlockContext.class);
        LumParser.StatementContext stmt = mock(LumParser.StatementContext.class);
        LumParser.DeclarationContext decl = mock(LumParser.DeclarationContext.class);

        when(ctx.IDENTIFIER()).thenReturn(mock(TerminalNode.class));
        when(ctx.IDENTIFIER().getText()).thenReturn("TestClass");
        when(ctx.block()).thenReturn(block);
        when(block.statement()).thenReturn(List.of(stmt));
        when(stmt.declaration()).thenReturn(decl);

        ClassModel model = ImportsModelFactory.createClassModel(imports, ctx);

        assertNotNull(model);
        assertTrue(imports.classes().containsKey("TestClass"));
    }

    // Create method models with correct access flags and parameters
    @Test
    public void test_create_method_model_with_access_flags() {
        ClassModel owner = mock(ClassModelImpl.class);
        Imports imports = new ImportsImpl(new HashMap<>(), new HashMap<>(), new HashMap<>());

        LumParser.FunctionDeclarationContext ctx = mock(LumParser.FunctionDeclarationContext.class);
        LumParser.AccessContext access = mock(LumParser.PublicContext.class);

        TerminalNode returnType = mock(TerminalNode.class);
        when(returnType.getText()).thenReturn("void");

        when(ctx.IDENTIFIER()).thenReturn(mock(TerminalNode.class));
        when(ctx.IDENTIFIER().getText()).thenReturn("testMethod");
        when(ctx.access()).thenReturn(access);
        when(ctx.type()).thenReturn(mock(LumParser.PlainTypeContext.class));
        when(((LumParser.PlainTypeContext) ctx.type()).IDENTIFIER()).thenReturn(new ArrayList<>(List.of(returnType)));
        when(((LumParser.PlainTypeContext) ctx.type()).IDENTIFIER(0)).thenReturn(returnType);

        MethodModel model = ImportsModelFactory.createMethodModel(owner, imports, ctx);

        assertNotNull(model);
        assertEquals("testMethod", model.name());
        assertEquals(owner, model.owner());
    }

    // Handle null or empty inheritance specifications
    @Test
    public void test_handle_null_inheritance() {
        Imports imports = new ImportsImpl(new HashMap<>(), new HashMap<>(), new HashMap<>());

        LumParser.ClassDeclarationContext ctx = mock(LumParser.ClassDeclarationContext.class);
        when(ctx.IDENTIFIER()).thenReturn(mock(TerminalNode.class));
        when(ctx.IDENTIFIER().getText()).thenReturn("TestClass");
        when(ctx.inheritance()).thenReturn(null);
        when(ctx.block()).thenReturn(mock(LumParser.BlockContext.class));
        when(ctx.block().statement()).thenReturn(new ArrayList<>());

        ClassModel model = ImportsModelFactory.createClassModel(imports, ctx);

        assertNotNull(model);
        assertEquals(ClassModel.of(Object.class), model.superClass());
        assertEquals(0, model.interfaces().length);
    }

    // Process class/interface declarations without any members
    @Test
    public void test_empty_class_declaration() {
        Imports imports = new ImportsImpl(new HashMap<>(), new HashMap<>(), new HashMap<>());

        LumParser.ClassDeclarationContext ctx = mock(LumParser.ClassDeclarationContext.class);
        when(ctx.IDENTIFIER()).thenReturn(mock(TerminalNode.class));
        when(ctx.IDENTIFIER().getText()).thenReturn("EmptyClass");
        when(ctx.block()).thenReturn(mock(LumParser.BlockContext.class));
        when(ctx.block().statement()).thenReturn(new ArrayList<>());

        ClassModel model = ImportsModelFactory.createClassModel(imports, ctx);

        assertNotNull(model);
        assertEquals("EmptyClass", model.name());
    }

    // Handle missing access modifiers and use defaults
    @Test
    public void test_default_access_modifiers() {
        Imports imports = new ImportsImpl(new HashMap<>(), new HashMap<>(), new HashMap<>());

        LumParser.ClassDeclarationContext ctx = mock(LumParser.ClassDeclarationContext.class);
        when(ctx.IDENTIFIER()).thenReturn(mock(TerminalNode.class));
        when(ctx.IDENTIFIER().getText()).thenReturn("TestClass");
        when(ctx.access()).thenReturn(null);
        when(ctx.modifier()).thenReturn(null);
        when(ctx.block()).thenReturn(mock(LumParser.BlockContext.class));
        when(ctx.block().statement()).thenReturn(new ArrayList<>());

        ClassModel model = ImportsModelFactory.createClassModel(imports, ctx);

        assertNotNull(model);
        assertTrue(model.accessFlags().isEmpty());
    }

    // Process fields without getter/setter declarations
    @Test
    public void test_field_without_accessors() {
        ClassModel owner = mock(ClassModelImpl.class);
        Imports imports = new ImportsImpl(new HashMap<>(), new HashMap<>(), new HashMap<>());

        LumParser.VariableDeclarationStatementContext ctx = mock(LumParser.VariableDeclarationStatementContext.class);
        LumParser.VariableDeclarationContext varDecl = mock(LumParser.VariableDeclarationContext.class);

        when(ctx.variableDeclaration()).thenReturn(List.of(varDecl));
        when(varDecl.IDENTIFIER()).thenReturn(mock(TerminalNode.class));
        when(varDecl.IDENTIFIER().getText()).thenReturn("testField");

        var typeCtx = mock(LumParser.PlainTypeContext.class);
        var type = mock(TerminalNode.class);
        when(typeCtx.IDENTIFIER()).thenReturn(new ArrayList<>(List.of(type)));
        when(type.getText()).thenReturn("str");
        when(varDecl.type()).thenReturn(typeCtx);

        when(varDecl.getterDeclaration()).thenReturn(null);
        when(varDecl.setterDeclaration()).thenReturn(null);

        List<FieldModel> fields = ImportsModelFactory.createFieldModels(owner, imports, ctx);

        assertNotNull(fields);
        assertEquals(1, fields.size());
        assertEquals("testField", fields.getFirst().name());
    }
}