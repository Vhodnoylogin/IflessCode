package com.demo;

import com.demo.experiment.Daughter;
import com.demo.experiment.Son;
import com.demo.experiment.router.TestRouter;

public class Main {
    public static void main(String[] args) {

        var rout = new TestRouter();

//        System.out.println(rout.getObject(Son.class.getName()).get());
//        System.out.println(rout.getObject(Daughter.class.getName()).get());
//        System.out.println(rout.getObject("Not exist").get());
    }
}