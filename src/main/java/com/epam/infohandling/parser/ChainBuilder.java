package com.epam.infohandling.parser;

import com.epam.infohandling.parser.ParagraphParser;
import com.epam.infohandling.parser.Parser;
import com.epam.infohandling.parser.SentenceParser;
import com.epam.infohandling.parser.TextParser;

public class ChainBuilder {

    public Parser build() {
        return new TextParser(new ParagraphParser(new SentenceParser(null)));
    }
}
