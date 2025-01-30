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
		T__73=74, T__74=75, T__75=76, T__76=77, T__77=78, IDENTIFIER=79, NUMBER=80, 
		STRING=81, WS=82, COMMENT=83, ArrayAccessOp=84;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_declaration = 2, RULE_controlStatement = 3, 
		RULE_block = 4, RULE_expression = 5, RULE_primary = 6, RULE_keyValueList = 7, 
		RULE_package = 8, RULE_importStatement = 9, RULE_importAs = 10, RULE_importSimple = 11, 
		RULE_importFrom = 12, RULE_importMultiple = 13, RULE_anySimpleImport = 14, 
		RULE_break = 15, RULE_continue = 16, RULE_return = 17, RULE_lambda = 18, 
		RULE_switchStatement = 19, RULE_case = 20, RULE_default = 21, RULE_ifStatement = 22, 
		RULE_if = 23, RULE_elif = 24, RULE_else = 25, RULE_loop = 26, RULE_whileLoopStatement = 27, 
		RULE_whileLoop = 28, RULE_doWhileLoop = 29, RULE_forLoopStatement = 30, 
		RULE_forILoop = 31, RULE_forEachLoop = 32, RULE_keyValue = 33, RULE_variableDeclarationStatement = 34, 
		RULE_variableDeclaration = 35, RULE_getterDeclaration = 36, RULE_setterDeclaration = 37, 
		RULE_functionDeclaration = 38, RULE_constructorDeclaration = 39, RULE_operatorDeclaration = 40, 
		RULE_parameterList = 41, RULE_parameter = 42, RULE_argumentList = 43, 
		RULE_classDeclaration = 44, RULE_inheritance = 45, RULE_inheritanceSpec = 46, 
		RULE_interfaceDeclaration = 47, RULE_functionSignature = 48, RULE_annotationDeclaration = 49, 
		RULE_annotation = 50, RULE_annotationArgs = 51, RULE_annotationArg = 52, 
		RULE_functionCall = 53, RULE_type = 54, RULE_genericDeclaration = 55, 
		RULE_generic = 56, RULE_literal = 57, RULE_operator = 58, RULE_binaryOperator = 59, 
		RULE_unaryOperator = 60, RULE_assignment = 61, RULE_access = 62, RULE_modifier = 63;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "declaration", "controlStatement", "block", "expression", 
			"primary", "keyValueList", "package", "importStatement", "importAs", 
			"importSimple", "importFrom", "importMultiple", "anySimpleImport", "break", 
			"continue", "return", "lambda", "switchStatement", "case", "default", 
			"ifStatement", "if", "elif", "else", "loop", "whileLoopStatement", "whileLoop", 
			"doWhileLoop", "forLoopStatement", "forILoop", "forEachLoop", "keyValue", 
			"variableDeclarationStatement", "variableDeclaration", "getterDeclaration", 
			"setterDeclaration", "functionDeclaration", "constructorDeclaration", 
			"operatorDeclaration", "parameterList", "parameter", "argumentList", 
			"classDeclaration", "inheritance", "inheritanceSpec", "interfaceDeclaration", 
			"functionSignature", "annotationDeclaration", "annotation", "annotationArgs", 
			"annotationArg", "functionCall", "type", "genericDeclaration", "generic", 
			"literal", "operator", "binaryOperator", "unaryOperator", "assignment", 
			"access", "modifier"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "':'", "'=>'", "'['", "']'", "'.'", "'('", "')'", 
			"','", "'package'", "'import'", "'as'", "'from'", "'break'", "'continue'", 
			"'return'", "'switch'", "'case'", "'default'", "'if'", "'elif'", "'else'", 
			"'while'", "'do'", "'for'", "'in'", "':='", "'='", "'get'", "'set'", 
			"'func'", "'init'", "'operator'", "'class'", "'extends'", "'implements'", 
			"'interface'", "'annotation'", "'@'", "'|'", "'&'", "'?'", "'super'", 
			"'null'", "'true'", "'false'", "'*'", "'/'", "'//'", "'%'", "'+'", "'-'", 
			"'>>'", "'<<'", "'^'", "'xor'", "'>'", "'<'", "'>='", "'<='", "'=='", 
			"'!='", "'is'", "'&&'", "'and'", "'||'", "'or'", "'++'", "'--'", "'!'", 
			"'not'", "'public'", "'private'", "'protected'", "'static'", "'abstract'", 
			"'final'"
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
			null, null, null, null, null, null, null, "IDENTIFIER", "NUMBER", "STRING", 
			"WS", "COMMENT", "ArrayAccessOp"
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
			setState(131);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 248279294515488L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 8191L) != 0)) {
				{
				{
				setState(128);
				statement();
				}
				}
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(134);
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
		public ImportStatementContext importStatement() {
			return getRuleContext(ImportStatementContext.class,0);
		}
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public ControlStatementContext controlStatement() {
			return getRuleContext(ControlStatementContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
			setState(144);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				package_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(137);
				importStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(138);
				declaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(139);
				controlStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(140);
				expression(0);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(141);
				break_();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(142);
				continue_();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(143);
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
			setState(154);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				functionDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				variableDeclarationStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(148);
				operatorDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(149);
				constructorDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(150);
				functionSignature();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(151);
				classDeclaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(152);
				interfaceDeclaration();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(153);
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
			setState(159);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__20:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				ifStatement();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(157);
				switchStatement();
				}
				break;
			case T__23:
			case T__24:
			case T__25:
				enterOuterAlt(_localctx, 3);
				{
				setState(158);
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
			setState(173);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(161);
				match(T__0);
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 248279294515488L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 8191L) != 0)) {
					{
					{
					setState(162);
					statement();
					}
					}
					setState(167);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(168);
				match(T__1);
				}
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(169);
				match(T__2);
				setState(170);
				statement();
				}
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 3);
				{
				setState(171);
				match(T__3);
				setState(172);
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
		public UnaryOperatorContext before;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryOperatorContext unaryOperator() {
			return getRuleContext(UnaryOperatorContext.class,0);
		}
		public TerminalNode ArrayAccessOp() { return getToken(LumParser.ArrayAccessOp, 0); }
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
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public FunctionCallContext functionCall() {
			return getRuleContext(FunctionCallContext.class,0);
		}
		public MemberAccessContext(ExpressionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostUnaryContext extends ExpressionContext {
		public UnaryOperatorContext after;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public UnaryOperatorContext unaryOperator() {
			return getRuleContext(UnaryOperatorContext.class,0);
		}
		public TerminalNode ArrayAccessOp() { return getToken(LumParser.ArrayAccessOp, 0); }
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
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				_localctx = new PrimaryExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(176);
				primary();
				}
				break;
			case 2:
				{
				_localctx = new LambdaExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(177);
				lambda();
				}
				break;
			case 3:
				{
				_localctx = new FunctionCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(178);
				functionCall();
				}
				break;
			case 4:
				{
				_localctx = new PreUnaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(179);
				((PreUnaryContext)_localctx).before = unaryOperator();
				setState(180);
				_la = _input.LA(1);
				if ( _la <= 0 || (_la==ArrayAccessOp) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(181);
				expression(2);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(206);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(204);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(185);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(186);
						binaryOperator();
						setState(187);
						expression(2);
						}
						break;
					case 2:
						{
						_localctx = new ArrayAccessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(189);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(190);
						match(T__4);
						setState(191);
						argumentList();
						setState(192);
						match(T__5);
						}
						break;
					case 3:
						{
						_localctx = new MemberAccessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(194);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(195);
						match(T__6);
						setState(198);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
						case 1:
							{
							setState(196);
							match(IDENTIFIER);
							}
							break;
						case 2:
							{
							setState(197);
							functionCall();
							}
							break;
						}
						}
						break;
					case 4:
						{
						_localctx = new PostUnaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(200);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(201);
						((PostUnaryContext)_localctx).after = unaryOperator();
						setState(202);
						_la = _input.LA(1);
						if ( _la <= 0 || (_la==ArrayAccessOp) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(208);
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
	public static class AssignmentExprContext extends PrimaryContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public AssignmentExprContext(PrimaryContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExprContext extends PrimaryContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenExprContext(PrimaryContext ctx) { copyFrom(ctx); }
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
			setState(227);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new LiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(209);
				literal();
				}
				break;
			case 2:
				_localctx = new IdentifierExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(210);
				match(IDENTIFIER);
				}
				break;
			case 3:
				_localctx = new AssignmentExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(211);
				match(IDENTIFIER);
				setState(212);
				assignment();
				}
				break;
			case 4:
				_localctx = new ListLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(213);
				match(T__4);
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246290604622112L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 7183L) != 0)) {
					{
					setState(214);
					argumentList();
					}
				}

				setState(217);
				match(T__5);
				}
				break;
			case 5:
				_localctx = new DictLiteralContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(218);
				match(T__4);
				setState(220);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==STRING) {
					{
					setState(219);
					keyValueList();
					}
				}

				setState(222);
				match(T__5);
				}
				break;
			case 6:
				_localctx = new ParenExprContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(223);
				match(T__7);
				setState(224);
				expression(0);
				setState(225);
				match(T__8);
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
			setState(229);
			keyValue();
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(230);
				match(T__9);
				setState(231);
				keyValue();
				}
				}
				setState(236);
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
		enterRule(_localctx, 16, RULE_package);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(237);
			match(T__10);
			setState(238);
			match(IDENTIFIER);
			setState(243);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(239);
					match(T__6);
					setState(240);
					match(IDENTIFIER);
					}
					} 
				}
				setState(245);
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
		enterRule(_localctx, 18, RULE_importStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(T__11);
			setState(250);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(247);
				anySimpleImport();
				}
				break;
			case 2:
				{
				setState(248);
				importFrom();
				}
				break;
			case 3:
				{
				setState(249);
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
		enterRule(_localctx, 20, RULE_importAs);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			importSimple();
			setState(253);
			match(T__12);
			setState(254);
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
		enterRule(_localctx, 22, RULE_importSimple);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(IDENTIFIER);
			setState(261);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(257);
					match(T__6);
					setState(258);
					match(IDENTIFIER);
					}
					} 
				}
				setState(263);
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
		enterRule(_localctx, 24, RULE_importFrom);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			importMultiple();
			setState(265);
			match(T__13);
			setState(266);
			match(IDENTIFIER);
			setState(271);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(267);
					match(T__6);
					setState(268);
					match(IDENTIFIER);
					}
					} 
				}
				setState(273);
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
		enterRule(_localctx, 26, RULE_importMultiple);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			anySimpleImport();
			setState(279);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(275);
					match(T__9);
					setState(276);
					anySimpleImport();
					}
					} 
				}
				setState(281);
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
		enterRule(_localctx, 28, RULE_anySimpleImport);
		try {
			setState(284);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				importSimple();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
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
		enterRule(_localctx, 30, RULE_break);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(T__14);
			setState(288);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(287);
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
		enterRule(_localctx, 32, RULE_continue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			match(T__15);
			setState(292);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(291);
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
		enterRule(_localctx, 34, RULE_return);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			match(T__16);
			setState(296);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(295);
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
		public List<TerminalNode> IDENTIFIER() { return getTokens(LumParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(LumParser.IDENTIFIER, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public LambdaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda; }
	}

	public final LambdaContext lambda() throws RecognitionException {
		LambdaContext _localctx = new LambdaContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_lambda);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(316);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				{
				setState(298);
				match(IDENTIFIER);
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(299);
					match(T__9);
					setState(300);
					match(IDENTIFIER);
					}
					}
					setState(305);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case T__7:
				{
				setState(306);
				match(T__7);
				setState(307);
				match(IDENTIFIER);
				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(308);
					match(T__9);
					setState(309);
					match(IDENTIFIER);
					}
					}
					setState(314);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(315);
				match(T__8);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(318);
			match(T__3);
			setState(328);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				setState(319);
				match(T__0);
				setState(323);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 248279294515488L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 8191L) != 0)) {
					{
					{
					setState(320);
					statement();
					}
					}
					setState(325);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(326);
				match(T__1);
				}
				break;
			case T__4:
			case T__7:
			case T__10:
			case T__11:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case T__20:
			case T__23:
			case T__24:
			case T__25:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__37:
			case T__38:
			case T__39:
			case T__44:
			case T__45:
			case T__46:
			case T__68:
			case T__69:
			case T__70:
			case T__71:
			case T__72:
			case T__73:
			case T__74:
			case T__75:
			case T__76:
			case T__77:
			case IDENTIFIER:
			case NUMBER:
			case STRING:
				{
				setState(327);
				statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		enterRule(_localctx, 38, RULE_switchStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			match(T__17);
			setState(331);
			expression(0);
			setState(332);
			match(T__0);
			setState(336);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__18) {
				{
				{
				setState(333);
				case_();
				}
				}
				setState(338);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(339);
			default_();
			setState(340);
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
		enterRule(_localctx, 40, RULE_case);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(342);
			match(T__18);
			setState(343);
			expression(0);
			setState(344);
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
		enterRule(_localctx, 42, RULE_default);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			match(T__19);
			setState(347);
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
		enterRule(_localctx, 44, RULE_ifStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			if_();
			setState(353);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(350);
					elif();
					}
					} 
				}
				setState(355);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			setState(357);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(356);
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
		enterRule(_localctx, 46, RULE_if);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			match(T__20);
			setState(360);
			expression(0);
			setState(361);
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
		enterRule(_localctx, 48, RULE_elif);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(363);
			match(T__21);
			setState(364);
			expression(0);
			setState(365);
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
		enterRule(_localctx, 50, RULE_else);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(367);
			match(T__22);
			setState(368);
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
		enterRule(_localctx, 52, RULE_loop);
		try {
			setState(372);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__23:
			case T__24:
				enterOuterAlt(_localctx, 1);
				{
				setState(370);
				whileLoopStatement();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 2);
				{
				setState(371);
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
		enterRule(_localctx, 54, RULE_whileLoopStatement);
		try {
			setState(376);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__23:
				enterOuterAlt(_localctx, 1);
				{
				setState(374);
				whileLoop();
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 2);
				{
				setState(375);
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
		enterRule(_localctx, 56, RULE_whileLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			match(T__23);
			setState(379);
			expression(0);
			setState(380);
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
		enterRule(_localctx, 58, RULE_doWhileLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			match(T__24);
			setState(383);
			block();
			setState(384);
			match(T__23);
			setState(385);
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
		enterRule(_localctx, 60, RULE_forLoopStatement);
		try {
			setState(389);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(387);
				forILoop();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(388);
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
		public VariableDeclarationStatementContext variableDeclarationStatement() {
			return getRuleContext(VariableDeclarationStatementContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForILoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forILoop; }
	}

	public final ForILoopContext forILoop() throws RecognitionException {
		ForILoopContext _localctx = new ForILoopContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_forILoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			match(T__25);
			setState(392);
			variableDeclarationStatement();
			setState(393);
			match(T__9);
			setState(394);
			expression(0);
			setState(395);
			match(T__9);
			setState(396);
			expression(0);
			setState(397);
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
		enterRule(_localctx, 64, RULE_forEachLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			match(T__25);
			setState(400);
			variableDeclaration();
			setState(401);
			match(T__26);
			setState(402);
			expression(0);
			setState(403);
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
	public static class KeyValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode STRING() { return getToken(LumParser.STRING, 0); }
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public KeyValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keyValue; }
	}

	public final KeyValueContext keyValue() throws RecognitionException {
		KeyValueContext _localctx = new KeyValueContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_keyValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
			_la = _input.LA(1);
			if ( !(_la==IDENTIFIER || _la==STRING) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(406);
			match(T__2);
			setState(407);
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
			setState(412);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__39) {
				{
				{
				setState(409);
				annotation();
				}
				}
				setState(414);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(416);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(415);
				access();
				}
			}

			setState(419);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(418);
				modifier();
				}
				break;
			}
			{
			setState(421);
			variableDeclaration();
			}
			setState(426);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(422);
					match(T__9);
					setState(423);
					variableDeclaration();
					}
					} 
				}
				setState(428);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
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
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			match(IDENTIFIER);
			setState(432);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(430);
				match(T__2);
				setState(431);
				type(0);
				}
				break;
			}
			setState(436);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				{
				setState(434);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(435);
				expression(0);
				}
				break;
			}
			setState(442);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(440);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
					case 1:
						{
						setState(438);
						getterDeclaration();
						}
						break;
					case 2:
						{
						setState(439);
						setterDeclaration();
						}
						break;
					}
					} 
				}
				setState(444);
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
			setState(446);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(445);
				access();
				}
			}

			setState(448);
			match(T__29);
			setState(450);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(449);
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
			setState(453);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(452);
				access();
				}
			}

			setState(455);
			match(T__30);
			setState(461);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(456);
				match(T__7);
				setState(457);
				parameter();
				setState(458);
				match(T__8);
				setState(459);
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
			setState(466);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__39) {
				{
				{
				setState(463);
				annotation();
				}
				}
				setState(468);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(470);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(469);
				access();
				}
			}

			setState(473);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				{
				setState(472);
				modifier();
				}
				break;
			}
			setState(475);
			match(T__31);
			setState(476);
			match(IDENTIFIER);
			setState(478);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(477);
				genericDeclaration();
				}
			}

			setState(480);
			match(T__7);
			setState(482);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(481);
				parameterList();
				}
			}

			setState(484);
			match(T__8);
			setState(487);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(485);
				match(T__2);
				setState(486);
				type(0);
				}
				break;
			}
			setState(490);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(489);
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
			setState(495);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__39) {
				{
				{
				setState(492);
				annotation();
				}
				}
				setState(497);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(499);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(498);
				access();
				}
			}

			setState(502);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				{
				setState(501);
				modifier();
				}
				break;
			}
			setState(504);
			match(T__32);
			setState(506);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(505);
				genericDeclaration();
				}
			}

			setState(508);
			match(T__7);
			setState(510);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(509);
				parameterList();
				}
			}

			setState(512);
			match(T__8);
			setState(513);
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
			setState(518);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__39) {
				{
				{
				setState(515);
				annotation();
				}
				}
				setState(520);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(522);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(521);
				access();
				}
			}

			setState(525);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				{
				setState(524);
				modifier();
				}
				break;
			}
			setState(527);
			match(T__33);
			setState(528);
			operator();
			setState(530);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(529);
				genericDeclaration();
				}
			}

			setState(532);
			match(T__7);
			setState(534);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(533);
				parameterList();
				}
			}

			setState(536);
			match(T__8);
			setState(539);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
			case 1:
				{
				setState(537);
				match(T__2);
				setState(538);
				type(0);
				}
				break;
			}
			setState(541);
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
			setState(543);
			parameter();
			setState(548);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(544);
				match(T__9);
				setState(545);
				parameter();
				}
				}
				setState(550);
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
			setState(551);
			match(IDENTIFIER);
			setState(552);
			match(T__2);
			setState(553);
			type(0);
			setState(556);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(554);
				match(T__28);
				setState(555);
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
			setState(558);
			expression(0);
			setState(563);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(559);
				match(T__9);
				setState(560);
				expression(0);
				}
				}
				setState(565);
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
			setState(569);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__39) {
				{
				{
				setState(566);
				annotation();
				}
				}
				setState(571);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(573);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(572);
				access();
				}
			}

			setState(576);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				{
				setState(575);
				modifier();
				}
				break;
			}
			setState(578);
			match(T__34);
			setState(579);
			match(IDENTIFIER);
			setState(581);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				{
				setState(580);
				genericDeclaration();
				}
				break;
			}
			setState(584);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,71,_ctx) ) {
			case 1:
				{
				setState(583);
				inheritance();
				}
				break;
			}
			setState(587);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				{
				setState(586);
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
			setState(589);
			match(T__7);
			setState(590);
			inheritanceSpec();
			setState(595);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(591);
				match(T__9);
				setState(592);
				inheritanceSpec();
				}
				}
				setState(597);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(598);
			match(T__8);
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
			setState(617);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(600);
				((InheritanceSpecContext)_localctx).first = type(0);
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 2);
				{
				setState(601);
				match(T__35);
				setState(602);
				match(T__28);
				setState(603);
				((InheritanceSpecContext)_localctx).extends_ = type(0);
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 3);
				{
				setState(604);
				match(T__36);
				setState(605);
				match(T__28);
				setState(606);
				match(T__4);
				setState(607);
				type(0);
				setState(612);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(608);
					match(T__9);
					setState(609);
					type(0);
					}
					}
					setState(614);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(615);
				match(T__5);
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
			setState(622);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__39) {
				{
				{
				setState(619);
				annotation();
				}
				}
				setState(624);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(626);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(625);
				access();
				}
			}

			setState(629);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				{
				setState(628);
				modifier();
				}
				break;
			}
			setState(631);
			match(T__37);
			setState(632);
			match(IDENTIFIER);
			setState(634);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				{
				setState(633);
				inheritance();
				}
				break;
			}
			setState(637);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(636);
				genericDeclaration();
				}
				break;
			}
			setState(640);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
			case 1:
				{
				setState(639);
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
			setState(645);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__39) {
				{
				{
				setState(642);
				annotation();
				}
				}
				setState(647);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(649);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(648);
				access();
				}
			}

			setState(652);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
			case 1:
				{
				setState(651);
				modifier();
				}
				break;
			}
			setState(654);
			match(T__31);
			setState(656);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(655);
				genericDeclaration();
				}
			}

			setState(658);
			match(IDENTIFIER);
			setState(659);
			match(T__7);
			setState(661);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(660);
				parameterList();
				}
			}

			setState(663);
			match(T__8);
			{
			setState(664);
			match(T__2);
			setState(665);
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
			setState(670);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__39) {
				{
				{
				setState(667);
				annotation();
				}
				}
				setState(672);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(674);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 7L) != 0)) {
				{
				setState(673);
				access();
				}
			}

			setState(677);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				{
				setState(676);
				modifier();
				}
				break;
			}
			setState(679);
			match(T__38);
			setState(680);
			match(IDENTIFIER);
			setState(686);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
			case 1:
				{
				setState(681);
				match(T__7);
				setState(683);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(682);
					parameterList();
					}
				}

				setState(685);
				match(T__8);
				}
				break;
			}
			setState(689);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				{
				setState(688);
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
			setState(691);
			match(T__39);
			setState(692);
			match(IDENTIFIER);
			setState(700);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(693);
				match(T__7);
				setState(696);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(694);
					annotationArgs();
					}
					break;
				case T__44:
				case T__45:
				case T__46:
				case NUMBER:
				case STRING:
					{
					setState(695);
					literal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(698);
				match(T__8);
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
			setState(702);
			annotationArg();
			setState(707);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(703);
				match(T__9);
				setState(704);
				annotationArg();
				}
				}
				setState(709);
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
			setState(710);
			match(IDENTIFIER);
			setState(711);
			match(T__28);
			setState(712);
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
			setState(714);
			match(IDENTIFIER);
			setState(716);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(715);
				genericDeclaration();
				}
			}

			setState(718);
			match(T__7);
			setState(720);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 246290604622112L) != 0) || ((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 7183L) != 0)) {
				{
				setState(719);
				argumentList();
				}
			}

			setState(722);
			match(T__8);
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
		int _startState = 108;
		enterRecursionRule(_localctx, 108, RULE_type, _p);
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
			setState(725);
			match(IDENTIFIER);
			setState(730);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,98,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(726);
					match(T__6);
					setState(727);
					match(IDENTIFIER);
					}
					} 
				}
				setState(732);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,98,_ctx);
			}
			}
			setState(734);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
			case 1:
				{
				setState(733);
				genericDeclaration();
				}
				break;
			}
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(752);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,103,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(750);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
					case 1:
						{
						_localctx = new UnionTypeContext(new TypeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(736);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(739); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(737);
								((UnionTypeContext)_localctx).union = match(T__40);
								setState(738);
								type(0);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(741); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,100,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					case 2:
						{
						_localctx = new IntersectionTypeContext(new TypeContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_type);
						setState(743);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(746); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(744);
								((IntersectionTypeContext)_localctx).intersection = match(T__41);
								setState(745);
								type(0);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(748); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,101,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					}
					} 
				}
				setState(754);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,103,_ctx);
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
		enterRule(_localctx, 110, RULE_genericDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(755);
			match(T__4);
			setState(756);
			generic();
			setState(761);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(757);
				match(T__9);
				setState(758);
				generic();
				}
				}
				setState(763);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(764);
			match(T__5);
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
		public Token extends_;
		public Token super_;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public GenericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generic; }
	}

	public final GenericContext generic() throws RecognitionException {
		GenericContext _localctx = new GenericContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_generic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(771);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
			case 1:
				{
				setState(766);
				_la = _input.LA(1);
				if ( !(_la==T__42 || _la==IDENTIFIER) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(769);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__2:
				case T__35:
					{
					setState(767);
					((GenericContext)_localctx).extends_ = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==T__2 || _la==T__35) ) {
						((GenericContext)_localctx).extends_ = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					break;
				case T__43:
					{
					setState(768);
					((GenericContext)_localctx).super_ = match(T__43);
					}
					break;
				case IDENTIFIER:
					break;
				default:
					break;
				}
				}
				break;
			}
			setState(773);
			type(0);
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
		enterRule(_localctx, 114, RULE_literal);
		try {
			setState(780);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				_localctx = new NumContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(775);
				match(NUMBER);
				}
				break;
			case STRING:
				_localctx = new StrContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(776);
				match(STRING);
				}
				break;
			case T__44:
				_localctx = new NullContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(777);
				match(T__44);
				}
				break;
			case T__45:
				_localctx = new TrueContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(778);
				match(T__45);
				}
				break;
			case T__46:
				_localctx = new FalseContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(779);
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
		public BinaryOperatorContext binaryOperator() {
			return getRuleContext(BinaryOperatorContext.class,0);
		}
		public UnaryOperatorContext unaryOperator() {
			return getRuleContext(UnaryOperatorContext.class,0);
		}
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_operator);
		try {
			setState(784);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__26:
			case T__40:
			case T__41:
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
				enterOuterAlt(_localctx, 1);
				{
				setState(782);
				binaryOperator();
				}
				break;
			case T__4:
			case T__68:
			case T__69:
			case T__70:
			case T__71:
				enterOuterAlt(_localctx, 2);
				{
				setState(783);
				unaryOperator();
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
		enterRule(_localctx, 118, RULE_binaryOperator);
		try {
			setState(810);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__47:
				_localctx = new MulContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(786);
				match(T__47);
				}
				break;
			case T__48:
				_localctx = new DivideContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(787);
				match(T__48);
				}
				break;
			case T__49:
				_localctx = new DIVContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(788);
				match(T__49);
				}
				break;
			case T__50:
				_localctx = new MODContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(789);
				match(T__50);
				}
				break;
			case T__51:
				_localctx = new AddContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(790);
				match(T__51);
				}
				break;
			case T__52:
				_localctx = new SubContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(791);
				match(T__52);
				}
				break;
			case T__53:
				_localctx = new SHRContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(792);
				match(T__53);
				}
				break;
			case T__54:
				_localctx = new SHLContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(793);
				match(T__54);
				}
				break;
			case T__41:
				_localctx = new ANDContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(794);
				match(T__41);
				}
				break;
			case T__55:
				_localctx = new XORContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(795);
				match(T__55);
				}
				break;
			case T__56:
				_localctx = new XORContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(796);
				match(T__56);
				}
				break;
			case T__40:
				_localctx = new ORContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(797);
				match(T__40);
				}
				break;
			case T__57:
				_localctx = new GTContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(798);
				match(T__57);
				}
				break;
			case T__58:
				_localctx = new LTContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(799);
				match(T__58);
				}
				break;
			case T__59:
				_localctx = new GEContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(800);
				match(T__59);
				}
				break;
			case T__60:
				_localctx = new LEContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(801);
				match(T__60);
				}
				break;
			case T__61:
				_localctx = new EQContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(802);
				match(T__61);
				}
				break;
			case T__62:
				_localctx = new NEQContext(_localctx);
				enterOuterAlt(_localctx, 18);
				{
				setState(803);
				match(T__62);
				}
				break;
			case T__63:
				_localctx = new IsInstanceContext(_localctx);
				enterOuterAlt(_localctx, 19);
				{
				setState(804);
				match(T__63);
				}
				break;
			case T__26:
				_localctx = new InContext(_localctx);
				enterOuterAlt(_localctx, 20);
				{
				setState(805);
				match(T__26);
				}
				break;
			case T__64:
				_localctx = new CANDContext(_localctx);
				enterOuterAlt(_localctx, 21);
				{
				setState(806);
				match(T__64);
				}
				break;
			case T__65:
				_localctx = new CANDContext(_localctx);
				enterOuterAlt(_localctx, 22);
				{
				setState(807);
				match(T__65);
				}
				break;
			case T__66:
				_localctx = new CORContext(_localctx);
				enterOuterAlt(_localctx, 23);
				{
				setState(808);
				match(T__66);
				}
				break;
			case T__67:
				_localctx = new CORContext(_localctx);
				enterOuterAlt(_localctx, 24);
				{
				setState(809);
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
	public static class ArrayAccessOpContext extends UnaryOperatorContext {
		public ArrayAccessOpContext(UnaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NotContext extends UnaryOperatorContext {
		public NotContext(UnaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class DecrementContext extends UnaryOperatorContext {
		public DecrementContext(UnaryOperatorContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IncrementContext extends UnaryOperatorContext {
		public IncrementContext(UnaryOperatorContext ctx) { copyFrom(ctx); }
	}

	public final UnaryOperatorContext unaryOperator() throws RecognitionException {
		UnaryOperatorContext _localctx = new UnaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_unaryOperator);
		try {
			setState(818);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__68:
				_localctx = new IncrementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(812);
				match(T__68);
				}
				break;
			case T__69:
				_localctx = new DecrementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(813);
				match(T__69);
				}
				break;
			case T__70:
				_localctx = new NotContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(814);
				match(T__70);
				}
				break;
			case T__71:
				_localctx = new NotContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(815);
				match(T__71);
				}
				break;
			case T__4:
				_localctx = new ArrayAccessOpContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(816);
				match(T__4);
				setState(817);
				match(T__5);
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
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(821);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -274877772726240L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 511L) != 0)) {
				{
				setState(820);
				operator();
				}
			}

			setState(823);
			match(T__28);
			setState(824);
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
		enterRule(_localctx, 124, RULE_access);
		try {
			setState(829);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__72:
				_localctx = new PublicContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(826);
				match(T__72);
				}
				break;
			case T__73:
				_localctx = new PrivateContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(827);
				match(T__73);
				}
				break;
			case T__74:
				_localctx = new ProtectedContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(828);
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
		enterRule(_localctx, 126, RULE_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(833);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__75:
				{
				setState(831);
				((ModifierContext)_localctx).static_ = match(T__75);
				}
				break;
			case T__76:
				{
				setState(832);
				((ModifierContext)_localctx).abstract_ = match(T__76);
				}
				break;
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__37:
			case T__38:
			case T__77:
			case IDENTIFIER:
				break;
			default:
				break;
			}
			setState(836);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__77) {
				{
				setState(835);
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
		case 54:
			return type_sempred((TypeContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001T\u0347\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0001\u0000\u0005\u0000"+
		"\u0082\b\u0000\n\u0000\f\u0000\u0085\t\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001\u0091\b\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u0002\u009b\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u00a0"+
		"\b\u0003\u0001\u0004\u0001\u0004\u0005\u0004\u00a4\b\u0004\n\u0004\f\u0004"+
		"\u00a7\t\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004\u00ae\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00b8\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005\u00c7\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0005\u0005\u00cd\b\u0005\n\u0005\f\u0005\u00d0\t\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006\u00d8\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00dd"+
		"\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006\u00e4\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\u00e9"+
		"\b\u0007\n\u0007\f\u0007\u00ec\t\u0007\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0005\b\u00f2\b\b\n\b\f\b\u00f5\t\b\u0001\t\u0001\t\u0001\t\u0001\t\u0003"+
		"\t\u00fb\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0005\u000b\u0104\b\u000b\n\u000b\f\u000b\u0107\t\u000b\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u010e\b\f\n\f\f\f\u0111\t\f\u0001"+
		"\r\u0001\r\u0001\r\u0005\r\u0116\b\r\n\r\f\r\u0119\t\r\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u011d\b\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u0121"+
		"\b\u000f\u0001\u0010\u0001\u0010\u0003\u0010\u0125\b\u0010\u0001\u0011"+
		"\u0001\u0011\u0003\u0011\u0129\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0005\u0012\u012e\b\u0012\n\u0012\f\u0012\u0131\t\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u0137\b\u0012\n\u0012\f\u0012"+
		"\u013a\t\u0012\u0001\u0012\u0003\u0012\u013d\b\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0005\u0012\u0142\b\u0012\n\u0012\f\u0012\u0145\t\u0012"+
		"\u0001\u0012\u0001\u0012\u0003\u0012\u0149\b\u0012\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0005\u0013\u014f\b\u0013\n\u0013\f\u0013\u0152"+
		"\t\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001"+
		"\u0016\u0005\u0016\u0160\b\u0016\n\u0016\f\u0016\u0163\t\u0016\u0001\u0016"+
		"\u0003\u0016\u0166\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0001\u001a\u0001\u001a\u0003\u001a\u0175\b\u001a\u0001\u001b"+
		"\u0001\u001b\u0003\u001b\u0179\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001e\u0001\u001e\u0003\u001e\u0186\b\u001e\u0001\u001f\u0001\u001f"+
		"\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001 \u0001 \u0001 \u0001 \u0001 \u0001 \u0001!\u0001!\u0001!\u0001"+
		"!\u0001\"\u0005\"\u019b\b\"\n\"\f\"\u019e\t\"\u0001\"\u0003\"\u01a1\b"+
		"\"\u0001\"\u0003\"\u01a4\b\"\u0001\"\u0001\"\u0001\"\u0005\"\u01a9\b\""+
		"\n\"\f\"\u01ac\t\"\u0001#\u0001#\u0001#\u0003#\u01b1\b#\u0001#\u0001#"+
		"\u0003#\u01b5\b#\u0001#\u0001#\u0005#\u01b9\b#\n#\f#\u01bc\t#\u0001$\u0003"+
		"$\u01bf\b$\u0001$\u0001$\u0003$\u01c3\b$\u0001%\u0003%\u01c6\b%\u0001"+
		"%\u0001%\u0001%\u0001%\u0001%\u0001%\u0003%\u01ce\b%\u0001&\u0005&\u01d1"+
		"\b&\n&\f&\u01d4\t&\u0001&\u0003&\u01d7\b&\u0001&\u0003&\u01da\b&\u0001"+
		"&\u0001&\u0001&\u0003&\u01df\b&\u0001&\u0001&\u0003&\u01e3\b&\u0001&\u0001"+
		"&\u0001&\u0003&\u01e8\b&\u0001&\u0003&\u01eb\b&\u0001\'\u0005\'\u01ee"+
		"\b\'\n\'\f\'\u01f1\t\'\u0001\'\u0003\'\u01f4\b\'\u0001\'\u0003\'\u01f7"+
		"\b\'\u0001\'\u0001\'\u0003\'\u01fb\b\'\u0001\'\u0001\'\u0003\'\u01ff\b"+
		"\'\u0001\'\u0001\'\u0001\'\u0001(\u0005(\u0205\b(\n(\f(\u0208\t(\u0001"+
		"(\u0003(\u020b\b(\u0001(\u0003(\u020e\b(\u0001(\u0001(\u0001(\u0003(\u0213"+
		"\b(\u0001(\u0001(\u0003(\u0217\b(\u0001(\u0001(\u0001(\u0003(\u021c\b"+
		"(\u0001(\u0001(\u0001)\u0001)\u0001)\u0005)\u0223\b)\n)\f)\u0226\t)\u0001"+
		"*\u0001*\u0001*\u0001*\u0001*\u0003*\u022d\b*\u0001+\u0001+\u0001+\u0005"+
		"+\u0232\b+\n+\f+\u0235\t+\u0001,\u0005,\u0238\b,\n,\f,\u023b\t,\u0001"+
		",\u0003,\u023e\b,\u0001,\u0003,\u0241\b,\u0001,\u0001,\u0001,\u0003,\u0246"+
		"\b,\u0001,\u0003,\u0249\b,\u0001,\u0003,\u024c\b,\u0001-\u0001-\u0001"+
		"-\u0001-\u0005-\u0252\b-\n-\f-\u0255\t-\u0001-\u0001-\u0001.\u0001.\u0001"+
		".\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0001.\u0005.\u0263\b.\n."+
		"\f.\u0266\t.\u0001.\u0001.\u0003.\u026a\b.\u0001/\u0005/\u026d\b/\n/\f"+
		"/\u0270\t/\u0001/\u0003/\u0273\b/\u0001/\u0003/\u0276\b/\u0001/\u0001"+
		"/\u0001/\u0003/\u027b\b/\u0001/\u0003/\u027e\b/\u0001/\u0003/\u0281\b"+
		"/\u00010\u00050\u0284\b0\n0\f0\u0287\t0\u00010\u00030\u028a\b0\u00010"+
		"\u00030\u028d\b0\u00010\u00010\u00030\u0291\b0\u00010\u00010\u00010\u0003"+
		"0\u0296\b0\u00010\u00010\u00010\u00010\u00011\u00051\u029d\b1\n1\f1\u02a0"+
		"\t1\u00011\u00031\u02a3\b1\u00011\u00031\u02a6\b1\u00011\u00011\u0001"+
		"1\u00011\u00031\u02ac\b1\u00011\u00031\u02af\b1\u00011\u00031\u02b2\b"+
		"1\u00012\u00012\u00012\u00012\u00012\u00032\u02b9\b2\u00012\u00012\u0003"+
		"2\u02bd\b2\u00013\u00013\u00013\u00053\u02c2\b3\n3\f3\u02c5\t3\u00014"+
		"\u00014\u00014\u00014\u00015\u00015\u00035\u02cd\b5\u00015\u00015\u0003"+
		"5\u02d1\b5\u00015\u00015\u00016\u00016\u00016\u00016\u00056\u02d9\b6\n"+
		"6\f6\u02dc\t6\u00016\u00036\u02df\b6\u00016\u00016\u00016\u00046\u02e4"+
		"\b6\u000b6\f6\u02e5\u00016\u00016\u00016\u00046\u02eb\b6\u000b6\f6\u02ec"+
		"\u00056\u02ef\b6\n6\f6\u02f2\t6\u00017\u00017\u00017\u00017\u00057\u02f8"+
		"\b7\n7\f7\u02fb\t7\u00017\u00017\u00018\u00018\u00018\u00038\u0302\b8"+
		"\u00038\u0304\b8\u00018\u00018\u00019\u00019\u00019\u00019\u00019\u0003"+
		"9\u030d\b9\u0001:\u0001:\u0003:\u0311\b:\u0001;\u0001;\u0001;\u0001;\u0001"+
		";\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001"+
		";\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0001;\u0003"+
		";\u032b\b;\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0003<\u0333\b<\u0001"+
		"=\u0003=\u0336\b=\u0001=\u0001=\u0001=\u0001>\u0001>\u0001>\u0003>\u033e"+
		"\b>\u0001?\u0001?\u0003?\u0342\b?\u0001?\u0003?\u0345\b?\u0001?\u0000"+
		"\u0002\nl@\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016"+
		"\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprt"+
		"vxz|~\u0000\u0005\u0001\u0000TT\u0002\u0000OOQQ\u0001\u0000\u001c\u001d"+
		"\u0002\u0000++OO\u0002\u0000\u0003\u0003$$\u03b0\u0000\u0083\u0001\u0000"+
		"\u0000\u0000\u0002\u0090\u0001\u0000\u0000\u0000\u0004\u009a\u0001\u0000"+
		"\u0000\u0000\u0006\u009f\u0001\u0000\u0000\u0000\b\u00ad\u0001\u0000\u0000"+
		"\u0000\n\u00b7\u0001\u0000\u0000\u0000\f\u00e3\u0001\u0000\u0000\u0000"+
		"\u000e\u00e5\u0001\u0000\u0000\u0000\u0010\u00ed\u0001\u0000\u0000\u0000"+
		"\u0012\u00f6\u0001\u0000\u0000\u0000\u0014\u00fc\u0001\u0000\u0000\u0000"+
		"\u0016\u0100\u0001\u0000\u0000\u0000\u0018\u0108\u0001\u0000\u0000\u0000"+
		"\u001a\u0112\u0001\u0000\u0000\u0000\u001c\u011c\u0001\u0000\u0000\u0000"+
		"\u001e\u011e\u0001\u0000\u0000\u0000 \u0122\u0001\u0000\u0000\u0000\""+
		"\u0126\u0001\u0000\u0000\u0000$\u013c\u0001\u0000\u0000\u0000&\u014a\u0001"+
		"\u0000\u0000\u0000(\u0156\u0001\u0000\u0000\u0000*\u015a\u0001\u0000\u0000"+
		"\u0000,\u015d\u0001\u0000\u0000\u0000.\u0167\u0001\u0000\u0000\u00000"+
		"\u016b\u0001\u0000\u0000\u00002\u016f\u0001\u0000\u0000\u00004\u0174\u0001"+
		"\u0000\u0000\u00006\u0178\u0001\u0000\u0000\u00008\u017a\u0001\u0000\u0000"+
		"\u0000:\u017e\u0001\u0000\u0000\u0000<\u0185\u0001\u0000\u0000\u0000>"+
		"\u0187\u0001\u0000\u0000\u0000@\u018f\u0001\u0000\u0000\u0000B\u0195\u0001"+
		"\u0000\u0000\u0000D\u019c\u0001\u0000\u0000\u0000F\u01ad\u0001\u0000\u0000"+
		"\u0000H\u01be\u0001\u0000\u0000\u0000J\u01c5\u0001\u0000\u0000\u0000L"+
		"\u01d2\u0001\u0000\u0000\u0000N\u01ef\u0001\u0000\u0000\u0000P\u0206\u0001"+
		"\u0000\u0000\u0000R\u021f\u0001\u0000\u0000\u0000T\u0227\u0001\u0000\u0000"+
		"\u0000V\u022e\u0001\u0000\u0000\u0000X\u0239\u0001\u0000\u0000\u0000Z"+
		"\u024d\u0001\u0000\u0000\u0000\\\u0269\u0001\u0000\u0000\u0000^\u026e"+
		"\u0001\u0000\u0000\u0000`\u0285\u0001\u0000\u0000\u0000b\u029e\u0001\u0000"+
		"\u0000\u0000d\u02b3\u0001\u0000\u0000\u0000f\u02be\u0001\u0000\u0000\u0000"+
		"h\u02c6\u0001\u0000\u0000\u0000j\u02ca\u0001\u0000\u0000\u0000l\u02d4"+
		"\u0001\u0000\u0000\u0000n\u02f3\u0001\u0000\u0000\u0000p\u0303\u0001\u0000"+
		"\u0000\u0000r\u030c\u0001\u0000\u0000\u0000t\u0310\u0001\u0000\u0000\u0000"+
		"v\u032a\u0001\u0000\u0000\u0000x\u0332\u0001\u0000\u0000\u0000z\u0335"+
		"\u0001\u0000\u0000\u0000|\u033d\u0001\u0000\u0000\u0000~\u0341\u0001\u0000"+
		"\u0000\u0000\u0080\u0082\u0003\u0002\u0001\u0000\u0081\u0080\u0001\u0000"+
		"\u0000\u0000\u0082\u0085\u0001\u0000\u0000\u0000\u0083\u0081\u0001\u0000"+
		"\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0086\u0001\u0000"+
		"\u0000\u0000\u0085\u0083\u0001\u0000\u0000\u0000\u0086\u0087\u0005\u0000"+
		"\u0000\u0001\u0087\u0001\u0001\u0000\u0000\u0000\u0088\u0091\u0003\u0010"+
		"\b\u0000\u0089\u0091\u0003\u0012\t\u0000\u008a\u0091\u0003\u0004\u0002"+
		"\u0000\u008b\u0091\u0003\u0006\u0003\u0000\u008c\u0091\u0003\n\u0005\u0000"+
		"\u008d\u0091\u0003\u001e\u000f\u0000\u008e\u0091\u0003 \u0010\u0000\u008f"+
		"\u0091\u0003\"\u0011\u0000\u0090\u0088\u0001\u0000\u0000\u0000\u0090\u0089"+
		"\u0001\u0000\u0000\u0000\u0090\u008a\u0001\u0000\u0000\u0000\u0090\u008b"+
		"\u0001\u0000\u0000\u0000\u0090\u008c\u0001\u0000\u0000\u0000\u0090\u008d"+
		"\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0090\u008f"+
		"\u0001\u0000\u0000\u0000\u0091\u0003\u0001\u0000\u0000\u0000\u0092\u009b"+
		"\u0003L&\u0000\u0093\u009b\u0003D\"\u0000\u0094\u009b\u0003P(\u0000\u0095"+
		"\u009b\u0003N\'\u0000\u0096\u009b\u0003`0\u0000\u0097\u009b\u0003X,\u0000"+
		"\u0098\u009b\u0003^/\u0000\u0099\u009b\u0003b1\u0000\u009a\u0092\u0001"+
		"\u0000\u0000\u0000\u009a\u0093\u0001\u0000\u0000\u0000\u009a\u0094\u0001"+
		"\u0000\u0000\u0000\u009a\u0095\u0001\u0000\u0000\u0000\u009a\u0096\u0001"+
		"\u0000\u0000\u0000\u009a\u0097\u0001\u0000\u0000\u0000\u009a\u0098\u0001"+
		"\u0000\u0000\u0000\u009a\u0099\u0001\u0000\u0000\u0000\u009b\u0005\u0001"+
		"\u0000\u0000\u0000\u009c\u00a0\u0003,\u0016\u0000\u009d\u00a0\u0003&\u0013"+
		"\u0000\u009e\u00a0\u00034\u001a\u0000\u009f\u009c\u0001\u0000\u0000\u0000"+
		"\u009f\u009d\u0001\u0000\u0000\u0000\u009f\u009e\u0001\u0000\u0000\u0000"+
		"\u00a0\u0007\u0001\u0000\u0000\u0000\u00a1\u00a5\u0005\u0001\u0000\u0000"+
		"\u00a2\u00a4\u0003\u0002\u0001\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a7\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a8\u0001\u0000\u0000\u0000"+
		"\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a8\u00ae\u0005\u0002\u0000\u0000"+
		"\u00a9\u00aa\u0005\u0003\u0000\u0000\u00aa\u00ae\u0003\u0002\u0001\u0000"+
		"\u00ab\u00ac\u0005\u0004\u0000\u0000\u00ac\u00ae\u0003\u0002\u0001\u0000"+
		"\u00ad\u00a1\u0001\u0000\u0000\u0000\u00ad\u00a9\u0001\u0000\u0000\u0000"+
		"\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\t\u0001\u0000\u0000\u0000\u00af"+
		"\u00b0\u0006\u0005\uffff\uffff\u0000\u00b0\u00b8\u0003\f\u0006\u0000\u00b1"+
		"\u00b8\u0003$\u0012\u0000\u00b2\u00b8\u0003j5\u0000\u00b3\u00b4\u0003"+
		"x<\u0000\u00b4\u00b5\b\u0000\u0000\u0000\u00b5\u00b6\u0003\n\u0005\u0002"+
		"\u00b6\u00b8\u0001\u0000\u0000\u0000\u00b7\u00af\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b1\u0001\u0000\u0000\u0000\u00b7\u00b2\u0001\u0000\u0000\u0000"+
		"\u00b7\u00b3\u0001\u0000\u0000\u0000\u00b8\u00ce\u0001\u0000\u0000\u0000"+
		"\u00b9\u00ba\n\u0001\u0000\u0000\u00ba\u00bb\u0003v;\u0000\u00bb\u00bc"+
		"\u0003\n\u0005\u0002\u00bc\u00cd\u0001\u0000\u0000\u0000\u00bd\u00be\n"+
		"\u0005\u0000\u0000\u00be\u00bf\u0005\u0005\u0000\u0000\u00bf\u00c0\u0003"+
		"V+\u0000\u00c0\u00c1\u0005\u0006\u0000\u0000\u00c1\u00cd\u0001\u0000\u0000"+
		"\u0000\u00c2\u00c3\n\u0004\u0000\u0000\u00c3\u00c6\u0005\u0007\u0000\u0000"+
		"\u00c4\u00c7\u0005O\u0000\u0000\u00c5\u00c7\u0003j5\u0000\u00c6\u00c4"+
		"\u0001\u0000\u0000\u0000\u00c6\u00c5\u0001\u0000\u0000\u0000\u00c7\u00cd"+
		"\u0001\u0000\u0000\u0000\u00c8\u00c9\n\u0003\u0000\u0000\u00c9\u00ca\u0003"+
		"x<\u0000\u00ca\u00cb\b\u0000\u0000\u0000\u00cb\u00cd\u0001\u0000\u0000"+
		"\u0000\u00cc\u00b9\u0001\u0000\u0000\u0000\u00cc\u00bd\u0001\u0000\u0000"+
		"\u0000\u00cc\u00c2\u0001\u0000\u0000\u0000\u00cc\u00c8\u0001\u0000\u0000"+
		"\u0000\u00cd\u00d0\u0001\u0000\u0000\u0000\u00ce\u00cc\u0001\u0000\u0000"+
		"\u0000\u00ce\u00cf\u0001\u0000\u0000\u0000\u00cf\u000b\u0001\u0000\u0000"+
		"\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d1\u00e4\u0003r9\u0000\u00d2"+
		"\u00e4\u0005O\u0000\u0000\u00d3\u00d4\u0005O\u0000\u0000\u00d4\u00e4\u0003"+
		"z=\u0000\u00d5\u00d7\u0005\u0005\u0000\u0000\u00d6\u00d8\u0003V+\u0000"+
		"\u00d7\u00d6\u0001\u0000\u0000\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000"+
		"\u00d8\u00d9\u0001\u0000\u0000\u0000\u00d9\u00e4\u0005\u0006\u0000\u0000"+
		"\u00da\u00dc\u0005\u0005\u0000\u0000\u00db\u00dd\u0003\u000e\u0007\u0000"+
		"\u00dc\u00db\u0001\u0000\u0000\u0000\u00dc\u00dd\u0001\u0000\u0000\u0000"+
		"\u00dd\u00de\u0001\u0000\u0000\u0000\u00de\u00e4\u0005\u0006\u0000\u0000"+
		"\u00df\u00e0\u0005\b\u0000\u0000\u00e0\u00e1\u0003\n\u0005\u0000\u00e1"+
		"\u00e2\u0005\t\u0000\u0000\u00e2\u00e4\u0001\u0000\u0000\u0000\u00e3\u00d1"+
		"\u0001\u0000\u0000\u0000\u00e3\u00d2\u0001\u0000\u0000\u0000\u00e3\u00d3"+
		"\u0001\u0000\u0000\u0000\u00e3\u00d5\u0001\u0000\u0000\u0000\u00e3\u00da"+
		"\u0001\u0000\u0000\u0000\u00e3\u00df\u0001\u0000\u0000\u0000\u00e4\r\u0001"+
		"\u0000\u0000\u0000\u00e5\u00ea\u0003B!\u0000\u00e6\u00e7\u0005\n\u0000"+
		"\u0000\u00e7\u00e9\u0003B!\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e9"+
		"\u00ec\u0001\u0000\u0000\u0000\u00ea\u00e8\u0001\u0000\u0000\u0000\u00ea"+
		"\u00eb\u0001\u0000\u0000\u0000\u00eb\u000f\u0001\u0000\u0000\u0000\u00ec"+
		"\u00ea\u0001\u0000\u0000\u0000\u00ed\u00ee\u0005\u000b\u0000\u0000\u00ee"+
		"\u00f3\u0005O\u0000\u0000\u00ef\u00f0\u0005\u0007\u0000\u0000\u00f0\u00f2"+
		"\u0005O\u0000\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f2\u00f5\u0001"+
		"\u0000\u0000\u0000\u00f3\u00f1\u0001\u0000\u0000\u0000\u00f3\u00f4\u0001"+
		"\u0000\u0000\u0000\u00f4\u0011\u0001\u0000\u0000\u0000\u00f5\u00f3\u0001"+
		"\u0000\u0000\u0000\u00f6\u00fa\u0005\f\u0000\u0000\u00f7\u00fb\u0003\u001c"+
		"\u000e\u0000\u00f8\u00fb\u0003\u0018\f\u0000\u00f9\u00fb\u0003\u001a\r"+
		"\u0000\u00fa\u00f7\u0001\u0000\u0000\u0000\u00fa\u00f8\u0001\u0000\u0000"+
		"\u0000\u00fa\u00f9\u0001\u0000\u0000\u0000\u00fb\u0013\u0001\u0000\u0000"+
		"\u0000\u00fc\u00fd\u0003\u0016\u000b\u0000\u00fd\u00fe\u0005\r\u0000\u0000"+
		"\u00fe\u00ff\u0005O\u0000\u0000\u00ff\u0015\u0001\u0000\u0000\u0000\u0100"+
		"\u0105\u0005O\u0000\u0000\u0101\u0102\u0005\u0007\u0000\u0000\u0102\u0104"+
		"\u0005O\u0000\u0000\u0103\u0101\u0001\u0000\u0000\u0000\u0104\u0107\u0001"+
		"\u0000\u0000\u0000\u0105\u0103\u0001\u0000\u0000\u0000\u0105\u0106\u0001"+
		"\u0000\u0000\u0000\u0106\u0017\u0001\u0000\u0000\u0000\u0107\u0105\u0001"+
		"\u0000\u0000\u0000\u0108\u0109\u0003\u001a\r\u0000\u0109\u010a\u0005\u000e"+
		"\u0000\u0000\u010a\u010f\u0005O\u0000\u0000\u010b\u010c\u0005\u0007\u0000"+
		"\u0000\u010c\u010e\u0005O\u0000\u0000\u010d\u010b\u0001\u0000\u0000\u0000"+
		"\u010e\u0111\u0001\u0000\u0000\u0000\u010f\u010d\u0001\u0000\u0000\u0000"+
		"\u010f\u0110\u0001\u0000\u0000\u0000\u0110\u0019\u0001\u0000\u0000\u0000"+
		"\u0111\u010f\u0001\u0000\u0000\u0000\u0112\u0117\u0003\u001c\u000e\u0000"+
		"\u0113\u0114\u0005\n\u0000\u0000\u0114\u0116\u0003\u001c\u000e\u0000\u0115"+
		"\u0113\u0001\u0000\u0000\u0000\u0116\u0119\u0001\u0000\u0000\u0000\u0117"+
		"\u0115\u0001\u0000\u0000\u0000\u0117\u0118\u0001\u0000\u0000\u0000\u0118"+
		"\u001b\u0001\u0000\u0000\u0000\u0119\u0117\u0001\u0000\u0000\u0000\u011a"+
		"\u011d\u0003\u0016\u000b\u0000\u011b\u011d\u0003\u0014\n\u0000\u011c\u011a"+
		"\u0001\u0000\u0000\u0000\u011c\u011b\u0001\u0000\u0000\u0000\u011d\u001d"+
		"\u0001\u0000\u0000\u0000\u011e\u0120\u0005\u000f\u0000\u0000\u011f\u0121"+
		"\u0005O\u0000\u0000\u0120\u011f\u0001\u0000\u0000\u0000\u0120\u0121\u0001"+
		"\u0000\u0000\u0000\u0121\u001f\u0001\u0000\u0000\u0000\u0122\u0124\u0005"+
		"\u0010\u0000\u0000\u0123\u0125\u0005O\u0000\u0000\u0124\u0123\u0001\u0000"+
		"\u0000\u0000\u0124\u0125\u0001\u0000\u0000\u0000\u0125!\u0001\u0000\u0000"+
		"\u0000\u0126\u0128\u0005\u0011\u0000\u0000\u0127\u0129\u0003\n\u0005\u0000"+
		"\u0128\u0127\u0001\u0000\u0000\u0000\u0128\u0129\u0001\u0000\u0000\u0000"+
		"\u0129#\u0001\u0000\u0000\u0000\u012a\u012f\u0005O\u0000\u0000\u012b\u012c"+
		"\u0005\n\u0000\u0000\u012c\u012e\u0005O\u0000\u0000\u012d\u012b\u0001"+
		"\u0000\u0000\u0000\u012e\u0131\u0001\u0000\u0000\u0000\u012f\u012d\u0001"+
		"\u0000\u0000\u0000\u012f\u0130\u0001\u0000\u0000\u0000\u0130\u013d\u0001"+
		"\u0000\u0000\u0000\u0131\u012f\u0001\u0000\u0000\u0000\u0132\u0133\u0005"+
		"\b\u0000\u0000\u0133\u0138\u0005O\u0000\u0000\u0134\u0135\u0005\n\u0000"+
		"\u0000\u0135\u0137\u0005O\u0000\u0000\u0136\u0134\u0001\u0000\u0000\u0000"+
		"\u0137\u013a\u0001\u0000\u0000\u0000\u0138\u0136\u0001\u0000\u0000\u0000"+
		"\u0138\u0139\u0001\u0000\u0000\u0000\u0139\u013b\u0001\u0000\u0000\u0000"+
		"\u013a\u0138\u0001\u0000\u0000\u0000\u013b\u013d\u0005\t\u0000\u0000\u013c"+
		"\u012a\u0001\u0000\u0000\u0000\u013c\u0132\u0001\u0000\u0000\u0000\u013d"+
		"\u013e\u0001\u0000\u0000\u0000\u013e\u0148\u0005\u0004\u0000\u0000\u013f"+
		"\u0143\u0005\u0001\u0000\u0000\u0140\u0142\u0003\u0002\u0001\u0000\u0141"+
		"\u0140\u0001\u0000\u0000\u0000\u0142\u0145\u0001\u0000\u0000\u0000\u0143"+
		"\u0141\u0001\u0000\u0000\u0000\u0143\u0144\u0001\u0000\u0000\u0000\u0144"+
		"\u0146\u0001\u0000\u0000\u0000\u0145\u0143\u0001\u0000\u0000\u0000\u0146"+
		"\u0149\u0005\u0002\u0000\u0000\u0147\u0149\u0003\u0002\u0001\u0000\u0148"+
		"\u013f\u0001\u0000\u0000\u0000\u0148\u0147\u0001\u0000\u0000\u0000\u0149"+
		"%\u0001\u0000\u0000\u0000\u014a\u014b\u0005\u0012\u0000\u0000\u014b\u014c"+
		"\u0003\n\u0005\u0000\u014c\u0150\u0005\u0001\u0000\u0000\u014d\u014f\u0003"+
		"(\u0014\u0000\u014e\u014d\u0001\u0000\u0000\u0000\u014f\u0152\u0001\u0000"+
		"\u0000\u0000\u0150\u014e\u0001\u0000\u0000\u0000\u0150\u0151\u0001\u0000"+
		"\u0000\u0000\u0151\u0153\u0001\u0000\u0000\u0000\u0152\u0150\u0001\u0000"+
		"\u0000\u0000\u0153\u0154\u0003*\u0015\u0000\u0154\u0155\u0005\u0002\u0000"+
		"\u0000\u0155\'\u0001\u0000\u0000\u0000\u0156\u0157\u0005\u0013\u0000\u0000"+
		"\u0157\u0158\u0003\n\u0005\u0000\u0158\u0159\u0003\b\u0004\u0000\u0159"+
		")\u0001\u0000\u0000\u0000\u015a\u015b\u0005\u0014\u0000\u0000\u015b\u015c"+
		"\u0003\b\u0004\u0000\u015c+\u0001\u0000\u0000\u0000\u015d\u0161\u0003"+
		".\u0017\u0000\u015e\u0160\u00030\u0018\u0000\u015f\u015e\u0001\u0000\u0000"+
		"\u0000\u0160\u0163\u0001\u0000\u0000\u0000\u0161\u015f\u0001\u0000\u0000"+
		"\u0000\u0161\u0162\u0001\u0000\u0000\u0000\u0162\u0165\u0001\u0000\u0000"+
		"\u0000\u0163\u0161\u0001\u0000\u0000\u0000\u0164\u0166\u00032\u0019\u0000"+
		"\u0165\u0164\u0001\u0000\u0000\u0000\u0165\u0166\u0001\u0000\u0000\u0000"+
		"\u0166-\u0001\u0000\u0000\u0000\u0167\u0168\u0005\u0015\u0000\u0000\u0168"+
		"\u0169\u0003\n\u0005\u0000\u0169\u016a\u0003\b\u0004\u0000\u016a/\u0001"+
		"\u0000\u0000\u0000\u016b\u016c\u0005\u0016\u0000\u0000\u016c\u016d\u0003"+
		"\n\u0005\u0000\u016d\u016e\u0003\b\u0004\u0000\u016e1\u0001\u0000\u0000"+
		"\u0000\u016f\u0170\u0005\u0017\u0000\u0000\u0170\u0171\u0003\b\u0004\u0000"+
		"\u01713\u0001\u0000\u0000\u0000\u0172\u0175\u00036\u001b\u0000\u0173\u0175"+
		"\u0003<\u001e\u0000\u0174\u0172\u0001\u0000\u0000\u0000\u0174\u0173\u0001"+
		"\u0000\u0000\u0000\u01755\u0001\u0000\u0000\u0000\u0176\u0179\u00038\u001c"+
		"\u0000\u0177\u0179\u0003:\u001d\u0000\u0178\u0176\u0001\u0000\u0000\u0000"+
		"\u0178\u0177\u0001\u0000\u0000\u0000\u01797\u0001\u0000\u0000\u0000\u017a"+
		"\u017b\u0005\u0018\u0000\u0000\u017b\u017c\u0003\n\u0005\u0000\u017c\u017d"+
		"\u0003\b\u0004\u0000\u017d9\u0001\u0000\u0000\u0000\u017e\u017f\u0005"+
		"\u0019\u0000\u0000\u017f\u0180\u0003\b\u0004\u0000\u0180\u0181\u0005\u0018"+
		"\u0000\u0000\u0181\u0182\u0003\n\u0005\u0000\u0182;\u0001\u0000\u0000"+
		"\u0000\u0183\u0186\u0003>\u001f\u0000\u0184\u0186\u0003@ \u0000\u0185"+
		"\u0183\u0001\u0000\u0000\u0000\u0185\u0184\u0001\u0000\u0000\u0000\u0186"+
		"=\u0001\u0000\u0000\u0000\u0187\u0188\u0005\u001a\u0000\u0000\u0188\u0189"+
		"\u0003D\"\u0000\u0189\u018a\u0005\n\u0000\u0000\u018a\u018b\u0003\n\u0005"+
		"\u0000\u018b\u018c\u0005\n\u0000\u0000\u018c\u018d\u0003\n\u0005\u0000"+
		"\u018d\u018e\u0003\b\u0004\u0000\u018e?\u0001\u0000\u0000\u0000\u018f"+
		"\u0190\u0005\u001a\u0000\u0000\u0190\u0191\u0003F#\u0000\u0191\u0192\u0005"+
		"\u001b\u0000\u0000\u0192\u0193\u0003\n\u0005\u0000\u0193\u0194\u0003\b"+
		"\u0004\u0000\u0194A\u0001\u0000\u0000\u0000\u0195\u0196\u0007\u0001\u0000"+
		"\u0000\u0196\u0197\u0005\u0003\u0000\u0000\u0197\u0198\u0003\n\u0005\u0000"+
		"\u0198C\u0001\u0000\u0000\u0000\u0199\u019b\u0003d2\u0000\u019a\u0199"+
		"\u0001\u0000\u0000\u0000\u019b\u019e\u0001\u0000\u0000\u0000\u019c\u019a"+
		"\u0001\u0000\u0000\u0000\u019c\u019d\u0001\u0000\u0000\u0000\u019d\u01a0"+
		"\u0001\u0000\u0000\u0000\u019e\u019c\u0001\u0000\u0000\u0000\u019f\u01a1"+
		"\u0003|>\u0000\u01a0\u019f\u0001\u0000\u0000\u0000\u01a0\u01a1\u0001\u0000"+
		"\u0000\u0000\u01a1\u01a3\u0001\u0000\u0000\u0000\u01a2\u01a4\u0003~?\u0000"+
		"\u01a3\u01a2\u0001\u0000\u0000\u0000\u01a3\u01a4\u0001\u0000\u0000\u0000"+
		"\u01a4\u01a5\u0001\u0000\u0000\u0000\u01a5\u01aa\u0003F#\u0000\u01a6\u01a7"+
		"\u0005\n\u0000\u0000\u01a7\u01a9\u0003F#\u0000\u01a8\u01a6\u0001\u0000"+
		"\u0000\u0000\u01a9\u01ac\u0001\u0000\u0000\u0000\u01aa\u01a8\u0001\u0000"+
		"\u0000\u0000\u01aa\u01ab\u0001\u0000\u0000\u0000\u01abE\u0001\u0000\u0000"+
		"\u0000\u01ac\u01aa\u0001\u0000\u0000\u0000\u01ad\u01b0\u0005O\u0000\u0000"+
		"\u01ae\u01af\u0005\u0003\u0000\u0000\u01af\u01b1\u0003l6\u0000\u01b0\u01ae"+
		"\u0001\u0000\u0000\u0000\u01b0\u01b1\u0001\u0000\u0000\u0000\u01b1\u01b4"+
		"\u0001\u0000\u0000\u0000\u01b2\u01b3\u0007\u0002\u0000\u0000\u01b3\u01b5"+
		"\u0003\n\u0005\u0000\u01b4\u01b2\u0001\u0000\u0000\u0000\u01b4\u01b5\u0001"+
		"\u0000\u0000\u0000\u01b5\u01ba\u0001\u0000\u0000\u0000\u01b6\u01b9\u0003"+
		"H$\u0000\u01b7\u01b9\u0003J%\u0000\u01b8\u01b6\u0001\u0000\u0000\u0000"+
		"\u01b8\u01b7\u0001\u0000\u0000\u0000\u01b9\u01bc\u0001\u0000\u0000\u0000"+
		"\u01ba\u01b8\u0001\u0000\u0000\u0000\u01ba\u01bb\u0001\u0000\u0000\u0000"+
		"\u01bbG\u0001\u0000\u0000\u0000\u01bc\u01ba\u0001\u0000\u0000\u0000\u01bd"+
		"\u01bf\u0003|>\u0000\u01be\u01bd\u0001\u0000\u0000\u0000\u01be\u01bf\u0001"+
		"\u0000\u0000\u0000\u01bf\u01c0\u0001\u0000\u0000\u0000\u01c0\u01c2\u0005"+
		"\u001e\u0000\u0000\u01c1\u01c3\u0003\b\u0004\u0000\u01c2\u01c1\u0001\u0000"+
		"\u0000\u0000\u01c2\u01c3\u0001\u0000\u0000\u0000\u01c3I\u0001\u0000\u0000"+
		"\u0000\u01c4\u01c6\u0003|>\u0000\u01c5\u01c4\u0001\u0000\u0000\u0000\u01c5"+
		"\u01c6\u0001\u0000\u0000\u0000\u01c6\u01c7\u0001\u0000\u0000\u0000\u01c7"+
		"\u01cd\u0005\u001f\u0000\u0000\u01c8\u01c9\u0005\b\u0000\u0000\u01c9\u01ca"+
		"\u0003T*\u0000\u01ca\u01cb\u0005\t\u0000\u0000\u01cb\u01cc\u0003\b\u0004"+
		"\u0000\u01cc\u01ce\u0001\u0000\u0000\u0000\u01cd\u01c8\u0001\u0000\u0000"+
		"\u0000\u01cd\u01ce\u0001\u0000\u0000\u0000\u01ceK\u0001\u0000\u0000\u0000"+
		"\u01cf\u01d1\u0003d2\u0000\u01d0\u01cf\u0001\u0000\u0000\u0000\u01d1\u01d4"+
		"\u0001\u0000\u0000\u0000\u01d2\u01d0\u0001\u0000\u0000\u0000\u01d2\u01d3"+
		"\u0001\u0000\u0000\u0000\u01d3\u01d6\u0001\u0000\u0000\u0000\u01d4\u01d2"+
		"\u0001\u0000\u0000\u0000\u01d5\u01d7\u0003|>\u0000\u01d6\u01d5\u0001\u0000"+
		"\u0000\u0000\u01d6\u01d7\u0001\u0000\u0000\u0000\u01d7\u01d9\u0001\u0000"+
		"\u0000\u0000\u01d8\u01da\u0003~?\u0000\u01d9\u01d8\u0001\u0000\u0000\u0000"+
		"\u01d9\u01da\u0001\u0000\u0000\u0000\u01da\u01db\u0001\u0000\u0000\u0000"+
		"\u01db\u01dc\u0005 \u0000\u0000\u01dc\u01de\u0005O\u0000\u0000\u01dd\u01df"+
		"\u0003n7\u0000\u01de\u01dd\u0001\u0000\u0000\u0000\u01de\u01df\u0001\u0000"+
		"\u0000\u0000\u01df\u01e0\u0001\u0000\u0000\u0000\u01e0\u01e2\u0005\b\u0000"+
		"\u0000\u01e1\u01e3\u0003R)\u0000\u01e2\u01e1\u0001\u0000\u0000\u0000\u01e2"+
		"\u01e3\u0001\u0000\u0000\u0000\u01e3\u01e4\u0001\u0000\u0000\u0000\u01e4"+
		"\u01e7\u0005\t\u0000\u0000\u01e5\u01e6\u0005\u0003\u0000\u0000\u01e6\u01e8"+
		"\u0003l6\u0000\u01e7\u01e5\u0001\u0000\u0000\u0000\u01e7\u01e8\u0001\u0000"+
		"\u0000\u0000\u01e8\u01ea\u0001\u0000\u0000\u0000\u01e9\u01eb\u0003\b\u0004"+
		"\u0000\u01ea\u01e9\u0001\u0000\u0000\u0000\u01ea\u01eb\u0001\u0000\u0000"+
		"\u0000\u01ebM\u0001\u0000\u0000\u0000\u01ec\u01ee\u0003d2\u0000\u01ed"+
		"\u01ec\u0001\u0000\u0000\u0000\u01ee\u01f1\u0001\u0000\u0000\u0000\u01ef"+
		"\u01ed\u0001\u0000\u0000\u0000\u01ef\u01f0\u0001\u0000\u0000\u0000\u01f0"+
		"\u01f3\u0001\u0000\u0000\u0000\u01f1\u01ef\u0001\u0000\u0000\u0000\u01f2"+
		"\u01f4\u0003|>\u0000\u01f3\u01f2\u0001\u0000\u0000\u0000\u01f3\u01f4\u0001"+
		"\u0000\u0000\u0000\u01f4\u01f6\u0001\u0000\u0000\u0000\u01f5\u01f7\u0003"+
		"~?\u0000\u01f6\u01f5\u0001\u0000\u0000\u0000\u01f6\u01f7\u0001\u0000\u0000"+
		"\u0000\u01f7\u01f8\u0001\u0000\u0000\u0000\u01f8\u01fa\u0005!\u0000\u0000"+
		"\u01f9\u01fb\u0003n7\u0000\u01fa\u01f9\u0001\u0000\u0000\u0000\u01fa\u01fb"+
		"\u0001\u0000\u0000\u0000\u01fb\u01fc\u0001\u0000\u0000\u0000\u01fc\u01fe"+
		"\u0005\b\u0000\u0000\u01fd\u01ff\u0003R)\u0000\u01fe\u01fd\u0001\u0000"+
		"\u0000\u0000\u01fe\u01ff\u0001\u0000\u0000\u0000\u01ff\u0200\u0001\u0000"+
		"\u0000\u0000\u0200\u0201\u0005\t\u0000\u0000\u0201\u0202\u0003\b\u0004"+
		"\u0000\u0202O\u0001\u0000\u0000\u0000\u0203\u0205\u0003d2\u0000\u0204"+
		"\u0203\u0001\u0000\u0000\u0000\u0205\u0208\u0001\u0000\u0000\u0000\u0206"+
		"\u0204\u0001\u0000\u0000\u0000\u0206\u0207\u0001\u0000\u0000\u0000\u0207"+
		"\u020a\u0001\u0000\u0000\u0000\u0208\u0206\u0001\u0000\u0000\u0000\u0209"+
		"\u020b\u0003|>\u0000\u020a\u0209\u0001\u0000\u0000\u0000\u020a\u020b\u0001"+
		"\u0000\u0000\u0000\u020b\u020d\u0001\u0000\u0000\u0000\u020c\u020e\u0003"+
		"~?\u0000\u020d\u020c\u0001\u0000\u0000\u0000\u020d\u020e\u0001\u0000\u0000"+
		"\u0000\u020e\u020f\u0001\u0000\u0000\u0000\u020f\u0210\u0005\"\u0000\u0000"+
		"\u0210\u0212\u0003t:\u0000\u0211\u0213\u0003n7\u0000\u0212\u0211\u0001"+
		"\u0000\u0000\u0000\u0212\u0213\u0001\u0000\u0000\u0000\u0213\u0214\u0001"+
		"\u0000\u0000\u0000\u0214\u0216\u0005\b\u0000\u0000\u0215\u0217\u0003R"+
		")\u0000\u0216\u0215\u0001\u0000\u0000\u0000\u0216\u0217\u0001\u0000\u0000"+
		"\u0000\u0217\u0218\u0001\u0000\u0000\u0000\u0218\u021b\u0005\t\u0000\u0000"+
		"\u0219\u021a\u0005\u0003\u0000\u0000\u021a\u021c\u0003l6\u0000\u021b\u0219"+
		"\u0001\u0000\u0000\u0000\u021b\u021c\u0001\u0000\u0000\u0000\u021c\u021d"+
		"\u0001\u0000\u0000\u0000\u021d\u021e\u0003\b\u0004\u0000\u021eQ\u0001"+
		"\u0000\u0000\u0000\u021f\u0224\u0003T*\u0000\u0220\u0221\u0005\n\u0000"+
		"\u0000\u0221\u0223\u0003T*\u0000\u0222\u0220\u0001\u0000\u0000\u0000\u0223"+
		"\u0226\u0001\u0000\u0000\u0000\u0224\u0222\u0001\u0000\u0000\u0000\u0224"+
		"\u0225\u0001\u0000\u0000\u0000\u0225S\u0001\u0000\u0000\u0000\u0226\u0224"+
		"\u0001\u0000\u0000\u0000\u0227\u0228\u0005O\u0000\u0000\u0228\u0229\u0005"+
		"\u0003\u0000\u0000\u0229\u022c\u0003l6\u0000\u022a\u022b\u0005\u001d\u0000"+
		"\u0000\u022b\u022d\u0003\n\u0005\u0000\u022c\u022a\u0001\u0000\u0000\u0000"+
		"\u022c\u022d\u0001\u0000\u0000\u0000\u022dU\u0001\u0000\u0000\u0000\u022e"+
		"\u0233\u0003\n\u0005\u0000\u022f\u0230\u0005\n\u0000\u0000\u0230\u0232"+
		"\u0003\n\u0005\u0000\u0231\u022f\u0001\u0000\u0000\u0000\u0232\u0235\u0001"+
		"\u0000\u0000\u0000\u0233\u0231\u0001\u0000\u0000\u0000\u0233\u0234\u0001"+
		"\u0000\u0000\u0000\u0234W\u0001\u0000\u0000\u0000\u0235\u0233\u0001\u0000"+
		"\u0000\u0000\u0236\u0238\u0003d2\u0000\u0237\u0236\u0001\u0000\u0000\u0000"+
		"\u0238\u023b\u0001\u0000\u0000\u0000\u0239\u0237\u0001\u0000\u0000\u0000"+
		"\u0239\u023a\u0001\u0000\u0000\u0000\u023a\u023d\u0001\u0000\u0000\u0000"+
		"\u023b\u0239\u0001\u0000\u0000\u0000\u023c\u023e\u0003|>\u0000\u023d\u023c"+
		"\u0001\u0000\u0000\u0000\u023d\u023e\u0001\u0000\u0000\u0000\u023e\u0240"+
		"\u0001\u0000\u0000\u0000\u023f\u0241\u0003~?\u0000\u0240\u023f\u0001\u0000"+
		"\u0000\u0000\u0240\u0241\u0001\u0000\u0000\u0000\u0241\u0242\u0001\u0000"+
		"\u0000\u0000\u0242\u0243\u0005#\u0000\u0000\u0243\u0245\u0005O\u0000\u0000"+
		"\u0244\u0246\u0003n7\u0000\u0245\u0244\u0001\u0000\u0000\u0000\u0245\u0246"+
		"\u0001\u0000\u0000\u0000\u0246\u0248\u0001\u0000\u0000\u0000\u0247\u0249"+
		"\u0003Z-\u0000\u0248\u0247\u0001\u0000\u0000\u0000\u0248\u0249\u0001\u0000"+
		"\u0000\u0000\u0249\u024b\u0001\u0000\u0000\u0000\u024a\u024c\u0003\b\u0004"+
		"\u0000\u024b\u024a\u0001\u0000\u0000\u0000\u024b\u024c\u0001\u0000\u0000"+
		"\u0000\u024cY\u0001\u0000\u0000\u0000\u024d\u024e\u0005\b\u0000\u0000"+
		"\u024e\u0253\u0003\\.\u0000\u024f\u0250\u0005\n\u0000\u0000\u0250\u0252"+
		"\u0003\\.\u0000\u0251\u024f\u0001\u0000\u0000\u0000\u0252\u0255\u0001"+
		"\u0000\u0000\u0000\u0253\u0251\u0001\u0000\u0000\u0000\u0253\u0254\u0001"+
		"\u0000\u0000\u0000\u0254\u0256\u0001\u0000\u0000\u0000\u0255\u0253\u0001"+
		"\u0000\u0000\u0000\u0256\u0257\u0005\t\u0000\u0000\u0257[\u0001\u0000"+
		"\u0000\u0000\u0258\u026a\u0003l6\u0000\u0259\u025a\u0005$\u0000\u0000"+
		"\u025a\u025b\u0005\u001d\u0000\u0000\u025b\u026a\u0003l6\u0000\u025c\u025d"+
		"\u0005%\u0000\u0000\u025d\u025e\u0005\u001d\u0000\u0000\u025e\u025f\u0005"+
		"\u0005\u0000\u0000\u025f\u0264\u0003l6\u0000\u0260\u0261\u0005\n\u0000"+
		"\u0000\u0261\u0263\u0003l6\u0000\u0262\u0260\u0001\u0000\u0000\u0000\u0263"+
		"\u0266\u0001\u0000\u0000\u0000\u0264\u0262\u0001\u0000\u0000\u0000\u0264"+
		"\u0265\u0001\u0000\u0000\u0000\u0265\u0267\u0001\u0000\u0000\u0000\u0266"+
		"\u0264\u0001\u0000\u0000\u0000\u0267\u0268\u0005\u0006\u0000\u0000\u0268"+
		"\u026a\u0001\u0000\u0000\u0000\u0269\u0258\u0001\u0000\u0000\u0000\u0269"+
		"\u0259\u0001\u0000\u0000\u0000\u0269\u025c\u0001\u0000\u0000\u0000\u026a"+
		"]\u0001\u0000\u0000\u0000\u026b\u026d\u0003d2\u0000\u026c\u026b\u0001"+
		"\u0000\u0000\u0000\u026d\u0270\u0001\u0000\u0000\u0000\u026e\u026c\u0001"+
		"\u0000\u0000\u0000\u026e\u026f\u0001\u0000\u0000\u0000\u026f\u0272\u0001"+
		"\u0000\u0000\u0000\u0270\u026e\u0001\u0000\u0000\u0000\u0271\u0273\u0003"+
		"|>\u0000\u0272\u0271\u0001\u0000\u0000\u0000\u0272\u0273\u0001\u0000\u0000"+
		"\u0000\u0273\u0275\u0001\u0000\u0000\u0000\u0274\u0276\u0003~?\u0000\u0275"+
		"\u0274\u0001\u0000\u0000\u0000\u0275\u0276\u0001\u0000\u0000\u0000\u0276"+
		"\u0277\u0001\u0000\u0000\u0000\u0277\u0278\u0005&\u0000\u0000\u0278\u027a"+
		"\u0005O\u0000\u0000\u0279\u027b\u0003Z-\u0000\u027a\u0279\u0001\u0000"+
		"\u0000\u0000\u027a\u027b\u0001\u0000\u0000\u0000\u027b\u027d\u0001\u0000"+
		"\u0000\u0000\u027c\u027e\u0003n7\u0000\u027d\u027c\u0001\u0000\u0000\u0000"+
		"\u027d\u027e\u0001\u0000\u0000\u0000\u027e\u0280\u0001\u0000\u0000\u0000"+
		"\u027f\u0281\u0003\b\u0004\u0000\u0280\u027f\u0001\u0000\u0000\u0000\u0280"+
		"\u0281\u0001\u0000\u0000\u0000\u0281_\u0001\u0000\u0000\u0000\u0282\u0284"+
		"\u0003d2\u0000\u0283\u0282\u0001\u0000\u0000\u0000\u0284\u0287\u0001\u0000"+
		"\u0000\u0000\u0285\u0283\u0001\u0000\u0000\u0000\u0285\u0286\u0001\u0000"+
		"\u0000\u0000\u0286\u0289\u0001\u0000\u0000\u0000\u0287\u0285\u0001\u0000"+
		"\u0000\u0000\u0288\u028a\u0003|>\u0000\u0289\u0288\u0001\u0000\u0000\u0000"+
		"\u0289\u028a\u0001\u0000\u0000\u0000\u028a\u028c\u0001\u0000\u0000\u0000"+
		"\u028b\u028d\u0003~?\u0000\u028c\u028b\u0001\u0000\u0000\u0000\u028c\u028d"+
		"\u0001\u0000\u0000\u0000\u028d\u028e\u0001\u0000\u0000\u0000\u028e\u0290"+
		"\u0005 \u0000\u0000\u028f\u0291\u0003n7\u0000\u0290\u028f\u0001\u0000"+
		"\u0000\u0000\u0290\u0291\u0001\u0000\u0000\u0000\u0291\u0292\u0001\u0000"+
		"\u0000\u0000\u0292\u0293\u0005O\u0000\u0000\u0293\u0295\u0005\b\u0000"+
		"\u0000\u0294\u0296\u0003R)\u0000\u0295\u0294\u0001\u0000\u0000\u0000\u0295"+
		"\u0296\u0001\u0000\u0000\u0000\u0296\u0297\u0001\u0000\u0000\u0000\u0297"+
		"\u0298\u0005\t\u0000\u0000\u0298\u0299\u0005\u0003\u0000\u0000\u0299\u029a"+
		"\u0003l6\u0000\u029aa\u0001\u0000\u0000\u0000\u029b\u029d\u0003d2\u0000"+
		"\u029c\u029b\u0001\u0000\u0000\u0000\u029d\u02a0\u0001\u0000\u0000\u0000"+
		"\u029e\u029c\u0001\u0000\u0000\u0000\u029e\u029f\u0001\u0000\u0000\u0000"+
		"\u029f\u02a2\u0001\u0000\u0000\u0000\u02a0\u029e\u0001\u0000\u0000\u0000"+
		"\u02a1\u02a3\u0003|>\u0000\u02a2\u02a1\u0001\u0000\u0000\u0000\u02a2\u02a3"+
		"\u0001\u0000\u0000\u0000\u02a3\u02a5\u0001\u0000\u0000\u0000\u02a4\u02a6"+
		"\u0003~?\u0000\u02a5\u02a4\u0001\u0000\u0000\u0000\u02a5\u02a6\u0001\u0000"+
		"\u0000\u0000\u02a6\u02a7\u0001\u0000\u0000\u0000\u02a7\u02a8\u0005\'\u0000"+
		"\u0000\u02a8\u02ae\u0005O\u0000\u0000\u02a9\u02ab\u0005\b\u0000\u0000"+
		"\u02aa\u02ac\u0003R)\u0000\u02ab\u02aa\u0001\u0000\u0000\u0000\u02ab\u02ac"+
		"\u0001\u0000\u0000\u0000\u02ac\u02ad\u0001\u0000\u0000\u0000\u02ad\u02af"+
		"\u0005\t\u0000\u0000\u02ae\u02a9\u0001\u0000\u0000\u0000\u02ae\u02af\u0001"+
		"\u0000\u0000\u0000\u02af\u02b1\u0001\u0000\u0000\u0000\u02b0\u02b2\u0003"+
		"\b\u0004\u0000\u02b1\u02b0\u0001\u0000\u0000\u0000\u02b1\u02b2\u0001\u0000"+
		"\u0000\u0000\u02b2c\u0001\u0000\u0000\u0000\u02b3\u02b4\u0005(\u0000\u0000"+
		"\u02b4\u02bc\u0005O\u0000\u0000\u02b5\u02b8\u0005\b\u0000\u0000\u02b6"+
		"\u02b9\u0003f3\u0000\u02b7\u02b9\u0003r9\u0000\u02b8\u02b6\u0001\u0000"+
		"\u0000\u0000\u02b8\u02b7\u0001\u0000\u0000\u0000\u02b9\u02ba\u0001\u0000"+
		"\u0000\u0000\u02ba\u02bb\u0005\t\u0000\u0000\u02bb\u02bd\u0001\u0000\u0000"+
		"\u0000\u02bc\u02b5\u0001\u0000\u0000\u0000\u02bc\u02bd\u0001\u0000\u0000"+
		"\u0000\u02bde\u0001\u0000\u0000\u0000\u02be\u02c3\u0003h4\u0000\u02bf"+
		"\u02c0\u0005\n\u0000\u0000\u02c0\u02c2\u0003h4\u0000\u02c1\u02bf\u0001"+
		"\u0000\u0000\u0000\u02c2\u02c5\u0001\u0000\u0000\u0000\u02c3\u02c1\u0001"+
		"\u0000\u0000\u0000\u02c3\u02c4\u0001\u0000\u0000\u0000\u02c4g\u0001\u0000"+
		"\u0000\u0000\u02c5\u02c3\u0001\u0000\u0000\u0000\u02c6\u02c7\u0005O\u0000"+
		"\u0000\u02c7\u02c8\u0005\u001d\u0000\u0000\u02c8\u02c9\u0005Q\u0000\u0000"+
		"\u02c9i\u0001\u0000\u0000\u0000\u02ca\u02cc\u0005O\u0000\u0000\u02cb\u02cd"+
		"\u0003n7\u0000\u02cc\u02cb\u0001\u0000\u0000\u0000\u02cc\u02cd\u0001\u0000"+
		"\u0000\u0000\u02cd\u02ce\u0001\u0000\u0000\u0000\u02ce\u02d0\u0005\b\u0000"+
		"\u0000\u02cf\u02d1\u0003V+\u0000\u02d0\u02cf\u0001\u0000\u0000\u0000\u02d0"+
		"\u02d1\u0001\u0000\u0000\u0000\u02d1\u02d2\u0001\u0000\u0000\u0000\u02d2"+
		"\u02d3\u0005\t\u0000\u0000\u02d3k\u0001\u0000\u0000\u0000\u02d4\u02d5"+
		"\u00066\uffff\uffff\u0000\u02d5\u02da\u0005O\u0000\u0000\u02d6\u02d7\u0005"+
		"\u0007\u0000\u0000\u02d7\u02d9\u0005O\u0000\u0000\u02d8\u02d6\u0001\u0000"+
		"\u0000\u0000\u02d9\u02dc\u0001\u0000\u0000\u0000\u02da\u02d8\u0001\u0000"+
		"\u0000\u0000\u02da\u02db\u0001\u0000\u0000\u0000\u02db\u02de\u0001\u0000"+
		"\u0000\u0000\u02dc\u02da\u0001\u0000\u0000\u0000\u02dd\u02df\u0003n7\u0000"+
		"\u02de\u02dd\u0001\u0000\u0000\u0000\u02de\u02df\u0001\u0000\u0000\u0000"+
		"\u02df\u02f0\u0001\u0000\u0000\u0000\u02e0\u02e3\n\u0002\u0000\u0000\u02e1"+
		"\u02e2\u0005)\u0000\u0000\u02e2\u02e4\u0003l6\u0000\u02e3\u02e1\u0001"+
		"\u0000\u0000\u0000\u02e4\u02e5\u0001\u0000\u0000\u0000\u02e5\u02e3\u0001"+
		"\u0000\u0000\u0000\u02e5\u02e6\u0001\u0000\u0000\u0000\u02e6\u02ef\u0001"+
		"\u0000\u0000\u0000\u02e7\u02ea\n\u0001\u0000\u0000\u02e8\u02e9\u0005*"+
		"\u0000\u0000\u02e9\u02eb\u0003l6\u0000\u02ea\u02e8\u0001\u0000\u0000\u0000"+
		"\u02eb\u02ec\u0001\u0000\u0000\u0000\u02ec\u02ea\u0001\u0000\u0000\u0000"+
		"\u02ec\u02ed\u0001\u0000\u0000\u0000\u02ed\u02ef\u0001\u0000\u0000\u0000"+
		"\u02ee\u02e0\u0001\u0000\u0000\u0000\u02ee\u02e7\u0001\u0000\u0000\u0000"+
		"\u02ef\u02f2\u0001\u0000\u0000\u0000\u02f0\u02ee\u0001\u0000\u0000\u0000"+
		"\u02f0\u02f1\u0001\u0000\u0000\u0000\u02f1m\u0001\u0000\u0000\u0000\u02f2"+
		"\u02f0\u0001\u0000\u0000\u0000\u02f3\u02f4\u0005\u0005\u0000\u0000\u02f4"+
		"\u02f9\u0003p8\u0000\u02f5\u02f6\u0005\n\u0000\u0000\u02f6\u02f8\u0003"+
		"p8\u0000\u02f7\u02f5\u0001\u0000\u0000\u0000\u02f8\u02fb\u0001\u0000\u0000"+
		"\u0000\u02f9\u02f7\u0001\u0000\u0000\u0000\u02f9\u02fa\u0001\u0000\u0000"+
		"\u0000\u02fa\u02fc\u0001\u0000\u0000\u0000\u02fb\u02f9\u0001\u0000\u0000"+
		"\u0000\u02fc\u02fd\u0005\u0006\u0000\u0000\u02fdo\u0001\u0000\u0000\u0000"+
		"\u02fe\u0301\u0007\u0003\u0000\u0000\u02ff\u0302\u0007\u0004\u0000\u0000"+
		"\u0300\u0302\u0005,\u0000\u0000\u0301\u02ff\u0001\u0000\u0000\u0000\u0301"+
		"\u0300\u0001\u0000\u0000\u0000\u0301\u0302\u0001\u0000\u0000\u0000\u0302"+
		"\u0304\u0001\u0000\u0000\u0000\u0303\u02fe\u0001\u0000\u0000\u0000\u0303"+
		"\u0304\u0001\u0000\u0000\u0000\u0304\u0305\u0001\u0000\u0000\u0000\u0305"+
		"\u0306\u0003l6\u0000\u0306q\u0001\u0000\u0000\u0000\u0307\u030d\u0005"+
		"P\u0000\u0000\u0308\u030d\u0005Q\u0000\u0000\u0309\u030d\u0005-\u0000"+
		"\u0000\u030a\u030d\u0005.\u0000\u0000\u030b\u030d\u0005/\u0000\u0000\u030c"+
		"\u0307\u0001\u0000\u0000\u0000\u030c\u0308\u0001\u0000\u0000\u0000\u030c"+
		"\u0309\u0001\u0000\u0000\u0000\u030c\u030a\u0001\u0000\u0000\u0000\u030c"+
		"\u030b\u0001\u0000\u0000\u0000\u030ds\u0001\u0000\u0000\u0000\u030e\u0311"+
		"\u0003v;\u0000\u030f\u0311\u0003x<\u0000\u0310\u030e\u0001\u0000\u0000"+
		"\u0000\u0310\u030f\u0001\u0000\u0000\u0000\u0311u\u0001\u0000\u0000\u0000"+
		"\u0312\u032b\u00050\u0000\u0000\u0313\u032b\u00051\u0000\u0000\u0314\u032b"+
		"\u00052\u0000\u0000\u0315\u032b\u00053\u0000\u0000\u0316\u032b\u00054"+
		"\u0000\u0000\u0317\u032b\u00055\u0000\u0000\u0318\u032b\u00056\u0000\u0000"+
		"\u0319\u032b\u00057\u0000\u0000\u031a\u032b\u0005*\u0000\u0000\u031b\u032b"+
		"\u00058\u0000\u0000\u031c\u032b\u00059\u0000\u0000\u031d\u032b\u0005)"+
		"\u0000\u0000\u031e\u032b\u0005:\u0000\u0000\u031f\u032b\u0005;\u0000\u0000"+
		"\u0320\u032b\u0005<\u0000\u0000\u0321\u032b\u0005=\u0000\u0000\u0322\u032b"+
		"\u0005>\u0000\u0000\u0323\u032b\u0005?\u0000\u0000\u0324\u032b\u0005@"+
		"\u0000\u0000\u0325\u032b\u0005\u001b\u0000\u0000\u0326\u032b\u0005A\u0000"+
		"\u0000\u0327\u032b\u0005B\u0000\u0000\u0328\u032b\u0005C\u0000\u0000\u0329"+
		"\u032b\u0005D\u0000\u0000\u032a\u0312\u0001\u0000\u0000\u0000\u032a\u0313"+
		"\u0001\u0000\u0000\u0000\u032a\u0314\u0001\u0000\u0000\u0000\u032a\u0315"+
		"\u0001\u0000\u0000\u0000\u032a\u0316\u0001\u0000\u0000\u0000\u032a\u0317"+
		"\u0001\u0000\u0000\u0000\u032a\u0318\u0001\u0000\u0000\u0000\u032a\u0319"+
		"\u0001\u0000\u0000\u0000\u032a\u031a\u0001\u0000\u0000\u0000\u032a\u031b"+
		"\u0001\u0000\u0000\u0000\u032a\u031c\u0001\u0000\u0000\u0000\u032a\u031d"+
		"\u0001\u0000\u0000\u0000\u032a\u031e\u0001\u0000\u0000\u0000\u032a\u031f"+
		"\u0001\u0000\u0000\u0000\u032a\u0320\u0001\u0000\u0000\u0000\u032a\u0321"+
		"\u0001\u0000\u0000\u0000\u032a\u0322\u0001\u0000\u0000\u0000\u032a\u0323"+
		"\u0001\u0000\u0000\u0000\u032a\u0324\u0001\u0000\u0000\u0000\u032a\u0325"+
		"\u0001\u0000\u0000\u0000\u032a\u0326\u0001\u0000\u0000\u0000\u032a\u0327"+
		"\u0001\u0000\u0000\u0000\u032a\u0328\u0001\u0000\u0000\u0000\u032a\u0329"+
		"\u0001\u0000\u0000\u0000\u032bw\u0001\u0000\u0000\u0000\u032c\u0333\u0005"+
		"E\u0000\u0000\u032d\u0333\u0005F\u0000\u0000\u032e\u0333\u0005G\u0000"+
		"\u0000\u032f\u0333\u0005H\u0000\u0000\u0330\u0331\u0005\u0005\u0000\u0000"+
		"\u0331\u0333\u0005\u0006\u0000\u0000\u0332\u032c\u0001\u0000\u0000\u0000"+
		"\u0332\u032d\u0001\u0000\u0000\u0000\u0332\u032e\u0001\u0000\u0000\u0000"+
		"\u0332\u032f\u0001\u0000\u0000\u0000\u0332\u0330\u0001\u0000\u0000\u0000"+
		"\u0333y\u0001\u0000\u0000\u0000\u0334\u0336\u0003t:\u0000\u0335\u0334"+
		"\u0001\u0000\u0000\u0000\u0335\u0336\u0001\u0000\u0000\u0000\u0336\u0337"+
		"\u0001\u0000\u0000\u0000\u0337\u0338\u0005\u001d\u0000\u0000\u0338\u0339"+
		"\u0003\n\u0005\u0000\u0339{\u0001\u0000\u0000\u0000\u033a\u033e\u0005"+
		"I\u0000\u0000\u033b\u033e\u0005J\u0000\u0000\u033c\u033e\u0005K\u0000"+
		"\u0000\u033d\u033a\u0001\u0000\u0000\u0000\u033d\u033b\u0001\u0000\u0000"+
		"\u0000\u033d\u033c\u0001\u0000\u0000\u0000\u033e}\u0001\u0000\u0000\u0000"+
		"\u033f\u0342\u0005L\u0000\u0000\u0340\u0342\u0005M\u0000\u0000\u0341\u033f"+
		"\u0001\u0000\u0000\u0000\u0341\u0340\u0001\u0000\u0000\u0000\u0341\u0342"+
		"\u0001\u0000\u0000\u0000\u0342\u0344\u0001\u0000\u0000\u0000\u0343\u0345"+
		"\u0005N\u0000\u0000\u0344\u0343\u0001\u0000\u0000\u0000\u0344\u0345\u0001"+
		"\u0000\u0000\u0000\u0345\u007f\u0001\u0000\u0000\u0000s\u0083\u0090\u009a"+
		"\u009f\u00a5\u00ad\u00b7\u00c6\u00cc\u00ce\u00d7\u00dc\u00e3\u00ea\u00f3"+
		"\u00fa\u0105\u010f\u0117\u011c\u0120\u0124\u0128\u012f\u0138\u013c\u0143"+
		"\u0148\u0150\u0161\u0165\u0174\u0178\u0185\u019c\u01a0\u01a3\u01aa\u01b0"+
		"\u01b4\u01b8\u01ba\u01be\u01c2\u01c5\u01cd\u01d2\u01d6\u01d9\u01de\u01e2"+
		"\u01e7\u01ea\u01ef\u01f3\u01f6\u01fa\u01fe\u0206\u020a\u020d\u0212\u0216"+
		"\u021b\u0224\u022c\u0233\u0239\u023d\u0240\u0245\u0248\u024b\u0253\u0264"+
		"\u0269\u026e\u0272\u0275\u027a\u027d\u0280\u0285\u0289\u028c\u0290\u0295"+
		"\u029e\u02a2\u02a5\u02ab\u02ae\u02b1\u02b8\u02bc\u02c3\u02cc\u02d0\u02da"+
		"\u02de\u02e5\u02ec\u02ee\u02f0\u02f9\u0301\u0303\u030c\u0310\u032a\u0332"+
		"\u0335\u033d\u0341\u0344";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}