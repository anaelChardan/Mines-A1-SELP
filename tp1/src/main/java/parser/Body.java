package parser;

import eval.State;
import lexer.SLexer;
import lexer.Token;
import lexer.UnexpectedCharacter;
import lexer.tokens.EOF;
import lexer.tokens.Equal;
import lexer.tokens.LPar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Body extends AST {

    Expression expression;

    public List<Definition> definitions = new ArrayList<Definition>();

    public Body(List<Definition> definitions, Expression expression) {
        this.definitions = definitions;
        this.expression = expression;
    }

    public static Body parse(Token token) throws SyntacticError, IOException, UnexpectedCharacter {
        if(token instanceof EOF)
        {
            return new Body(new ArrayList<Definition>(), null);
        }
        if (token instanceof LPar)
        {
            Token next = SLexer.getToken();

            if (!(next instanceof Equal))
            {
                return new Body(new ArrayList<Definition>(), Expression.parseAfterLPar(next));
            }

            List<Definition> defs = new ArrayList<Definition>();
            defs.add(Definition.parse());
            Body b = Body.parse(SLexer.getToken());
            defs.addAll(b.definitions);
            return new Body(defs, b.expression);
        }

        return new Body(new ArrayList<Definition>(), Expression.parse(token));
    }

    public static Body parse(Token t1, Token t2) throws SyntacticError, UnexpectedCharacter, IOException {
        if (t1 instanceof LPar && t2 instanceof Equal)
        {
            List<Definition> defs = new ArrayList<Definition>();
            defs.add(Definition.parse());
            Body b = Body.parse(SLexer.getToken());
            defs.addAll(b.definitions);
            return new Body(defs, b.expression);
        }

        return new Body(new ArrayList<Definition>(), Expression.parse(t1, t2));
    }

    public int eval(State<Integer> stVar, State<FunctionDefinition> stFunc)
    {
        for (Definition def : definitions)
        {
            def.eval(stVar, stFunc);
        }
        return expression.eval(stVar, stFunc);
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Body( \n");
        for(Definition def : definitions)
        {
            builder.append(def.toString() + " \n");
        }
        if (expression != null)
        {
            builder.append(expression.toString() + "\n");
        }
        builder.append(")");
        return builder.toString();
    }
}
