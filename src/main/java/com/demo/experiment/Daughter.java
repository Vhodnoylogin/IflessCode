package com.demo.experiment;

import com.demo.experiment.router.TestRouter;
import com.demo.ifless.annotations.Marker;
import com.demo.ifless.router.Router;

@Marker(router = TestRouter.class)
public class Daughter implements IParent {
    @Override
    public String get() {
        return this.getClass().getName();
    }
}
