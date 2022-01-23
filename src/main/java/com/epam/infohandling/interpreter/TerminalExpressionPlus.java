package com.epam.infohandling.interpreter;

public class TerminalExpressionPlus implements AbstractExpression {
    @Override
    public void interpret(Context context) {
        context.pushValue(context.popValue() + context.popValue());
    }
}
