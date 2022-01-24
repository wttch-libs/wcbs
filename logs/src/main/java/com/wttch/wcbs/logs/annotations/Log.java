package com.wttch.wcbs.logs.annotations;

import com.wttch.wcbs.logs.LogTemplate;
import java.lang.annotation.*;

/**
 * 日志切面注解，将注解添加在想要处理日志的方法上来开启该框架的日志功能。
 *
 * @see com.wttch.wcbs.logs.component.LogAspect
 * @author wttch
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Log {
  /**
   * 获取日志的key
   *
   * <p>配合 {@link LogTemplate#getKey()}，来获取对应的日志模板。
   *
   * @return 日志的key
   */
  String value();
}
