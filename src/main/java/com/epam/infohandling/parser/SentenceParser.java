package com.epam.infohandling.parser;

import com.epam.infohandling.composite.Component;
import com.epam.infohandling.composite.Composite;
import com.epam.infohandling.composite.Lexeme;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractParser {

    private static final String LEXEME_REGEX = "[^ \\[\\]]+|\\[.+?\\]";
    private static final String EXPRESSION_REGEX = "\\[.+?\\]";

    public SentenceParser(Parser successor) {
        super(successor);
    }

    @Override
    public Component parse(String text) {
        Composite composite = new Composite();
        Pattern pattern = Pattern.compile(LEXEME_REGEX);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String lexemeString = matcher.group();
            Lexeme lexeme;
            if (lexemeString.matches(EXPRESSION_REGEX)) {
                lexeme = Lexeme.expression(lexemeString);
            } else {
                lexeme = Lexeme.word(lexemeString);
            }

            composite.add(lexeme);
        }

        return composite;
    }
}
