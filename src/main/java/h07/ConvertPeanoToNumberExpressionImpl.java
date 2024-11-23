package h07;

import h07.peano.NaturalNumber;
import h07.peano.PeanoNumberExpression;
import h07.peano.Successor;
import org.tudalgo.algoutils.student.annotation.StudentCreationRequired;

/**
 * Converts a Peano number expression to a number expression.
 */
@StudentCreationRequired
public class ConvertPeanoToNumberExpressionImpl implements ConvertPeanoToNumberExpression {
    @Override
    public NumberExpression convert(PeanoNumberExpression peanoNumberExpression) {
        NaturalNumber naturalNumber = peanoNumberExpression.evaluate();

        if (naturalNumber instanceof Successor successor) {
            return () -> new ConvertPeanoToNumberExpressionImpl().convert(() -> successor.predecessor).evaluate() + 1;
        } else {
            return () -> 0;
        }
    }
}
