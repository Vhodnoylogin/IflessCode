package com.demo.ifless.compile.annotations;

import com.demo.ifless.constants.Constants;
import com.demo.ifless.runtime.router.Router;

import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
@Repeatable(value = IflessMarkers.class)
public @interface IflessMarker {
    String uniqueCheckName() default Constants.NON_SPECIFIC_NAME;

    Class<? extends Router<?>> router();

    boolean isDefault() default false;
}
