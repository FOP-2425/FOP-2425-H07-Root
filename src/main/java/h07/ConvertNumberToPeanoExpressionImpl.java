package h07;

import h07.Peano.PeanoNumberExpression;
import h07.Peano.Zero;
import h07.Peano.Successor;
import org.tudalgo.algoutils.student.annotation.StudentCreationRequired;

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
