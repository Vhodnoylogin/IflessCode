package com.demo.ifless.router;

import com.demo.ifless.annotations.Marker;
import com.demo.ifless.scanner.SuperScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Router<S, T extends S> {
    //    private final Map<String, Class<? extends T>> map = new HashMap<>();
    private final Map<String, T> map = new HashMap<>();
    private T defaultVal;

    protected Router() {
        init();
    }

    private void init() {
        try {
            for (Class<?> clazz : SuperScanner.ALL_CLASSES.findAnnotations(Marker.class)) {
                var name = clazz.getAnnotation(Marker.class).uniqueCheckName();
                name = "".equals(name) ? clazz.getName() : name;
                if (clazz.getAnnotation(Marker.class).isDefault()) {
                    this.defaultVal = ((Class<T>) clazz).getDeclaredConstructor().newInstance();
                } else {
                    this.map.put(
                            name,
                            ((Class<T>) clazz).getDeclaredConstructor().newInstance()
                    );
                }
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
//        SuperScanner.ALL_CLASSES
//                .findAnnotations(Marker.class)
//                .forEach(x-> map.put(
//                        keyOrClassName.apply(x),
//                        (Class<? extends T>) x
//                ));
    }

    public T getObject(String key) {
        return this.map.getOrDefault(key, this.defaultVal);
    }
}
