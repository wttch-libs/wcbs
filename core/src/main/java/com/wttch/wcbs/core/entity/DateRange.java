package com.wttch.wcbs.core.entity;

import java.time.LocalDate;

public class DateRange extends Range<LocalDate> {
  public DateRange(LocalDate begin, LocalDate end) {
    super(begin, end);
  }
}
