grammar myCompiler;

options {
   language = Java;
}

@header {
    // import packages here.
    import java.util.HashMap;
    import java.util.ArrayList;
}

@members {
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
       	TextCode.add("@.str = private unnamed_addr constant [4 x i8] c\"\%d\\0A\\00\"");       	
       	TextCode.add("@.str.1 = private unnamed_addr constant [4 x i8] c\"\%f\\0A\\00\"");
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
}

program: VOID MAIN '(' ')'
        {
           /* Output function prologue */
           prologue();
        }

        '{' 
           declarations
           statements
        '}'
        {
	   if (TRACEON)
	      System.out.println("VOID MAIN () {declarations statements}");

           /* output function epilogue */	  
           epilogue();
        }
        ;


declarations: type Identifier ';' declarations
        {
           if (TRACEON)
              System.out.println("declarations: type Identifier : declarations");

           if (symbolTable.containsKey($Identifier.text)) {
              // variable re-declared.
              System.out.println("Type Error: " + 
                                  $Identifier.getLine() + 
                                 ": Redeclared identifier.");
              System.exit(0);
           }
                 
           /* Add ID and its info into the symbol table. */
	       Info the_entry = new Info();
	       the_entry.theType = $type.attr_type;
	       the_entry.theVar.varIndex = varCount;
	       varCount ++;
	       symbolTable.put($Identifier.text, the_entry);

           // issue the instruction.
           // Ex: \%a = alloca i32, align 4
           //INT
           if ($type.attr_type == Type.INT) { 
              TextCode.add("\%t" + the_entry.theVar.varIndex + " = alloca i32, align 4");
           }
           //FLOAT
	   if ($type.attr_type == Type.FLOAT){ 
              TextCode.add("\%t" + the_entry.theVar.varIndex + " = alloca float, align 4");
           }
	  
        }
        | 
        {
           if (TRACEON)
              System.out.println("declarations: ");
        }
        ;


type
returns [Type attr_type]
    : INT { if (TRACEON) System.out.println("type: INT"); $attr_type=Type.INT; }
    | CHAR { if (TRACEON) System.out.println("type: CHAR"); $attr_type=Type.CHAR; }
    | FLOAT {if (TRACEON) System.out.println("type: FLOAT"); $attr_type=Type.FLOAT; }
    | VOID {if (TRACEON) System.out.println("type: VOID"); $attr_type=Type.VOID; }
    | CONST_INT {if (TRACEON) System.out.println("type: CONST_INT"); $attr_type=Type.CONST_INT; }
    | CONST_FLOAT {if (TRACEON) System.out.println("type: CONST_FLOAT"); $attr_type=Type.CONST_FLOAT; }
    | BOOL {if (TRACEON) System.out.println("type: BOOL"); $attr_type=Type.BOOL; }
    | ERROR {if (TRACEON) System.out.println("type: ERROR"); $attr_type=Type.ERROR; }
	;


statements:statement statements
          |
          ;


statement: assign_stmt ';'
         | if_stmt
         | func_no_return_stmt ';'
         | print_function ';'
         ;


print_function
@init{}
            : PRINTF '(' STRING_LITERAL ',' Identifier ')'
            {
               	Type the_type = symbolTable.get($Identifier.text).theType;	
	       	int vIndex = symbolTable.get($Identifier.text).theVar.varIndex;	
		Info the_entry = new Info();	
               	if(the_type == Type.INT)
	       	{
		    TextCode.add("\%t" + varCount + "=load i32, i32* \%t" + vIndex);
                    	    vIndex = varCount++;
		    the_entry.theType = Type.INT;
                    	    the_entry.theVar.varIndex = varCount++;
                    	    TextCode.add("\%t" + the_entry.theVar.varIndex+ " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str, i64 0, i64 0), i32 \%t" + vIndex + ")");
               	}else if(the_type == Type.FLOAT){
		    TextCode.add("\%t" + varCount + " =load float, float* \%t" + vIndex);
                    	    vIndex = varCount++;
                    	    TextCode.add("\%t" + varCount + " = fpext float \%t" + vIndex + " to double");
                    	    vIndex = varCount++;
		    the_entry.theType = Type.FLOAT;
                    	    the_entry.theVar.varIndex = varCount++;
                    	    TextCode.add("\%t" + the_entry.theVar.varIndex + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i64 0, i64 0), double \%t" + vIndex + ")");
               	}
            }
            | PRINTF '(' STRING_LITERAL ')'
            {
                Info the_entry = new Info();
	        the_entry.theType = Type.INT;
	        the_entry.theVar.varIndex = varCount++;
                TextCode.add("\%t"+the_entry.theVar.varIndex + " = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([6 x i8], [6 x i8]* @.str.2, i64 0, i64 0))");
            }
            ;
	

	 
