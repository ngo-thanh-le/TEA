package com.qsoft.eip.common.annotation;

import android.view.View;
import com.qsoft.eip.common.ICommand;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 * User: Le
 * Date: 10/21/13
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Command
{
    Class<? extends ICommand> value();

    Class<?> event() default View.OnClickListener.class;

    String[] parameters() default {};
}
