package com.github.code.manage_web.aspect;

import java.lang.annotation.*;

/**
 * WEB 请求的切面，主要处理日志打印和异常处理
 *
 * @author yukaiji
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebRequest {

    /**
     * 是否输出结果体日志
     *
     * @return true/false
     */
    boolean isOutputRespBody() default true;

    /**
     * 多个敏感词逗号隔开
     *
     * @return 敏感词
     */
    String sensitiveWords() default "";
}
