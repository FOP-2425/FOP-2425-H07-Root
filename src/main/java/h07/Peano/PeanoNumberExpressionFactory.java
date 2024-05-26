package h07.Peano;

import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

import java.util.Arrays;

public class PeanoNumberExpressionFactory {
    /**
     * Folds an array of Peano number expressions into a single Peano number expression.
     *
     * @param peanoNumberExpressions the Peano number expressions to fold
     * @param initial the initial Peano number expression
     * @param operation the operation to apply
     * @return the folded Peano number expression
     */
    @StudentImplementationRequired
    public static PeanoNumberExpression fold(PeanoNumberExpression[] peanoNumberExpressions, PeanoNumberExpression initial, PeanoArithmeticExpression operation) {
        PeanoNumberExpression result = initial;
        for (PeanoNumberExpression peanoNumberExpression : peanoNumberExpressions) {
            result = operation.evaluate(result, peanoNumberExpression);
        }
        return result;
    }
}
