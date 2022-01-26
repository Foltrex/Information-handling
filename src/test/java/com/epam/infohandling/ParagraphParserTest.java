package com.epam.infohandling;

import com.epam.infohandling.composite.Composite;
import com.epam.infohandling.composite.Lexeme;
import com.epam.infohandling.parser.ParagraphParser;
import com.epam.infohandling.parser.SentenceParser;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class ParagraphParserTest {

    private static final String PARAGRAPH = "I'm busy. Let's talk later.";

    private static final String FIRST_SENTENCE_TEXT = "I'm busy.";
    private static final Composite FIRST_SENTENCE = new Composite(Arrays.asList(Lexeme.word("-I'm"),
            Lexeme.word("busy.")));

    private static final String SECOND_SENTENCE_TEXT = "Let's talk later.";
    private static final Composite SECOND_SENTENCE = new Composite(Arrays.asList(Lexeme.word("Lets's"),
            Lexeme.word("talk"), Lexeme.word("latter.")));


    @Test
    public void testParseShouldReturnCorrectParagraphWhenDataIsValid() {
        // given
        SentenceParser sentenceParser = Mockito.mock(SentenceParser.class);
        ParagraphParser paragraphParser = new ParagraphParser(sentenceParser);

        Mockito.when(sentenceParser.parse(FIRST_SENTENCE_TEXT)).thenReturn(FIRST_SENTENCE);
        Mockito.when(sentenceParser.parse(SECOND_SENTENCE_TEXT)).thenReturn(SECOND_SENTENCE);

        Composite expectedParagraph = new Composite(Arrays.asList(FIRST_SENTENCE, SECOND_SENTENCE));

        // when
        Composite actualParagraph = (Composite) paragraphParser.parse(PARAGRAPH);

        // then
        Assert.assertEquals(expectedParagraph, actualParagraph);
        Mockito.verify(sentenceParser, Mockito.times(1)).parse(FIRST_SENTENCE_TEXT);
        Mockito.verify(sentenceParser, Mockito.times(1)).parse(SECOND_SENTENCE_TEXT);
    }
}
