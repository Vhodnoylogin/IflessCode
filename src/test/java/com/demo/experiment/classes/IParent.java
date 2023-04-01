package com.demo.experiment.classes;

import com.demo.experiment.router.TestRouter;
import com.demo.ifless.annotations.Marker;

public interface IParent {
    String get();

    @Marker(router = TestRouter.class, isDefault = true)
    class Def implements IParent {
        @Override
        public String get() {
            return this.getClass().getName();
        }
    }
}
