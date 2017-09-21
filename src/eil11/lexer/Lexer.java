package eil11.lexer;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Lexer {

    private static Pattern tokenPatterns;
    private final Matcher matcher;

    static {
        int i = 0;
        StringBuilder pattern = new StringBuilder();
        Token.Type typeArr[] = Token.Type.values();


        while (true) {
            pattern.append(typeArr[i].getPattern());
            if (i == (typeArr.length - 1)) {
                break;
            }
            pattern.append("|");
            i++;
        }
        tokenPatterns = Pattern.compile(pattern.toString());
    }

    public Lexer (String input) {
        this.matcher = tokenPatterns.matcher(input);

    }

    public Boolean hasNext() {
        return matcher.find();
    }

    public LocationalToken next() throws ParserException {
        if (hasNext()) {
            return new LocationalToken(Token.of(), matcher.end());



        } else {
            throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED);
        }
    }

    public LocationalToken nextValid(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes) throws ParserException {
//        Set<LocationalToken> set = new HashSet<LocationalToken>(Collections.list(enumeration));
    }

}
