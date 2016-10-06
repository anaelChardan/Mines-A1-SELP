package lexer.tokens;

import lexer.Token;

public class Identifier extends Token {
    public String value;

    public Identifier( String val ) {
        this.value = val;
    }

    public String toString()
    {
        return this.getClass().getSimpleName() + "(" + value + ")";
    }
}
