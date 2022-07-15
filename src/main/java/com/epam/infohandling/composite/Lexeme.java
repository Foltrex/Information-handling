package com.epam.infohandling.composite;

import lombok.Value;

@Value
public class Lexeme implements Component {

    String value;
    LexemeType type;

    public Lexeme(String value, LexemeType type) {
        this.value = value;
        this.type = type;
    }

    public static Lexeme word(String value) {
        return new Lexeme(value, LexemeType.WORD);
    }

    public static Lexeme expression(String value) {
        return new Lexeme(value, LexemeType.EXPRESSION);
    }

    @Override
    public int size() {
        return value.length();
    }

}
