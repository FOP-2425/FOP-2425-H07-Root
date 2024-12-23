package h07;

import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricProvider;
import org.tudalgo.algoutils.tutor.general.json.JsonParameterSet;

import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.criterion;

public class H07_RubricProvider implements RubricProvider {

    private static final Criterion H7_1_1 = Criterion.builder()
        .shortDescription("H7.1.1 | NumberExpression")
        .maxPoints(2)
        .addChildCriteria(
            criterion(
                "Das Interface NumberExpression wurde korrekt erstellt.",
                JUnitTestRef.ofMethod(() -> NumberExpressionTest.class.getMethod("testDefinition"))
            ),
            criterion(
                "Das Interface PeanoNumberExpression wurde korrekt erstellt.",
                JUnitTestRef.ofMethod(() -> PeanoNumberExpressionTest.class.getMethod("testDefinition"))
            )
        )
        .build();

    private static final Criterion H7_1_2 = Criterion.builder()
        .shortDescription("H7.1.2 | ArithmeticExpression")
        .maxPoints(2)
        .addChildCriteria(
            criterion(
                "Das Interface ArithmeticExpression wurde korrekt erstellt.",
                JUnitTestRef.ofMethod(() -> ArithmeticExpressionTest.class.getMethod("testDefinition"))
            ),
            criterion(
                "Das Interface PeanoArithmeticExpression wurde korrekt erstellt.",
                JUnitTestRef.ofMethod(() -> PeanoArithmeticExpressionTest.class.getMethod("testDefinition"))
            )
        )
        .build();

    private static final Criterion H7_1 = Criterion.builder()
        .shortDescription("H7.1 | Interfaces definieren")
        .minPoints(0)
        .addChildCriteria(
            H7_1_1,
            H7_1_2,
            criterion(
                "Alle Klassen und Methoden wurden korrekt benannt.",
                -1,
                JUnitTestRef.and(
                    JUnitTestRef.ofMethod(() -> NumberExpressionTest.class.getMethod("testNaming")),
                    JUnitTestRef.ofMethod(() -> PeanoNumberExpressionTest.class.getMethod("testNaming")),
                    JUnitTestRef.ofMethod(() -> ArithmeticExpressionTest.class.getMethod("testNaming")),
                    JUnitTestRef.ofMethod(() -> PeanoArithmeticExpressionTest.class.getMethod("testNaming"))
                )
            ),
            criterion(
                "Alle Klassen wurden im korrekten package definiert.",
                -1,
                JUnitTestRef.and(
                    JUnitTestRef.ofMethod(() -> NumberExpressionTest.class.getMethod("testPackage")),
                    JUnitTestRef.ofMethod(() -> PeanoNumberExpressionTest.class.getMethod("testPackage")),
                    JUnitTestRef.ofMethod(() -> ArithmeticExpressionTest.class.getMethod("testPackage")),
                    JUnitTestRef.ofMethod(() -> PeanoArithmeticExpressionTest.class.getMethod("testPackage"))
                )
            )
        )
        .build();

    private static final Criterion H7_2_1 = Criterion.builder()
        .shortDescription("H7.2.1 | Multiplikationstabelle")
        .maxPoints(3)
        .addChildCriteria(
            criterion(
                "Für ArithmeticExpression wurden nur Lambda-Ausdrücke in Standardform und für NumberExpression nur "
                    + "Lambda-Ausdrücke in Kurzform verwendet.",
                JUnitTestRef.ofMethod(() -> NumberExpressionFactoryTest.class.getMethod("testMultiplicationTableRequirements"))
            ),
            criterion(
                "Die Multiplikationstabelle gibt für lowerBound = upperBound die korrekten Werte aus.",
                JUnitTestRef.ofMethod(() -> NumberExpressionFactoryTest.class.getMethod(
                    "multiplicationTableSimple",
                    JsonParameterSet.class
                ))
            ),
            criterion(
                "Die Multiplikationstabelle gibt für komplexere lowerBound und upperBound Werte die korrekten Werte aus.",
                JUnitTestRef.ofMethod(() -> NumberExpressionFactoryTest.class.getMethod(
                    "multiplicationTableComplex",
                    JsonParameterSet.class
                ))
            )
        )
        .build();

