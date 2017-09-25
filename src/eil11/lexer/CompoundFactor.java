package eil11.lexer;

import java.util.Optional;

public class CompoundFactor {

    private final Identifier leftExpression;
    private final Identifier rightExpression;

    @Override
    public String toString() {
        return "CompoundFactor{" +
                "leftExpression=" + leftExpression.toString() +
                ", rightExpression=" + rightExpression.toString() +
                '}';
    }

    private CompoundFactor(Identifier leftExpression, Identifier rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }


    private static class Builder {

        private static final CompoundFactor build(LocationalToken token,
                                                  DisjunctiveLexer lexer) throws ParserException {
            Token.Type[] order = {Token.Type.OPEN, Token.Type.ID, Token.Type.AND, Token.Type.ID,
                                    Token.Type.CLOSE};

            int orderIndex = 0;
            Optional<LocationalToken> curToken;
            Identifier left = null;
            Identifier right = null;


            if (token.getTokenType() != order[orderIndex]) {
                throw new ParserException(token, ParserException.ErrorCode.OPEN_EXPECTED);
            }
            orderIndex++;

            while (orderIndex < order.length) {
                curToken = lexer.nextValid();
                if (curToken.get().getTokenType() != order[orderIndex]){
                    throw new ParserException(order[orderIndex].getErrorCode().get());
                }

                if (orderIndex == 1) {
                    left = Identifier.Builder.build(curToken.get());
                }

                if (orderIndex == 3) {
                    right = Identifier.Builder.build(curToken.get());
                }

                orderIndex++;
            }

            return new CompoundFactor(left, right);
        }

    }
}
