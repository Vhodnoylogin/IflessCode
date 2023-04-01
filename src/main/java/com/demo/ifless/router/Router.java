package com.demo.ifless.router;

import com.demo.ifless.annotations.Marker;
import com.demo.ifless.exeptions.CreateObjectException;
import com.demo.ifless.exeptions.NoDefaultObjectException;
import com.demo.ifless.scanner.SuperScanner;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
public abstract class Router<T> {
    public static final String NON_SPECIFIC_NAME = "";
    private static final Class<Marker> MARKER = Marker.class;
    private final Map<String, Supplier<T>> map = new HashMap<>();
    private final Class<T> clazz;

    protected Router(Class<T> clazz) {
        this.clazz = clazz;
        init();
    }

    private void init() {

        Predicate<Class<?>> isForCurrentRouterPredicate =
                x -> Arrays.stream(x.getAnnotationsByType(MARKER))
                        .anyMatch(anno -> anno.router() == this.getClass());

        Function<Class<?>, String> nameFunc = x -> {
            var anno = Arrays.stream(x.getAnnotationsByType(MARKER))
                    .filter(a -> a.router() == this.getClass())
                    .findAny();
            if (anno.isEmpty()) {
                // условие бесполезное, так как всегда сначала надо применять isForCurrentRouterPredicate()
                return null;
            }
            var name = anno.get().uniqueCheckName();
            name = NON_SPECIFIC_NAME.equals(name) ? x.getName() : name;
            name = anno.get().isDefault() ? NON_SPECIFIC_NAME : name;
            return name;
        };

        Function<Class<?>, Supplier<T>> genFunc = x ->
                () -> {
                    try {
                        return ((Class<T>) x).getConstructor().newInstance();
                    } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                             NoSuchMethodException e) {
                        throw new CreateObjectException(e);
                    }
                };

        var allGenObjects = SuperScanner.ALL_CLASSES
                .findAnnotatedClasses(MARKER)
                .stream()
                .filter(isForCurrentRouterPredicate)
                .filter(this.clazz::isAssignableFrom)
                .collect(Collectors.toMap(nameFunc, genFunc));
        this.map.putAll(allGenObjects);

//        for (Class<?> annotatedClass : SuperScanner.ALL_CLASSES
//                .findAnnotatedClasses(MARKER)) {
//            log.info("{}", annotatedClass);
//
//            var test1 = Arrays.stream(annotatedClass.getAnnotationsByType(MARKER))
//                    .peek(x -> log.info("test 1 annotation = {}", x))
//                    .peek(x -> log.info("x.router() == this.getClass() - {}", x.router() == this.getClass()))
//                    .anyMatch(anno -> anno.router() == this.getClass());
//            if(test1){
//                var test2 = this.clazz.isAssignableFrom(annotatedClass);
//                log.info("{} parent of {} - {}", this.clazz, annotatedClass, test2);
//                if(test2){
//                    var name = nameFunc.apply(annotatedClass);
//                    var ob = genFunc.apply(annotatedClass);
//                    log.info("name = \"{}\", object = {}", name, ob);
//                    this.map.put(name, ob);
//                }
//            }
//            log.info("__________________");
//        }
    }

    public T getObject(@NonNull String key) throws NoDefaultObjectException, CreateObjectException {
        if (this.map.containsKey(key)) {
            var res = this.map.get(key).get();
            this.map.put(key, () -> res);
            return res;
        }
        if (this.map.containsKey(NON_SPECIFIC_NAME)) {
            var res = this.map.get(NON_SPECIFIC_NAME).get();
            this.map.put(key, () -> res);
            return res;
        }
        throw new NoDefaultObjectException();
    }
}
