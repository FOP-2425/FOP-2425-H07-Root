package h07;

import h07.peano.NaturalNumber;
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

import static h07.ClassReference.CONVERT_NUMBER_TO_PEANO_EXPRESSION_IMPL;
import static h07.ClassReference.NUMBER_EXPRESSION;
import static h07.H07Test.getCtElements;
import static h07.MethodReference.CONVERT_NUMBER_TO_PEANO_EXPRESSION_CONVERT;
import static h07.MethodReference.PEANO_NUMBER_EXPRESSION_EVALUATE;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;

@TestForSubmission
public class ConvertNumberToPeanoExpressionImplTest {

    @Test
    public void testDefinition() {
        CONVERT_NUMBER_TO_PEANO_EXPRESSION_IMPL.assertCorrectlyDefined();
    }

    @Test
    public void testNaming() {
        CONVERT_NUMBER_TO_PEANO_EXPRESSION_IMPL.assertNamedCorrectly();
    }

    @Test
    public void testPackage() {
        CONVERT_NUMBER_TO_PEANO_EXPRESSION_IMPL.assertDefinedInCorrectPackage();
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "ConvertNumberToPeanoExpressionImpl_X.json")
    public void testEvaluate_X(JsonParameterSet params) throws Throwable {
        performTest(params);
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "ConvertNumberToPeanoExpressionImpl_0.json")
    public void testEvaluate_0(JsonParameterSet params) throws Throwable {
        performTest(params);
    }

    private static void performTest(JsonParameterSet params) throws Throwable {
        int x = params.getInt("x");

        Object numberExpression = NumberConverter.toNumberExpression(x);

        Object mock = mock(CONVERT_NUMBER_TO_PEANO_EXPRESSION_IMPL.getLink().reflection(), CALLS_REAL_METHODS);
        Object returned = CONVERT_NUMBER_TO_PEANO_EXPRESSION_CONVERT.invoke(mock.getClass(), mock, numberExpression);

        NaturalNumber actualPeano = PEANO_NUMBER_EXPRESSION_EVALUATE.invoke(returned.getClass(), returned);

        int actual = NumberConverter.toNaturalNumber(actualPeano);

        assertEquals(x, actual, params.toContext(), r -> "Value returned by convert() does not match expected.");
    }

    @Test
    public void testConvertRequirements() {
        CtMethod<?> evaluate =
            ((BasicMethodLink) CONVERT_NUMBER_TO_PEANO_EXPRESSION_CONVERT.getLink(
                CONVERT_NUMBER_TO_PEANO_EXPRESSION_IMPL.getLink().reflection(),
                NUMBER_EXPRESSION.getLink().reflection()
            )).getCtElement();

        getCtElements(List.of(CONVERT_NUMBER_TO_PEANO_EXPRESSION_IMPL.getLink().reflection()), CtLambda.class, evaluate)
            .forEach(ctLambda -> {
                    String body = ctLambda.toStringDebug();
                    Context context = contextBuilder().add("Lambda", body).build();

                    Assertions2.assertNull(
                        ctLambda.getBody(),
                        context,
                        r -> "convert() does not use correct lambdas."
                    );
                }
            );
    }
}
