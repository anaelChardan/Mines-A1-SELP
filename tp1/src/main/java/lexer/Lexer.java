package lexer;

import lexer.tokens.*;

import java.util.*;
import java.io.*;

public class Lexer {
    private FileReader in;
    private int i; // current ASCII character (coded as an integer)


    public Lexer( String filename ) throws IOException {
        File file = new File( filename );
        try {
            in = new FileReader( file );
            i = in.read(); // initialize lexer
        } catch ( FileNotFoundException e ) {
            System.err.println( "File : " + filename + " not found" );
            throw e; // pass the exception up the stack
        } catch ( IOException e ) {
            in.close(); // close the reader
            throw e; // pass the exception up the stack
        }
    }

    public List<Token> lex() throws UnexpectedCharacter, IOException {
        // return the list of tokens recorded in the file
        List<Token> tokens = new ArrayList<Token>();

        try {
            Token token = getToken();

            while ( !(token instanceof EOF) ) {
                tokens.add( token );
                token = getToken();
            }
            tokens.add( token ); // this is the EOF token
        } catch ( IOException e ) {
            in.close(); // close the reader
            throw e; // pass the exception up the stack
        }
        return tokens;
    }

    protected Token getToken() throws UnexpectedCharacter, IOException {
        switch ( i ) {
            case -1:
                in.close();
                return new EOF();
            case '(':
                i = in.read();
                return new LPar();
            case ')':
                i = in.read();
                return new RPar();
            case '=':
                i = in.read();
                if (i == '='){
                    i = in.read();
                    return new Op(Operator.EQUALS);
                }
                return new Equal();
            case '+':
                i = in.read();
                return new Op( Operator.PLUS );
            case '-':
                i = in.read();
                return new Op( Operator.MINUS );
            case '*':
                i = in.read();
                return new Op( Operator.TIMES );
            case '/':
                i = in.read();
                return new Op( Operator.DIVIDE );
            case '<':
                i = in.read();
                return new Op( Operator.INFERIOR );
            case '0':
                i = in.read();
                return new Literal( 0 );
            case 10 :case  32:
                i = in.read();
                return getToken();
            default:
                StringBuilder sb = new StringBuilder( );
                if (isLetter( i ))
                {
                    do {
                        sb.append( Character.toChars( i ) );
                        i = in.read();
                    } while ( isLetter( i ) || isDigit( i ) );

                    String letterToken = sb.toString();

                    if ( letterToken.equals( "if" ) )
                    {
                        return new If();
                    }

                    if ( letterToken.equals( "define" ) )
                    {
                        return new Define();
                    }

                    return new Identifier( letterToken );
                }
                if (isDigit( i ))
                {
                    do {
                        sb.append( Character.toChars( i ) );
                        i = in.read();
                    } while ( isDigit( i ));

                    return new Literal( Integer.parseInt( sb.toString() ) );
                }
                throw new UnexpectedCharacter( i );
        }
    }

    private boolean isLetter(int i)
    {
        return 'a' <= i && i <= 'z';
    }

    private boolean isDigit(int i)
    {
        return Character.isDigit( i );
    }

}


