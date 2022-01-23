package com.epam.infohandling;

import com.epam.infohandling.interpreter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpressionCalculator {

    private static final String SEPARATOR = "\\p{Blank}+";

    public double calculate(String expression, Map<String, Double> parameters) {
        List<AbstractExpression> expressions = parse(expression);
        Context context = new Context();

        for (AbstractExpression terminal: expressions) {
            terminal.interpret(context);
        }

        return context.popValue();
    }

    public List<AbstractExpression> parse (String expression) {
        List<AbstractExpression> expressions = new ArrayList<>();

        for (String lexeme: expression.split(SEPARATOR)) {
            if (lexeme.isEmpty()) {
                continue;
            }

            char temp = lexeme.charAt(0);
            switch (temp) {
                case '+':
                    expressions.add(new TerminalExpressionPlus());
                    break;
                case '-':
                    expressions.add(new TerminalExpressionMinus());
                    break;
                case '*':
                    expressions.add(new TerminalExpressionMultiply());
                    break;
                case '/':
                    expressions.add(new TerminalExpressionDivide());
                    break;
                default:
                    Scanner scanner = new Scanner(lexeme);
                    if (scanner.hasNextDouble()) {
                        expressions.add(new NonTerminalExpression(scanner.nextDouble()));
                    }
            }
        }

        return expressions;
    }
}
