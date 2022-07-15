package com.epam.infohandling.interpreter;

public class NonTerminalExpression implements AbstractExpression {

    private final double number;

    public NonTerminalExpression(double number) {
        this.number = number;
    }

    @Override
    public void interpret(Context context) {
        context.pushValue(number);
    }
}
