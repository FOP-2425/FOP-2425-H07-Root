package h07;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.function.IntPredicate;

/**
 * A factory class for creating number expressions.
 */
public class NumberExpressionFactory {

    /**
     * Calulates the product of all possible pairs of numbers in the given range.
     *
     * @param lowerBound the lower bound of the multiplication table, inclusive
     * @param upperBound the upper bound of the multiplication table, inclusive
     * @return An array of number expressions representing the result of the multiplication table of the numbers from lowerBound to upperBound.
     */
    public static NumberExpression[] multiplicationTable(int lowerBound, int upperBound) {
        int numberOfNumbers = upperBound - lowerBound + 1;
        NumberExpression[] baseNumbers = new NumberExpression[numberOfNumbers];
        NumberExpression[] numbers = new NumberExpression[numberOfNumbers * numberOfNumbers];

        for (int i = lowerBound; i <= upperBound; i++) {
            // Copy to local variable to make it effectively final, so it can be used in lambda
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
     * Calulates the modulo of all possible pairs of numbers in the given range.
     *
     * @param lowerBound the lower bound of the modulo table, inclusive
     * @param upperBound the upper bound of the modulo table, inclusive
     * @return An array of number expressions representing the result of the modulo table of the numbers from lowerBound to upperBound.
     */
    @StudentImplementationRequired
    public static NumberExpression[] moduloTable(int lowerBound, int upperBound) {
        int numberOfNumbers = upperBound - lowerBound + 1;
        NumberExpression[] baseNumbers = new NumberExpression[numberOfNumbers];
        NumberExpression[] numbers = new NumberExpression[numberOfNumbers * numberOfNumbers];

        for (int i = 0; i < numberOfNumbers; i++) {
            // Copy to local variable to make it effectively final, so it can be used in lambda
            int finalI = i;
            baseNumbers[i] = () -> finalI + lowerBound;
        }

        ArithmeticExpression modulo = (num1, num2) -> () -> num1.evaluate() % num2.evaluate();

        for (int i = 0; i < numberOfNumbers; i++) {
            for (int j = 0; j < numberOfNumbers; j++) {
                numbers[i * numberOfNumbers + j] = modulo.evaluate(baseNumbers[i], baseNumbers[j]);
            }
        }

        return numbers;
    }

    /**
     * Computes (a * (b - c) + d) % a for each element in the arrays.
     *
     * It is guaranteed that the arrays have the same length.
     *
     * @param a an array of number expressions
     * @param b an array of number expressions
     * @param c an array of number expressions
     * @param d an array of number expressions
     * @return An array of number expressions representing the result of the complex expression.
     */
    @StudentImplementationRequired
    public static NumberExpression[] complexExpression(NumberExpression[] a, NumberExpression[] b, NumberExpression[] c, NumberExpression[] d) {
        NumberExpression[] result = new NumberExpression[a.length];

        ArithmeticExpression multiplication = (num1, num2) -> () -> num1.evaluate() * num2.evaluate();
        ArithmeticExpression addition = (num1, num2) -> () -> num1.evaluate() + num2.evaluate();
        ArithmeticExpression subtraction = (num1, num2) -> () -> num1.evaluate() - num2.evaluate();
        ArithmeticExpression modulo = (num1, num2) -> () -> num1.evaluate() % num2.evaluate();

        for (int i = 0; i < a.length; i++) {
            NumberExpression bMinusC = subtraction.evaluate(b[i], c[i]);
            NumberExpression aTimesBMinusC = multiplication.evaluate(a[i], bMinusC);
            NumberExpression aTimesBMinusCPlusD = addition.evaluate(aTimesBMinusC, d[i]);
            result[i] = modulo.evaluate(aTimesBMinusCPlusD, a[i]);
        }

        return result;
    }

    /**
     * Filters the given array of number expressions based on the given predicate.
     * The returned array should contain only the number expressions that satisfy the predicate in the same order as they appear in the input array.
     * This means there should be no null values in the returned array.
     *
     * @param numbers the array of number expressions to filter
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
