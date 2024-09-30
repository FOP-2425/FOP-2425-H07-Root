package h07;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class PeanoArithmeticExpressionTest {

    @Test
    public void testDefinition() {
        ClassReference.PEANO_ARITHMETIC_EXPRESSION.assertCorrectlyDefined();
        MethodReference.PEANO_ARITHMETIC_EXPRESSION_EVALUATE.assertCorrectlyDefined();
    }

    @Test
    public void testNaming() {
        ClassReference.PEANO_ARITHMETIC_EXPRESSION.assertNamedCorrectly();
        MethodReference.PEANO_ARITHMETIC_EXPRESSION_EVALUATE.assertNamedCorrectly();
    }

    @Test
    public void testPackage() {
        ClassReference.PEANO_ARITHMETIC_EXPRESSION.assertDefinedInCorrectPackage();
    }
}
