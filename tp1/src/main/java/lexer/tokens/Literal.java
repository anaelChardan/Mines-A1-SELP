package lexer.tokens;

import lexer.Token;

public class Literal extends Token {

    public int value;

    public Literal( int val ) {
        this.value = val;
    }

    public String toString()
    {
        return this.getClass().getSimpleName() + "(" + value + ")";
    }
}
