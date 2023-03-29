package com.demo.experiment;

import com.demo.ifless.annotations.Marker;

@Marker
public class Daughter implements IParent {
    @Override
    public String get() {
        return this.getClass().getName();
    }
}
