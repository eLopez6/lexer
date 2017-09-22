package eil11.test;


import eil11.lexer.Lexer;
import org.junit.Before;
import org.junit.Test;

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
    }

    @Test
    public void nextValid() throws Exception {
    }

    @Test
    public void complexityChecker() throws Exception {
    }

}