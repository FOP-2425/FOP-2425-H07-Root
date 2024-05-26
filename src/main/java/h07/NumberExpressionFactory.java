package h07;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.function.IntPredicate;

public class NumberExpressionFactory {
    /**
     * @return An array of number expressions representing the result of the multiplication table of the numbers 1 to 10.
     */
    @StudentImplementationRequired
    public static NumberExpression[] littleMultiplicationTable() {
        NumberExpression[] baseNumbers = new NumberExpression[10];
        NumberExpression[] numbers = new NumberExpression[100];

        for (int i = 0; i < 10; i++) {
            // Copy to local variable to make it effectively final, so it can be used in lambda
            int finalI = i;
            baseNumbers[i] = () -> finalI + 1;
        }

        ArithmeticExpression multiplication = (num1, num2) -> () -> num1.evaluate() * num2.evaluate();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                numbers[i * 10 + j] = multiplication.evaluate(baseNumbers[i], baseNumbers[j]);
            }
        }

        return numbers;
    }

    /**
     * @return An array of number expressions representing the result of the modulo table of the numbers 1 to 10.
     */
    @StudentImplementationRequired
    public static NumberExpression[] moduloTable() {
        NumberExpression[] baseNumbers = new NumberExpression[10];
        NumberExpression[] numbers = new NumberExpression[100];

        for (int i = 0; i < 10; i++) {
            // Copy to local variable to make it effectively final, so it can be used in lambda
            int finalI = i;
            baseNumbers[i] = () -> finalI + 1;
        }

        ArithmeticExpression modulo = (num1, num2) -> () -> num1.evaluate() % num2.evaluate();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                numbers[i * 10 + j] = modulo.evaluate(baseNumbers[i], baseNumbers[j]);
            }
        }

        return numbers;
    }

    /**
     * Computes (a * (b - c) + d) % a for each element in the arrays.
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
