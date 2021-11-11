package com.wttch.wcbs.jdbc.hikari;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Hikari 数据库连接池属性
 *
 * @author wttch
 */
@NoArgsConstructor
@Getter
@Setter
public class HikariDataSourceProperties {
  /** 自动提交从池中返回的连接 */
  private Boolean autoCommit = true;
  /** 等待来自池的连接的最大毫秒数 */
  private Integer connectionTimeout = 30000;
  /** 连接允许在池中闲置的最长时间 */
  private Integer idleTimeout = 600000;
  /** 池中连接最长生命周期 */
  private Integer maxLifetime = 1800000;
  /** 池中维护的最小空闲连接数 */
  private Integer minimumIdle = 10;
  /** 池中最大连接数，包括闲置和使用中的连接 */
  private Integer maximumPoolSize = 25;
  /** 连接池的用户定义名称 */
  private String poolName = "HikariCP";
}
