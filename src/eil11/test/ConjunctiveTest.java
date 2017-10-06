package eil11.test;

import eil11.lexer.*;
import org.junit.Test;

public class ConjunctiveTest {

    @Test
    public void testConjunc() throws ParserException {
        String input = "not ((a and b) and (c and (d and e)))";

        DisjunctiveLexer lexer = new DisjunctiveLexer(input);
        DisjunctiveExpression express;

        express = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
        System.out.println(express.toString());
        System.out.println(express.conjunctiveExpression());
    }
}
