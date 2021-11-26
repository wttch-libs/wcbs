package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.data.mybatis.Parameter;
import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import com.wttch.wcbs.data.mybatis.exception.MybatisException;
import lombok.Setter;

import java.util.List;

/**
 * 所有通用查询字段的基类
 *
 * @author wttch
 */
public abstract class QueryItem<T> {
  QueryItem() {}

  /** 通用查询的字段名称 */
  @Setter protected String key;

  /** 通用查询的数值 */
  protected T value;

  public void setValue(Object value) {
    if (valueType().isInstance(value)) {
      this.value = valueType().cast(value);
    } else {
      throw new MybatisException(String.format("无法将 %s 转换为 %s", value, this.getClass()));
    }
  }

  public abstract Class<T> valueType();

  /**
   * 获取处理的查询字段的类型
   *
   * @return 处理查询的字段的类型
   */
  public abstract QueryParamType queryParamType();

  /**
   * 获取查询用的sql表达式
   *
   * @return 查询用的sql表达式
   */
  public abstract String queryExpression();

  /**
   * 获取参数列表, 为了防止sql注入，将参数类型和值封装起来交给 {@link org.apache.ibatis.mapping.BoundSql} 来处理生成sql。
   *
   * @return 参数列表
   */
  public abstract List<Parameter> parameters();
}
