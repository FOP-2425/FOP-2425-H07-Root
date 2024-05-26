package h07;

import h07.Peano.*;
import org.tudalgo.algoutils.student.annotation.DoNotTouch;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

/**
 * Main entry point in executing the program.
 */
public class Main {
    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        testHeader("Multiplication Table");
        numberExpressionMultiplicationTableTests();

        testHeader("Modulo Table");
        numberExpressionModuloTableTests();

        testHeader("Complex Expressions");
        numberExpressionComplexTests();

        testHeader("Peano Number Expressions");
        peanoNumberExpressionTests();

        testHeader("Filter, Fold, Map");
        filterFoldMapTests();
    }

    @DoNotTouch
    private static void testHeader(String testName) {
        System.out.println("-----------------------------------");
        System.out.println("Running test: " + testName);
        System.out.println("-----------------------------------");
    }

    @StudentImplementationRequired
    private static void numberExpressionMultiplicationTableTests() {
        NumberExpression[] multiplicationTable = NumberExpressionFactory.multiplicationTable(1, 10);
        for (NumberExpression arithmeticExpression : multiplicationTable) {
            System.out.println(arithmeticExpression.evaluate());
        }
    }

    @StudentImplementationRequired
    private static void numberExpressionModuloTableTests() {
        NumberExpression[] multiplicationTable = NumberExpressionFactory.moduloTable(1, 10);
        for (NumberExpression arithmeticExpression : multiplicationTable) {
            System.out.println(arithmeticExpression.evaluate());
        }
    }

    @StudentImplementationRequired
    private static void numberExpressionComplexTests() {
        NumberExpression[] a = NumberExpressionFactory.multiplicationTable(2, 4);
        NumberExpression[] b = NumberExpressionFactory.multiplicationTable(10, 13);
        NumberExpression[] c = NumberExpressionFactory.multiplicationTable(3, 6);
        NumberExpression[] d = NumberExpressionFactory.moduloTable(8, 11);
        assert a.length == b.length && b.length == c.length && c.length == d.length;

        NumberExpression[] complexExpressions = NumberExpressionFactory.complexExpression(a, b, c, d);
        for (NumberExpression arithmeticExpression : complexExpressions) {
            System.out.println(arithmeticExpression.evaluate());
        }
    }

    @StudentImplementationRequired
    private static void peanoNumberExpressionTests() {
        PeanoNumberExpression three = new ConvertNumberToPeanoExpressionImpl().convert(() -> 3);
        PeanoNumberExpression seven = new ConvertNumberToPeanoExpressionImpl().convert(() -> 7);

        PeanoNumberExpression sum = new PeanoAddExpression().evaluate(three, seven);
        PeanoNumberExpression product = new PeanoMultiplyExpression().evaluate(three, seven);
        PeanoNumberExpression subtract = new PeanoSubtractionExpression().evaluate(seven, three);

        System.out.println(sum.evaluate());
        System.out.println(new ConvertPeanoToNumberExpressionImpl().convert(sum).evaluate());
        System.out.println(product.evaluate());
        System.out.println(new ConvertPeanoToNumberExpressionImpl().convert(product).evaluate());
        System.out.println(subtract.evaluate());
        System.out.println(new ConvertPeanoToNumberExpressionImpl().convert(subtract).evaluate());
    }

    @StudentImplementationRequired
    private static void filterFoldMapTests() {
        NumberExpression[] numbers = NumberExpressionFactory.multiplicationTable(1, 3);
        NumberExpression[] filteredNumbers = NumberExpressionFactory.filter(numbers, n -> n % 2 == 0);
        PeanoNumberExpression[] filteredPeanoNumbers = PeanoNumberExpressionFactory.fromNumberExpressions(filteredNumbers);
        PeanoNumberExpression foldedPeanoNumbers = PeanoNumberExpressionFactory.fold(filteredPeanoNumbers, Zero::new, new PeanoAddExpression());
        NaturalNumber foldedPeanoNumber = foldedPeanoNumbers.evaluate();
        System.out.println(foldedPeanoNumber);
        int foldedNumber = new ConvertPeanoToNumberExpressionImpl().convert(foldedPeanoNumbers).evaluate();
        System.out.println(foldedNumber);
    }
}
