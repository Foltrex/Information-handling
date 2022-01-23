package com.epam.infohandling.parser;

import com.epam.infohandling.Component;
import com.epam.infohandling.Composite;

public class TextParser extends AbstractParser {

    public TextParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String text) {
        Composite composite = new Composite();
        String[] parts = text.split("\n");

        for (String part: parts) {
            Component paragraph = getSuccessor().parse(part);
            composite.add(paragraph);
        }

        return composite;
    }
}
