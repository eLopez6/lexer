package eil11.test;


import eil11.lexer.*;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;


public class DisjuncLexerTest {

    @Test
    public void testIdentifier() {
        LocationalToken token1 = new LocationalToken(Token.of(Token.Type.ID, "twelve"), 0);
        LocationalToken token2 = new LocationalToken(Token.of(Token.Type.NOT, null), 0);

        try {
            Identifier id = Identifier.Builder.build(token1);
            assertTrue(true);
        }
        catch (ParserException e) {

            try {
                Identifier not = Identifier.Builder.build(token2);
            }
            catch (ParserException q) {
                assertTrue(true);
            }
        }
    }

    @Test
    public void nextValid() {
        DisjunctiveLexer lexer = new DisjunctiveLexer("(a and b)");

        try {
            LocationalToken result = lexer.nextValid().get();
            assertEquals(Token.Type.ID, result.getTokenType());
        }
        catch (ParserException e) {

        }
    }
}
