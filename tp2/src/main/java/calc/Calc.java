package calc;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import parser.Calc0Lexer;
import parser.Calc0Parser;

import java.io.FileInputStream;
import java.io.InputStream;

public class Calc {
	static boolean verbose = false;
	
    public static void main(String[] args) throws Exception {
        String inputFile = null; 
        if ( args.length>0 ) inputFile = args[0];        
        if (args.length>1 && args[1].equals("-v")) verbose = true;
        InputStream is = System.in;
        if ( inputFile!=null ) is = new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(is); 
        Calc0Lexer lexer = new Calc0Lexer(input); 
        CommonTokenStream tokens = new CommonTokenStream(lexer); 
        Calc0Parser parser = new Calc0Parser(tokens); 
        ParseTree tree = parser.program(); 
        System.out.println(tree.toStringTree(parser));
    }
}        