package eil11.lexer;


public class CompoundFactor implements Factor {

    private final DisjunctiveExpression leftExpression;
    private final DisjunctiveExpression rightExpression;

    @Override
    public String toString() {
        return "CompoundFactor{" +
                "leftExpression=" + leftExpression.toString() +
                ", rightExpression=" + rightExpression.toString() +
                '}';
    }

    @Override
    public ConjunctiveRepresentation conjunctiveRepresentation() {
        StringBuilder expression = new StringBuilder();
        String lExpress;
        String rExpress;

        // Get if they are IDs
        lExpress = getIDString(leftExpression);
        rExpress = getIDString(rightExpression);

        // If they are null, then the expressions were not IDs





        return null;
    }

    private String getIDString(DisjunctiveExpression express) {
        if (express.getFactor().getClass() == Identifier.class) {
            return express.getFactor().toString();
        }
        else {
            return null;
        }
    }

    private CompoundFactor(DisjunctiveExpression leftExpression, DisjunctiveExpression  rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }


    public static class Builder {

        public static final CompoundFactor build(LocationalToken token,
                                                  DisjunctiveLexer lexer) throws ParserException {

            DisjunctiveExpression  left = null;
            DisjunctiveExpression  right = null;


            // Compound factor: OPEN DisjunctiveExpression AND DisjunctiveExpression CLOSE

            if (DisjunctiveExpression.Builder.checkForTokenType(Token.Type.OPEN, token)) {
                left = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
            }

            verifyAndAdvance(Token.Type.AND, lexer);


            if ((DisjunctiveExpression.Builder.checkForTokenType(Token.Type.OPEN, token))) {
                right = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
            }

            return new CompoundFactor(left, right);
        }

        private static void verifyAndAdvance(Token.Type type, DisjunctiveLexer lexer)
                throws ParserException{
            ParserException.verify(type, lexer.nextValid().get());    // nextValid advances
        }

    }
}
