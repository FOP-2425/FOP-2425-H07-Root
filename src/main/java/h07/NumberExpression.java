package h07;

import org.tudalgo.algoutils.student.annotation.StudentCreationRequired;

/**
 * Represents a number expression.
 */
@StudentCreationRequired
public interface NumberExpression {
    /**
     * Evaluates the expression represented by this node.
     *
     * @return the result of the evaluation
     */
    int evaluate();
}
