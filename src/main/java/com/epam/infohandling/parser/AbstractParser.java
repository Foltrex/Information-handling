package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Composite;

public abstract class AbstractParser implements Parser {

    private Parser successor;

    public AbstractParser(Parser successor) {
        this.successor = successor;
    }

    protected Parser getSuccessor() {
        return successor;
    }

    protected Component parseBySeparator(String text, String separatorRegex) {
        Composite composite = new Composite();
        String[] parts = text.split(separatorRegex);

        for (String part : parts) {
            Component component = getSuccessor().parse(part);
            composite.add(component);
        }

        return composite;
    }
}
