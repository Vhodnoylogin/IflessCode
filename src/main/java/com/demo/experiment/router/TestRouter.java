package com.demo.experiment.router;

import com.demo.experiment.IParent;
import com.demo.ifless.router.Router;

public class TestRouter extends Router<IParent> {
    public TestRouter() {
        super(IParent.class);
    }
}
