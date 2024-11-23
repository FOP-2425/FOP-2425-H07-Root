package h07;

import h07.peano.PeanoNumberExpression;
import org.tudalgo.algoutils.student.annotation.StudentCreationRequired;

/**
 * Converts a number expression to a Peano number expression.
 */
@StudentCreationRequired
public interface ConvertNumberToPeanoExpression {
    /**
     * Converts a number expression to a Peano number expression.
     *
     * @param numberExpression the number expression to convert
     * @return the Peano number expression
     */
    PeanoNumberExpression convert(NumberExpression numberExpression);
}
