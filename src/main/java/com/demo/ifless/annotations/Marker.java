package com.demo.ifless.annotations;


import com.demo.ifless.router.Router;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Marker {
    String uniqueCheckName() default Router.NON_SPECIFIC_NAME;
    Class<? extends Router<?>> router();
    boolean isDefault() default false;

}
