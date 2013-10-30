package com.qsoft.eip.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: Le
 * Date: 10/11/13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SaveActivityState
{
    public TransferScope value() default TransferScope.WITHIN_ACTIVITY;
}
