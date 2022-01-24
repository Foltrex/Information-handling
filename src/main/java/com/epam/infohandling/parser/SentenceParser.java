package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;

public class SentenceParser extends AbstractParser {

    private static final String EXPRESSION_REGEX = "\\[()\\]";
    private static final String LEXEME_SEPARATOR = "\\s+";

    public SentenceParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String text) {
        return null;
    }
}
