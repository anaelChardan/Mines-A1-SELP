public class TestGreen extends Test {
	
	public static void main(String[] args) {
		Test.main(args);

		String path = "src/test/resources/test";

		// Calc Green		
		test(verbose, path + "/greenZeroUnix.calc", "0", "0");
		test(verbose, path + "/greenZeroDos.calc", "0", "0");
		test(verbose, path + "/greenOne.calc", "1", "1");
		test(verbose, path + "/greenLit.calc", "123", "123");
		test(verbose, path + "/greenUnExpMinus.calc", "-1", "-1");
		test(verbose, path + "/greenBinExpPlus.calc", "(+ 1 1)", "2");
		test(verbose, path + "/greenBinExpPlus01.calc", "(+ 01)", "1");
		test(verbose, path + "/greenBinExpMinus.calc", "(- 1 1)", "0");
		test(verbose, path + "/greenBinExpDivide.calc", "(/ 5 2)", "2");
		test(verbose, path + "/greenBinExpEqual.calc", "(== 1 2)", "0");
		test(verbose, path + "/greenBinExpEqual2.calc", "(== 3 3)", "1");
		test(verbose, path + "/greenNestedExp.calc", "(+ (- 2 3) (+ 0 1))", "0");
		test(verbose, path + "/greenIf0.calc", "(if 0 1 0)", "0");
		test(verbose, path + "/greenIf0Indent.calc", "(if 0 1 0)", "0");
		test(verbose, path + "/greenIf1.calc", "(if 1 1 0)", "1");
		test(verbose, path + "/greenIf2.calc", "(if 2 1 0)", "1");
		test(verbose, path + "/greenNestedIfInTest.calc", "(if (if 1 1 0) 2 3)", "2");
		test(verbose, path + "/greenNestedIfInBranch.calc", "(if 1 (if 1 2 0) 3)", "2");
		test(verbose, path + "/greenNestedIfInExp.calc", "(+ (if 1 1 0) 1)", "2");
		test(verbose, path + "/greenNestedExpAsTest.calc", "(if (- 1 1) 1 44)", "44");

		report();
	}
}
