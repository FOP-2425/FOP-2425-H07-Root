package h07;

import org.mockito.MockMakers;
import org.mockito.MockSettings;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;
import org.tudalgo.algoutils.tutor.general.reflections.BasicMethodLink;
import spoon.reflect.code.CtBodyHolder;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.withSettings;

public class H07Test {

    public MockSettings getSettings() {

        TestCycle cycle = TestCycleResolver.getTestCycle();

        if (cycle != null) {
            Thread.currentThread()
                .setContextClassLoader((ClassLoader) TestCycleResolver.getTestCycle().getClassLoader());
            return withSettings().mockMaker(MockMakers.PROXY);
        }

        return withSettings();
    }

    public static <T extends CtElement> Stream<T> getCtElements(final List<Class<?>> classesToSearch, final Class<T> toSearch,
                                                                 final CtBodyHolder bodyHolder) {
        return bodyHolder.getElements(new TypeFilter<>(CtElement.class) {
                @Override
                public boolean matches(final CtElement element) {
                    return (toSearch.isInstance(element) || (element instanceof CtInvocation<?>))
                        && classesToSearch.contains(element.getParent(CtMethod.class).getDeclaringType().getActualClass());
                }
            })
            .stream()
            .filter(ctElement -> !ctElement.equals(bodyHolder))
            .flatMap(element -> {
                if (element instanceof final CtInvocation<?> call) {
                    final var calledMethod = call.getExecutable();
                    if (calledMethod.getDeclaringType() == null
                        || !calledMethod.getDeclaringType().getQualifiedName().startsWith("h00.")) {
                        return Stream.of();
                    }
                    final var actualCalledMethod = calledMethod.getActualMethod();
                    final CtMethod<?> calledMethodCt = BasicMethodLink.of(actualCalledMethod).getCtElement();
                    return getCtElements(classesToSearch, toSearch, calledMethodCt);
                }
                return Stream.of((T) element);
            });
    }
}
