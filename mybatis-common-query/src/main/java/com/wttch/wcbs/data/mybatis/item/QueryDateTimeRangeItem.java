package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.core.entity.DateTimeRange;
import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 时间范围检索
 *
 * @author wttch
 */
@NoArgsConstructor
class QueryDateTimeRangeItem extends BaseQueryRangeItem<DateTimeRange, LocalDateTime> {
  /**
   * 获取范围表示的数据的类型
   *
   * @return 范围表示的数据的类型
   */
  @Override
  public Class<LocalDateTime> rangeValueType() {
    return LocalDateTime.class;
  }

  /**
   * 获取通用查询的字段的数值类型
   *
   * @return 通用查询的字段的数值类型
   */
  @Override
  public Class<DateTimeRange> valueType() {
    return DateTimeRange.class;
  }

  /**
   * 获取处理的查询字段的类型
   *
   * @return 处理查询的字段的类型
   */
  @Override
  public QueryParamType queryParamType() {
    return QueryParamType.DATE_TIME_RANGE;
  }
}
