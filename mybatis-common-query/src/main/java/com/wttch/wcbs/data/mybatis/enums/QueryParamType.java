package com.wttch.wcbs.data.mybatis.enums;

import lombok.AllArgsConstructor;

/**
 * 所有接口通用查询可以使用的类型
 *
 * @author wttch
 */
@AllArgsConstructor
public enum QueryParamType {
  /** 字符串精准查询 */
  STRING("STRING"),
  /** 字符串模糊查询 */
  STRING_FUZZY("STRING_FUZZY"),
  /** 字符串全模糊查询 */
  STRING_FULL_FUZZY("STRING_FULL_FUZZY"),
  /** 字符串列表查询 */
  STRING_LIST("STRING_LIST"),
  /** 整型查询 */
  INTEGER("INTEGER"),
  /** 整型列表查询 */
  INTEGER_LIST("INTEGER_LIST"),
  /** 长整型查询 */
  LONG("LONG"),
  /** 长整型列表查询 */
  LONG_LIST("LONG_LIST"),
  /** 日期范围查询 */
  DATE_RANGE("DATE_RANGE"),
  /** 时间范围查询 */
  DATE_TIME_RANGE("DATE_TIME_RANGE"),
  /** 日期查询 */
  DATE("DATE"),
  /** boolean判断 */
  BOOLEAN("BOOLEAN"),
  BIG_DECIMAL("BIG_DECIMAL"),
  NULL("NULL");
  private final String type;
}
