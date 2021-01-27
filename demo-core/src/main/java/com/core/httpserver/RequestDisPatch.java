package com.core.httpserver;

import java.lang.annotation.*;

/**
 * 处理http handler注解
 *
 * @author
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestDisPatch {

    String uri();

    String method() default "POST";

    boolean isCheckSign() default true;

    public static interface Method {
        public String POST = "POST";
        public String GET = "GET";

    }
}
