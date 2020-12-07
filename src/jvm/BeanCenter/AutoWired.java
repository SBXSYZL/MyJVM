package jvm.BeanCenter;

import java.lang.annotation.*;

/**
 * @author 22454
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoWired {
}
