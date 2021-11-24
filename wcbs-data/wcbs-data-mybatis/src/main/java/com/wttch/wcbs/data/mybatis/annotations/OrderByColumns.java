package com.wttch.wcbs.data.mybatis.annotations;

import java.lang.annotation.*;

/**
 * 在通用查询实体类上标注可以进行排序的字段
 *
 * <p>可以使用 {@link OrderByColumn} 多次标注，也可以使用 {@code @OrderByColumns({"column1", "column2"})} 的形式。
 * 解析时会对两个字段进行非空判断，来决定使用哪个字段。
 *
 * @author wttch
 * @see OrderByColumn
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderByColumns {
  /**
   * 获取所有可以排序的字段
   *
   * @return 所有可以排序字段的声明
   */
  OrderByColumn[] value() default {};

  /**
   * 获取所有可以排序的字段
   *
   * @return 所有可以排序字段的声明
   */
  String[] columnNames() default {};
}
