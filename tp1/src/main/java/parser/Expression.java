package parser;

import eval.State;
import lexer.SLexer;
import lexer.Token;
import lexer.UnexpectedCharacter;
import lexer.tokens.*;
import lexer.tokens.Literal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Expression extends AST {
    public static Expression parse(Token t) throws IOException, UnexpectedCharacter, SyntacticError {

        if (t instanceof lexer.tokens.Literal) {
            return new parser.Literal(((Literal) t).value);
        }

        if (t instanceof Identifier) {
            return new Variable(((Identifier) t).value);
        }

        if (t instanceof LPar) {
            Token next = SLexer.getToken();
            return Expression.parseAfterLPar(next);
        }

        throw new SyntacticError("This is not a valid Expression");
    }

    public static Expression parseAfterLPar(Token next) throws SyntacticError, IOException, UnexpectedCharacter {
        //C'est un call de fonction
        if (next instanceof Identifier) {
            FunctionName functionName = new FunctionName(((Identifier) next).value);
            List<Expression> expressions = new ArrayList<>();

            while (!(next instanceof RPar)) {
                expressions.add(Expression.parse(SLexer.getToken()));
                next = SLexer.getToken();
            }

            return new FunctionCall(functionName.value, expressions);
        }

        if (next instanceof If) {
            Expression expr1 = Expression.parse(SLexer.getToken());
            Expression expr2 = Expression.parse(SLexer.getToken());
            Expression expr3 = Expression.parse(SLexer.getToken());
            Token last = SLexer.getToken();
            if (last instanceof RPar) {
                return new ConditionnalExpression(expr1, expr2, expr3);
            }

            throw new SyntacticError("ConditionnalExpression is like this  -> (if Expression Expresssion Expression )");
        }

        if (next instanceof Op) {
            //Il faut juste vérifier que ce soit un -
            Expression expr1 = Expression.parse(SLexer.getToken());
            Token nextToken = SLexer.getToken();

            //On arrive sur une ) donc c'est une expression unaire
            if (nextToken instanceof RPar) {
                if (((Op) next).value.equals(Operator.MINUS)) {
                    return new UnaryMinus(expr1);
                }
                throw new SyntacticError("UnaryExpresssion requires a minus like this ->  (- Expression) ");
            }

            //Ce netait pas une ) donc cest une binary expression
            Expression expr2 = Expression.parse(nextToken);
            Token last = SLexer.getToken();
            if (last instanceof RPar) {
                return new BinaryExpression(expr1, expr2, ((Op) next).value);
            }

            throw new SyntacticError("BinaryExpression is like this -> (OP Expression Expression)");
        }

        throw new SyntacticError("This is not a valid Expression");
    }

    public static Expression parse(Token t1, Token t2) throws SyntacticError, UnexpectedCharacter, IOException {
        if (t2 instanceof EOF)
        {
            if (t1 instanceof lexer.tokens.Literal) {
                return new parser.Literal(((Literal) t1).value);
            }

            if (t1 instanceof Identifier) {
                return new Variable(((Identifier) t1).value);
            }
        }

        if (t1 instanceof LPar)
        {
            return Expression.parseAfterLPar(t2);
        }

        throw new SyntacticError("This is not a valid Expression");
    }

    //Resultat de l'interpretation de l'expression
    abstract public int eval(State<Integer> stVar, State<FunctionDefinition> stFunc);
}
