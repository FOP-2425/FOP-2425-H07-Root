package h07;

import org.junit.jupiter.api.Test;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;

@TestForSubmission
public class NumberExpressionTest extends H07Test {

    @Test
    public void testDefinition() {
        ClassReference.NUMBER_EXPRESSION.assertCorrectlyDefined();
        MethodReference.NUMBER_EXPRESSION_EVALUATE.assertCorrectlyDefined();
    }

    @Test
    public void testNaming() {
        ClassReference.NUMBER_EXPRESSION.assertNamedCorrectly();
        MethodReference.NUMBER_EXPRESSION_EVALUATE.assertNamedCorrectly();
    }

    @Test
    public void testPackage() {
        ClassReference.NUMBER_EXPRESSION.assertDefinedInCorrectPackage();
    }
}
