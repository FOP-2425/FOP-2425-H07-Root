package h07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.tudalgo.algoutils.tutor.general.ResourceUtils;
import org.tudalgo.algoutils.tutor.general.assertions.Assertions2;
import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSetTest;
import org.tudalgo.algoutils.tutor.general.match.BasicStringMatchers;
import org.tudalgo.algoutils.tutor.general.reflections.BasicMethodLink;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;
import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.code.CtBodyHolder;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.code.CtLambda;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;
import spoon.support.compiler.VirtualFile;

import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntPredicate;
import java.util.stream.Stream;

import static h07.ClassReference.*;
import static h07.H07Test.getCtElements;
import static h07.MethodReference.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.sourcegrade.jagr.api.testing.extension.TestCycleResolver.getTestCycle;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;

@TestForSubmission
public class NumberExpressionFactoryTest {

    @ParameterizedTest
    @JsonParameterSetTest(value = "NumberExpressionFactory_multiply_Simple.json")
    public void multiplicationTableSimple(JsonParameterSet params) throws Throwable {
        performTest_multiplication(params);
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "NumberExpressionFactory_multiply_Complex.json")
    public void multiplicationTableComplex(JsonParameterSet params) throws Throwable {
        performTest_multiplication(params);
    }

    private static void performTest_multiplication(JsonParameterSet params) throws Throwable {
        int lowerBound = params.getInt("lowerBound");
        int upperBound = params.getInt("upperBound");

        List<Integer> expected = new ArrayList<>();
        params.getRootNode().get("expected").forEach(value -> expected.add(value.intValue()));

        Context context = params.toContext();

        List<Object> actualExpressions = multiplicationTable(lowerBound, upperBound);
        List<Integer> actual = actualExpressions.stream().map(expr -> {
            try {
                return NUMBER_EXPRESSION_EVALUATE.<Integer>invoke(expr.getClass(), expr);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }).toList();

        assertEquals(expected, actual, context, r -> "Returned multiplication table does not match expected.");
    }

    private static List<Object> multiplicationTable(int lowerBound, int upperBound) throws Throwable {
        int numberOfNumbers = upperBound - lowerBound + 1;
        List<Object> baseNumbers = new ArrayList<>(numberOfNumbers);

        for (int i = lowerBound; i <= upperBound; i++) {

            Object expression = NumberConverter.toNumberExpression(i);
            baseNumbers.add(expression);
        }

        return List.of(BasicMethodLink.of(Arrays.stream(NumberExpressionFactory.class.getMethods())
                .filter(method -> method.getParameterCount() == 1 && method.getName().equals("multiplicationTable"))
                .findFirst()
                .get())
            .invokeStatic((Object) (baseNumbers.toArray((Object[]) Array.newInstance(
                NUMBER_EXPRESSION.getLink().reflection(),
                0
            )))));
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "NumberExpressionFactory_filter_empty.json")
    public void filter_empty(JsonParameterSet params) throws Throwable {
        performTest_filter(params);
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "NumberExpressionFactory_filter_true.json")
    public void filter_true(JsonParameterSet params) throws Throwable {
        performTest_filter(params);
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "NumberExpressionFactory_filter_false.json")
    public void filter_false(JsonParameterSet params) throws Throwable {
        performTest_filter(params);
    }

    @ParameterizedTest
    @JsonParameterSetTest(value = "NumberExpressionFactory_filter_complex.json")
    public void filter_complex(JsonParameterSet params) throws Throwable {
        performTest_filter(params);
    }

    private static void performTest_filter(JsonParameterSet params) throws Throwable {
        List<Integer> inputs = new ArrayList<>();
        params.getRootNode().get("inputs").forEach(value -> inputs.add(value.intValue()));

        Map<Integer, Boolean> filterMappings = new HashMap<>();
        AtomicInteger currentElem = new AtomicInteger();
        params.getRootNode()
            .get("filter")
            .forEach(value -> filterMappings.put(inputs.get(currentElem.getAndIncrement()), value.booleanValue()));

        List<Integer> expected = new ArrayList<>();
        params.getRootNode().get("expected").forEach(value -> expected.add(value.intValue()));

        IntPredicate predicate = filterMappings::get;

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

        List<Object> actualExpressions = List.of(BasicTypeLink.of(NumberExpressionFactory.class)
            .getMethod(BasicStringMatchers.identical("filter"))
            .invokeStatic(
                baseNumbers.toArray((Object[]) Array.newInstance(NUMBER_EXPRESSION.getLink().reflection(), 0)),
                predicate
            )
        );
        List<Integer> actual = actualExpressions.stream().map(expr -> {
            try {
                return NUMBER_EXPRESSION_EVALUATE.<Integer>invoke(expr.getClass(), expr);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }).toList();

        Context context = contextBuilder()
            .add("inputs", inputs)
            .add("filter", filterMappings)
            .add("expected", expected)
            .build();

        assertEquals(expected, actual, context, r -> "Returned List of expressions does not match expected list.");
    }

    @Test
    public void testMultiplicationTableRequirements() throws Throwable {
        CtMethod<?> multiplicationTable = BasicMethodLink.of(Arrays.stream(NumberExpressionFactory.class.getMethods())
                .filter(method -> method.getParameterCount() == 1 && method.getName().equals("multiplicationTable"))
                .findFirst()
                .get())
            .getCtElement();

        List<CtLambda> lambdas = getCtElements(List.of(NumberExpressionFactory.class), CtLambda.class, multiplicationTable).toList();
        boolean hasCorrectLambdas = lambdas.stream()
            .anyMatch(ctLambda -> {
                    if (ctLambda.getBody() == null) {
                        return false;
                    }

//                    if (ctLambda.getType().getActualClass() != ARITHMETIC_EXPRESSION.getLink().reflection()) {
//                        return false;
//                    }

                    List<CtLambda> innerLambdas =
                        getCtElements(List.of(NumberExpressionFactory.class), CtLambda.class, ctLambda).toList();
//                    if (innerLambdas.size() != 1 || innerLambdas.get(0).getType().getActualClass() != NUMBER_EXPRESSION.getLink().reflection()) {
//                        return false;
//                    }
//                    return innerLambdas.get(0).getBody() == null;

                    return innerLambdas.size() == 1 && innerLambdas.get(0).getBody() == null;
                }
            );

        Assertions2.assertTrue(hasCorrectLambdas, contextBuilder().add("Lambdas", lambdas.stream().map(CtLambda::toStringDebug).toList()).build(), r -> "multiplicationTable() does not use correct lambdas.");

        boolean returnsArrayOfLambda =
            multiplicationTable(0, 2).stream().allMatch(it -> it.getClass().getName().contains("$$Lambda"));

        Assertions2.assertTrue(
            returnsArrayOfLambda,
            emptyContext(),
            r -> "multiplicationTable() does not return array of objects created from lambdas."
        );
    }


    private static CtMethod<?> testSpoon(Class<?> toGet){
        final Launcher launcher = new Launcher();
        launcher.getEnvironment().setComplianceLevel(17);
        launcher.getEnvironment().setIgnoreSyntaxErrors(true);
        launcher.getEnvironment().setNoClasspath(true);
        launcher.getEnvironment().setPreserveLineNumbers(true);
        launcher.getEnvironment().setAutoImports(true);

        final String sourceCode = ResourceUtils.getTypeContent(toGet.getName());
        final VirtualFile file = new VirtualFile(sourceCode, toGet.getName());

        @SuppressWarnings("UnstableApiUsage") final TestCycle cycle = getTestCycle();
        if (cycle != null) {
            launcher.getEnvironment().setInputClassLoader(new URLClassLoader(new URL[0], NumberExpressionFactory.class.getClassLoader()));
//            launcher.getEnvironment().getInputClassLoader()
        }
        System.out.println(Arrays.toString(((URLClassLoader) launcher.getEnvironment().getInputClassLoader()).getURLs()));

        launcher.addInputResource(file);
        final CtModel model = launcher.buildModel();

        return (CtMethod<?>) model
            .getAllTypes()
            .stream()
            .filter(type-> type.getActualClass() == toGet)
            .map(CtClass.class::cast)
            .flatMap(clazz ->
                clazz.getAllMethods()
                    .stream()
            )
            .filter(method -> ((CtMethod) method).getParameters().size() == 1 && ((CtMethod) method).getSimpleName().equals("multiplicationTable"))
            .findFirst()
            .orElse(null);
    }
}
