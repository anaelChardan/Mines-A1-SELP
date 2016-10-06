package parser;

import eval.State;

import java.util.ArrayList;
import java.util.List;

public class FunctionCall extends Expression {

    String calledFunction;
    List<Expression> expressions;

    public FunctionCall(String calledFunction, List<Expression> expressions) {
        this.calledFunction = calledFunction;
        this.expressions = expressions;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("FunctionCall( ");
        builder.append(calledFunction + " ");

        if (expressions != null)
        {
            for(Expression exp : expressions)
            {
                builder.append(exp.toString() + " ");
            }
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    public int eval(State<Integer> stVar, State<FunctionDefinition> stFunc) {
        List<Integer> valuesPassed = new ArrayList<>();

        for (Expression expression : expressions)
        {
            valuesPassed.add(expression.eval(stVar, stFunc));
        }

        return stFunc.lookup(calledFunction).eval(valuesPassed, stFunc);
    }
}
