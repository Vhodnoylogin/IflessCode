package com.demo.experiment.router;

import com.demo.experiment.classes.IParent;
import com.demo.ifless.runtime.router.Router;

public class TestRouter extends Router<IParent> {
    public TestRouter() {
        super(IParent.class);
    }
}
