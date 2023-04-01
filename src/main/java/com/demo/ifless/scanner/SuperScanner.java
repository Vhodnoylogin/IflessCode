package com.demo.ifless.scanner;

import com.google.common.reflect.ClassPath;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Slf4j
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
            Predicate<ClassPath.ClassInfo> classInfoPredicate = x -> {
                try {
                    Class.forName(x.getName());
                    return true;
                } catch (ClassNotFoundException e) {
                    return false;
                }
            };

            var pack = "com.demo";

            ClassPath.from(ClassLoader.getSystemClassLoader())
                    .getAllClasses()
                    .stream()
                    .filter(x -> x.getPackageName().startsWith(pack))
                    .filter(x -> !"module-info".equals(x.getSimpleName()))
                    .filter(classInfoPredicate)
                    .map(ClassPath.ClassInfo::load)
                    .forEach(allClasses::add);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Class<?>> getAllClasses() {
        return this.allClasses.stream().toList();
    }

    public <T extends Annotation> List<Class<?>> findAnnotatedClasses(Class<T> clazz) {
        return this.allClasses.stream()
                .filter(x -> x.getAnnotation(clazz) != null)
                .toList();
    }

}
