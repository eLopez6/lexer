package eil11.test;


import eil11.lexer.*;
import org.junit.Test;


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
    public void nextValid() throws ParserException {
        DisjunctiveLexer lexer = new DisjunctiveLexer("(a and b)");
        DisjunctiveLexer fail = new DisjunctiveLexer("(a or b)");

        try {
            LocationalToken result = lexer.nextValid().get();
            assertEquals(Token.Type.OPEN, result.getTokenType());

            result = lexer.nextValid().get();
            assertEquals(Token.Type.ID, result.getTokenType());

            result = lexer.nextValid().get();
            assertEquals(Token.Type.AND, result.getTokenType());

            result = lexer.nextValid().get();
            assertEquals(Token.Type.ID, result.getTokenType());

            result = lexer.nextValid().get();
            assertEquals(Token.Type.CLOSE, result.getTokenType());

        }
        catch (ParserException e) {
            System.out.println("exception thrown");
        }


        try {
            LocationalToken failer = fail.nextValid().get();
            assertEquals(Token.Type.OPEN, failer.getTokenType());

            failer = fail.nextValid().get();
            assertEquals(Token.Type.ID, failer.getTokenType());

            failer = fail.nextValid().get();
            assertEquals(Token.Type.OR, failer.getTokenType());
        }
        catch (ParserException q) {
            System.out.println(q.toString());
        }
    }

    @Test
    public void testNegate() throws ParserException {
        DisjunctiveLexer lexer = new DisjunctiveLexer("(a and (b and c))");
        DisjunctiveExpression express;

        express = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
        DisjunctiveExpression neg = express.negate();

        System.out.println(express.toString());
        System.out.println(neg.toString());

        assertTrue(!neg.isPositive());
        assertEquals(express.getFactor(), neg.getFactor());
    }

    @Test
    public void testToString() {
        DisjunctiveLexer lexer = new DisjunctiveLexer("(a and b)");
        DisjunctiveExpression express;
    }
}
