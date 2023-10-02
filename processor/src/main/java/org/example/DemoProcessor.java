package org.example;

import java.util.LinkedList;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import static javax.lang.model.SourceVersion.RELEASE_17;
import static javax.tools.Diagnostic.Kind.NOTE;

public class DemoProcessor extends AbstractProcessor {
    private final DemoScanner scanner = new DemoScanner();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (annotations.isEmpty()) {
            return true;
        }
        var processedElements = roundEnv
            .getElementsAnnotatedWith(Scannable.class)
            .stream()
            .peek(element -> {
                var annotation = element.getAnnotation(Scannable.class);
                processingEnv.getMessager().printMessage(NOTE, "Annotation value: " + annotation.name());
            })
            .map(element -> element.accept(scanner, new LinkedList<>()))
            .reduce(new LinkedList<>(), (first, second) -> {
                first.addAll(second);
                return first;
            });

        processingEnv.getMessager().printMessage(NOTE, "Scanned elements: " + processedElements);

        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Scannable.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return RELEASE_17;
    }
}
