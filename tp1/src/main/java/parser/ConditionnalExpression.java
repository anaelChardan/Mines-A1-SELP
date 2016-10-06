package parser;

import eval.State;

public class ConditionnalExpression extends Expression {
    private final Expression expr1;
    private final Expression expr2;
    private final Expression expr3;

    public ConditionnalExpression(Expression expr1, Expression expr2, Expression expr3) {
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.expr3 = expr3;
    }

    @Override
    public String toString() {
        return "Conditionnal(" + expr1 + "," + expr2 + "," + expr3 + ")";
    }

    @Override
    public int eval(State<Integer> stVar, State<FunctionDefinition> stFunc) {
        return expr1.eval(stVar, stFunc) == 0 ? expr3.eval(stVar, stFunc) : expr2.eval(stVar, stFunc);
    }
}
