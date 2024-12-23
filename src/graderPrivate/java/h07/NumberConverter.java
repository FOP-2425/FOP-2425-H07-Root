package h07;

import java.lang.reflect.InvocationTargetException;

import static h07.ClassReference.NUMBER_EXPRESSION;
import static h07.ClassReference.PEANO_NUMBER_EXPRESSION;
import static h07.MethodReference.NUMBER_EXPRESSION_EVALUATE;
import static h07.MethodReference.PEANO_NUMBER_EXPRESSION_EVALUATE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class NumberConverter {

    public static int toNaturalNumber(Object peanoNumber) {
        String representation = peanoNumber.toString();
        return representation.length() - representation.replace("S", "").length();
    }

    public static Object toPeanoNumber(int number) {
        Object peanoNumber = null;
        try {
            peanoNumber = ClassReference.ZERO.getLink().reflection().getConstructor().newInstance();

            for (int i = 0; i < number; i++) {
                peanoNumber = ClassReference.SUCCESSOR.getLink().reflection().getConstructor(ClassReference.NATURAL_NUMBER.getLink().reflection()).newInstance(peanoNumber);
            }
            return peanoNumber;
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object toNumberExpression(int number) throws Throwable {
        Object numberExpression = mock(NUMBER_EXPRESSION.getLink().reflection());
        when(NUMBER_EXPRESSION_EVALUATE.invoke(numberExpression.getClass(), numberExpression)).thenReturn(number);
        return numberExpression;
    }

    public static Object toPeanoNumberExpression(int number) throws Throwable {
        Object numberExpression = mock(PEANO_NUMBER_EXPRESSION.getLink().reflection());
        when(PEANO_NUMBER_EXPRESSION_EVALUATE.invoke(numberExpression.getClass(), numberExpression)).thenReturn(toPeanoNumber(number));
        return numberExpression;
    }
}
