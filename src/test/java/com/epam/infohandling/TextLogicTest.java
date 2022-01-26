package com.epam.infohandling;

import com.epam.infohandling.composite.Composite;
import com.epam.infohandling.composite.Lexeme;
import com.epam.infohandling.exception.InformationHandlingException;
import com.epam.infohandling.logic.ExpressionCalculator;
import com.epam.infohandling.logic.TextLogic;
import com.google.common.collect.ImmutableMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TextLogicTest {

    private final TextLogic textLogic = new TextLogic(new ExpressionCalculator());

    private static final Lexeme FIRST_WORD = Lexeme.word("I'm");
    private static final Lexeme SECOND_WORD = Lexeme.word("years");
    private static final Lexeme THIRD_WORD = Lexeme.word("old");

    private static final Lexeme EXPRESSION = Lexeme.expression("[x 8 9 * +]");
    private static final Lexeme CALCULATED_EXPRESSION = Lexeme.word("100.0");

    @Test
    public void testRestoreTextShouldReturnTextWhenCompositeIsGiven() {
        // given
        String expected = "I'm [x 8 9 * +] years old  \n";
        Composite text = createTextFromLexemes();
        
        // when
        String actual = textLogic.restoreText(text);

        // then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testCalculateShouldReturnCompositeWithCalculatedExpressionsWhenCompositeIsGiven() throws InformationHandlingException {
        // given
        Map<String, Double> parameters = ImmutableMap.of("x", 28.0);
        Composite text = createTextFromLexemes();

        Composite expectedCalculatedText = createCalculatedText();

        // when
        Composite actualCalculatedText = (Composite) textLogic.calculate(text, parameters);

        // then
        Assert.assertEquals(expectedCalculatedText, actualCalculatedText);
    }

    @Test
    public void testSortParagraphsBySentencesCountShouldReturnTextWithParagraphsSortedBySentencesCountWhenCompositeIsGiven() {
        // given
        Composite textWithUnsortedParagraphs = createTextWithUnsortedParagraphs();

        Composite expectedTextWithSortedParagraphs = createTextWithSortedBySentencesCountParagraphs();

        // when
        Composite actualTextWithSortedParagraphs = textLogic.sortParagraphsBySentencesCount(textWithUnsortedParagraphs);

        // then
        Assert.assertEquals(expectedTextWithSortedParagraphs, actualTextWithSortedParagraphs);
    }

    @Test
    public void testSortSentencesByWordsLengthShouldReturnTextWithSentencesSortedByWordsLengthWhenCompositeIsGiven() {
        // given
        Composite textWithUnsortedSentences = createTextFromLexemes();

        Composite expectedTextWithSortedSentences = createTextWithSortedByWordsLengthSentences();

        // when
        Composite actualTextWithSortedSentences = textLogic.sortSentencesByWordsLength(textWithUnsortedSentences);

        // then
        Assert.assertEquals(expectedTextWithSortedSentences, actualTextWithSortedSentences);
    }


    private Composite createTextWithSortedByWordsLengthSentences() {
        Composite sortedSentence = new Composite(Arrays.asList(FIRST_WORD, THIRD_WORD, SECOND_WORD, EXPRESSION));
        Composite sortedParagraph = new Composite(Arrays.asList(sortedSentence));
        Composite sortedText = new Composite(Arrays.asList(sortedParagraph));

        return sortedText;
    }

    private Composite createTextWithSortedBySentencesCountParagraphs() {
        Composite sentence = new Composite(Arrays.asList(FIRST_WORD, EXPRESSION, SECOND_WORD, THIRD_WORD));

        Composite firstParagraph = new Composite(Arrays.asList(sentence));
        Composite secondParagraph = new Composite(Arrays.asList(sentence, sentence));
        Composite thirdParagraph = new Composite(Arrays.asList(sentence, sentence, sentence));

        Composite text = new Composite(Arrays.asList(firstParagraph, secondParagraph, thirdParagraph));

        return text;
    }

    private Composite createTextWithUnsortedParagraphs() {
        Composite sentence = new Composite(Arrays.asList(FIRST_WORD, EXPRESSION, SECOND_WORD, THIRD_WORD));

        Composite firstParagraph = new Composite(Arrays.asList(sentence, sentence, sentence));
        Composite secondParagraph = new Composite(Arrays.asList(sentence, sentence));
        Composite thirdParagraph = new Composite(Arrays.asList(sentence));

        Composite text = new Composite(Arrays.asList(firstParagraph, secondParagraph, thirdParagraph));

        return text;
    }

    private Composite createCalculatedText() {
        Composite sentence = new Composite(Arrays.asList(FIRST_WORD, CALCULATED_EXPRESSION, SECOND_WORD, THIRD_WORD));
        Composite paragraph = new Composite(Arrays.asList(sentence));
        Composite text = new Composite(Arrays.asList(paragraph));

        return text;
    }

    private Composite createTextFromLexemes() {
        Composite sentence = new Composite(Arrays.asList(FIRST_WORD, EXPRESSION, SECOND_WORD, THIRD_WORD));
        Composite paragraph = new Composite(Arrays.asList(sentence));
        Composite text = new Composite(Arrays.asList(paragraph));

        return text;
    }

}
