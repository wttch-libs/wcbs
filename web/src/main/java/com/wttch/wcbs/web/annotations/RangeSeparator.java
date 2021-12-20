package com.wttch.wcbs.web.annotations;

import static com.wttch.wcbs.web.jackson.serializer.DateRangeJsonSerializer.DEFAULT_SEPARATOR;

import java.lang.annotation.*;

/**
 * 范围的分割符号注解, 在json序列化时提供定制的功能.
 *
 * <p>默认使用 "/" 作为分割符号.
 *
 * @author wttch
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface RangeSeparator {

  /**
   * 获取范围的分割符号
   *
   * @return 范围的分割符号，默认为 "/"
   */
  String value() default DEFAULT_SEPARATOR;
}
