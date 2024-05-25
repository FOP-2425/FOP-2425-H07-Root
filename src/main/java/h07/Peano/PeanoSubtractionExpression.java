package h07.Peano;

import org.tudalgo.algoutils.student.annotation.StudentCreationRequired;

@StudentCreationRequired
public class PeanoSubtractionExpression implements PeanoArithmeticExpression {
    @Override
    public PeanoNumberExpression evaluate(PeanoNumberExpression num1, PeanoNumberExpression num2) {
        NaturalNumber naturalNumber1 = num1.evaluate();
        NaturalNumber naturalNumber2 = num2.evaluate();

        if (naturalNumber1 instanceof Successor naturalNumber1Peano && naturalNumber2 instanceof Successor naturalNumber2Peano) {
            return () -> new PeanoSubtractionExpression().evaluate(
                () -> naturalNumber1Peano.predecessor,
                () -> naturalNumber2Peano.predecessor
            ).evaluate();
        } else if (naturalNumber1 instanceof Successor) {
            return num1;
        } else {
            return Zero::new;
        }
    }
}
