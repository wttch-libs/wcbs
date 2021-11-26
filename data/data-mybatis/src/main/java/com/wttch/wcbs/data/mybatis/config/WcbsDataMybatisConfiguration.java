package com.wttch.wcbs.data.mybatis.config;

import com.wttch.wcbs.data.mybatis.interceptor.QueryFieldInterceptor;
import java.util.Properties;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通用查询和一些自定mybatis相关的自动化配置
 *
 * @author wttch
 */
@Configuration
public class WcbsDataMybatisConfiguration {
  /**
   * 同时值此号多种方言的 sql, 需要在mapper xml文件中指定相应的 database id
   *
   * @return databaseId 提供器
   */
  @Bean
  public DatabaseIdProvider databaseIdProvider() {
    var databaseIdProvider = new VendorDatabaseIdProvider();
    var properties = new Properties();
    properties.setProperty("Oracle", "oracle");
    properties.setProperty("MySQL", "mysql");
    properties.setProperty("DB2", "db2");
    properties.setProperty("Derby", "derby");
    properties.setProperty("H2", "h2");
    properties.setProperty("HSQL", "hsql");
    properties.setProperty("Informix", "informix");
    properties.setProperty("MS-SQL", "ms-sql");
    properties.setProperty("PostgreSQL", "postgresql");
    properties.setProperty("Sybase", "sybase");
    properties.setProperty("Hana", "hana");
    databaseIdProvider.setProperties(properties);
    return databaseIdProvider;
  }

  @Bean
  public QueryFieldInterceptor testHandler() {
    return new QueryFieldInterceptor();
  }
}
