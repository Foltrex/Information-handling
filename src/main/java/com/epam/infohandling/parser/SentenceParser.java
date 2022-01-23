package com.epam.infohandling.parser;

import com.epam.infohandling.Component;

public class SentenceParser extends AbstractParser {

    public SentenceParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String text) {
        return null;
    }
}
