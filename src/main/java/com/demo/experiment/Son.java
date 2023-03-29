package com.demo.experiment;


import com.demo.ifless.annotations.Marker;

@Marker
public class Son implements IParent {
    @Override
    public String get() {
        return this.getClass().getName();
    }
}