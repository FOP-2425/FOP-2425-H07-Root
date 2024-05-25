package h07;

import h07.Peano.NaturalNumber;
import h07.Peano.PeanoNumberExpression;
import h07.Peano.Successor;
import org.tudalgo.algoutils.student.annotation.StudentCreationRequired;

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
