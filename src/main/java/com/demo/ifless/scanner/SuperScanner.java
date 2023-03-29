package com.demo.ifless.scanner;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuperScanner {
    public static final SuperScanner ALL_CLASSES = new SuperScanner();

    private final Set<Class<?>> allClasses = new HashSet<>();

    public SuperScanner(String rootPackage) {
        init(rootPackage);
    }

    private SuperScanner() {
        init("");
    }

    private void init(String rootPackage) {
        try {
            ClassPath.from(ClassLoader.getSystemClassLoader())
                    .getAllClasses()
                    .stream()
                    .filter(x -> x.getPackageName().startsWith(rootPackage))
                    .map(ClassPath.ClassInfo::load)
                    .forEach(allClasses::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Class<?>> getAllClasses() {
        return this.allClasses.stream().toList();
    }

    public <T extends Annotation> List<Class<?>> findAnnotations(Class<T> clazz) {
        return this.allClasses.stream()
                .filter(x -> x.getAnnotation(clazz) != null)
                .toList();
    }

}
