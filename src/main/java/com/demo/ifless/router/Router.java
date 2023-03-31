package com.demo.ifless.router;

import com.demo.ifless.annotations.Marker;
import com.demo.ifless.exeptions.CreateObjectException;
import com.demo.ifless.exeptions.NoDefaultObjectException;
import com.demo.ifless.scanner.SuperScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class Router<T> {
    private final Map<String, Supplier<T>> map = new HashMap<>();
    private Class<T> clazz;

    protected Router() {
        init();
    }

    private void init() {
        for (Class<?> clazz : SuperScanner.ALL_CLASSES.findAnnotations(Marker.class)) {
            var name = clazz.getAnnotation(Marker.class).uniqueCheckName();
            name = "".equals(name) ? clazz.getName() : name;
            name = clazz.getAnnotation(Marker.class).isDefault() ? "" : name;


            Supplier<T> sup;
            sup = () -> {
                try {
                    return ((Class<T>) clazz).getConstructor().newInstance();
                } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                         NoSuchMethodException e) {
                    throw new CreateObjectException(e);
                }
            };

            this.map.put(name, sup);
        }
    }

    public T getObject(String key) {
        if(this.map.containsKey(key)){
            var res = this.map.get(key).get();
            this.map.put(key, ()-> res);
            return res;
        }
        if(this.map.containsKey("")){
            var res = this.map.get("").get();
            this.map.put(key, ()-> res);
            return res;
        }
        throw new NoDefaultObjectException();
    }
}
