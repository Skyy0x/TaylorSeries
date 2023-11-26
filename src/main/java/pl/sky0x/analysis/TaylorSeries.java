package pl.sky0x.analysis;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * The TaylorSeries class provides methods for approximating functions using Taylor series,
 * calculating factorials, and finding derivatives.
 */
public final class TaylorSeries {

    // A small constant to represent a very small change in the input for derivative calculations.
    private static final BigDecimal EPSILON = new BigDecimal("1e-100");

    /**
     * Approximates the given function using a Taylor series.
     *<p>
     * @param function  The function to be approximated.
     * @param x         The point to find.
     * @param precision The number of terms in the Taylor series.
     * <p>
     * @return The approximation of the function at the specified point x.
     */
    public double taylorSeriesApproximation(Function<BigDecimal, BigDecimal> function, double x, int precision) {
        final BigDecimal a = new BigDecimal((double) Math.round(x));
        final BigDecimal dx = new BigDecimal(x).subtract(a);

        double summation = 0;
        Function<BigDecimal, BigDecimal> lastDerivative = function;

        // Iterate through the terms of the Taylor series.
        for (int i = 0; i < precision; i++) {
            if(i > 1) {
                lastDerivative = derivative(lastDerivative);
            }
            // Update the summation with the current term.
            summation += lastDerivative.apply(a)
                    .multiply(new BigDecimal(Math.pow(dx.doubleValue(), i) / factorial(i)))
                    .doubleValue();
        }
        return summation;
    }

    /**
     * Calculates the factorial of a given number using recursion.
     *
     * @param number The number for which the factorial is calculated.
     * @return The factorial of the given number.
     */
    public int factorial(int number) {
        return number > 1 ? factorial(number - 1) * number : 1;
    }

    /**
     * Calculates the numerical derivative of a given function at a specific point using the definition of derivative.
     *
     * @param function The function for which the derivative is calculated.
     * @return The derivative of function.
     */
    public Function<BigDecimal, BigDecimal> derivative(Function<BigDecimal, BigDecimal> function) {
        return x -> function.apply(x.add(EPSILON)).subtract(function.apply(x)).divide(EPSILON);
    }
}
