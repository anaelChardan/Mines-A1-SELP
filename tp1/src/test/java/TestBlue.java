public class TestBlue extends Test {
	
	public static void main(String[] args) {
		Test.main(args);

		String path = "src/test/resources/test";

		// Calc Blue
		test(verbose, path + "/blueVar.calc", "(= a 11) a", "11");
		test(verbose, path + "/blueVar2.calc", "(= a 11) (= a 12) a", "12");
		test(verbose, path + "/blueDef.calc", "(= a (+ 5 6)) a", "11");
		test(verbose, path + "/blueDef2.calc", "(= b 7) (= c 8) (= a (if (< b c) b c)) a", "7");

		report();
	}
}
