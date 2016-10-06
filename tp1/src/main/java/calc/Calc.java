package calc;

import eval.State;
import lexer.SLexer;
import lexer.Token;
import lexer.UnexpectedCharacter;
import parser.*;

import java.io.IOException;

public class Calc {
    public static void main(String[] args) throws IOException, UnexpectedCharacter, SyntacticError {
        SLexer.init(args[0]);

        //Test Green
        //Expression parsed = Expression.parse(SLexer.getToken());

        //Test Blue
        //Body parsed = Body.parse(SLexer.getToken());

        //Test Rouge
        Program parsed = Program.parse(SLexer.getToken());

        System.out.println(parsed.eval(new State<Integer>(), new State<FunctionDefinition>()));

    }
}