    private static final Criterion H7_2 = Criterion.builder()
        .shortDescription("H7.2 | Multiplikationstabelle")
        .addChildCriteria(
            H7_2_1
        )
        .build();


    private static final Criterion H7_3_1 = Criterion.builder()
        .shortDescription("H7.3.1 | PeanoAddExpression")
        .minPoints(0)
        .maxPoints(5)
        .addChildCriteria(
            criterion(
                "Die Klasse PeanoAddExpression wurde korrekt erstellt.",
                JUnitTestRef.ofMethod(() -> PeanoAddExpressionTest.class.getMethod("testDefinition"))
            ),
            criterion(
                "Die Methode evaluate gibt für X + 0 das korrekte Ergebnis zurück.",
                JUnitTestRef.ofMethod(() -> PeanoAddExpressionTest.class.getMethod("testEvaluate_X0", JsonParameterSet.class))
            ),
            criterion(
                "Die Methode evaluate gibt für 0 + X das korrekte Ergebnis zurück.",
                JUnitTestRef.ofMethod(() -> PeanoAddExpressionTest.class.getMethod("testEvaluate_0X", JsonParameterSet.class))
            ),
            criterion(
                "Die Methode evaluate gibt für X + Y das korrekte Ergebnis zurück.",
                2,
                JUnitTestRef.ofMethod(() -> PeanoAddExpressionTest.class.getMethod("testEvaluate_XY", JsonParameterSet.class))
            ),
            criterion(
                "Verbindliche Anforderung nicht erfüllt",
                -2,
                JUnitTestRef.ofMethod(() -> PeanoAddExpressionTest.class.getMethod("testEvaluateRequirements"))
            )
        )
        .build();

    private static final Criterion H7_3_2 = Criterion.builder()
        .shortDescription("H7.3.2 | PeanoMultiplyExpression")
        .minPoints(0)
        .maxPoints(5)
        .addChildCriteria(
            criterion(
                "Die Klasse PeanoMultiplyExpression wurde korrekt erstellt.",
                JUnitTestRef.ofMethod(() -> PeanoMultiplyExpressionTest.class.getMethod("testDefinition"))
            ),
            criterion(
                "Die Methode evaluate gibt für X * 0 das korrekte Ergebnis zurück.",
                JUnitTestRef.ofMethod(() -> PeanoMultiplyExpressionTest.class.getMethod(
                    "testEvaluate_X0",
                    JsonParameterSet.class
                ))
            ),
            criterion(
                "Die Methode evaluate gibt für 0 * X das korrekte Ergebnis zurück.",
                JUnitTestRef.ofMethod(() -> PeanoMultiplyExpressionTest.class.getMethod(
                    "testEvaluate_0X",
                    JsonParameterSet.class
                ))
            ),
            criterion(
                "Die Methode evaluate gibt für X * Y das korrekte Ergebnis zurück.",
                2,
                JUnitTestRef.ofMethod(() -> PeanoMultiplyExpressionTest.class.getMethod(
                    "testEvaluate_XY",
                    JsonParameterSet.class
                ))
            ),
            criterion(
                "Verbindliche Anforderung nicht erfüllt",
                -2,
                JUnitTestRef.ofMethod(() -> PeanoMultiplyExpressionTest.class.getMethod("testEvaluateRequirements"))
            )
        )
        .build();

