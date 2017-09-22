package eil11.lexer;

import java.util.Optional;
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
            Token.Type tokenType = null;
            String match = "";


            // Checks for all types to see which token corresponds to correct Token Type
            for (Token.Type type : Token.Type.values()) {
                match = matcher.group(type.getPattern());
                tokenType = type;
                if (!match.equals(""))
                    break;
            }

            // No token found
            if (match.equals(""))
                throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED);

            return new LocationalToken(Token.of(tokenType, match), matcher.start());

        } else {
            throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED);
        }
    }

    public Optional<LocationalToken> nextValid(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes) throws ParserException {
        LocationalToken locToken;

        while (hasNext()) {
            locToken = next();

            if (invalidTypes.contains(locToken.getTokenType()))
                throw new ParserException(locToken, ParserException.ErrorCode.INVALID_TOKEN);

            if (validTypes.contains(locToken.getTokenType()))
                return Optional.ofNullable(locToken);

            // do nothing if a neither valid nor invalid token is found
        }

        return Optional.empty();
    }

    /**
     * Measures the complexity by summing the frequency of all ANDs and ORs
     * Resets the matcher, disrupting count. This could affect other methods.
     * @return sum of complexity
     */
    public int complexityChecker() {
        // sum all ands
        // sum all ors
        // return
        String match = null;
        Token.Type tokenType = null;

        int complexitySum = 0;
        matcher.reset();    // Resets to start point
        while (hasNext()) {
            // Checks for all types to see which token corresponds to correct Token Type
            for (Token.Type type : Token.Type.values()) {
                match = matcher.group(type.getPattern());
                tokenType = type;
                if (!match.equals(""))
                    break;
            }

            if (Token.of(tokenType, match).getType().getIsComplex())
                complexitySum++;
        }
        return complexitySum;
    }

}
