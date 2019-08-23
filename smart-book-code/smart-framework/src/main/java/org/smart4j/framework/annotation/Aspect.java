package org.smart4j.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by creasypita on 8/23/2019.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
