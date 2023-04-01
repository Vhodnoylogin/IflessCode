package com.demo.ifless.runtime.annotations;


import com.demo.ifless.runtime.router.Router;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Repeatable(value = Markers.class)
public @interface Marker {
    String uniqueCheckName() default Router.NON_SPECIFIC_NAME;
    Class<? extends Router<?>> router();
    boolean isDefault() default false;
}

