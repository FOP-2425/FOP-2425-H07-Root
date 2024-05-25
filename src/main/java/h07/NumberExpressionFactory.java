package h07;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

public class NumberExpressionFactory {
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
}
