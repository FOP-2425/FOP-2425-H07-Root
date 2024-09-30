package h07;

import org.tudalgo.algoutils.tutor.general.assertions.Context;
import org.tudalgo.algoutils.tutor.general.reflections.BasicPackageLink;
import org.tudalgo.algoutils.tutor.general.reflections.BasicTypeLink;
import org.tudalgo.algoutils.tutor.general.reflections.Link;
import org.tudalgo.algoutils.tutor.general.reflections.Modifier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertEquals;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertNotNull;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.assertTrue;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.contextBuilder;
import static org.tudalgo.algoutils.tutor.general.assertions.Assertions2.emptyContext;


public class ClassReference {

    public static final List<String> PREDEFINED_PACKAGES = List.of("h07", "h07.Peano", "h07.peano");

    public static final ClassReference NUMBER_EXPRESSION = new ClassReference("h07", "NumberExpression", Link.Kind.INTERFACE);
    public static final ClassReference PEANO_NUMBER_EXPRESSION =
        new ClassReference("h07.peano", "PeanoNumberExpression", Link.Kind.INTERFACE);
    public static final ClassReference ARITHMETIC_EXPRESSION =
        new ClassReference("h07", "ArithmeticExpression", Link.Kind.INTERFACE);
    public static final ClassReference PEANO_ARITHMETIC_EXPRESSION =
        new ClassReference("h07.peano", "PeanoArithmeticExpression", Link.Kind.INTERFACE);
    public static final ClassReference PEANO_ADD_EXPRESSION = new ClassReference("h07.peano",
        "PeanoAddExpression",
        Link.Kind.CLASS,
        new BasicTypeLink[] {PEANO_ARITHMETIC_EXPRESSION.getLink()},
        Modifier.PUBLIC,
        Modifier.NON_FINAL
    );
    public static final ClassReference PEANO_MULTIPLY_EXPRESSION = new ClassReference("h07.peano",
        "PeanoMultiplyExpression",
        Link.Kind.CLASS,
        new BasicTypeLink[] {PEANO_ARITHMETIC_EXPRESSION.getLink()},
        Modifier.PUBLIC,
        Modifier.NON_FINAL
    );
    public static final ClassReference CONVERT_NUMBER_TO_PEANO_EXPRESSION =
        new ClassReference("h07", "ConvertNumberToPeanoExpression", Link.Kind.INTERFACE);
    public static final ClassReference CONVERT_PEANO_TO_NUMBER_EXPRESSION =
        new ClassReference("h07", "ConvertPeanoToNumberExpression", Link.Kind.INTERFACE);
    public static final ClassReference CONVERT_NUMBER_TO_PEANO_EXPRESSION_IMPL = new ClassReference("h07",
        "ConvertNumberToPeanoExpressionImpl",
        Link.Kind.CLASS,
        new BasicTypeLink[] {CONVERT_NUMBER_TO_PEANO_EXPRESSION.getLink()},
        Modifier.PUBLIC,
        Modifier.NON_FINAL
    );
    public static final ClassReference CONVERT_PEANO_TO_NUMBER_EXPRESSION_IMPL = new ClassReference("h07",
        "ConvertPeanoToNumberExpressionImpl",
        Link.Kind.CLASS,
        new BasicTypeLink[] {CONVERT_PEANO_TO_NUMBER_EXPRESSION.getLink()},
        Modifier.PUBLIC,
        Modifier.NON_FINAL
    );

    private final String pack;
    private final String name;
    private final Link.Kind kind;
    private final BasicTypeLink[] superClasses;
    private final Modifier[] modifiers;
    private BasicTypeLink link;

    public ClassReference(String pack, String name, Link.Kind kind, BasicTypeLink... superClasses) {
        this(pack, name, kind, superClasses, new Modifier[0]);
    }

    public ClassReference(String pack, String name, Link.Kind kind, BasicTypeLink[] superClasses,
                          Modifier... modifiers) {
        this.pack = pack;
        this.name = name;
        this.kind = kind;
        this.superClasses = superClasses;
        this.modifiers = modifiers;

        try {

            for (String packageName : PREDEFINED_PACKAGES) {
                link = (BasicTypeLink) BasicPackageLink.of(packageName).getType(Tests.stringMatcher(name));
                if (link != null && !link.reflection().getName().endsWith("Test")) {
                    return;
                }
            }
            link = null;

        } catch (Exception ignored) {
        }
    }

    public String getName() {
        return name;
    }

    public boolean isDefined() {
        return link != null;
    }

    public void assertDefined() {
        assertTrue(
            isDefined(),
            emptyContext(),
            r -> String.format("Could not find Type %s. Type is not defined or could not be found.", name)
        );
    }

    public void assertCorrectlyDefined() {
        assertDefined();
        Context context = contextBuilder()
            .add("expected package", pack)
            .add("expected kind", kind)
            .add("expected name", name)
            .add("expected modifier", Arrays.stream(modifiers).map(Modifier::keyword).collect(Collectors.joining(", ")))
            .add("package", link.reflection().getPackage().getName())
            .add("kind", link.kind())
            .add("name", link.name())
            .build();

        assertNotNull(link, context, r -> "Could not find class %s.".formatted(name));
        assertEquals(kind, link.kind(), context, r -> "Kind does not match expected kind.");
        assertTrue(
            Arrays.stream(modifiers).allMatch(m -> m.is(link.modifiers())),
            context,
            r -> "The modifiers of the type do not match the expected modifiers."
        );
    }

    public void assertNamedCorrectly() {
        if (!isDefined()) {
            return;
        }
        Context context = contextBuilder()
            .add("expected name", name)
            .add("name", link.name())
            .build();

        assertNotNull(link, context, r -> "Could not find class %s.".formatted(name));
        assertEquals(name, link.name(), context, r -> "The name of the Type does not match the expected name.");
    }

    public void assertDefinedInCorrectPackage() {
        assertDefined();
        Context context = contextBuilder()
            .add("expected package", pack)
            .add("expected kind", kind)
            .add("expected name", name)
            .add("expected modifier", Arrays.stream(modifiers).map(Modifier::keyword).collect(Collectors.joining(", ")))
            .add("package", link.reflection().getPackage().getName())
            .add("kind", link.kind())
            .add("name", link.name())
            .build();

        assertNotNull(link, context, r -> "Could not find class %s.".formatted(name));
        assertEquals(
            pack,
            link.reflection().getPackage().getName(),
            context,
            r -> "Package name does not match expected package name."
        );
    }

    public BasicTypeLink getLink() {
        assertDefined();
        return link;
    }
}