if_stmt
            : IF '(' cond=cond_expression ')'
            {
               String Ltrue = newLabel();
               String Lfalse = newLabel();
               String Lend = newLabel();
               TextCode.add("br i1 \%cond" + $cond.theInfo.theVar.varIndex + ", label \%" + Ltrue + ", label \%" + Lfalse);
               
               //Ltrue's label
               TextCode.add(Ltrue + ":");
            }
            block_stmt
            {
            	//branch to Lend's label
               TextCode.add("br label \%" + Lend);
              //Lfalse's label
               TextCode.add(Lfalse + ":");
            }
            //where is else exist
            (ELSE block_stmt
       	    {
       	    	//branch to Lend's label
               	TextCode.add("br label \%" + Lend);
               	//Lend's label
               	TextCode.add(Lend + ":");
            	    }
            )? 
            ;


				  
block_stmt: '{' statements '}'
	  ;


assign_stmt: Identifier '=' arith_expression
             {
                Info theRHS = $arith_expression.theInfo;
	  Info theLHS = symbolTable.get($Identifier.text);
                if ((theLHS.theType == Type.INT) && (theRHS.theType == Type.INT)){		   
                   TextCode.add("store i32 \%t" + theRHS.theVar.varIndex + ", i32* \%t" + theLHS.theVar.varIndex);
	  }else if ((theLHS.theType == Type.INT) && (theRHS.theType == Type.CONST_INT)){
                   TextCode.add("store i32 " + theRHS.theVar.iValue + ", i32* \%t" + theLHS.theVar.varIndex);	
	  }else if ((theLHS.theType == Type.FLOAT) && (theRHS.theType == Type.FLOAT)){		   
                   TextCode.add("store float \%t" + theRHS.theVar.varIndex + ", float* \%t" + theLHS.theVar.varIndex);
	  }else if ((theLHS.theType == Type.FLOAT) && (theRHS.theType == Type.CONST_FLOAT)){		   
                   double value = theRHS.theVar.fValue;
                   long answer = Double.doubleToLongBits(value);
                   String FloatValue = Long.toHexString(answer);
                   TextCode.add("store float 0x" + FloatValue + ", float* \%t" + theLHS.theVar.varIndex);
            	  }
	}
             ;

		   
func_no_return_stmt: Identifier '(' argument ')'
                   ;


argument: arg (',' arg)*
        ;

arg: arith_expression
   | STRING_LITERAL
   ;
		   
