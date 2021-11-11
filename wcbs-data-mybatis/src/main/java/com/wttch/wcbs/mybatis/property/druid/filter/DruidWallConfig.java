package com.wttch.wcbs.mybatis.property.druid.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 使用WallFilter能让Select/Delete/Update的SQL自动加上租户隔离字段
 *
 * @author wttch
 */
@Getter
@Setter
@NoArgsConstructor
public class DruidWallConfig {
  /** 一次执行多条sql */
  private Boolean multiStatementAllow = false;
}
