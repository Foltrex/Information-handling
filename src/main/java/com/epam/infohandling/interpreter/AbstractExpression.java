package com.epam.infohandling.interpreter;

import com.epam.infohandling.exception.InformationHandlingException;

public interface AbstractExpression {
    void interpret(Context context) throws InformationHandlingException;
}
