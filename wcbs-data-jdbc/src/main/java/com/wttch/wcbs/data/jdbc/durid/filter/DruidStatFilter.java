package com.wttch.wcbs.data.jdbc.durid.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用于统计监控信息
 *
 * @author wttch
 */
@Getter
@Setter
@NoArgsConstructor
public class DruidStatFilter {
  private Boolean enabled;
  private Boolean logSlowSql;
  private Integer slowSqlMillis = 5000;
  private String mergeSql;
}
