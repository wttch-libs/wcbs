package com.wttch.wcbs.data.jdbc.config;

import com.wttch.wcbs.core.config.WcbsCoreAutoConfiguration;
import com.wttch.wcbs.data.jdbc.aspect.AssignDataSourceMethodAspect;
import com.wttch.wcbs.data.jdbc.database.MultipleRoutingDataSource;
import com.wttch.wcbs.data.jdbc.database.provider.*;
import javax.annotation.Resource;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * wcbs-mybatis 自动化配置
 *
 * @author wttch
 */
@Configuration
@EnableConfigurationProperties({MultipleDataSourceProperties.class})
@AutoConfigureAfter({WcbsCoreAutoConfiguration.class})
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
public class MultipleDataSourceAutoConfiguration {
  @Resource private MultipleDataSourceProperties multipleDataSourceProperty;

  @Bean
  @ConditionalOnMissingBean
  public MultipleDataSourceProvider multipleDataSourceProvider() {
    return new YamlMultipleDataSourceProvider(multipleDataSourceProperty);
  }

  @Bean
  @ConditionalOnMissingBean
  public InitSqlPathProvider initSqlPathProvider() {
    return new YamlInitSqlPathProvider(multipleDataSourceProperty);
  }

  @Bean
  @Primary
  public MultipleRoutingDataSource multipleRoutingDataSource(
      MultipleDataSourceProvider provider, InitSqlPathProvider initSqlPathProvider) {
    var multipleRoutingDataSource = new MultipleRoutingDataSource();
    multipleRoutingDataSource.setProvider(provider);
    multipleRoutingDataSource.setInitSqlPathProvider(initSqlPathProvider);
    multipleRoutingDataSource.setPrimary(multipleDataSourceProperty.getPrimary());
    return multipleRoutingDataSource;
  }

  @Bean
  @ConditionalOnMissingBean
  public AssignDataSourceMethodAspect assignDataSourceMethodAspect() {
    return new AssignDataSourceMethodAspect();
  }
}
