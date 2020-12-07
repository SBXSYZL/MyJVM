package jvm.BeanCenter;

import java.lang.annotation.*;

/**
 * @author 22454
 * 该注解负责在 bean 中心配置 bean 的时候，先创建出该对象，
 * 以及他内部的 field，然后执行一次标记为 target 的标记方法
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Task {
}
