package com.wttch.wcbs.data.jdbc.annotations;

import java.lang.annotation.*;

/**
 * 数据源切换的注解，将此注解标注在需要切换数据源的dao/service上，来对其执行的数据源进行切换。
 *
 * <p>暂时除了默认数据源似乎不支持事物
 *
 * @author wttch
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface AssignDataSource {
  /**
   * 获取切换的数据源的名称
   *
   * @return 要切换的数据源的名称
   */
  String value();
}
