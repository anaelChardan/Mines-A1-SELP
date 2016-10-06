public class TestErrorsRed extends Test {

	public static void main(String[] args){

		Test.main(args);

		String path = "src/test/resources/test";

		// various errors
		test(verbose, path + "/blueErrorEqual.calc", "(= a)", "error");
		report();
	}
}
