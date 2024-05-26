package h07.Peano;

import org.tudalgo.algoutils.student.annotation.StudentCreationRequired;

/**
 * Represents a Peano number expression.
 */
@StudentCreationRequired
public interface PeanoNumberExpression {
    /**
     * Evaluates the expression represented by this node.
     *
     * @return the result of the evaluation
     */
    NaturalNumber evaluate();
}
