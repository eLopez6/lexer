package eil11.lexer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class Token {
    private final Token.Type type;
    private final Optional<String> data;
    private static Map<Builder, Token> tokenMap = new HashMap<>();

    public enum Type {
        NOT ("not", false, false, Optional.empty()),
        AND ("and", false, true, Optional.ofNullable(ParserException.ErrorCode.AND_EXPECTED)),
        OR ("or", false, true, Optional.empty()),
        OPEN ("\\(", false, false, Optional.ofNullable(ParserException.ErrorCode.OPEN_EXPECTED)),
        CLOSE ("\\)", false, false, Optional.ofNullable(ParserException.ErrorCode.CLOSE_EXPECTED)),
        ID ("D+", true, false, Optional.ofNullable(ParserException.ErrorCode.ID_EXPECTED)),
        NUMBER ("(-)?\\d+", true, false, Optional.empty()),
        BINARYOP ("[\\+\\-\\*\\/]", true, false, Optional.empty()),
        WHITESPACE ("\\s+", false, false, Optional.empty());

        private final String pattern;
        private final Boolean hasData;
        private final boolean isComplex;
        private final Optional<ParserException.ErrorCode> errorCode;

        Type (String pattern, Boolean hasData, boolean complex, Optional<ParserException.ErrorCode> errorCode){
            this.pattern = pattern;
            this.hasData = hasData;
            this.isComplex = complex;
            this.errorCode = errorCode;
        }

        public String getPattern() {
            return pattern;
        }

        public Boolean getHasData() {
            return hasData;
        }

        public boolean getIsComplex() {
            return isComplex;
        }

        public Optional<ParserException.ErrorCode> getErrorCode() {
            return this.errorCode;
        }
    }

    public Type getType() {
        return type;
    }

    public Optional<String> getData() {
        return data;
    }

    @Override
    public String toString() {
        String str;
        str = type.name();

        if (type.hasData) {
            str += data.get();
        }
        return str;
    }

    // Auto-generated
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        if (getType() != token.getType()) return false;
        return getData().equals(token.getData());
    }

    // Auto-generated
    @Override
    public int hashCode() {
        int result = getType().hashCode();
        result = 31 * result + getData().hashCode();
        return result;
    }

    private Token(Type type, Optional<String> data){
        this.type = type;
        if (data == null)
            this.data = Optional.empty();
        else
            this.data = data;
    }


    /**
     * Populate map with Tokens, returns the Token if already is in the map. Returns the newly created Token
     * @param type The type of the token
     * @param ancil The ancilliary data stored in the Token
     * @return the Token if it exists inside the Map, or the token if it is created
     */
    public static Token of(Type type, String ancil){
        Token returnToken;
        Optional<String> opt;
        opt = Optional.ofNullable(ancil);

        Builder searchBuilder = new Builder(type, opt);
        Token searchToken = searchBuilder.build();
        returnToken = tokenMap.putIfAbsent(searchBuilder, searchToken);

        returnToken = (returnToken == null) ? searchToken : returnToken;    // If returnToken null, assign it search
        return returnToken;
    }

    // BUILDER PRIVATE INNER CLASS

    private static class Builder {
        private final Type type;
        private final Optional<String> data;

        private Builder(Type type, Optional<String> data){
            this.type = type;
            if (data == null)
                this.data = Optional.empty();
            else
                this.data = data;
        }


        public Type getType() {
            return type;
        }

        public Optional<String> getData() {
            return data;
        }

        private Token build(){
            return new Token(getType(), getData());
        }

        // Auto-generated
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Builder builder = (Builder) o;

            if (getType() != builder.getType()) return false;
            return getData().equals(builder.getData());
        }

        // Auto-generated
        @Override
        public int hashCode() {
            int result = getType().hashCode();
            result = 31 * result + getData().hashCode();
            return result;
        }
    }
}