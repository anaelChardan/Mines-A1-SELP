public class TestErrors extends Test {
	
	public static void main(String[] args){

		Test.main(args);

		String path = "src/test/resources/test";

	// various errors
		test(verbose, path + "/error.calc", "no file", "error");
		test(verbose, path + "/errorEmpty.calc", "", "error");
		test(verbose, path + "/errorGarbage.calc", "FOO!", "error");
		test(verbose, path + "/errorGarbage2.calc", "00", "error");
		test(verbose, path + "/errorUnaryExp.calc", "(+ 2)", "error");
		test(verbose, path + "/errorParens.calc", "(2)", "error");
		test(verbose, path + "/errorRPar.calc", "(+ 1 2", "error");
		test(verbose, path + "/errorIf.calc", "(if1 1 0)", "error");
		test(verbose, path + "/errorIf2.calc", "(if 1 1)", "error");
		test(verbose, path + "/errorDivide.calc", "(/ 1 0)", "error");

		report();
	}
}
