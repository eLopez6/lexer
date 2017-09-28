package eil11.lexer;

public class DisjunctiveExpression {

    private final Factor factor;
    private final boolean positive;

    public final DisjunctiveExpression negate() {
    }


    private class Builder {
        public DisjunctiveExpression build(LocationalToken token, DisjunctiveLexer lexer)
                throws ParserException {

            if (token.getTokenType() != Token.Type.NOT) {

            }



        }
    }

}
