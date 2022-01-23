package com.epam.infohandling.parser;

import com.epam.infohandling.Component;

public class ParagraphParser extends AbstractParser {

    public ParagraphParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String text) {
        return null;
    }
}
