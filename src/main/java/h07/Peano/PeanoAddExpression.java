package h07.Peano;

import org.tudalgo.algoutils.student.annotation.StudentCreationRequired;

@StudentCreationRequired
public class PeanoAddExpression implements PeanoArithmeticExpression {
    @Override
    public PeanoNumberExpression evaluate(PeanoNumberExpression num1, PeanoNumberExpression num2) {
        NaturalNumber naturalNumber1 = num1.evaluate();

        if (naturalNumber1 instanceof Successor naturalNumber1Peano) {
            return () -> new Successor(
                new PeanoAddExpression().evaluate(
                    () -> naturalNumber1Peano.predecessor,
                    num2
                ).evaluate()
            );
        } else {
            return num2;
        }
    }
}
