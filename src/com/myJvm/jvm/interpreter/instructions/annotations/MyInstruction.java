package com.myJvm.jvm.interpreter.instructions.annotations;

import java.lang.annotation.*;

/**
 * @author 22454
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyInstruction {
}
