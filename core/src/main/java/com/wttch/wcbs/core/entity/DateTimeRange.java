package com.wttch.wcbs.core.entity;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;

/**
 * 时间范围
 *
 * @author wttch
 */
public class DateTimeRange extends Range<LocalDateTime> {
  public DateTimeRange(@Nullable LocalDateTime begin, @Nullable LocalDateTime end) {
    super(begin, end);
  }

  public static DateTimeRange of(@Nullable LocalDateTime begin, @Nullable LocalDateTime end) {
    return new DateTimeRange(begin, end);
  }
}
