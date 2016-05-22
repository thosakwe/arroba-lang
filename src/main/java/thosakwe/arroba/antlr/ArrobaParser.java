// Generated from C:/Users/tobe/Source/Java/arroba/src/main/antlr4/thosakwe/arroba/antlr\Arroba.g4 by ANTLR 4.5.1
package thosakwe.arroba.antlr;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ArrobaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SL_CMT=1, WS=2, ARR_FAT=3, ARR_L=4, ARR_R=5, COLON=6, COMMA=7, CURLY_L=8, 
		CURLY_R=9, DOT=10, EQUALS=11, PAREN_L=12, PAREN_R=13, SEMI=14, SQUARE_L=15, 
		SQUARE_R=16, CARET=17, PLUS=18, MINUS=19, MODULO=20, TIMES=21, DIVIDE=22, 
		SUMMA=23, BITWISE_AND=24, BITWISE_OR=25, BITWISE_NOT=26, BITWISE_SHL=27, 
		BITWISE_SHR=28, BITWISE_XOR=29, IF=30, ELIF=31, ELSE=32, IS=33, NOT=34, 
		AND=35, OR=36, LT=37, LTE=38, GT=39, GTE=40, FN=41, LOCAL=42, RET=43, 
		DBL=44, HEX=45, INT=46, STRING=47, ID=48;
	public static final int
		RULE_compilationUnit = 0, RULE_stmt = 1, RULE_assignStmt = 2, RULE_exprStmt = 3, 
		RULE_retStmt = 4, RULE_expr = 5, RULE_paramSpec = 6;
	public static final String[] ruleNames = {
		"compilationUnit", "stmt", "assignStmt", "exprStmt", "retStmt", "expr", 
		"paramSpec"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, "'=>'", "'<-'", "'->'", "':'", "','", "'{'", "'}'", 
		"'.'", "'='", "'('", "')'", "';'", "'['", "']'", "'^'", "'+'", "'-'", 
		"'%'", "'*'", "'/'", "'$'", "'&'", "'|'", "'!'", "'<<'", "'>>'", "'xor'", 
		"'if'", "'elif'", "'else'", null, null, null, null, null, null, null, 
		null, "'fn'", "'local'", "'ret'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SL_CMT", "WS", "ARR_FAT", "ARR_L", "ARR_R", "COLON", "COMMA", "CURLY_L", 
		"CURLY_R", "DOT", "EQUALS", "PAREN_L", "PAREN_R", "SEMI", "SQUARE_L", 
		"SQUARE_R", "CARET", "PLUS", "MINUS", "MODULO", "TIMES", "DIVIDE", "SUMMA", 
		"BITWISE_AND", "BITWISE_OR", "BITWISE_NOT", "BITWISE_SHL", "BITWISE_SHR", 
		"BITWISE_XOR", "IF", "ELIF", "ELSE", "IS", "NOT", "AND", "OR", "LT", "LTE", 
		"GT", "GTE", "FN", "LOCAL", "RET", "DBL", "HEX", "INT", "STRING", "ID"
	};
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
	public String getGrammarFileName() { return "Arroba.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ArrobaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CompilationUnitContext extends ParserRuleContext {
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterCompilationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitCompilationUnit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitCompilationUnit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARR_FAT) | (1L << PAREN_L) | (1L << SQUARE_L) | (1L << FN) | (1L << LOCAL) | (1L << RET) | (1L << DBL) | (1L << HEX) | (1L << INT) | (1L << STRING) | (1L << ID))) != 0)) {
				{
				{
				setState(14);
				stmt();
				}
				}
				setState(19);
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

	public static class StmtContext extends ParserRuleContext {
		public AssignStmtContext assignStmt() {
			return getRuleContext(AssignStmtContext.class,0);
		}
		public ExprStmtContext exprStmt() {
			return getRuleContext(ExprStmtContext.class,0);
		}
		public RetStmtContext retStmt() {
			return getRuleContext(RetStmtContext.class,0);
		}
		public StmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmtContext stmt() throws RecognitionException {
		StmtContext _localctx = new StmtContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stmt);
		try {
			setState(23);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(20);
				assignStmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(21);
				exprStmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(22);
				retStmt();
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

	public static class AssignStmtContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ARR_L() { return getToken(ArrobaParser.ARR_L, 0); }
		public AssignStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterAssignStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitAssignStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitAssignStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignStmtContext assignStmt() throws RecognitionException {
		AssignStmtContext _localctx = new AssignStmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assignStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			expr(0);
			setState(26);
			match(ARR_L);
			setState(27);
			expr(0);
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

	public static class ExprStmtContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ExprStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterExprStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitExprStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitExprStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprStmtContext exprStmt() throws RecognitionException {
		ExprStmtContext _localctx = new ExprStmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exprStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			expr(0);
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

	public static class RetStmtContext extends ParserRuleContext {
		public TerminalNode RET() { return getToken(ArrobaParser.RET, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public RetStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_retStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterRetStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitRetStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitRetStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RetStmtContext retStmt() throws RecognitionException {
		RetStmtContext _localctx = new RetStmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_retStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			match(RET);
			setState(32);
			expr(0);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StringExprContext extends ExprContext {
		public TerminalNode STRING() { return getToken(ArrobaParser.STRING, 0); }
		public StringExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterStringExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitStringExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitStringExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunctionExprContext extends ExprContext {
		public TerminalNode FN() { return getToken(ArrobaParser.FN, 0); }
		public ParamSpecContext paramSpec() {
			return getRuleContext(ParamSpecContext.class,0);
		}
		public TerminalNode CURLY_L() { return getToken(ArrobaParser.CURLY_L, 0); }
		public TerminalNode CURLY_R() { return getToken(ArrobaParser.CURLY_R, 0); }
		public List<StmtContext> stmt() {
			return getRuleContexts(StmtContext.class);
		}
		public StmtContext stmt(int i) {
			return getRuleContext(StmtContext.class,i);
		}
		public FunctionExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterFunctionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitFunctionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitFunctionExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdExprContext extends ExprContext {
		public TerminalNode ID() { return getToken(ArrobaParser.ID, 0); }
		public IdExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterIdExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitIdExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitIdExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumExprContext extends ExprContext {
		public TerminalNode INT() { return getToken(ArrobaParser.INT, 0); }
		public TerminalNode HEX() { return getToken(ArrobaParser.HEX, 0); }
		public TerminalNode DBL() { return getToken(ArrobaParser.DBL, 0); }
		public NumExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterNumExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitNumExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitNumExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LocalExprContext extends ExprContext {
		public TerminalNode LOCAL() { return getToken(ArrobaParser.LOCAL, 0); }
		public TerminalNode COLON() { return getToken(ArrobaParser.COLON, 0); }
		public TerminalNode ID() { return getToken(ArrobaParser.ID, 0); }
		public LocalExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterLocalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitLocalExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitLocalExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MemberExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode DOT() { return getToken(ArrobaParser.DOT, 0); }
		public TerminalNode ID() { return getToken(ArrobaParser.ID, 0); }
		public MemberExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterMemberExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitMemberExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitMemberExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NestedExprContext extends ExprContext {
		public TerminalNode PAREN_L() { return getToken(ArrobaParser.PAREN_L, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode PAREN_R() { return getToken(ArrobaParser.PAREN_R, 0); }
		public NestedExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterNestedExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitNestedExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitNestedExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VectorExprContext extends ExprContext {
		public TerminalNode SQUARE_L() { return getToken(ArrobaParser.SQUARE_L, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode SQUARE_R() { return getToken(ArrobaParser.SQUARE_R, 0); }
		public List<TerminalNode> COMMA() { return getTokens(ArrobaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ArrobaParser.COMMA, i);
		}
		public VectorExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterVectorExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitVectorExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitVectorExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InlineFunctionExprContext extends ExprContext {
		public ParamSpecContext paramSpec() {
			return getRuleContext(ParamSpecContext.class,0);
		}
		public TerminalNode ARR_FAT() { return getToken(ArrobaParser.ARR_FAT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public InlineFunctionExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterInlineFunctionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitInlineFunctionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitInlineFunctionExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MathExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(ArrobaParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(ArrobaParser.MINUS, 0); }
		public TerminalNode TIMES() { return getToken(ArrobaParser.TIMES, 0); }
		public TerminalNode DIVIDE() { return getToken(ArrobaParser.DIVIDE, 0); }
		public TerminalNode MODULO() { return getToken(ArrobaParser.MODULO, 0); }
		public TerminalNode CARET() { return getToken(ArrobaParser.CARET, 0); }
		public MathExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterMathExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitMathExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitMathExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ArrowRightExprContext extends ExprContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ARR_R() { return getToken(ArrobaParser.ARR_R, 0); }
		public ArrowRightExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterArrowRightExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitArrowRightExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitArrowRightExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class InvocationExprContext extends ExprContext {
		public ExprContext target;
		public TerminalNode PAREN_L() { return getToken(ArrobaParser.PAREN_L, 0); }
		public TerminalNode PAREN_R() { return getToken(ArrobaParser.PAREN_R, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ArrobaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ArrobaParser.COMMA, i);
		}
		public InvocationExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterInvocationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitInvocationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitInvocationExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				_localctx = new InlineFunctionExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(35);
				paramSpec();
				setState(36);
				match(ARR_FAT);
				setState(37);
				expr(3);
				}
				break;
			case 2:
				{
				_localctx = new IdExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(39);
				match(ID);
				}
				break;
			case 3:
				{
				_localctx = new NumExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(40);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DBL) | (1L << HEX) | (1L << INT))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case 4:
				{
				_localctx = new StringExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(41);
				match(STRING);
				}
				break;
			case 5:
				{
				_localctx = new VectorExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(42);
				match(SQUARE_L);
				setState(48);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(43);
						expr(0);
						setState(44);
						match(COMMA);
						}
						} 
					}
					setState(50);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				}
				setState(51);
				expr(0);
				setState(52);
				match(SQUARE_R);
				}
				break;
			case 6:
				{
				_localctx = new LocalExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(54);
				match(LOCAL);
				setState(55);
				match(COLON);
				setState(56);
				match(ID);
				}
				break;
			case 7:
				{
				_localctx = new FunctionExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(57);
				match(FN);
				setState(58);
				paramSpec();
				setState(59);
				match(CURLY_L);
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARR_FAT) | (1L << PAREN_L) | (1L << SQUARE_L) | (1L << FN) | (1L << LOCAL) | (1L << RET) | (1L << DBL) | (1L << HEX) | (1L << INT) | (1L << STRING) | (1L << ID))) != 0)) {
					{
					{
					setState(60);
					stmt();
					}
					}
					setState(65);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(66);
				match(CURLY_R);
				}
				break;
			case 8:
				{
				_localctx = new NestedExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(68);
				match(PAREN_L);
				setState(69);
				expr(0);
				setState(70);
				match(PAREN_R);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(99);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(97);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new MathExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(74);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(75);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CARET) | (1L << PLUS) | (1L << MINUS) | (1L << MODULO) | (1L << TIMES) | (1L << DIVIDE))) != 0)) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(76);
						expr(11);
						}
						break;
					case 2:
						{
						_localctx = new ArrowRightExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(77);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(78);
						match(ARR_R);
						setState(79);
						expr(6);
						}
						break;
					case 3:
						{
						_localctx = new MemberExprContext(new ExprContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(80);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(81);
						match(DOT);
						setState(82);
						match(ID);
						}
						break;
					case 4:
						{
						_localctx = new InvocationExprContext(new ExprContext(_parentctx, _parentState));
						((InvocationExprContext)_localctx).target = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(83);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(84);
						match(PAREN_L);
						setState(94);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ARR_FAT) | (1L << PAREN_L) | (1L << SQUARE_L) | (1L << FN) | (1L << LOCAL) | (1L << DBL) | (1L << HEX) | (1L << INT) | (1L << STRING) | (1L << ID))) != 0)) {
							{
							setState(90);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
							while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
								if ( _alt==1 ) {
									{
									{
									setState(85);
									expr(0);
									setState(86);
									match(COMMA);
									}
									} 
								}
								setState(92);
								_errHandler.sync(this);
								_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
							}
							setState(93);
							expr(0);
							}
						}

						setState(96);
						match(PAREN_R);
						}
						break;
					}
					} 
				}
				setState(101);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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

	public static class ParamSpecContext extends ParserRuleContext {
		public TerminalNode PAREN_L() { return getToken(ArrobaParser.PAREN_L, 0); }
		public TerminalNode PAREN_R() { return getToken(ArrobaParser.PAREN_R, 0); }
		public List<TerminalNode> ID() { return getTokens(ArrobaParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(ArrobaParser.ID, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ArrobaParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ArrobaParser.COMMA, i);
		}
		public ParamSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).enterParamSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ArrobaListener ) ((ArrobaListener)listener).exitParamSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ArrobaVisitor ) return ((ArrobaVisitor<? extends T>)visitor).visitParamSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamSpecContext paramSpec() throws RecognitionException {
		ParamSpecContext _localctx = new ParamSpecContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_paramSpec);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			_la = _input.LA(1);
			if (_la==PAREN_L) {
				{
				setState(102);
				match(PAREN_L);
				setState(111);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(107);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(103);
							match(ID);
							setState(104);
							match(COMMA);
							}
							} 
						}
						setState(109);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
					}
					setState(110);
					match(ID);
					}
				}

				setState(113);
				match(PAREN_R);
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
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 10);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 8);
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\62w\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\3\2\7\2\22\n\2\f\2\16\2\25"+
		"\13\2\3\3\3\3\3\3\5\3\32\n\3\3\4\3\4\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\61\n\7\f\7\16\7\64\13\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7@\n\7\f\7\16\7C\13\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\5\7K\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\7\7[\n\7\f\7\16\7^\13\7\3\7\5\7a\n\7\3\7\7\7d\n\7\f\7\16"+
		"\7g\13\7\3\b\3\b\3\b\7\bl\n\b\f\b\16\bo\13\b\3\b\5\br\n\b\3\b\5\bu\n\b"+
		"\3\b\2\3\f\t\2\4\6\b\n\f\16\2\4\3\2.\60\3\2\23\30\u0084\2\23\3\2\2\2\4"+
		"\31\3\2\2\2\6\33\3\2\2\2\b\37\3\2\2\2\n!\3\2\2\2\fJ\3\2\2\2\16t\3\2\2"+
		"\2\20\22\5\4\3\2\21\20\3\2\2\2\22\25\3\2\2\2\23\21\3\2\2\2\23\24\3\2\2"+
		"\2\24\3\3\2\2\2\25\23\3\2\2\2\26\32\5\6\4\2\27\32\5\b\5\2\30\32\5\n\6"+
		"\2\31\26\3\2\2\2\31\27\3\2\2\2\31\30\3\2\2\2\32\5\3\2\2\2\33\34\5\f\7"+
		"\2\34\35\7\6\2\2\35\36\5\f\7\2\36\7\3\2\2\2\37 \5\f\7\2 \t\3\2\2\2!\""+
		"\7-\2\2\"#\5\f\7\2#\13\3\2\2\2$%\b\7\1\2%&\5\16\b\2&\'\7\5\2\2\'(\5\f"+
		"\7\5(K\3\2\2\2)K\7\62\2\2*K\t\2\2\2+K\7\61\2\2,\62\7\21\2\2-.\5\f\7\2"+
		"./\7\t\2\2/\61\3\2\2\2\60-\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3"+
		"\2\2\2\63\65\3\2\2\2\64\62\3\2\2\2\65\66\5\f\7\2\66\67\7\22\2\2\67K\3"+
		"\2\2\289\7,\2\29:\7\b\2\2:K\7\62\2\2;<\7+\2\2<=\5\16\b\2=A\7\n\2\2>@\5"+
		"\4\3\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DE\7"+
		"\13\2\2EK\3\2\2\2FG\7\16\2\2GH\5\f\7\2HI\7\17\2\2IK\3\2\2\2J$\3\2\2\2"+
		"J)\3\2\2\2J*\3\2\2\2J+\3\2\2\2J,\3\2\2\2J8\3\2\2\2J;\3\2\2\2JF\3\2\2\2"+
		"Ke\3\2\2\2LM\f\f\2\2MN\t\3\2\2Nd\5\f\7\rOP\f\7\2\2PQ\7\7\2\2Qd\5\f\7\b"+
		"RS\f\n\2\2ST\7\f\2\2Td\7\62\2\2UV\f\4\2\2V`\7\16\2\2WX\5\f\7\2XY\7\t\2"+
		"\2Y[\3\2\2\2ZW\3\2\2\2[^\3\2\2\2\\Z\3\2\2\2\\]\3\2\2\2]_\3\2\2\2^\\\3"+
		"\2\2\2_a\5\f\7\2`\\\3\2\2\2`a\3\2\2\2ab\3\2\2\2bd\7\17\2\2cL\3\2\2\2c"+
		"O\3\2\2\2cR\3\2\2\2cU\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2f\r\3\2\2\2"+
		"ge\3\2\2\2hq\7\16\2\2ij\7\62\2\2jl\7\t\2\2ki\3\2\2\2lo\3\2\2\2mk\3\2\2"+
		"\2mn\3\2\2\2np\3\2\2\2om\3\2\2\2pr\7\62\2\2qm\3\2\2\2qr\3\2\2\2rs\3\2"+
		"\2\2su\7\17\2\2th\3\2\2\2tu\3\2\2\2u\17\3\2\2\2\16\23\31\62AJ\\`cemqt";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}