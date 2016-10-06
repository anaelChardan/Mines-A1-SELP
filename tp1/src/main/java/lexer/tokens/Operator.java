package lexer.tokens;

public enum Operator {
    PLUS( "+" ),
    MINUS( "-" ),
    TIMES( "*" ),
    DIVIDE( "/" ),
    EQUALS( "==" ),
    INFERIOR( "<" );

    private final String op;

    private Operator( final String op ) {
        this.op = op;
    }

    @Override
    public String toString() {
        return this.op;
    }
}
