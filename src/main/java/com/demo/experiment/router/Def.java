package com.demo.experiment.router;

import com.demo.experiment.IParent;
import com.demo.ifless.annotations.Marker;

@Marker(isDefault = true)
public class Def implements IParent {
    @Override
    public String get() {
        return "default";
    }
}
