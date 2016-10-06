package lexer.tokens;

import lexer.Token;

public class Op extends Token{

    public Operator value;
    public Op( Operator op ) {
        this.value = op;
    }

    public String toString()
    {
        return this.getClass().getSimpleName() + "(" + value.toString() + ")";
    }
}
