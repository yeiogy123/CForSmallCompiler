// $ANTLR 3.5.2 myCompiler.g 2022-06-18 22:33:03

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class myCompilerLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int T__36=36;
	public static final int BOOL=4;
	public static final int CHAR=5;
	public static final int COMMENT=6;
	public static final int CONST_FLOAT=7;
	public static final int CONST_INT=8;
	public static final int ConditionOP=9;
	public static final int ELSE=10;
	public static final int ERROR=11;
	public static final int EscapeSequence=12;
	public static final int FLOAT=13;
	public static final int FOR=14;
	public static final int Floating_point_constant=15;
	public static final int IF=16;
	public static final int INT=17;
	public static final int Identifier=18;
	public static final int Integer_constant=19;
	public static final int MAIN=20;
	public static final int PRINTF=21;
	public static final int STRING_LITERAL=22;
	public static final int VOID=23;
	public static final int WS=24;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public myCompilerLexer() {} 
	public myCompilerLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public myCompilerLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "myCompiler.g"; }

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:7:7: ( '&' )
			// myCompiler.g:7:9: '&'
			{
			match('&'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:8:7: ( '(' )
			// myCompiler.g:8:9: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:9:7: ( ')' )
			// myCompiler.g:9:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:10:7: ( '*' )
			// myCompiler.g:10:9: '*'
			{
			match('*'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:11:7: ( '+' )
			// myCompiler.g:11:9: '+'
			{
			match('+'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:12:7: ( ',' )
			// myCompiler.g:12:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:13:7: ( '-' )
			// myCompiler.g:13:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:14:7: ( '/' )
			// myCompiler.g:14:9: '/'
			{
			match('/'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:15:7: ( ';' )
			// myCompiler.g:15:9: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:16:7: ( '=' )
			// myCompiler.g:16:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:17:7: ( '{' )
			// myCompiler.g:17:9: '{'
			{
			match('{'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__35"

	// $ANTLR start "T__36"
	public final void mT__36() throws RecognitionException {
		try {
			int _type = T__36;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:18:7: ( '}' )
			// myCompiler.g:18:9: '}'
			{
			match('}'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__36"

	// $ANTLR start "FLOAT"
	public final void mFLOAT() throws RecognitionException {
		try {
			int _type = FLOAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:680:6: ( 'float' )
			// myCompiler.g:680:7: 'float'
			{
			match("float"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FLOAT"

	// $ANTLR start "INT"
	public final void mINT() throws RecognitionException {
		try {
			int _type = INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:681:4: ( 'int' )
			// myCompiler.g:681:5: 'int'
			{
			match("int"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "INT"

	// $ANTLR start "CHAR"
	public final void mCHAR() throws RecognitionException {
		try {
			int _type = CHAR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:682:5: ( 'char' )
			// myCompiler.g:682:7: 'char'
			{
			match("char"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CHAR"

	// $ANTLR start "MAIN"
	public final void mMAIN() throws RecognitionException {
		try {
			int _type = MAIN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:683:5: ( 'main' )
			// myCompiler.g:683:7: 'main'
			{
			match("main"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "MAIN"

	// $ANTLR start "CONST_INT"
	public final void mCONST_INT() throws RecognitionException {
		try {
			int _type = CONST_INT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:684:10: ( 'const int' )
			// myCompiler.g:684:12: 'const int'
			{
			match("const int"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CONST_INT"

	// $ANTLR start "CONST_FLOAT"
	public final void mCONST_FLOAT() throws RecognitionException {
		try {
			int _type = CONST_FLOAT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:685:12: ( 'const float' )
			// myCompiler.g:685:14: 'const float'
			{
			match("const float"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CONST_FLOAT"

	// $ANTLR start "BOOL"
	public final void mBOOL() throws RecognitionException {
		try {
			int _type = BOOL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:686:5: ( 'bool' )
			// myCompiler.g:686:7: 'bool'
			{
			match("bool"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "BOOL"

	// $ANTLR start "VOID"
	public final void mVOID() throws RecognitionException {
		try {
			int _type = VOID;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:687:5: ( 'void' )
			// myCompiler.g:687:7: 'void'
			{
			match("void"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "VOID"

	// $ANTLR start "PRINTF"
	public final void mPRINTF() throws RecognitionException {
		try {
			int _type = PRINTF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:688:7: ( 'printf' )
			// myCompiler.g:688:9: 'printf'
			{
			match("printf"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "PRINTF"

	// $ANTLR start "IF"
	public final void mIF() throws RecognitionException {
		try {
			int _type = IF;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:689:3: ( 'if' )
			// myCompiler.g:689:5: 'if'
			{
			match("if"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "IF"

	// $ANTLR start "ELSE"
	public final void mELSE() throws RecognitionException {
		try {
			int _type = ELSE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:690:5: ( 'else' )
			// myCompiler.g:690:7: 'else'
			{
			match("else"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ELSE"

	// $ANTLR start "FOR"
	public final void mFOR() throws RecognitionException {
		try {
			int _type = FOR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:691:4: ( 'for' )
			// myCompiler.g:691:6: 'for'
			{
			match("for"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "FOR"

	// $ANTLR start "ERROR"
	public final void mERROR() throws RecognitionException {
		try {
			int _type = ERROR;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:692:6: ( 'error' )
			// myCompiler.g:692:7: 'error'
			{
			match("error"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ERROR"

	// $ANTLR start "ConditionOP"
	public final void mConditionOP() throws RecognitionException {
		try {
			int _type = ConditionOP;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:694:12: ( '>' | '>=' | '<' | '<=' | '==' | '!=' )
			int alt1=6;
			switch ( input.LA(1) ) {
			case '>':
				{
				int LA1_1 = input.LA(2);
				if ( (LA1_1=='=') ) {
					alt1=2;
				}

				else {
					alt1=1;
				}

				}
				break;
			case '<':
				{
				int LA1_2 = input.LA(2);
				if ( (LA1_2=='=') ) {
					alt1=4;
				}

				else {
					alt1=3;
				}

				}
				break;
			case '=':
				{
				alt1=5;
				}
				break;
			case '!':
				{
				alt1=6;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}
			switch (alt1) {
				case 1 :
					// myCompiler.g:694:14: '>'
					{
					match('>'); 
					}
					break;
				case 2 :
					// myCompiler.g:694:19: '>='
					{
					match(">="); 

					}
					break;
				case 3 :
					// myCompiler.g:694:26: '<'
					{
					match('<'); 
					}
					break;
				case 4 :
					// myCompiler.g:694:32: '<='
					{
					match("<="); 

					}
					break;
				case 5 :
					// myCompiler.g:694:39: '=='
					{
					match("=="); 

					}
					break;
				case 6 :
					// myCompiler.g:694:46: '!='
					{
					match("!="); 

					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ConditionOP"

	// $ANTLR start "Identifier"
	public final void mIdentifier() throws RecognitionException {
		try {
			int _type = Identifier;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:696:11: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )* )
			// myCompiler.g:696:12: ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			{
			if ( (input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			// myCompiler.g:696:36: ( 'a' .. 'z' | 'A' .. 'Z' | '0' .. '9' | '_' )*
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// myCompiler.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop2;
				}
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Identifier"

	// $ANTLR start "Integer_constant"
	public final void mInteger_constant() throws RecognitionException {
		try {
			int _type = Integer_constant;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:697:17: ( ( '0' .. '9' )+ )
			// myCompiler.g:697:18: ( '0' .. '9' )+
			{
			// myCompiler.g:697:18: ( '0' .. '9' )+
			int cnt3=0;
			loop3:
			while (true) {
				int alt3=2;
				int LA3_0 = input.LA(1);
				if ( ((LA3_0 >= '0' && LA3_0 <= '9')) ) {
					alt3=1;
				}

				switch (alt3) {
				case 1 :
					// myCompiler.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt3 >= 1 ) break loop3;
					EarlyExitException eee = new EarlyExitException(3, input);
					throw eee;
				}
				cnt3++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Integer_constant"

	// $ANTLR start "Floating_point_constant"
	public final void mFloating_point_constant() throws RecognitionException {
		try {
			int _type = Floating_point_constant;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:698:24: ( ( '0' .. '9' )+ '.' ( '0' .. '9' )+ )
			// myCompiler.g:698:25: ( '0' .. '9' )+ '.' ( '0' .. '9' )+
			{
			// myCompiler.g:698:25: ( '0' .. '9' )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '0' && LA4_0 <= '9')) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// myCompiler.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			match('.'); 
			// myCompiler.g:698:39: ( '0' .. '9' )+
			int cnt5=0;
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( ((LA5_0 >= '0' && LA5_0 <= '9')) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// myCompiler.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt5 >= 1 ) break loop5;
					EarlyExitException eee = new EarlyExitException(5, input);
					throw eee;
				}
				cnt5++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "Floating_point_constant"

	// $ANTLR start "STRING_LITERAL"
	public final void mSTRING_LITERAL() throws RecognitionException {
		try {
			int _type = STRING_LITERAL;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:701:5: ( '\"' ( EscapeSequence |~ ( '\\\\' | '\"' ) )* '\"' )
			// myCompiler.g:701:8: '\"' ( EscapeSequence |~ ( '\\\\' | '\"' ) )* '\"'
			{
			match('\"'); 
			// myCompiler.g:701:12: ( EscapeSequence |~ ( '\\\\' | '\"' ) )*
			loop6:
			while (true) {
				int alt6=3;
				int LA6_0 = input.LA(1);
				if ( (LA6_0=='\\') ) {
					alt6=1;
				}
				else if ( ((LA6_0 >= '\u0000' && LA6_0 <= '!')||(LA6_0 >= '#' && LA6_0 <= '[')||(LA6_0 >= ']' && LA6_0 <= '\uFFFF')) ) {
					alt6=2;
				}

				switch (alt6) {
				case 1 :
					// myCompiler.g:701:14: EscapeSequence
					{
					mEscapeSequence(); 

					}
					break;
				case 2 :
					// myCompiler.g:701:31: ~ ( '\\\\' | '\"' )
					{
					if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					break loop6;
				}
			}

			match('\"'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "STRING_LITERAL"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:704:3: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
			// myCompiler.g:704:4: ( ' ' | '\\t' | '\\r' | '\\n' )
			{
			if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "COMMENT"
	public final void mCOMMENT() throws RecognitionException {
		try {
			int _type = COMMENT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// myCompiler.g:705:8: ( '/*' ( . )* '*/' )
			// myCompiler.g:705:9: '/*' ( . )* '*/'
			{
			match("/*"); 

			// myCompiler.g:705:14: ( . )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0=='*') ) {
					int LA7_1 = input.LA(2);
					if ( (LA7_1=='/') ) {
						alt7=2;
					}
					else if ( ((LA7_1 >= '\u0000' && LA7_1 <= '.')||(LA7_1 >= '0' && LA7_1 <= '\uFFFF')) ) {
						alt7=1;
					}

				}
				else if ( ((LA7_0 >= '\u0000' && LA7_0 <= ')')||(LA7_0 >= '+' && LA7_0 <= '\uFFFF')) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// myCompiler.g:705:14: .
					{
					matchAny(); 
					}
					break;

				default :
					break loop7;
				}
			}

			match("*/"); 

			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMENT"

	// $ANTLR start "EscapeSequence"
	public final void mEscapeSequence() throws RecognitionException {
		try {
			// myCompiler.g:711:5: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' ) )
			// myCompiler.g:711:9: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\\\"' | '\\'' | '\\\\' )
			{
			match('\\'); 
			if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			}

		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "EscapeSequence"

	@Override
	public void mTokens() throws RecognitionException {
		// myCompiler.g:1:8: ( T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | FLOAT | INT | CHAR | MAIN | CONST_INT | CONST_FLOAT | BOOL | VOID | PRINTF | IF | ELSE | FOR | ERROR | ConditionOP | Identifier | Integer_constant | Floating_point_constant | STRING_LITERAL | WS | COMMENT )
		int alt8=32;
		alt8 = dfa8.predict(input);
		switch (alt8) {
			case 1 :
				// myCompiler.g:1:10: T__25
				{
				mT__25(); 

				}
				break;
			case 2 :
				// myCompiler.g:1:16: T__26
				{
				mT__26(); 

				}
				break;
			case 3 :
				// myCompiler.g:1:22: T__27
				{
				mT__27(); 

				}
				break;
			case 4 :
				// myCompiler.g:1:28: T__28
				{
				mT__28(); 

				}
				break;
			case 5 :
				// myCompiler.g:1:34: T__29
				{
				mT__29(); 

				}
				break;
			case 6 :
				// myCompiler.g:1:40: T__30
				{
				mT__30(); 

				}
				break;
			case 7 :
				// myCompiler.g:1:46: T__31
				{
				mT__31(); 

				}
				break;
			case 8 :
				// myCompiler.g:1:52: T__32
				{
				mT__32(); 

				}
				break;
			case 9 :
				// myCompiler.g:1:58: T__33
				{
				mT__33(); 

				}
				break;
			case 10 :
				// myCompiler.g:1:64: T__34
				{
				mT__34(); 

				}
				break;
			case 11 :
				// myCompiler.g:1:70: T__35
				{
				mT__35(); 

				}
				break;
			case 12 :
				// myCompiler.g:1:76: T__36
				{
				mT__36(); 

				}
				break;
			case 13 :
				// myCompiler.g:1:82: FLOAT
				{
				mFLOAT(); 

				}
				break;
			case 14 :
				// myCompiler.g:1:88: INT
				{
				mINT(); 

				}
				break;
			case 15 :
				// myCompiler.g:1:92: CHAR
				{
				mCHAR(); 

				}
				break;
			case 16 :
				// myCompiler.g:1:97: MAIN
				{
				mMAIN(); 

				}
				break;
			case 17 :
				// myCompiler.g:1:102: CONST_INT
				{
				mCONST_INT(); 

				}
				break;
			case 18 :
				// myCompiler.g:1:112: CONST_FLOAT
				{
				mCONST_FLOAT(); 

				}
				break;
			case 19 :
				// myCompiler.g:1:124: BOOL
				{
				mBOOL(); 

				}
				break;
			case 20 :
				// myCompiler.g:1:129: VOID
				{
				mVOID(); 

				}
				break;
			case 21 :
				// myCompiler.g:1:134: PRINTF
				{
				mPRINTF(); 

				}
				break;
			case 22 :
				// myCompiler.g:1:141: IF
				{
				mIF(); 

				}
				break;
			case 23 :
				// myCompiler.g:1:144: ELSE
				{
				mELSE(); 

				}
				break;
			case 24 :
				// myCompiler.g:1:149: FOR
				{
				mFOR(); 

				}
				break;
			case 25 :
				// myCompiler.g:1:153: ERROR
				{
				mERROR(); 

				}
				break;
			case 26 :
				// myCompiler.g:1:159: ConditionOP
				{
				mConditionOP(); 

				}
				break;
			case 27 :
				// myCompiler.g:1:171: Identifier
				{
				mIdentifier(); 

				}
				break;
			case 28 :
				// myCompiler.g:1:182: Integer_constant
				{
				mInteger_constant(); 

				}
				break;
			case 29 :
				// myCompiler.g:1:199: Floating_point_constant
				{
				mFloating_point_constant(); 

				}
				break;
			case 30 :
				// myCompiler.g:1:223: STRING_LITERAL
				{
				mSTRING_LITERAL(); 

				}
				break;
			case 31 :
				// myCompiler.g:1:238: WS
				{
				mWS(); 

				}
				break;
			case 32 :
				// myCompiler.g:1:241: COMMENT
				{
				mCOMMENT(); 

				}
				break;

		}
	}


	protected DFA8 dfa8 = new DFA8(this);
	static final String DFA8_eotS =
		"\10\uffff\1\33\1\uffff\1\34\2\uffff\10\26\2\uffff\1\51\5\uffff\3\26\1"+
		"\56\10\26\2\uffff\1\26\1\70\1\71\1\uffff\11\26\2\uffff\1\103\1\26\1\105"+
		"\1\106\1\107\1\26\1\111\1\26\1\113\1\uffff\1\26\3\uffff\1\26\1\uffff\1"+
		"\116\2\uffff\1\121\4\uffff";
	static final String DFA8_eofS =
		"\122\uffff";
	static final String DFA8_minS =
		"\1\11\7\uffff\1\52\1\uffff\1\75\2\uffff\1\154\1\146\1\150\1\141\2\157"+
		"\1\162\1\154\2\uffff\1\56\5\uffff\1\157\1\162\1\164\1\60\1\141\1\156\1"+
		"\151\1\157\2\151\1\163\1\162\2\uffff\1\141\2\60\1\uffff\1\162\1\163\1"+
		"\156\1\154\1\144\1\156\1\145\1\157\1\164\2\uffff\1\60\1\164\3\60\1\164"+
		"\1\60\1\162\1\60\1\uffff\1\40\3\uffff\1\146\1\uffff\1\60\1\uffff\1\146"+
		"\1\60\4\uffff";
	static final String DFA8_maxS =
		"\1\175\7\uffff\1\52\1\uffff\1\75\2\uffff\1\157\1\156\1\157\1\141\2\157"+
		"\2\162\2\uffff\1\71\5\uffff\1\157\1\162\1\164\1\172\1\141\1\156\1\151"+
		"\1\157\2\151\1\163\1\162\2\uffff\1\141\2\172\1\uffff\1\162\1\163\1\156"+
		"\1\154\1\144\1\156\1\145\1\157\1\164\2\uffff\1\172\1\164\3\172\1\164\1"+
		"\172\1\162\1\172\1\uffff\1\40\3\uffff\1\146\1\uffff\1\172\1\uffff\1\151"+
		"\1\172\4\uffff";
	static final String DFA8_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\uffff\1\11\1\uffff\1\13\1\14\10"+
		"\uffff\1\32\1\33\1\uffff\1\36\1\37\1\40\1\10\1\12\14\uffff\1\34\1\35\3"+
		"\uffff\1\26\11\uffff\1\30\1\16\11\uffff\1\17\1\uffff\1\20\1\23\1\24\1"+
		"\uffff\1\27\1\uffff\1\15\2\uffff\1\31\1\21\1\22\1\25";
	static final String DFA8_specialS =
		"\122\uffff}>";
	static final String[] DFA8_transitionS = {
			"\2\31\2\uffff\1\31\22\uffff\1\31\1\25\1\30\3\uffff\1\1\1\uffff\1\2\1"+
			"\3\1\4\1\5\1\6\1\7\1\uffff\1\10\12\27\1\uffff\1\11\1\25\1\12\1\25\2\uffff"+
			"\32\26\4\uffff\1\26\1\uffff\1\26\1\21\1\17\1\26\1\24\1\15\2\26\1\16\3"+
			"\26\1\20\2\26\1\23\5\26\1\22\4\26\1\13\1\uffff\1\14",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\32",
			"",
			"\1\25",
			"",
			"",
			"\1\35\2\uffff\1\36",
			"\1\40\7\uffff\1\37",
			"\1\41\6\uffff\1\42",
			"\1\43",
			"\1\44",
			"\1\45",
			"\1\46",
			"\1\47\5\uffff\1\50",
			"",
			"",
			"\1\52\1\uffff\12\27",
			"",
			"",
			"",
			"",
			"",
			"\1\53",
			"\1\54",
			"\1\55",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\1\57",
			"\1\60",
			"\1\61",
			"\1\62",
			"\1\63",
			"\1\64",
			"\1\65",
			"\1\66",
			"",
			"",
			"\1\67",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"",
			"\1\72",
			"\1\73",
			"\1\74",
			"\1\75",
			"\1\76",
			"\1\77",
			"\1\100",
			"\1\101",
			"\1\102",
			"",
			"",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\1\104",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\1\110",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"\1\112",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"",
			"\1\114",
			"",
			"",
			"",
			"\1\115",
			"",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"",
			"\1\120\2\uffff\1\117",
			"\12\26\7\uffff\32\26\4\uffff\1\26\1\uffff\32\26",
			"",
			"",
			"",
			""
	};

	static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
	static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
	static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
	static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
	static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
	static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
	static final short[][] DFA8_transition;

	static {
		int numStates = DFA8_transitionS.length;
		DFA8_transition = new short[numStates][];
		for (int i=0; i<numStates; i++) {
			DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
		}
	}

	protected class DFA8 extends DFA {

		public DFA8(BaseRecognizer recognizer) {
			this.recognizer = recognizer;
			this.decisionNumber = 8;
			this.eot = DFA8_eot;
			this.eof = DFA8_eof;
			this.min = DFA8_min;
			this.max = DFA8_max;
			this.accept = DFA8_accept;
			this.special = DFA8_special;
			this.transition = DFA8_transition;
		}
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | FLOAT | INT | CHAR | MAIN | CONST_INT | CONST_FLOAT | BOOL | VOID | PRINTF | IF | ELSE | FOR | ERROR | ConditionOP | Identifier | Integer_constant | Floating_point_constant | STRING_LITERAL | WS | COMMENT );";
		}
	}

}
