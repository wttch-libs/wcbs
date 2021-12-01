package com.wttch.wcbs.data.mybatis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.mapping.BoundSql;

/**
 * 查询参数
 *
 * <p>拼接sql时为了防止sql注入使用 {@link org.apache.ibatis.mapping.BoundSql} 来处理sql， 将 {@link #mappingClass}
 * 装入 {@link BoundSql#getParameterMappings()} 将 {@link #value} 装入 {@link
 * BoundSql#getParameterObject()} 来预处理sql。
 *
 * @author wttch
 * @see BoundSql
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Parameter {
  /** 参数类 */
  private Class<?> mappingClass;
  /** 参数值 */
  private Object value;
}
