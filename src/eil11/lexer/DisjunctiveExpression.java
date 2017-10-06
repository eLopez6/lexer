package eil11.lexer;


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

//    public final String conjunctiveExpression() {
//        ConjunctiveRepresentation rep = factor.conjunctiveRepresentation();
//
//        if (isDoubleNegative(rep)) {
//            // return the positive form
//        }
//        else {
//            // Return a negation of the expression
//        }
//        return null;
//
//    }

    private boolean isDoubleNegative(ConjunctiveRepresentation rep) {
        return (positive && rep.isNegation());
    }

    private DisjunctiveExpression(Factor factor, boolean positive) {
        this.factor = factor;
        this.positive = positive;
    }

    @Override
    public String toString() {
        String not = "";
        if (!positive) {
            not = "not ";
        }
        return (not + factor.toString());
    }


    public static class Builder {
        public static DisjunctiveExpression build(LocationalToken token, DisjunctiveLexer lexer)
                throws ParserException {

            Factor factor;
            boolean positive;

            LocationalToken curToken;

            positive = !checkForNot(token);
            if (token.getTokenType() == Token.Type.NOT) {
                curToken = lexer.nextValid().get();
            }
            else {
                curToken = token;
            }


            if (curToken.getTokenType() == Token.Type.OPEN) {
                factor = CompoundFactor.Builder.build(lexer.nextValid().get(), lexer);
            }
            else {
                factor = Identifier.Builder.build(curToken);
            }

            return new DisjunctiveExpression(factor, positive);
        }

        public static boolean checkForNot(LocationalToken token) {
            return token.getTokenType() == Token.Type.NOT;
        }
    }



}
