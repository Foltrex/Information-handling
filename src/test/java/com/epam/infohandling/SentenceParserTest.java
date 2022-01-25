package com.epam.infohandling;

import com.epam.infohandling.composite.Composite;
import com.epam.infohandling.composite.Lexeme;
import com.epam.infohandling.parser.SentenceParser;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class SentenceParserTest {

    private static final String SENTENCE = "Let's talk later.";

    @Test
    public void testParseShouldReturnCorrectComponentWhenSentenceIsValid() {
        // given
        SentenceParser sentenceParser = new SentenceParser(null);
        Composite expectedSentence = new Composite(Arrays.asList(Lexeme.word("Let's"),
                Lexeme.word("talk"), Lexeme.word("later.")));

        // when
        Composite actualSentence = (Composite) sentenceParser.parse(SENTENCE);

        // then
        Assert.assertEquals(expectedSentence, actualSentence);
    }
}
