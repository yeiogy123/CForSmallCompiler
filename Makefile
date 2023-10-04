all:
	java -jar ./antlr-3.5.2-complete.jar myCompiler.g
	javac -cp ./antlr-3.5.2-complete.jar:. *.java
	java -cp ./antlr-3.5.2-complete.jar:. myCompiler_test test3.c > test3.ll
	java -cp ./antlr-3.5.2-complete.jar:. myCompiler_test test2.c > test2.ll
	java -cp ./antlr-3.5.2-complete.jar:. myCompiler_test test1.c > test1.ll
	./clang test1.ll -o test1.out
	./clang test2.ll -o test2.out
	./clang test3.ll -o test3.out
clean:
	rm myCompilerParser* myCompilerLexer* myCompiler.tokens myCompiler_test.class test1.ll test2.ll test3.ll
