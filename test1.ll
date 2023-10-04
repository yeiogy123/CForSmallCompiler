declare dso_local i32 @printf(i8*, ...)

@.str = private unnamed_addr constant [4 x i8] c"%d\0A\00"
@.str.1 = private unnamed_addr constant [4 x i8] c"%f\0A\00"
@.str.2 = private unnamed_addr constant [6 x i8] c"Hello\00"
define dso_local i32 @main()
{
%t0 = alloca i32, align 4
%t1 = alloca i32, align 4
%t2 = alloca i32, align 4
%t3 = alloca i32, align 4
store i32 1, i32* %t3
%t4=load i32, i32* %t3
%cond0 = icmp eq i32 %t4, 1
br i1 %cond0, label %L1, label %L2
L1:
store i32 5, i32* %t1
br label %L3
L2:
store i32 4, i32* %t0
br label %L3
L3:
%t5=load i32, i32* %t3
%t6 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str, i64 0, i64 0), i32 %t5)
%t7=load i32, i32* %t1
%t8 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str, i64 0, i64 0), i32 %t7)
ret i32 0
}
