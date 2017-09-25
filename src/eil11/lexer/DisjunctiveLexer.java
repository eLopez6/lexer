package eil11.lexer;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class DisjunctiveLexer {
    private static final Set<Token.Type> validTypes = new HashSet<>();
    private static final Set<Token.Type> invalidTypes = new HashSet<>();
    private Lexer lexer;


    static {
        validTypes.add(Token.Type.AND);
        validTypes.add(Token.Type.NOT);
        validTypes.add(Token.Type.ID);
        validTypes.add(Token.Type.CLOSE);
        validTypes.add(Token.Type.OPEN);

        invalidTypes.add(Token.Type.OR);
        invalidTypes.add(Token.Type.NUMBER);
        invalidTypes.add(Token.Type.BINARYOP);
    }

    public DisjunctiveLexer (String input) {
        this.lexer = new Lexer(input);
    }

    public Optional<LocationalToken> nextValid() throws ParserException {
        return this.lexer.nextValid(validTypes, invalidTypes);
    }


}
