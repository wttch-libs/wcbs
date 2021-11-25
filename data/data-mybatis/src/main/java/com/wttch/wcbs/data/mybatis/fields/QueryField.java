package com.wttch.wcbs.data.mybatis.fields;

import lombok.Setter;

/**
 * 所有通用查询字段的基类
 *
 * @author wttch
 */
public abstract class QueryField<T> implements QueryableField {

  public abstract Class<T> handleType();

  @Setter protected String key;
  @Setter protected T value;
}
