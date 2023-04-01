package com.demo.experiment.classes;


import com.demo.experiment.router.TestRouter;
import com.demo.ifless.annotations.Marker;

@Marker(router = TestRouter.class, uniqueCheckName = "Sonny")
public class Son implements IParent {
    @Override
    public String get() {
        return this.getClass().getName();
    }
}