package parser;

import eval.State;
import lexer.tokens.Operator;

public class BinaryExpression extends Expression{
    private  Expression expr1;
    private  Expression expr2;
    private  Operator operator;

    public BinaryExpression(Expression expr1, Expression expr2, Operator operator) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "BinaryExpression( '" + operator + "' "+ expr1 + ", " + expr2 + ")";
    }

    @Override
    public int eval(State<Integer> stVar, State<FunctionDefinition> stFunc) {
        int val1 = expr1.eval(stVar, stFunc);
        int val2 = expr2.eval(stVar, stFunc);
        switch (operator)
        {
            case PLUS:
                return val1 + val2;
            case MINUS:
                return val1 - val2;
            case TIMES:
                return val1 * val2;
            case DIVIDE:
                return  val1 / val2;
            case EQUALS:
                return (val1 == val2) ? 1 : 0;
            case INFERIOR:
                return (val1 < val2) ? 1 : 0;
            default:
                throw new RuntimeException("Cannot evaluation given expression");
        }
    }
}
