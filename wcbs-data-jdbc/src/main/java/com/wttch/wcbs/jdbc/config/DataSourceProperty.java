package com.wttch.wcbs.jdbc.config;

import com.wttch.wcbs.jdbc.durid.DruidDataSourceProperties;
import com.wttch.wcbs.jdbc.hikari.HikariDataSourceProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;

/**
 * 单个数据库源配置
 *
 * @author wttch
 */
@NoArgsConstructor
@Getter
@Setter
public class DataSourceProperty {
  /** 数据库源的名称，必填 */
  private String name;

  private String driverClassName;
  private String url;
  private String username;
  private String password;
  /** 加载系统默认的初始化 sql */
  private Boolean loadFunction;
  /** 初始化sql的路径 */
  private List<String> initSqlPath;

  @NestedConfigurationProperty private HikariDataSourceProperties hikari;

  @NestedConfigurationProperty private DruidDataSourceProperties druid;
}
