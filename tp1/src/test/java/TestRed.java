public class TestRed extends Test {
	
	public static void main(String[] args) {
		Test.main(args);

		String path = "src/test/resources/test";

		// Calc Red
		test(verbose, path + "/redInDisorder.calc", "(define (add7 n)\n" +
				"    (= c (add4 n))\n" +
				"    (= c (add3 c))\n" +
				"    c\n" +
				")\n" +
				"(define (add4 n)\n" +
				"    (+ n 4)\n" +
				")\n" +
				"(define (add3 n)\n" +
				"    (+ n 3)\n" +
				")\n" +
				"(add7 0)", "7");
		test(verbose, path + "/redFactoriel.calc", "(define (fact n)\n" +
				"    (if (< n 1)\n" +
				"        1\n" +
				"        (* n (fact (- n 1)))\n" +
				"    )\n" +
				")\n" +
				"(fact 5)", "120");
		test(verbose, path + "/redFibonnaci.calc", "(define (fibonacci n)\n" +
				"   (= fib1 (- n 1))\n" +
				"   (= fib2 (- n 2))\n" +
				"   (if (< n 2)\n" +
				"       1\n" +
				"       (+ (fibonacci fib1)\n" +
				"          (fibonacci fib2)\n" +
				"       )\n" +
				"   )\n" +
				")\n" +
				"(fibonacci 15)", "987");

		report();
	}
}
