package eil11.lexer;


public class CompoundFactor implements Factor {

    private final DisjunctiveExpression leftExpression;
    private final DisjunctiveExpression rightExpression;

    @Override
    public String toString() {
        return ("(" + this.leftExpression.toString() + " and " + this.rightExpression.toString() + ")");
    }

//    @Override
//    public ConjunctiveRepresentation conjunctiveRepresentation() {
//        StringBuilder expression = new StringBuilder();
//        String lExpress;
//        String rExpress;
//
//        // Get if they are IDs
//        lExpress = getIDString(leftExpression);
//        rExpress = getIDString(rightExpression);
//
//        // If they are null, then the expressions were not IDs
//        return null;
//    }

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

            DisjunctiveExpression  left;
            DisjunctiveExpression  right;

            // Compound factor: OPEN DisjunctiveExpression AND DisjunctiveExpression CLOSE
            left = DisjunctiveExpression.Builder.build(token, lexer);

            ParserException.verify(Token.Type.AND, lexer.nextValid().get());
            LocationalToken nextStart = lexer.nextValid().get();


            right = DisjunctiveExpression.Builder.build(nextStart, lexer);
            return new CompoundFactor(left, right);
        }

        private static void verifyAndAdvance(Token.Type type, DisjunctiveLexer lexer)
                throws ParserException{
            ParserException.verify(type, lexer.nextValid().get());    // nextValid advances
        }

    }
}
