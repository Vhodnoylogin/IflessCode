package com.demo.experiment;


import com.demo.ifless.annotations.Marker;
import com.demo.ifless.router.Router;

@Marker(router = Router.class, uniqueCheckName = "test")
public class Son implements IParent {
    @Override
    public String get() {
        return this.getClass().getName();
    }
}