package com.wttch.wcbs.data.mybatis;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum QueryParamType {
  STRING("STRING"),
  STRING_FUZZY("STRING_FUZZY"),
  STRING_LIST("STRING_LIST"),
  INTEGER("INTEGER"),
  INTEGER_LIST("INTEGER_LIST"),
  LONG("LONG"),
  LONG_LIST("LONG_LIST"),
  DATE_RANGE("DATE_RANGE"),
  DATE_TIME_RANGE("DATE_TIME_RANGE"),
  DATE("DATE"),
  BOOLEAN("BOOLEAN"),
  BOOLEAN_LIST("BOOLEAN_LIST"),
  NAME("NAME"),
  NAME_FUZZY("NAME_FUZZY"),
  BIG_DECIMAL("BIG_DECIMAL"),
  NULL("NULL");
  private final String type;
}
