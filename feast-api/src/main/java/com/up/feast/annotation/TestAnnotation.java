package com.up.feast.annotation;

import java.lang.annotation.*;

/**
 * Description
 *
 * @author liub
 * @date 2019-01-30 14:14
 */
@Target({ElementType.METHOD}) // 该注解的作用域
@Documented  // javadoc可查看
@Retention(RetentionPolicy.RUNTIME) // 运行期有效，可通过反射增强
public @interface TestAnnotation {

    String address() default "beijing";

    enum age {
        kid,
        middleAge,
        old
    }

    age condition() default age.middleAge;
}
