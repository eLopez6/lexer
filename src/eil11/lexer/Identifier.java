package eil11.lexer;

public final class Identifier implements Factor {

    private final String id;

    @Override
    public String toString() {
        return "Identifier{" +
                "id='" + id + '\'' +
                '}';
    }

    private Identifier(String id) {
        this.id = id;
    }

    @Override
    public ConjunctiveRepresentation conjunctiveRepresentation() {
        return new ConjunctiveRepresentation(id, false);
    }

    public static class Builder {
        public static final Identifier build(LocationalToken token) throws ParserException {
            ParserException.verify(token.getTokenType(), token);
            return new Identifier(token.getTokenAncilData().get());
        }
    }
}
