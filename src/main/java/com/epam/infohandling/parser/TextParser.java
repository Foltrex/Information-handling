package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;

public class TextParser extends AbstractParser {

    private static final String PARAGRAPH_REGEX = "[^\\n]+(?:\\n+|$)";

    public TextParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String text) {
        return super.parseByRegex(text, PARAGRAPH_REGEX);
    }
}
