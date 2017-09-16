package eil11.test;

import eil11.lexer.Token;
import org.junit.Before;
import org.junit.Test;
import java.util.Optional;

import static org.junit.Assert.*;

/* Instead use assertEquals() for comparisons of Strings, etc */

public class TokenTest {
    @Before
    public void setUp() throws Exception {
        Token.of(Token.Type.NOT, null);
        Token.of(Token.Type.NUMBER, "456");
        Token.of(Token.Type.ID, "the1");
        Token.of(Token.Type.OPEN, null);
        Token.of(Token.Type.CLOSE, null);
    }


    @Test
    public void testGetData() throws Exception {
        Optional<String> opt1 = Optional.empty();
        Optional<String> opt2 = Optional.of("the1");
        Token.Type val1 = Token.Type.NOT;
        Token.Type val2 = Token.Type.ID;

        Token resultOf = Token.of(val1, null);
        Optional<String> optOf = resultOf.getData();

        assertEquals(optOf, Optional.empty());
        assertTrue(Token.of(val2, "the1").getData().equals(opt2));
        assertFalse(Token.of(val1,null).getData().equals(opt2));
    }

    @Test
    public void testHashCode() throws Exception {
        Optional<String> opt1 = Optional.empty();
        Optional<String> opt2 = Optional.of("the1");
        Token.Type val1 = Token.Type.NOT;
        Token.Type val2 = Token.Type.ID;

        int hash1 = Token.of(val1, null).hashCode();
        int hash2 = Token.of(val1, null).hashCode();

        int hash3 = Token.of(val2, "the1").hashCode();
        int hash4 = Token.of(val2, "the1").hashCode();

        assertTrue(hash1 == hash2);
        assertTrue(hash3 == hash4);
        assertFalse(hash1 == hash3);
    }

    @Test
    public void testToString() throws Exception {
        Optional<String> opt1 = Optional.empty();
        Optional<String> opt2 = Optional.of("the1");
        Token.Type val1 = Token.Type.NOT;
        Token.Type val2 = Token.Type.ID;

        String str1 = Token.of(val1, null).toString();
        String str2 = Token.of(val2, "the1").toString();

        assertTrue(str1.equals("!"));
        assertTrue(str2.equals("the1"));
        assertFalse(str1.equals(str2));
    }

    @Test
    public void testOf() throws Exception {
        // Testing adding a new token without ancillary data
        Token.Type new1 = Token.Type.WHITESPACE;
        Token result1 = Token.of(new1, null);
        assertTrue(result1 == Token.of(new1, null)); // Checks for the same object\

        // Testing adding a new token with ancillary data
        Token.Type new2 = Token.Type.ID;
        Token result2 = Token.of(new2, "the2");
        assertTrue(result2 == Token.of(new2, "the2"));

        // Testing retrieval of Token (should work if the other tests have worked)
        assertTrue(result1 == Token.of(new1, null));
    }

}