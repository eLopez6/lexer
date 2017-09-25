package eil11.lexer;

public final class Identifier {

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

    public static class Builder {
        public static final Identifier build(LocationalToken token) throws ParserException {
            if (token.getTokenType() != Token.Type.ID) {
                throw new ParserException(ParserException.ErrorCode.ID_EXPECTED);
            }
            else {
                return new Identifier(token.getTokenAncilData().get());
            }
        }
    }

}
