package com.wttch.wcbs.data.mybatis.annotations;

import java.lang.annotation.*;
import org.springframework.core.annotation.AliasFor;

/**
 * 在通用查询实体类上标注可以进行排序的字段
 *
 * @author wttch
 * @see OrderByColumns
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Repeatable(OrderByColumns.class)
public @interface OrderByColumn {
  /**
   * 获取排序的字段名称
   *
   * @return 排序的字段名称
   * @see #columnName()
   */
  @AliasFor("columnName")
  String value() default "";

  @AliasFor("value")
  String columnName() default "";
}
