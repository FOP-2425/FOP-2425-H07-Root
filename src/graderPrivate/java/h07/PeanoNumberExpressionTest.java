package h07;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class PeanoNumberExpressionTest {

    @Test
    public void testDefinition() {
        ClassReference.PEANO_NUMBER_EXPRESSION.assertCorrectlyDefined();
        MethodReference.PEANO_NUMBER_EXPRESSION_EVALUATE.assertCorrectlyDefined();
    }

    @Test
    public void testNaming() {
        ClassReference.PEANO_NUMBER_EXPRESSION.assertNamedCorrectly();
        MethodReference.PEANO_NUMBER_EXPRESSION_EVALUATE.assertNamedCorrectly();
    }

    @Test
    public void testPackage() {
        ClassReference.PEANO_NUMBER_EXPRESSION.assertDefinedInCorrectPackage();
    }
}
