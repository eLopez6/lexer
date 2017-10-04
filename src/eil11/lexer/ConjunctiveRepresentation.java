package eil11.lexer;

public final class ConjunctiveRepresentation {
    private final String representation;
    private final boolean negation;

    public String getRepresentation() {
        return representation;
    }

    public boolean isNegation() {
        return negation;
    }

    public ConjunctiveRepresentation(String representation, boolean negation) {
        this.representation = representation;
        this.negation = negation;
    }
}
