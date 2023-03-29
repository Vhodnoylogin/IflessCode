package com.demo.ifless.router;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Router<T> {
    private final Map<Class<T>, T> map = new HashMap<>();

    public void init(String rootPackage) {
        try {

            ClassPath.from(ClassLoader.getSystemClassLoader())
                    .getAllClasses()
                    .stream()
                    .filter(x -> x.getPackageName().startsWith(rootPackage))
                    .map(ClassPath.ClassInfo::load)
                    .forEach(x -> System.out.println(x));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
