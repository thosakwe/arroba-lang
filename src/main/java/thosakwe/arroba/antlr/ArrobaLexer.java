// Generated from C:/Users/tobe/Source/Java/arroba/src/main/antlr4/thosakwe/arroba/antlr\Arroba.g4 by ANTLR 4.5.1
package thosakwe.arroba.antlr;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ArrobaLexer extends Lexer {
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
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"SL_CMT", "WS", "ARR_FAT", "ARR_L", "ARR_R", "COLON", "COMMA", "CURLY_L", 
		"CURLY_R", "DOT", "EQUALS", "PAREN_L", "PAREN_R", "SEMI", "SQUARE_L", 
		"SQUARE_R", "CARET", "PLUS", "MINUS", "MODULO", "TIMES", "DIVIDE", "SUMMA", 
		"BITWISE_AND", "BITWISE_OR", "BITWISE_NOT", "BITWISE_SHL", "BITWISE_SHR", 
		"BITWISE_XOR", "IF", "ELIF", "ELSE", "IS", "NOT", "AND", "OR", "LT", "LTE", 
		"GT", "GTE", "FN", "LOCAL", "RET", "DBL", "HEX", "INT", "ESCAPED", "STRING", 
		"ID"
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


	public ArrobaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Arroba.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\62\u014a\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\3\2\3\2\5"+
		"\2i\n\2\3\2\7\2l\n\2\f\2\16\2o\13\2\3\2\3\2\3\3\3\3\3\3\5\3v\n\3\3\3\3"+
		"\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n"+
		"\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3\"\5\"\u00cb\n\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u00d6\n#\3$\3$\3"+
		"$\3$\3$\5$\u00dd\n$\3%\3%\3%\3%\5%\u00e3\n%\3&\3&\3&\5&\u00e8\n&\3\'\3"+
		"\'\3\'\3\'\3\'\3\'\5\'\u00f0\n\'\3(\3(\3(\5(\u00f5\n(\3)\3)\3)\3)\3)\3"+
		")\5)\u00fd\n)\3*\3*\3*\3+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3-\5-\u010d\n-\3"+
		"-\6-\u0110\n-\r-\16-\u0111\3-\3-\6-\u0116\n-\r-\16-\u0117\3.\3.\3.\3."+
		"\6.\u011e\n.\r.\16.\u011f\3.\6.\u0123\n.\r.\16.\u0124\3.\5.\u0128\n.\3"+
		"/\5/\u012b\n/\3/\6/\u012e\n/\r/\16/\u012f\3\60\3\60\3\60\3\60\3\60\3\60"+
		"\5\60\u0138\n\60\3\61\3\61\3\61\7\61\u013d\n\61\f\61\16\61\u0140\13\61"+
		"\3\61\3\61\3\62\3\62\7\62\u0146\n\62\f\62\16\62\u0149\13\62\3\u013e\2"+
		"\63\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\2a\61c\62\3\2\t\3\2\f\f\5"+
		"\2\f\f\17\17\"\"\3\2\62;\5\2\62;C\\c|\4\2\f\f\17\17\5\2C\\aac|\6\2\62"+
		";C\\aac|\u0162\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2"+
		"\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2"+
		"a\3\2\2\2\2c\3\2\2\2\3h\3\2\2\2\5u\3\2\2\2\7y\3\2\2\2\t|\3\2\2\2\13\177"+
		"\3\2\2\2\r\u0082\3\2\2\2\17\u0084\3\2\2\2\21\u0086\3\2\2\2\23\u0088\3"+
		"\2\2\2\25\u008a\3\2\2\2\27\u008c\3\2\2\2\31\u008e\3\2\2\2\33\u0090\3\2"+
		"\2\2\35\u0092\3\2\2\2\37\u0094\3\2\2\2!\u0096\3\2\2\2#\u0098\3\2\2\2%"+
		"\u009a\3\2\2\2\'\u009c\3\2\2\2)\u009e\3\2\2\2+\u00a0\3\2\2\2-\u00a2\3"+
		"\2\2\2/\u00a4\3\2\2\2\61\u00a6\3\2\2\2\63\u00a8\3\2\2\2\65\u00aa\3\2\2"+
		"\2\67\u00ac\3\2\2\29\u00af\3\2\2\2;\u00b2\3\2\2\2=\u00b6\3\2\2\2?\u00b9"+
		"\3\2\2\2A\u00be\3\2\2\2C\u00ca\3\2\2\2E\u00d5\3\2\2\2G\u00dc\3\2\2\2I"+
		"\u00e2\3\2\2\2K\u00e7\3\2\2\2M\u00ef\3\2\2\2O\u00f4\3\2\2\2Q\u00fc\3\2"+
		"\2\2S\u00fe\3\2\2\2U\u0101\3\2\2\2W\u0107\3\2\2\2Y\u010c\3\2\2\2[\u0127"+
		"\3\2\2\2]\u012a\3\2\2\2_\u0137\3\2\2\2a\u0139\3\2\2\2c\u0143\3\2\2\2e"+
		"i\7%\2\2fg\7\61\2\2gi\7\61\2\2he\3\2\2\2hf\3\2\2\2im\3\2\2\2jl\n\2\2\2"+
		"kj\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2np\3\2\2\2om\3\2\2\2pq\b\2\2\2"+
		"q\4\3\2\2\2rv\t\3\2\2st\7\17\2\2tv\7\f\2\2ur\3\2\2\2us\3\2\2\2vw\3\2\2"+
		"\2wx\b\3\3\2x\6\3\2\2\2yz\7?\2\2z{\7@\2\2{\b\3\2\2\2|}\7>\2\2}~\7/\2\2"+
		"~\n\3\2\2\2\177\u0080\7/\2\2\u0080\u0081\7@\2\2\u0081\f\3\2\2\2\u0082"+
		"\u0083\7<\2\2\u0083\16\3\2\2\2\u0084\u0085\7.\2\2\u0085\20\3\2\2\2\u0086"+
		"\u0087\7}\2\2\u0087\22\3\2\2\2\u0088\u0089\7\177\2\2\u0089\24\3\2\2\2"+
		"\u008a\u008b\7\60\2\2\u008b\26\3\2\2\2\u008c\u008d\7?\2\2\u008d\30\3\2"+
		"\2\2\u008e\u008f\7*\2\2\u008f\32\3\2\2\2\u0090\u0091\7+\2\2\u0091\34\3"+
		"\2\2\2\u0092\u0093\7=\2\2\u0093\36\3\2\2\2\u0094\u0095\7]\2\2\u0095 \3"+
		"\2\2\2\u0096\u0097\7_\2\2\u0097\"\3\2\2\2\u0098\u0099\7`\2\2\u0099$\3"+
		"\2\2\2\u009a\u009b\7-\2\2\u009b&\3\2\2\2\u009c\u009d\7/\2\2\u009d(\3\2"+
		"\2\2\u009e\u009f\7\'\2\2\u009f*\3\2\2\2\u00a0\u00a1\7,\2\2\u00a1,\3\2"+
		"\2\2\u00a2\u00a3\7\61\2\2\u00a3.\3\2\2\2\u00a4\u00a5\7&\2\2\u00a5\60\3"+
		"\2\2\2\u00a6\u00a7\7(\2\2\u00a7\62\3\2\2\2\u00a8\u00a9\7~\2\2\u00a9\64"+
		"\3\2\2\2\u00aa\u00ab\7#\2\2\u00ab\66\3\2\2\2\u00ac\u00ad\7>\2\2\u00ad"+
		"\u00ae\7>\2\2\u00ae8\3\2\2\2\u00af\u00b0\7@\2\2\u00b0\u00b1\7@\2\2\u00b1"+
		":\3\2\2\2\u00b2\u00b3\7z\2\2\u00b3\u00b4\7q\2\2\u00b4\u00b5\7t\2\2\u00b5"+
		"<\3\2\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8\7h\2\2\u00b8>\3\2\2\2\u00b9\u00ba"+
		"\7g\2\2\u00ba\u00bb\7n\2\2\u00bb\u00bc\7k\2\2\u00bc\u00bd\7h\2\2\u00bd"+
		"@\3\2\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0\7n\2\2\u00c0\u00c1\7u\2\2\u00c1"+
		"\u00c2\7g\2\2\u00c2B\3\2\2\2\u00c3\u00c4\7?\2\2\u00c4\u00cb\7?\2\2\u00c5"+
		"\u00c6\7k\2\2\u00c6\u00cb\7u\2\2\u00c7\u00c8\7g\2\2\u00c8\u00c9\7s\2\2"+
		"\u00c9\u00cb\7w\2\2\u00ca\u00c3\3\2\2\2\u00ca\u00c5\3\2\2\2\u00ca\u00c7"+
		"\3\2\2\2\u00cbD\3\2\2\2\u00cc\u00cd\7#\2\2\u00cd\u00d6\7?\2\2\u00ce\u00cf"+
		"\7p\2\2\u00cf\u00d0\7q\2\2\u00d0\u00d6\7v\2\2\u00d1\u00d2\7p\2\2\u00d2"+
		"\u00d3\7g\2\2\u00d3\u00d4\7s\2\2\u00d4\u00d6\7w\2\2\u00d5\u00cc\3\2\2"+
		"\2\u00d5\u00ce\3\2\2\2\u00d5\u00d1\3\2\2\2\u00d6F\3\2\2\2\u00d7\u00d8"+
		"\7(\2\2\u00d8\u00dd\7(\2\2\u00d9\u00da\7c\2\2\u00da\u00db\7p\2\2\u00db"+
		"\u00dd\7f\2\2\u00dc\u00d7\3\2\2\2\u00dc\u00d9\3\2\2\2\u00ddH\3\2\2\2\u00de"+
		"\u00df\7~\2\2\u00df\u00e3\7~\2\2\u00e0\u00e1\7q\2\2\u00e1\u00e3\7t\2\2"+
		"\u00e2\u00de\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3J\3\2\2\2\u00e4\u00e8\7"+
		">\2\2\u00e5\u00e6\7n\2\2\u00e6\u00e8\7v\2\2\u00e7\u00e4\3\2\2\2\u00e7"+
		"\u00e5\3\2\2\2\u00e8L\3\2\2\2\u00e9\u00ea\5K&\2\u00ea\u00eb\5\27\f\2\u00eb"+
		"\u00f0\3\2\2\2\u00ec\u00ed\7n\2\2\u00ed\u00ee\7v\2\2\u00ee\u00f0\7g\2"+
		"\2\u00ef\u00e9\3\2\2\2\u00ef\u00ec\3\2\2\2\u00f0N\3\2\2\2\u00f1\u00f5"+
		"\7@\2\2\u00f2\u00f3\7i\2\2\u00f3\u00f5\7v\2\2\u00f4\u00f1\3\2\2\2\u00f4"+
		"\u00f2\3\2\2\2\u00f5P\3\2\2\2\u00f6\u00f7\5O(\2\u00f7\u00f8\5\27\f\2\u00f8"+
		"\u00fd\3\2\2\2\u00f9\u00fa\7i\2\2\u00fa\u00fb\7v\2\2\u00fb\u00fd\7g\2"+
		"\2\u00fc\u00f6\3\2\2\2\u00fc\u00f9\3\2\2\2\u00fdR\3\2\2\2\u00fe\u00ff"+
		"\7h\2\2\u00ff\u0100\7p\2\2\u0100T\3\2\2\2\u0101\u0102\7n\2\2\u0102\u0103"+
		"\7q\2\2\u0103\u0104\7e\2\2\u0104\u0105\7c\2\2\u0105\u0106\7n\2\2\u0106"+
		"V\3\2\2\2\u0107\u0108\7t\2\2\u0108\u0109\7g\2\2\u0109\u010a\7v\2\2\u010a"+
		"X\3\2\2\2\u010b\u010d\5\'\24\2\u010c\u010b\3\2\2\2\u010c\u010d\3\2\2\2"+
		"\u010d\u010f\3\2\2\2\u010e\u0110\t\4\2\2\u010f\u010e\3\2\2\2\u0110\u0111"+
		"\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\3\2\2\2\u0113"+
		"\u0115\5\25\13\2\u0114\u0116\t\4\2\2\u0115\u0114\3\2\2\2\u0116\u0117\3"+
		"\2\2\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118Z\3\2\2\2\u0119\u011a"+
		"\7\62\2\2\u011a\u011b\7z\2\2\u011b\u011d\3\2\2\2\u011c\u011e\t\5\2\2\u011d"+
		"\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2"+
		"\2\2\u0120\u0128\3\2\2\2\u0121\u0123\t\5\2\2\u0122\u0121\3\2\2\2\u0123"+
		"\u0124\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\3\2"+
		"\2\2\u0126\u0128\7j\2\2\u0127\u0119\3\2\2\2\u0127\u0122\3\2\2\2\u0128"+
		"\\\3\2\2\2\u0129\u012b\5\'\24\2\u012a\u0129\3\2\2\2\u012a\u012b\3\2\2"+
		"\2\u012b\u012d\3\2\2\2\u012c\u012e\t\4\2\2\u012d\u012c\3\2\2\2\u012e\u012f"+
		"\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130^\3\2\2\2\u0131"+
		"\u0132\7^\2\2\u0132\u0138\7$\2\2\u0133\u0134\7^\2\2\u0134\u0138\7t\2\2"+
		"\u0135\u0136\7^\2\2\u0136\u0138\7p\2\2\u0137\u0131\3\2\2\2\u0137\u0133"+
		"\3\2\2\2\u0137\u0135\3\2\2\2\u0138`\3\2\2\2\u0139\u013e\7$\2\2\u013a\u013d"+
		"\5_\60\2\u013b\u013d\n\6\2\2\u013c\u013a\3\2\2\2\u013c\u013b\3\2\2\2\u013d"+
		"\u0140\3\2\2\2\u013e\u013f\3\2\2\2\u013e\u013c\3\2\2\2\u013f\u0141\3\2"+
		"\2\2\u0140\u013e\3\2\2\2\u0141\u0142\7$\2\2\u0142b\3\2\2\2\u0143\u0147"+
		"\t\7\2\2\u0144\u0146\t\b\2\2\u0145\u0144\3\2\2\2\u0146\u0149\3\2\2\2\u0147"+
		"\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148d\3\2\2\2\u0149\u0147\3\2\2\2"+
		"\32\2hmu\u00ca\u00d5\u00dc\u00e2\u00e7\u00ef\u00f4\u00fc\u010c\u0111\u0117"+
		"\u011f\u0124\u0127\u012a\u012f\u0137\u013c\u013e\u0147\4\2\3\2\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}