package eil11.lexer;

import java.util.Optional;

public class DisjunctiveExpression {

    private final Factor factor;
    private final boolean positive;

    public final DisjunctiveExpression negate() {
        return new DisjunctiveExpression(factor, !positive);
    }

    private DisjunctiveExpression(Factor factor, boolean positive) {
        this.factor = factor;
        this.positive = positive;
    }


    private class Builder {
        public DisjunctiveExpression build(LocationalToken token, DisjunctiveLexer lexer)
                throws ParserException {

            Factor factor;
            boolean positive;

            int orderIndex = 0;
            Optional<LocationalToken> curToken;

            positive = !checkForNot(token);  // token should be generated from a nextValid() call

            curToken = lexer.nextValid();

            try {
                factor = CompoundFactor.Builder.build(token, lexer);
            }
            catch (ParserException e) {
                factor = Identifier.Builder.build(curToken.get());
            }

            return new DisjunctiveExpression(factor, positive);
        }

        private boolean checkForNot(LocationalToken token) {
            return token.getTokenType() == Token.Type.NOT;
        }

        private boolean checkForID(LocationalToken token) throws ParserException {
            if (token.getTokenType() != Token.Type.ID) {
                throw new ParserException(ParserException.ErrorCode.ID_EXPECTED);
            }
            else {
                return true;
            }
        }
    }



}