    private static final Criterion H7_3 = Criterion.builder()
        .shortDescription("H7.3 | Peano Arithmetik")
        .minPoints(0)
        .addChildCriteria(
            H7_3_1,
            H7_3_2,
            criterion(
                "Alle Klassen und Methoden wurden korrekt benannt.",
                -1,
                JUnitTestRef.and(
                    JUnitTestRef.ofMethod(() -> PeanoAddExpressionTest.class.getMethod("testNaming")),
                    JUnitTestRef.ofMethod(() -> PeanoMultiplyExpressionTest.class.getMethod("testNaming"))
                )
            ),
            criterion(
                "Alle Klassen wurden im korrekten package definiert.",
                -1,
                JUnitTestRef.and(
                    JUnitTestRef.ofMethod(() -> PeanoAddExpressionTest.class.getMethod("testPackage")),
                    JUnitTestRef.ofMethod(() -> PeanoMultiplyExpressionTest.class.getMethod("testPackage"))
                )
            )
        )
        .build();

    private static final Criterion H7_4_1 = Criterion.builder()
        .shortDescription("H7.4.1 | Interfaces für Map erstellen")
        .maxPoints(2)
        .addChildCriteria(
            criterion(
                "Das Interface ConvertNumberToPeanoExpression wurde korrekt erstellt.",
                JUnitTestRef.ofMethod(() -> ConvertNumberToPeanoExpressionTest.class.getMethod("testDefinition"))
            ),
            criterion(
                "Das Interface ConvertPeanoToNumberExpression wurde korrekt erstellt.",
                JUnitTestRef.ofMethod(() -> ConvertPeanoToNumberExpressionTest.class.getMethod("testDefinition"))
            )
        )
        .build();

    private static final Criterion H7_4_2 = Criterion.builder()
        .shortDescription("H7.4.2 | Conversions Implementieren")
        .maxPoints(7)
        .addChildCriteria(
            criterion(
                "Die Klasse ConvertNumberToPeanoExpressionImpl wurde korrekt erstellt.",
                JUnitTestRef.ofMethod(() -> ConvertNumberToPeanoExpressionImplTest.class.getMethod("testDefinition"))
            ),
            criterion(
                "Die Methode convert von ConvertNumberToPeanoExpressionImpl gibt für 0 das korrekte Ergebnis zurück.",
                JUnitTestRef.ofMethod(() -> ConvertNumberToPeanoExpressionImplTest.class.getMethod(
                    "testEvaluate_0",
                    JsonParameterSet.class
                ))
            ),
            criterion(
                "Die Methode convert von ConvertNumberToPeanoExpressionImpl gibt für X das korrekte Ergebnis zurück.",
                JUnitTestRef.ofMethod(() -> ConvertNumberToPeanoExpressionImplTest.class.getMethod(
                    "testEvaluate_X",
                    JsonParameterSet.class
                ))
            ),
            criterion(
                "Die Klasse ConvertPeanoToNumberExpressionImpl wurde korrekt erstellt.",
                JUnitTestRef.ofMethod(() -> ConvertPeanoToNumberExpressionImplTest.class.getMethod("testDefinition"))
            ),
            criterion(
                "Die Methode convert von ConvertPeanoToNumberExpressionImpl gibt für 0 das korrekte Ergebnis zurück.",
                JUnitTestRef.ofMethod(() -> ConvertPeanoToNumberExpressionImplTest.class.getMethod(
                    "testEvaluate_0",
                    JsonParameterSet.class
                ))
            ),
            criterion(
                "Die Methode convert von ConvertPeanoToNumberExpressionImpl gibt für X das korrekte Ergebnis zurück.",
                2,
                JUnitTestRef.ofMethod(() -> ConvertPeanoToNumberExpressionImplTest.class.getMethod(
                    "testEvaluate_X",
                    JsonParameterSet.class
                ))
            )
        )
        .build();

    private static final Criterion H7_4_3 = Criterion.builder()
        .shortDescription("H7.4.3 | Von Zahlen zu Peano-Zahlen")
        .maxPoints(1)
        .addChildCriteria(
            criterion(
                "Die Methode fromNumberExpressions in PeanoNumberExpressionFactory wandelt einen Array von NumberExpressions "
                    + "korrekt um.",
                JUnitTestRef.ofMethod(() -> PeanoNumberExpressionFactoryTest.class.getMethod("testFromNumberExpressions", JsonParameterSet.class))
            )
        )
        .build();

