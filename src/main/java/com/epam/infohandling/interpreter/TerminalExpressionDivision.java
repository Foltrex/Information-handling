package com.epam.infohandling.interpreter;

import com.epam.infohandling.exception.InformationHandlingException;

public class TerminalExpressionDivision implements AbstractExpression {
    @Override
    public void interpret(Context context) throws InformationHandlingException {
        double secondOperand = context.popValue();
        double firstOperand = context.popValue();

        if (secondOperand == 0) {
            throw new InformationHandlingException("Division by zero");
        }

        context.pushValue(firstOperand / secondOperand);
    }
}
