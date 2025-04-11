// Generated from C:/Users/Egor/Documents/Java/serious/lum/core/src/main/resources/Lum.g4 by ANTLR 4.13.2
package lum.core.parsing.antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class LumParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, T__67=68, T__68=69, T__69=70, T__70=71, T__71=72, T__72=73, 
		T__73=74, T__74=75, T__75=76, T__76=77, T__77=78, NEW=79, ARRAY=80, IDENTIFIER=81, 
		NUMBER=82, STRING=83, WS=84, COMMENT=85;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_declaration = 2, RULE_controlStatement = 3, 
		RULE_block = 4, RULE_expression = 5, RULE_primary = 6, RULE_keyValueList = 7, 
		RULE_keyValue = 8, RULE_package = 9, RULE_importStatement = 10, RULE_importAs = 11, 
		RULE_importSimple = 12, RULE_importFrom = 13, RULE_importMultiple = 14, 
		RULE_anySimpleImport = 15, RULE_break = 16, RULE_continue = 17, RULE_return = 18, 
		RULE_lambda = 19, RULE_switchStatement = 20, RULE_case = 21, RULE_default = 22, 
		RULE_ifStatement = 23, RULE_if = 24, RULE_elif = 25, RULE_else = 26, RULE_loop = 27, 
		RULE_whileLoopStatement = 28, RULE_whileLoop = 29, RULE_doWhileLoop = 30, 
		RULE_forLoopStatement = 31, RULE_forILoop = 32, RULE_forEachLoop = 33, 
		RULE_variableDeclarationStatement = 34, RULE_variableDeclaration = 35, 
		RULE_getterDeclaration = 36, RULE_setterDeclaration = 37, RULE_functionDeclaration = 38, 
		RULE_constructorDeclaration = 39, RULE_operatorDeclaration = 40, RULE_parameterList = 41, 
		RULE_parameter = 42, RULE_argumentList = 43, RULE_classDeclaration = 44, 
		RULE_inheritance = 45, RULE_inheritanceSpec = 46, RULE_interfaceDeclaration = 47, 
		RULE_functionSignature = 48, RULE_annotationDeclaration = 49, RULE_annotation = 50, 
		RULE_annotationArgs = 51, RULE_annotationArg = 52, RULE_functionCall = 53, 
		RULE_superCall = 54, RULE_type = 55, RULE_genericDeclaration = 56, RULE_generic = 57, 
		RULE_literal = 58, RULE_operator = 59, RULE_binaryOperator = 60, RULE_unaryOperator = 61, 
		RULE_preUnaryOperator = 62, RULE_postUnaryOperator = 63, RULE_assignment = 64, 
		RULE_access = 65, RULE_modifier = 66;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "declaration", "controlStatement", "block", "expression", 
			"primary", "keyValueList", "keyValue", "package", "importStatement", 
			"importAs", "importSimple", "importFrom", "importMultiple", "anySimpleImport", 
			"break", "continue", "return", "lambda", "switchStatement", "case", "default", 
			"ifStatement", "if", "elif", "else", "loop", "whileLoopStatement", "whileLoop", 
			"doWhileLoop", "forLoopStatement", "forILoop", "forEachLoop", "variableDeclarationStatement", 
			"variableDeclaration", "getterDeclaration", "setterDeclaration", "functionDeclaration", 
			"constructorDeclaration", "operatorDeclaration", "parameterList", "parameter", 
			"argumentList", "classDeclaration", "inheritance", "inheritanceSpec", 
			"interfaceDeclaration", "functionSignature", "annotationDeclaration", 
			"annotation", "annotationArgs", "annotationArg", "functionCall", "superCall", 
			"type", "genericDeclaration", "generic", "literal", "operator", "binaryOperator", 
			"unaryOperator", "preUnaryOperator", "postUnaryOperator", "assignment", 
			"access", "modifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'=>'", "'['", "']'", "'.'", "'as'", "'super'", "'('", 
			"')'", "','", "':'", "'package'", "'import'", "'from'", "'break'", "'continue'", 
			"'return'", "'switch'", "'case'", "'default'", "'if'", "'elif'", "'else'", 
			"'while'", "'do'", "'for'", "'in'", "':='", "'='", "'get'", "'set'", 
			"'func'", "'init'", "'operator'", "'class'", "'extends'", "'implements'", 
			"'interface'", "'annotation'", "'@'", "'|'", "'&'", "'?'", "'null'", 
			"'true'", "'false'", "'*'", "'/'", "'//'", "'%'", "'+'", "'-'", "'>>'", 
			"'<<'", "'^'", "'xor'", "'>'", "'<'", "'>='", "'<='", "'=='", "'!='", 
			"'is'", "'&&'", "'and'", "'||'", "'or'", "'!'", "'not'", "'++'", "'--'", 
			"'public'", "'private'", "'protected'", "'static'", "'abstract'", "'final'", 
			"'new'", "'[]'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "NEW", "ARRAY", "IDENTIFIER", 
			"NUMBER", "STRING", "WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Lum.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LumParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(LumParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 250267984421648L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 30719L) != 0)) {
				{
				{
				setState(134);
				statement();
				}
				}
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(140);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public PackageContext package_() {
			return getRuleContext(PackageContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ImportStatementContext importStatement() {
			return getRuleContext(ImportStatementContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public ControlStatementContext controlStatement() {
			return getRuleContext(ControlStatementContext.class,0);
		}
		public BreakContext break_() {
			return getRuleContext(BreakContext.class,0);
		}
		public ContinueContext continue_() {
			return getRuleContext(ContinueContext.class,0);
		}
		public ReturnContext return_() {
			return getRuleContext(ReturnContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(142);
				package_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(143);
				expression(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(144);
				importStatement();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(145);
				declaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(146);
				controlStatement();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(147);
				break_();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(148);
				continue_();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(149);
				return_();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public VariableDeclarationStatementContext variableDeclarationStatement() {
			return getRuleContext(VariableDeclarationStatementContext.class,0);
		}
		public OperatorDeclarationContext operatorDeclaration() {
			return getRuleContext(OperatorDeclarationContext.class,0);
		}
		public ConstructorDeclarationContext constructorDeclaration() {
			return getRuleContext(ConstructorDeclarationContext.class,0);
		}
		public FunctionSignatureContext functionSignature() {
			return getRuleContext(FunctionSignatureContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public AnnotationDeclarationContext annotationDeclaration() {
			return getRuleContext(AnnotationDeclarationContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaration);
		try {
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				functionDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(153);
				variableDeclarationStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(154);
				operatorDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(155);
				constructorDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(156);
				functionSignature();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(157);
				classDeclaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(158);
				interfaceDeclaration();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(159);
				annotationDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ControlStatementContext extends ParserRuleContext {
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public SwitchStatementContext switchStatement() {
			return getRuleContext(SwitchStatementContext.class,0);
		}
		public LoopContext loop() {
			return getRuleContext(LoopContext.class,0);
		}
		public ControlStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_controlStatement; }
	}

	public final ControlStatementContext controlStatement() throws RecognitionException {
		ControlStatementContext _localctx = new ControlStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_controlStatement);
		try {
			setState(165);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__21:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				ifStatement();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 2);
				{
				setState(163);
				switchStatement();
				}
				break;
			case T__24:
			case T__25:
			case T__26:
				enterOuterAlt(_localctx, 3);
				{
				setState(164);
				loop();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BlockContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_block);
		int _la;
		try {
			setState(177);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(167);
				match(T__0);
				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 250267984421648L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 30719L) != 0)) {
					{
					{
					setState(168);
					statement();
					}
					}
					setState(173);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(174);
				match(T__1);
				}
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				match(T__2);
				setState(176);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAccessContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public ArrayAccessContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallExprContext extends ExpressionContext {
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public FunctionCallExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PreUnaryContext extends ExpressionContext {
		public PreUnaryOperatorContext preUnaryOperator() {
			return getRuleContext(PreUnaryOperatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PreUnaryContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryExpressionContext extends ExpressionContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public PrimaryExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MemberAccessContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public MemberAccessContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SuperCallExprContext extends ExpressionContext {
		public SuperCallContext superCall() {
			return getRuleContext(SuperCallContext.class,0);
		}
		public SuperCallExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostUnaryContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PostUnaryOperatorContext postUnaryOperator() {
			return getRuleContext(PostUnaryOperatorContext.class,0);
		}
		public PostUnaryContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BinaryContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BinaryOperatorContext binaryOperator() {
			return getRuleContext(BinaryOperatorContext.class,0);
		}
		public BinaryContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentExprContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public AssignmentExprContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CastExpressionContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public CastExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LambdaExpressionContext extends ExpressionContext {
		public LambdaContext lambda() {
			return getRuleContext(LambdaContext.class,0);
		}
		public LambdaExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				_localctx = new FunctionCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(180);
				functionCall();
				}
				break;
			case 2:
				{
				_localctx = new SuperCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(181);
				superCall();
				}
				break;
			case 3:
				{
				_localctx = new LambdaExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(182);
				lambda();
				}
				break;
			case 4:
				{
				_localctx = new PreUnaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(183);
				preUnaryOperator();
				setState(184);
				expression(5);
				}
				break;
			case 5:
				{
				_localctx = new PrimaryExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(186);
				primary();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(213);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(211);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(189);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(190);
						binaryOperator();
						setState(191);
						expression(5);
						}
						break;
					case 2:
						{
						_localctx = new ArrayAccessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(193);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(194);
						match(T__3);
						setState(195);
						argumentList();
						setState(196);
						match(T__4);
						}
						break;
					case 3:
						{
						_localctx = new MemberAccessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(198);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(199);
						match(T__5);
						setState(202);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
						case 1:
							{
							setState(200);
							functionCall();
							}
							break;
						case 2:
							{
							setState(201);
							match(IDENTIFIER);
							}
							break;
						}
						}
						break;
					case 4:
						{
						_localctx = new PostUnaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(204);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(205);
						postUnaryOperator();
						}
						break;
					case 5:
						{
						_localctx = new AssignmentExprContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(206);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(207);
						assignment();
						}
						break;
					case 6:
						{
						_localctx = new CastExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(208);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(209);
						match(T__6);
						setState(210);
						type(0);
						}
						break;
					}
					} 
				}
				setState(215);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrimaryContext extends ParserRuleContext {
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
	 
		public PrimaryContext() { }
		public void copyFrom(PrimaryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ListLiteralContext extends PrimaryContext {
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public ListLiteralContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentifierExprContext extends PrimaryContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public IdentifierExprContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LiteralExprContext extends PrimaryContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralExprContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExprContext extends PrimaryContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenExprContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SuperAccessContext extends PrimaryContext {
		public SuperAccessContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DictLiteralContext extends PrimaryContext {
		public KeyValueListContext keyValueList() {
			return getRuleContext(KeyValueListContext.class,0);
		}
		public DictLiteralContext(PrimaryContext ctx) { copyFrom(ctx); }
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_primary);
		int _la;
		try {
			setState(233);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new LiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(216);
				literal();
				}
				break;
			case 2:
				_localctx = new IdentifierExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(217);
				match(IDENTIFIER);
				}
				break;
			case 3:
				_localctx = new SuperAccessContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(218);
				match(T__7);
				}
				break;
			case 4:
				_localctx = new ListLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(219);
				match(T__3);
				setState(221);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246290604622608L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 29711L) != 0)) {
					{
					setState(220);
					argumentList();
					}
				}

				setState(223);
				match(T__4);
				}
				break;
			case 5:
				_localctx = new DictLiteralContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(224);
				match(T__3);
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246290604622608L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 29711L) != 0)) {
					{
					setState(225);
					keyValueList();
					}
				}

				setState(228);
				match(T__4);
				}
				break;
			case 6:
				_localctx = new ParenExprContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(229);
				match(T__8);
				setState(230);
				expression(0);
				setState(231);
				match(T__9);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeyValueListContext extends ParserRuleContext {
		public List<KeyValueContext> keyValue() {
			return getRuleContexts(KeyValueContext.class);
		}
		public KeyValueContext keyValue(int i) {
			return getRuleContext(KeyValueContext.class,i);
		}
		public KeyValueListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyValueList; }
	}

	public final KeyValueListContext keyValueList() throws RecognitionException {
		KeyValueListContext _localctx = new KeyValueListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_keyValueList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			keyValue();
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(236);
				match(T__10);
				setState(237);
				keyValue();
				}
				}
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeyValueContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public KeyValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyValue; }
	}

	public final KeyValueContext keyValue() throws RecognitionException {
		KeyValueContext _localctx = new KeyValueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_keyValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			expression(0);
			setState(244);
			match(T__11);
			setState(245);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PackageContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(LumParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(LumParser.IDENTIFIER, i);
		}
		public PackageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_package; }
	}

	public final PackageContext package_() throws RecognitionException {
		PackageContext _localctx = new PackageContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_package);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(247);
			match(T__12);
			setState(248);
			match(IDENTIFIER);
			setState(253);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(249);
					match(T__5);
					setState(250);
					match(IDENTIFIER);
					}
					} 
				}
				setState(255);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportStatementContext extends ParserRuleContext {
		public AnySimpleImportContext anySimpleImport() {
			return getRuleContext(AnySimpleImportContext.class,0);
		}
		public ImportFromContext importFrom() {
			return getRuleContext(ImportFromContext.class,0);
		}
		public ImportMultipleContext importMultiple() {
			return getRuleContext(ImportMultipleContext.class,0);
		}
		public ImportStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importStatement; }
	}

	public final ImportStatementContext importStatement() throws RecognitionException {
		ImportStatementContext _localctx = new ImportStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_importStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(T__13);
			setState(260);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(257);
				anySimpleImport();
				}
				break;
			case 2:
				{
				setState(258);
				importFrom();
				}
				break;
			case 3:
				{
				setState(259);
				importMultiple();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportAsContext extends ParserRuleContext {
		public ImportSimpleContext importSimple() {
			return getRuleContext(ImportSimpleContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public ImportAsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importAs; }
	}

	public final ImportAsContext importAs() throws RecognitionException {
		ImportAsContext _localctx = new ImportAsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_importAs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			importSimple();
			setState(263);
			match(T__6);
			setState(264);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportSimpleContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(LumParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(LumParser.IDENTIFIER, i);
		}
		public ImportSimpleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importSimple; }
	}

	public final ImportSimpleContext importSimple() throws RecognitionException {
		ImportSimpleContext _localctx = new ImportSimpleContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_importSimple);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(IDENTIFIER);
			setState(271);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(267);
					match(T__5);
					setState(268);
					match(IDENTIFIER);
					}
					} 
				}
				setState(273);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportFromContext extends ParserRuleContext {
		public ImportMultipleContext importMultiple() {
			return getRuleContext(ImportMultipleContext.class,0);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(LumParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(LumParser.IDENTIFIER, i);
		}
		public ImportFromContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importFrom; }
	}

	public final ImportFromContext importFrom() throws RecognitionException {
		ImportFromContext _localctx = new ImportFromContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_importFrom);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			importMultiple();
			setState(275);
			match(T__14);
			setState(276);
			match(IDENTIFIER);
			setState(281);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(277);
					match(T__5);
					setState(278);
					match(IDENTIFIER);
					}
					} 
				}
				setState(283);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ImportMultipleContext extends ParserRuleContext {
		public List<AnySimpleImportContext> anySimpleImport() {
			return getRuleContexts(AnySimpleImportContext.class);
		}
		public AnySimpleImportContext anySimpleImport(int i) {
			return getRuleContext(AnySimpleImportContext.class,i);
		}
		public ImportMultipleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importMultiple; }
	}

	public final ImportMultipleContext importMultiple() throws RecognitionException {
		ImportMultipleContext _localctx = new ImportMultipleContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_importMultiple);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			anySimpleImport();
			setState(289);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(285);
					match(T__10);
					setState(286);
					anySimpleImport();
					}
					} 
				}
				setState(291);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnySimpleImportContext extends ParserRuleContext {
		public ImportSimpleContext importSimple() {
			return getRuleContext(ImportSimpleContext.class,0);
		}
		public ImportAsContext importAs() {
			return getRuleContext(ImportAsContext.class,0);
		}
		public AnySimpleImportContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anySimpleImport; }
	}

	public final AnySimpleImportContext anySimpleImport() throws RecognitionException {
		AnySimpleImportContext _localctx = new AnySimpleImportContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_anySimpleImport);
		try {
			setState(294);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(292);
				importSimple();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(293);
				importAs();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BreakContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public BreakContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_break; }
	}

	public final BreakContext break_() throws RecognitionException {
		BreakContext _localctx = new BreakContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_break);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(T__15);
			setState(298);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(297);
				match(IDENTIFIER);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ContinueContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public ContinueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continue; }
	}

	public final ContinueContext continue_() throws RecognitionException {
		ContinueContext _localctx = new ContinueContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_continue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			match(T__16);
			setState(302);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(301);
				match(IDENTIFIER);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ReturnContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return; }
	}

	public final ReturnContext return_() throws RecognitionException {
		ReturnContext _localctx = new ReturnContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_return);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			match(T__17);
			setState(306);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(305);
				expression(0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LambdaContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public LambdaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda; }
	}

	public final LambdaContext lambda() throws RecognitionException {
		LambdaContext _localctx = new LambdaContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_lambda);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				{
				setState(308);
				parameterList();
				}
				}
				break;
			case T__8:
				{
				setState(309);
				match(T__8);
				setState(310);
				parameterList();
				setState(311);
				match(T__9);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(315);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SwitchStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DefaultContext default_() {
			return getRuleContext(DefaultContext.class,0);
		}
		public List<CaseContext> case_() {
			return getRuleContexts(CaseContext.class);
		}
		public CaseContext case_(int i) {
			return getRuleContext(CaseContext.class,i);
		}
		public SwitchStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStatement; }
	}

	public final SwitchStatementContext switchStatement() throws RecognitionException {
		SwitchStatementContext _localctx = new SwitchStatementContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_switchStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			match(T__18);
			setState(318);
			expression(0);
			setState(319);
			match(T__0);
			setState(323);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__19) {
				{
				{
				setState(320);
				case_();
				}
				}
				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(326);
			default_();
			setState(327);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CaseContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public CaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_case; }
	}

	public final CaseContext case_() throws RecognitionException {
		CaseContext _localctx = new CaseContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_case);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(329);
			match(T__19);
			setState(330);
			expression(0);
			setState(331);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefaultContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public DefaultContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_default; }
	}

	public final DefaultContext default_() throws RecognitionException {
		DefaultContext _localctx = new DefaultContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_default);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			match(T__20);
			setState(334);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfStatementContext extends ParserRuleContext {
		public IfContext if_() {
			return getRuleContext(IfContext.class,0);
		}
		public List<ElifContext> elif() {
			return getRuleContexts(ElifContext.class);
		}
		public ElifContext elif(int i) {
			return getRuleContext(ElifContext.class,i);
		}
		public ElseContext else_() {
			return getRuleContext(ElseContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_ifStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			if_();
			setState(340);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(337);
					elif();
					}
					} 
				}
				setState(342);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			setState(344);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(343);
				else_();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if; }
	}

	public final IfContext if_() throws RecognitionException {
		IfContext _localctx = new IfContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_if);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			match(T__21);
			setState(347);
			expression(0);
			setState(348);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElifContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ElifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elif; }
	}

	public final ElifContext elif() throws RecognitionException {
		ElifContext _localctx = new ElifContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_elif);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(350);
			match(T__22);
			setState(351);
			expression(0);
			setState(352);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ElseContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else; }
	}

	public final ElseContext else_() throws RecognitionException {
		ElseContext _localctx = new ElseContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_else);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			match(T__23);
			setState(355);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LoopContext extends ParserRuleContext {
		public WhileLoopStatementContext whileLoopStatement() {
			return getRuleContext(WhileLoopStatementContext.class,0);
		}
		public ForLoopStatementContext forLoopStatement() {
			return getRuleContext(ForLoopStatementContext.class,0);
		}
		public LoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop; }
	}

	public final LoopContext loop() throws RecognitionException {
		LoopContext _localctx = new LoopContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_loop);
		try {
			setState(359);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__24:
			case T__25:
				enterOuterAlt(_localctx, 1);
				{
				setState(357);
				whileLoopStatement();
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				setState(358);
				forLoopStatement();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileLoopStatementContext extends ParserRuleContext {
		public WhileLoopContext whileLoop() {
			return getRuleContext(WhileLoopContext.class,0);
		}
		public DoWhileLoopContext doWhileLoop() {
			return getRuleContext(DoWhileLoopContext.class,0);
		}
		public WhileLoopStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileLoopStatement; }
	}

	public final WhileLoopStatementContext whileLoopStatement() throws RecognitionException {
		WhileLoopStatementContext _localctx = new WhileLoopStatementContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_whileLoopStatement);
		try {
			setState(363);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__24:
				enterOuterAlt(_localctx, 1);
				{
				setState(361);
				whileLoop();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 2);
				{
				setState(362);
				doWhileLoop();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class WhileLoopContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public WhileLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileLoop; }
	}

	public final WhileLoopContext whileLoop() throws RecognitionException {
		WhileLoopContext _localctx = new WhileLoopContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_whileLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(365);
			match(T__24);
			setState(366);
			expression(0);
			setState(367);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DoWhileLoopContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DoWhileLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doWhileLoop; }
	}

	public final DoWhileLoopContext doWhileLoop() throws RecognitionException {
		DoWhileLoopContext _localctx = new DoWhileLoopContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_doWhileLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(369);
			match(T__25);
			setState(370);
			block();
			setState(371);
			match(T__24);
			setState(372);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForLoopStatementContext extends ParserRuleContext {
		public ForILoopContext forILoop() {
			return getRuleContext(ForILoopContext.class,0);
		}
		public ForEachLoopContext forEachLoop() {
			return getRuleContext(ForEachLoopContext.class,0);
		}
		public ForLoopStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forLoopStatement; }
	}

	public final ForLoopStatementContext forLoopStatement() throws RecognitionException {
		ForLoopStatementContext _localctx = new ForLoopStatementContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_forLoopStatement);
		try {
			setState(376);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(374);
				forILoop();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(375);
				forEachLoop();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForILoopContext extends ParserRuleContext {
		public ExpressionContext condition;
		public ExpressionContext iter;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ForILoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forILoop; }
	}

	public final ForILoopContext forILoop() throws RecognitionException {
		ForILoopContext _localctx = new ForILoopContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_forILoop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			match(T__26);
			setState(380);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(379);
				variableDeclaration();
				}
			}

			setState(382);
			match(T__10);
			setState(384);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246290604622608L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 29711L) != 0)) {
				{
				setState(383);
				((ForILoopContext)_localctx).condition = expression(0);
				}
			}

			setState(386);
			match(T__10);
			setState(388);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246290604622608L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 29711L) != 0)) {
				{
				setState(387);
				((ForILoopContext)_localctx).iter = expression(0);
				}
			}

			setState(390);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForEachLoopContext extends ParserRuleContext {
		public VariableDeclarationContext variableDeclaration() {
			return getRuleContext(VariableDeclarationContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForEachLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forEachLoop; }
	}

	public final ForEachLoopContext forEachLoop() throws RecognitionException {
		ForEachLoopContext _localctx = new ForEachLoopContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_forEachLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			match(T__26);
			setState(393);
			variableDeclaration();
			setState(394);
			match(T__27);
			setState(395);
			expression(0);
			setState(396);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationStatementContext extends ParserRuleContext {
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public VariableDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarationStatement; }
	}

	public final VariableDeclarationStatementContext variableDeclarationStatement() throws RecognitionException {
		VariableDeclarationStatementContext _localctx = new VariableDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_variableDeclarationStatement);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(401);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__40) {
				{
				{
				setState(398);
				annotation();
				}
				}
				setState(403);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(405);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(404);
				access();
				}
			}

			setState(408);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(407);
				modifier();
				}
				break;
			}
			{
			setState(410);
			variableDeclaration();
			}
			setState(415);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(411);
					match(T__10);
					setState(412);
					variableDeclaration();
					}
					} 
				}
				setState(417);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclarationContext extends ParserRuleContext {
		public Token typeEq;
		public Token eq;
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<GetterDeclarationContext> getterDeclaration() {
			return getRuleContexts(GetterDeclarationContext.class);
		}
		public GetterDeclarationContext getterDeclaration(int i) {
			return getRuleContext(GetterDeclarationContext.class,i);
		}
		public List<SetterDeclarationContext> setterDeclaration() {
			return getRuleContexts(SetterDeclarationContext.class);
		}
		public SetterDeclarationContext setterDeclaration(int i) {
			return getRuleContext(SetterDeclarationContext.class,i);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_variableDeclaration);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			match(IDENTIFIER);
			setState(421);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(419);
				match(T__11);
				setState(420);
				type(0);
				}
				break;
			}
			setState(428);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(425);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__28:
					{
					setState(423);
					((VariableDeclarationContext)_localctx).typeEq = match(T__28);
					}
					break;
				case T__29:
					{
					setState(424);
					((VariableDeclarationContext)_localctx).eq = match(T__29);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(427);
				expression(0);
				}
				break;
			}
			setState(434);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(432);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
					case 1:
						{
						setState(430);
						getterDeclaration();
						}
						break;
					case 2:
						{
						setState(431);
						setterDeclaration();
						}
						break;
					}
					} 
				}
				setState(436);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GetterDeclarationContext extends ParserRuleContext {
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public GetterDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_getterDeclaration; }
	}

	public final GetterDeclarationContext getterDeclaration() throws RecognitionException {
		GetterDeclarationContext _localctx = new GetterDeclarationContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_getterDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(437);
				access();
				}
			}

			setState(440);
			match(T__30);
			setState(442);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(441);
				block();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetterDeclarationContext extends ParserRuleContext {
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public SetterDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setterDeclaration; }
	}

	public final SetterDeclarationContext setterDeclaration() throws RecognitionException {
		SetterDeclarationContext _localctx = new SetterDeclarationContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_setterDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(444);
				access();
				}
			}

			setState(447);
			match(T__31);
			setState(453);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(448);
				match(T__8);
				setState(449);
				parameter();
				setState(450);
				match(T__9);
				setState(451);
				block();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionDeclarationContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public GenericDeclarationContext genericDeclaration() {
			return getRuleContext(GenericDeclarationContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_functionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(458);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__40) {
				{
				{
				setState(455);
				annotation();
				}
				}
				setState(460);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(462);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(461);
				access();
				}
			}

			setState(465);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				{
				setState(464);
				modifier();
				}
				break;
			}
			setState(467);
			match(T__32);
			setState(468);
			match(IDENTIFIER);
			setState(470);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(469);
				genericDeclaration();
				}
			}

			setState(472);
			match(T__8);
			setState(474);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(473);
				parameterList();
				}
			}

			setState(476);
			match(T__9);
			setState(479);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(477);
				match(T__11);
				setState(478);
				type(0);
				}
				break;
			}
			setState(482);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(481);
				block();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstructorDeclarationContext extends ParserRuleContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public GenericDeclarationContext genericDeclaration() {
			return getRuleContext(GenericDeclarationContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public ConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDeclaration; }
	}

	public final ConstructorDeclarationContext constructorDeclaration() throws RecognitionException {
		ConstructorDeclarationContext _localctx = new ConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_constructorDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(487);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__40) {
				{
				{
				setState(484);
				annotation();
				}
				}
				setState(489);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(491);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(490);
				access();
				}
			}

			setState(494);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				{
				setState(493);
				modifier();
				}
				break;
			}
			setState(496);
			match(T__33);
			setState(498);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(497);
				genericDeclaration();
				}
			}

			setState(500);
			match(T__8);
			setState(502);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(501);
				parameterList();
				}
			}

			setState(504);
			match(T__9);
			setState(505);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OperatorDeclarationContext extends ParserRuleContext {
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public GenericDeclarationContext genericDeclaration() {
			return getRuleContext(GenericDeclarationContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public OperatorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operatorDeclaration; }
	}

	public final OperatorDeclarationContext operatorDeclaration() throws RecognitionException {
		OperatorDeclarationContext _localctx = new OperatorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_operatorDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(510);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__40) {
				{
				{
				setState(507);
				annotation();
				}
				}
				setState(512);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(514);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(513);
				access();
				}
			}

			setState(517);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				{
				setState(516);
				modifier();
				}
				break;
			}
			setState(519);
			match(T__34);
			setState(520);
			operator();
			setState(522);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(521);
				genericDeclaration();
				}
			}

			setState(524);
			match(T__8);
			setState(526);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(525);
				parameterList();
				}
			}

			setState(528);
			match(T__9);
			setState(531);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(529);
				match(T__11);
				setState(530);
				type(0);
				}
			}

			setState(533);
			block();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterListContext extends ParserRuleContext {
		public List<ParameterContext> parameter() {
			return getRuleContexts(ParameterContext.class);
		}
		public ParameterContext parameter(int i) {
			return getRuleContext(ParameterContext.class,i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(535);
			parameter();
			setState(540);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(536);
				match(T__10);
				setState(537);
				parameter();
				}
				}
				setState(542);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543);
			match(IDENTIFIER);
			setState(544);
			match(T__11);
			setState(545);
			type(0);
			setState(548);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__29) {
				{
				setState(546);
				match(T__29);
				setState(547);
				expression(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArgumentListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argumentList; }
	}

	public final ArgumentListContext argumentList() throws RecognitionException {
		ArgumentListContext _localctx = new ArgumentListContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_argumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(550);
			expression(0);
			setState(555);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(551);
				match(T__10);
				setState(552);
				expression(0);
				}
				}
				setState(557);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public GenericDeclarationContext genericDeclaration() {
			return getRuleContext(GenericDeclarationContext.class,0);
		}
		public InheritanceContext inheritance() {
			return getRuleContext(InheritanceContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(561);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__40) {
				{
				{
				setState(558);
				annotation();
				}
				}
				setState(563);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(565);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(564);
				access();
				}
			}

			setState(568);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				{
				setState(567);
				modifier();
				}
				break;
			}
			setState(570);
			match(T__35);
			setState(571);
			match(IDENTIFIER);
			setState(573);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				{
				setState(572);
				genericDeclaration();
				}
				break;
			}
			setState(576);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
			case 1:
				{
				setState(575);
				inheritance();
				}
				break;
			}
			setState(579);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				{
				setState(578);
				block();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InheritanceContext extends ParserRuleContext {
		public List<InheritanceSpecContext> inheritanceSpec() {
			return getRuleContexts(InheritanceSpecContext.class);
		}
		public InheritanceSpecContext inheritanceSpec(int i) {
			return getRuleContext(InheritanceSpecContext.class,i);
		}
		public InheritanceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inheritance; }
	}

	public final InheritanceContext inheritance() throws RecognitionException {
		InheritanceContext _localctx = new InheritanceContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_inheritance);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(581);
			match(T__8);
			setState(582);
			inheritanceSpec();
			setState(587);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(583);
				match(T__10);
				setState(584);
				inheritanceSpec();
				}
				}
				setState(589);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(590);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InheritanceSpecContext extends ParserRuleContext {
		public TypeContext first;
		public TypeContext extends_;
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public InheritanceSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inheritanceSpec; }
	}

	public final InheritanceSpecContext inheritanceSpec() throws RecognitionException {
		InheritanceSpecContext _localctx = new InheritanceSpecContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_inheritanceSpec);
		int _la;
		try {
			setState(609);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(592);
				((InheritanceSpecContext)_localctx).first = type(0);
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 2);
				{
				setState(593);
				match(T__36);
				setState(594);
				match(T__29);
				setState(595);
				((InheritanceSpecContext)_localctx).extends_ = type(0);
				}
				break;
			case T__37:
				enterOuterAlt(_localctx, 3);
				{
				setState(596);
				match(T__37);
				setState(597);
				match(T__29);
				setState(598);
				match(T__3);
				setState(599);
				type(0);
				setState(604);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__10) {
					{
					{
					setState(600);
					match(T__10);
					setState(601);
					type(0);
					}
					}
					setState(606);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(607);
				match(T__4);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InterfaceDeclarationContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public InheritanceContext inheritance() {
			return getRuleContext(InheritanceContext.class,0);
		}
		public GenericDeclarationContext genericDeclaration() {
			return getRuleContext(GenericDeclarationContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public InterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceDeclaration; }
	}

	public final InterfaceDeclarationContext interfaceDeclaration() throws RecognitionException {
		InterfaceDeclarationContext _localctx = new InterfaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_interfaceDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(614);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__40) {
				{
				{
				setState(611);
				annotation();
				}
				}
				setState(616);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(618);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(617);
				access();
				}
			}

			setState(621);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				{
				setState(620);
				modifier();
				}
				break;
			}
			setState(623);
			match(T__38);
			setState(624);
			match(IDENTIFIER);
			setState(626);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				{
				setState(625);
				inheritance();
				}
				break;
			}
			setState(629);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(628);
				genericDeclaration();
				}
				break;
			}
			setState(632);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
			case 1:
				{
				setState(631);
				block();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionSignatureContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public GenericDeclarationContext genericDeclaration() {
			return getRuleContext(GenericDeclarationContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public FunctionSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionSignature; }
	}

	public final FunctionSignatureContext functionSignature() throws RecognitionException {
		FunctionSignatureContext _localctx = new FunctionSignatureContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_functionSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(637);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__40) {
				{
				{
				setState(634);
				annotation();
				}
				}
				setState(639);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(641);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(640);
				access();
				}
			}

			setState(644);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
			case 1:
				{
				setState(643);
				modifier();
				}
				break;
			}
			setState(646);
			match(T__32);
			setState(648);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(647);
				genericDeclaration();
				}
			}

			setState(650);
			match(IDENTIFIER);
			setState(651);
			match(T__8);
			setState(653);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(652);
				parameterList();
				}
			}

			setState(655);
			match(T__9);
			{
			setState(656);
			match(T__11);
			setState(657);
			type(0);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationDeclarationContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public AnnotationDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationDeclaration; }
	}

	public final AnnotationDeclarationContext annotationDeclaration() throws RecognitionException {
		AnnotationDeclarationContext _localctx = new AnnotationDeclarationContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_annotationDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(662);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__40) {
				{
				{
				setState(659);
				annotation();
				}
				}
				setState(664);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(666);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(665);
				access();
				}
			}

			setState(669);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				{
				setState(668);
				modifier();
				}
				break;
			}
			setState(671);
			match(T__39);
			setState(672);
			match(IDENTIFIER);
			setState(678);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
			case 1:
				{
				setState(673);
				match(T__8);
				setState(675);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(674);
					parameterList();
					}
				}

				setState(677);
				match(T__9);
				}
				break;
			}
			setState(681);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				{
				setState(680);
				block();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public AnnotationArgsContext annotationArgs() {
			return getRuleContext(AnnotationArgsContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(683);
			match(T__40);
			setState(684);
			match(IDENTIFIER);
			setState(692);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(685);
				match(T__8);
				setState(688);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(686);
					annotationArgs();
					}
					break;
				case T__44:
				case T__45:
				case T__46:
				case NUMBER:
				case STRING:
					{
					setState(687);
					literal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(690);
				match(T__9);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationArgsContext extends ParserRuleContext {
		public List<AnnotationArgContext> annotationArg() {
			return getRuleContexts(AnnotationArgContext.class);
		}
		public AnnotationArgContext annotationArg(int i) {
			return getRuleContext(AnnotationArgContext.class,i);
		}
		public AnnotationArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationArgs; }
	}

	public final AnnotationArgsContext annotationArgs() throws RecognitionException {
		AnnotationArgsContext _localctx = new AnnotationArgsContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_annotationArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(694);
			annotationArg();
			setState(699);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(695);
				match(T__10);
				setState(696);
				annotationArg();
				}
				}
				setState(701);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnnotationArgContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public TerminalNode STRING() { return getToken(LumParser.STRING, 0); }
		public AnnotationArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationArg; }
	}

	public final AnnotationArgContext annotationArg() throws RecognitionException {
		AnnotationArgContext _localctx = new AnnotationArgContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_annotationArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(702);
			match(IDENTIFIER);
			setState(703);
			match(T__29);
			setState(704);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionCallContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public TerminalNode NEW() { return getToken(LumParser.NEW, 0); }
		public GenericDeclarationContext genericDeclaration() {
			return getRuleContext(GenericDeclarationContext.class,0);
		}
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public FunctionCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionCall; }
	}

	public final FunctionCallContext functionCall() throws RecognitionException {
		FunctionCallContext _localctx = new FunctionCallContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(706);
			_la = _input.LA(1);
			if ( !(_la==NEW || _la==IDENTIFIER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(708);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(707);
				genericDeclaration();
				}
			}

			setState(710);
			match(T__8);
			setState(712);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246290604622608L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 29711L) != 0)) {
				{
				setState(711);
				argumentList();
				}
			}

			setState(714);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SuperCallContext extends ParserRuleContext {
		public TerminalNode NEW() { return getToken(LumParser.NEW, 0); }
		public ArgumentListContext argumentList() {
			return getRuleContext(ArgumentListContext.class,0);
		}
		public SuperCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superCall; }
	}

	public final SuperCallContext superCall() throws RecognitionException {
		SuperCallContext _localctx = new SuperCallContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_superCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(716);
			match(T__7);
			setState(717);
			match(T__5);
			setState(718);
			match(NEW);
			setState(719);
			match(T__8);
			setState(721);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246290604622608L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 29711L) != 0)) {
				{
				setState(720);
				argumentList();
				}
			}

			setState(723);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	 
		public TypeContext() { }
		public void copyFrom(TypeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayTypeContext extends TypeContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<TerminalNode> ARRAY() { return getTokens(LumParser.ARRAY); }
		public TerminalNode ARRAY(int i) {
			return getToken(LumParser.ARRAY, i);
		}
		public ArrayTypeContext(TypeContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnionTypeContext extends TypeContext {
		public Token union;
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public UnionTypeContext(TypeContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntersectionTypeContext extends TypeContext {
		public Token intersection;
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public IntersectionTypeContext(TypeContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PlainTypeContext extends TypeContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(LumParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(LumParser.IDENTIFIER, i);
		}
		public GenericDeclarationContext genericDeclaration() {
			return getRuleContext(GenericDeclarationContext.class,0);
		}
		public PlainTypeContext(TypeContext ctx) { copyFrom(ctx); }
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 110;
		enterRecursionRule(_localctx, 110, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			_localctx = new PlainTypeContext(_localctx);
			_ctx = _localctx;
			_prevctx = _localctx;

			{
			{
			setState(726);
			match(IDENTIFIER);
			setState(731);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(727);
					match(T__5);
					setState(728);
					match(IDENTIFIER);
					}
					} 
				}
				setState(733);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
			}
			}
			setState(735);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				{
				setState(734);
				genericDeclaration();
				}
				break;
			}
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(759);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(757);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,104,_ctx) ) {
					case 1:
						{
						_localctx = new UnionTypeContext(new TypeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(737);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(740); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(738);
								((UnionTypeContext)_localctx).union = match(T__41);
								setState(739);
								type(0);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(742); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,101,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					case 2:
						{
						_localctx = new IntersectionTypeContext(new TypeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(744);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(747); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(745);
								((IntersectionTypeContext)_localctx).intersection = match(T__42);
								setState(746);
								type(0);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(749); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					case 3:
						{
						_localctx = new ArrayTypeContext(new TypeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(751);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(753); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(752);
								match(ARRAY);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(755); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,103,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					}
					} 
				}
				setState(761);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GenericDeclarationContext extends ParserRuleContext {
		public List<GenericContext> generic() {
			return getRuleContexts(GenericContext.class);
		}
		public GenericContext generic(int i) {
			return getRuleContext(GenericContext.class,i);
		}
		public GenericDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericDeclaration; }
	}

	public final GenericDeclarationContext genericDeclaration() throws RecognitionException {
		GenericDeclarationContext _localctx = new GenericDeclarationContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_genericDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(762);
			match(T__3);
			setState(763);
			generic();
			setState(768);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__10) {
				{
				{
				setState(764);
				match(T__10);
				setState(765);
				generic();
				}
				}
				setState(770);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(771);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GenericContext extends ParserRuleContext {
		public GenericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generic; }
	 
		public GenericContext() { }
		public void copyFrom(GenericContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnboundGenericContext extends GenericContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public UnboundGenericContext(GenericContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoundGenericContext extends GenericContext {
		public Token QUESTION_MARK;
		public Token extends_;
		public Token super_;
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public BoundGenericContext(GenericContext ctx) { copyFrom(ctx); }
	}

	public final GenericContext generic() throws RecognitionException {
		GenericContext _localctx = new GenericContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_generic);
		int _la;
		try {
			setState(783);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,109,_ctx) ) {
			case 1:
				_localctx = new UnboundGenericContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(773);
				type(0);
				}
				break;
			case 2:
				_localctx = new BoundGenericContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(776);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(774);
					match(IDENTIFIER);
					}
					break;
				case T__43:
					{
					setState(775);
					((BoundGenericContext)_localctx).QUESTION_MARK = match(T__43);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				{
				setState(780);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__11:
				case T__36:
					{
					setState(778);
					((BoundGenericContext)_localctx).extends_ = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__11 || _la==T__36) ) {
						((BoundGenericContext)_localctx).extends_ = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				case T__7:
					{
					setState(779);
					((BoundGenericContext)_localctx).super_ = match(T__7);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(782);
				type(0);
				}
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StrContext extends LiteralContext {
		public TerminalNode STRING() { return getToken(LumParser.STRING, 0); }
		public StrContext(LiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NullContext extends LiteralContext {
		public NullContext(LiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumContext extends LiteralContext {
		public TerminalNode NUMBER() { return getToken(LumParser.NUMBER, 0); }
		public NumContext(LiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TrueContext extends LiteralContext {
		public TrueContext(LiteralContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FalseContext extends LiteralContext {
		public FalseContext(LiteralContext ctx) { copyFrom(ctx); }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_literal);
		try {
			setState(790);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				_localctx = new NumContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(785);
				match(NUMBER);
				}
				break;
			case STRING:
				_localctx = new StrContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(786);
				match(STRING);
				}
				break;
			case T__44:
				_localctx = new NullContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(787);
				match(T__44);
				}
				break;
			case T__45:
				_localctx = new TrueContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(788);
				match(T__45);
				}
				break;
			case T__46:
				_localctx = new FalseContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(789);
				match(T__46);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OperatorContext extends ParserRuleContext {
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
	 
		public OperatorContext() { }
		public void copyFrom(OperatorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArrayAccessOpContext extends OperatorContext {
		public TerminalNode ARRAY() { return getToken(LumParser.ARRAY, 0); }
		public ArrayAccessOpContext(OperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryOpContext extends OperatorContext {
		public UnaryOperatorContext unaryOperator() {
			return getRuleContext(UnaryOperatorContext.class,0);
		}
		public UnaryOpContext(OperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BinaryOpContext extends OperatorContext {
		public BinaryOperatorContext binaryOperator() {
			return getRuleContext(BinaryOperatorContext.class,0);
		}
		public BinaryOpContext(OperatorContext ctx) { copyFrom(ctx); }
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_operator);
		try {
			setState(795);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__27:
			case T__41:
			case T__42:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__51:
			case T__52:
			case T__53:
			case T__54:
			case T__55:
			case T__56:
			case T__57:
			case T__58:
			case T__59:
			case T__60:
			case T__61:
			case T__62:
			case T__63:
			case T__64:
			case T__65:
			case T__66:
			case T__67:
				_localctx = new BinaryOpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(792);
				binaryOperator();
				}
				break;
			case T__68:
			case T__69:
			case T__70:
			case T__71:
				_localctx = new UnaryOpContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(793);
				unaryOperator();
				}
				break;
			case ARRAY:
				_localctx = new ArrayAccessOpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(794);
				match(ARRAY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BinaryOperatorContext extends ParserRuleContext {
		public BinaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binaryOperator; }
	 
		public BinaryOperatorContext() { }
		public void copyFrom(BinaryOperatorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AddContext extends BinaryOperatorContext {
		public AddContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SubContext extends BinaryOperatorContext {
		public SubContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MODContext extends BinaryOperatorContext {
		public MODContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ORContext extends BinaryOperatorContext {
		public ORContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MulContext extends BinaryOperatorContext {
		public MulContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InContext extends BinaryOperatorContext {
		public InContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CORContext extends BinaryOperatorContext {
		public CORContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LTContext extends BinaryOperatorContext {
		public LTContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CANDContext extends BinaryOperatorContext {
		public CANDContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EQContext extends BinaryOperatorContext {
		public EQContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GTContext extends BinaryOperatorContext {
		public GTContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DIVContext extends BinaryOperatorContext {
		public DIVContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DivideContext extends BinaryOperatorContext {
		public DivideContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SHLContext extends BinaryOperatorContext {
		public SHLContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ANDContext extends BinaryOperatorContext {
		public ANDContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LEContext extends BinaryOperatorContext {
		public LEContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class XORContext extends BinaryOperatorContext {
		public XORContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NEQContext extends BinaryOperatorContext {
		public NEQContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class SHRContext extends BinaryOperatorContext {
		public SHRContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class GEContext extends BinaryOperatorContext {
		public GEContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IsInstanceContext extends BinaryOperatorContext {
		public IsInstanceContext(BinaryOperatorContext ctx) { copyFrom(ctx); }
	}

	public final BinaryOperatorContext binaryOperator() throws RecognitionException {
		BinaryOperatorContext _localctx = new BinaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_binaryOperator);
		try {
			setState(821);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__47:
				_localctx = new MulContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(797);
				match(T__47);
				}
				break;
			case T__48:
				_localctx = new DivideContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(798);
				match(T__48);
				}
				break;
			case T__49:
				_localctx = new DIVContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(799);
				match(T__49);
				}
				break;
			case T__50:
				_localctx = new MODContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(800);
				match(T__50);
				}
				break;
			case T__51:
				_localctx = new AddContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(801);
				match(T__51);
				}
				break;
			case T__52:
				_localctx = new SubContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(802);
				match(T__52);
				}
				break;
			case T__53:
				_localctx = new SHRContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(803);
				match(T__53);
				}
				break;
			case T__54:
				_localctx = new SHLContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(804);
				match(T__54);
				}
				break;
			case T__42:
				_localctx = new ANDContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(805);
				match(T__42);
				}
				break;
			case T__55:
				_localctx = new XORContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(806);
				match(T__55);
				}
				break;
			case T__56:
				_localctx = new XORContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(807);
				match(T__56);
				}
				break;
			case T__41:
				_localctx = new ORContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(808);
				match(T__41);
				}
				break;
			case T__57:
				_localctx = new GTContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(809);
				match(T__57);
				}
				break;
			case T__58:
				_localctx = new LTContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(810);
				match(T__58);
				}
				break;
			case T__59:
				_localctx = new GEContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(811);
				match(T__59);
				}
				break;
			case T__60:
				_localctx = new LEContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(812);
				match(T__60);
				}
				break;
			case T__61:
				_localctx = new EQContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(813);
				match(T__61);
				}
				break;
			case T__62:
				_localctx = new NEQContext(_localctx);
				enterOuterAlt(_localctx, 18);
				{
				setState(814);
				match(T__62);
				}
				break;
			case T__63:
				_localctx = new IsInstanceContext(_localctx);
				enterOuterAlt(_localctx, 19);
				{
				setState(815);
				match(T__63);
				}
				break;
			case T__27:
				_localctx = new InContext(_localctx);
				enterOuterAlt(_localctx, 20);
				{
				setState(816);
				match(T__27);
				}
				break;
			case T__64:
				_localctx = new CANDContext(_localctx);
				enterOuterAlt(_localctx, 21);
				{
				setState(817);
				match(T__64);
				}
				break;
			case T__65:
				_localctx = new CANDContext(_localctx);
				enterOuterAlt(_localctx, 22);
				{
				setState(818);
				match(T__65);
				}
				break;
			case T__66:
				_localctx = new CORContext(_localctx);
				enterOuterAlt(_localctx, 23);
				{
				setState(819);
				match(T__66);
				}
				break;
			case T__67:
				_localctx = new CORContext(_localctx);
				enterOuterAlt(_localctx, 24);
				{
				setState(820);
				match(T__67);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UnaryOperatorContext extends ParserRuleContext {
		public UnaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOperator; }
	 
		public UnaryOperatorContext() { }
		public void copyFrom(UnaryOperatorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostUnaryOpContext extends UnaryOperatorContext {
		public PostUnaryOperatorContext postUnaryOperator() {
			return getRuleContext(PostUnaryOperatorContext.class,0);
		}
		public PostUnaryOpContext(UnaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PreUnaryOpContext extends UnaryOperatorContext {
		public PreUnaryOperatorContext preUnaryOperator() {
			return getRuleContext(PreUnaryOperatorContext.class,0);
		}
		public PreUnaryOpContext(UnaryOperatorContext ctx) { copyFrom(ctx); }
	}

	public final UnaryOperatorContext unaryOperator() throws RecognitionException {
		UnaryOperatorContext _localctx = new UnaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_unaryOperator);
		try {
			setState(825);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
			case 1:
				_localctx = new PreUnaryOpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(823);
				preUnaryOperator();
				}
				break;
			case 2:
				_localctx = new PostUnaryOpContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(824);
				postUnaryOperator();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PreUnaryOperatorContext extends ParserRuleContext {
		public PreUnaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preUnaryOperator; }
	 
		public PreUnaryOperatorContext() { }
		public void copyFrom(PreUnaryOperatorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotContext extends PreUnaryOperatorContext {
		public NotContext(PreUnaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AnyUnaryContext extends PreUnaryOperatorContext {
		public PostUnaryOperatorContext postUnaryOperator() {
			return getRuleContext(PostUnaryOperatorContext.class,0);
		}
		public AnyUnaryContext(PreUnaryOperatorContext ctx) { copyFrom(ctx); }
	}

	public final PreUnaryOperatorContext preUnaryOperator() throws RecognitionException {
		PreUnaryOperatorContext _localctx = new PreUnaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_preUnaryOperator);
		try {
			setState(830);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__70:
			case T__71:
				_localctx = new AnyUnaryContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(827);
				postUnaryOperator();
				}
				break;
			case T__68:
				_localctx = new NotContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(828);
				match(T__68);
				}
				break;
			case T__69:
				_localctx = new NotContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(829);
				match(T__69);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PostUnaryOperatorContext extends ParserRuleContext {
		public PostUnaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postUnaryOperator; }
	 
		public PostUnaryOperatorContext() { }
		public void copyFrom(PostUnaryOperatorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DecrementContext extends PostUnaryOperatorContext {
		public DecrementContext(PostUnaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IncrementContext extends PostUnaryOperatorContext {
		public IncrementContext(PostUnaryOperatorContext ctx) { copyFrom(ctx); }
	}

	public final PostUnaryOperatorContext postUnaryOperator() throws RecognitionException {
		PostUnaryOperatorContext _localctx = new PostUnaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_postUnaryOperator);
		try {
			setState(834);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__70:
				_localctx = new IncrementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(832);
				match(T__70);
				}
				break;
			case T__71:
				_localctx = new DecrementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(833);
				match(T__71);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignmentContext extends ParserRuleContext {
		public Token eq;
		public Token typeEq;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BinaryOperatorContext binaryOperator() {
			return getRuleContext(BinaryOperatorContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(837);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 28)) & ~0x3f) == 0 && ((1L << (_la - 28)) & 2199022256129L) != 0)) {
				{
				setState(836);
				binaryOperator();
				}
			}

			setState(841);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__29:
				{
				setState(839);
				((AssignmentContext)_localctx).eq = match(T__29);
				}
				break;
			case T__28:
				{
				setState(840);
				((AssignmentContext)_localctx).typeEq = match(T__28);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(843);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AccessContext extends ParserRuleContext {
		public AccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_access; }
	 
		public AccessContext() { }
		public void copyFrom(AccessContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ProtectedContext extends AccessContext {
		public ProtectedContext(AccessContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrivateContext extends AccessContext {
		public PrivateContext(AccessContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PublicContext extends AccessContext {
		public PublicContext(AccessContext ctx) { copyFrom(ctx); }
	}

	public final AccessContext access() throws RecognitionException {
		AccessContext _localctx = new AccessContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_access);
		try {
			setState(848);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__72:
				_localctx = new PublicContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(845);
				match(T__72);
				}
				break;
			case T__73:
				_localctx = new PrivateContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(846);
				match(T__73);
				}
				break;
			case T__74:
				_localctx = new ProtectedContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(847);
				match(T__74);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ModifierContext extends ParserRuleContext {
		public Token static_;
		public Token abstract_;
		public Token final_;
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(852);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__75:
				{
				setState(850);
				((ModifierContext)_localctx).static_ = match(T__75);
				}
				break;
			case T__76:
				{
				setState(851);
				((ModifierContext)_localctx).abstract_ = match(T__76);
				}
				break;
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__38:
			case T__39:
			case T__77:
			case IDENTIFIER:
				break;
			default:
				break;
			}
			setState(855);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__77) {
				{
				setState(854);
				((ModifierContext)_localctx).final_ = match(T__77);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		case 55:
			return type_sempred((TypeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 6);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 3);
		case 7:
			return precpred(_ctx, 2);
		case 8:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001U\u035a\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0002"+
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0002"+
		"A\u0007A\u0002B\u0007B\u0001\u0000\u0005\u0000\u0088\b\u0000\n\u0000\f"+
		"\u0000\u008b\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001\u0097\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u00a1\b\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u00a6\b\u0003\u0001\u0004\u0001"+
		"\u0004\u0005\u0004\u00aa\b\u0004\n\u0004\f\u0004\u00ad\t\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0003\u0004\u00b2\b\u0004\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0003\u0005\u00bc\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00cb\b\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u0005\u00d4\b\u0005\n\u0005\f\u0005\u00d7\t\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00de\b\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00e3\b\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00ea\b\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u00ef\b\u0007\n\u0007\f\u0007"+
		"\u00f2\t\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0005\t\u00fc\b\t\n\t\f\t\u00ff\t\t\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u0105\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0005\f\u010e\b\f\n\f\f\f\u0111\t\f\u0001\r\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0005\r\u0118\b\r\n\r\f\r\u011b\t\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0005\u000e\u0120\b\u000e\n\u000e\f\u000e\u0123"+
		"\t\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u0127\b\u000f\u0001\u0010"+
		"\u0001\u0010\u0003\u0010\u012b\b\u0010\u0001\u0011\u0001\u0011\u0003\u0011"+
		"\u012f\b\u0011\u0001\u0012\u0001\u0012\u0003\u0012\u0133\b\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u013a"+
		"\b\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0005\u0014\u0142\b\u0014\n\u0014\f\u0014\u0145\t\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0005\u0017"+
		"\u0153\b\u0017\n\u0017\f\u0017\u0156\t\u0017\u0001\u0017\u0003\u0017\u0159"+
		"\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001b\u0001\u001b\u0003\u001b\u0168\b\u001b\u0001\u001c\u0001\u001c\u0003"+
		"\u001c\u016c\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001"+
		"\u001f\u0003\u001f\u0179\b\u001f\u0001 \u0001 \u0003 \u017d\b \u0001 "+
		"\u0001 \u0003 \u0181\b \u0001 \u0001 \u0003 \u0185\b \u0001 \u0001 \u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001\"\u0005\"\u0190\b\"\n\"\f\""+
		"\u0193\t\"\u0001\"\u0003\"\u0196\b\"\u0001\"\u0003\"\u0199\b\"\u0001\""+
		"\u0001\"\u0001\"\u0005\"\u019e\b\"\n\"\f\"\u01a1\t\"\u0001#\u0001#\u0001"+
		"#\u0003#\u01a6\b#\u0001#\u0001#\u0003#\u01aa\b#\u0001#\u0003#\u01ad\b"+
		"#\u0001#\u0001#\u0005#\u01b1\b#\n#\f#\u01b4\t#\u0001$\u0003$\u01b7\b$"+
		"\u0001$\u0001$\u0003$\u01bb\b$\u0001%\u0003%\u01be\b%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001%\u0001%\u0003%\u01c6\b%\u0001&\u0005&\u01c9\b&\n&\f&\u01cc"+
		"\t&\u0001&\u0003&\u01cf\b&\u0001&\u0003&\u01d2\b&\u0001&\u0001&\u0001"+
		"&\u0003&\u01d7\b&\u0001&\u0001&\u0003&\u01db\b&\u0001&\u0001&\u0001&\u0003"+
		"&\u01e0\b&\u0001&\u0003&\u01e3\b&\u0001\'\u0005\'\u01e6\b\'\n\'\f\'\u01e9"+
		"\t\'\u0001\'\u0003\'\u01ec\b\'\u0001\'\u0003\'\u01ef\b\'\u0001\'\u0001"+
		"\'\u0003\'\u01f3\b\'\u0001\'\u0001\'\u0003\'\u01f7\b\'\u0001\'\u0001\'"+
		"\u0001\'\u0001(\u0005(\u01fd\b(\n(\f(\u0200\t(\u0001(\u0003(\u0203\b("+
		"\u0001(\u0003(\u0206\b(\u0001(\u0001(\u0001(\u0003(\u020b\b(\u0001(\u0001"+
		"(\u0003(\u020f\b(\u0001(\u0001(\u0001(\u0003(\u0214\b(\u0001(\u0001(\u0001"+
		")\u0001)\u0001)\u0005)\u021b\b)\n)\f)\u021e\t)\u0001*\u0001*\u0001*\u0001"+
		"*\u0001*\u0003*\u0225\b*\u0001+\u0001+\u0001+\u0005+\u022a\b+\n+\f+\u022d"+
		"\t+\u0001,\u0005,\u0230\b,\n,\f,\u0233\t,\u0001,\u0003,\u0236\b,\u0001"+
		",\u0003,\u0239\b,\u0001,\u0001,\u0001,\u0003,\u023e\b,\u0001,\u0003,\u0241"+
		"\b,\u0001,\u0003,\u0244\b,\u0001-\u0001-\u0001-\u0001-\u0005-\u024a\b"+
		"-\n-\f-\u024d\t-\u0001-\u0001-\u0001.\u0001.\u0001.\u0001.\u0001.\u0001"+
		".\u0001.\u0001.\u0001.\u0001.\u0005.\u025b\b.\n.\f.\u025e\t.\u0001.\u0001"+
		".\u0003.\u0262\b.\u0001/\u0005/\u0265\b/\n/\f/\u0268\t/\u0001/\u0003/"+
		"\u026b\b/\u0001/\u0003/\u026e\b/\u0001/\u0001/\u0001/\u0003/\u0273\b/"+
		"\u0001/\u0003/\u0276\b/\u0001/\u0003/\u0279\b/\u00010\u00050\u027c\b0"+
		"\n0\f0\u027f\t0\u00010\u00030\u0282\b0\u00010\u00030\u0285\b0\u00010\u0001"+
		"0\u00030\u0289\b0\u00010\u00010\u00010\u00030\u028e\b0\u00010\u00010\u0001"+
		"0\u00010\u00011\u00051\u0295\b1\n1\f1\u0298\t1\u00011\u00031\u029b\b1"+
		"\u00011\u00031\u029e\b1\u00011\u00011\u00011\u00011\u00031\u02a4\b1\u0001"+
		"1\u00031\u02a7\b1\u00011\u00031\u02aa\b1\u00012\u00012\u00012\u00012\u0001"+
		"2\u00032\u02b1\b2\u00012\u00012\u00032\u02b5\b2\u00013\u00013\u00013\u0005"+
		"3\u02ba\b3\n3\f3\u02bd\t3\u00014\u00014\u00014\u00014\u00015\u00015\u0003"+
		"5\u02c5\b5\u00015\u00015\u00035\u02c9\b5\u00015\u00015\u00016\u00016\u0001"+
		"6\u00016\u00016\u00036\u02d2\b6\u00016\u00016\u00017\u00017\u00017\u0001"+
		"7\u00057\u02da\b7\n7\f7\u02dd\t7\u00017\u00037\u02e0\b7\u00017\u00017"+
		"\u00017\u00047\u02e5\b7\u000b7\f7\u02e6\u00017\u00017\u00017\u00047\u02ec"+
		"\b7\u000b7\f7\u02ed\u00017\u00017\u00047\u02f2\b7\u000b7\f7\u02f3\u0005"+
		"7\u02f6\b7\n7\f7\u02f9\t7\u00018\u00018\u00018\u00018\u00058\u02ff\b8"+
		"\n8\f8\u0302\t8\u00018\u00018\u00019\u00019\u00019\u00039\u0309\b9\u0001"+
		"9\u00019\u00039\u030d\b9\u00019\u00039\u0310\b9\u0001:\u0001:\u0001:\u0001"+
		":\u0001:\u0003:\u0317\b:\u0001;\u0001;\u0001;\u0003;\u031c\b;\u0001<\u0001"+
		"<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001"+
		"<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001"+
		"<\u0001<\u0001<\u0003<\u0336\b<\u0001=\u0001=\u0003=\u033a\b=\u0001>\u0001"+
		">\u0001>\u0003>\u033f\b>\u0001?\u0001?\u0003?\u0343\b?\u0001@\u0003@\u0346"+
		"\b@\u0001@\u0001@\u0003@\u034a\b@\u0001@\u0001@\u0001A\u0001A\u0001A\u0003"+
		"A\u0351\bA\u0001B\u0001B\u0003B\u0355\bB\u0001B\u0003B\u0358\bB\u0001"+
		"B\u0000\u0002\nnC\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfh"+
		"jlnprtvxz|~\u0080\u0082\u0084\u0000\u0002\u0002\u0000OOQQ\u0002\u0000"+
		"\f\f%%\u03c7\u0000\u0089\u0001\u0000\u0000\u0000\u0002\u0096\u0001\u0000"+
		"\u0000\u0000\u0004\u00a0\u0001\u0000\u0000\u0000\u0006\u00a5\u0001\u0000"+
		"\u0000\u0000\b\u00b1\u0001\u0000\u0000\u0000\n\u00bb\u0001\u0000\u0000"+
		"\u0000\f\u00e9\u0001\u0000\u0000\u0000\u000e\u00eb\u0001\u0000\u0000\u0000"+
		"\u0010\u00f3\u0001\u0000\u0000\u0000\u0012\u00f7\u0001\u0000\u0000\u0000"+
		"\u0014\u0100\u0001\u0000\u0000\u0000\u0016\u0106\u0001\u0000\u0000\u0000"+
		"\u0018\u010a\u0001\u0000\u0000\u0000\u001a\u0112\u0001\u0000\u0000\u0000"+
		"\u001c\u011c\u0001\u0000\u0000\u0000\u001e\u0126\u0001\u0000\u0000\u0000"+
		" \u0128\u0001\u0000\u0000\u0000\"\u012c\u0001\u0000\u0000\u0000$\u0130"+
		"\u0001\u0000\u0000\u0000&\u0139\u0001\u0000\u0000\u0000(\u013d\u0001\u0000"+
		"\u0000\u0000*\u0149\u0001\u0000\u0000\u0000,\u014d\u0001\u0000\u0000\u0000"+
		".\u0150\u0001\u0000\u0000\u00000\u015a\u0001\u0000\u0000\u00002\u015e"+
		"\u0001\u0000\u0000\u00004\u0162\u0001\u0000\u0000\u00006\u0167\u0001\u0000"+
		"\u0000\u00008\u016b\u0001\u0000\u0000\u0000:\u016d\u0001\u0000\u0000\u0000"+
		"<\u0171\u0001\u0000\u0000\u0000>\u0178\u0001\u0000\u0000\u0000@\u017a"+
		"\u0001\u0000\u0000\u0000B\u0188\u0001\u0000\u0000\u0000D\u0191\u0001\u0000"+
		"\u0000\u0000F\u01a2\u0001\u0000\u0000\u0000H\u01b6\u0001\u0000\u0000\u0000"+
		"J\u01bd\u0001\u0000\u0000\u0000L\u01ca\u0001\u0000\u0000\u0000N\u01e7"+
		"\u0001\u0000\u0000\u0000P\u01fe\u0001\u0000\u0000\u0000R\u0217\u0001\u0000"+
		"\u0000\u0000T\u021f\u0001\u0000\u0000\u0000V\u0226\u0001\u0000\u0000\u0000"+
		"X\u0231\u0001\u0000\u0000\u0000Z\u0245\u0001\u0000\u0000\u0000\\\u0261"+
		"\u0001\u0000\u0000\u0000^\u0266\u0001\u0000\u0000\u0000`\u027d\u0001\u0000"+
		"\u0000\u0000b\u0296\u0001\u0000\u0000\u0000d\u02ab\u0001\u0000\u0000\u0000"+
		"f\u02b6\u0001\u0000\u0000\u0000h\u02be\u0001\u0000\u0000\u0000j\u02c2"+
		"\u0001\u0000\u0000\u0000l\u02cc\u0001\u0000\u0000\u0000n\u02d5\u0001\u0000"+
		"\u0000\u0000p\u02fa\u0001\u0000\u0000\u0000r\u030f\u0001\u0000\u0000\u0000"+
		"t\u0316\u0001\u0000\u0000\u0000v\u031b\u0001\u0000\u0000\u0000x\u0335"+
		"\u0001\u0000\u0000\u0000z\u0339\u0001\u0000\u0000\u0000|\u033e\u0001\u0000"+
		"\u0000\u0000~\u0342\u0001\u0000\u0000\u0000\u0080\u0345\u0001\u0000\u0000"+
		"\u0000\u0082\u0350\u0001\u0000\u0000\u0000\u0084\u0354\u0001\u0000\u0000"+
		"\u0000\u0086\u0088\u0003\u0002\u0001\u0000\u0087\u0086\u0001\u0000\u0000"+
		"\u0000\u0088\u008b\u0001\u0000\u0000\u0000\u0089\u0087\u0001\u0000\u0000"+
		"\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u008c\u0001\u0000\u0000"+
		"\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008c\u008d\u0005\u0000\u0000"+
		"\u0001\u008d\u0001\u0001\u0000\u0000\u0000\u008e\u0097\u0003\u0012\t\u0000"+
		"\u008f\u0097\u0003\n\u0005\u0000\u0090\u0097\u0003\u0014\n\u0000\u0091"+
		"\u0097\u0003\u0004\u0002\u0000\u0092\u0097\u0003\u0006\u0003\u0000\u0093"+
		"\u0097\u0003 \u0010\u0000\u0094\u0097\u0003\"\u0011\u0000\u0095\u0097"+
		"\u0003$\u0012\u0000\u0096\u008e\u0001\u0000\u0000\u0000\u0096\u008f\u0001"+
		"\u0000\u0000\u0000\u0096\u0090\u0001\u0000\u0000\u0000\u0096\u0091\u0001"+
		"\u0000\u0000\u0000\u0096\u0092\u0001\u0000\u0000\u0000\u0096\u0093\u0001"+
		"\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0096\u0095\u0001"+
		"\u0000\u0000\u0000\u0097\u0003\u0001\u0000\u0000\u0000\u0098\u00a1\u0003"+
		"L&\u0000\u0099\u00a1\u0003D\"\u0000\u009a\u00a1\u0003P(\u0000\u009b\u00a1"+
		"\u0003N\'\u0000\u009c\u00a1\u0003`0\u0000\u009d\u00a1\u0003X,\u0000\u009e"+
		"\u00a1\u0003^/\u0000\u009f\u00a1\u0003b1\u0000\u00a0\u0098\u0001\u0000"+
		"\u0000\u0000\u00a0\u0099\u0001\u0000\u0000\u0000\u00a0\u009a\u0001\u0000"+
		"\u0000\u0000\u00a0\u009b\u0001\u0000\u0000\u0000\u00a0\u009c\u0001\u0000"+
		"\u0000\u0000\u00a0\u009d\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000"+
		"\u0000\u0000\u00a0\u009f\u0001\u0000\u0000\u0000\u00a1\u0005\u0001\u0000"+
		"\u0000\u0000\u00a2\u00a6\u0003.\u0017\u0000\u00a3\u00a6\u0003(\u0014\u0000"+
		"\u00a4\u00a6\u00036\u001b\u0000\u00a5\u00a2\u0001\u0000\u0000\u0000\u00a5"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a4\u0001\u0000\u0000\u0000\u00a6"+
		"\u0007\u0001\u0000\u0000\u0000\u00a7\u00ab\u0005\u0001\u0000\u0000\u00a8"+
		"\u00aa\u0003\u0002\u0001\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00aa"+
		"\u00ad\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ab"+
		"\u00ac\u0001\u0000\u0000\u0000\u00ac\u00ae\u0001\u0000\u0000\u0000\u00ad"+
		"\u00ab\u0001\u0000\u0000\u0000\u00ae\u00b2\u0005\u0002\u0000\u0000\u00af"+
		"\u00b0\u0005\u0003\u0000\u0000\u00b0\u00b2\u0003\u0002\u0001\u0000\u00b1"+
		"\u00a7\u0001\u0000\u0000\u0000\u00b1\u00af\u0001\u0000\u0000\u0000\u00b2"+
		"\t\u0001\u0000\u0000\u0000\u00b3\u00b4\u0006\u0005\uffff\uffff\u0000\u00b4"+
		"\u00bc\u0003j5\u0000\u00b5\u00bc\u0003l6\u0000\u00b6\u00bc\u0003&\u0013"+
		"\u0000\u00b7\u00b8\u0003|>\u0000\u00b8\u00b9\u0003\n\u0005\u0005\u00b9"+
		"\u00bc\u0001\u0000\u0000\u0000\u00ba\u00bc\u0003\f\u0006\u0000\u00bb\u00b3"+
		"\u0001\u0000\u0000\u0000\u00bb\u00b5\u0001\u0000\u0000\u0000\u00bb\u00b6"+
		"\u0001\u0000\u0000\u0000\u00bb\u00b7\u0001\u0000\u0000\u0000\u00bb\u00ba"+
		"\u0001\u0000\u0000\u0000\u00bc\u00d5\u0001\u0000\u0000\u0000\u00bd\u00be"+
		"\n\u0004\u0000\u0000\u00be\u00bf\u0003x<\u0000\u00bf\u00c0\u0003\n\u0005"+
		"\u0005\u00c0\u00d4\u0001\u0000\u0000\u0000\u00c1\u00c2\n\b\u0000\u0000"+
		"\u00c2\u00c3\u0005\u0004\u0000\u0000\u00c3\u00c4\u0003V+\u0000\u00c4\u00c5"+
		"\u0005\u0005\u0000\u0000\u00c5\u00d4\u0001\u0000\u0000\u0000\u00c6\u00c7"+
		"\n\u0007\u0000\u0000\u00c7\u00ca\u0005\u0006\u0000\u0000\u00c8\u00cb\u0003"+
		"j5\u0000\u00c9\u00cb\u0005Q\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000"+
		"\u0000\u00ca\u00c9\u0001\u0000\u0000\u0000\u00cb\u00d4\u0001\u0000\u0000"+
		"\u0000\u00cc\u00cd\n\u0006\u0000\u0000\u00cd\u00d4\u0003~?\u0000\u00ce"+
		"\u00cf\n\u0003\u0000\u0000\u00cf\u00d4\u0003\u0080@\u0000\u00d0\u00d1"+
		"\n\u0002\u0000\u0000\u00d1\u00d2\u0005\u0007\u0000\u0000\u00d2\u00d4\u0003"+
		"n7\u0000\u00d3\u00bd\u0001\u0000\u0000\u0000\u00d3\u00c1\u0001\u0000\u0000"+
		"\u0000\u00d3\u00c6\u0001\u0000\u0000\u0000\u00d3\u00cc\u0001\u0000\u0000"+
		"\u0000\u00d3\u00ce\u0001\u0000\u0000\u0000\u00d3\u00d0\u0001\u0000\u0000"+
		"\u0000\u00d4\u00d7\u0001\u0000\u0000\u0000\u00d5\u00d3\u0001\u0000\u0000"+
		"\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u000b\u0001\u0000\u0000"+
		"\u0000\u00d7\u00d5\u0001\u0000\u0000\u0000\u00d8\u00ea\u0003t:\u0000\u00d9"+
		"\u00ea\u0005Q\u0000\u0000\u00da\u00ea\u0005\b\u0000\u0000\u00db\u00dd"+
		"\u0005\u0004\u0000\u0000\u00dc\u00de\u0003V+\u0000\u00dd\u00dc\u0001\u0000"+
		"\u0000\u0000\u00dd\u00de\u0001\u0000\u0000\u0000\u00de\u00df\u0001\u0000"+
		"\u0000\u0000\u00df\u00ea\u0005\u0005\u0000\u0000\u00e0\u00e2\u0005\u0004"+
		"\u0000\u0000\u00e1\u00e3\u0003\u000e\u0007\u0000\u00e2\u00e1\u0001\u0000"+
		"\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000\u0000\u00e3\u00e4\u0001\u0000"+
		"\u0000\u0000\u00e4\u00ea\u0005\u0005\u0000\u0000\u00e5\u00e6\u0005\t\u0000"+
		"\u0000\u00e6\u00e7\u0003\n\u0005\u0000\u00e7\u00e8\u0005\n\u0000\u0000"+
		"\u00e8\u00ea\u0001\u0000\u0000\u0000\u00e9\u00d8\u0001\u0000\u0000\u0000"+
		"\u00e9\u00d9\u0001\u0000\u0000\u0000\u00e9\u00da\u0001\u0000\u0000\u0000"+
		"\u00e9\u00db\u0001\u0000\u0000\u0000\u00e9\u00e0\u0001\u0000\u0000\u0000"+
		"\u00e9\u00e5\u0001\u0000\u0000\u0000\u00ea\r\u0001\u0000\u0000\u0000\u00eb"+
		"\u00f0\u0003\u0010\b\u0000\u00ec\u00ed\u0005\u000b\u0000\u0000\u00ed\u00ef"+
		"\u0003\u0010\b\u0000\u00ee\u00ec\u0001\u0000\u0000\u0000\u00ef\u00f2\u0001"+
		"\u0000\u0000\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001"+
		"\u0000\u0000\u0000\u00f1\u000f\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001"+
		"\u0000\u0000\u0000\u00f3\u00f4\u0003\n\u0005\u0000\u00f4\u00f5\u0005\f"+
		"\u0000\u0000\u00f5\u00f6\u0003\n\u0005\u0000\u00f6\u0011\u0001\u0000\u0000"+
		"\u0000\u00f7\u00f8\u0005\r\u0000\u0000\u00f8\u00fd\u0005Q\u0000\u0000"+
		"\u00f9\u00fa\u0005\u0006\u0000\u0000\u00fa\u00fc\u0005Q\u0000\u0000\u00fb"+
		"\u00f9\u0001\u0000\u0000\u0000\u00fc\u00ff\u0001\u0000\u0000\u0000\u00fd"+
		"\u00fb\u0001\u0000\u0000\u0000\u00fd\u00fe\u0001\u0000\u0000\u0000\u00fe"+
		"\u0013\u0001\u0000\u0000\u0000\u00ff\u00fd\u0001\u0000\u0000\u0000\u0100"+
		"\u0104\u0005\u000e\u0000\u0000\u0101\u0105\u0003\u001e\u000f\u0000\u0102"+
		"\u0105\u0003\u001a\r\u0000\u0103\u0105\u0003\u001c\u000e\u0000\u0104\u0101"+
		"\u0001\u0000\u0000\u0000\u0104\u0102\u0001\u0000\u0000\u0000\u0104\u0103"+
		"\u0001\u0000\u0000\u0000\u0105\u0015\u0001\u0000\u0000\u0000\u0106\u0107"+
		"\u0003\u0018\f\u0000\u0107\u0108\u0005\u0007\u0000\u0000\u0108\u0109\u0005"+
		"Q\u0000\u0000\u0109\u0017\u0001\u0000\u0000\u0000\u010a\u010f\u0005Q\u0000"+
		"\u0000\u010b\u010c\u0005\u0006\u0000\u0000\u010c\u010e\u0005Q\u0000\u0000"+
		"\u010d\u010b\u0001\u0000\u0000\u0000\u010e\u0111\u0001\u0000\u0000\u0000"+
		"\u010f\u010d\u0001\u0000\u0000\u0000\u010f\u0110\u0001\u0000\u0000\u0000"+
		"\u0110\u0019\u0001\u0000\u0000\u0000\u0111\u010f\u0001\u0000\u0000\u0000"+
		"\u0112\u0113\u0003\u001c\u000e\u0000\u0113\u0114\u0005\u000f\u0000\u0000"+
		"\u0114\u0119\u0005Q\u0000\u0000\u0115\u0116\u0005\u0006\u0000\u0000\u0116"+
		"\u0118\u0005Q\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u0118\u011b"+
		"\u0001\u0000\u0000\u0000\u0119\u0117\u0001\u0000\u0000\u0000\u0119\u011a"+
		"\u0001\u0000\u0000\u0000\u011a\u001b\u0001\u0000\u0000\u0000\u011b\u0119"+
		"\u0001\u0000\u0000\u0000\u011c\u0121\u0003\u001e\u000f\u0000\u011d\u011e"+
		"\u0005\u000b\u0000\u0000\u011e\u0120\u0003\u001e\u000f\u0000\u011f\u011d"+
		"\u0001\u0000\u0000\u0000\u0120\u0123\u0001\u0000\u0000\u0000\u0121\u011f"+
		"\u0001\u0000\u0000\u0000\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u001d"+
		"\u0001\u0000\u0000\u0000\u0123\u0121\u0001\u0000\u0000\u0000\u0124\u0127"+
		"\u0003\u0018\f\u0000\u0125\u0127\u0003\u0016\u000b\u0000\u0126\u0124\u0001"+
		"\u0000\u0000\u0000\u0126\u0125\u0001\u0000\u0000\u0000\u0127\u001f\u0001"+
		"\u0000\u0000\u0000\u0128\u012a\u0005\u0010\u0000\u0000\u0129\u012b\u0005"+
		"Q\u0000\u0000\u012a\u0129\u0001\u0000\u0000\u0000\u012a\u012b\u0001\u0000"+
		"\u0000\u0000\u012b!\u0001\u0000\u0000\u0000\u012c\u012e\u0005\u0011\u0000"+
		"\u0000\u012d\u012f\u0005Q\u0000\u0000\u012e\u012d\u0001\u0000\u0000\u0000"+
		"\u012e\u012f\u0001\u0000\u0000\u0000\u012f#\u0001\u0000\u0000\u0000\u0130"+
		"\u0132\u0005\u0012\u0000\u0000\u0131\u0133\u0003\n\u0005\u0000\u0132\u0131"+
		"\u0001\u0000\u0000\u0000\u0132\u0133\u0001\u0000\u0000\u0000\u0133%\u0001"+
		"\u0000\u0000\u0000\u0134\u013a\u0003R)\u0000\u0135\u0136\u0005\t\u0000"+
		"\u0000\u0136\u0137\u0003R)\u0000\u0137\u0138\u0005\n\u0000\u0000\u0138"+
		"\u013a\u0001\u0000\u0000\u0000\u0139\u0134\u0001\u0000\u0000\u0000\u0139"+
		"\u0135\u0001\u0000\u0000\u0000\u013a\u013b\u0001\u0000\u0000\u0000\u013b"+
		"\u013c\u0003\b\u0004\u0000\u013c\'\u0001\u0000\u0000\u0000\u013d\u013e"+
		"\u0005\u0013\u0000\u0000\u013e\u013f\u0003\n\u0005\u0000\u013f\u0143\u0005"+
		"\u0001\u0000\u0000\u0140\u0142\u0003*\u0015\u0000\u0141\u0140\u0001\u0000"+
		"\u0000\u0000\u0142\u0145\u0001\u0000\u0000\u0000\u0143\u0141\u0001\u0000"+
		"\u0000\u0000\u0143\u0144\u0001\u0000\u0000\u0000\u0144\u0146\u0001\u0000"+
		"\u0000\u0000\u0145\u0143\u0001\u0000\u0000\u0000\u0146\u0147\u0003,\u0016"+
		"\u0000\u0147\u0148\u0005\u0002\u0000\u0000\u0148)\u0001\u0000\u0000\u0000"+
		"\u0149\u014a\u0005\u0014\u0000\u0000\u014a\u014b\u0003\n\u0005\u0000\u014b"+
		"\u014c\u0003\b\u0004\u0000\u014c+\u0001\u0000\u0000\u0000\u014d\u014e"+
		"\u0005\u0015\u0000\u0000\u014e\u014f\u0003\b\u0004\u0000\u014f-\u0001"+
		"\u0000\u0000\u0000\u0150\u0154\u00030\u0018\u0000\u0151\u0153\u00032\u0019"+
		"\u0000\u0152\u0151\u0001\u0000\u0000\u0000\u0153\u0156\u0001\u0000\u0000"+
		"\u0000\u0154\u0152\u0001\u0000\u0000\u0000\u0154\u0155\u0001\u0000\u0000"+
		"\u0000\u0155\u0158\u0001\u0000\u0000\u0000\u0156\u0154\u0001\u0000\u0000"+
		"\u0000\u0157\u0159\u00034\u001a\u0000\u0158\u0157\u0001\u0000\u0000\u0000"+
		"\u0158\u0159\u0001\u0000\u0000\u0000\u0159/\u0001\u0000\u0000\u0000\u015a"+
		"\u015b\u0005\u0016\u0000\u0000\u015b\u015c\u0003\n\u0005\u0000\u015c\u015d"+
		"\u0003\b\u0004\u0000\u015d1\u0001\u0000\u0000\u0000\u015e\u015f\u0005"+
		"\u0017\u0000\u0000\u015f\u0160\u0003\n\u0005\u0000\u0160\u0161\u0003\b"+
		"\u0004\u0000\u01613\u0001\u0000\u0000\u0000\u0162\u0163\u0005\u0018\u0000"+
		"\u0000\u0163\u0164\u0003\b\u0004\u0000\u01645\u0001\u0000\u0000\u0000"+
		"\u0165\u0168\u00038\u001c\u0000\u0166\u0168\u0003>\u001f\u0000\u0167\u0165"+
		"\u0001\u0000\u0000\u0000\u0167\u0166\u0001\u0000\u0000\u0000\u01687\u0001"+
		"\u0000\u0000\u0000\u0169\u016c\u0003:\u001d\u0000\u016a\u016c\u0003<\u001e"+
		"\u0000\u016b\u0169\u0001\u0000\u0000\u0000\u016b\u016a\u0001\u0000\u0000"+
		"\u0000\u016c9\u0001\u0000\u0000\u0000\u016d\u016e\u0005\u0019\u0000\u0000"+
		"\u016e\u016f\u0003\n\u0005\u0000\u016f\u0170\u0003\b\u0004\u0000\u0170"+
		";\u0001\u0000\u0000\u0000\u0171\u0172\u0005\u001a\u0000\u0000\u0172\u0173"+
		"\u0003\b\u0004\u0000\u0173\u0174\u0005\u0019\u0000\u0000\u0174\u0175\u0003"+
		"\n\u0005\u0000\u0175=\u0001\u0000\u0000\u0000\u0176\u0179\u0003@ \u0000"+
		"\u0177\u0179\u0003B!\u0000\u0178\u0176\u0001\u0000\u0000\u0000\u0178\u0177"+
		"\u0001\u0000\u0000\u0000\u0179?\u0001\u0000\u0000\u0000\u017a\u017c\u0005"+
		"\u001b\u0000\u0000\u017b\u017d\u0003F#\u0000\u017c\u017b\u0001\u0000\u0000"+
		"\u0000\u017c\u017d\u0001\u0000\u0000\u0000\u017d\u017e\u0001\u0000\u0000"+
		"\u0000\u017e\u0180\u0005\u000b\u0000\u0000\u017f\u0181\u0003\n\u0005\u0000"+
		"\u0180\u017f\u0001\u0000\u0000\u0000\u0180\u0181\u0001\u0000\u0000\u0000"+
		"\u0181\u0182\u0001\u0000\u0000\u0000\u0182\u0184\u0005\u000b\u0000\u0000"+
		"\u0183\u0185\u0003\n\u0005\u0000\u0184\u0183\u0001\u0000\u0000\u0000\u0184"+
		"\u0185\u0001\u0000\u0000\u0000\u0185\u0186\u0001\u0000\u0000\u0000\u0186"+
		"\u0187\u0003\b\u0004\u0000\u0187A\u0001\u0000\u0000\u0000\u0188\u0189"+
		"\u0005\u001b\u0000\u0000\u0189\u018a\u0003F#\u0000\u018a\u018b\u0005\u001c"+
		"\u0000\u0000\u018b\u018c\u0003\n\u0005\u0000\u018c\u018d\u0003\b\u0004"+
		"\u0000\u018dC\u0001\u0000\u0000\u0000\u018e\u0190\u0003d2\u0000\u018f"+
		"\u018e\u0001\u0000\u0000\u0000\u0190\u0193\u0001\u0000\u0000\u0000\u0191"+
		"\u018f\u0001\u0000\u0000\u0000\u0191\u0192\u0001\u0000\u0000\u0000\u0192"+
		"\u0195\u0001\u0000\u0000\u0000\u0193\u0191\u0001\u0000\u0000\u0000\u0194"+
		"\u0196\u0003\u0082A\u0000\u0195\u0194\u0001\u0000\u0000\u0000\u0195\u0196"+
		"\u0001\u0000\u0000\u0000\u0196\u0198\u0001\u0000\u0000\u0000\u0197\u0199"+
		"\u0003\u0084B\u0000\u0198\u0197\u0001\u0000\u0000\u0000\u0198\u0199\u0001"+
		"\u0000\u0000\u0000\u0199\u019a\u0001\u0000\u0000\u0000\u019a\u019f\u0003"+
		"F#\u0000\u019b\u019c\u0005\u000b\u0000\u0000\u019c\u019e\u0003F#\u0000"+
		"\u019d\u019b\u0001\u0000\u0000\u0000\u019e\u01a1\u0001\u0000\u0000\u0000"+
		"\u019f\u019d\u0001\u0000\u0000\u0000\u019f\u01a0\u0001\u0000\u0000\u0000"+
		"\u01a0E\u0001\u0000\u0000\u0000\u01a1\u019f\u0001\u0000\u0000\u0000\u01a2"+
		"\u01a5\u0005Q\u0000\u0000\u01a3\u01a4\u0005\f\u0000\u0000\u01a4\u01a6"+
		"\u0003n7\u0000\u01a5\u01a3\u0001\u0000\u0000\u0000\u01a5\u01a6\u0001\u0000"+
		"\u0000\u0000\u01a6\u01ac\u0001\u0000\u0000\u0000\u01a7\u01aa\u0005\u001d"+
		"\u0000\u0000\u01a8\u01aa\u0005\u001e\u0000\u0000\u01a9\u01a7\u0001\u0000"+
		"\u0000\u0000\u01a9\u01a8\u0001\u0000\u0000\u0000\u01aa\u01ab\u0001\u0000"+
		"\u0000\u0000\u01ab\u01ad\u0003\n\u0005\u0000\u01ac\u01a9\u0001\u0000\u0000"+
		"\u0000\u01ac\u01ad\u0001\u0000\u0000\u0000\u01ad\u01b2\u0001\u0000\u0000"+
		"\u0000\u01ae\u01b1\u0003H$\u0000\u01af\u01b1\u0003J%\u0000\u01b0\u01ae"+
		"\u0001\u0000\u0000\u0000\u01b0\u01af\u0001\u0000\u0000\u0000\u01b1\u01b4"+
		"\u0001\u0000\u0000\u0000\u01b2\u01b0\u0001\u0000\u0000\u0000\u01b2\u01b3"+
		"\u0001\u0000\u0000\u0000\u01b3G\u0001\u0000\u0000\u0000\u01b4\u01b2\u0001"+
		"\u0000\u0000\u0000\u01b5\u01b7\u0003\u0082A\u0000\u01b6\u01b5\u0001\u0000"+
		"\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7\u01b8\u0001\u0000"+
		"\u0000\u0000\u01b8\u01ba\u0005\u001f\u0000\u0000\u01b9\u01bb\u0003\b\u0004"+
		"\u0000\u01ba\u01b9\u0001\u0000\u0000\u0000\u01ba\u01bb\u0001\u0000\u0000"+
		"\u0000\u01bbI\u0001\u0000\u0000\u0000\u01bc\u01be\u0003\u0082A\u0000\u01bd"+
		"\u01bc\u0001\u0000\u0000\u0000\u01bd\u01be\u0001\u0000\u0000\u0000\u01be"+
		"\u01bf\u0001\u0000\u0000\u0000\u01bf\u01c5\u0005 \u0000\u0000\u01c0\u01c1"+
		"\u0005\t\u0000\u0000\u01c1\u01c2\u0003T*\u0000\u01c2\u01c3\u0005\n\u0000"+
		"\u0000\u01c3\u01c4\u0003\b\u0004\u0000\u01c4\u01c6\u0001\u0000\u0000\u0000"+
		"\u01c5\u01c0\u0001\u0000\u0000\u0000\u01c5\u01c6\u0001\u0000\u0000\u0000"+
		"\u01c6K\u0001\u0000\u0000\u0000\u01c7\u01c9\u0003d2\u0000\u01c8\u01c7"+
		"\u0001\u0000\u0000\u0000\u01c9\u01cc\u0001\u0000\u0000\u0000\u01ca\u01c8"+
		"\u0001\u0000\u0000\u0000\u01ca\u01cb\u0001\u0000\u0000\u0000\u01cb\u01ce"+
		"\u0001\u0000\u0000\u0000\u01cc\u01ca\u0001\u0000\u0000\u0000\u01cd\u01cf"+
		"\u0003\u0082A\u0000\u01ce\u01cd\u0001\u0000\u0000\u0000\u01ce\u01cf\u0001"+
		"\u0000\u0000\u0000\u01cf\u01d1\u0001\u0000\u0000\u0000\u01d0\u01d2\u0003"+
		"\u0084B\u0000\u01d1\u01d0\u0001\u0000\u0000\u0000\u01d1\u01d2\u0001\u0000"+
		"\u0000\u0000\u01d2\u01d3\u0001\u0000\u0000\u0000\u01d3\u01d4\u0005!\u0000"+
		"\u0000\u01d4\u01d6\u0005Q\u0000\u0000\u01d5\u01d7\u0003p8\u0000\u01d6"+
		"\u01d5\u0001\u0000\u0000\u0000\u01d6\u01d7\u0001\u0000\u0000\u0000\u01d7"+
		"\u01d8\u0001\u0000\u0000\u0000\u01d8\u01da\u0005\t\u0000\u0000\u01d9\u01db"+
		"\u0003R)\u0000\u01da\u01d9\u0001\u0000\u0000\u0000\u01da\u01db\u0001\u0000"+
		"\u0000\u0000\u01db\u01dc\u0001\u0000\u0000\u0000\u01dc\u01df\u0005\n\u0000"+
		"\u0000\u01dd\u01de\u0005\f\u0000\u0000\u01de\u01e0\u0003n7\u0000\u01df"+
		"\u01dd\u0001\u0000\u0000\u0000\u01df\u01e0\u0001\u0000\u0000\u0000\u01e0"+
		"\u01e2\u0001\u0000\u0000\u0000\u01e1\u01e3\u0003\b\u0004\u0000\u01e2\u01e1"+
		"\u0001\u0000\u0000\u0000\u01e2\u01e3\u0001\u0000\u0000\u0000\u01e3M\u0001"+
		"\u0000\u0000\u0000\u01e4\u01e6\u0003d2\u0000\u01e5\u01e4\u0001\u0000\u0000"+
		"\u0000\u01e6\u01e9\u0001\u0000\u0000\u0000\u01e7\u01e5\u0001\u0000\u0000"+
		"\u0000\u01e7\u01e8\u0001\u0000\u0000\u0000\u01e8\u01eb\u0001\u0000\u0000"+
		"\u0000\u01e9\u01e7\u0001\u0000\u0000\u0000\u01ea\u01ec\u0003\u0082A\u0000"+
		"\u01eb\u01ea\u0001\u0000\u0000\u0000\u01eb\u01ec\u0001\u0000\u0000\u0000"+
		"\u01ec\u01ee\u0001\u0000\u0000\u0000\u01ed\u01ef\u0003\u0084B\u0000\u01ee"+
		"\u01ed\u0001\u0000\u0000\u0000\u01ee\u01ef\u0001\u0000\u0000\u0000\u01ef"+
		"\u01f0\u0001\u0000\u0000\u0000\u01f0\u01f2\u0005\"\u0000\u0000\u01f1\u01f3"+
		"\u0003p8\u0000\u01f2\u01f1\u0001\u0000\u0000\u0000\u01f2\u01f3\u0001\u0000"+
		"\u0000\u0000\u01f3\u01f4\u0001\u0000\u0000\u0000\u01f4\u01f6\u0005\t\u0000"+
		"\u0000\u01f5\u01f7\u0003R)\u0000\u01f6\u01f5\u0001\u0000\u0000\u0000\u01f6"+
		"\u01f7\u0001\u0000\u0000\u0000\u01f7\u01f8\u0001\u0000\u0000\u0000\u01f8"+
		"\u01f9\u0005\n\u0000\u0000\u01f9\u01fa\u0003\b\u0004\u0000\u01faO\u0001"+
		"\u0000\u0000\u0000\u01fb\u01fd\u0003d2\u0000\u01fc\u01fb\u0001\u0000\u0000"+
		"\u0000\u01fd\u0200\u0001\u0000\u0000\u0000\u01fe\u01fc\u0001\u0000\u0000"+
		"\u0000\u01fe\u01ff\u0001\u0000\u0000\u0000\u01ff\u0202\u0001\u0000\u0000"+
		"\u0000\u0200\u01fe\u0001\u0000\u0000\u0000\u0201\u0203\u0003\u0082A\u0000"+
		"\u0202\u0201\u0001\u0000\u0000\u0000\u0202\u0203\u0001\u0000\u0000\u0000"+
		"\u0203\u0205\u0001\u0000\u0000\u0000\u0204\u0206\u0003\u0084B\u0000\u0205"+
		"\u0204\u0001\u0000\u0000\u0000\u0205\u0206\u0001\u0000\u0000\u0000\u0206"+
		"\u0207\u0001\u0000\u0000\u0000\u0207\u0208\u0005#\u0000\u0000\u0208\u020a"+
		"\u0003v;\u0000\u0209\u020b\u0003p8\u0000\u020a\u0209\u0001\u0000\u0000"+
		"\u0000\u020a\u020b\u0001\u0000\u0000\u0000\u020b\u020c\u0001\u0000\u0000"+
		"\u0000\u020c\u020e\u0005\t\u0000\u0000\u020d\u020f\u0003R)\u0000\u020e"+
		"\u020d\u0001\u0000\u0000\u0000\u020e\u020f\u0001\u0000\u0000\u0000\u020f"+
		"\u0210\u0001\u0000\u0000\u0000\u0210\u0213\u0005\n\u0000\u0000\u0211\u0212"+
		"\u0005\f\u0000\u0000\u0212\u0214\u0003n7\u0000\u0213\u0211\u0001\u0000"+
		"\u0000\u0000\u0213\u0214\u0001\u0000\u0000\u0000\u0214\u0215\u0001\u0000"+
		"\u0000\u0000\u0215\u0216\u0003\b\u0004\u0000\u0216Q\u0001\u0000\u0000"+
		"\u0000\u0217\u021c\u0003T*\u0000\u0218\u0219\u0005\u000b\u0000\u0000\u0219"+
		"\u021b\u0003T*\u0000\u021a\u0218\u0001\u0000\u0000\u0000\u021b\u021e\u0001"+
		"\u0000\u0000\u0000\u021c\u021a\u0001\u0000\u0000\u0000\u021c\u021d\u0001"+
		"\u0000\u0000\u0000\u021dS\u0001\u0000\u0000\u0000\u021e\u021c\u0001\u0000"+
		"\u0000\u0000\u021f\u0220\u0005Q\u0000\u0000\u0220\u0221\u0005\f\u0000"+
		"\u0000\u0221\u0224\u0003n7\u0000\u0222\u0223\u0005\u001e\u0000\u0000\u0223"+
		"\u0225\u0003\n\u0005\u0000\u0224\u0222\u0001\u0000\u0000\u0000\u0224\u0225"+
		"\u0001\u0000\u0000\u0000\u0225U\u0001\u0000\u0000\u0000\u0226\u022b\u0003"+
		"\n\u0005\u0000\u0227\u0228\u0005\u000b\u0000\u0000\u0228\u022a\u0003\n"+
		"\u0005\u0000\u0229\u0227\u0001\u0000\u0000\u0000\u022a\u022d\u0001\u0000"+
		"\u0000\u0000\u022b\u0229\u0001\u0000\u0000\u0000\u022b\u022c\u0001\u0000"+
		"\u0000\u0000\u022cW\u0001\u0000\u0000\u0000\u022d\u022b\u0001\u0000\u0000"+
		"\u0000\u022e\u0230\u0003d2\u0000\u022f\u022e\u0001\u0000\u0000\u0000\u0230"+
		"\u0233\u0001\u0000\u0000\u0000\u0231\u022f\u0001\u0000\u0000\u0000\u0231"+
		"\u0232\u0001\u0000\u0000\u0000\u0232\u0235\u0001\u0000\u0000\u0000\u0233"+
		"\u0231\u0001\u0000\u0000\u0000\u0234\u0236\u0003\u0082A\u0000\u0235\u0234"+
		"\u0001\u0000\u0000\u0000\u0235\u0236\u0001\u0000\u0000\u0000\u0236\u0238"+
		"\u0001\u0000\u0000\u0000\u0237\u0239\u0003\u0084B\u0000\u0238\u0237\u0001"+
		"\u0000\u0000\u0000\u0238\u0239\u0001\u0000\u0000\u0000\u0239\u023a\u0001"+
		"\u0000\u0000\u0000\u023a\u023b\u0005$\u0000\u0000\u023b\u023d\u0005Q\u0000"+
		"\u0000\u023c\u023e\u0003p8\u0000\u023d\u023c\u0001\u0000\u0000\u0000\u023d"+
		"\u023e\u0001\u0000\u0000\u0000\u023e\u0240\u0001\u0000\u0000\u0000\u023f"+
		"\u0241\u0003Z-\u0000\u0240\u023f\u0001\u0000\u0000\u0000\u0240\u0241\u0001"+
		"\u0000\u0000\u0000\u0241\u0243\u0001\u0000\u0000\u0000\u0242\u0244\u0003"+
		"\b\u0004\u0000\u0243\u0242\u0001\u0000\u0000\u0000\u0243\u0244\u0001\u0000"+
		"\u0000\u0000\u0244Y\u0001\u0000\u0000\u0000\u0245\u0246\u0005\t\u0000"+
		"\u0000\u0246\u024b\u0003\\.\u0000\u0247\u0248\u0005\u000b\u0000\u0000"+
		"\u0248\u024a\u0003\\.\u0000\u0249\u0247\u0001\u0000\u0000\u0000\u024a"+
		"\u024d\u0001\u0000\u0000\u0000\u024b\u0249\u0001\u0000\u0000\u0000\u024b"+
		"\u024c\u0001\u0000\u0000\u0000\u024c\u024e\u0001\u0000\u0000\u0000\u024d"+
		"\u024b\u0001\u0000\u0000\u0000\u024e\u024f\u0005\n\u0000\u0000\u024f["+
		"\u0001\u0000\u0000\u0000\u0250\u0262\u0003n7\u0000\u0251\u0252\u0005%"+
		"\u0000\u0000\u0252\u0253\u0005\u001e\u0000\u0000\u0253\u0262\u0003n7\u0000"+
		"\u0254\u0255\u0005&\u0000\u0000\u0255\u0256\u0005\u001e\u0000\u0000\u0256"+
		"\u0257\u0005\u0004\u0000\u0000\u0257\u025c\u0003n7\u0000\u0258\u0259\u0005"+
		"\u000b\u0000\u0000\u0259\u025b\u0003n7\u0000\u025a\u0258\u0001\u0000\u0000"+
		"\u0000\u025b\u025e\u0001\u0000\u0000\u0000\u025c\u025a\u0001\u0000\u0000"+
		"\u0000\u025c\u025d\u0001\u0000\u0000\u0000\u025d\u025f\u0001\u0000\u0000"+
		"\u0000\u025e\u025c\u0001\u0000\u0000\u0000\u025f\u0260\u0005\u0005\u0000"+
		"\u0000\u0260\u0262\u0001\u0000\u0000\u0000\u0261\u0250\u0001\u0000\u0000"+
		"\u0000\u0261\u0251\u0001\u0000\u0000\u0000\u0261\u0254\u0001\u0000\u0000"+
		"\u0000\u0262]\u0001\u0000\u0000\u0000\u0263\u0265\u0003d2\u0000\u0264"+
		"\u0263\u0001\u0000\u0000\u0000\u0265\u0268\u0001\u0000\u0000\u0000\u0266"+
		"\u0264\u0001\u0000\u0000\u0000\u0266\u0267\u0001\u0000\u0000\u0000\u0267"+
		"\u026a\u0001\u0000\u0000\u0000\u0268\u0266\u0001\u0000\u0000\u0000\u0269"+
		"\u026b\u0003\u0082A\u0000\u026a\u0269\u0001\u0000\u0000\u0000\u026a\u026b"+
		"\u0001\u0000\u0000\u0000\u026b\u026d\u0001\u0000\u0000\u0000\u026c\u026e"+
		"\u0003\u0084B\u0000\u026d\u026c\u0001\u0000\u0000\u0000\u026d\u026e\u0001"+
		"\u0000\u0000\u0000\u026e\u026f\u0001\u0000\u0000\u0000\u026f\u0270\u0005"+
		"\'\u0000\u0000\u0270\u0272\u0005Q\u0000\u0000\u0271\u0273\u0003Z-\u0000"+
		"\u0272\u0271\u0001\u0000\u0000\u0000\u0272\u0273\u0001\u0000\u0000\u0000"+
		"\u0273\u0275\u0001\u0000\u0000\u0000\u0274\u0276\u0003p8\u0000\u0275\u0274"+
		"\u0001\u0000\u0000\u0000\u0275\u0276\u0001\u0000\u0000\u0000\u0276\u0278"+
		"\u0001\u0000\u0000\u0000\u0277\u0279\u0003\b\u0004\u0000\u0278\u0277\u0001"+
		"\u0000\u0000\u0000\u0278\u0279\u0001\u0000\u0000\u0000\u0279_\u0001\u0000"+
		"\u0000\u0000\u027a\u027c\u0003d2\u0000\u027b\u027a\u0001\u0000\u0000\u0000"+
		"\u027c\u027f\u0001\u0000\u0000\u0000\u027d\u027b\u0001\u0000\u0000\u0000"+
		"\u027d\u027e\u0001\u0000\u0000\u0000\u027e\u0281\u0001\u0000\u0000\u0000"+
		"\u027f\u027d\u0001\u0000\u0000\u0000\u0280\u0282\u0003\u0082A\u0000\u0281"+
		"\u0280\u0001\u0000\u0000\u0000\u0281\u0282\u0001\u0000\u0000\u0000\u0282"+
		"\u0284\u0001\u0000\u0000\u0000\u0283\u0285\u0003\u0084B\u0000\u0284\u0283"+
		"\u0001\u0000\u0000\u0000\u0284\u0285\u0001\u0000\u0000\u0000\u0285\u0286"+
		"\u0001\u0000\u0000\u0000\u0286\u0288\u0005!\u0000\u0000\u0287\u0289\u0003"+
		"p8\u0000\u0288\u0287\u0001\u0000\u0000\u0000\u0288\u0289\u0001\u0000\u0000"+
		"\u0000\u0289\u028a\u0001\u0000\u0000\u0000\u028a\u028b\u0005Q\u0000\u0000"+
		"\u028b\u028d\u0005\t\u0000\u0000\u028c\u028e\u0003R)\u0000\u028d\u028c"+
		"\u0001\u0000\u0000\u0000\u028d\u028e\u0001\u0000\u0000\u0000\u028e\u028f"+
		"\u0001\u0000\u0000\u0000\u028f\u0290\u0005\n\u0000\u0000\u0290\u0291\u0005"+
		"\f\u0000\u0000\u0291\u0292\u0003n7\u0000\u0292a\u0001\u0000\u0000\u0000"+
		"\u0293\u0295\u0003d2\u0000\u0294\u0293\u0001\u0000\u0000\u0000\u0295\u0298"+
		"\u0001\u0000\u0000\u0000\u0296\u0294\u0001\u0000\u0000\u0000\u0296\u0297"+
		"\u0001\u0000\u0000\u0000\u0297\u029a\u0001\u0000\u0000\u0000\u0298\u0296"+
		"\u0001\u0000\u0000\u0000\u0299\u029b\u0003\u0082A\u0000\u029a\u0299\u0001"+
		"\u0000\u0000\u0000\u029a\u029b\u0001\u0000\u0000\u0000\u029b\u029d\u0001"+
		"\u0000\u0000\u0000\u029c\u029e\u0003\u0084B\u0000\u029d\u029c\u0001\u0000"+
		"\u0000\u0000\u029d\u029e\u0001\u0000\u0000\u0000\u029e\u029f\u0001\u0000"+
		"\u0000\u0000\u029f\u02a0\u0005(\u0000\u0000\u02a0\u02a6\u0005Q\u0000\u0000"+
		"\u02a1\u02a3\u0005\t\u0000\u0000\u02a2\u02a4\u0003R)\u0000\u02a3\u02a2"+
		"\u0001\u0000\u0000\u0000\u02a3\u02a4\u0001\u0000\u0000\u0000\u02a4\u02a5"+
		"\u0001\u0000\u0000\u0000\u02a5\u02a7\u0005\n\u0000\u0000\u02a6\u02a1\u0001"+
		"\u0000\u0000\u0000\u02a6\u02a7\u0001\u0000\u0000\u0000\u02a7\u02a9\u0001"+
		"\u0000\u0000\u0000\u02a8\u02aa\u0003\b\u0004\u0000\u02a9\u02a8\u0001\u0000"+
		"\u0000\u0000\u02a9\u02aa\u0001\u0000\u0000\u0000\u02aac\u0001\u0000\u0000"+
		"\u0000\u02ab\u02ac\u0005)\u0000\u0000\u02ac\u02b4\u0005Q\u0000\u0000\u02ad"+
		"\u02b0\u0005\t\u0000\u0000\u02ae\u02b1\u0003f3\u0000\u02af\u02b1\u0003"+
		"t:\u0000\u02b0\u02ae\u0001\u0000\u0000\u0000\u02b0\u02af\u0001\u0000\u0000"+
		"\u0000\u02b1\u02b2\u0001\u0000\u0000\u0000\u02b2\u02b3\u0005\n\u0000\u0000"+
		"\u02b3\u02b5\u0001\u0000\u0000\u0000\u02b4\u02ad\u0001\u0000\u0000\u0000"+
		"\u02b4\u02b5\u0001\u0000\u0000\u0000\u02b5e\u0001\u0000\u0000\u0000\u02b6"+
		"\u02bb\u0003h4\u0000\u02b7\u02b8\u0005\u000b\u0000\u0000\u02b8\u02ba\u0003"+
		"h4\u0000\u02b9\u02b7\u0001\u0000\u0000\u0000\u02ba\u02bd\u0001\u0000\u0000"+
		"\u0000\u02bb\u02b9\u0001\u0000\u0000\u0000\u02bb\u02bc\u0001\u0000\u0000"+
		"\u0000\u02bcg\u0001\u0000\u0000\u0000\u02bd\u02bb\u0001\u0000\u0000\u0000"+
		"\u02be\u02bf\u0005Q\u0000\u0000\u02bf\u02c0\u0005\u001e\u0000\u0000\u02c0"+
		"\u02c1\u0005S\u0000\u0000\u02c1i\u0001\u0000\u0000\u0000\u02c2\u02c4\u0007"+
		"\u0000\u0000\u0000\u02c3\u02c5\u0003p8\u0000\u02c4\u02c3\u0001\u0000\u0000"+
		"\u0000\u02c4\u02c5\u0001\u0000\u0000\u0000\u02c5\u02c6\u0001\u0000\u0000"+
		"\u0000\u02c6\u02c8\u0005\t\u0000\u0000\u02c7\u02c9\u0003V+\u0000\u02c8"+
		"\u02c7\u0001\u0000\u0000\u0000\u02c8\u02c9\u0001\u0000\u0000\u0000\u02c9"+
		"\u02ca\u0001\u0000\u0000\u0000\u02ca\u02cb\u0005\n\u0000\u0000\u02cbk"+
		"\u0001\u0000\u0000\u0000\u02cc\u02cd\u0005\b\u0000\u0000\u02cd\u02ce\u0005"+
		"\u0006\u0000\u0000\u02ce\u02cf\u0005O\u0000\u0000\u02cf\u02d1\u0005\t"+
		"\u0000\u0000\u02d0\u02d2\u0003V+\u0000\u02d1\u02d0\u0001\u0000\u0000\u0000"+
		"\u02d1\u02d2\u0001\u0000\u0000\u0000\u02d2\u02d3\u0001\u0000\u0000\u0000"+
		"\u02d3\u02d4\u0005\n\u0000\u0000\u02d4m\u0001\u0000\u0000\u0000\u02d5"+
		"\u02d6\u00067\uffff\uffff\u0000\u02d6\u02db\u0005Q\u0000\u0000\u02d7\u02d8"+
		"\u0005\u0006\u0000\u0000\u02d8\u02da\u0005Q\u0000\u0000\u02d9\u02d7\u0001"+
		"\u0000\u0000\u0000\u02da\u02dd\u0001\u0000\u0000\u0000\u02db\u02d9\u0001"+
		"\u0000\u0000\u0000\u02db\u02dc\u0001\u0000\u0000\u0000\u02dc\u02df\u0001"+
		"\u0000\u0000\u0000\u02dd\u02db\u0001\u0000\u0000\u0000\u02de\u02e0\u0003"+
		"p8\u0000\u02df\u02de\u0001\u0000\u0000\u0000\u02df\u02e0\u0001\u0000\u0000"+
		"\u0000\u02e0\u02f7\u0001\u0000\u0000\u0000\u02e1\u02e4\n\u0003\u0000\u0000"+
		"\u02e2\u02e3\u0005*\u0000\u0000\u02e3\u02e5\u0003n7\u0000\u02e4\u02e2"+
		"\u0001\u0000\u0000\u0000\u02e5\u02e6\u0001\u0000\u0000\u0000\u02e6\u02e4"+
		"\u0001\u0000\u0000\u0000\u02e6\u02e7\u0001\u0000\u0000\u0000\u02e7\u02f6"+
		"\u0001\u0000\u0000\u0000\u02e8\u02eb\n\u0002\u0000\u0000\u02e9\u02ea\u0005"+
		"+\u0000\u0000\u02ea\u02ec\u0003n7\u0000\u02eb\u02e9\u0001\u0000\u0000"+
		"\u0000\u02ec\u02ed\u0001\u0000\u0000\u0000\u02ed\u02eb\u0001\u0000\u0000"+
		"\u0000\u02ed\u02ee\u0001\u0000\u0000\u0000\u02ee\u02f6\u0001\u0000\u0000"+
		"\u0000\u02ef\u02f1\n\u0001\u0000\u0000\u02f0\u02f2\u0005P\u0000\u0000"+
		"\u02f1\u02f0\u0001\u0000\u0000\u0000\u02f2\u02f3\u0001\u0000\u0000\u0000"+
		"\u02f3\u02f1\u0001\u0000\u0000\u0000\u02f3\u02f4\u0001\u0000\u0000\u0000"+
		"\u02f4\u02f6\u0001\u0000\u0000\u0000\u02f5\u02e1\u0001\u0000\u0000\u0000"+
		"\u02f5\u02e8\u0001\u0000\u0000\u0000\u02f5\u02ef\u0001\u0000\u0000\u0000"+
		"\u02f6\u02f9\u0001\u0000\u0000\u0000\u02f7\u02f5\u0001\u0000\u0000\u0000"+
		"\u02f7\u02f8\u0001\u0000\u0000\u0000\u02f8o\u0001\u0000\u0000\u0000\u02f9"+
		"\u02f7\u0001\u0000\u0000\u0000\u02fa\u02fb\u0005\u0004\u0000\u0000\u02fb"+
		"\u0300\u0003r9\u0000\u02fc\u02fd\u0005\u000b\u0000\u0000\u02fd\u02ff\u0003"+
		"r9\u0000\u02fe\u02fc\u0001\u0000\u0000\u0000\u02ff\u0302\u0001\u0000\u0000"+
		"\u0000\u0300\u02fe\u0001\u0000\u0000\u0000\u0300\u0301\u0001\u0000\u0000"+
		"\u0000\u0301\u0303\u0001\u0000\u0000\u0000\u0302\u0300\u0001\u0000\u0000"+
		"\u0000\u0303\u0304\u0005\u0005\u0000\u0000\u0304q\u0001\u0000\u0000\u0000"+
		"\u0305\u0310\u0003n7\u0000\u0306\u0309\u0005Q\u0000\u0000\u0307\u0309"+
		"\u0005,\u0000\u0000\u0308\u0306\u0001\u0000\u0000\u0000\u0308\u0307\u0001"+
		"\u0000\u0000\u0000\u0309\u030c\u0001\u0000\u0000\u0000\u030a\u030d\u0007"+
		"\u0001\u0000\u0000\u030b\u030d\u0005\b\u0000\u0000\u030c\u030a\u0001\u0000"+
		"\u0000\u0000\u030c\u030b\u0001\u0000\u0000\u0000\u030d\u030e\u0001\u0000"+
		"\u0000\u0000\u030e\u0310\u0003n7\u0000\u030f\u0305\u0001\u0000\u0000\u0000"+
		"\u030f\u0308\u0001\u0000\u0000\u0000\u0310s\u0001\u0000\u0000\u0000\u0311"+
		"\u0317\u0005R\u0000\u0000\u0312\u0317\u0005S\u0000\u0000\u0313\u0317\u0005"+
		"-\u0000\u0000\u0314\u0317\u0005.\u0000\u0000\u0315\u0317\u0005/\u0000"+
		"\u0000\u0316\u0311\u0001\u0000\u0000\u0000\u0316\u0312\u0001\u0000\u0000"+
		"\u0000\u0316\u0313\u0001\u0000\u0000\u0000\u0316\u0314\u0001\u0000\u0000"+
		"\u0000\u0316\u0315\u0001\u0000\u0000\u0000\u0317u\u0001\u0000\u0000\u0000"+
		"\u0318\u031c\u0003x<\u0000\u0319\u031c\u0003z=\u0000\u031a\u031c\u0005"+
		"P\u0000\u0000\u031b\u0318\u0001\u0000\u0000\u0000\u031b\u0319\u0001\u0000"+
		"\u0000\u0000\u031b\u031a\u0001\u0000\u0000\u0000\u031cw\u0001\u0000\u0000"+
		"\u0000\u031d\u0336\u00050\u0000\u0000\u031e\u0336\u00051\u0000\u0000\u031f"+
		"\u0336\u00052\u0000\u0000\u0320\u0336\u00053\u0000\u0000\u0321\u0336\u0005"+
		"4\u0000\u0000\u0322\u0336\u00055\u0000\u0000\u0323\u0336\u00056\u0000"+
		"\u0000\u0324\u0336\u00057\u0000\u0000\u0325\u0336\u0005+\u0000\u0000\u0326"+
		"\u0336\u00058\u0000\u0000\u0327\u0336\u00059\u0000\u0000\u0328\u0336\u0005"+
		"*\u0000\u0000\u0329\u0336\u0005:\u0000\u0000\u032a\u0336\u0005;\u0000"+
		"\u0000\u032b\u0336\u0005<\u0000\u0000\u032c\u0336\u0005=\u0000\u0000\u032d"+
		"\u0336\u0005>\u0000\u0000\u032e\u0336\u0005?\u0000\u0000\u032f\u0336\u0005"+
		"@\u0000\u0000\u0330\u0336\u0005\u001c\u0000\u0000\u0331\u0336\u0005A\u0000"+
		"\u0000\u0332\u0336\u0005B\u0000\u0000\u0333\u0336\u0005C\u0000\u0000\u0334"+
		"\u0336\u0005D\u0000\u0000\u0335\u031d\u0001\u0000\u0000\u0000\u0335\u031e"+
		"\u0001\u0000\u0000\u0000\u0335\u031f\u0001\u0000\u0000\u0000\u0335\u0320"+
		"\u0001\u0000\u0000\u0000\u0335\u0321\u0001\u0000\u0000\u0000\u0335\u0322"+
		"\u0001\u0000\u0000\u0000\u0335\u0323\u0001\u0000\u0000\u0000\u0335\u0324"+
		"\u0001\u0000\u0000\u0000\u0335\u0325\u0001\u0000\u0000\u0000\u0335\u0326"+
		"\u0001\u0000\u0000\u0000\u0335\u0327\u0001\u0000\u0000\u0000\u0335\u0328"+
		"\u0001\u0000\u0000\u0000\u0335\u0329\u0001\u0000\u0000\u0000\u0335\u032a"+
		"\u0001\u0000\u0000\u0000\u0335\u032b\u0001\u0000\u0000\u0000\u0335\u032c"+
		"\u0001\u0000\u0000\u0000\u0335\u032d\u0001\u0000\u0000\u0000\u0335\u032e"+
		"\u0001\u0000\u0000\u0000\u0335\u032f\u0001\u0000\u0000\u0000\u0335\u0330"+
		"\u0001\u0000\u0000\u0000\u0335\u0331\u0001\u0000\u0000\u0000\u0335\u0332"+
		"\u0001\u0000\u0000\u0000\u0335\u0333\u0001\u0000\u0000\u0000\u0335\u0334"+
		"\u0001\u0000\u0000\u0000\u0336y\u0001\u0000\u0000\u0000\u0337\u033a\u0003"+
		"|>\u0000\u0338\u033a\u0003~?\u0000\u0339\u0337\u0001\u0000\u0000\u0000"+
		"\u0339\u0338\u0001\u0000\u0000\u0000\u033a{\u0001\u0000\u0000\u0000\u033b"+
		"\u033f\u0003~?\u0000\u033c\u033f\u0005E\u0000\u0000\u033d\u033f\u0005"+
		"F\u0000\u0000\u033e\u033b\u0001\u0000\u0000\u0000\u033e\u033c\u0001\u0000"+
		"\u0000\u0000\u033e\u033d\u0001\u0000\u0000\u0000\u033f}\u0001\u0000\u0000"+
		"\u0000\u0340\u0343\u0005G\u0000\u0000\u0341\u0343\u0005H\u0000\u0000\u0342"+
		"\u0340\u0001\u0000\u0000\u0000\u0342\u0341\u0001\u0000\u0000\u0000\u0343"+
		"\u007f\u0001\u0000\u0000\u0000\u0344\u0346\u0003x<\u0000\u0345\u0344\u0001"+
		"\u0000\u0000\u0000\u0345\u0346\u0001\u0000\u0000\u0000\u0346\u0349\u0001"+
		"\u0000\u0000\u0000\u0347\u034a\u0005\u001e\u0000\u0000\u0348\u034a\u0005"+
		"\u001d\u0000\u0000\u0349\u0347\u0001\u0000\u0000\u0000\u0349\u0348\u0001"+
		"\u0000\u0000\u0000\u034a\u034b\u0001\u0000\u0000\u0000\u034b\u034c\u0003"+
		"\n\u0005\u0000\u034c\u0081\u0001\u0000\u0000\u0000\u034d\u0351\u0005I"+
		"\u0000\u0000\u034e\u0351\u0005J\u0000\u0000\u034f\u0351\u0005K\u0000\u0000"+
		"\u0350\u034d\u0001\u0000\u0000\u0000\u0350\u034e\u0001\u0000\u0000\u0000"+
		"\u0350\u034f\u0001\u0000\u0000\u0000\u0351\u0083\u0001\u0000\u0000\u0000"+
		"\u0352\u0355\u0005L\u0000\u0000\u0353\u0355\u0005M\u0000\u0000\u0354\u0352"+
		"\u0001\u0000\u0000\u0000\u0354\u0353\u0001\u0000\u0000\u0000\u0354\u0355"+
		"\u0001\u0000\u0000\u0000\u0355\u0357\u0001\u0000\u0000\u0000\u0356\u0358"+
		"\u0005N\u0000\u0000\u0357\u0356\u0001\u0000\u0000\u0000\u0357\u0358\u0001"+
		"\u0000\u0000\u0000\u0358\u0085\u0001\u0000\u0000\u0000y\u0089\u0096\u00a0"+
		"\u00a5\u00ab\u00b1\u00bb\u00ca\u00d3\u00d5\u00dd\u00e2\u00e9\u00f0\u00fd"+
		"\u0104\u010f\u0119\u0121\u0126\u012a\u012e\u0132\u0139\u0143\u0154\u0158"+
		"\u0167\u016b\u0178\u017c\u0180\u0184\u0191\u0195\u0198\u019f\u01a5\u01a9"+
		"\u01ac\u01b0\u01b2\u01b6\u01ba\u01bd\u01c5\u01ca\u01ce\u01d1\u01d6\u01da"+
		"\u01df\u01e2\u01e7\u01eb\u01ee\u01f2\u01f6\u01fe\u0202\u0205\u020a\u020e"+
		"\u0213\u021c\u0224\u022b\u0231\u0235\u0238\u023d\u0240\u0243\u024b\u025c"+
		"\u0261\u0266\u026a\u026d\u0272\u0275\u0278\u027d\u0281\u0284\u0288\u028d"+
		"\u0296\u029a\u029d\u02a3\u02a6\u02a9\u02b0\u02b4\u02bb\u02c4\u02c8\u02d1"+
		"\u02db\u02df\u02e6\u02ed\u02f3\u02f5\u02f7\u0300\u0308\u030c\u030f\u0316"+
		"\u031b\u0335\u0339\u033e\u0342\u0345\u0349\u0350\u0354\u0357";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}