	.text
	.file	"test1.ll"
	.globl	main                    # -- Begin function main
	.p2align	4, 0x90
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# %bb.0:
	subq	$24, %rsp
	.cfi_def_cfa_offset 32
	movl	$1, 8(%rsp)
	xorl	%eax, %eax
	testb	%al, %al
	jne	.LBB0_2
# %bb.1:                                # %L1
	movl	$5, 12(%rsp)
	jmp	.LBB0_3
.LBB0_2:                                # %L2
	movl	$4, 16(%rsp)
.LBB0_3:                                # %L3
	movl	8(%rsp), %esi
	movl	$.L.str, %edi
	xorl	%eax, %eax
	callq	printf
	movl	12(%rsp), %esi
	movl	$.L.str, %edi
	xorl	%eax, %eax
	callq	printf
	xorl	%eax, %eax
	addq	$24, %rsp
	.cfi_def_cfa_offset 8
	retq
.Lfunc_end0:
	.size	main, .Lfunc_end0-main
	.cfi_endproc
                                        # -- End function
	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%d\n"
	.size	.L.str, 4

	.type	.L.str.1,@object        # @.str.1
.L.str.1:
	.asciz	"%f\n"
	.size	.L.str.1, 4

	.type	.L.str.2,@object        # @.str.2
.L.str.2:
	.asciz	"Hello"
	.size	.L.str.2, 6

	.section	".note.GNU-stack","",@progbits
