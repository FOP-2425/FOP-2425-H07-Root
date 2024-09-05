package h07;

import h07.peano.PeanoNumberExpression;
import h07.peano.Zero;
import h07.peano.Successor;
import org.tudalgo.algoutils.student.annotation.StudentCreationRequired;

/**
 * Converts a number expression to a Peano number expression.
 */
@StudentCreationRequired
public class ConvertNumberToPeanoExpressionImpl implements ConvertNumberToPeanoExpression {
    @Override
    public PeanoNumberExpression convert(NumberExpression numberExpression) {
        int value = numberExpression.evaluate();

        if (value == 0) {
            return Zero::new;
        } else {
            return () -> new Successor(new ConvertNumberToPeanoExpressionImpl().convert(() -> value - 1).evaluate());
        }
    }
}
