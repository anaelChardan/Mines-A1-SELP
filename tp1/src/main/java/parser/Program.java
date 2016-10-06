package parser;

import eval.State;
import lexer.SLexer;
import lexer.Token;
import lexer.UnexpectedCharacter;
import lexer.tokens.Define;
import lexer.tokens.LPar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Program extends AST {
    List<FunctionDefinition> functions;
    Body body;

    public Program(List<FunctionDefinition> functions, Body body) {
        this.functions = functions;
        this.body = body;
    }

    public static Program parse(Token t1) throws IOException, UnexpectedCharacter, SyntacticError {
        Token t2 = SLexer.getToken();

        //This is a function
        if (t1 instanceof LPar && t2 instanceof Define)
        {
            List<FunctionDefinition> functs = new ArrayList<>();
            functs.add(FunctionDefinition.parse());
            Program p = Program.parse(SLexer.getToken());
            functs.addAll(p.functions);
            return new Program(functs, p.body);
        }

        //This is not a function
        return new Program(new ArrayList<>(), Body.parse(t1, t2));
    }

    public int eval(State<Integer> stVar, State<FunctionDefinition> stFunc)
    {
        for (FunctionDefinition func : functions)
        {
            stFunc.bind(func.head.functionName, func);
        }

        return body.eval(stVar, stFunc);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Program( \n");
        for(FunctionDefinition func : functions)
        {
            builder.append(func.toString() + " \n");
        }
        if (body != null)
        {
            builder.append(body.toString() + "\n");
        }
        builder.append(")");
        return builder.toString();
    }
}
