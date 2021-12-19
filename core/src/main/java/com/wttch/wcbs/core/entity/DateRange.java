package com.wttch.wcbs.core.entity;

import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

/**
 * 日期范围，表示一段日期
 *
 * @author wttch
 */
public class DateRange extends Range<LocalDate> {
  public DateRange() {
    this(null, null);
  }

  public DateRange(@Nullable LocalDate begin, @Nullable LocalDate end) {
    super(begin, end);
  }

  public static DateRange of(@Nullable LocalDate begin, @Nullable LocalDate end) {
    return new DateRange(begin, end);
  }
}
