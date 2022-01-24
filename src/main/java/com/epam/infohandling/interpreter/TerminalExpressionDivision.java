package com.epam.infohandling.interpreter;

import com.epam.infohandling.exception.InformationHandlingException;

public class TerminalExpressionDivision implements AbstractExpression {
    @Override
    public void interpret(Context context) throws InformationHandlingException {
        double firstOperand = context.popValue();

        double secondOperand = context.popValue();
        if (secondOperand == 0) {
            throw new InformationHandlingException("Division by zero");
        }
        context.pushValue(context.popValue() / context.popValue());
    }
}
