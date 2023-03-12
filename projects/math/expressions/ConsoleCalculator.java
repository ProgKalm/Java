package projects.math.expressions;

import projects.math.expressions.parser.ExpressionParser;

import java.util.Scanner;


public class ConsoleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionParser parser = new ExpressionParser();
        while (scanner.hasNextLine()) {
            try {
                System.out.println(parser.evaluation(scanner.nextLine()));
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}