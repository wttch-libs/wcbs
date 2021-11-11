package com.wttch.wcbs.jdbc.util;

import com.wttch.wcbs.core.exception.FrameworkException;
import com.wttch.wcbs.jdbc.property.DataSourceProperty;
import com.wttch.wcbs.jdbc.property.druid.DruidConfigDataSource;
import com.wttch.wcbs.jdbc.property.druid.DruidDataSourceProperties;
import com.wttch.wcbs.jdbc.property.hikari.HikariDataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.SQLException;
import java.util.Objects;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

/**
 * 数据库源工厂，会配置对应的数据库连接池
 *
 * @author wttch
 */
@Slf4j
public class DataSourceFactory {
  private static final String DRUID_DATASOURCE = "com.alibaba.druid.pool.DruidDataSource";
  private static final String HIKARICP_DATASOURCE = "com.zaxxer.hikari.HikariDataSource";

  private DataSourceFactory() {}

  public static DataSource createDataSource(
      String dataSourceKey, DataSourceProperty dataSourceProperty) {
    if (dataSourceProperty.getDruid().getEnable()) {
      try {
        Class.forName(DRUID_DATASOURCE);
        return createDruidDataSource(dataSourceProperty);
      } catch (ClassNotFoundException ex) {
        log.warn("{} 不使用 druid 数据库连接池，因为 druid 的依赖不存在", dataSourceKey);
      }
    }
    if (Objects.nonNull(dataSourceProperty.getHikari())) {
      try {
        Class.forName(HIKARICP_DATASOURCE);
        return createHikariDataSource(dataSourceProperty);
      } catch (ClassNotFoundException ex) {
        log.warn("{} 不使用 hikari 数据库连接池，因为 hikari 的依赖不存在.", dataSourceKey);
      }
    }
    return createBasicDataSource(dataSourceProperty);
  }

  private static DataSource createBasicDataSource(DataSourceProperty dataSourceProperty) {
    try {
      Class.forName("org.springframework.boot.jdbc.DataSourceBuilder");
      return DataSourceBuilder.create()
          .url(dataSourceProperty.getUrl())
          .username(dataSourceProperty.getUsername())
          .password(dataSourceProperty.getPassword())
          .driverClassName(dataSourceProperty.getDriverClassName())
          .type(SimpleDriverDataSource.class)
          .build();
    } catch (ClassNotFoundException ex) {
      log.error("无法使用spring boot 默认的数据源，因为依赖不存在.");
      throw new FrameworkException(ex.getMessage());
    }
  }

  private static DataSource createDruidDataSource(DataSourceProperty dataSourceProperty) {
    DruidConfigDataSource druidDataSource = new DruidConfigDataSource();
    druidDataSource.setUrl(dataSourceProperty.getUrl());
    druidDataSource.setUsername(dataSourceProperty.getUsername());
    druidDataSource.setPassword(dataSourceProperty.getPassword());
    druidDataSource.setDriverClassName(dataSourceProperty.getDriverClassName());
    DruidDataSourceProperties druidProperties = dataSourceProperty.getDruid();

    druidDataSource.addFilter(FilterConvertor.from(druidProperties.getFilter().getWall()));

    try {
      BeanUtils.copyProperties(druidProperties, druidDataSource);
      druidDataSource.init();
    } catch (SQLException var4) {
      log.error("create druid datasource failed", var4);
    }

    return druidDataSource;
  }

  private static DataSource createHikariDataSource(DataSourceProperty dataSourceProperty) {
    HikariDataSource hikariDataSource = new HikariDataSource();
    hikariDataSource.setJdbcUrl(dataSourceProperty.getUrl());
    hikariDataSource.setUsername(dataSourceProperty.getUsername());
    hikariDataSource.setPassword(dataSourceProperty.getPassword());
    hikariDataSource.setDriverClassName(dataSourceProperty.getDriverClassName());
    HikariDataSourceProperties hikariProperties = dataSourceProperty.getHikari();

    try {
      BeanUtils.copyProperties(hikariProperties, hikariDataSource);
    } catch (Exception var4) {
      log.error("create hikari datasource failed", var4);
    }

    return hikariDataSource;
  }
}
