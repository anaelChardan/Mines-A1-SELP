package parser;

import lexer.SLexer;
import lexer.Token;
import lexer.UnexpectedCharacter;
import lexer.tokens.Identifier;
import lexer.tokens.LPar;
import lexer.tokens.RPar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Head extends AST {
    String functionName;
    List<String> variables;

    public Head(String functionName, List<String> variables) {
        this.functionName = functionName;
        this.variables = variables;
    }

    public static Head parse(Token next) throws SyntacticError, IOException, UnexpectedCharacter {
        if (next instanceof LPar)
        {
            Token functionName = SLexer.getToken();
            if (functionName instanceof Identifier)
            {
                List<String> variables = new ArrayList<String>();
                Token variable = SLexer.getToken();
                while (variable instanceof Identifier)
                {
                    variables.add(((Identifier) variable).value);
                    variable = SLexer.getToken();
                }

                if (variable instanceof RPar)
                {
                    return new Head(((Identifier) functionName).value, variables);
                }
            }
        }
        throw new SyntacticError("This is not a valid Head");
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Head( ").append(functionName).append(" ");
        for (String var : variables) {
            builder.append(var + " ");
        }
        builder.append(")");
        return builder.toString();
    }
}
