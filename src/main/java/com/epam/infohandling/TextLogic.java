package com.epam.infohandling;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Composite;
import com.epam.infohandling.composite.Lexeme;
import com.epam.infohandling.composite.LexemeType;
import com.epam.infohandling.exception.InformationHandlingException;
import com.epam.infohandling.parser.ChainBuilder;
import com.epam.infohandling.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TextLogic {

    private final ExpressionCalculator expressionCalculator;

    public TextLogic(ExpressionCalculator expressionCalculator) {
        this.expressionCalculator = expressionCalculator;
    }

    public Component parse(String text) {
        ChainBuilder chainBuilder = new ChainBuilder();
        Parser parser = chainBuilder.build();
        return parser.parse(text);
    }

    public Component calculate(Composite text, Map<String, Double> parameters) throws InformationHandlingException {
        return calculateExpressionsInComponent(text, parameters);
    }


    private Component calculateExpressionsInComponent(Component component, Map<String, Double> parameters) throws InformationHandlingException {
        Component calculateExpression = null;

        if (component.getClass() == Composite.class) {
            Composite composite = (Composite) component;
            List<Component> calculatedComponents = new ArrayList<>();
            for (Component compositeComponent: composite.getComponents()) {
                Component calculatedComponent = calculateExpressionsInComponent(compositeComponent, parameters);
            }

            calculateExpression = new Composite(calculatedComponents);

        } else if (component.getClass() == Lexeme.class) {
            Lexeme lexeme = (Lexeme) component;

            String calculatedLexemeValue;
            if (lexeme.getType() == LexemeType.EXPRESSION) {
                double calculatedExpression = expressionCalculator.calculate(lexeme.getValue(), parameters);
                calculatedLexemeValue = String.valueOf(calculatedExpression);
            } else {
                calculatedLexemeValue = lexeme.getValue();
            }

            calculateExpression = Lexeme.word(calculatedLexemeValue);
        }

        return calculateExpression;
    }
}
