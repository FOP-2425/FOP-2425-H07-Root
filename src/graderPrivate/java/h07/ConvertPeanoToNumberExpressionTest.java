package h07;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class ConvertPeanoToNumberExpressionTest {

    @Test
    public void testDefinition() {
        ClassReference.CONVERT_PEANO_TO_NUMBER_EXPRESSION.assertCorrectlyDefined();
        MethodReference.CONVERT_PEANO_TO_NUMBER_EXPRESSION_CONVERT.assertCorrectlyDefined();
    }

    @Test
    public void testNaming() {
        ClassReference.CONVERT_PEANO_TO_NUMBER_EXPRESSION.assertNamedCorrectly();
        MethodReference.CONVERT_PEANO_TO_NUMBER_EXPRESSION_CONVERT.assertNamedCorrectly();
    }

    @Test
    public void testPackage() {
        ClassReference.CONVERT_PEANO_TO_NUMBER_EXPRESSION.assertDefinedInCorrectPackage();
    }
}
