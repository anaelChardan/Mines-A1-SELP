import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

import calc.Calc;

public class Test {
	static int count = 0;
	static int success = 0;
	static boolean verbose = false;
	
	public static void report(){
		System.out.println(success + " successful tests out of " + count);
	}

	public static void test(boolean verbose, String filename, String mess1, String mess2){
		count++;
		String[] args0 = new String[1];
		args0[0] = filename;
		if (verbose) {
			System.out.println("====: " + filename);
			System.out.println(mess1);
			System.out.println("expected: " + mess2);
		}
		PrintStream out = System.out;
		try {		
			System.setOut(new PrintStream("result.txt"));
			Calc.main(args0);
			System.setOut(out);
			Scanner sc = new Scanner(new File("result.txt"));
			int found = 0;
			try {
				found = sc.nextInt();
			} catch(Exception e){
				System.err.println("Result is not an integer");
			}
			sc.close();
			int expected = Integer.parseInt(mess2);
			if (verbose) System.out.println(found);
			if (found != expected) {
				System.err.println("FAILURE on " + filename);
				System.err.println("expected " + expected + " found " + found);
			} else success++;
		} catch(Exception e){
			System.setOut(out);
			if (mess2.equals("error")) success++;
		}		
	}
	public static void main(String[] args){		
		if (args.length > 0 && args[0].equals("-v")) 
			verbose = true;
		else verbose = false;
	}
}

