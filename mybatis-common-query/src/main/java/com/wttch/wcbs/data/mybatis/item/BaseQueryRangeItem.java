package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.core.entity.Range;
import com.wttch.wcbs.data.mybatis.Parameter;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.apache.ibatis.mapping.BoundSql;

/**
 * 范围查询的基类，一些范围查询通用的方法
 *
 * @param <R> 范围实体的类型
 * @param <T> 范围表示的数据类型
 * @author wttch
 */
public abstract class BaseQueryRangeItem<R extends Range<T>, T> extends QueryItem<R> {

  /**
   * 获取范围表示的数据的类型
   *
   * @return 范围表示的数据的类型
   */
  public abstract Class<T> rangeValueType();

  /**
   * 获取查询用的sql表达式
   *
   * @return 查询用的sql表达式
   */
  @Override
  public String queryExpression() {
    var sql = "";
    if (Objects.nonNull(value.getBegin())) {
      sql += String.format(" %s >= ? ", key);
    }
    if (Objects.nonNull(value.getEnd())) {
      if (Objects.nonNull(value.getBegin())) {
        sql += " and ";
      }
      sql += String.format(" %s <= ? ", key);
    }
    return sql;
  }

  /**
   * 获取参数列表, 为了防止sql注入，将参数类型和值封装起来交给 {@link BoundSql} 来处理生成sql。
   *
   * @return 参数列表
   */
  @Override
  public List<Parameter> parameters() {
    var params = new LinkedList<Parameter>();
    if (Objects.nonNull(value.getBegin())) {
      params.add(new Parameter(rangeValueType(), value.getBegin()));
    }
    if (Objects.nonNull(value.getEnd())) {
      params.add(new Parameter(rangeValueType(), value.getEnd()));
    }
    return params;
  }
}
