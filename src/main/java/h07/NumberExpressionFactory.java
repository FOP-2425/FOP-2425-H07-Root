package h07;

import java.util.function.IntPredicate;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * A factory class for creating number expressions.
 */
public class NumberExpressionFactory {

    /**
     * Calulates the product of all possible pairs of numbers in the given range.
     *
     * @param lowerBound the lower bound of the multiplication table, inclusive
     * @param upperBound the upper bound of the multiplication table, inclusive
     * @return An array of number expressions representing the result of the
     *         multiplication table of the numbers from lowerBound to upperBound.
     */
    @StudentImplementationRequired
    public static NumberExpression[] multiplicationTable(int lowerBound, int upperBound) {
        int numberOfNumbers = upperBound - lowerBound + 1;
        NumberExpression[] baseNumbers = new NumberExpression[numberOfNumbers];
        NumberExpression[] numbers = new NumberExpression[numberOfNumbers * numberOfNumbers];

        for (int i = lowerBound; i <= upperBound; i++) {
            // Copy to local variable to make it effectively final, so it can be used in
            // lambda
            int finalI = i;
            baseNumbers[i - lowerBound] = () -> finalI;
        }

        ArithmeticExpression multiplication = (num1, num2) -> () -> num1.evaluate() * num2.evaluate();

        for (int i = 0; i < numberOfNumbers; i++) {
            for (int j = 0; j < numberOfNumbers; j++) {
                numbers[i * numberOfNumbers + j] = multiplication.evaluate(baseNumbers[i], baseNumbers[j]);
            }
        }

        return numbers;
    }

    /**
     * Filters the given array of number expressions based on the given predicate.
     * The returned array should contain only the number expressions that satisfy
     * the predicate in the same order as they appear in the input array.
     * This means there should be no null values in the returned array.
     *
     * @param numbers   the array of number expressions to filter
     * @param predicate the predicate to filter the number expressions
     * @return An array of number expressions that satisfy the predicate.
     */
    @StudentImplementationRequired
    public static NumberExpression[] filter(NumberExpression[] numbers, IntPredicate predicate) {
        int count = 0;
        for (NumberExpression number : numbers) {
            if (predicate.test(number.evaluate())) {
                count++;
            }
        }

        NumberExpression[] result = new NumberExpression[count];

        int nextIndex = 0;
        for (NumberExpression number : numbers) {
            if (predicate.test(number.evaluate())) {
                result[nextIndex++] = number;
            }
        }

        return result;
    }
}
