package org.example;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(value = SOURCE)
public @interface Scannable {
    String name() default "";
}
