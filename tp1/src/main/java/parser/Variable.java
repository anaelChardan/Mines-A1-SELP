package parser;

import eval.State;

public class Variable extends Expression {

    public String value;

    public Variable(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Variable(" + value + ")";
    }

    @Override
    public int eval(State<Integer> stVar, State<FunctionDefinition> stFunc) {
        return stVar.lookup(value);
    }
}
