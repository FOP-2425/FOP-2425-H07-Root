package h07;

import h07.Peano.PeanoNumberExpression;

public interface ConvertPeanoToNumberExpression {
    /**
     * Converts a Peano number expression to a number expression.
     *
     * @param peanoNumberExpression the Peano number expression to convert
     * @return the number expression
     */
    NumberExpression convert(PeanoNumberExpression peanoNumberExpression);
}
