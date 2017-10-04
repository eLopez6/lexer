package eil11.lexer;

import java.util.Optional;

public class DisjunctiveExpression {

    private final Factor factor;
    private final boolean positive;

    public Factor getFactor() {
        return factor;
    }

    public boolean isPositive() {
        return positive;
    }

    public final DisjunctiveExpression negate() {
        return new DisjunctiveExpression(factor, !positive);
    }

    public final String conjunctiveExpression() {
        ConjunctiveRepresentation rep = factor.conjunctiveRepresentation();

        if (isDoubleNegative(rep)) {
            // return the positive form
        }
        else {
            // Return a negation of the expression
        }
        return null;

    }

    private boolean isDoubleNegative(ConjunctiveRepresentation rep) {
        return (positive && rep.isNegation());
    }

    private DisjunctiveExpression(Factor factor, boolean positive) {
        this.factor = factor;
        this.positive = positive;
    }

    @Override
    public String toString() {
        return "DisjunctiveExpression{" +
                "factor=" + factor +
                ", positive=" + positive +
                '}';
    }


    public static class Builder {
        public static DisjunctiveExpression build(LocationalToken token, DisjunctiveLexer lexer)
                throws ParserException {

            Factor factor;
            boolean positive;

            Optional<LocationalToken> curToken;

            positive = !checkForNot(token);  // token should be generated from a nextValid() call

            curToken = lexer.nextValid();

            try {
                factor = CompoundFactor.Builder.build(curToken.get(), lexer);
            }
            catch (ParserException e) {
                factor = Identifier.Builder.build(curToken.get());
            }

            return new DisjunctiveExpression(factor, positive);
        }

        public static boolean checkForNot(LocationalToken token) {
            return token.getTokenType() == Token.Type.NOT;
        }


        public static boolean checkForTokenType(Token.Type type, LocationalToken token) throws ParserException {
            ParserException.verify(type, token);
            return true;
        }
    }



}
