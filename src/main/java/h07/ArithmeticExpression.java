package h07;

public interface ArithmeticExpression {
    /**
     * Applies the arithmetic operation to the two numbers.
     *
     * @param num1 the first number
     * @param num2 the second number
     * @return the result of the operation
     */
    NumberExpression evaluate(NumberExpression num1, NumberExpression num2);
}
