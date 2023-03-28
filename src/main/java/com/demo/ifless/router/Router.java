package com.demo.ifless.router;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Router<T> {
    private final Map<Class<T>, T> map = new HashMap<>();

    public void init() {
        try {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources("");
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                File file = new File(resource.toURI());
                var res = file.getCanonicalPath() + " | " + file.getName();

                System.out.println(res);

                System.out.println(resources.hasMoreElements());
//                var list = file.isDirectory() ? List.of(file.listFiles()) : List.of(file);
//                for (File f : list) {
//                    if (file.getName().endsWith(".class")) {
//
//                    }
//                }
            }
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
