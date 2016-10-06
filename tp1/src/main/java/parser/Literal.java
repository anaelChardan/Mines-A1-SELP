package parser;

import eval.State;

public class Literal extends Expression {
    int value;

    public Literal(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Literal(" + value + ")";
    }

    @Override
    public int eval(State<Integer> stVar, State<FunctionDefinition> stFunc) {
        return value;
    }
}
