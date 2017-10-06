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

    public final String conjunctiveExpression() throws ParserException {
        ConjunctiveRepresentation rep = factor.conjunctiveRepresentation();
        System.out.println(rep.getRepresentation());
        Lexer lexer = new Lexer(rep.getRepresentation());

        if (isDoubleNegative(rep)) {
            // return the positive form
            return computeRepresentation(lexer);
        }
        else {
            // Return a negation of the expression
            return rep.getRepresentation();
        }

    }

    private String computeRepresentation(Lexer lexer)
            throws ParserException {
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        LocationalToken tok;


        do {
            lexer.matcherFind();
            tok = lexer.next();
//            System.out.println(tok.getTokenType().name());

            switch (tok.getTokenType()) {
                case NOT:
                    break;

                case OR:
                    sb.append(" and ");
                    break;

                default:
                    sb.append(tokStringRep(tok));
            }
        }
        while(lexer.hasNext());


//
//
//        while (lexer.hasNext()) {
//            tok = lexer.next();
//
//            switch (tok.getTokenType()) {
//                case NOT:
//                    break;
//
//                case OR:
//                    sb.append("and");
//                    break;
//
//                default:
//                    sb.append(tokStringRep(tok));
//            }

        sb.append(')');
        return sb.toString();
    }

    private String tokStringRep(LocationalToken token) {
        switch (token.getTokenType()) {
            case ID:
                return token.getTokenAncilData().get();
            case OPEN:
                return "(";
            case CLOSE:
                return ")";
            default:
                return "";
        }
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
        if (!positive) {
            String negation;

            if (factor.getClass() == CompoundFactor.class) {
                negation = "(not ";
                negation += "(";
                negation += factor.conjunctiveRepresentation().getRepresentation();
                negation += "))";
                return negation;
            }
            else {
                return "not " + factor.toString();    // this will be ID
            }
        }
        else {
            return factor.toString();
        }
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

            lexer.nextValid();
            return new DisjunctiveExpression(factor, positive);
        }

        public static boolean checkForNot(LocationalToken token) {
            return token.getTokenType() == Token.Type.NOT;
        }
    }



}
