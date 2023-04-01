package com.demo.ifless.runtime.scanner;

import com.google.common.reflect.ClassPath;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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


    private static Set<Class<?>> getAllClasses(String rootPackage) {
        try {
            Predicate<ClassPath.ClassInfo> classInfoPredicate = x -> {
                try {
                    Class.forName(x.getName());
                    return true;
                } catch (ClassNotFoundException | NoClassDefFoundError e) {
                    return false;
                }
            };

            return ClassPath.from(ClassLoader.getSystemClassLoader())
                    .getAllClasses()
                    .stream()
                    .filter(x -> x.getPackageName().startsWith(rootPackage))
                    .filter(x -> !"module-info".equals(x.getSimpleName()))
                    .filter(classInfoPredicate)
                    .map(ClassPath.ClassInfo::load)
                    .collect(Collectors.toUnmodifiableSet());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void init(String rootPackage) {
        this.allClasses.addAll(getAllClasses(rootPackage));
    }

    public List<Class<?>> getAllClasses() {
        return this.allClasses.stream().toList();
    }

    public <T extends Annotation> List<Class<?>> findAnnotatedClasses(Class<T> clazz) {
        return this.allClasses.stream()
                .filter(x -> x.getAnnotationsByType(clazz).length > 0)
                .toList();
    }

}
