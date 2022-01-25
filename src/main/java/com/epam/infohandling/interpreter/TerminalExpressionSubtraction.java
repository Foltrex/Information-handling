package com.epam.infohandling.interpreter;

public class TerminalExpressionSubtraction implements AbstractExpression {
    @Override
    public void interpret(Context context) {
        double secondOperand = context.popValue();
        double firstOperand = context.popValue();

        context.pushValue(firstOperand - secondOperand);
    }
}
