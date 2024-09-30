package h07;

import h07.peano.PeanoNumberExpressionFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSetTest;
import org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static h07.ClassReference.NUMBER_EXPRESSION;
import static h07.ClassReference.PEANO_ARITHMETIC_EXPRESSION;
import static h07.ClassReference.PEANO_NUMBER_EXPRESSION;
import static h07.MethodReference.NUMBER_EXPRESSION_EVALUATE;
import static h07.MethodReference.PEANO_ARITHMETIC_EXPRESSION_EVALUATE;
import static h07.MethodReference.PEANO_NUMBER_EXPRESSION_EVALUATE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertNotNull;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;

@TestForSubmission
public class PeanoNumberExpressionFactoryTest {

    @ParameterizedTest
    @JsonParameterSetTest(value = "PeanoNumberExpressionFactory_empty.json")
    public void fold_empty(JsonParameterSet params) throws Throwable {
        performTest(params);
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "PeanoNumberExpressionFactory_filled.json")
    public void fold_filled(JsonParameterSet params) throws Throwable {
        performTest(params);
    }

    private static void performTest(JsonParameterSet params) throws Throwable {
        List<Integer> inputs = new ArrayList<>();
        params.getRootNode().get("inputs").forEach(value -> inputs.add(value.intValue()));
        int initial = params.getInt("initial");
        String operation = params.getString("operation");

        int expected = params.getInt("expected");

        List<Object> peanoInputs = new ArrayList<>();
        inputs.forEach(input -> {
            Object expression = null;
            try {
                expression = NumberConverter.toPeanoNumberExpression(input);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            peanoInputs.add(expression);
        });

        Object initialPeano = NumberConverter.toPeanoNumberExpression(initial);

        Object peanoOperation = mock(PEANO_ARITHMETIC_EXPRESSION.getLink().reflection());
        when(PEANO_ARITHMETIC_EXPRESSION_EVALUATE.invoke(
            peanoOperation.getClass(),
            peanoOperation,
            any(),
            any()
        )).thenAnswer(mock -> {
            Object peanoVal1 = mock.getArgument(0);
            Object peanoVal2 = mock.getArgument(1);

            int val1 = NumberConverter.toNaturalNumber(PEANO_NUMBER_EXPRESSION_EVALUATE.invoke(peanoVal1.getClass(), peanoVal1));
            int val2 = NumberConverter.toNaturalNumber(PEANO_NUMBER_EXPRESSION_EVALUATE.invoke(peanoVal2.getClass(), peanoVal2));

            int result = switch (operation) {
                case "+" -> val1 + val2;
                case "*" -> val1 * val2;
                case "*2*" -> val1 * 2 * val2;
                default -> 0;
            };

            return NumberConverter.toPeanoNumberExpression(result);
        });

        Object actualPeano = BasicTypeLink.of(PeanoNumberExpressionFactory.class)
            .getMethod(BasicStringMatchers.identical("fold"))
            .invokeStatic(
                peanoInputs.toArray((Object[]) Array.newInstance(PEANO_NUMBER_EXPRESSION.getLink().reflection(), 0)),
                initialPeano,
                peanoOperation
            );
        assertNotNull(actualPeano, params.toContext(), r -> "Fold returned null.");
        int actual =
            NumberConverter.toNaturalNumber(PEANO_NUMBER_EXPRESSION_EVALUATE.invoke(actualPeano.getClass(), actualPeano));

        assertEquals(expected, actual, params.toContext(), r -> "Returned value of fold does not match expected.");
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "PeanoNumberExpressionFactory_fromNumber.json")
    public void testFromNumberExpressions(JsonParameterSet params) throws Throwable {
        List<Integer> inputs = new ArrayList<>();
        params.getRootNode().get("inputs").forEach(value -> inputs.add(value.intValue()));

        List<Integer> expected = new ArrayList<>();
        params.getRootNode().get("expected").forEach(value -> expected.add(value.intValue()));

        List<Object> baseNumbers = new ArrayList<>();
        inputs.forEach(input -> {
            Object expression = mock(NUMBER_EXPRESSION.getLink().reflection());
            try {
                when(NUMBER_EXPRESSION_EVALUATE.invoke(NUMBER_EXPRESSION.getLink().reflection(), expression)).thenReturn(input);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
            baseNumbers.add(expression);
        });

        List<Object> actualExpressions = List.of(BasicTypeLink.of(PeanoNumberExpressionFactory.class)
            .getMethod(BasicStringMatchers.identical("fromNumberExpressions"))
            .invokeStatic(
                (Object) baseNumbers.toArray((Object[]) Array.newInstance(NUMBER_EXPRESSION.getLink().reflection(), 0))
            )
        );
        List<Integer> actual = actualExpressions.stream().map(expr -> {
            try {
                return NumberConverter.toNaturalNumber(PEANO_NUMBER_EXPRESSION_EVALUATE.invoke(expr.getClass(), expr));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }).toList();

        Context context = contextBuilder()
            .add("inputs", inputs)
            .add("expected", expected)
            .build();

        assertEquals(expected, actual, context, r -> "Returned List of expressions does not match expected list.");
    }
}
