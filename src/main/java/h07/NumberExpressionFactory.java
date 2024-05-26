package h07;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

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
}
