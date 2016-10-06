package lexer;

public abstract class Token {
    public String toString()
    {
        return this.getClass().getSimpleName();
    }
}
