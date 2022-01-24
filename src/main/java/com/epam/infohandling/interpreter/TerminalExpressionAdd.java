package com.epam.infohandling.interpreter;

public class TerminalExpressionAdd implements AbstractExpression {
    @Override
    public void interpret(Context context) {
        double firstOperand = context.popValue();
        double secondOperand = context.popValue();
        context.pushValue(firstOperand + secondOperand);
    }
}
