// Generated from C:/Users/Egor/Documents/Java/serious/lum/core/src/main/resources/Lum.g4 by ANTLR 4.13.2
package lum.core.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

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
		T__73=74, T__74=75, T__75=76, T__76=77, IDENTIFIER=78, NUMBER=79, STRING=80, 
		WS=81, COMMENT=82;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_declaration = 2, RULE_controlStatement = 3, 
		RULE_block = 4, RULE_expression = 5, RULE_primary = 6, RULE_keyValueList = 7, 
		RULE_package = 8, RULE_importStatement = 9, RULE_importAs = 10, RULE_importSimple = 11, 
		RULE_importFrom = 12, RULE_importMultiple = 13, RULE_break = 14, RULE_continue = 15, 
		RULE_return = 16, RULE_lambda = 17, RULE_switchStatement = 18, RULE_case = 19, 
		RULE_default = 20, RULE_ifStatement = 21, RULE_if = 22, RULE_elif = 23, 
		RULE_else = 24, RULE_loop = 25, RULE_whileLoopStatement = 26, RULE_whileLoop = 27, 
		RULE_doWhileLoop = 28, RULE_forLoopStatement = 29, RULE_forILoop = 30, 
		RULE_forEachLoop = 31, RULE_keyValue = 32, RULE_variableDeclarationStatement = 33, 
		RULE_variableDeclaration = 34, RULE_getterDeclaration = 35, RULE_setterDeclaration = 36, 
		RULE_functionDeclaration = 37, RULE_constructorDeclaration = 38, RULE_operatorDeclaration = 39, 
		RULE_parameterList = 40, RULE_parameter = 41, RULE_argumentList = 42, 
		RULE_classDeclaration = 43, RULE_inheritance = 44, RULE_inheritanceSpec = 45, 
		RULE_interfaceDeclaration = 46, RULE_functionSignature = 47, RULE_annotationDeclaration = 48, 
		RULE_annotation = 49, RULE_annotationArgs = 50, RULE_annotationArg = 51, 
		RULE_functionCall = 52, RULE_type = 53, RULE_genericDeclaration = 54, 
		RULE_generic = 55, RULE_literal = 56, RULE_operator = 57, RULE_binaryOperator = 58, 
		RULE_unaryOperator = 59, RULE_assignment = 60, RULE_access = 61, RULE_modifier = 62;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "declaration", "controlStatement", "block", "expression", 
			"primary", "keyValueList", "package", "importStatement", "importAs", 
			"importSimple", "importFrom", "importMultiple", "break", "continue", 
			"return", "lambda", "switchStatement", "case", "default", "ifStatement", 
			"if", "elif", "else", "loop", "whileLoopStatement", "whileLoop", "doWhileLoop", 
			"forLoopStatement", "forILoop", "forEachLoop", "keyValue", "variableDeclarationStatement", 
			"variableDeclaration", "getterDeclaration", "setterDeclaration", "functionDeclaration", 
			"constructorDeclaration", "operatorDeclaration", "parameterList", "parameter", 
			"argumentList", "classDeclaration", "inheritance", "inheritanceSpec", 
			"interfaceDeclaration", "functionSignature", "annotationDeclaration", 
			"annotation", "annotationArgs", "annotationArg", "functionCall", "type", 
			"genericDeclaration", "generic", "literal", "operator", "binaryOperator", 
			"unaryOperator", "assignment", "access", "modifier"
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
			"'interface'", "'annotation'", "'@'", "'|'", "'super'", "'&'", "'null'", 
			"'true'", "'false'", "'*'", "'/'", "'//'", "'%'", "'+'", "'-'", "'>>'", 
			"'<<'", "'^'", "'xor'", "'>'", "'<'", "'>='", "'<='", "'=='", "'!='", 
			"'is'", "'&&'", "'and'", "'||'", "'or'", "'++'", "'--'", "'!'", "'not'", 
			"'public'", "'private'", "'protected'", "'static'", "'abstract'", "'final'"
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
			null, null, null, null, null, null, "IDENTIFIER", "NUMBER", "STRING", 
			"WS", "COMMENT"
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
			setState(129);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 125133992204576L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 8191L) != 0)) {
				{
				{
				setState(126);
				statement();
				}
				}
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(132);
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
			setState(142);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(134);
				package_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(135);
				importStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(136);
				declaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(137);
				controlStatement();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(138);
				expression(0);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(139);
				break_();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(140);
				continue_();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(141);
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
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(144);
				functionDeclaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(145);
				variableDeclarationStatement();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(146);
				operatorDeclaration();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(147);
				constructorDeclaration();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(148);
				classDeclaration();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(149);
				interfaceDeclaration();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(150);
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
			setState(156);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__20:
				enterOuterAlt(_localctx, 1);
				{
				setState(153);
				ifStatement();
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				switchStatement();
				}
				break;
			case T__23:
			case T__24:
			case T__25:
				enterOuterAlt(_localctx, 3);
				{
				setState(155);
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
			setState(170);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(158);
				match(T__0);
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 125133992204576L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 8191L) != 0)) {
					{
					{
					setState(159);
					statement();
					}
					}
					setState(164);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(165);
				match(T__1);
				}
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(166);
				match(T__2);
				setState(167);
				statement();
				}
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 3);
				{
				setState(168);
				match(T__3);
				setState(169);
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
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
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
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(179);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				_localctx = new PrimaryExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(173);
				primary();
				}
				break;
			case 2:
				{
				_localctx = new LambdaExpressionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(174);
				lambda();
				}
				break;
			case 3:
				{
				_localctx = new FunctionCallExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(175);
				functionCall();
				}
				break;
			case 4:
				{
				_localctx = new PreUnaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(176);
				((PreUnaryContext)_localctx).before = unaryOperator();
				setState(177);
				expression(2);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(200);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(198);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new BinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(182);
						binaryOperator();
						setState(183);
						expression(2);
						}
						break;
					case 2:
						{
						_localctx = new ArrayAccessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(185);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(186);
						match(T__4);
						setState(187);
						expression(0);
						setState(188);
						match(T__5);
						}
						break;
					case 3:
						{
						_localctx = new MemberAccessContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(190);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(191);
						match(T__6);
						setState(194);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
						case 1:
							{
							setState(192);
							match(IDENTIFIER);
							}
							break;
						case 2:
							{
							setState(193);
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
						setState(196);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(197);
						((PostUnaryContext)_localctx).after = unaryOperator();
						}
						break;
					}
					} 
				}
				setState(202);
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
			setState(221);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new LiteralExprContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(203);
				literal();
				}
				break;
			case 2:
				_localctx = new IdentifierExprContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(204);
				match(IDENTIFIER);
				}
				break;
			case 3:
				_localctx = new AssignmentExprContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(205);
				match(IDENTIFIER);
				setState(206);
				assignment();
				}
				break;
			case 4:
				_localctx = new ListLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(207);
				match(T__4);
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 123145302311200L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 7183L) != 0)) {
					{
					setState(208);
					argumentList();
					}
				}

				setState(211);
				match(T__5);
				}
				break;
			case 5:
				_localctx = new DictLiteralContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(212);
				match(T__4);
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER || _la==STRING) {
					{
					setState(213);
					keyValueList();
					}
				}

				setState(216);
				match(T__5);
				}
				break;
			case 6:
				_localctx = new ParenExprContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(217);
				match(T__7);
				setState(218);
				expression(0);
				setState(219);
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
			setState(223);
			keyValue();
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(224);
				match(T__9);
				setState(225);
				keyValue();
				}
				}
				setState(230);
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
			setState(231);
			match(T__10);
			setState(232);
			match(IDENTIFIER);
			setState(237);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(233);
					match(T__6);
					setState(234);
					match(IDENTIFIER);
					}
					} 
				}
				setState(239);
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
		public ImportSimpleContext importSimple() {
			return getRuleContext(ImportSimpleContext.class,0);
		}
		public ImportAsContext importAs() {
			return getRuleContext(ImportAsContext.class,0);
		}
		public ImportFromContext importFrom() {
			return getRuleContext(ImportFromContext.class,0);
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
			setState(240);
			match(T__11);
			setState(244);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(241);
				importSimple();
				}
				break;
			case 2:
				{
				setState(242);
				importAs();
				}
				break;
			case 3:
				{
				setState(243);
				importFrom();
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
			setState(246);
			importSimple();
			setState(247);
			match(T__12);
			setState(248);
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
			setState(250);
			match(IDENTIFIER);
			setState(255);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(251);
					match(T__6);
					setState(252);
					match(IDENTIFIER);
					}
					} 
				}
				setState(257);
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
		public List<ImportAsContext> importAs() {
			return getRuleContexts(ImportAsContext.class);
		}
		public ImportAsContext importAs(int i) {
			return getRuleContext(ImportAsContext.class,i);
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
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			importAs();
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(259);
				match(T__9);
				setState(260);
				importAs();
				}
				}
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(266);
			match(T__13);
			setState(267);
			match(IDENTIFIER);
			setState(272);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(268);
					match(T__6);
					setState(269);
					match(IDENTIFIER);
					}
					} 
				}
				setState(274);
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
	public static class ImportMultipleContext extends ParserRuleContext {
		public List<ImportSimpleContext> importSimple() {
			return getRuleContexts(ImportSimpleContext.class);
		}
		public ImportSimpleContext importSimple(int i) {
			return getRuleContext(ImportSimpleContext.class,i);
		}
		public ImportMultipleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importMultiple; }
	}

	public final ImportMultipleContext importMultiple() throws RecognitionException {
		ImportMultipleContext _localctx = new ImportMultipleContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_importMultiple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
			importSimple();
			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(276);
				match(T__9);
				setState(277);
				importSimple();
				}
				}
				setState(282);
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
	public static class BreakContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public BreakContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_break; }
	}

	public final BreakContext break_() throws RecognitionException {
		BreakContext _localctx = new BreakContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_break);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(283);
			match(T__14);
			setState(285);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(284);
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
		enterRule(_localctx, 30, RULE_continue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
			match(T__15);
			setState(289);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(288);
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
		enterRule(_localctx, 32, RULE_return);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(T__16);
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(292);
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
		enterRule(_localctx, 34, RULE_lambda);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(313);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				{
				setState(295);
				match(IDENTIFIER);
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(296);
					match(T__9);
					setState(297);
					match(IDENTIFIER);
					}
					}
					setState(302);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case T__7:
				{
				setState(303);
				match(T__7);
				setState(304);
				match(IDENTIFIER);
				setState(309);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(305);
					match(T__9);
					setState(306);
					match(IDENTIFIER);
					}
					}
					setState(311);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(312);
				match(T__8);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(315);
			match(T__3);
			setState(325);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				setState(316);
				match(T__0);
				setState(320);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 125133992204576L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 8191L) != 0)) {
					{
					{
					setState(317);
					statement();
					}
					}
					setState(322);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(323);
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
			case T__43:
			case T__44:
			case T__45:
			case T__67:
			case T__68:
			case T__69:
			case T__70:
			case T__71:
			case T__72:
			case T__73:
			case T__74:
			case T__75:
			case T__76:
			case IDENTIFIER:
			case NUMBER:
			case STRING:
				{
				setState(324);
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
		enterRule(_localctx, 36, RULE_switchStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(327);
			match(T__17);
			setState(328);
			expression(0);
			setState(329);
			match(T__0);
			setState(333);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__18) {
				{
				{
				setState(330);
				case_();
				}
				}
				setState(335);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(336);
			default_();
			setState(337);
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
		enterRule(_localctx, 38, RULE_case);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(339);
			match(T__18);
			setState(340);
			expression(0);
			setState(341);
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
		enterRule(_localctx, 40, RULE_default);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(343);
			match(T__19);
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
		enterRule(_localctx, 42, RULE_ifStatement);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			if_();
			setState(350);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(347);
					elif();
					}
					} 
				}
				setState(352);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			setState(354);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(353);
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
		enterRule(_localctx, 44, RULE_if);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			match(T__20);
			setState(357);
			expression(0);
			setState(358);
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
		enterRule(_localctx, 46, RULE_elif);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(360);
			match(T__21);
			setState(361);
			expression(0);
			setState(362);
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
		enterRule(_localctx, 48, RULE_else);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			match(T__22);
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
		enterRule(_localctx, 50, RULE_loop);
		try {
			setState(369);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__23:
			case T__24:
				enterOuterAlt(_localctx, 1);
				{
				setState(367);
				whileLoopStatement();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 2);
				{
				setState(368);
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
		enterRule(_localctx, 52, RULE_whileLoopStatement);
		try {
			setState(373);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__23:
				enterOuterAlt(_localctx, 1);
				{
				setState(371);
				whileLoop();
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 2);
				{
				setState(372);
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
		enterRule(_localctx, 54, RULE_whileLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(375);
			match(T__23);
			setState(376);
			expression(0);
			setState(377);
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
		enterRule(_localctx, 56, RULE_doWhileLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(379);
			match(T__24);
			setState(380);
			block();
			setState(381);
			match(T__23);
			setState(382);
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
		enterRule(_localctx, 58, RULE_forLoopStatement);
		try {
			setState(386);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(384);
				forILoop();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(385);
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
		enterRule(_localctx, 60, RULE_forILoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			match(T__25);
			setState(389);
			variableDeclarationStatement();
			setState(390);
			match(T__9);
			setState(391);
			expression(0);
			setState(392);
			match(T__9);
			setState(393);
			expression(0);
			setState(394);
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
		enterRule(_localctx, 62, RULE_forEachLoop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			match(T__25);
			setState(397);
			variableDeclaration();
			setState(398);
			match(T__26);
			setState(399);
			expression(0);
			setState(400);
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
		enterRule(_localctx, 64, RULE_keyValue);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			_la = _input.LA(1);
			if ( !(_la==IDENTIFIER || _la==STRING) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(403);
			match(T__2);
			setState(404);
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
		enterRule(_localctx, 66, RULE_variableDeclarationStatement);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(407);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & 7L) != 0)) {
				{
				setState(406);
				access();
				}
			}

			setState(410);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(409);
				modifier();
				}
				break;
			}
			{
			setState(412);
			variableDeclaration();
			}
			setState(417);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(413);
					match(T__9);
					setState(414);
					variableDeclaration();
					}
					} 
				}
				setState(419);
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
		enterRule(_localctx, 68, RULE_variableDeclaration);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(420);
			match(IDENTIFIER);
			setState(423);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(421);
				match(T__2);
				setState(422);
				type(0);
				}
				break;
			}
			setState(427);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(425);
				_la = _input.LA(1);
				if ( !(_la==T__27 || _la==T__28) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(426);
				expression(0);
				}
				break;
			}
			setState(433);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(431);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case T__29:
						{
						setState(429);
						getterDeclaration();
						}
						break;
					case T__30:
						{
						setState(430);
						setterDeclaration();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(435);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
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
		enterRule(_localctx, 70, RULE_getterDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
			match(T__29);
			setState(438);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(437);
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
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public SetterDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setterDeclaration; }
	}

	public final SetterDeclarationContext setterDeclaration() throws RecognitionException {
		SetterDeclarationContext _localctx = new SetterDeclarationContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_setterDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			match(T__30);
			setState(444);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
			case 1:
				{
				{
				setState(441);
				parameter();
				}
				setState(442);
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
		enterRule(_localctx, 74, RULE_functionDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__39) {
				{
				{
				setState(446);
				annotation();
				}
				}
				setState(451);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(453);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & 7L) != 0)) {
				{
				setState(452);
				access();
				}
			}

			setState(456);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(455);
				modifier();
				}
				break;
			}
			setState(458);
			match(T__31);
			setState(459);
			match(IDENTIFIER);
			setState(461);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(460);
				genericDeclaration();
				}
			}

			setState(463);
			match(T__7);
			setState(465);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(464);
				parameterList();
				}
			}

			setState(467);
			match(T__8);
			setState(470);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				{
				setState(468);
				match(T__2);
				setState(469);
				type(0);
				}
				break;
			}
			setState(473);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				{
				setState(472);
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
		enterRule(_localctx, 76, RULE_constructorDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(478);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__39) {
				{
				{
				setState(475);
				annotation();
				}
				}
				setState(480);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(482);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & 7L) != 0)) {
				{
				setState(481);
				access();
				}
			}

			setState(485);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(484);
				modifier();
				}
				break;
			}
			setState(487);
			match(T__32);
			setState(489);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(488);
				genericDeclaration();
				}
			}

			setState(491);
			match(T__7);
			setState(493);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(492);
				parameterList();
				}
			}

			setState(495);
			match(T__8);
			setState(496);
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
		enterRule(_localctx, 78, RULE_operatorDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(499);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & 7L) != 0)) {
				{
				setState(498);
				access();
				}
			}

			setState(502);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(501);
				modifier();
				}
				break;
			}
			setState(504);
			match(T__33);
			setState(505);
			operator();
			setState(507);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(506);
				genericDeclaration();
				}
			}

			setState(509);
			match(T__7);
			setState(511);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(510);
				parameterList();
				}
			}

			setState(513);
			match(T__8);
			setState(516);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				setState(514);
				match(T__2);
				setState(515);
				type(0);
				}
				break;
			}
			setState(518);
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
		enterRule(_localctx, 80, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(520);
			parameter();
			setState(525);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(521);
				match(T__9);
				setState(522);
				parameter();
				}
				}
				setState(527);
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
		enterRule(_localctx, 82, RULE_parameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(528);
			match(IDENTIFIER);
			setState(529);
			match(T__2);
			setState(530);
			type(0);
			setState(533);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__28) {
				{
				setState(531);
				match(T__28);
				setState(532);
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
		enterRule(_localctx, 84, RULE_argumentList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(535);
			expression(0);
			setState(540);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(536);
				match(T__9);
				setState(537);
				expression(0);
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
	public static class ClassDeclarationContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
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
		public InheritanceContext inheritance() {
			return getRuleContext(InheritanceContext.class,0);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(546);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__39) {
				{
				{
				setState(543);
				annotation();
				}
				}
				setState(548);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(550);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & 7L) != 0)) {
				{
				setState(549);
				access();
				}
			}

			setState(553);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,65,_ctx) ) {
			case 1:
				{
				setState(552);
				modifier();
				}
				break;
			}
			setState(555);
			match(T__34);
			setState(556);
			match(IDENTIFIER);
			setState(558);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(557);
				genericDeclaration();
				}
			}

			setState(561);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(560);
				inheritance();
				}
			}

			setState(563);
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
		enterRule(_localctx, 88, RULE_inheritance);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(565);
			match(T__7);
			setState(566);
			inheritanceSpec();
			setState(571);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(567);
				match(T__9);
				setState(568);
				inheritanceSpec();
				}
				}
				setState(573);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(574);
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
		enterRule(_localctx, 90, RULE_inheritanceSpec);
		int _la;
		try {
			setState(593);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(576);
				type(0);
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 2);
				{
				setState(577);
				match(T__35);
				setState(578);
				match(T__28);
				setState(579);
				type(0);
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 3);
				{
				setState(580);
				match(T__36);
				setState(581);
				match(T__28);
				setState(582);
				match(T__4);
				setState(583);
				type(0);
				setState(588);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(584);
					match(T__9);
					setState(585);
					type(0);
					}
					}
					setState(590);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(591);
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
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public ModifierContext modifier() {
			return getRuleContext(ModifierContext.class,0);
		}
		public GenericDeclarationContext genericDeclaration() {
			return getRuleContext(GenericDeclarationContext.class,0);
		}
		public List<FunctionSignatureContext> functionSignature() {
			return getRuleContexts(FunctionSignatureContext.class);
		}
		public FunctionSignatureContext functionSignature(int i) {
			return getRuleContext(FunctionSignatureContext.class,i);
		}
		public InterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceDeclaration; }
	}

	public final InterfaceDeclarationContext interfaceDeclaration() throws RecognitionException {
		InterfaceDeclarationContext _localctx = new InterfaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_interfaceDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(596);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & 7L) != 0)) {
				{
				setState(595);
				access();
				}
			}

			setState(599);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				{
				setState(598);
				modifier();
				}
				break;
			}
			setState(601);
			match(T__37);
			setState(602);
			match(IDENTIFIER);
			setState(604);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(603);
				genericDeclaration();
				}
			}

			setState(606);
			match(T__0);
			setState(610);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 32)) & ~0x3f) == 0 && ((1L << (_la - 32)) & 69269232549889L) != 0)) {
				{
				{
				setState(607);
				functionSignature();
				}
				}
				setState(612);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(613);
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
	public static class FunctionSignatureContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
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
		enterRule(_localctx, 94, RULE_functionSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(616);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & 7L) != 0)) {
				{
				setState(615);
				access();
				}
			}

			setState(619);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
			case 1:
				{
				setState(618);
				modifier();
				}
				break;
			}
			setState(621);
			match(T__31);
			setState(623);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(622);
				genericDeclaration();
				}
			}

			setState(625);
			match(IDENTIFIER);
			setState(626);
			match(T__7);
			setState(628);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(627);
				parameterList();
				}
			}

			setState(630);
			match(T__8);
			{
			setState(631);
			match(T__2);
			setState(632);
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
		enterRule(_localctx, 96, RULE_annotationDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(637);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__39) {
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
			if (((((_la - 72)) & ~0x3f) == 0 && ((1L << (_la - 72)) & 7L) != 0)) {
				{
				setState(640);
				access();
				}
			}

			setState(644);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
			case 1:
				{
				setState(643);
				modifier();
				}
				break;
			}
			setState(646);
			match(T__38);
			setState(647);
			match(IDENTIFIER);
			setState(653);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
			case 1:
				{
				setState(648);
				match(T__7);
				setState(650);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDENTIFIER) {
					{
					setState(649);
					parameterList();
					}
				}

				setState(652);
				match(T__8);
				}
				break;
			}
			setState(656);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,84,_ctx) ) {
			case 1:
				{
				setState(655);
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
		enterRule(_localctx, 98, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(658);
			match(T__39);
			setState(659);
			match(IDENTIFIER);
			setState(667);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__7) {
				{
				setState(660);
				match(T__7);
				setState(663);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(661);
					annotationArgs();
					}
					break;
				case T__43:
				case T__44:
				case T__45:
				case NUMBER:
				case STRING:
					{
					setState(662);
					literal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(665);
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
		enterRule(_localctx, 100, RULE_annotationArgs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(669);
			annotationArg();
			setState(674);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(670);
				match(T__9);
				setState(671);
				annotationArg();
				}
				}
				setState(676);
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
		enterRule(_localctx, 102, RULE_annotationArg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(677);
			match(IDENTIFIER);
			setState(678);
			match(T__28);
			setState(679);
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
		enterRule(_localctx, 104, RULE_functionCall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(681);
			match(IDENTIFIER);
			setState(683);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(682);
				genericDeclaration();
				}
			}

			setState(685);
			match(T__7);
			setState(687);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 123145302311200L) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & 7183L) != 0)) {
				{
				setState(686);
				argumentList();
				}
			}

			setState(689);
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
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public GenericDeclarationContext genericDeclaration() {
			return getRuleContext(GenericDeclarationContext.class,0);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 106;
		enterRecursionRule(_localctx, 106, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(692);
			match(IDENTIFIER);
			setState(694);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
			case 1:
				{
				setState(693);
				genericDeclaration();
				}
				break;
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(705);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(696);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(699); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(697);
							match(T__40);
							setState(698);
							type(0);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(701); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					}
					} 
				}
				setState(707);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
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
		enterRule(_localctx, 108, RULE_genericDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(708);
			match(T__4);
			setState(709);
			generic();
			setState(714);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(710);
				match(T__9);
				setState(711);
				generic();
				}
				}
				setState(716);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(717);
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
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public TerminalNode IDENTIFIER() { return getToken(LumParser.IDENTIFIER, 0); }
		public GenericContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_generic; }
	}

	public final GenericContext generic() throws RecognitionException {
		GenericContext _localctx = new GenericContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_generic);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(724);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
			case 1:
				{
				setState(719);
				match(IDENTIFIER);
				setState(722);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__2:
				case T__35:
					{
					setState(720);
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
				case T__41:
					{
					setState(721);
					((GenericContext)_localctx).super_ = match(T__41);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
			setState(726);
			type(0);
			setState(731);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__42) {
				{
				{
				setState(727);
				match(T__42);
				setState(728);
				type(0);
				}
				}
				setState(733);
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
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(LumParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(LumParser.STRING, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(734);
			_la = _input.LA(1);
			if ( !(((((_la - 44)) & ~0x3f) == 0 && ((1L << (_la - 44)) & 103079215111L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
		enterRule(_localctx, 114, RULE_operator);
		try {
			setState(738);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__40:
			case T__42:
			case T__46:
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
				enterOuterAlt(_localctx, 1);
				{
				setState(736);
				binaryOperator();
				}
				break;
			case T__67:
			case T__68:
			case T__69:
			case T__70:
				enterOuterAlt(_localctx, 2);
				{
				setState(737);
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
		enterRule(_localctx, 116, RULE_binaryOperator);
		try {
			setState(763);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__46:
				_localctx = new MulContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(740);
				match(T__46);
				}
				break;
			case T__47:
				_localctx = new DivideContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(741);
				match(T__47);
				}
				break;
			case T__48:
				_localctx = new DIVContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(742);
				match(T__48);
				}
				break;
			case T__49:
				_localctx = new MODContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(743);
				match(T__49);
				}
				break;
			case T__50:
				_localctx = new AddContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(744);
				match(T__50);
				}
				break;
			case T__51:
				_localctx = new SubContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(745);
				match(T__51);
				}
				break;
			case T__52:
				_localctx = new SHRContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(746);
				match(T__52);
				}
				break;
			case T__53:
				_localctx = new SHLContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(747);
				match(T__53);
				}
				break;
			case T__42:
				_localctx = new ANDContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(748);
				match(T__42);
				}
				break;
			case T__54:
				_localctx = new XORContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(749);
				match(T__54);
				}
				break;
			case T__55:
				_localctx = new XORContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(750);
				match(T__55);
				}
				break;
			case T__40:
				_localctx = new ORContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(751);
				match(T__40);
				}
				break;
			case T__56:
				_localctx = new GTContext(_localctx);
				enterOuterAlt(_localctx, 13);
				{
				setState(752);
				match(T__56);
				}
				break;
			case T__57:
				_localctx = new LTContext(_localctx);
				enterOuterAlt(_localctx, 14);
				{
				setState(753);
				match(T__57);
				}
				break;
			case T__58:
				_localctx = new GEContext(_localctx);
				enterOuterAlt(_localctx, 15);
				{
				setState(754);
				match(T__58);
				}
				break;
			case T__59:
				_localctx = new LEContext(_localctx);
				enterOuterAlt(_localctx, 16);
				{
				setState(755);
				match(T__59);
				}
				break;
			case T__60:
				_localctx = new EQContext(_localctx);
				enterOuterAlt(_localctx, 17);
				{
				setState(756);
				match(T__60);
				}
				break;
			case T__61:
				_localctx = new NEQContext(_localctx);
				enterOuterAlt(_localctx, 18);
				{
				setState(757);
				match(T__61);
				}
				break;
			case T__62:
				_localctx = new IsInstanceContext(_localctx);
				enterOuterAlt(_localctx, 19);
				{
				setState(758);
				match(T__62);
				}
				break;
			case T__63:
				_localctx = new CANDContext(_localctx);
				enterOuterAlt(_localctx, 20);
				{
				setState(759);
				match(T__63);
				}
				break;
			case T__64:
				_localctx = new CANDContext(_localctx);
				enterOuterAlt(_localctx, 21);
				{
				setState(760);
				match(T__64);
				}
				break;
			case T__65:
				_localctx = new CORContext(_localctx);
				enterOuterAlt(_localctx, 22);
				{
				setState(761);
				match(T__65);
				}
				break;
			case T__66:
				_localctx = new CORContext(_localctx);
				enterOuterAlt(_localctx, 23);
				{
				setState(762);
				match(T__66);
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
		enterRule(_localctx, 118, RULE_unaryOperator);
		try {
			setState(769);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__67:
				_localctx = new IncrementContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(765);
				match(T__67);
				}
				break;
			case T__68:
				_localctx = new DecrementContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(766);
				match(T__68);
				}
				break;
			case T__69:
				_localctx = new NotContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(767);
				match(T__69);
				}
				break;
			case T__70:
				_localctx = new NotContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(768);
				match(T__70);
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
		enterRule(_localctx, 120, RULE_assignment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(772);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((((_la - 41)) & ~0x3f) == 0 && ((1L << (_la - 41)) & 2147483589L) != 0)) {
				{
				setState(771);
				operator();
				}
			}

			setState(774);
			match(T__28);
			setState(775);
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
		enterRule(_localctx, 122, RULE_access);
		try {
			setState(780);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__71:
				_localctx = new PublicContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(777);
				match(T__71);
				}
				break;
			case T__72:
				_localctx = new PrivateContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(778);
				match(T__72);
				}
				break;
			case T__73:
				_localctx = new ProtectedContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(779);
				match(T__73);
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
		enterRule(_localctx, 124, RULE_modifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(784);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__74:
				{
				setState(782);
				((ModifierContext)_localctx).static_ = match(T__74);
				}
				break;
			case T__75:
				{
				setState(783);
				((ModifierContext)_localctx).abstract_ = match(T__75);
				}
				break;
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__37:
			case T__38:
			case T__76:
			case IDENTIFIER:
				break;
			default:
				break;
			}
			setState(787);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__76) {
				{
				setState(786);
				((ModifierContext)_localctx).final_ = match(T__76);
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
		case 53:
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
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001R\u0316\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0001\u0000\u0005\u0000\u0080\b\u0000"+
		"\n\u0000\f\u0000\u0083\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001\u008f\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u0098\b\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0003\u0003\u009d\b\u0003\u0001\u0004\u0001"+
		"\u0004\u0005\u0004\u00a1\b\u0004\n\u0004\f\u0004\u00a4\t\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00ab\b\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005\u00b4\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005\u00c3\b\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005\u00c7\b\u0005\n\u0005\f\u0005\u00ca"+
		"\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006\u00d2\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006\u00d7\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0003\u0006\u00de\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0005"+
		"\u0007\u00e3\b\u0007\n\u0007\f\u0007\u00e6\t\u0007\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0005\b\u00ec\b\b\n\b\f\b\u00ef\t\b\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0003\t\u00f5\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0005\u000b\u00fe\b\u000b\n\u000b\f\u000b\u0101"+
		"\t\u000b\u0001\f\u0001\f\u0001\f\u0005\f\u0106\b\f\n\f\f\f\u0109\t\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0005\f\u010f\b\f\n\f\f\f\u0112\t\f\u0001\r"+
		"\u0001\r\u0001\r\u0005\r\u0117\b\r\n\r\f\r\u011a\t\r\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u011e\b\u000e\u0001\u000f\u0001\u000f\u0003\u000f\u0122"+
		"\b\u000f\u0001\u0010\u0001\u0010\u0003\u0010\u0126\b\u0010\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0005\u0011\u012b\b\u0011\n\u0011\f\u0011\u012e"+
		"\t\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u0134"+
		"\b\u0011\n\u0011\f\u0011\u0137\t\u0011\u0001\u0011\u0003\u0011\u013a\b"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u013f\b\u0011\n"+
		"\u0011\f\u0011\u0142\t\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u0146"+
		"\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012\u014c"+
		"\b\u0012\n\u0012\f\u0012\u014f\t\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0005\u0015\u015d\b\u0015\n\u0015"+
		"\f\u0015\u0160\t\u0015\u0001\u0015\u0003\u0015\u0163\b\u0015\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019"+
		"\u0003\u0019\u0172\b\u0019\u0001\u001a\u0001\u001a\u0003\u001a\u0176\b"+
		"\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0003"+
		"\u001d\u0183\b\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001"+
		" \u0001!\u0003!\u0198\b!\u0001!\u0003!\u019b\b!\u0001!\u0001!\u0001!\u0005"+
		"!\u01a0\b!\n!\f!\u01a3\t!\u0001\"\u0001\"\u0001\"\u0003\"\u01a8\b\"\u0001"+
		"\"\u0001\"\u0003\"\u01ac\b\"\u0001\"\u0001\"\u0005\"\u01b0\b\"\n\"\f\""+
		"\u01b3\t\"\u0001#\u0001#\u0003#\u01b7\b#\u0001$\u0001$\u0001$\u0001$\u0003"+
		"$\u01bd\b$\u0001%\u0005%\u01c0\b%\n%\f%\u01c3\t%\u0001%\u0003%\u01c6\b"+
		"%\u0001%\u0003%\u01c9\b%\u0001%\u0001%\u0001%\u0003%\u01ce\b%\u0001%\u0001"+
		"%\u0003%\u01d2\b%\u0001%\u0001%\u0001%\u0003%\u01d7\b%\u0001%\u0003%\u01da"+
		"\b%\u0001&\u0005&\u01dd\b&\n&\f&\u01e0\t&\u0001&\u0003&\u01e3\b&\u0001"+
		"&\u0003&\u01e6\b&\u0001&\u0001&\u0003&\u01ea\b&\u0001&\u0001&\u0003&\u01ee"+
		"\b&\u0001&\u0001&\u0001&\u0001\'\u0003\'\u01f4\b\'\u0001\'\u0003\'\u01f7"+
		"\b\'\u0001\'\u0001\'\u0001\'\u0003\'\u01fc\b\'\u0001\'\u0001\'\u0003\'"+
		"\u0200\b\'\u0001\'\u0001\'\u0001\'\u0003\'\u0205\b\'\u0001\'\u0001\'\u0001"+
		"(\u0001(\u0001(\u0005(\u020c\b(\n(\f(\u020f\t(\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0003)\u0216\b)\u0001*\u0001*\u0001*\u0005*\u021b\b*\n*\f*\u021e"+
		"\t*\u0001+\u0005+\u0221\b+\n+\f+\u0224\t+\u0001+\u0003+\u0227\b+\u0001"+
		"+\u0003+\u022a\b+\u0001+\u0001+\u0001+\u0003+\u022f\b+\u0001+\u0003+\u0232"+
		"\b+\u0001+\u0001+\u0001,\u0001,\u0001,\u0001,\u0005,\u023a\b,\n,\f,\u023d"+
		"\t,\u0001,\u0001,\u0001-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001-\u0001"+
		"-\u0001-\u0001-\u0005-\u024b\b-\n-\f-\u024e\t-\u0001-\u0001-\u0003-\u0252"+
		"\b-\u0001.\u0003.\u0255\b.\u0001.\u0003.\u0258\b.\u0001.\u0001.\u0001"+
		".\u0003.\u025d\b.\u0001.\u0001.\u0005.\u0261\b.\n.\f.\u0264\t.\u0001."+
		"\u0001.\u0001/\u0003/\u0269\b/\u0001/\u0003/\u026c\b/\u0001/\u0001/\u0003"+
		"/\u0270\b/\u0001/\u0001/\u0001/\u0003/\u0275\b/\u0001/\u0001/\u0001/\u0001"+
		"/\u00010\u00050\u027c\b0\n0\f0\u027f\t0\u00010\u00030\u0282\b0\u00010"+
		"\u00030\u0285\b0\u00010\u00010\u00010\u00010\u00030\u028b\b0\u00010\u0003"+
		"0\u028e\b0\u00010\u00030\u0291\b0\u00011\u00011\u00011\u00011\u00011\u0003"+
		"1\u0298\b1\u00011\u00011\u00031\u029c\b1\u00012\u00012\u00012\u00052\u02a1"+
		"\b2\n2\f2\u02a4\t2\u00013\u00013\u00013\u00013\u00014\u00014\u00034\u02ac"+
		"\b4\u00014\u00014\u00034\u02b0\b4\u00014\u00014\u00015\u00015\u00015\u0003"+
		"5\u02b7\b5\u00015\u00015\u00015\u00045\u02bc\b5\u000b5\f5\u02bd\u0005"+
		"5\u02c0\b5\n5\f5\u02c3\t5\u00016\u00016\u00016\u00016\u00056\u02c9\b6"+
		"\n6\f6\u02cc\t6\u00016\u00016\u00017\u00017\u00017\u00037\u02d3\b7\u0003"+
		"7\u02d5\b7\u00017\u00017\u00017\u00057\u02da\b7\n7\f7\u02dd\t7\u00018"+
		"\u00018\u00019\u00019\u00039\u02e3\b9\u0001:\u0001:\u0001:\u0001:\u0001"+
		":\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001"+
		":\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0001:\u0003:\u02fc"+
		"\b:\u0001;\u0001;\u0001;\u0001;\u0003;\u0302\b;\u0001<\u0003<\u0305\b"+
		"<\u0001<\u0001<\u0001<\u0001=\u0001=\u0001=\u0003=\u030d\b=\u0001>\u0001"+
		">\u0003>\u0311\b>\u0001>\u0003>\u0314\b>\u0001>\u0000\u0002\nj?\u0000"+
		"\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c"+
		"\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|\u0000\u0004\u0002"+
		"\u0000NNPP\u0001\u0000\u001c\u001d\u0002\u0000\u0003\u0003$$\u0002\u0000"+
		",.OP\u036e\u0000\u0081\u0001\u0000\u0000\u0000\u0002\u008e\u0001\u0000"+
		"\u0000\u0000\u0004\u0097\u0001\u0000\u0000\u0000\u0006\u009c\u0001\u0000"+
		"\u0000\u0000\b\u00aa\u0001\u0000\u0000\u0000\n\u00b3\u0001\u0000\u0000"+
		"\u0000\f\u00dd\u0001\u0000\u0000\u0000\u000e\u00df\u0001\u0000\u0000\u0000"+
		"\u0010\u00e7\u0001\u0000\u0000\u0000\u0012\u00f0\u0001\u0000\u0000\u0000"+
		"\u0014\u00f6\u0001\u0000\u0000\u0000\u0016\u00fa\u0001\u0000\u0000\u0000"+
		"\u0018\u0102\u0001\u0000\u0000\u0000\u001a\u0113\u0001\u0000\u0000\u0000"+
		"\u001c\u011b\u0001\u0000\u0000\u0000\u001e\u011f\u0001\u0000\u0000\u0000"+
		" \u0123\u0001\u0000\u0000\u0000\"\u0139\u0001\u0000\u0000\u0000$\u0147"+
		"\u0001\u0000\u0000\u0000&\u0153\u0001\u0000\u0000\u0000(\u0157\u0001\u0000"+
		"\u0000\u0000*\u015a\u0001\u0000\u0000\u0000,\u0164\u0001\u0000\u0000\u0000"+
		".\u0168\u0001\u0000\u0000\u00000\u016c\u0001\u0000\u0000\u00002\u0171"+
		"\u0001\u0000\u0000\u00004\u0175\u0001\u0000\u0000\u00006\u0177\u0001\u0000"+
		"\u0000\u00008\u017b\u0001\u0000\u0000\u0000:\u0182\u0001\u0000\u0000\u0000"+
		"<\u0184\u0001\u0000\u0000\u0000>\u018c\u0001\u0000\u0000\u0000@\u0192"+
		"\u0001\u0000\u0000\u0000B\u0197\u0001\u0000\u0000\u0000D\u01a4\u0001\u0000"+
		"\u0000\u0000F\u01b4\u0001\u0000\u0000\u0000H\u01b8\u0001\u0000\u0000\u0000"+
		"J\u01c1\u0001\u0000\u0000\u0000L\u01de\u0001\u0000\u0000\u0000N\u01f3"+
		"\u0001\u0000\u0000\u0000P\u0208\u0001\u0000\u0000\u0000R\u0210\u0001\u0000"+
		"\u0000\u0000T\u0217\u0001\u0000\u0000\u0000V\u0222\u0001\u0000\u0000\u0000"+
		"X\u0235\u0001\u0000\u0000\u0000Z\u0251\u0001\u0000\u0000\u0000\\\u0254"+
		"\u0001\u0000\u0000\u0000^\u0268\u0001\u0000\u0000\u0000`\u027d\u0001\u0000"+
		"\u0000\u0000b\u0292\u0001\u0000\u0000\u0000d\u029d\u0001\u0000\u0000\u0000"+
		"f\u02a5\u0001\u0000\u0000\u0000h\u02a9\u0001\u0000\u0000\u0000j\u02b3"+
		"\u0001\u0000\u0000\u0000l\u02c4\u0001\u0000\u0000\u0000n\u02d4\u0001\u0000"+
		"\u0000\u0000p\u02de\u0001\u0000\u0000\u0000r\u02e2\u0001\u0000\u0000\u0000"+
		"t\u02fb\u0001\u0000\u0000\u0000v\u0301\u0001\u0000\u0000\u0000x\u0304"+
		"\u0001\u0000\u0000\u0000z\u030c\u0001\u0000\u0000\u0000|\u0310\u0001\u0000"+
		"\u0000\u0000~\u0080\u0003\u0002\u0001\u0000\u007f~\u0001\u0000\u0000\u0000"+
		"\u0080\u0083\u0001\u0000\u0000\u0000\u0081\u007f\u0001\u0000\u0000\u0000"+
		"\u0081\u0082\u0001\u0000\u0000\u0000\u0082\u0084\u0001\u0000\u0000\u0000"+
		"\u0083\u0081\u0001\u0000\u0000\u0000\u0084\u0085\u0005\u0000\u0000\u0001"+
		"\u0085\u0001\u0001\u0000\u0000\u0000\u0086\u008f\u0003\u0010\b\u0000\u0087"+
		"\u008f\u0003\u0012\t\u0000\u0088\u008f\u0003\u0004\u0002\u0000\u0089\u008f"+
		"\u0003\u0006\u0003\u0000\u008a\u008f\u0003\n\u0005\u0000\u008b\u008f\u0003"+
		"\u001c\u000e\u0000\u008c\u008f\u0003\u001e\u000f\u0000\u008d\u008f\u0003"+
		" \u0010\u0000\u008e\u0086\u0001\u0000\u0000\u0000\u008e\u0087\u0001\u0000"+
		"\u0000\u0000\u008e\u0088\u0001\u0000\u0000\u0000\u008e\u0089\u0001\u0000"+
		"\u0000\u0000\u008e\u008a\u0001\u0000\u0000\u0000\u008e\u008b\u0001\u0000"+
		"\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008d\u0001\u0000"+
		"\u0000\u0000\u008f\u0003\u0001\u0000\u0000\u0000\u0090\u0098\u0003J%\u0000"+
		"\u0091\u0098\u0003B!\u0000\u0092\u0098\u0003N\'\u0000\u0093\u0098\u0003"+
		"L&\u0000\u0094\u0098\u0003V+\u0000\u0095\u0098\u0003\\.\u0000\u0096\u0098"+
		"\u0003`0\u0000\u0097\u0090\u0001\u0000\u0000\u0000\u0097\u0091\u0001\u0000"+
		"\u0000\u0000\u0097\u0092\u0001\u0000\u0000\u0000\u0097\u0093\u0001\u0000"+
		"\u0000\u0000\u0097\u0094\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000"+
		"\u0000\u0000\u0097\u0096\u0001\u0000\u0000\u0000\u0098\u0005\u0001\u0000"+
		"\u0000\u0000\u0099\u009d\u0003*\u0015\u0000\u009a\u009d\u0003$\u0012\u0000"+
		"\u009b\u009d\u00032\u0019\u0000\u009c\u0099\u0001\u0000\u0000\u0000\u009c"+
		"\u009a\u0001\u0000\u0000\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009d"+
		"\u0007\u0001\u0000\u0000\u0000\u009e\u00a2\u0005\u0001\u0000\u0000\u009f"+
		"\u00a1\u0003\u0002\u0001\u0000\u00a0\u009f\u0001\u0000\u0000\u0000\u00a1"+
		"\u00a4\u0001\u0000\u0000\u0000\u00a2\u00a0\u0001\u0000\u0000\u0000\u00a2"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a5\u0001\u0000\u0000\u0000\u00a4"+
		"\u00a2\u0001\u0000\u0000\u0000\u00a5\u00ab\u0005\u0002\u0000\u0000\u00a6"+
		"\u00a7\u0005\u0003\u0000\u0000\u00a7\u00ab\u0003\u0002\u0001\u0000\u00a8"+
		"\u00a9\u0005\u0004\u0000\u0000\u00a9\u00ab\u0003\u0002\u0001\u0000\u00aa"+
		"\u009e\u0001\u0000\u0000\u0000\u00aa\u00a6\u0001\u0000\u0000\u0000\u00aa"+
		"\u00a8\u0001\u0000\u0000\u0000\u00ab\t\u0001\u0000\u0000\u0000\u00ac\u00ad"+
		"\u0006\u0005\uffff\uffff\u0000\u00ad\u00b4\u0003\f\u0006\u0000\u00ae\u00b4"+
		"\u0003\"\u0011\u0000\u00af\u00b4\u0003h4\u0000\u00b0\u00b1\u0003v;\u0000"+
		"\u00b1\u00b2\u0003\n\u0005\u0002\u00b2\u00b4\u0001\u0000\u0000\u0000\u00b3"+
		"\u00ac\u0001\u0000\u0000\u0000\u00b3\u00ae\u0001\u0000\u0000\u0000\u00b3"+
		"\u00af\u0001\u0000\u0000\u0000\u00b3\u00b0\u0001\u0000\u0000\u0000\u00b4"+
		"\u00c8\u0001\u0000\u0000\u0000\u00b5\u00b6\n\u0001\u0000\u0000\u00b6\u00b7"+
		"\u0003t:\u0000\u00b7\u00b8\u0003\n\u0005\u0002\u00b8\u00c7\u0001\u0000"+
		"\u0000\u0000\u00b9\u00ba\n\u0005\u0000\u0000\u00ba\u00bb\u0005\u0005\u0000"+
		"\u0000\u00bb\u00bc\u0003\n\u0005\u0000\u00bc\u00bd\u0005\u0006\u0000\u0000"+
		"\u00bd\u00c7\u0001\u0000\u0000\u0000\u00be\u00bf\n\u0004\u0000\u0000\u00bf"+
		"\u00c2\u0005\u0007\u0000\u0000\u00c0\u00c3\u0005N\u0000\u0000\u00c1\u00c3"+
		"\u0003h4\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c2\u00c1\u0001\u0000"+
		"\u0000\u0000\u00c3\u00c7\u0001\u0000\u0000\u0000\u00c4\u00c5\n\u0003\u0000"+
		"\u0000\u00c5\u00c7\u0003v;\u0000\u00c6\u00b5\u0001\u0000\u0000\u0000\u00c6"+
		"\u00b9\u0001\u0000\u0000\u0000\u00c6\u00be\u0001\u0000\u0000\u0000\u00c6"+
		"\u00c4\u0001\u0000\u0000\u0000\u00c7\u00ca\u0001\u0000\u0000\u0000\u00c8"+
		"\u00c6\u0001\u0000\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000\u00c9"+
		"\u000b\u0001\u0000\u0000\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00cb"+
		"\u00de\u0003p8\u0000\u00cc\u00de\u0005N\u0000\u0000\u00cd\u00ce\u0005"+
		"N\u0000\u0000\u00ce\u00de\u0003x<\u0000\u00cf\u00d1\u0005\u0005\u0000"+
		"\u0000\u00d0\u00d2\u0003T*\u0000\u00d1\u00d0\u0001\u0000\u0000\u0000\u00d1"+
		"\u00d2\u0001\u0000\u0000\u0000\u00d2\u00d3\u0001\u0000\u0000\u0000\u00d3"+
		"\u00de\u0005\u0006\u0000\u0000\u00d4\u00d6\u0005\u0005\u0000\u0000\u00d5"+
		"\u00d7\u0003\u000e\u0007\u0000\u00d6\u00d5\u0001\u0000\u0000\u0000\u00d6"+
		"\u00d7\u0001\u0000\u0000\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000\u00d8"+
		"\u00de\u0005\u0006\u0000\u0000\u00d9\u00da\u0005\b\u0000\u0000\u00da\u00db"+
		"\u0003\n\u0005\u0000\u00db\u00dc\u0005\t\u0000\u0000\u00dc\u00de\u0001"+
		"\u0000\u0000\u0000\u00dd\u00cb\u0001\u0000\u0000\u0000\u00dd\u00cc\u0001"+
		"\u0000\u0000\u0000\u00dd\u00cd\u0001\u0000\u0000\u0000\u00dd\u00cf\u0001"+
		"\u0000\u0000\u0000\u00dd\u00d4\u0001\u0000\u0000\u0000\u00dd\u00d9\u0001"+
		"\u0000\u0000\u0000\u00de\r\u0001\u0000\u0000\u0000\u00df\u00e4\u0003@"+
		" \u0000\u00e0\u00e1\u0005\n\u0000\u0000\u00e1\u00e3\u0003@ \u0000\u00e2"+
		"\u00e0\u0001\u0000\u0000\u0000\u00e3\u00e6\u0001\u0000\u0000\u0000\u00e4"+
		"\u00e2\u0001\u0000\u0000\u0000\u00e4\u00e5\u0001\u0000\u0000\u0000\u00e5"+
		"\u000f\u0001\u0000\u0000\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e7"+
		"\u00e8\u0005\u000b\u0000\u0000\u00e8\u00ed\u0005N\u0000\u0000\u00e9\u00ea"+
		"\u0005\u0007\u0000\u0000\u00ea\u00ec\u0005N\u0000\u0000\u00eb\u00e9\u0001"+
		"\u0000\u0000\u0000\u00ec\u00ef\u0001\u0000\u0000\u0000\u00ed\u00eb\u0001"+
		"\u0000\u0000\u0000\u00ed\u00ee\u0001\u0000\u0000\u0000\u00ee\u0011\u0001"+
		"\u0000\u0000\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000\u00f0\u00f4\u0005"+
		"\f\u0000\u0000\u00f1\u00f5\u0003\u0016\u000b\u0000\u00f2\u00f5\u0003\u0014"+
		"\n\u0000\u00f3\u00f5\u0003\u0018\f\u0000\u00f4\u00f1\u0001\u0000\u0000"+
		"\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f4\u00f3\u0001\u0000\u0000"+
		"\u0000\u00f5\u0013\u0001\u0000\u0000\u0000\u00f6\u00f7\u0003\u0016\u000b"+
		"\u0000\u00f7\u00f8\u0005\r\u0000\u0000\u00f8\u00f9\u0005N\u0000\u0000"+
		"\u00f9\u0015\u0001\u0000\u0000\u0000\u00fa\u00ff\u0005N\u0000\u0000\u00fb"+
		"\u00fc\u0005\u0007\u0000\u0000\u00fc\u00fe\u0005N\u0000\u0000\u00fd\u00fb"+
		"\u0001\u0000\u0000\u0000\u00fe\u0101\u0001\u0000\u0000\u0000\u00ff\u00fd"+
		"\u0001\u0000\u0000\u0000\u00ff\u0100\u0001\u0000\u0000\u0000\u0100\u0017"+
		"\u0001\u0000\u0000\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0102\u0107"+
		"\u0003\u0014\n\u0000\u0103\u0104\u0005\n\u0000\u0000\u0104\u0106\u0003"+
		"\u0014\n\u0000\u0105\u0103\u0001\u0000\u0000\u0000\u0106\u0109\u0001\u0000"+
		"\u0000\u0000\u0107\u0105\u0001\u0000\u0000\u0000\u0107\u0108\u0001\u0000"+
		"\u0000\u0000\u0108\u010a\u0001\u0000\u0000\u0000\u0109\u0107\u0001\u0000"+
		"\u0000\u0000\u010a\u010b\u0005\u000e\u0000\u0000\u010b\u0110\u0005N\u0000"+
		"\u0000\u010c\u010d\u0005\u0007\u0000\u0000\u010d\u010f\u0005N\u0000\u0000"+
		"\u010e\u010c\u0001\u0000\u0000\u0000\u010f\u0112\u0001\u0000\u0000\u0000"+
		"\u0110\u010e\u0001\u0000\u0000\u0000\u0110\u0111\u0001\u0000\u0000\u0000"+
		"\u0111\u0019\u0001\u0000\u0000\u0000\u0112\u0110\u0001\u0000\u0000\u0000"+
		"\u0113\u0118\u0003\u0016\u000b\u0000\u0114\u0115\u0005\n\u0000\u0000\u0115"+
		"\u0117\u0003\u0016\u000b\u0000\u0116\u0114\u0001\u0000\u0000\u0000\u0117"+
		"\u011a\u0001\u0000\u0000\u0000\u0118\u0116\u0001\u0000\u0000\u0000\u0118"+
		"\u0119\u0001\u0000\u0000\u0000\u0119\u001b\u0001\u0000\u0000\u0000\u011a"+
		"\u0118\u0001\u0000\u0000\u0000\u011b\u011d\u0005\u000f\u0000\u0000\u011c"+
		"\u011e\u0005N\u0000\u0000\u011d\u011c\u0001\u0000\u0000\u0000\u011d\u011e"+
		"\u0001\u0000\u0000\u0000\u011e\u001d\u0001\u0000\u0000\u0000\u011f\u0121"+
		"\u0005\u0010\u0000\u0000\u0120\u0122\u0005N\u0000\u0000\u0121\u0120\u0001"+
		"\u0000\u0000\u0000\u0121\u0122\u0001\u0000\u0000\u0000\u0122\u001f\u0001"+
		"\u0000\u0000\u0000\u0123\u0125\u0005\u0011\u0000\u0000\u0124\u0126\u0003"+
		"\n\u0005\u0000\u0125\u0124\u0001\u0000\u0000\u0000\u0125\u0126\u0001\u0000"+
		"\u0000\u0000\u0126!\u0001\u0000\u0000\u0000\u0127\u012c\u0005N\u0000\u0000"+
		"\u0128\u0129\u0005\n\u0000\u0000\u0129\u012b\u0005N\u0000\u0000\u012a"+
		"\u0128\u0001\u0000\u0000\u0000\u012b\u012e\u0001\u0000\u0000\u0000\u012c"+
		"\u012a\u0001\u0000\u0000\u0000\u012c\u012d\u0001\u0000\u0000\u0000\u012d"+
		"\u013a\u0001\u0000\u0000\u0000\u012e\u012c\u0001\u0000\u0000\u0000\u012f"+
		"\u0130\u0005\b\u0000\u0000\u0130\u0135\u0005N\u0000\u0000\u0131\u0132"+
		"\u0005\n\u0000\u0000\u0132\u0134\u0005N\u0000\u0000\u0133\u0131\u0001"+
		"\u0000\u0000\u0000\u0134\u0137\u0001\u0000\u0000\u0000\u0135\u0133\u0001"+
		"\u0000\u0000\u0000\u0135\u0136\u0001\u0000\u0000\u0000\u0136\u0138\u0001"+
		"\u0000\u0000\u0000\u0137\u0135\u0001\u0000\u0000\u0000\u0138\u013a\u0005"+
		"\t\u0000\u0000\u0139\u0127\u0001\u0000\u0000\u0000\u0139\u012f\u0001\u0000"+
		"\u0000\u0000\u013a\u013b\u0001\u0000\u0000\u0000\u013b\u0145\u0005\u0004"+
		"\u0000\u0000\u013c\u0140\u0005\u0001\u0000\u0000\u013d\u013f\u0003\u0002"+
		"\u0001\u0000\u013e\u013d\u0001\u0000\u0000\u0000\u013f\u0142\u0001\u0000"+
		"\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0140\u0141\u0001\u0000"+
		"\u0000\u0000\u0141\u0143\u0001\u0000\u0000\u0000\u0142\u0140\u0001\u0000"+
		"\u0000\u0000\u0143\u0146\u0005\u0002\u0000\u0000\u0144\u0146\u0003\u0002"+
		"\u0001\u0000\u0145\u013c\u0001\u0000\u0000\u0000\u0145\u0144\u0001\u0000"+
		"\u0000\u0000\u0146#\u0001\u0000\u0000\u0000\u0147\u0148\u0005\u0012\u0000"+
		"\u0000\u0148\u0149\u0003\n\u0005\u0000\u0149\u014d\u0005\u0001\u0000\u0000"+
		"\u014a\u014c\u0003&\u0013\u0000\u014b\u014a\u0001\u0000\u0000\u0000\u014c"+
		"\u014f\u0001\u0000\u0000\u0000\u014d\u014b\u0001\u0000\u0000\u0000\u014d"+
		"\u014e\u0001\u0000\u0000\u0000\u014e\u0150\u0001\u0000\u0000\u0000\u014f"+
		"\u014d\u0001\u0000\u0000\u0000\u0150\u0151\u0003(\u0014\u0000\u0151\u0152"+
		"\u0005\u0002\u0000\u0000\u0152%\u0001\u0000\u0000\u0000\u0153\u0154\u0005"+
		"\u0013\u0000\u0000\u0154\u0155\u0003\n\u0005\u0000\u0155\u0156\u0003\b"+
		"\u0004\u0000\u0156\'\u0001\u0000\u0000\u0000\u0157\u0158\u0005\u0014\u0000"+
		"\u0000\u0158\u0159\u0003\b\u0004\u0000\u0159)\u0001\u0000\u0000\u0000"+
		"\u015a\u015e\u0003,\u0016\u0000\u015b\u015d\u0003.\u0017\u0000\u015c\u015b"+
		"\u0001\u0000\u0000\u0000\u015d\u0160\u0001\u0000\u0000\u0000\u015e\u015c"+
		"\u0001\u0000\u0000\u0000\u015e\u015f\u0001\u0000\u0000\u0000\u015f\u0162"+
		"\u0001\u0000\u0000\u0000\u0160\u015e\u0001\u0000\u0000\u0000\u0161\u0163"+
		"\u00030\u0018\u0000\u0162\u0161\u0001\u0000\u0000\u0000\u0162\u0163\u0001"+
		"\u0000\u0000\u0000\u0163+\u0001\u0000\u0000\u0000\u0164\u0165\u0005\u0015"+
		"\u0000\u0000\u0165\u0166\u0003\n\u0005\u0000\u0166\u0167\u0003\b\u0004"+
		"\u0000\u0167-\u0001\u0000\u0000\u0000\u0168\u0169\u0005\u0016\u0000\u0000"+
		"\u0169\u016a\u0003\n\u0005\u0000\u016a\u016b\u0003\b\u0004\u0000\u016b"+
		"/\u0001\u0000\u0000\u0000\u016c\u016d\u0005\u0017\u0000\u0000\u016d\u016e"+
		"\u0003\b\u0004\u0000\u016e1\u0001\u0000\u0000\u0000\u016f\u0172\u0003"+
		"4\u001a\u0000\u0170\u0172\u0003:\u001d\u0000\u0171\u016f\u0001\u0000\u0000"+
		"\u0000\u0171\u0170\u0001\u0000\u0000\u0000\u01723\u0001\u0000\u0000\u0000"+
		"\u0173\u0176\u00036\u001b\u0000\u0174\u0176\u00038\u001c\u0000\u0175\u0173"+
		"\u0001\u0000\u0000\u0000\u0175\u0174\u0001\u0000\u0000\u0000\u01765\u0001"+
		"\u0000\u0000\u0000\u0177\u0178\u0005\u0018\u0000\u0000\u0178\u0179\u0003"+
		"\n\u0005\u0000\u0179\u017a\u0003\b\u0004\u0000\u017a7\u0001\u0000\u0000"+
		"\u0000\u017b\u017c\u0005\u0019\u0000\u0000\u017c\u017d\u0003\b\u0004\u0000"+
		"\u017d\u017e\u0005\u0018\u0000\u0000\u017e\u017f\u0003\n\u0005\u0000\u017f"+
		"9\u0001\u0000\u0000\u0000\u0180\u0183\u0003<\u001e\u0000\u0181\u0183\u0003"+
		">\u001f\u0000\u0182\u0180\u0001\u0000\u0000\u0000\u0182\u0181\u0001\u0000"+
		"\u0000\u0000\u0183;\u0001\u0000\u0000\u0000\u0184\u0185\u0005\u001a\u0000"+
		"\u0000\u0185\u0186\u0003B!\u0000\u0186\u0187\u0005\n\u0000\u0000\u0187"+
		"\u0188\u0003\n\u0005\u0000\u0188\u0189\u0005\n\u0000\u0000\u0189\u018a"+
		"\u0003\n\u0005\u0000\u018a\u018b\u0003\b\u0004\u0000\u018b=\u0001\u0000"+
		"\u0000\u0000\u018c\u018d\u0005\u001a\u0000\u0000\u018d\u018e\u0003D\""+
		"\u0000\u018e\u018f\u0005\u001b\u0000\u0000\u018f\u0190\u0003\n\u0005\u0000"+
		"\u0190\u0191\u0003\b\u0004\u0000\u0191?\u0001\u0000\u0000\u0000\u0192"+
		"\u0193\u0007\u0000\u0000\u0000\u0193\u0194\u0005\u0003\u0000\u0000\u0194"+
		"\u0195\u0003\n\u0005\u0000\u0195A\u0001\u0000\u0000\u0000\u0196\u0198"+
		"\u0003z=\u0000\u0197\u0196\u0001\u0000\u0000\u0000\u0197\u0198\u0001\u0000"+
		"\u0000\u0000\u0198\u019a\u0001\u0000\u0000\u0000\u0199\u019b\u0003|>\u0000"+
		"\u019a\u0199\u0001\u0000\u0000\u0000\u019a\u019b\u0001\u0000\u0000\u0000"+
		"\u019b\u019c\u0001\u0000\u0000\u0000\u019c\u01a1\u0003D\"\u0000\u019d"+
		"\u019e\u0005\n\u0000\u0000\u019e\u01a0\u0003D\"\u0000\u019f\u019d\u0001"+
		"\u0000\u0000\u0000\u01a0\u01a3\u0001\u0000\u0000\u0000\u01a1\u019f\u0001"+
		"\u0000\u0000\u0000\u01a1\u01a2\u0001\u0000\u0000\u0000\u01a2C\u0001\u0000"+
		"\u0000\u0000\u01a3\u01a1\u0001\u0000\u0000\u0000\u01a4\u01a7\u0005N\u0000"+
		"\u0000\u01a5\u01a6\u0005\u0003\u0000\u0000\u01a6\u01a8\u0003j5\u0000\u01a7"+
		"\u01a5\u0001\u0000\u0000\u0000\u01a7\u01a8\u0001\u0000\u0000\u0000\u01a8"+
		"\u01ab\u0001\u0000\u0000\u0000\u01a9\u01aa\u0007\u0001\u0000\u0000\u01aa"+
		"\u01ac\u0003\n\u0005\u0000\u01ab\u01a9\u0001\u0000\u0000\u0000\u01ab\u01ac"+
		"\u0001\u0000\u0000\u0000\u01ac\u01b1\u0001\u0000\u0000\u0000\u01ad\u01b0"+
		"\u0003F#\u0000\u01ae\u01b0\u0003H$\u0000\u01af\u01ad\u0001\u0000\u0000"+
		"\u0000\u01af\u01ae\u0001\u0000\u0000\u0000\u01b0\u01b3\u0001\u0000\u0000"+
		"\u0000\u01b1\u01af\u0001\u0000\u0000\u0000\u01b1\u01b2\u0001\u0000\u0000"+
		"\u0000\u01b2E\u0001\u0000\u0000\u0000\u01b3\u01b1\u0001\u0000\u0000\u0000"+
		"\u01b4\u01b6\u0005\u001e\u0000\u0000\u01b5\u01b7\u0003\b\u0004\u0000\u01b6"+
		"\u01b5\u0001\u0000\u0000\u0000\u01b6\u01b7\u0001\u0000\u0000\u0000\u01b7"+
		"G\u0001\u0000\u0000\u0000\u01b8\u01bc\u0005\u001f\u0000\u0000\u01b9\u01ba"+
		"\u0003R)\u0000\u01ba\u01bb\u0003\b\u0004\u0000\u01bb\u01bd\u0001\u0000"+
		"\u0000\u0000\u01bc\u01b9\u0001\u0000\u0000\u0000\u01bc\u01bd\u0001\u0000"+
		"\u0000\u0000\u01bdI\u0001\u0000\u0000\u0000\u01be\u01c0\u0003b1\u0000"+
		"\u01bf\u01be\u0001\u0000\u0000\u0000\u01c0\u01c3\u0001\u0000\u0000\u0000"+
		"\u01c1\u01bf\u0001\u0000\u0000\u0000\u01c1\u01c2\u0001\u0000\u0000\u0000"+
		"\u01c2\u01c5\u0001\u0000\u0000\u0000\u01c3\u01c1\u0001\u0000\u0000\u0000"+
		"\u01c4\u01c6\u0003z=\u0000\u01c5\u01c4\u0001\u0000\u0000\u0000\u01c5\u01c6"+
		"\u0001\u0000\u0000\u0000\u01c6\u01c8\u0001\u0000\u0000\u0000\u01c7\u01c9"+
		"\u0003|>\u0000\u01c8\u01c7\u0001\u0000\u0000\u0000\u01c8\u01c9\u0001\u0000"+
		"\u0000\u0000\u01c9\u01ca\u0001\u0000\u0000\u0000\u01ca\u01cb\u0005 \u0000"+
		"\u0000\u01cb\u01cd\u0005N\u0000\u0000\u01cc\u01ce\u0003l6\u0000\u01cd"+
		"\u01cc\u0001\u0000\u0000\u0000\u01cd\u01ce\u0001\u0000\u0000\u0000\u01ce"+
		"\u01cf\u0001\u0000\u0000\u0000\u01cf\u01d1\u0005\b\u0000\u0000\u01d0\u01d2"+
		"\u0003P(\u0000\u01d1\u01d0\u0001\u0000\u0000\u0000\u01d1\u01d2\u0001\u0000"+
		"\u0000\u0000\u01d2\u01d3\u0001\u0000\u0000\u0000\u01d3\u01d6\u0005\t\u0000"+
		"\u0000\u01d4\u01d5\u0005\u0003\u0000\u0000\u01d5\u01d7\u0003j5\u0000\u01d6"+
		"\u01d4\u0001\u0000\u0000\u0000\u01d6\u01d7\u0001\u0000\u0000\u0000\u01d7"+
		"\u01d9\u0001\u0000\u0000\u0000\u01d8\u01da\u0003\b\u0004\u0000\u01d9\u01d8"+
		"\u0001\u0000\u0000\u0000\u01d9\u01da\u0001\u0000\u0000\u0000\u01daK\u0001"+
		"\u0000\u0000\u0000\u01db\u01dd\u0003b1\u0000\u01dc\u01db\u0001\u0000\u0000"+
		"\u0000\u01dd\u01e0\u0001\u0000\u0000\u0000\u01de\u01dc\u0001\u0000\u0000"+
		"\u0000\u01de\u01df\u0001\u0000\u0000\u0000\u01df\u01e2\u0001\u0000\u0000"+
		"\u0000\u01e0\u01de\u0001\u0000\u0000\u0000\u01e1\u01e3\u0003z=\u0000\u01e2"+
		"\u01e1\u0001\u0000\u0000\u0000\u01e2\u01e3\u0001\u0000\u0000\u0000\u01e3"+
		"\u01e5\u0001\u0000\u0000\u0000\u01e4\u01e6\u0003|>\u0000\u01e5\u01e4\u0001"+
		"\u0000\u0000\u0000\u01e5\u01e6\u0001\u0000\u0000\u0000\u01e6\u01e7\u0001"+
		"\u0000\u0000\u0000\u01e7\u01e9\u0005!\u0000\u0000\u01e8\u01ea\u0003l6"+
		"\u0000\u01e9\u01e8\u0001\u0000\u0000\u0000\u01e9\u01ea\u0001\u0000\u0000"+
		"\u0000\u01ea\u01eb\u0001\u0000\u0000\u0000\u01eb\u01ed\u0005\b\u0000\u0000"+
		"\u01ec\u01ee\u0003P(\u0000\u01ed\u01ec\u0001\u0000\u0000\u0000\u01ed\u01ee"+
		"\u0001\u0000\u0000\u0000\u01ee\u01ef\u0001\u0000\u0000\u0000\u01ef\u01f0"+
		"\u0005\t\u0000\u0000\u01f0\u01f1\u0003\b\u0004\u0000\u01f1M\u0001\u0000"+
		"\u0000\u0000\u01f2\u01f4\u0003z=\u0000\u01f3\u01f2\u0001\u0000\u0000\u0000"+
		"\u01f3\u01f4\u0001\u0000\u0000\u0000\u01f4\u01f6\u0001\u0000\u0000\u0000"+
		"\u01f5\u01f7\u0003|>\u0000\u01f6\u01f5\u0001\u0000\u0000\u0000\u01f6\u01f7"+
		"\u0001\u0000\u0000\u0000\u01f7\u01f8\u0001\u0000\u0000\u0000\u01f8\u01f9"+
		"\u0005\"\u0000\u0000\u01f9\u01fb\u0003r9\u0000\u01fa\u01fc\u0003l6\u0000"+
		"\u01fb\u01fa\u0001\u0000\u0000\u0000\u01fb\u01fc\u0001\u0000\u0000\u0000"+
		"\u01fc\u01fd\u0001\u0000\u0000\u0000\u01fd\u01ff\u0005\b\u0000\u0000\u01fe"+
		"\u0200\u0003P(\u0000\u01ff\u01fe\u0001\u0000\u0000\u0000\u01ff\u0200\u0001"+
		"\u0000\u0000\u0000\u0200\u0201\u0001\u0000\u0000\u0000\u0201\u0204\u0005"+
		"\t\u0000\u0000\u0202\u0203\u0005\u0003\u0000\u0000\u0203\u0205\u0003j"+
		"5\u0000\u0204\u0202\u0001\u0000\u0000\u0000\u0204\u0205\u0001\u0000\u0000"+
		"\u0000\u0205\u0206\u0001\u0000\u0000\u0000\u0206\u0207\u0003\b\u0004\u0000"+
		"\u0207O\u0001\u0000\u0000\u0000\u0208\u020d\u0003R)\u0000\u0209\u020a"+
		"\u0005\n\u0000\u0000\u020a\u020c\u0003R)\u0000\u020b\u0209\u0001\u0000"+
		"\u0000\u0000\u020c\u020f\u0001\u0000\u0000\u0000\u020d\u020b\u0001\u0000"+
		"\u0000\u0000\u020d\u020e\u0001\u0000\u0000\u0000\u020eQ\u0001\u0000\u0000"+
		"\u0000\u020f\u020d\u0001\u0000\u0000\u0000\u0210\u0211\u0005N\u0000\u0000"+
		"\u0211\u0212\u0005\u0003\u0000\u0000\u0212\u0215\u0003j5\u0000\u0213\u0214"+
		"\u0005\u001d\u0000\u0000\u0214\u0216\u0003\n\u0005\u0000\u0215\u0213\u0001"+
		"\u0000\u0000\u0000\u0215\u0216\u0001\u0000\u0000\u0000\u0216S\u0001\u0000"+
		"\u0000\u0000\u0217\u021c\u0003\n\u0005\u0000\u0218\u0219\u0005\n\u0000"+
		"\u0000\u0219\u021b\u0003\n\u0005\u0000\u021a\u0218\u0001\u0000\u0000\u0000"+
		"\u021b\u021e\u0001\u0000\u0000\u0000\u021c\u021a\u0001\u0000\u0000\u0000"+
		"\u021c\u021d\u0001\u0000\u0000\u0000\u021dU\u0001\u0000\u0000\u0000\u021e"+
		"\u021c\u0001\u0000\u0000\u0000\u021f\u0221\u0003b1\u0000\u0220\u021f\u0001"+
		"\u0000\u0000\u0000\u0221\u0224\u0001\u0000\u0000\u0000\u0222\u0220\u0001"+
		"\u0000\u0000\u0000\u0222\u0223\u0001\u0000\u0000\u0000\u0223\u0226\u0001"+
		"\u0000\u0000\u0000\u0224\u0222\u0001\u0000\u0000\u0000\u0225\u0227\u0003"+
		"z=\u0000\u0226\u0225\u0001\u0000\u0000\u0000\u0226\u0227\u0001\u0000\u0000"+
		"\u0000\u0227\u0229\u0001\u0000\u0000\u0000\u0228\u022a\u0003|>\u0000\u0229"+
		"\u0228\u0001\u0000\u0000\u0000\u0229\u022a\u0001\u0000\u0000\u0000\u022a"+
		"\u022b\u0001\u0000\u0000\u0000\u022b\u022c\u0005#\u0000\u0000\u022c\u022e"+
		"\u0005N\u0000\u0000\u022d\u022f\u0003l6\u0000\u022e\u022d\u0001\u0000"+
		"\u0000\u0000\u022e\u022f\u0001\u0000\u0000\u0000\u022f\u0231\u0001\u0000"+
		"\u0000\u0000\u0230\u0232\u0003X,\u0000\u0231\u0230\u0001\u0000\u0000\u0000"+
		"\u0231\u0232\u0001\u0000\u0000\u0000\u0232\u0233\u0001\u0000\u0000\u0000"+
		"\u0233\u0234\u0003\b\u0004\u0000\u0234W\u0001\u0000\u0000\u0000\u0235"+
		"\u0236\u0005\b\u0000\u0000\u0236\u023b\u0003Z-\u0000\u0237\u0238\u0005"+
		"\n\u0000\u0000\u0238\u023a\u0003Z-\u0000\u0239\u0237\u0001\u0000\u0000"+
		"\u0000\u023a\u023d\u0001\u0000\u0000\u0000\u023b\u0239\u0001\u0000\u0000"+
		"\u0000\u023b\u023c\u0001\u0000\u0000\u0000\u023c\u023e\u0001\u0000\u0000"+
		"\u0000\u023d\u023b\u0001\u0000\u0000\u0000\u023e\u023f\u0005\t\u0000\u0000"+
		"\u023fY\u0001\u0000\u0000\u0000\u0240\u0252\u0003j5\u0000\u0241\u0242"+
		"\u0005$\u0000\u0000\u0242\u0243\u0005\u001d\u0000\u0000\u0243\u0252\u0003"+
		"j5\u0000\u0244\u0245\u0005%\u0000\u0000\u0245\u0246\u0005\u001d\u0000"+
		"\u0000\u0246\u0247\u0005\u0005\u0000\u0000\u0247\u024c\u0003j5\u0000\u0248"+
		"\u0249\u0005\n\u0000\u0000\u0249\u024b\u0003j5\u0000\u024a\u0248\u0001"+
		"\u0000\u0000\u0000\u024b\u024e\u0001\u0000\u0000\u0000\u024c\u024a\u0001"+
		"\u0000\u0000\u0000\u024c\u024d\u0001\u0000\u0000\u0000\u024d\u024f\u0001"+
		"\u0000\u0000\u0000\u024e\u024c\u0001\u0000\u0000\u0000\u024f\u0250\u0005"+
		"\u0006\u0000\u0000\u0250\u0252\u0001\u0000\u0000\u0000\u0251\u0240\u0001"+
		"\u0000\u0000\u0000\u0251\u0241\u0001\u0000\u0000\u0000\u0251\u0244\u0001"+
		"\u0000\u0000\u0000\u0252[\u0001\u0000\u0000\u0000\u0253\u0255\u0003z="+
		"\u0000\u0254\u0253\u0001\u0000\u0000\u0000\u0254\u0255\u0001\u0000\u0000"+
		"\u0000\u0255\u0257\u0001\u0000\u0000\u0000\u0256\u0258\u0003|>\u0000\u0257"+
		"\u0256\u0001\u0000\u0000\u0000\u0257\u0258\u0001\u0000\u0000\u0000\u0258"+
		"\u0259\u0001\u0000\u0000\u0000\u0259\u025a\u0005&\u0000\u0000\u025a\u025c"+
		"\u0005N\u0000\u0000\u025b\u025d\u0003l6\u0000\u025c\u025b\u0001\u0000"+
		"\u0000\u0000\u025c\u025d\u0001\u0000\u0000\u0000\u025d\u025e\u0001\u0000"+
		"\u0000\u0000\u025e\u0262\u0005\u0001\u0000\u0000\u025f\u0261\u0003^/\u0000"+
		"\u0260\u025f\u0001\u0000\u0000\u0000\u0261\u0264\u0001\u0000\u0000\u0000"+
		"\u0262\u0260\u0001\u0000\u0000\u0000\u0262\u0263\u0001\u0000\u0000\u0000"+
		"\u0263\u0265\u0001\u0000\u0000\u0000\u0264\u0262\u0001\u0000\u0000\u0000"+
		"\u0265\u0266\u0005\u0002\u0000\u0000\u0266]\u0001\u0000\u0000\u0000\u0267"+
		"\u0269\u0003z=\u0000\u0268\u0267\u0001\u0000\u0000\u0000\u0268\u0269\u0001"+
		"\u0000\u0000\u0000\u0269\u026b\u0001\u0000\u0000\u0000\u026a\u026c\u0003"+
		"|>\u0000\u026b\u026a\u0001\u0000\u0000\u0000\u026b\u026c\u0001\u0000\u0000"+
		"\u0000\u026c\u026d\u0001\u0000\u0000\u0000\u026d\u026f\u0005 \u0000\u0000"+
		"\u026e\u0270\u0003l6\u0000\u026f\u026e\u0001\u0000\u0000\u0000\u026f\u0270"+
		"\u0001\u0000\u0000\u0000\u0270\u0271\u0001\u0000\u0000\u0000\u0271\u0272"+
		"\u0005N\u0000\u0000\u0272\u0274\u0005\b\u0000\u0000\u0273\u0275\u0003"+
		"P(\u0000\u0274\u0273\u0001\u0000\u0000\u0000\u0274\u0275\u0001\u0000\u0000"+
		"\u0000\u0275\u0276\u0001\u0000\u0000\u0000\u0276\u0277\u0005\t\u0000\u0000"+
		"\u0277\u0278\u0005\u0003\u0000\u0000\u0278\u0279\u0003j5\u0000\u0279_"+
		"\u0001\u0000\u0000\u0000\u027a\u027c\u0003b1\u0000\u027b\u027a\u0001\u0000"+
		"\u0000\u0000\u027c\u027f\u0001\u0000\u0000\u0000\u027d\u027b\u0001\u0000"+
		"\u0000\u0000\u027d\u027e\u0001\u0000\u0000\u0000\u027e\u0281\u0001\u0000"+
		"\u0000\u0000\u027f\u027d\u0001\u0000\u0000\u0000\u0280\u0282\u0003z=\u0000"+
		"\u0281\u0280\u0001\u0000\u0000\u0000\u0281\u0282\u0001\u0000\u0000\u0000"+
		"\u0282\u0284\u0001\u0000\u0000\u0000\u0283\u0285\u0003|>\u0000\u0284\u0283"+
		"\u0001\u0000\u0000\u0000\u0284\u0285\u0001\u0000\u0000\u0000\u0285\u0286"+
		"\u0001\u0000\u0000\u0000\u0286\u0287\u0005\'\u0000\u0000\u0287\u028d\u0005"+
		"N\u0000\u0000\u0288\u028a\u0005\b\u0000\u0000\u0289\u028b\u0003P(\u0000"+
		"\u028a\u0289\u0001\u0000\u0000\u0000\u028a\u028b\u0001\u0000\u0000\u0000"+
		"\u028b\u028c\u0001\u0000\u0000\u0000\u028c\u028e\u0005\t\u0000\u0000\u028d"+
		"\u0288\u0001\u0000\u0000\u0000\u028d\u028e\u0001\u0000\u0000\u0000\u028e"+
		"\u0290\u0001\u0000\u0000\u0000\u028f\u0291\u0003\b\u0004\u0000\u0290\u028f"+
		"\u0001\u0000\u0000\u0000\u0290\u0291\u0001\u0000\u0000\u0000\u0291a\u0001"+
		"\u0000\u0000\u0000\u0292\u0293\u0005(\u0000\u0000\u0293\u029b\u0005N\u0000"+
		"\u0000\u0294\u0297\u0005\b\u0000\u0000\u0295\u0298\u0003d2\u0000\u0296"+
		"\u0298\u0003p8\u0000\u0297\u0295\u0001\u0000\u0000\u0000\u0297\u0296\u0001"+
		"\u0000\u0000\u0000\u0298\u0299\u0001\u0000\u0000\u0000\u0299\u029a\u0005"+
		"\t\u0000\u0000\u029a\u029c\u0001\u0000\u0000\u0000\u029b\u0294\u0001\u0000"+
		"\u0000\u0000\u029b\u029c\u0001\u0000\u0000\u0000\u029cc\u0001\u0000\u0000"+
		"\u0000\u029d\u02a2\u0003f3\u0000\u029e\u029f\u0005\n\u0000\u0000\u029f"+
		"\u02a1\u0003f3\u0000\u02a0\u029e\u0001\u0000\u0000\u0000\u02a1\u02a4\u0001"+
		"\u0000\u0000\u0000\u02a2\u02a0\u0001\u0000\u0000\u0000\u02a2\u02a3\u0001"+
		"\u0000\u0000\u0000\u02a3e\u0001\u0000\u0000\u0000\u02a4\u02a2\u0001\u0000"+
		"\u0000\u0000\u02a5\u02a6\u0005N\u0000\u0000\u02a6\u02a7\u0005\u001d\u0000"+
		"\u0000\u02a7\u02a8\u0005P\u0000\u0000\u02a8g\u0001\u0000\u0000\u0000\u02a9"+
		"\u02ab\u0005N\u0000\u0000\u02aa\u02ac\u0003l6\u0000\u02ab\u02aa\u0001"+
		"\u0000\u0000\u0000\u02ab\u02ac\u0001\u0000\u0000\u0000\u02ac\u02ad\u0001"+
		"\u0000\u0000\u0000\u02ad\u02af\u0005\b\u0000\u0000\u02ae\u02b0\u0003T"+
		"*\u0000\u02af\u02ae\u0001\u0000\u0000\u0000\u02af\u02b0\u0001\u0000\u0000"+
		"\u0000\u02b0\u02b1\u0001\u0000\u0000\u0000\u02b1\u02b2\u0005\t\u0000\u0000"+
		"\u02b2i\u0001\u0000\u0000\u0000\u02b3\u02b4\u00065\uffff\uffff\u0000\u02b4"+
		"\u02b6\u0005N\u0000\u0000\u02b5\u02b7\u0003l6\u0000\u02b6\u02b5\u0001"+
		"\u0000\u0000\u0000\u02b6\u02b7\u0001\u0000\u0000\u0000\u02b7\u02c1\u0001"+
		"\u0000\u0000\u0000\u02b8\u02bb\n\u0001\u0000\u0000\u02b9\u02ba\u0005)"+
		"\u0000\u0000\u02ba\u02bc\u0003j5\u0000\u02bb\u02b9\u0001\u0000\u0000\u0000"+
		"\u02bc\u02bd\u0001\u0000\u0000\u0000\u02bd\u02bb\u0001\u0000\u0000\u0000"+
		"\u02bd\u02be\u0001\u0000\u0000\u0000\u02be\u02c0\u0001\u0000\u0000\u0000"+
		"\u02bf\u02b8\u0001\u0000\u0000\u0000\u02c0\u02c3\u0001\u0000\u0000\u0000"+
		"\u02c1\u02bf\u0001\u0000\u0000\u0000\u02c1\u02c2\u0001\u0000\u0000\u0000"+
		"\u02c2k\u0001\u0000\u0000\u0000\u02c3\u02c1\u0001\u0000\u0000\u0000\u02c4"+
		"\u02c5\u0005\u0005\u0000\u0000\u02c5\u02ca\u0003n7\u0000\u02c6\u02c7\u0005"+
		"\n\u0000\u0000\u02c7\u02c9\u0003n7\u0000\u02c8\u02c6\u0001\u0000\u0000"+
		"\u0000\u02c9\u02cc\u0001\u0000\u0000\u0000\u02ca\u02c8\u0001\u0000\u0000"+
		"\u0000\u02ca\u02cb\u0001\u0000\u0000\u0000\u02cb\u02cd\u0001\u0000\u0000"+
		"\u0000\u02cc\u02ca\u0001\u0000\u0000\u0000\u02cd\u02ce\u0005\u0006\u0000"+
		"\u0000\u02cem\u0001\u0000\u0000\u0000\u02cf\u02d2\u0005N\u0000\u0000\u02d0"+
		"\u02d3\u0007\u0002\u0000\u0000\u02d1\u02d3\u0005*\u0000\u0000\u02d2\u02d0"+
		"\u0001\u0000\u0000\u0000\u02d2\u02d1\u0001\u0000\u0000\u0000\u02d3\u02d5"+
		"\u0001\u0000\u0000\u0000\u02d4\u02cf\u0001\u0000\u0000\u0000\u02d4\u02d5"+
		"\u0001\u0000\u0000\u0000\u02d5\u02d6\u0001\u0000\u0000\u0000\u02d6\u02db"+
		"\u0003j5\u0000\u02d7\u02d8\u0005+\u0000\u0000\u02d8\u02da\u0003j5\u0000"+
		"\u02d9\u02d7\u0001\u0000\u0000\u0000\u02da\u02dd\u0001\u0000\u0000\u0000"+
		"\u02db\u02d9\u0001\u0000\u0000\u0000\u02db\u02dc\u0001\u0000\u0000\u0000"+
		"\u02dco\u0001\u0000\u0000\u0000\u02dd\u02db\u0001\u0000\u0000\u0000\u02de"+
		"\u02df\u0007\u0003\u0000\u0000\u02dfq\u0001\u0000\u0000\u0000\u02e0\u02e3"+
		"\u0003t:\u0000\u02e1\u02e3\u0003v;\u0000\u02e2\u02e0\u0001\u0000\u0000"+
		"\u0000\u02e2\u02e1\u0001\u0000\u0000\u0000\u02e3s\u0001\u0000\u0000\u0000"+
		"\u02e4\u02fc\u0005/\u0000\u0000\u02e5\u02fc\u00050\u0000\u0000\u02e6\u02fc"+
		"\u00051\u0000\u0000\u02e7\u02fc\u00052\u0000\u0000\u02e8\u02fc\u00053"+
		"\u0000\u0000\u02e9\u02fc\u00054\u0000\u0000\u02ea\u02fc\u00055\u0000\u0000"+
		"\u02eb\u02fc\u00056\u0000\u0000\u02ec\u02fc\u0005+\u0000\u0000\u02ed\u02fc"+
		"\u00057\u0000\u0000\u02ee\u02fc\u00058\u0000\u0000\u02ef\u02fc\u0005)"+
		"\u0000\u0000\u02f0\u02fc\u00059\u0000\u0000\u02f1\u02fc\u0005:\u0000\u0000"+
		"\u02f2\u02fc\u0005;\u0000\u0000\u02f3\u02fc\u0005<\u0000\u0000\u02f4\u02fc"+
		"\u0005=\u0000\u0000\u02f5\u02fc\u0005>\u0000\u0000\u02f6\u02fc\u0005?"+
		"\u0000\u0000\u02f7\u02fc\u0005@\u0000\u0000\u02f8\u02fc\u0005A\u0000\u0000"+
		"\u02f9\u02fc\u0005B\u0000\u0000\u02fa\u02fc\u0005C\u0000\u0000\u02fb\u02e4"+
		"\u0001\u0000\u0000\u0000\u02fb\u02e5\u0001\u0000\u0000\u0000\u02fb\u02e6"+
		"\u0001\u0000\u0000\u0000\u02fb\u02e7\u0001\u0000\u0000\u0000\u02fb\u02e8"+
		"\u0001\u0000\u0000\u0000\u02fb\u02e9\u0001\u0000\u0000\u0000\u02fb\u02ea"+
		"\u0001\u0000\u0000\u0000\u02fb\u02eb\u0001\u0000\u0000\u0000\u02fb\u02ec"+
		"\u0001\u0000\u0000\u0000\u02fb\u02ed\u0001\u0000\u0000\u0000\u02fb\u02ee"+
		"\u0001\u0000\u0000\u0000\u02fb\u02ef\u0001\u0000\u0000\u0000\u02fb\u02f0"+
		"\u0001\u0000\u0000\u0000\u02fb\u02f1\u0001\u0000\u0000\u0000\u02fb\u02f2"+
		"\u0001\u0000\u0000\u0000\u02fb\u02f3\u0001\u0000\u0000\u0000\u02fb\u02f4"+
		"\u0001\u0000\u0000\u0000\u02fb\u02f5\u0001\u0000\u0000\u0000\u02fb\u02f6"+
		"\u0001\u0000\u0000\u0000\u02fb\u02f7\u0001\u0000\u0000\u0000\u02fb\u02f8"+
		"\u0001\u0000\u0000\u0000\u02fb\u02f9\u0001\u0000\u0000\u0000\u02fb\u02fa"+
		"\u0001\u0000\u0000\u0000\u02fcu\u0001\u0000\u0000\u0000\u02fd\u0302\u0005"+
		"D\u0000\u0000\u02fe\u0302\u0005E\u0000\u0000\u02ff\u0302\u0005F\u0000"+
		"\u0000\u0300\u0302\u0005G\u0000\u0000\u0301\u02fd\u0001\u0000\u0000\u0000"+
		"\u0301\u02fe\u0001\u0000\u0000\u0000\u0301\u02ff\u0001\u0000\u0000\u0000"+
		"\u0301\u0300\u0001\u0000\u0000\u0000\u0302w\u0001\u0000\u0000\u0000\u0303"+
		"\u0305\u0003r9\u0000\u0304\u0303\u0001\u0000\u0000\u0000\u0304\u0305\u0001"+
		"\u0000\u0000\u0000\u0305\u0306\u0001\u0000\u0000\u0000\u0306\u0307\u0005"+
		"\u001d\u0000\u0000\u0307\u0308\u0003\n\u0005\u0000\u0308y\u0001\u0000"+
		"\u0000\u0000\u0309\u030d\u0005H\u0000\u0000\u030a\u030d\u0005I\u0000\u0000"+
		"\u030b\u030d\u0005J\u0000\u0000\u030c\u0309\u0001\u0000\u0000\u0000\u030c"+
		"\u030a\u0001\u0000\u0000\u0000\u030c\u030b\u0001\u0000\u0000\u0000\u030d"+
		"{\u0001\u0000\u0000\u0000\u030e\u0311\u0005K\u0000\u0000\u030f\u0311\u0005"+
		"L\u0000\u0000\u0310\u030e\u0001\u0000\u0000\u0000\u0310\u030f\u0001\u0000"+
		"\u0000\u0000\u0310\u0311\u0001\u0000\u0000\u0000\u0311\u0313\u0001\u0000"+
		"\u0000\u0000\u0312\u0314\u0005M\u0000\u0000\u0313\u0312\u0001\u0000\u0000"+
		"\u0000\u0313\u0314\u0001\u0000\u0000\u0000\u0314}\u0001\u0000\u0000\u0000"+
		"h\u0081\u008e\u0097\u009c\u00a2\u00aa\u00b3\u00c2\u00c6\u00c8\u00d1\u00d6"+
		"\u00dd\u00e4\u00ed\u00f4\u00ff\u0107\u0110\u0118\u011d\u0121\u0125\u012c"+
		"\u0135\u0139\u0140\u0145\u014d\u015e\u0162\u0171\u0175\u0182\u0197\u019a"+
		"\u01a1\u01a7\u01ab\u01af\u01b1\u01b6\u01bc\u01c1\u01c5\u01c8\u01cd\u01d1"+
		"\u01d6\u01d9\u01de\u01e2\u01e5\u01e9\u01ed\u01f3\u01f6\u01fb\u01ff\u0204"+
		"\u020d\u0215\u021c\u0222\u0226\u0229\u022e\u0231\u023b\u024c\u0251\u0254"+
		"\u0257\u025c\u0262\u0268\u026b\u026f\u0274\u027d\u0281\u0284\u028a\u028d"+
		"\u0290\u0297\u029b\u02a2\u02ab\u02af\u02b6\u02bd\u02c1\u02ca\u02d2\u02d4"+
		"\u02db\u02e2\u02fb\u0301\u0304\u030c\u0310\u0313";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}