package com.epam.infohandling.logic;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Composite;
import com.epam.infohandling.composite.Lexeme;
import com.epam.infohandling.composite.LexemeType;
import com.epam.infohandling.exception.InformationHandlingException;
import com.epam.infohandling.logic.ExpressionCalculator;
import com.epam.infohandling.parser.ChainBuilder;
import com.epam.infohandling.parser.Parser;

import java.util.ArrayList;
import java.util.Comparator;
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

    public String restoreText(Composite text) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Component paragraph : text.getComponents()) {
            restore(paragraph, stringBuilder);
            stringBuilder.append('\n');
        }

        return String.valueOf(stringBuilder);
    }

    // recursive function
    private void restore(Component component, StringBuilder stringBuilder) {
        if (component.getClass() == Lexeme.class) {
            Lexeme lexeme = (Lexeme) component;
            stringBuilder.append(lexeme);
            return;
        }

        for (Component childComponent : component.getComponents()) {
            restore(childComponent, stringBuilder);
            stringBuilder.append(' ');
        }
    }


    // recursive function
    public Component calculate(Component component, Map<String, Double> parameters) throws InformationHandlingException {
        Component calculateExpression = null;

        if (component.getClass() == Composite.class) {
            Composite composite = (Composite) component;
            List<Component> calculatedComponents = new ArrayList<>();
            for (Component compositeComponent: composite.getComponents()) {
                Component calculatedComponent = calculate(compositeComponent, parameters);
                calculatedComponents.add(calculatedComponent);
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


    public Composite sortParagraphsBySentencesCount(Composite text) {
        List<Component> sortedParagraphs = new ArrayList<>(text.getComponents());
        sortedParagraphs.sort(Comparator.comparingDouble(Component::size));
        return new Composite(sortedParagraphs);
    }


    public Composite sortSentencesByWordsLength(Composite text) {
        Composite textWithSortedSentences = new Composite();
        for (Component paragraph : text.getComponents()) {
            Composite paragraphWithSortedSentences = new Composite();
            for (Component sentence : paragraph.getComponents()) {
                List<Component> sortedWords = new ArrayList<>(sentence.getComponents());
                sortedWords.sort(Comparator.comparingInt(Component::size));
                Composite sortedSentence = new Composite(sortedWords);
                paragraphWithSortedSentences.add(sortedSentence);
            }
            textWithSortedSentences.add(paragraphWithSortedSentences);
        }

        return textWithSortedSentences;
    }
}
