package eil11.lexer;

import java.util.Scanner;

public class LexerRunner {

    public static void main (String[] args) {
        System.out.print("Enter a disjunction: ");
        Scanner sc = new Scanner(System.in);

        String inputString = sc.nextLine();
        DisjunctiveLexer lexer = new DisjunctiveLexer(inputString);
        DisjunctiveExpression expression;

        try {
            expression = DisjunctiveExpression.Builder.build(lexer.nextValid().get(), lexer);
            System.out.println(expression.conjunctiveExpression());
        }
        catch (Exception e) {
            System.out.println("Error occurred in processing input. Make sure your input is a disjunction");
            System.out.println("Disjunction is of this form: (FACTOR and FACTOR), where factor can be expanded");
            System.out.println("For example, \"(a and b)\" is valid.");
        }
    }



}
