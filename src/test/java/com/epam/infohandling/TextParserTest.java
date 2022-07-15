package com.epam.infohandling;


import com.epam.infohandling.composite.Composite;
import com.epam.infohandling.composite.Lexeme;
import com.epam.infohandling.parser.ParagraphParser;
import com.epam.infohandling.parser.TextParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class TextParserTest {

    private static final String TEXT = "I'm busy. Let's talk later.\nOk.";

    private static final String FIRST_PARAGRAPH_TEXT = "I'm busy. Let's talk later.\n";
    private static final Composite FIRST_PARAGRAPH = new Composite();

    private static final String SECOND_PARAGRAPH_TEXT = "Ok.";
    private static final Composite SECOND_PARAGRAPH = new Composite();


    @BeforeClass
    public static void initializeFirstParagraph() {
        Composite firstSentence = new Composite(Arrays.asList(Lexeme.word("I'm"), Lexeme.word("busy.")));
        Composite secondSentence = new Composite(Arrays.asList(Lexeme.word("Let's"), Lexeme.word("talk"), Lexeme.word("later.")));

        FIRST_PARAGRAPH.add(firstSentence);
        FIRST_PARAGRAPH.add(secondSentence);
    }

    @BeforeClass
    public static void initializeSecondParagraph() {
        Composite firstSentence = new Composite(Arrays.asList(Lexeme.word("Ok")));

        SECOND_PARAGRAPH.add(firstSentence);
    }


    @Test
    public void testParseShouldReturnCorrectTextWhenDataIsValid() {
        // given
        ParagraphParser paragraphParser = Mockito.mock(ParagraphParser.class);
        TextParser textParser = new TextParser(paragraphParser);

        Mockito.when(paragraphParser.parse(FIRST_PARAGRAPH_TEXT)).thenReturn(FIRST_PARAGRAPH);
        Mockito.when(paragraphParser.parse(SECOND_PARAGRAPH_TEXT)).thenReturn(SECOND_PARAGRAPH);

        Composite expectedText = new Composite(Arrays.asList(FIRST_PARAGRAPH, SECOND_PARAGRAPH));

        // when
        Composite actualText = (Composite) textParser.parse(TEXT);

        // then
        Assert.assertEquals(expectedText, actualText);
        Mockito.verify(paragraphParser, Mockito.times(1)).parse(FIRST_PARAGRAPH_TEXT);
        Mockito.verify(paragraphParser, Mockito.times(1)).parse(SECOND_PARAGRAPH_TEXT);
    }
}
