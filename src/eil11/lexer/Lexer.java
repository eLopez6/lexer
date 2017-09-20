package eil11.lexer;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Lexer {

    private static Pattern tokenPatterns;
    private final Matcher matcher;

    public Lexer (String input) {

    }

    public Boolean hasNext() {

    }

    public LocationalToken next() throws ParserException {

    }

    public LocationalToken next(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes) throws ParserException {

    }

}
