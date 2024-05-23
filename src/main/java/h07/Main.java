package h07;

import h07.Peano.PeanoAddExpression;
import h07.Peano.PeanoMultiplyExpression;
import h07.Peano.PeanoNumberExpression;
import h07.Peano.PeanoSubtractionExpression;

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
        PeanoNumberExpression seven = new ConvertNumberToPeanoExpressionImpl().convert(() -> 7);
        PeanoNumberExpression fifteen = new ConvertNumberToPeanoExpressionImpl().convert(() -> 15);

        PeanoNumberExpression sum = new PeanoAddExpression().evaluate(fifteen, seven);
        PeanoNumberExpression product = new PeanoMultiplyExpression().evaluate(fifteen, seven);
        PeanoNumberExpression subtract = new PeanoSubtractionExpression().evaluate(fifteen, seven);

        System.out.println(new ConvertPeanoToNumberExpressionImpl().convert(sum).evaluate());
        System.out.println(new ConvertPeanoToNumberExpressionImpl().convert(product).evaluate());
        System.out.println(new ConvertPeanoToNumberExpressionImpl().convert(subtract).evaluate());
    }
}
