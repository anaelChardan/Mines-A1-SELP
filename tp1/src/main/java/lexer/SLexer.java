package lexer;

import java.io.IOException;

public class SLexer {

    private static Lexer lexer;

    public static void init(String filename) throws IOException {
        lexer = new Lexer(filename);
    }

    public static Token getToken() throws IOException, UnexpectedCharacter {
        return lexer.getToken();
    }
}
