package com.demo.experiment.classes;

import com.demo.experiment.router.TestRouter;
import com.demo.experiment.router.TestRouter2;
import com.demo.ifless.runtime.annotations.Marker;

@Marker(router = TestRouter2.class)
@Marker(router = TestRouter.class)
public class Daughter implements IParent {
    @Override
    public String get() {
        return this.getClass().getName();
    }
}