    private static final Criterion H7_4_4 = Criterion.builder()
        .shortDescription("H7.4.4 | Filter")
        .maxPoints(3)
        .addChildCriteria(
            criterion(
                "Filter gibt für ein leeres Array ein leeres Array zurück.",
                JUnitTestRef.ofMethod(() -> NumberExpressionFactoryTest.class.getMethod("filter_empty", JsonParameterSet.class))
            ),
            criterion(
                "Filter gibt für ein Predicate, das immer true ist, das gesamte Array zurück und für ein Predicate, das immer "
                    + "false ist, ein leeres Array.",
                JUnitTestRef.and(
                    JUnitTestRef.ofMethod(() -> NumberExpressionFactoryTest.class.getMethod(
                        "filter_true",
                        JsonParameterSet.class
                    )),
                    JUnitTestRef.ofMethod(() -> NumberExpressionFactoryTest.class.getMethod(
                        "filter_false",
                        JsonParameterSet.class
                    ))
                )
            ),
            criterion(
                "Filter gibt für ein Predicate, welches nur durch vier teilbare Zahlen akzeptiert, nur die durch drei teilbaren"
                    + " Zahlen zurück.",
                JUnitTestRef.ofMethod(() -> NumberExpressionFactoryTest.class.getMethod("filter_complex", JsonParameterSet.class))
            )
        )
        .build();

    private static final Criterion H7_4_5 = Criterion.builder()
        .shortDescription("H7.4.5 | Fold")
        .maxPoints(2)
        .addChildCriteria(
            criterion(
                "Fold gibt für ein leeres Array das Initial zurück.",
                JUnitTestRef.ofMethod(() -> PeanoNumberExpressionFactoryTest.class.getMethod(
                    "fold_empty",
                    JsonParameterSet.class
                ))
            ),
            criterion(
                "Fold gibt für ein Array mit mehreren Elementen das korrekte Ergebnis zurück.",
                JUnitTestRef.ofMethod(() -> PeanoNumberExpressionFactoryTest.class.getMethod(
                    "fold_filled",
                    JsonParameterSet.class
                ))
            )
        )
        .build();

    private static final Criterion H7_4 = Criterion.builder()
        .shortDescription("H7.4 | Funktionen höherer Ordnung")
        .addChildCriteria(
            H7_4_1,
            H7_4_2,
            H7_4_3,
            H7_4_4,
            H7_4_5,
            criterion(
                "Alle Klassen und Methoden wurden korrekt benannt.",
                -1,
                JUnitTestRef.and(
                    JUnitTestRef.ofMethod(() -> ConvertNumberToPeanoExpressionTest.class.getMethod("testNaming")),
                    JUnitTestRef.ofMethod(() -> ConvertPeanoToNumberExpressionTest.class.getMethod("testNaming")),
                    JUnitTestRef.ofMethod(() -> ConvertNumberToPeanoExpressionImplTest.class.getMethod("testNaming")),
                    JUnitTestRef.ofMethod(() -> ConvertPeanoToNumberExpressionImplTest.class.getMethod("testNaming"))
                )
            ),
            criterion(
                "Alle Klassen wurden im korrekten package definiert.",
                -1,
                JUnitTestRef.and(
                    JUnitTestRef.ofMethod(() -> ConvertNumberToPeanoExpressionTest.class.getMethod("testPackage")),
                    JUnitTestRef.ofMethod(() -> ConvertPeanoToNumberExpressionTest.class.getMethod("testPackage")),
                    JUnitTestRef.ofMethod(() -> ConvertNumberToPeanoExpressionImplTest.class.getMethod("testPackage")),
                    JUnitTestRef.ofMethod(() -> ConvertPeanoToNumberExpressionImplTest.class.getMethod("testPackage"))
                )
            )
        )
        .build();


    public static final Rubric RUBRIC = Rubric.builder()
        .title("H07 | Peano Arithmetik")
        .addChildCriteria(
            H7_1,
            H7_2,
            H7_3,
            H7_4
        )
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
