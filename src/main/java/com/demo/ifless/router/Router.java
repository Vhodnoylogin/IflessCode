package com.demo.ifless.router;

import com.demo.ifless.annotations.Marker;
import com.demo.ifless.exeptions.CreateObjectException;
import com.demo.ifless.exeptions.NoDefaultObjectException;
import com.demo.ifless.scanner.SuperScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
        Predicate<Class<?>> findRouterPredicate =
                x -> x.getAnnotation(MARKER).router() == this.clazz;

        Function<Class<?>, String> nameFunc = x -> {
            var name = clazz.getAnnotation(MARKER).uniqueCheckName();
            name = NON_SPECIFIC_NAME.equals(name) ? clazz.getName() : name;
            name = clazz.getAnnotation(MARKER).isDefault() ? NON_SPECIFIC_NAME : name;
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
                .filter(findRouterPredicate)
                .collect(Collectors.toMap(nameFunc, genFunc));

        this.map.putAll(allGenObjects);
    }

    public T getObject(String key) throws NoDefaultObjectException, CreateObjectException {
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
