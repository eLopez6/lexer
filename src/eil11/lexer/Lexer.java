package eil11.lexer;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Lexer {

    private static Pattern tokenPatterns;
    private final Matcher matcher;

    static {
        tokenPatterns = Pattern.compile(Token.Type.NOT.getPattern());
        tokenPatterns = Pattern.compile(Token.Type.AND.getPattern());
        tokenPatterns = Pattern.compile(Token.Type.OR.getPattern());
        tokenPatterns = Pattern.compile(Token.Type.OPEN.getPattern());
        tokenPatterns = Pattern.compile(Token.Type.CLOSE.getPattern());
        tokenPatterns = Pattern.compile(Token.Type.ID.getPattern());
        tokenPatterns = Pattern.compile(Token.Type.NUMBER.getPattern());
        tokenPatterns = Pattern.compile(Token.Type.BINARYOP.getPattern());
        tokenPatterns = Pattern.compile(Token.Type.WHITESPACE.getPattern());
    }


    public Lexer (String input) {
//        tokenPatterns =
    }

    public Boolean hasNext() {

    }

    public LocationalToken next() throws ParserException {

    }

    public LocationalToken next(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes) throws ParserException {

    }

}
