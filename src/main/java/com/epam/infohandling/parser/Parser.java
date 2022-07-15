package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;

public interface Parser {

    Component parse(String text);
}
