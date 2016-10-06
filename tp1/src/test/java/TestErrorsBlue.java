public class TestErrorsBlue extends Test {

	public static void main(String[] args){

		Test.main(args);

		String path = "src/test/resources/test";

		// various errors
		test(verbose, path + "/blueErrorEqual.calc", "(= a)", "error");
		test(verbose, path + "/blueErrorEqual0.calc", "(= 5)", "error");
		test(verbose, path + "/blueErrorEqual2.calc", "(=a 5)", "error");
		test(verbose, path + "/blueErrorEqual3.calc", "(= (= a (+ 1 2)) (- n 1))", "error");
		test(verbose, path + "/blueErrorBody0.calc", "34 (= a 12) a", "error");

		report();
	}
}
