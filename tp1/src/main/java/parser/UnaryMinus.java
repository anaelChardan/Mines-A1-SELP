package parser;

import eval.State;

public class UnaryMinus extends Expression {
    private final Expression expr1;

    public UnaryMinus(Expression expr1) {
        this.expr1 = expr1;
    }


    @Override
    public String toString() {
        return "UnaryMinus(" + expr1 + ")";
    }

    @Override
    public int eval(State<Integer> stVar, State<FunctionDefinition> stFunc) {
        return - expr1.eval(stVar, stFunc);
    }
}
