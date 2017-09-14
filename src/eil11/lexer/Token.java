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
        OPEN ("(", false),
        CLOSE (")", false),
        ID ("(D)+[dD_]+", true),
        NUMBER ("(-)?\\d+", true),
        BINARYOP ("[+-*/]", true),
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

    public boolean equals(Token comp){
        return (this.hashCode() == comp.hashCode());
    }

    public int hashCode(){
        int multiplier = 0;
        switch (getType()){
            case NOT:
                multiplier = 1;
                break;
            case AND:
                multiplier = 2;
                break;
            case OR:
                multiplier = 3;
                break;
            case OPEN:
                multiplier = 4;
                break;
            case CLOSE:
                multiplier = 5;
                break;
            case ID:
                multiplier = 6;
                break;
            case NUMBER:
                multiplier = 7;
                break;
            case BINARYOP:
                multiplier = 8;
                break;
            case WHITESPACE:
                multiplier = 9;
                break;
            default:
                System.exit(1);
        }

        if (type.getHasData()) {
            return multiplier * type.getPattern().hashCode();
        }
        else {
            return multiplier * 47717;
        }

    }

    public String toString(){
        if (this.getData().isPresent())
            return this.getData().get();
        else {
            switch (getType()) {
                case NOT:
                    return "!";
                case AND:
                    return "&";
                case OR:
                    return "|";
                case WHITESPACE:
                    return " ";
                case OPEN:
                    return "(";
                case CLOSE:
                    return ")";
                default:
                    return null;
            }
        }
    }

    private Token(Type type, Optional<String> data){
        this.type = type;
        this.data = data;
    }

    /**
     * Populate map with Tokens, returns the Token if already is in the map. Returns the newly created Token
     * @param type The type of the token
     * @param ancil The ancilliary data stored in the Token
     * @return the Token if it exists inside the Map, or the token if it is created
     */
    private Token of(Type type, String ancil){
        Token returnToken;

        Optional<String> opt;

        if (ancil != null)
            opt = Optional.of(ancil);
        else
            opt = Optional.empty();

        if (!tokenMap.isEmpty()){
            if ((returnToken = tokenMap.get(ancil)) == null) {

                returnToken = tokenMap.put(new Builder(type, opt), new Token(type, opt));
            }
        } else {
            returnToken = tokenMap.put(new Builder(type, opt), new Token(type, opt));
        }
        return returnToken;
    }

    // BUILDER PRIVATE INNER CLASS

    private class Builder {
        private final Type type;
        private final Optional<String> data;

        private Builder(Type type, Optional<String> data){
            this.type = type;
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

        public int hashCode() {
            int multiplier = 0;
            switch (type){
                case NOT:
                    multiplier = 1;
                    break;
                case AND:
                    multiplier = 2;
                    break;
                case OR:
                    multiplier = 3;
                    break;
                case OPEN:
                    multiplier = 4;
                    break;
                case CLOSE:
                    multiplier = 5;
                    break;
                case ID:
                    multiplier = 6;
                    break;
                case NUMBER:
                    multiplier = 7;
                    break;
                case BINARYOP:
                    multiplier = 8;
                    break;
                case WHITESPACE:
                    multiplier = 9;
                    break;
                default:
                    System.exit(1);
            }

            if (type.getHasData()) {
                return multiplier * type.getPattern().hashCode();
            }
            else {
                return multiplier * 47717;
            }
        }

        public boolean equals(Builder comp){
            return (this.hashCode() == comp.hashCode());
        }
    }
}