package com.wttch.wcbs.data.mybatis.fields;

import lombok.Setter;

/**
 * 所有通用查询字段的基类
 *
 * @author wttch
 */
public abstract class QueryField<T> implements QueryableField {
  protected QueryField(T value) {
    this.value = value;
  }

  @Setter protected String key;
  protected final T value;
}
