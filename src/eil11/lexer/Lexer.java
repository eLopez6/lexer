package eil11.lexer;

import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Lexer {

    private static Pattern tokenPatterns;
    private final Matcher matcher;
    private int complexity = 0;

    static {
        int i = 0;
        StringBuilder pattern = new StringBuilder();
        Token.Type typeArr[] = Token.Type.values();


        while (true) {
            // Add capturing group to Regex pattern
            pattern.append("(?<");
            pattern.append(typeArr[i].name());
            pattern.append(">");

            // Add pattern
            pattern.append(typeArr[i].getPattern());
            pattern.append(")");
            if (i == (typeArr.length - 1)) {
                break;
            }
            pattern.append("|");
            i++;
        }
        System.out.println(pattern.toString());
        tokenPatterns = Pattern.compile(pattern.toString());
    }

    public Lexer (String input) {
        this.matcher = tokenPatterns.matcher(input);
    }

    public Boolean hasNext() {
        if (matcher.find()) {
            matcher.region(matcher.start(), matcher.end());
            return true;
        }
        else {
            return false;
        }
    }

    private Token createToken() throws ParserException {
        Token.Type tokenType = null;
        String match = "";

        // Checks for all types to see which token corresponds to correct Token Type
        for (Token.Type type : Token.Type.values()) {
            match = matcher.group();
            tokenType = type;
            if (!match.equals(""))
                break;
        }

        if (match.equals(""))
            throw new ParserException(ParserException.ErrorCode.TOKEN_EXPECTED);

        return Token.of(tokenType, match);
    }

    public LocationalToken next() throws ParserException {
        Token tok = createToken();
        complexity += tok.getType().getIsComplex() ? 1 : 0;
        return new LocationalToken(tok, matcher.start());
    }

    public Optional<LocationalToken> nextValid(Set<Token.Type> validTypes, Set<Token.Type> invalidTypes) throws ParserException {
        LocationalToken locToken;

        while (hasNext()) {
            locToken = next();
            if (invalidTypes.contains(locToken.getTokenType())) {
                throw new ParserException(locToken, ParserException.ErrorCode.INVALID_TOKEN);
            }

            if (validTypes.contains(locToken.getTokenType())) {
                return Optional.of(locToken);
            }
        }
        return Optional.empty();
    }
}
