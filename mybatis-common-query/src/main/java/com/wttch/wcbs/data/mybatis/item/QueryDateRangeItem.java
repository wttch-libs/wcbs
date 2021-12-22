package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.core.entity.DateRange;
import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * 日期范围查询
 *
 * @author wttch
 */
@NoArgsConstructor
class QueryDateRangeItem extends BaseQueryRangeItem<DateRange, LocalDate> {
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
   * 获取范围表示的数据的类型
   *
   * @return 范围表示的数据的类型
   */
  @Override
  public Class<LocalDate> rangeValueType() {
    return LocalDate.class;
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
}
