package h07;

import h07.peano.NaturalNumber;
import h07.peano.PeanoMultiplyExpression;
import h07.peano.PeanoNumberExpression;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSetTest;
import org.tudalgo.algoutils.tutor.general.reflections.BasicMethodLink;
import spoon.reflect.code.CtLambda;
import spoon.reflect.declaration.CtMethod;

import java.util.List;

import static h07.ClassReference.PEANO_ADD_EXPRESSION;
import static h07.ClassReference.PEANO_MULTIPLY_EXPRESSION;
import static h07.ClassReference.PEANO_NUMBER_EXPRESSION;
import static h07.H07Test.getCtElements;
import static h07.MethodReference.PEANO_ARITHMETIC_EXPRESSION_EVALUATE;
import static h07.MethodReference.PEANO_NUMBER_EXPRESSION_EVALUATE;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;

@TestForSubmission
public class PeanoMultiplyExpressionTest {

    @Test
    public void testDefinition() {
        ClassReference.PEANO_MULTIPLY_EXPRESSION.assertCorrectlyDefined();
    }

    @Test
    public void testNaming() {
        ClassReference.PEANO_MULTIPLY_EXPRESSION.assertNamedCorrectly();
    }

    @Test
    public void testPackage() {
        ClassReference.PEANO_MULTIPLY_EXPRESSION.assertDefinedInCorrectPackage();
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "PeanoMultiplyExpression_0X.json")
    public void testEvaluate_0X(JsonParameterSet params) throws Throwable {
        performTest(params);
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "PeanoMultiplyExpression_X0.json")
    public void testEvaluate_X0(JsonParameterSet params) throws Throwable {
        performTest(params);
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "PeanoMultiplyExpression_XY.json")
    public void testEvaluate_XY(JsonParameterSet params) throws Throwable {
        performTest(params);
    }

    private static void performTest(JsonParameterSet params) throws Throwable {
        int x = params.getInt("x");
        int y = params.getInt("y");
        int expected = params.getInt("expected");

        Object xPeano = NumberConverter.toPeanoNumberExpression(x);
        Object yPeano = NumberConverter.toPeanoNumberExpression(y);

        Object mock = mock(PEANO_MULTIPLY_EXPRESSION.getLink().reflection(), CALLS_REAL_METHODS);
        Object returned = PEANO_ARITHMETIC_EXPRESSION_EVALUATE.invoke(mock.getClass(), mock, xPeano, yPeano);

        NaturalNumber actualPeano = PEANO_NUMBER_EXPRESSION_EVALUATE.invoke(returned.getClass(), returned);

        int actual = NumberConverter.toNaturalNumber(actualPeano);

        assertEquals(
            expected,
            actual,
            params.toContext("expected"),
            r -> "Value returned by PeanoMultiplyExpression does not match expected."
        );
    }

    @Test
    public void testEvaluateRequirements() {
        CtMethod<?> evaluate =
            ((BasicMethodLink) PEANO_ARITHMETIC_EXPRESSION_EVALUATE.getLink(
                PEANO_MULTIPLY_EXPRESSION.getLink().reflection(),
                PEANO_NUMBER_EXPRESSION.getLink().reflection(),
                PEANO_NUMBER_EXPRESSION.getLink().reflection()
            )).getCtElement();

        getCtElements(List.of(PEANO_MULTIPLY_EXPRESSION.getLink().reflection()), CtLambda.class, evaluate)
            .forEach(ctLambda -> {
                    String body = ctLambda.toStringDebug();
                    Context context = contextBuilder().add("Lambda", body).build();

                    Assertions2.assertNull(
                        ctLambda.getBody(),
                        context,
                        r -> "evaluate() does not use correct lambdas."
                    );
                }
            );
    }
}
