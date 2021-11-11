package com.wttch.wcbs.jdbc.property;

import com.wttch.wcbs.jdbc.property.druid.DruidDataSourceProperties;
import com.wttch.wcbs.jdbc.property.hikari.HikariDataSourceProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * 单个数据库源配置
 *
 * @author wttch
 */
@NoArgsConstructor
@Getter
@Setter
public class DataSourceProperty {
  private String name;
  private String driverClassName;
  private String url;
  private String username;
  private String password;

  @NestedConfigurationProperty
  private HikariDataSourceProperties hikari = new HikariDataSourceProperties();

  @NestedConfigurationProperty
  private DruidDataSourceProperties druid = new DruidDataSourceProperties();
}
