package com.nowcoder.community.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/30 - 10 - 30 - 21:33
 * @Description: com.nowcoder.community.annotation
 * @version: 1.0
 */
// 该自定义注解用来描述方法
@Target(ElementType.METHOD)
// 声明注解有效时长
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {

}
