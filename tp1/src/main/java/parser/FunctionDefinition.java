package parser;

import eval.State;
import lexer.SLexer;
import lexer.Token;
import lexer.UnexpectedCharacter;
import lexer.tokens.Define;
import lexer.tokens.LPar;
import lexer.tokens.RPar;

import java.io.IOException;
import java.util.List;

public class FunctionDefinition extends AST {
    Head   head;
    Body   body;

    public FunctionDefinition(Head head, Body body) {
        this.head = head;
        this.body = body;
    }

    public static FunctionDefinition parse(Token t) throws IOException, UnexpectedCharacter, SyntacticError {
        if (t instanceof LPar)
        {
            Token t2 = SLexer.getToken();

            if (t2 instanceof Define)
            {
                return parse();
            }
        }
        throw new SyntacticError("This is not a valid Function Definition");
    }

    public static FunctionDefinition parse() throws IOException, UnexpectedCharacter, SyntacticError {
        Head head = Head.parse(SLexer.getToken());
        Body body = Body.parse(SLexer.getToken());
        Token finalToken = SLexer.getToken();
        if (finalToken instanceof RPar)
        {
            return new FunctionDefinition(head, body);
        }
        throw new SyntacticError("This is not a valid function definition");
    }

    @Override
    public String toString() {
        return "FunctionDefinition( " + head.toString() + " " + body.toString() + " )";
    }

    public int eval(List<Integer> args, State<FunctionDefinition> stFunc)
    {
        if (args.size() != this.head.variables.size())
        {
            throw new RuntimeException("The call of the function " + this.head.functionName + " has not the same number of arguments");
        }

        return body.eval(this.bindArguments(this.head.variables, args), stFunc );

    }

    private State<Integer> bindArguments(List<String> formals, List<Integer> args) {
        State<Integer> integers = new State<>();
        int count = 0;
        for(String var : formals)
        {
            integers.put(var, args.get(count));
            count++;
        }

        return integers;
    }
}
