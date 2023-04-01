package com.demo;

import com.demo.experiment.classes.Daughter;
import com.demo.experiment.router.TestRouter;
import org.junit.jupiter.api.Test;

public class TestRun {

    @Test
    public void testRouter() {
        var rout = new TestRouter();

        System.out.println(rout.getObject("Sonny").get());
        System.out.println(rout.getObject(Daughter.class.getName()).get());
        System.out.println(rout.getObject("Not exist").get());
    }
}