cond_expression returns [Info theInfo]
@init {
    theInfo = new Info();
} 
	: a=arith_expression (ConditionOP b = arith_expression )*
               {
	    $theInfo.theType = Type.BOOL;
         	    $theInfo.theVar.varIndex = condCount;
                  String option = String.valueOf($ConditionOP.text);
                  if(option.compareTo("==")==0){
                        if( ($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.INT) ){
                           TextCode.add("\%cond" + condCount + " = icmp eq i32 \%t" + $a.theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
                           
	             $theInfo.theType = Type.INT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.CONST_INT) ){
                           TextCode.add("\%cond" + condCount + " = icmp eq i32 \%t" + $a.theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
	             $theInfo.theType = Type.INT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.FLOAT) ){
                           TextCode.add("\%cond" + condCount + " = fcmp oeq float* \%t" + $a.theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
	             $theInfo.theType = Type.FLOAT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.CONST_FLOAT)){
                           int vIndex = $a.theInfo.theVar.varIndex;
                           TextCode.add("\%t"+ varCount + " = fpext float \%t" + vIndex + " to double");
                           $a.theInfo.theVar.varIndex = varCount;
                           varCount++;
                           double value = $b.theInfo.theVar.fValue;
                           String FloatValue = String.format("\%.8e",value);
                           TextCode.add("\%cond" + condCount + " = fcmp oeq double \%t" + $a.theInfo.theVar.varIndex + ", " + FloatValue);
	             $theInfo.theType = Type.FLOAT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }
                  }else if(option.compareTo("!=")==0){
                        if( ($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.INT)){
                           TextCode.add("\%cond" + condCount + " = icmp ne i32 \%t" + $a.theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
	             $theInfo.theType = Type.INT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.CONST_INT)){
                           TextCode.add("\%cond" + condCount + " = icmp ne i32 \%t" + $a.theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
	             $theInfo.theType = Type.INT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.FLOAT)){
                           TextCode.add("\%cond" + condCount + " = fcmp une float* \%t" + $a.theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
	             $theInfo.theType = Type.FLOAT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.CONST_FLOAT)){
                           int vIndex = $a.theInfo.theVar.varIndex;
                           TextCode.add("\%t"+ varCount + " = fpext float \%t" + vIndex + " to double");
                           $a.theInfo.theVar.varIndex = varCount++;
                           double value = $b.theInfo.theVar.fValue;
                           String FloatValue = String.format("\%.8e",value);
                           TextCode.add("\%cond" + condCount + " = fcmp une double \%t" + $a.theInfo.theVar.varIndex + ", " + FloatValue);
	             $theInfo.theType = Type.FLOAT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }
                  }else if(option.compareTo("<=")==0){
                        if( ($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.INT)){
                           TextCode.add("\%cond" + condCount + " = icmp sle i32 \%t" + $a.theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
	             $theInfo.theType = Type.INT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.CONST_INT)){
                           TextCode.add("\%cond" + condCount + " = icmp sle i32 \%t" + $a.theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
	             $theInfo.theType = Type.INT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.FLOAT)){
                           TextCode.add("\%cond" + condCount + " = fcmp ole float* \%t" + $a.theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
	             $theInfo.theType = Type.FLOAT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.CONST_FLOAT)){
                           int vIndex = $a.theInfo.theVar.varIndex;
                           TextCode.add("\%t"+ varCount + " = fpext float \%t" + vIndex + " to double");
                           $a.theInfo.theVar.varIndex = varCount;
                           varCount++;
                           double value = $b.theInfo.theVar.fValue;
                           String FloatValue = String.format("\%.8e",value);
                           TextCode.add("\%cond" + condCount + " = fcmp ole double \%t" + $a.theInfo.theVar.varIndex + ", " + FloatValue);
	             $theInfo.theType = Type.FLOAT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }
                  }else if(option.compareTo(">=")==0){
                        if( ($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.INT)){
                           TextCode.add("\%cond" + condCount + " = icmp sge i32 \%t" + $a.theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
	             $theInfo.theType = Type.INT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.CONST_INT)){
                           TextCode.add("\%cond" + condCount + " = icmp sge i32 \%t" + $a.theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
	             $theInfo.theType = Type.INT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.FLOAT)){
                           TextCode.add("\%cond" + condCount + " = fcmp oge float* \%t" + $a.theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
	             $theInfo.theType = Type.FLOAT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.CONST_FLOAT)){
                           int vIndex = $a.theInfo.theVar.varIndex;
                           TextCode.add("\%t"+ varCount + " = fpext float \%t" + vIndex + " to double");
                           $a.theInfo.theVar.varIndex = varCount;
                           varCount++;
                           double value = $b.theInfo.theVar.fValue;
                           String FloatValue = String.format("\%.8e",value);
                           TextCode.add("\%cond" + condCount + " = fcmp oge double \%t" + $a.theInfo.theVar.varIndex + ", " + FloatValue);
	             $theInfo.theType = Type.FLOAT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }
                  }else if(option.compareTo("<")==0){
                        if( ($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.INT) )
			{
                           TextCode.add("\%cond" + condCount + " = icmp slt i32 \%t" + $a.theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
	             $theInfo.theType = Type.INT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.CONST_INT)){
                           TextCode.add("\%cond" + condCount + " = icmp slt i32 \%t" + $a.theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
	             $theInfo.theType = Type.INT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.FLOAT)){
                           TextCode.add("\%cond" + condCount + " = fcmp olt float* \%t" + $a.theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
	             $theInfo.theType = Type.FLOAT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.CONST_FLOAT)){
                           int vIndex = $a.theInfo.theVar.varIndex;
                           TextCode.add("\%t"+ varCount + " = fpext float \%t" + vIndex + " to double");
                           $a.theInfo.theVar.varIndex = varCount;
                           varCount++;
                           double value = $b.theInfo.theVar.fValue;
                           String FloatValue = String.format("\%.8e",value);
                           TextCode.add("\%cond" + condCount + " = fcmp olt double \%t" + $a.theInfo.theVar.varIndex + ", " + FloatValue);
	             $theInfo.theType = Type.FLOAT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }
                  }else if(option.compareTo(">")==0){
                        if( ($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.INT) )
			{
                           TextCode.add("\%cond" + condCount + " = icmp sgt i32 \%t" + $a.theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
	             $theInfo.theType = Type.INT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.CONST_INT)){
                           TextCode.add("\%cond" + condCount + " = icmp sgt i32 \%t" + $a.theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
	             $theInfo.theType = Type.INT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.FLOAT)){
                           TextCode.add("\%cond" + condCount + " = fcmp ogt float* \%t" + $a.theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
	             $theInfo.theType = Type.FLOAT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }else if( ($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.CONST_FLOAT)){
                           int vIndex = $a.theInfo.theVar.varIndex;
                           TextCode.add("\%t"+ varCount + " = fpext float \%t" + vIndex + " to double");
                           $a.theInfo.theVar.varIndex = varCount;
                           varCount++;
                           double value = $b.theInfo.theVar.fValue;
                           String FloatValue = String.format("\%.8e",value);
                           TextCode.add("\%cond" + condCount + " = fcmp ogt double \%t" + $a.theInfo.theVar.varIndex + ", " + FloatValue);
	             $theInfo.theType = Type.FLOAT;
	             $theInfo.theVar.varIndex =  condCount;
                           condCount++;
                        }
                  }
               }
               ;
			   
arith_expression returns [Info theInfo]
@init {
    theInfo = new Info();
}
                : a=multExpr { $theInfo=$a.theInfo; }
                 ( '+' b=multExpr
                    {
                       //check the type is int or float						   
                       if (($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.INT)){
                            TextCode.add("\%t" + varCount + " = add nsw i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
		$theInfo.theType = Type.INT;
		$theInfo.theVar.varIndex = varCount;
		varCount++;
                       }else if (($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.CONST_INT)){
                            TextCode.add("\%t" + varCount + " = add nsw i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
		$theInfo.theType = Type.INT;
		$theInfo.theVar.varIndex =  varCount;
		varCount++;
                       }else if (($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.FLOAT)){
                            TextCode.add("\%t" + varCount + " = fadd nsw float \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
		$theInfo.theType = Type.FLOAT;
		$theInfo.theVar.varIndex =  varCount;
		varCount++;
                       }else if (($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.CONST_FLOAT)){
		float float2string = $b.theInfo.theVar.fValue;
                            String FloatValue = String.format("\%.8e",float2string);
                            TextCode.add("\%t" + varCount + " = fpext float \%t" + $theInfo.theVar.varIndex + " to double");
                            int current = varCount;
		varCount++;
                            TextCode.add("\%t" + varCount + " = fadd double \%t" + current + ", " + FloatValue);
                            int finalN = varCount;
		varCount++;
                            TextCode.add("\%t" + varCount + " = fptrunc double \%t" + finalN + " to float");
 		$theInfo.theType = Type.FLOAT;
		$theInfo.theVar.varIndex = varCount;
		varCount++;
                      }
                  }
                 | '-' multExpr
                     {
                       //check the type is int or float						   
                       if (($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.INT)){
                            TextCode.add("\%t" + varCount + " = sub nsw i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
		$theInfo.theType = Type.INT;
		$theInfo.theVar.varIndex = varCount;
	              varCount++;
                       }else if (($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.CONST_INT)){
                            TextCode.add("\%t" + varCount + " = sub nsw i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
		$theInfo.theType = Type.INT;
		$theInfo.theVar.varIndex = varCount;
	              varCount++;
                       }else if (($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.FLOAT)){
                            TextCode.add("\%t" + varCount + " = fsub nsw float \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
		$theInfo.theType = Type.FLOAT;
		$theInfo.theVar.varIndex = varCount;
	              varCount++;
                       }else if (($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.CONST_FLOAT)){
	              int current, finalN;
	              float float2string  = $b.theInfo.theVar.fValue;
                            String FloatValue = String.format("\%.8e",float2string);
                            TextCode.add("\%t" + varCount + " = fpext float \%t" + $theInfo.theVar.varIndex + " to double");
                            current = varCount;
	              varCount++;  
                            TextCode.add("\%t" + varCount + " = fsub double \%t" + current + ", " + FloatValue);
                            finalN =varCount;
	              varCount++;
                            TextCode.add("\%t" + varCount + " = fptrunc double \%t" + finalN + " to float");
	              $theInfo.theType = Type.FLOAT;
	              $theInfo.theVar.varIndex = varCount;
	              varCount++;
                     }
                    }
                 )*
                 ;

multExpr
returns [Info theInfo]
@init {
	theInfo = new Info();
}
          : a=signExpr { $theInfo=$a.theInfo; }
          ( '*' b=signExpr 
                  {
                       //check the type is int or float					   
                       if (($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.INT)){
                            TextCode.add("\%t" + varCount + " = mul nsw i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
		$theInfo.theType = Type.INT;
		$theInfo.theVar.varIndex = varCount;
	              varCount++;
                       }else if (($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.CONST_INT)){
                            TextCode.add("\%t" + varCount + " = mul nsw i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
		$theInfo.theType = Type.INT;
		$theInfo.theVar.varIndex = varCount;
	              varCount++;
                       }else if (($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.FLOAT)){
                            TextCode.add("\%t" + varCount + " = fmul nsw float \%t" + $theInfo.theVar.varIndex+ ", \%t" + $b.theInfo.theVar.varIndex);
		$theInfo.theType = Type.FLOAT;
		$theInfo.theVar.varIndex = varCount;
	              varCount++;
                       }else if (($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.CONST_FLOAT)){
		int current,finalN;
		float float2string = $b.theInfo.theVar.fValue;
                            String FloatValue = String.format("\%.8e",float2string);
                            TextCode.add("\%t" + varCount + " = fpext float \%t" + $theInfo.theVar.varIndex + " to double");
                            current = varCount;
	              varCount++;
                            TextCode.add("\%t" + varCount + " = fmul double \%t" + current + ", " + FloatValue);
                            finalN = varCount;
	              varCount++;
                            TextCode.add("\%t" + varCount + " = fptrunc double \%t" + finalN + " to float");
		$theInfo.theType = Type.FLOAT;
		$theInfo.theVar.varIndex = varCount;
	              varCount++;
                      }
                  }
          | '/' signExpr
                  {
                       //check the type is int or float						   
                       if (($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.INT)){
                            TextCode.add("\%t" + varCount + " = sdiv nsw i32 \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
		$theInfo.theType = Type.INT;
		$theInfo.theVar.varIndex = varCount;
	              varCount++;
                       } else if (($a.theInfo.theType == Type.INT) && ($b.theInfo.theType == Type.CONST_INT)){
                            TextCode.add("\%t" + varCount + " = sdiv nsw i32 \%t" + $theInfo.theVar.varIndex + ", " + $b.theInfo.theVar.iValue);
	   	$theInfo.theType = Type.INT;
		$theInfo.theVar.varIndex = varCount;
	              varCount++;
                       }else if (($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.FLOAT)){
                            TextCode.add("\%t" + varCount + " = fdiv nsw float \%t" + $theInfo.theVar.varIndex + ", \%t" + $b.theInfo.theVar.varIndex);
		$theInfo.theType = Type.FLOAT;
		$theInfo.theVar.varIndex = varCount;
	              varCount++;
                       }else if (($a.theInfo.theType == Type.FLOAT) && ($b.theInfo.theType == Type.CONST_FLOAT)){
		int current,finalN;
		float float2string = $b.theInfo.theVar.fValue;
                            String FloatValue = String.format("\%.8e",float2string);
                            TextCode.add("\%t" + varCount + " = fpext float \%t" + $theInfo.theVar.varIndex + " to double");
                            current = varCount;
	              varCount++;
                            TextCode.add("\%t" + varCount + " = fdiv double \%t" + current + ", " + FloatValue);
                            finalN = varCount;
	              varCount++;
                            TextCode.add("\%t" + varCount + " = fptrunc double \%t" + finalN + " to float");
		$theInfo.theType = Type.FLOAT;
		$theInfo.theVar.varIndex = varCount;
	              varCount++;
                       }
                  }
	  )*
	  ;

signExpr
returns [Info theInfo]
@init {theInfo = new Info();}
        : a=primaryExpr { $theInfo=$a.theInfo; } 
        | '-' b=primaryExpr{
        	         if ($b.theInfo.theType == Type.INT){
                            TextCode.add("\%t" + varCount + " = mul nsw i32 -1" + ", \%t" + $b.theInfo.theVar.varIndex);
		$theInfo.theType = Type.INT;
		$theInfo.theVar.varIndex = varCount;
	              varCount++;
                       }else if ($b.theInfo.theType == Type.FLOAT){
                            TextCode.add("\%t" + varCount + " = fmul nsw float -1" + ", \%t" + $b.theInfo.theVar.varIndex);
		$theInfo.theType = Type.FLOAT;
		$theInfo.theVar.varIndex = varCount;
	              varCount++;
                       }
         	}
	;
		  
primaryExpr
returns [Info theInfo]
@init {
    theInfo = new Info();
}
           : Integer_constant{
              $theInfo.theType = Type.CONST_INT;
	$theInfo.theVar.iValue = Integer.parseInt($Integer_constant.text);
           }
           | Floating_point_constant{
              $theInfo.theType = Type.CONST_FLOAT;
	$theInfo.theVar.fValue = Float.parseFloat($Floating_point_constant.text);
           }
           | Identifier{
                // get type information from symbolTable.
                Type the_type = symbolTable.get($Identifier.text).theType;
	  $theInfo.theType = the_type;

                // get variable index from symbolTable.
                int vIndex = symbolTable.get($Identifier.text).theVar.varIndex;
		
	  //two data types, int and float
                switch (the_type) {
                case INT: 
		TextCode.add("\%t" + varCount + "=load i32, i32* \%t" + vIndex);
		$theInfo.theVar.varIndex = varCount;
		varCount ++;
                  	break;
                case FLOAT:
                   	TextCode.add("\%t" + varCount + " = load float, float* \%t" + vIndex);
		$theInfo.theVar.varIndex = varCount;
		varCount ++;
                         break;			
                }
          }
          | '&' Identifier
          | '(' arith_expression ')'
           ;

		   
/* description of the tokens */
FLOAT:'float';
INT:'int';
CHAR: 'char';
MAIN: 'main';
CONST_INT: 'const int';
CONST_FLOAT: 'const float';
BOOL: 'bool';
VOID: 'void';
PRINTF: 'printf';
IF: 'if';
ELSE: 'else';
FOR: 'for';
ERROR:'error';

ConditionOP: '>' |'>=' | '<' | '<=' | '==' | '!=';

Identifier:('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
Integer_constant:'0'..'9'+;
Floating_point_constant:'0'..'9'+ '.' '0'..'9'+;

STRING_LITERAL
    :  '"' ( EscapeSequence | ~('\\'|'"') )* '"'
    ;

WS:( ' ' | '\t' | '\r' | '\n' ) {$channel=HIDDEN;};
COMMENT:'/*' .* '*/' {$channel=HIDDEN;};


fragment
EscapeSequence
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    ;
