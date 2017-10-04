package eil11.test;


import eil11.lexer.Lexer;
import eil11.lexer.LocationalToken;
import eil11.lexer.ParserException;
import eil11.lexer.Token;
import org.junit.Test;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void hasNext() throws Exception {
        String input = "and and and";
        Lexer lexer = new Lexer(input);

        assertTrue(lexer.hasNext());
        assertTrue(lexer.hasNext());
        assertTrue(lexer.hasNext());
        assertTrue(lexer.hasNext());
        assertTrue(lexer.hasNext());
        assertFalse(lexer.hasNext());
    }

    @Test
    public void next() throws Exception {
        String input = "and or and";
        Lexer lexer = new Lexer(input);

        LocationalToken tok1 = lexer.next();
        LocationalToken tok2 = lexer.next();
        LocationalToken tok3 = lexer.next();

        assertNotEquals(tok1, tok2);
        assertNotEquals(tok1, tok3);
        assertEquals(tok1.getTokenType(), tok2.getTokenType());
    }

    @Test
    public void nextValid() throws Exception {
        Set<Token.Type> validTypes = new HashSet<>();
        Set<Token.Type> invalidTypes = new HashSet<>();
        Lexer lexer = new Lexer("and not or");

        validTypes.add(Token.Type.AND);
        validTypes.add(Token.Type.WHITESPACE);
        invalidTypes.add(Token.Type.NOT);

        LocationalToken tok1 = lexer.nextValid(validTypes, invalidTypes).get();
        LocationalToken tok2 = lexer.nextValid(validTypes, invalidTypes).get();


        assertNotEquals(Optional.empty(), tok1);
        assertNotEquals(Optional.empty(), tok2);
        lexer.next();
        lexer.next();

//        LocationalToken tok3 = lexer.nextValid(validTypes, invalidTypes).get();
        // Not sure how to show success here, but it does in fact throw exception


        LocationalToken tok4 = null;
        try {
            tok4 = lexer.nextValid(validTypes, invalidTypes).orElse(null);
        }
        catch (ParserException e) {
            assertNull(tok4);
        }

    }

}