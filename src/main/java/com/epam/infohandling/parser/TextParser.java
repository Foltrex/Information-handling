package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;

public class TextParser extends AbstractParser {

    private static final String PARAGRAPH_SEPARATOR = "\n";

    public TextParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String text) {
        return parseBySeparator(text, PARAGRAPH_SEPARATOR);
    }
}
