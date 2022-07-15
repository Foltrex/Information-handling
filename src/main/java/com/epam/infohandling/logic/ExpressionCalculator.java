package com.epam.infohandling.logic;

import com.epam.infohandling.exception.InformationHandlingException;
import com.epam.infohandling.interpreter.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ExpressionCalculator {
    private static final Logger LOGGER = LogManager.getLogger(ExpressionCalculator.class);

    private static final String SEPARATOR = "\\p{Blank}+";

    public double calculate(String expression, Map<String, Double> parameters) throws InformationHandlingException {
        LOGGER.debug("Calculating: " + expression);

        List<AbstractExpression> expressions = parse(expression, parameters);
        Context context = new Context();

        for (AbstractExpression terminal: expressions) {
            terminal.interpret(context);
        }

        return context.popValue();
    }

    private List<AbstractExpression> parse (String expression, Map<String, Double> parameters) throws InformationHandlingException {
        List<AbstractExpression> expressionList = new ArrayList<>();

        expression = expression.replaceAll("[\\[\\]]", "");
        for (String lexeme: expression.split(SEPARATOR)) {
            if (lexeme.isEmpty()) {
                continue;
            }

            switch (lexeme) {
                case "+":
                    expressionList.add(new TerminalExpressionAddition());
                    break;
                case "-":
                    expressionList.add(new TerminalExpressionSubtraction());
                    break;
                case "*":
                    expressionList.add(new TerminalExpressionMultiplication());
                    break;
                case "/":
                    expressionList.add(new TerminalExpressionDivision());
                    break;
                default:
                    Scanner scanner = new Scanner(lexeme);
                    if (scanner.hasNextDouble()) {
                        double value = scanner.nextDouble();
                        expressionList.add(new NonTerminalExpression(value));
                    } else {
                        String key = scanner.next();
                        if (parameters.containsKey(key)) {
                            double value = parameters.get(key);
                            expressionList.add(new NonTerminalExpression(value));
                        } else {
                            throw new InformationHandlingException("Getting the value of a missing key");
                        }
                    }
            }
        }

        return expressionList;
    }
}
