package parser;

import eval.State;
import lexer.SLexer;
import lexer.Token;
import lexer.UnexpectedCharacter;
import lexer.tokens.Equal;
import lexer.tokens.Identifier;
import lexer.tokens.LPar;
import lexer.tokens.RPar;

import java.io.IOException;

public class Definition extends AST {

    public String variable;
    public Expression expression;

    public Definition(String variable, Expression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    //On ne sait pas si c'est une definition
    public static Definition parse(Token t) throws IOException, UnexpectedCharacter, SyntacticError {
        if (t instanceof LPar)
        {
            Token next = SLexer.getToken();

            if (next instanceof Equal)
            {
                return parse();
            }
        }
        throw new SyntacticError("this is not a Definition");
    }

    //On sait que c'est une definition
    public static Definition parse() throws IOException, UnexpectedCharacter, SyntacticError {
        Token identifer = SLexer.getToken();
        if ( identifer instanceof Identifier)
        {
            Expression expression = Expression.parse(SLexer.getToken());
            Token finalToken = SLexer.getToken();

            if (finalToken instanceof RPar)
            {
                return new Definition(((Identifier) identifer).value, expression);
            }
        }
        throw new SyntacticError("This is not a Definition");
    }

    public void eval(State<Integer> stVar, State<FunctionDefinition> stFunc)
    {
        stVar.bind(variable, expression.eval(stVar, stFunc));
    }


    @Override
    public String toString() {
        return "Definition( '=' "+ variable + " " + expression.toString() + " )";
    }
}
