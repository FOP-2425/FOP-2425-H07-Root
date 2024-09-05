package h07;

import org.sourcegrade.jagr.api.rubric.*;
import static org.tudalgo.algoutils.tutor.general.jagr.RubricUtils.criterion;

public class H07_RubricProvider implements RubricProvider {

    private static final Criterion H7_1_1 = Criterion.builder()
        .shortDescription("H7.1.1 | NumberExpression")
        .maxPoints(2)
        .addChildCriteria(
            criterion(
                "Das Interface NumberExpression wurde korrekt erstellt."
            ),
            criterion(
                "Das Interface PeanoNumberExpression wurde korrekt erstellt."
            )
        )
        .build();

    private static final Criterion H7_1_2 = Criterion.builder()
        .shortDescription("H7.1.2 | ArithmeticExpression")
        .maxPoints(2)
        .addChildCriteria(
            criterion(
                "Das Interface ArithmeticExpression wurde korrekt erstellt."
            ),
            criterion(
                "Das Interface PeanoArithmeticExpression wurde korrekt erstellt."
            )
        )
        .build();

    private static final Criterion H7_1 = Criterion.builder()
        .shortDescription("H7.1 | Interfaces definieren")
        .addChildCriteria(
            H7_1_1,
            H7_1_2
        )
        .build();

    private static final Criterion H7_2_1 = Criterion.builder()
        .shortDescription("H7.2.1 | Multiplikationstabelle")
        .maxPoints(3)
        .addChildCriteria(
            criterion(
                "Für ArithmeticExpression wurden nur Lambda-Ausdrücke in Standardform und für NumberExpression nur Lambda-Ausdrücke in Kurzform verwendet."
            ),
            criterion(
                "Die Multiplikationstabelle gibt für lowerBound = 1 und upperBound = 10 die korrekten Werte aus."
            ),
            criterion(
                "Die Multiplikationstabelle gibt für lowerBound = 11 und upperBound = 20 die korrekten Werte aus."
            ) // oder irgendwelche Werte bei denen lower bound > 1
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
        .maxPoints(5)
        .addChildCriteria(
            criterion(
                "Die Methode evaluate gibt für X + 0 das korrekte Ergebnis zurück."
            ),
            criterion(
                "Die Methode evaluate gibt für 0 + X das korrekte Ergebnis zurück."
            ),
            criterion(
                "Die Methode evaluate gibt für X + Y das korrekte Ergebnis zurück."
            ) // Hierfür 3 Punkte
        )
        .build();

    private static final Criterion H7_3_2 = Criterion.builder()
        .shortDescription("H7.3.2 | PeanoMultiplyExpression")
        .maxPoints(5)
        .addChildCriteria(
            criterion(
                "Die Methode evaluate gibt für X * 0 das korrekte Ergebnis zurück."
            ),
            criterion(
                "Die Methode evaluate gibt für 0 * X das korrekte Ergebnis zurück."
            ),
            criterion(
                "Die Methode evaluate gibt für X * Y das korrekte Ergebnis zurück."
            ) // Hierfür 3 Punkte
        )
        .build();

    private static final Criterion H7_3 = Criterion.builder()
        .shortDescription("H7.3 | Peano Arithmetik")
        .addChildCriteria(
            H7_3_1,
            H7_3_2
        )
        .build();

    private static final Criterion H7_4_1 = Criterion.builder()
        .shortDescription("H7.4.1 | Interfaces für Map erstellen")
        .maxPoints(2)
        .addChildCriteria(
            criterion(
                "Das Interface ConvertNumberToPeanoExpression wurde korrekt erstellt."
                ),
            criterion(
                "Das Interface ConvertPeanoToNumberExpression wurde korrekt erstellt."
            )
        )
        .build();

    private static final Criterion H7_4_2 = Criterion.builder()
        .shortDescription("H7.4.2 | Conversions Implementieren")
        .maxPoints(8)
        .addChildCriteria(
            criterion(
                "Die Klasse ConvertNumberToPeanoExpressionImpl wurde korrekt erstellt."
            ),
            criterion(
                "Die Methode convert von ConvertNumberToPeanoExpressionImpl gibt für 0 das korrekte Ergebnis zurück."
            ),
            criterion(
                "Die Methode convert von ConvertNumberToPeanoExpressionImpl gibt für X das korrekte Ergebnis zurück."
            ), // 1 Punkte
            criterion(
                "Die Klasse ConvertPeanoToNumberExpressionImpl wurde korrekt erstellt."
            ),
            criterion(
                "Die Methode convert von ConvertPeanoToNumberExpressionImpl gibt für 0 das korrekte Ergebnis zurück."
            ),
            criterion(
                "Die Methode convert von ConvertPeanoToNumberExpressionImpl gibt für X das korrekte Ergebnis zurück."
            ), // 2 Punkte
            criterion(
                "Die Methode fromNumberExpressions in PeanoNumberExpressionFactory wandelt einen Array von NumberExpressions korrekt um."
            )
        )
        .build();

    private static final Criterion H7_4_3 = Criterion.builder()
        .shortDescription("H7.4.3 | Filter")
        .maxPoints(3)
        .addChildCriteria(
            criterion(
                "Filter gibt für ein leeres Array ein leeres Array zurück."
            ),
            criterion(
                "Filter gibt für ein Predicate, das immer true ist, das gesamte Array zurück und für ein Predicate, das immer false ist, ein leeres Array."
            ),
            criterion(
                "Filter gibt für ein Predicate, welches nur durch vier teilbare Zahlen akzeptiert, nur die durch drei teilbaren Zahlen zurück."
            )
        )
        .build();

    private static final Criterion H7_4_4 = Criterion.builder()
        .shortDescription("H7.4.4 | Fold")
        .maxPoints(2)
        .addChildCriteria(
            criterion(
                "Fold gibt für ein leeres Array das Initial zurück."
            ),
            criterion(
                "Fold gibt für ein Array mit mehreren Elementen das korrekte Ergebnis zurück."
            )
        )
        .build();

    private static final Criterion H7_4 = Criterion.builder()
        .shortDescription("H7.4 | Funktionen höherer Ordnung")
        .addChildCriteria(
            H7_4_1,
            H7_4_2,
            H7_4_3,
            H7_4_4
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
