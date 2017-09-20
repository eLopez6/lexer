package eil11.lexer;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class Token {
    private final Token.Type type;
    private final Optional<String> data;
    private static Map<Builder, Token> tokenMap = new HashMap<>();

    public enum Type {
        NOT ("(?i)not", false),
        AND ("(?i)and", false),
        OR ("(?i)or", false),
        OPEN ("\\(", false),
        CLOSE ("\\)", false),
        ID ("(D)+[dD_]+", true),
        NUMBER ("(-)?\\d+", true),
        BINARYOP ("[\\+\\-\\*\\/]", true),
        WHITESPACE ("\\s+", false);

        private final String pattern;
        private final Boolean hasData;

        Type (String pattern, Boolean hasData){
            this.pattern = pattern;
            this.hasData = hasData;
        }

        public String getPattern() {
            return pattern;
        }

        public Boolean getHasData() {
            return hasData;
        }
    }

    public Type getType() {
        return type;
    }

    public Optional<String> getData() {
        return data;
    }

    @Override
    public String toString(){
        if (this.getData().isPresent()) {

            switch (getType()) {
                case ID:
                    return "ID(" + this.getData().get() + ")";
                case NUMBER:
                    return "NUMBER(" + this.getData().get() + ")";
                case BINARYOP:
                    return "BINARYOP(" + this.getData().get() + ")";
                default:
                    return null;
            }

        }

        else {
            switch (getType()) {
                case NOT:
                    return "NOT";
                case AND:
                    return "AND";
                case OR:
                    return "OR";
                case WHITESPACE:
                    return " ";
                case OPEN:
                    return "OPEN";
                case CLOSE:
                    return "CLOSE";
                default:
                    return null;
            }
        }
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