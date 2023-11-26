package pl.sky0x.analysis;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

public class TaylorSeriesTest {

    @Test
    public void testTaylorSeriesApproximation() {
        TaylorSeries taylorSeries = new TaylorSeries();

        // Test the taylorSeriesApproximation method with x^2 function
        double result = taylorSeries.taylorSeriesApproximation(x -> x.pow(2), 2.1, 20);

        // Define the acceptable range for the result
        double expectedValue = 2.1 * 2.1;
        double tolerance = 0.2;

        assertTrue(result >= expectedValue - tolerance && result <= expectedValue + tolerance);
    }

    @Test
    public void testFactorial() {
        TaylorSeries taylorSeries = new TaylorSeries();

        // Test the factorial method
        int result = taylorSeries.factorial(5);

        // Expected result for factorial(5)
        int expectedResult = 120;

        assertEquals(expectedResult, result);
    }

    @Test
    public void testDerivative() {
        TaylorSeries taylorSeries = new TaylorSeries();

        // Test the derivative method
        Function<BigDecimal, BigDecimal> originalFunction = x -> x.pow(2);
        Function<BigDecimal, BigDecimal> derivativeFunction = taylorSeries.derivative(originalFunction);

        // Choose a specific point for testing
        BigDecimal testPoint = new BigDecimal(2.0);

        // Calculate derivative at the test point
        BigDecimal result = derivativeFunction.apply(testPoint);

        // Expected result for the derivative of x^2 at x = 2
        BigDecimal expectedResult = new BigDecimal(4.0);

        assertEquals(expectedResult.doubleValue(), result.doubleValue(), 1e-10);
    }
}
