package com.demo.experiment.router;

import com.demo.experiment.classes.IParent;
import com.demo.ifless.runtime.router.Router;

public class TestRouter2 extends Router<IParent> {
    public TestRouter2() {
        super(IParent.class);
    }
}
