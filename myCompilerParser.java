// $ANTLR 3.5.2 myCompiler.g 2022-06-18 22:33:03

    // import packages here.
    import java.util.HashMap;
    import java.util.ArrayList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class myCompilerParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "BOOL", "CHAR", "COMMENT", "CONST_FLOAT", 
		"CONST_INT", "ConditionOP", "ELSE", "ERROR", "EscapeSequence", "FLOAT", 
		"FOR", "Floating_point_constant", "IF", "INT", "Identifier", "Integer_constant", 
		"MAIN", "PRINTF", "STRING_LITERAL", "VOID", "WS", "'&'", "'('", "')'", 
		"'*'", "'+'", "','", "'-'", "'/'", "';'", "'='", "'{'", "'}'"
	};
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
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public myCompilerParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public myCompilerParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return myCompilerParser.tokenNames; }
	@Override public String getGrammarFileName() { return "myCompiler.g"; }


	    boolean TRACEON = false;

	    // Type info
	    public enum Type{
	       INT, CONST_INT, FLOAT, CONST_FLOAT, CHAR, VOID, ERROR, BOOL;
	    }

	    // Record the info of a var OR a cons.
	    class TheVar {
		int   varIndex; // Ex: t1, t2, ...
		int   iValue;   // Ex: 123
		float fValue;   // Ex: 2.314
	    };

	    class Info {
		Type theType;  // type info
		TheVar theVar;
		
		//constructor for Info
		Info(){
			theType = Type.ERROR;
			theVar = new TheVar();
		}
	    };


	    // TextCode is a set of assembly instruction code.
	    List<String> TextCode = new ArrayList<String>();

	    //A symbol table, where ArrayList is easy to extend.

	    HashMap<String, Info> symbolTable = new HashMap<String, Info>();

	    // labelNum represent temporary label.
	    // The index initialize to 0.
	    int labelNum = 0;
		
	    // varCount represent temporary var.
	    // The index initialize to 0.
	    int varCount = 0;

	    // condCount represent temporary variables.
	    // The index initialize is 0.
	    int condCount = 0;
	    

	    /*
	     * Output prologue.
	     */
	    void prologue()
	    {
	       	TextCode.add("declare dso_local i32 @printf(i8*, ...)\n");
	       	TextCode.add("@.str = private unnamed_addr constant [4 x i8] c\"%d\\0A\\00\"");       	
	       	TextCode.add("@.str.1 = private unnamed_addr constant [4 x i8] c\"%f\\0A\\00\"");
	       	TextCode.add("@.str.2 = private unnamed_addr constant [6 x i8] c\"Hello\\00\"");
		TextCode.add("define dso_local i32 @main()");
		TextCode.add("{");
	    }
	    
		
	    /*
	     * Output epilogue.
	     */
	    void epilogue()
	    {
	       /* handle epilogue */
		TextCode.add("ret i32 0");
	       	TextCode.add("}");
	    }
	    
	    
	    /* Generate a new label */
	    String newLabel()
	    {
	       labelNum ++;
	       return (new String("L")) + Integer.toString(labelNum);
	    }  
	    
	    
	    public List<String> getTextCode()
	    {
	       return TextCode;
	    }



	// $ANTLR start "program"
	// myCompiler.g:99:1: program : VOID MAIN '(' ')' '{' declarations statements '}' ;
	public final void program() throws RecognitionException {
		try {
			// myCompiler.g:99:8: ( VOID MAIN '(' ')' '{' declarations statements '}' )
			// myCompiler.g:99:10: VOID MAIN '(' ')' '{' declarations statements '}'
			{
			match(input,VOID,FOLLOW_VOID_in_program36); 
			match(input,MAIN,FOLLOW_MAIN_in_program38); 
			match(input,26,FOLLOW_26_in_program40); 
			match(input,27,FOLLOW_27_in_program42); 

			           /* Output function prologue */
			           prologue();
			        
			match(input,35,FOLLOW_35_in_program63); 
			pushFollow(FOLLOW_declarations_in_program77);
			declarations();
			state._fsp--;

			pushFollow(FOLLOW_statements_in_program90);
			statements();
			state._fsp--;

			match(input,36,FOLLOW_36_in_program100); 

				   if (TRACEON)
				      System.out.println("VOID MAIN () {declarations statements}");

			           /* output function epilogue */	  
			           epilogue();
			        
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "program"



	// $ANTLR start "declarations"
	// myCompiler.g:119:1: declarations : ( type Identifier ';' declarations |);
	public final void declarations() throws RecognitionException {
		Token Identifier1=null;
		Type type2 =null;

		try {
			// myCompiler.g:119:13: ( type Identifier ';' declarations |)
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( ((LA1_0 >= BOOL && LA1_0 <= CHAR)||(LA1_0 >= CONST_FLOAT && LA1_0 <= CONST_INT)||LA1_0==ERROR||LA1_0==FLOAT||LA1_0==INT||LA1_0==VOID) ) {
				alt1=1;
			}
			else if ( (LA1_0==IF||LA1_0==Identifier||LA1_0==PRINTF||LA1_0==36) ) {
				alt1=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}

			switch (alt1) {
				case 1 :
					// myCompiler.g:119:15: type Identifier ';' declarations
					{
					pushFollow(FOLLOW_type_in_declarations127);
					type2=type();
					state._fsp--;

					Identifier1=(Token)match(input,Identifier,FOLLOW_Identifier_in_declarations129); 
					match(input,33,FOLLOW_33_in_declarations131); 
					pushFollow(FOLLOW_declarations_in_declarations133);
					declarations();
					state._fsp--;


					           if (TRACEON)
					              System.out.println("declarations: type Identifier : declarations");

					           if (symbolTable.containsKey((Identifier1!=null?Identifier1.getText():null))) {
					              // variable re-declared.
					              System.out.println("Type Error: " + 
					                                  Identifier1.getLine() + 
					                                 ": Redeclared identifier.");
					              System.exit(0);
					           }
					                 
					           /* Add ID and its info into the symbol table. */
						       Info the_entry = new Info();
						       the_entry.theType = type2;
						       the_entry.theVar.varIndex = varCount;
						       varCount ++;
						       symbolTable.put((Identifier1!=null?Identifier1.getText():null), the_entry);

					           // issue the instruction.
					           // Ex: %a = alloca i32, align 4
					           //INT
					           if (type2 == Type.INT) { 
					              TextCode.add("%t" + the_entry.theVar.varIndex + " = alloca i32, align 4");
					           }
					           //FLOAT
						   if (type2 == Type.FLOAT){ 
					              TextCode.add("%t" + the_entry.theVar.varIndex + " = alloca float, align 4");
					           }
						  
					        
					}
					break;
				case 2 :
					// myCompiler.g:152:9: 
					{

					           if (TRACEON)
					              System.out.println("declarations: ");
					        
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "declarations"



	// $ANTLR start "type"
	// myCompiler.g:159:1: type returns [Type attr_type] : ( INT | CHAR | FLOAT | VOID | CONST_INT | CONST_FLOAT | BOOL | ERROR );
	public final Type type() throws RecognitionException {
		Type attr_type = null;


		try {
			// myCompiler.g:161:5: ( INT | CHAR | FLOAT | VOID | CONST_INT | CONST_FLOAT | BOOL | ERROR )
			int alt2=8;
			switch ( input.LA(1) ) {
			case INT:
				{
				alt2=1;
				}
				break;
			case CHAR:
				{
				alt2=2;
				}
				break;
			case FLOAT:
				{
				alt2=3;
				}
				break;
			case VOID:
				{
				alt2=4;
				}
				break;
			case CONST_INT:
				{
				alt2=5;
				}
				break;
			case CONST_FLOAT:
				{
				alt2=6;
				}
				break;
			case BOOL:
				{
				alt2=7;
				}
				break;
			case ERROR:
				{
				alt2=8;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}
			switch (alt2) {
				case 1 :
					// myCompiler.g:161:7: INT
					{
					match(input,INT,FOLLOW_INT_in_type190); 
					 if (TRACEON) System.out.println("type: INT"); attr_type =Type.INT; 
					}
					break;
				case 2 :
					// myCompiler.g:162:7: CHAR
					{
					match(input,CHAR,FOLLOW_CHAR_in_type200); 
					 if (TRACEON) System.out.println("type: CHAR"); attr_type =Type.CHAR; 
					}
					break;
				case 3 :
					// myCompiler.g:163:7: FLOAT
					{
					match(input,FLOAT,FOLLOW_FLOAT_in_type210); 
					if (TRACEON) System.out.println("type: FLOAT"); attr_type =Type.FLOAT; 
					}
					break;
				case 4 :
					// myCompiler.g:164:7: VOID
					{
					match(input,VOID,FOLLOW_VOID_in_type220); 
					if (TRACEON) System.out.println("type: VOID"); attr_type =Type.VOID; 
					}
					break;
				case 5 :
					// myCompiler.g:165:7: CONST_INT
					{
					match(input,CONST_INT,FOLLOW_CONST_INT_in_type230); 
					if (TRACEON) System.out.println("type: CONST_INT"); attr_type =Type.CONST_INT; 
					}
					break;
				case 6 :
					// myCompiler.g:166:7: CONST_FLOAT
					{
					match(input,CONST_FLOAT,FOLLOW_CONST_FLOAT_in_type240); 
					if (TRACEON) System.out.println("type: CONST_FLOAT"); attr_type =Type.CONST_FLOAT; 
					}
					break;
				case 7 :
					// myCompiler.g:167:7: BOOL
					{
					match(input,BOOL,FOLLOW_BOOL_in_type250); 
					if (TRACEON) System.out.println("type: BOOL"); attr_type =Type.BOOL; 
					}
					break;
				case 8 :
					// myCompiler.g:168:7: ERROR
					{
					match(input,ERROR,FOLLOW_ERROR_in_type260); 
					if (TRACEON) System.out.println("type: ERROR"); attr_type =Type.ERROR; 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return attr_type;
	}
	// $ANTLR end "type"



	// $ANTLR start "statements"
	// myCompiler.g:172:1: statements : ( statement statements |);
	public final void statements() throws RecognitionException {
		try {
			// myCompiler.g:172:11: ( statement statements |)
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==IF||LA3_0==Identifier||LA3_0==PRINTF) ) {
				alt3=1;
			}
			else if ( (LA3_0==36) ) {
				alt3=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// myCompiler.g:172:12: statement statements
					{
					pushFollow(FOLLOW_statement_in_statements271);
					statement();
					state._fsp--;

					pushFollow(FOLLOW_statements_in_statements273);
					statements();
					state._fsp--;

					}
					break;
				case 2 :
					// myCompiler.g:174:11: 
					{
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "statements"



	// $ANTLR start "statement"
	// myCompiler.g:177:1: statement : ( assign_stmt ';' | if_stmt | func_no_return_stmt ';' | print_function ';' );
	public final void statement() throws RecognitionException {
		try {
			// myCompiler.g:177:10: ( assign_stmt ';' | if_stmt | func_no_return_stmt ';' | print_function ';' )
			int alt4=4;
			switch ( input.LA(1) ) {
			case Identifier:
				{
				int LA4_1 = input.LA(2);
				if ( (LA4_1==34) ) {
					alt4=1;
				}
				else if ( (LA4_1==26) ) {
					alt4=3;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case IF:
				{
				alt4=2;
				}
				break;
			case PRINTF:
				{
				alt4=4;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}
			switch (alt4) {
				case 1 :
					// myCompiler.g:177:12: assign_stmt ';'
					{
					pushFollow(FOLLOW_assign_stmt_in_statement304);
					assign_stmt();
					state._fsp--;

					match(input,33,FOLLOW_33_in_statement306); 
					}
					break;
				case 2 :
					// myCompiler.g:178:12: if_stmt
					{
					pushFollow(FOLLOW_if_stmt_in_statement319);
					if_stmt();
					state._fsp--;

					}
					break;
				case 3 :
					// myCompiler.g:179:12: func_no_return_stmt ';'
					{
					pushFollow(FOLLOW_func_no_return_stmt_in_statement332);
					func_no_return_stmt();
					state._fsp--;

					match(input,33,FOLLOW_33_in_statement334); 
					}
					break;
				case 4 :
					// myCompiler.g:180:12: print_function ';'
					{
					pushFollow(FOLLOW_print_function_in_statement347);
					print_function();
					state._fsp--;

					match(input,33,FOLLOW_33_in_statement349); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "statement"



	// $ANTLR start "print_function"
	// myCompiler.g:184:1: print_function : ( PRINTF '(' STRING_LITERAL ',' Identifier ')' | PRINTF '(' STRING_LITERAL ')' );
	public final void print_function() throws RecognitionException {
		Token Identifier3=null;

		try {
			// myCompiler.g:186:13: ( PRINTF '(' STRING_LITERAL ',' Identifier ')' | PRINTF '(' STRING_LITERAL ')' )
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==PRINTF) ) {
				int LA5_1 = input.LA(2);
				if ( (LA5_1==26) ) {
					int LA5_2 = input.LA(3);
					if ( (LA5_2==STRING_LITERAL) ) {
						int LA5_3 = input.LA(4);
						if ( (LA5_3==30) ) {
							alt5=1;
						}
						else if ( (LA5_3==27) ) {
							alt5=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 5, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 5, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 5, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 5, 0, input);
				throw nvae;
			}

			switch (alt5) {
				case 1 :
					// myCompiler.g:186:15: PRINTF '(' STRING_LITERAL ',' Identifier ')'
					{
					match(input,PRINTF,FOLLOW_PRINTF_in_print_function384); 
					match(input,26,FOLLOW_26_in_print_function386); 
					match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_print_function388); 
					match(input,30,FOLLOW_30_in_print_function390); 
					Identifier3=(Token)match(input,Identifier,FOLLOW_Identifier_in_print_function392); 
					match(input,27,FOLLOW_27_in_print_function394); 

					               	Type the_type = symbolTable.get((Identifier3!=null?Identifier3.getText():null)).theType;	
						       	int vIndex = symbolTable.get((Identifier3!=null?Identifier3.getText():null)).theVar.varIndex;	
							Info the_entry = new Info();	
					               	if(the_type == Type.INT)
						       	{
							    TextCode.add("%t" + varCount + "=load i32, i32* %t" + vIndex);
					                    	    vIndex = varCount++;
							    the_entry.theType = Type.INT;
					                    	    the_entry.theVar.varIndex = varCount++;
					                    	    TextCode.add("%t" + the_entry.theVar.varIndex+ " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str, i64 0, i64 0), i32 %t" + vIndex + ")");
					               	}else if(the_type == Type.FLOAT){
							    TextCode.add("%t" + varCount + " =load float, float* %t" + vIndex);
					                    	    vIndex = varCount++;
					                    	    TextCode.add("%t" + varCount + " = fpext float %t" + vIndex + " to double");
					                    	    vIndex = varCount++;
							    the_entry.theType = Type.FLOAT;
					                    	    the_entry.theVar.varIndex = varCount++;
					                    	    TextCode.add("%t" + the_entry.theVar.varIndex + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i64 0, i64 0), double %t" + vIndex + ")");
					               	}
					            
					}
					break;
				case 2 :
					// myCompiler.g:208:15: PRINTF '(' STRING_LITERAL ')'
					{
					match(input,PRINTF,FOLLOW_PRINTF_in_print_function424); 
					match(input,26,FOLLOW_26_in_print_function426); 
					match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_print_function428); 
					match(input,27,FOLLOW_27_in_print_function430); 

					                Info the_entry = new Info();
						        the_entry.theType = Type.INT;
						        the_entry.theVar.varIndex = varCount++;
					                TextCode.add("%t"+the_entry.theVar.varIndex + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([6 x i8], [6 x i8]* @.str.2, i64 0, i64 0))");
					            
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "print_function"



	// $ANTLR start "if_stmt"
	// myCompiler.g:219:1: if_stmt : IF '(' cond= cond_expression ')' block_stmt ( ELSE block_stmt )? ;
	public final void if_stmt() throws RecognitionException {
		Info cond =null;

		try {
			// myCompiler.g:220:13: ( IF '(' cond= cond_expression ')' block_stmt ( ELSE block_stmt )? )
			// myCompiler.g:220:15: IF '(' cond= cond_expression ')' block_stmt ( ELSE block_stmt )?
			{
			match(input,IF,FOLLOW_IF_in_if_stmt482); 
			match(input,26,FOLLOW_26_in_if_stmt484); 
			pushFollow(FOLLOW_cond_expression_in_if_stmt488);
			cond=cond_expression();
			state._fsp--;

			match(input,27,FOLLOW_27_in_if_stmt490); 

			               String Ltrue = newLabel();
			               String Lfalse = newLabel();
			               String Lend = newLabel();
			               TextCode.add("br i1 %cond" + cond.theVar.varIndex + ", label %" + Ltrue + ", label %" + Lfalse);
			               
			               //Ltrue's label
			               TextCode.add(Ltrue + ":");
			            
			pushFollow(FOLLOW_block_stmt_in_if_stmt518);
			block_stmt();
			state._fsp--;


			            	//branch to Lend's label
			               TextCode.add("br label %" + Lend);
			              //Lfalse's label
			               TextCode.add(Lfalse + ":");
			            
			// myCompiler.g:238:13: ( ELSE block_stmt )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==ELSE) ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// myCompiler.g:238:14: ELSE block_stmt
					{
					match(input,ELSE,FOLLOW_ELSE_in_if_stmt560); 
					pushFollow(FOLLOW_block_stmt_in_if_stmt562);
					block_stmt();
					state._fsp--;


					       	    	//branch to Lend's label
					               	TextCode.add("br label %" + Lend);
					               	//Lend's label
					               	TextCode.add(Lend + ":");
					            	    
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "if_stmt"



	// $ANTLR start "block_stmt"
	// myCompiler.g:250:1: block_stmt : '{' statements '}' ;
	public final void block_stmt() throws RecognitionException {
		try {
			// myCompiler.g:250:11: ( '{' statements '}' )
			// myCompiler.g:250:13: '{' statements '}'
			{
			match(input,35,FOLLOW_35_in_block_stmt620); 
			pushFollow(FOLLOW_statements_in_block_stmt622);
			statements();
			state._fsp--;

			match(input,36,FOLLOW_36_in_block_stmt624); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "block_stmt"



	// $ANTLR start "assign_stmt"
	// myCompiler.g:254:1: assign_stmt : Identifier '=' arith_expression ;
	public final void assign_stmt() throws RecognitionException {
		Token Identifier5=null;
		Info arith_expression4 =null;

		try {
			// myCompiler.g:254:12: ( Identifier '=' arith_expression )
			// myCompiler.g:254:14: Identifier '=' arith_expression
			{
			Identifier5=(Token)match(input,Identifier,FOLLOW_Identifier_in_assign_stmt636); 
			match(input,34,FOLLOW_34_in_assign_stmt638); 
			pushFollow(FOLLOW_arith_expression_in_assign_stmt640);
			arith_expression4=arith_expression();
			state._fsp--;


			                Info theRHS = arith_expression4;
				  Info theLHS = symbolTable.get((Identifier5!=null?Identifier5.getText():null));
			                if ((theLHS.theType == Type.INT) && (theRHS.theType == Type.INT)){		   
			                   TextCode.add("store i32 %t" + theRHS.theVar.varIndex + ", i32* %t" + theLHS.theVar.varIndex);
				  }else if ((theLHS.theType == Type.INT) && (theRHS.theType == Type.CONST_INT)){
			                   TextCode.add("store i32 " + theRHS.theVar.iValue + ", i32* %t" + theLHS.theVar.varIndex);	
				  }else if ((theLHS.theType == Type.FLOAT) && (theRHS.theType == Type.FLOAT)){		   
			                   TextCode.add("store float %t" + theRHS.theVar.varIndex + ", float* %t" + theLHS.theVar.varIndex);
				  }else if ((theLHS.theType == Type.FLOAT) && (theRHS.theType == Type.CONST_FLOAT)){		   
			                   double value = theRHS.theVar.fValue;
			                   long answer = Double.doubleToLongBits(value);
			                   String FloatValue = Long.toHexString(answer);
			                   TextCode.add("store float 0x" + FloatValue + ", float* %t" + theLHS.theVar.varIndex);
			            	  }
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "assign_stmt"



	// $ANTLR start "func_no_return_stmt"
	// myCompiler.g:274:1: func_no_return_stmt : Identifier '(' argument ')' ;
	public final void func_no_return_stmt() throws RecognitionException {
		try {
			// myCompiler.g:274:20: ( Identifier '(' argument ')' )
			// myCompiler.g:274:22: Identifier '(' argument ')'
			{
			match(input,Identifier,FOLLOW_Identifier_in_func_no_return_stmt682); 
			match(input,26,FOLLOW_26_in_func_no_return_stmt684); 
			pushFollow(FOLLOW_argument_in_func_no_return_stmt686);
			argument();
			state._fsp--;

			match(input,27,FOLLOW_27_in_func_no_return_stmt688); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "func_no_return_stmt"



	// $ANTLR start "argument"
	// myCompiler.g:278:1: argument : arg ( ',' arg )* ;
	public final void argument() throws RecognitionException {
		try {
			// myCompiler.g:278:9: ( arg ( ',' arg )* )
			// myCompiler.g:278:11: arg ( ',' arg )*
			{
			pushFollow(FOLLOW_arg_in_argument716);
			arg();
			state._fsp--;

			// myCompiler.g:278:15: ( ',' arg )*
			loop7:
			while (true) {
				int alt7=2;
				int LA7_0 = input.LA(1);
				if ( (LA7_0==30) ) {
					alt7=1;
				}

				switch (alt7) {
				case 1 :
					// myCompiler.g:278:16: ',' arg
					{
					match(input,30,FOLLOW_30_in_argument719); 
					pushFollow(FOLLOW_arg_in_argument721);
					arg();
					state._fsp--;

					}
					break;

				default :
					break loop7;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "argument"



	// $ANTLR start "arg"
	// myCompiler.g:281:1: arg : ( arith_expression | STRING_LITERAL );
	public final void arg() throws RecognitionException {
		try {
			// myCompiler.g:281:4: ( arith_expression | STRING_LITERAL )
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==Floating_point_constant||(LA8_0 >= Identifier && LA8_0 <= Integer_constant)||(LA8_0 >= 25 && LA8_0 <= 26)||LA8_0==31) ) {
				alt8=1;
			}
			else if ( (LA8_0==STRING_LITERAL) ) {
				alt8=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}

			switch (alt8) {
				case 1 :
					// myCompiler.g:281:6: arith_expression
					{
					pushFollow(FOLLOW_arith_expression_in_arg739);
					arith_expression();
					state._fsp--;

					}
					break;
				case 2 :
					// myCompiler.g:282:6: STRING_LITERAL
					{
					match(input,STRING_LITERAL,FOLLOW_STRING_LITERAL_in_arg746); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "arg"



	// $ANTLR start "cond_expression"
	// myCompiler.g:285:1: cond_expression returns [Info theInfo] : a= arith_expression ( ConditionOP b= arith_expression )* ;
	public final Info cond_expression() throws RecognitionException {
		Info theInfo = null;


		Token ConditionOP6=null;
		Info a =null;
		Info b =null;


		    theInfo = new Info();

		try {
			// myCompiler.g:289:2: (a= arith_expression ( ConditionOP b= arith_expression )* )
			// myCompiler.g:289:4: a= arith_expression ( ConditionOP b= arith_expression )*
			{
			pushFollow(FOLLOW_arith_expression_in_cond_expression776);
			a=arith_expression();
			state._fsp--;

			// myCompiler.g:289:23: ( ConditionOP b= arith_expression )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0==ConditionOP) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// myCompiler.g:289:24: ConditionOP b= arith_expression
					{
					ConditionOP6=(Token)match(input,ConditionOP,FOLLOW_ConditionOP_in_cond_expression779); 
					pushFollow(FOLLOW_arith_expression_in_cond_expression785);
					b=arith_expression();
					state._fsp--;

					}
					break;

				default :
					break loop9;
				}
			}


				    theInfo.theType = Type.BOOL;
			         	    theInfo.theVar.varIndex = condCount;
			                  String option = String.valueOf((ConditionOP6!=null?ConditionOP6.getText():null));
			                  if(option.compareTo("==")==0){
			                        if( (a.theType == Type.INT) && (b.theType == Type.INT) ){
			                           TextCode.add("%cond" + condCount + " = icmp eq i32 %t" + a.theVar.varIndex + ", %t" + b.theVar.varIndex);
			                           
				             theInfo.theType = Type.INT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.INT) && (b.theType == Type.CONST_INT) ){
			                           TextCode.add("%cond" + condCount + " = icmp eq i32 %t" + a.theVar.varIndex + ", " + b.theVar.iValue);
				             theInfo.theType = Type.INT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.FLOAT) && (b.theType == Type.FLOAT) ){
			                           TextCode.add("%cond" + condCount + " = fcmp oeq float* %t" + a.theVar.varIndex + ", %t" + b.theVar.varIndex);
				             theInfo.theType = Type.FLOAT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.FLOAT) && (b.theType == Type.CONST_FLOAT)){
			                           int vIndex = a.theVar.varIndex;
			                           TextCode.add("%t"+ varCount + " = fpext float %t" + vIndex + " to double");
			                           a.theVar.varIndex = varCount;
			                           varCount++;
			                           double value = b.theVar.fValue;
			                           String FloatValue = String.format("%.8e",value);
			                           TextCode.add("%cond" + condCount + " = fcmp oeq double %t" + a.theVar.varIndex + ", " + FloatValue);
				             theInfo.theType = Type.FLOAT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }
			                  }else if(option.compareTo("!=")==0){
			                        if( (a.theType == Type.INT) && (b.theType == Type.INT)){
			                           TextCode.add("%cond" + condCount + " = icmp ne i32 %t" + a.theVar.varIndex + ", %t" + b.theVar.varIndex);
				             theInfo.theType = Type.INT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.INT) && (b.theType == Type.CONST_INT)){
			                           TextCode.add("%cond" + condCount + " = icmp ne i32 %t" + a.theVar.varIndex + ", " + b.theVar.iValue);
				             theInfo.theType = Type.INT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.FLOAT) && (b.theType == Type.FLOAT)){
			                           TextCode.add("%cond" + condCount + " = fcmp une float* %t" + a.theVar.varIndex + ", %t" + b.theVar.varIndex);
				             theInfo.theType = Type.FLOAT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.FLOAT) && (b.theType == Type.CONST_FLOAT)){
			                           int vIndex = a.theVar.varIndex;
			                           TextCode.add("%t"+ varCount + " = fpext float %t" + vIndex + " to double");
			                           a.theVar.varIndex = varCount++;
			                           double value = b.theVar.fValue;
			                           String FloatValue = String.format("%.8e",value);
			                           TextCode.add("%cond" + condCount + " = fcmp une double %t" + a.theVar.varIndex + ", " + FloatValue);
				             theInfo.theType = Type.FLOAT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }
			                  }else if(option.compareTo("<=")==0){
			                        if( (a.theType == Type.INT) && (b.theType == Type.INT)){
			                           TextCode.add("%cond" + condCount + " = icmp sle i32 %t" + a.theVar.varIndex + ", %t" + b.theVar.varIndex);
				             theInfo.theType = Type.INT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.INT) && (b.theType == Type.CONST_INT)){
			                           TextCode.add("%cond" + condCount + " = icmp sle i32 %t" + a.theVar.varIndex + ", " + b.theVar.iValue);
				             theInfo.theType = Type.INT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.FLOAT) && (b.theType == Type.FLOAT)){
			                           TextCode.add("%cond" + condCount + " = fcmp ole float* %t" + a.theVar.varIndex + ", %t" + b.theVar.varIndex);
				             theInfo.theType = Type.FLOAT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.FLOAT) && (b.theType == Type.CONST_FLOAT)){
			                           int vIndex = a.theVar.varIndex;
			                           TextCode.add("%t"+ varCount + " = fpext float %t" + vIndex + " to double");
			                           a.theVar.varIndex = varCount;
			                           varCount++;
			                           double value = b.theVar.fValue;
			                           String FloatValue = String.format("%.8e",value);
			                           TextCode.add("%cond" + condCount + " = fcmp ole double %t" + a.theVar.varIndex + ", " + FloatValue);
				             theInfo.theType = Type.FLOAT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }
			                  }else if(option.compareTo(">=")==0){
			                        if( (a.theType == Type.INT) && (b.theType == Type.INT)){
			                           TextCode.add("%cond" + condCount + " = icmp sge i32 %t" + a.theVar.varIndex + ", %t" + b.theVar.varIndex);
				             theInfo.theType = Type.INT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.INT) && (b.theType == Type.CONST_INT)){
			                           TextCode.add("%cond" + condCount + " = icmp sge i32 %t" + a.theVar.varIndex + ", " + b.theVar.iValue);
				             theInfo.theType = Type.INT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.FLOAT) && (b.theType == Type.FLOAT)){
			                           TextCode.add("%cond" + condCount + " = fcmp oge float* %t" + a.theVar.varIndex + ", %t" + b.theVar.varIndex);
				             theInfo.theType = Type.FLOAT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.FLOAT) && (b.theType == Type.CONST_FLOAT)){
			                           int vIndex = a.theVar.varIndex;
			                           TextCode.add("%t"+ varCount + " = fpext float %t" + vIndex + " to double");
			                           a.theVar.varIndex = varCount;
			                           varCount++;
			                           double value = b.theVar.fValue;
			                           String FloatValue = String.format("%.8e",value);
			                           TextCode.add("%cond" + condCount + " = fcmp oge double %t" + a.theVar.varIndex + ", " + FloatValue);
				             theInfo.theType = Type.FLOAT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }
			                  }else if(option.compareTo("<")==0){
			                        if( (a.theType == Type.INT) && (b.theType == Type.INT) )
						{
			                           TextCode.add("%cond" + condCount + " = icmp slt i32 %t" + a.theVar.varIndex + ", %t" + b.theVar.varIndex);
				             theInfo.theType = Type.INT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.INT) && (b.theType == Type.CONST_INT)){
			                           TextCode.add("%cond" + condCount + " = icmp slt i32 %t" + a.theVar.varIndex + ", " + b.theVar.iValue);
				             theInfo.theType = Type.INT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.FLOAT) && (b.theType == Type.FLOAT)){
			                           TextCode.add("%cond" + condCount + " = fcmp olt float* %t" + a.theVar.varIndex + ", %t" + b.theVar.varIndex);
				             theInfo.theType = Type.FLOAT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.FLOAT) && (b.theType == Type.CONST_FLOAT)){
			                           int vIndex = a.theVar.varIndex;
			                           TextCode.add("%t"+ varCount + " = fpext float %t" + vIndex + " to double");
			                           a.theVar.varIndex = varCount;
			                           varCount++;
			                           double value = b.theVar.fValue;
			                           String FloatValue = String.format("%.8e",value);
			                           TextCode.add("%cond" + condCount + " = fcmp olt double %t" + a.theVar.varIndex + ", " + FloatValue);
				             theInfo.theType = Type.FLOAT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }
			                  }else if(option.compareTo(">")==0){
			                        if( (a.theType == Type.INT) && (b.theType == Type.INT) )
						{
			                           TextCode.add("%cond" + condCount + " = icmp sgt i32 %t" + a.theVar.varIndex + ", %t" + b.theVar.varIndex);
				             theInfo.theType = Type.INT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.INT) && (b.theType == Type.CONST_INT)){
			                           TextCode.add("%cond" + condCount + " = icmp sgt i32 %t" + a.theVar.varIndex + ", " + b.theVar.iValue);
				             theInfo.theType = Type.INT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.FLOAT) && (b.theType == Type.FLOAT)){
			                           TextCode.add("%cond" + condCount + " = fcmp ogt float* %t" + a.theVar.varIndex + ", %t" + b.theVar.varIndex);
				             theInfo.theType = Type.FLOAT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }else if( (a.theType == Type.FLOAT) && (b.theType == Type.CONST_FLOAT)){
			                           int vIndex = a.theVar.varIndex;
			                           TextCode.add("%t"+ varCount + " = fpext float %t" + vIndex + " to double");
			                           a.theVar.varIndex = varCount;
			                           varCount++;
			                           double value = b.theVar.fValue;
			                           String FloatValue = String.format("%.8e",value);
			                           TextCode.add("%cond" + condCount + " = fcmp ogt double %t" + a.theVar.varIndex + ", " + FloatValue);
				             theInfo.theType = Type.FLOAT;
				             theInfo.theVar.varIndex =  condCount;
			                           condCount++;
			                        }
			                  }
			               
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "cond_expression"



	// $ANTLR start "arith_expression"
	// myCompiler.g:468:1: arith_expression returns [Info theInfo] : a= multExpr ( '+' b= multExpr | '-' multExpr )* ;
	public final Info arith_expression() throws RecognitionException {
		Info theInfo = null;


		Info a =null;
		Info b =null;


		    theInfo = new Info();

		try {
			// myCompiler.g:472:17: (a= multExpr ( '+' b= multExpr | '-' multExpr )* )
			// myCompiler.g:472:19: a= multExpr ( '+' b= multExpr | '-' multExpr )*
			{
			pushFollow(FOLLOW_multExpr_in_arith_expression862);
			a=multExpr();
			state._fsp--;

			 theInfo =a; 
			// myCompiler.g:473:18: ( '+' b= multExpr | '-' multExpr )*
			loop10:
			while (true) {
				int alt10=3;
				int LA10_0 = input.LA(1);
				if ( (LA10_0==29) ) {
					alt10=1;
				}
				else if ( (LA10_0==31) ) {
					alt10=2;
				}

				switch (alt10) {
				case 1 :
					// myCompiler.g:473:20: '+' b= multExpr
					{
					match(input,29,FOLLOW_29_in_arith_expression885); 
					pushFollow(FOLLOW_multExpr_in_arith_expression889);
					b=multExpr();
					state._fsp--;


					                       //check the type is int or float						   
					                       if ((a.theType == Type.INT) && (b.theType == Type.INT)){
					                            TextCode.add("%t" + varCount + " = add nsw i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
							theInfo.theType = Type.INT;
							theInfo.theVar.varIndex = varCount;
							varCount++;
					                       }else if ((a.theType == Type.INT) && (b.theType == Type.CONST_INT)){
					                            TextCode.add("%t" + varCount + " = add nsw i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);
							theInfo.theType = Type.INT;
							theInfo.theVar.varIndex =  varCount;
							varCount++;
					                       }else if ((a.theType == Type.FLOAT) && (b.theType == Type.FLOAT)){
					                            TextCode.add("%t" + varCount + " = fadd nsw float %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
							theInfo.theType = Type.FLOAT;
							theInfo.theVar.varIndex =  varCount;
							varCount++;
					                       }else if ((a.theType == Type.FLOAT) && (b.theType == Type.CONST_FLOAT)){
							float float2string = b.theVar.fValue;
					                            String FloatValue = String.format("%.8e",float2string);
					                            TextCode.add("%t" + varCount + " = fpext float %t" + theInfo.theVar.varIndex + " to double");
					                            int current = varCount;
							varCount++;
					                            TextCode.add("%t" + varCount + " = fadd double %t" + current + ", " + FloatValue);
					                            int finalN = varCount;
							varCount++;
					                            TextCode.add("%t" + varCount + " = fptrunc double %t" + finalN + " to float");
					 		theInfo.theType = Type.FLOAT;
							theInfo.theVar.varIndex = varCount;
							varCount++;
					                      }
					                  
					}
					break;
				case 2 :
					// myCompiler.g:506:20: '-' multExpr
					{
					match(input,31,FOLLOW_31_in_arith_expression932); 
					pushFollow(FOLLOW_multExpr_in_arith_expression934);
					multExpr();
					state._fsp--;


					                       //check the type is int or float						   
					                       if ((a.theType == Type.INT) && (b.theType == Type.INT)){
					                            TextCode.add("%t" + varCount + " = sub nsw i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
							theInfo.theType = Type.INT;
							theInfo.theVar.varIndex = varCount;
						              varCount++;
					                       }else if ((a.theType == Type.INT) && (b.theType == Type.CONST_INT)){
					                            TextCode.add("%t" + varCount + " = sub nsw i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);
							theInfo.theType = Type.INT;
							theInfo.theVar.varIndex = varCount;
						              varCount++;
					                       }else if ((a.theType == Type.FLOAT) && (b.theType == Type.FLOAT)){
					                            TextCode.add("%t" + varCount + " = fsub nsw float %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
							theInfo.theType = Type.FLOAT;
							theInfo.theVar.varIndex = varCount;
						              varCount++;
					                       }else if ((a.theType == Type.FLOAT) && (b.theType == Type.CONST_FLOAT)){
						              int current, finalN;
						              float float2string  = b.theVar.fValue;
					                            String FloatValue = String.format("%.8e",float2string);
					                            TextCode.add("%t" + varCount + " = fpext float %t" + theInfo.theVar.varIndex + " to double");
					                            current = varCount;
						              varCount++;  
					                            TextCode.add("%t" + varCount + " = fsub double %t" + current + ", " + FloatValue);
					                            finalN =varCount;
						              varCount++;
					                            TextCode.add("%t" + varCount + " = fptrunc double %t" + finalN + " to float");
						              theInfo.theType = Type.FLOAT;
						              theInfo.theVar.varIndex = varCount;
						              varCount++;
					                     }
					                    
					}
					break;

				default :
					break loop10;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "arith_expression"



	// $ANTLR start "multExpr"
	// myCompiler.g:543:1: multExpr returns [Info theInfo] : a= signExpr ( '*' b= signExpr | '/' signExpr )* ;
	public final Info multExpr() throws RecognitionException {
		Info theInfo = null;


		Info a =null;
		Info b =null;


			theInfo = new Info();

		try {
			// myCompiler.g:548:11: (a= signExpr ( '*' b= signExpr | '/' signExpr )* )
			// myCompiler.g:548:13: a= signExpr ( '*' b= signExpr | '/' signExpr )*
			{
			pushFollow(FOLLOW_signExpr_in_multExpr1024);
			a=signExpr();
			state._fsp--;

			 theInfo =a; 
			// myCompiler.g:549:11: ( '*' b= signExpr | '/' signExpr )*
			loop11:
			while (true) {
				int alt11=3;
				int LA11_0 = input.LA(1);
				if ( (LA11_0==28) ) {
					alt11=1;
				}
				else if ( (LA11_0==32) ) {
					alt11=2;
				}

				switch (alt11) {
				case 1 :
					// myCompiler.g:549:13: '*' b= signExpr
					{
					match(input,28,FOLLOW_28_in_multExpr1040); 
					pushFollow(FOLLOW_signExpr_in_multExpr1044);
					b=signExpr();
					state._fsp--;


					                       //check the type is int or float					   
					                       if ((a.theType == Type.INT) && (b.theType == Type.INT)){
					                            TextCode.add("%t" + varCount + " = mul nsw i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
							theInfo.theType = Type.INT;
							theInfo.theVar.varIndex = varCount;
						              varCount++;
					                       }else if ((a.theType == Type.INT) && (b.theType == Type.CONST_INT)){
					                            TextCode.add("%t" + varCount + " = mul nsw i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);
							theInfo.theType = Type.INT;
							theInfo.theVar.varIndex = varCount;
						              varCount++;
					                       }else if ((a.theType == Type.FLOAT) && (b.theType == Type.FLOAT)){
					                            TextCode.add("%t" + varCount + " = fmul nsw float %t" + theInfo.theVar.varIndex+ ", %t" + b.theVar.varIndex);
							theInfo.theType = Type.FLOAT;
							theInfo.theVar.varIndex = varCount;
						              varCount++;
					                       }else if ((a.theType == Type.FLOAT) && (b.theType == Type.CONST_FLOAT)){
							int current,finalN;
							float float2string = b.theVar.fValue;
					                            String FloatValue = String.format("%.8e",float2string);
					                            TextCode.add("%t" + varCount + " = fpext float %t" + theInfo.theVar.varIndex + " to double");
					                            current = varCount;
						              varCount++;
					                            TextCode.add("%t" + varCount + " = fmul double %t" + current + ", " + FloatValue);
					                            finalN = varCount;
						              varCount++;
					                            TextCode.add("%t" + varCount + " = fptrunc double %t" + finalN + " to float");
							theInfo.theType = Type.FLOAT;
							theInfo.theVar.varIndex = varCount;
						              varCount++;
					                      }
					                  
					}
					break;
				case 2 :
					// myCompiler.g:583:13: '/' signExpr
					{
					match(input,32,FOLLOW_32_in_multExpr1079); 
					pushFollow(FOLLOW_signExpr_in_multExpr1081);
					signExpr();
					state._fsp--;


					                       //check the type is int or float						   
					                       if ((a.theType == Type.INT) && (b.theType == Type.INT)){
					                            TextCode.add("%t" + varCount + " = sdiv nsw i32 %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
							theInfo.theType = Type.INT;
							theInfo.theVar.varIndex = varCount;
						              varCount++;
					                       } else if ((a.theType == Type.INT) && (b.theType == Type.CONST_INT)){
					                            TextCode.add("%t" + varCount + " = sdiv nsw i32 %t" + theInfo.theVar.varIndex + ", " + b.theVar.iValue);
						   	theInfo.theType = Type.INT;
							theInfo.theVar.varIndex = varCount;
						              varCount++;
					                       }else if ((a.theType == Type.FLOAT) && (b.theType == Type.FLOAT)){
					                            TextCode.add("%t" + varCount + " = fdiv nsw float %t" + theInfo.theVar.varIndex + ", %t" + b.theVar.varIndex);
							theInfo.theType = Type.FLOAT;
							theInfo.theVar.varIndex = varCount;
						              varCount++;
					                       }else if ((a.theType == Type.FLOAT) && (b.theType == Type.CONST_FLOAT)){
							int current,finalN;
							float float2string = b.theVar.fValue;
					                            String FloatValue = String.format("%.8e",float2string);
					                            TextCode.add("%t" + varCount + " = fpext float %t" + theInfo.theVar.varIndex + " to double");
					                            current = varCount;
						              varCount++;
					                            TextCode.add("%t" + varCount + " = fdiv double %t" + current + ", " + FloatValue);
					                            finalN = varCount;
						              varCount++;
					                            TextCode.add("%t" + varCount + " = fptrunc double %t" + finalN + " to float");
							theInfo.theType = Type.FLOAT;
							theInfo.theVar.varIndex = varCount;
						              varCount++;
					                       }
					                  
					}
					break;

				default :
					break loop11;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "multExpr"



	// $ANTLR start "signExpr"
	// myCompiler.g:620:1: signExpr returns [Info theInfo] : (a= primaryExpr | '-' b= primaryExpr );
	public final Info signExpr() throws RecognitionException {
		Info theInfo = null;


		Info a =null;
		Info b =null;

		theInfo = new Info();
		try {
			// myCompiler.g:623:9: (a= primaryExpr | '-' b= primaryExpr )
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==Floating_point_constant||(LA12_0 >= Identifier && LA12_0 <= Integer_constant)||(LA12_0 >= 25 && LA12_0 <= 26)) ) {
				alt12=1;
			}
			else if ( (LA12_0==31) ) {
				alt12=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}

			switch (alt12) {
				case 1 :
					// myCompiler.g:623:11: a= primaryExpr
					{
					pushFollow(FOLLOW_primaryExpr_in_signExpr1138);
					a=primaryExpr();
					state._fsp--;

					 theInfo =a; 
					}
					break;
				case 2 :
					// myCompiler.g:624:11: '-' b= primaryExpr
					{
					match(input,31,FOLLOW_31_in_signExpr1153); 
					pushFollow(FOLLOW_primaryExpr_in_signExpr1157);
					b=primaryExpr();
					state._fsp--;


					        	         if (b.theType == Type.INT){
					                            TextCode.add("%t" + varCount + " = mul nsw i32 -1" + ", %t" + b.theVar.varIndex);
							theInfo.theType = Type.INT;
							theInfo.theVar.varIndex = varCount;
						              varCount++;
					                       }else if (b.theType == Type.FLOAT){
					                            TextCode.add("%t" + varCount + " = fmul nsw float -1" + ", %t" + b.theVar.varIndex);
							theInfo.theType = Type.FLOAT;
							theInfo.theVar.varIndex = varCount;
						              varCount++;
					                       }
					         	
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "signExpr"



	// $ANTLR start "primaryExpr"
	// myCompiler.g:639:1: primaryExpr returns [Info theInfo] : ( Integer_constant | Floating_point_constant | Identifier | '&' Identifier | '(' arith_expression ')' );
	public final Info primaryExpr() throws RecognitionException {
		Info theInfo = null;


		Token Integer_constant7=null;
		Token Floating_point_constant8=null;
		Token Identifier9=null;


		    theInfo = new Info();

		try {
			// myCompiler.g:644:12: ( Integer_constant | Floating_point_constant | Identifier | '&' Identifier | '(' arith_expression ')' )
			int alt13=5;
			switch ( input.LA(1) ) {
			case Integer_constant:
				{
				alt13=1;
				}
				break;
			case Floating_point_constant:
				{
				alt13=2;
				}
				break;
			case Identifier:
				{
				alt13=3;
				}
				break;
			case 25:
				{
				alt13=4;
				}
				break;
			case 26:
				{
				alt13=5;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}
			switch (alt13) {
				case 1 :
					// myCompiler.g:644:14: Integer_constant
					{
					Integer_constant7=(Token)match(input,Integer_constant,FOLLOW_Integer_constant_in_primaryExpr1192); 

					              theInfo.theType = Type.CONST_INT;
						theInfo.theVar.iValue = Integer.parseInt((Integer_constant7!=null?Integer_constant7.getText():null));
					           
					}
					break;
				case 2 :
					// myCompiler.g:648:14: Floating_point_constant
					{
					Floating_point_constant8=(Token)match(input,Floating_point_constant,FOLLOW_Floating_point_constant_in_primaryExpr1208); 

					              theInfo.theType = Type.CONST_FLOAT;
						theInfo.theVar.fValue = Float.parseFloat((Floating_point_constant8!=null?Floating_point_constant8.getText():null));
					           
					}
					break;
				case 3 :
					// myCompiler.g:652:14: Identifier
					{
					Identifier9=(Token)match(input,Identifier,FOLLOW_Identifier_in_primaryExpr1224); 

					                // get type information from symbolTable.
					                Type the_type = symbolTable.get((Identifier9!=null?Identifier9.getText():null)).theType;
						  theInfo.theType = the_type;

					                // get variable index from symbolTable.
					                int vIndex = symbolTable.get((Identifier9!=null?Identifier9.getText():null)).theVar.varIndex;
							
						  //two data types, int and float
					                switch (the_type) {
					                case INT: 
							TextCode.add("%t" + varCount + "=load i32, i32* %t" + vIndex);
							theInfo.theVar.varIndex = varCount;
							varCount ++;
					                  	break;
					                case FLOAT:
					                   	TextCode.add("%t" + varCount + " = load float, float* %t" + vIndex);
							theInfo.theVar.varIndex = varCount;
							varCount ++;
					                         break;			
					                }
					          
					}
					break;
				case 4 :
					// myCompiler.g:674:13: '&' Identifier
					{
					match(input,25,FOLLOW_25_in_primaryExpr1239); 
					match(input,Identifier,FOLLOW_Identifier_in_primaryExpr1241); 
					}
					break;
				case 5 :
					// myCompiler.g:675:13: '(' arith_expression ')'
					{
					match(input,26,FOLLOW_26_in_primaryExpr1255); 
					pushFollow(FOLLOW_arith_expression_in_primaryExpr1257);
					arith_expression();
					state._fsp--;

					match(input,27,FOLLOW_27_in_primaryExpr1259); 
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return theInfo;
	}
	// $ANTLR end "primaryExpr"

	// Delegated rules



	public static final BitSet FOLLOW_VOID_in_program36 = new BitSet(new long[]{0x0000000000100000L});
	public static final BitSet FOLLOW_MAIN_in_program38 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_program40 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_program42 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_35_in_program63 = new BitSet(new long[]{0x0000001000A729B0L});
	public static final BitSet FOLLOW_declarations_in_program77 = new BitSet(new long[]{0x0000001000250000L});
	public static final BitSet FOLLOW_statements_in_program90 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_36_in_program100 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_type_in_declarations127 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_Identifier_in_declarations129 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_33_in_declarations131 = new BitSet(new long[]{0x00000000008229B0L});
	public static final BitSet FOLLOW_declarations_in_declarations133 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INT_in_type190 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CHAR_in_type200 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_FLOAT_in_type210 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_VOID_in_type220 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CONST_INT_in_type230 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CONST_FLOAT_in_type240 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_BOOL_in_type250 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ERROR_in_type260 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_statement_in_statements271 = new BitSet(new long[]{0x0000000000250000L});
	public static final BitSet FOLLOW_statements_in_statements273 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_assign_stmt_in_statement304 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_33_in_statement306 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_if_stmt_in_statement319 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_func_no_return_stmt_in_statement332 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_33_in_statement334 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_print_function_in_statement347 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_33_in_statement349 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRINTF_in_print_function384 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_print_function386 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_print_function388 = new BitSet(new long[]{0x0000000040000000L});
	public static final BitSet FOLLOW_30_in_print_function390 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_Identifier_in_print_function392 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_print_function394 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_PRINTF_in_print_function424 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_print_function426 = new BitSet(new long[]{0x0000000000400000L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_print_function428 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_print_function430 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_IF_in_if_stmt482 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_if_stmt484 = new BitSet(new long[]{0x00000000860C8000L});
	public static final BitSet FOLLOW_cond_expression_in_if_stmt488 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_if_stmt490 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_block_stmt_in_if_stmt518 = new BitSet(new long[]{0x0000000000000402L});
	public static final BitSet FOLLOW_ELSE_in_if_stmt560 = new BitSet(new long[]{0x0000000800000000L});
	public static final BitSet FOLLOW_block_stmt_in_if_stmt562 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_35_in_block_stmt620 = new BitSet(new long[]{0x0000001000250000L});
	public static final BitSet FOLLOW_statements_in_block_stmt622 = new BitSet(new long[]{0x0000001000000000L});
	public static final BitSet FOLLOW_36_in_block_stmt624 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_assign_stmt636 = new BitSet(new long[]{0x0000000400000000L});
	public static final BitSet FOLLOW_34_in_assign_stmt638 = new BitSet(new long[]{0x00000000860C8000L});
	public static final BitSet FOLLOW_arith_expression_in_assign_stmt640 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_func_no_return_stmt682 = new BitSet(new long[]{0x0000000004000000L});
	public static final BitSet FOLLOW_26_in_func_no_return_stmt684 = new BitSet(new long[]{0x00000000864C8000L});
	public static final BitSet FOLLOW_argument_in_func_no_return_stmt686 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_func_no_return_stmt688 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_arg_in_argument716 = new BitSet(new long[]{0x0000000040000002L});
	public static final BitSet FOLLOW_30_in_argument719 = new BitSet(new long[]{0x00000000864C8000L});
	public static final BitSet FOLLOW_arg_in_argument721 = new BitSet(new long[]{0x0000000040000002L});
	public static final BitSet FOLLOW_arith_expression_in_arg739 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_STRING_LITERAL_in_arg746 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_arith_expression_in_cond_expression776 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_ConditionOP_in_cond_expression779 = new BitSet(new long[]{0x00000000860C8000L});
	public static final BitSet FOLLOW_arith_expression_in_cond_expression785 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_multExpr_in_arith_expression862 = new BitSet(new long[]{0x00000000A0000002L});
	public static final BitSet FOLLOW_29_in_arith_expression885 = new BitSet(new long[]{0x00000000860C8000L});
	public static final BitSet FOLLOW_multExpr_in_arith_expression889 = new BitSet(new long[]{0x00000000A0000002L});
	public static final BitSet FOLLOW_31_in_arith_expression932 = new BitSet(new long[]{0x00000000860C8000L});
	public static final BitSet FOLLOW_multExpr_in_arith_expression934 = new BitSet(new long[]{0x00000000A0000002L});
	public static final BitSet FOLLOW_signExpr_in_multExpr1024 = new BitSet(new long[]{0x0000000110000002L});
	public static final BitSet FOLLOW_28_in_multExpr1040 = new BitSet(new long[]{0x00000000860C8000L});
	public static final BitSet FOLLOW_signExpr_in_multExpr1044 = new BitSet(new long[]{0x0000000110000002L});
	public static final BitSet FOLLOW_32_in_multExpr1079 = new BitSet(new long[]{0x00000000860C8000L});
	public static final BitSet FOLLOW_signExpr_in_multExpr1081 = new BitSet(new long[]{0x0000000110000002L});
	public static final BitSet FOLLOW_primaryExpr_in_signExpr1138 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_31_in_signExpr1153 = new BitSet(new long[]{0x00000000060C8000L});
	public static final BitSet FOLLOW_primaryExpr_in_signExpr1157 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Integer_constant_in_primaryExpr1192 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Floating_point_constant_in_primaryExpr1208 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_Identifier_in_primaryExpr1224 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_25_in_primaryExpr1239 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_Identifier_in_primaryExpr1241 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_26_in_primaryExpr1255 = new BitSet(new long[]{0x00000000860C8000L});
	public static final BitSet FOLLOW_arith_expression_in_primaryExpr1257 = new BitSet(new long[]{0x0000000008000000L});
	public static final BitSet FOLLOW_27_in_primaryExpr1259 = new BitSet(new long[]{0x0000000000000002L});
}
