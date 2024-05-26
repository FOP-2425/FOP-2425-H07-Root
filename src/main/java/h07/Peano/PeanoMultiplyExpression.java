package h07.Peano;

import org.tudalgo.algoutils.student.annotation.StudentCreationRequired;

/**
 * Represents a multiplication operation in Peano arithmetic.
*/
@StudentCreationRequired
public class PeanoMultiplyExpression implements PeanoArithmeticExpression {
    @Override
    public PeanoNumberExpression evaluate(PeanoNumberExpression num1, PeanoNumberExpression num2) {
        NaturalNumber naturalNumber1 = num1.evaluate();

        if (naturalNumber1 instanceof Successor naturalNumber1Peano) {
            if (naturalNumber1Peano.predecessor instanceof Zero) {
                return num2;
            } else {
                return () -> new PeanoAddExpression().evaluate(
                    new PeanoMultiplyExpression().evaluate(
                        () -> naturalNumber1Peano.predecessor,
                        num2
                    ),
                    num2
                ).evaluate();
            }
        } else {
            return Zero::new;
        }
    }
}
