package h07;

import h07.peano.NaturalNumber;
import h07.peano.Successor;
import h07.peano.Zero;

import static h07.ClassReference.NUMBER_EXPRESSION;
import static h07.ClassReference.PEANO_NUMBER_EXPRESSION;
import static h07.MethodReference.NUMBER_EXPRESSION_EVALUATE;
import static h07.MethodReference.PEANO_NUMBER_EXPRESSION_EVALUATE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberConverter {

    public static int toNaturalNumber(NaturalNumber peanoNumber) {
        String representation = peanoNumber.toString();
        return representation.length() - representation.replace("S", "").length();
    }

    public static NaturalNumber toPeanoNumber(int number) {
        NaturalNumber peanoNumber = new Zero();
        for (int i = 0; i < number; i++) {
            peanoNumber = new Successor(peanoNumber);
        }
        return peanoNumber;
    }

    public static Object toNumberExpression(int number) throws Throwable {
        Object numberExpression = mock(NUMBER_EXPRESSION.getLink().reflection());
        when(NUMBER_EXPRESSION_EVALUATE.invoke(numberExpression.getClass(), numberExpression)).thenReturn(number);
        return numberExpression;
    }

    public static Object toPeanoNumberExpression(int number) throws Throwable {
        Object numberExpression = mock(PEANO_NUMBER_EXPRESSION.getLink().reflection());
        when(PEANO_NUMBER_EXPRESSION_EVALUATE.invoke(numberExpression.getClass(), numberExpression)).thenReturn(toPeanoNumber(
            number));
        return numberExpression;
    }
}
