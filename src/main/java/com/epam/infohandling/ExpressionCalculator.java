package com.epam.infohandling;

import com.epam.infohandling.exception.InformationHandlingException;
import com.epam.infohandling.interpreter.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpressionCalculator {

    private static final String SEPARATOR = "\\p{Blank}+";

    public double calculate(String expression, Map<String, Double> parameters) throws InformationHandlingException {
        List<AbstractExpression> expressions = parse(expression, parameters);
        Context context = new Context();

        for (AbstractExpression terminal: expressions) {
            terminal.interpret(context);
        }

        return context.popValue();
    }

    private List<AbstractExpression> parse (String expression, Map<String, Double> parameters) throws InformationHandlingException {
        List<AbstractExpression> expressions = new ArrayList<>();

        for (String lexeme: expression.split(SEPARATOR)) {
            if (lexeme.isEmpty()) {
                continue;
            }

            switch (lexeme) {
                case "+":
                    expressions.add(new TerminalExpressionAddition());
                    break;
                case "-":
                    expressions.add(new TerminalExpressionSubtraction());
                    break;
                case "*":
                    expressions.add(new TerminalExpressionMultiplication());
                    break;
                case "/":
                    expressions.add(new TerminalExpressionDivision());
                    break;
                default:
                    Scanner scanner = new Scanner(lexeme);
                    if (scanner.hasNextDouble()) {
                        double value = scanner.nextDouble();
                        expressions.add(new NonTerminalExpression(value));
                    } else {
                        String key = scanner.next();
                        if (parameters.containsKey(key)) {
                            double value = parameters.get(key);
                            expressions.add(new NonTerminalExpression(value));
                        } else {
                            throw new InformationHandlingException("Getting the value of a missing key");
                        }
                    }
            }
        }

        return expressions;
    }
}
