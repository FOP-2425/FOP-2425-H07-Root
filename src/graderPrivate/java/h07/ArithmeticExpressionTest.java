package h07;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class ArithmeticExpressionTest {

    @Test
    public void testDefinition() {
        ClassReference.ARITHMETIC_EXPRESSION.assertCorrectlyDefined();
        MethodReference.ARITHMETIC_EXPRESSION_EVALUATE.assertCorrectlyDefined();
    }

    @Test
    public void testNaming() {
        ClassReference.ARITHMETIC_EXPRESSION.assertNamedCorrectly();
        MethodReference.ARITHMETIC_EXPRESSION_EVALUATE.assertNamedCorrectly();
    }

    @Test
    public void testPackage() {
        ClassReference.ARITHMETIC_EXPRESSION.assertDefinedInCorrectPackage();
    }
}
