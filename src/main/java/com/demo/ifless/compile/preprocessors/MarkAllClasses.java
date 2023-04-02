package com.demo.ifless.compile.preprocessors;

import javafx.util.Pair;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class MarkAllClasses {
    private final Map<Class<? extends Annotation>, Set<Class<?>>> annoClasses = new HashMap<>();

    private void init(RoundEnvironment roundEnv) {
        Function<TypeElement, Class<?>> castToClass = x -> {
            try {
                return Class.forName(x.getQualifiedName().toString());
            } catch (ClassNotFoundException e) {
                return null;
            }
        };


        /* функция преобразует тип в пары (аннотация, класс)
            сначала получаем список всех аннотаций, которые есть на классе
            затем преобразуем их в настоящие классы аннотаций
            потом перобразуем непонятный ТипЭлемента в настоящий класс объекта
            затем все собираем в пары
         */
        BiConsumer<TypeElement, Consumer<Pair<Class<? extends Annotation>, Class<?>>>> annotationToPairs =
                (x, cons) ->
                        x.getAnnotationMirrors()
                                .stream()
                                .map(AnnotationMirror::getAnnotationType)
                                .map(DeclaredType::asElement)
                                .map(anno -> (TypeElement) anno)
                                .map(anno -> anno.getAnnotation(Annotation.class))
                                .map(Annotation::getClass)
                                .map(anno -> new Pair<Class<? extends Annotation>, Class<?>>(
                                        anno,
                                        castToClass.apply(x))
                                )
                                .filter(pair -> pair.getValue() != null)
                                .forEach(cons);


        roundEnv.getRootElements()
                .stream()
                .filter(x -> x.getKind().isClass())
                .map(x -> (TypeElement) x)
                .filter(x -> !x.getAnnotationMirrors().isEmpty())
                .mapMulti(annotationToPairs)
                .peek(x -> this.annoClasses.put(x.getKey(), new HashSet<>()))
                .forEach(x -> this.annoClasses.get(x.getKey()).add(x.getValue()));

    }
}
