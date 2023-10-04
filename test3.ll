declare dso_local i32 @printf(i8*, ...)

@.str = private unnamed_addr constant [4 x i8] c"%d\0A\00"
@.str.1 = private unnamed_addr constant [4 x i8] c"%f\0A\00"
@.str.2 = private unnamed_addr constant [6 x i8] c"Hello\00"
define dso_local i32 @main()
{
%t0 = alloca float, align 4
%t1 = alloca i32, align 4
store float 0x3ff1c28f60000000, float* %t0
%t2 =load float, float* %t0
%t3 = fpext float %t2 to double
%t4 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.1, i64 0, i64 0), double %t3)
%t5 = load float, float* %t0
%t6 = fpext float %t5 to double
%cond0 = fcmp ogt double %t6, 1.23000002e+00
br i1 %cond0, label %L1, label %L2
L1:
store i32 3, i32* %t1
br label %L3
L2:
%t7 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([6 x i8], [6 x i8]* @.str.2, i64 0, i64 0))
br label %L3
L3:
ret i32 0
}
