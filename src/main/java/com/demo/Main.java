package com.demo;

import com.demo.experiment.Daughter;
import com.demo.experiment.Son;
import com.demo.experiment.router.TestRouter;

public class Main {
    public static void main(String[] args) {
//        var res = SuperScanner.ALL_CLASSES.findAnnotations(Marker.class);
//        System.out.println(res);
//
//
//        res.stream()
//                .map(x->x.getAnnotation(Marker.class))
////                .filter(x->x.uniqueCheckName()==)
//                .forEach(System.out::println);

//        var list = SuperScanner.ALL_CLASSES.getAllClasses();
//        list.stream()
//                .filter(x-> x.getAnnotations().length > 0)
//                .forEach(System.out::println);

        var rout = new TestRouter();

        System.out.println(rout.getObject(Son.class.getName()).get());
        System.out.println(rout.getObject(Daughter.class.getName()).get());
        System.out.println(rout.getObject("Not exist").get());
    }
}