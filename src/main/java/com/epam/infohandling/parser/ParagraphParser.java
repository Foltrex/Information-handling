package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;

public class ParagraphParser extends AbstractParser {

    private static final String SENTENCE_REGEX = "(\\p{Upper}|\\p{Digit}).+?[.!?…]";

    public ParagraphParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String text) {
        return parseByRegex(text, SENTENCE_REGEX);
    }
}
