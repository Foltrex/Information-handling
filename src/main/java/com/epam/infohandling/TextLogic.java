package com.epam.infohandling;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Composite;
import com.epam.infohandling.parser.ChainBuilder;
import com.epam.infohandling.parser.Parser;

import java.util.Map;

public class TextLogic {

    public Component parse(String text) {
        ChainBuilder chainBuilder = new ChainBuilder();
        Parser parser = chainBuilder.build();
        return parser.parse(text);
    }

    public Component calculate(Composite text, Map<String, Double> parameters) {
        throw new UnsupportedOperationException();
    }

    public Component reverse(Composite text) {
        throw new UnsupportedOperationException();
    }

    public String restore(Composite text) {
        throw new UnsupportedOperationException();
    }
}
