package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;

public class ParagraphParser extends AbstractParser {

    private static final String SENTENCE_SEPARATOR = "[\\.\\!\\?\\.{3}]";

    public ParagraphParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String text) {
        return parseBySeparator(text, SENTENCE_SEPARATOR);
    }
}
