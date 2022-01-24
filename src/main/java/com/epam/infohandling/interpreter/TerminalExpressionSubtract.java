package com.epam.infohandling.interpreter;

public class TerminalExpressionSubtract implements AbstractExpression {
    @Override
    public void interpret(Context context) {
        context.pushValue(-context.popValue() + context.popValue());
    }
}
