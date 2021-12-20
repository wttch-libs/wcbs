package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.core.entity.DateRange;
import com.wttch.wcbs.data.mybatis.Parameter;
import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import lombok.NoArgsConstructor;
import org.apache.ibatis.mapping.BoundSql;

/**
 * 日期范围查询
 *
 * @author wttch
 */
@NoArgsConstructor
class QueryDateRangeItem extends QueryItem<DateRange> {
  /**
   * 获取通用查询的字段的数值类型
   *
   * @return 通用查询的字段的数值类型
   */
  @Override
  public Class<DateRange> valueType() {
    return DateRange.class;
  }

  /**
   * 获取处理的查询字段的类型
   *
   * @return 处理查询的字段的类型
   */
  @Override
  public QueryParamType queryParamType() {
    return QueryParamType.DATE_RANGE;
  }

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
      params.add(new Parameter(LocalDate.class, value.getBegin()));
    }
    if (Objects.nonNull(value.getEnd())) {
      params.add(new Parameter(LocalDate.class, value.getEnd()));
    }
    return params;
  }
}
