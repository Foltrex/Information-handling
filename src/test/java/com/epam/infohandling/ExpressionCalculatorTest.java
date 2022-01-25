package com.epam.infohandling;

import com.epam.infohandling.exception.InformationHandlingException;
import com.epam.infohandling.logic.ExpressionCalculator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ExpressionCalculatorTest {

    private final ExpressionCalculator expressionCalculator = new ExpressionCalculator();
    private final Map<String, Double> parameters = new HashMap<>();

    private static final double ACCURACY = 1e-6;


    @Before
    public void initializeParameters() {
        parameters.put("x", 10.0);
    }

    @Test(expected = InformationHandlingException.class)
    public void testCalculateShouldThrowExceptionWhenExpressionHasUnknownVariable() throws InformationHandlingException {
        //given
        String expression = "[x y +]";

        //when
        double actual = expressionCalculator.calculate(expression, parameters);
    }

    @Test
    public void testCalculateShouldCalculateSumWhenExpressionIsCorrect() throws InformationHandlingException {
        //given
        String expression = "[x 40 +]";

        //when
        double actual = expressionCalculator.calculate(expression, parameters);

        //then
        Assert.assertEquals(50.0, actual, ACCURACY);
    }

    @Test
    public void testCalculateShouldCalculateSubtractionWhenExpressionIsCorrect() throws InformationHandlingException {
        //given
        String expression = "[x 40 -]";

        //when
        double actual = expressionCalculator.calculate(expression, parameters);

        //then
        Assert.assertEquals(-30.0, actual, ACCURACY);
    }

    @Test
    public void testCalculateShouldCalculateMultiplyingWhenExpressionIsCorrect() throws InformationHandlingException {
        //given
        String expression = "[x 40 *]";

        //when
        double actual = expressionCalculator.calculate(expression, parameters);

        //then
        Assert.assertEquals(400.0, actual, ACCURACY);
    }

    @Test
    public void testCalculateShouldCalculateDividingWhenExpressionIsCorrect() throws InformationHandlingException {
        //given
        String expression = "[x 40 /]";

        //when
        double actual = expressionCalculator.calculate(expression, parameters);

        //then
        Assert.assertEquals(0.25, actual, ACCURACY);
    }
}
