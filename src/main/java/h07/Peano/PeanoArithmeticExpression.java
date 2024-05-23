package h07.Peano;

public interface PeanoArithmeticExpression {
    /**
     * Evaluates the expression represented by this node.
     *
     * @param num1 the first number to evaluate
     * @param num2 the second number to evaluate
     * @return the result of the evaluation
     */
    PeanoNumberExpression evaluate(PeanoNumberExpression num1, PeanoNumberExpression num2);
}
