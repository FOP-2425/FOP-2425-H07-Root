package h07;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class ConvertNumberToPeanoExpressionTest {

    @Test
    public void testDefinition() {
        ClassReference.CONVERT_NUMBER_TO_PEANO_EXPRESSION.assertCorrectlyDefined();
        MethodReference.CONVERT_NUMBER_TO_PEANO_EXPRESSION_CONVERT.assertCorrectlyDefined();
    }

    @Test
    public void testNaming() {
        ClassReference.CONVERT_NUMBER_TO_PEANO_EXPRESSION.assertNamedCorrectly();
        MethodReference.CONVERT_NUMBER_TO_PEANO_EXPRESSION_CONVERT.assertNamedCorrectly();
    }

    @Test
    public void testPackage() {
        ClassReference.CONVERT_NUMBER_TO_PEANO_EXPRESSION.assertDefinedInCorrectPackage();
    }
}
