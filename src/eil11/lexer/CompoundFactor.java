package eil11.lexer;


public class CompoundFactor implements Factor {

    private final DisjunctiveExpression leftExpression;
    private final DisjunctiveExpression rightExpression;

    @Override
    public String toString() {
        return ("(" + this.leftExpression.toString() + " and " + this.rightExpression.toString() + ")");
    }

    @Override
    public ConjunctiveRepresentation conjunctiveRepresentation() {
//        StringBuilder expression = new StringBuilder();
        String lExpress;
        String rExpress;

        lExpress = leftExpression.negate().toString();
        rExpress = rightExpression.negate().toString();

        // If they are null, then the expressions were not IDs
        return new ConjunctiveRepresentation((lExpress + " or " + rExpress), true);
    }

    private CompoundFactor(DisjunctiveExpression leftExpression, DisjunctiveExpression  rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }


    public static class Builder {

        public static final CompoundFactor build(LocationalToken token,
                                                  DisjunctiveLexer lexer) throws ParserException {

            DisjunctiveExpression  left;
            DisjunctiveExpression  right;

            // Compound factor: OPEN DisjunctiveExpression AND DisjunctiveExpression CLOSE
            left = DisjunctiveExpression.Builder.build(token, lexer);

//            LocationalToken nextStart = lexer.nextValid().get();


            right = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
            return new CompoundFactor(left, right);
        }

        private static void verifyAndAdvance(Token.Type type, DisjunctiveLexer lexer)
                throws ParserException{
            ParserException.verify(type, lexer.nextValid().get());    // nextValid advances
        }

    }
}
