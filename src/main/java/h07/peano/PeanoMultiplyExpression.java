package h07.peano;

import org.tudalgo.algoutils.student.annotation.StudentCreationRequired;

/**
 * Represents a multiplication operation in Peano arithmetic.
 */
@StudentCreationRequired
public class PeanoMultiplyExpression implements PeanoArithmeticExpression {
    @Override
    public PeanoNumberExpression evaluate(PeanoNumberExpression num1, PeanoNumberExpression num2) {
        NaturalNumber naturalNumber2 = num2.evaluate();

        if (naturalNumber2 instanceof Successor naturalNumber2Peano) {
            if (naturalNumber2Peano.predecessor instanceof Zero) {
                return num1;
            } else {
                return () -> new PeanoAddExpression().evaluate(
                        num1,
                        new PeanoMultiplyExpression().evaluate(
                                num1,
                                () -> naturalNumber2Peano.predecessor))
                        .evaluate();
            }
        } else {
            return Zero::new;
        }
    }
}
