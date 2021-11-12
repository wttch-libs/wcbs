package com.wttch.wcbs.jdbc.config;

import com.wttch.wcbs.core.config.WcbsCoreAutoConfiguration;
import com.wttch.wcbs.jdbc.aspect.AssignDataSourceMethodAspect;
import com.wttch.wcbs.jdbc.database.MultipleRoutingDataSource;
import com.wttch.wcbs.jdbc.database.provider.*;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;

/**
 * wcbs-mybatis 自动化配置
 *
 * @author wttch
 */
@Configuration
@EnableConfigurationProperties({MultipleDataSourceProperty.class})
@AutoConfigureAfter({WcbsCoreAutoConfiguration.class})
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
public class MultipleDataSourceAutoConfiguration {
  @Resource private MultipleDataSourceProperty multipleDataSourceProperty;

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
  public MultipleRoutingDataSource multipleRoutingDataSource(MultipleDataSourceProvider provider) {
    var multipleRoutingDataSource = new MultipleRoutingDataSource();
    multipleRoutingDataSource.setProvider(provider);
    multipleRoutingDataSource.setPrimary(multipleDataSourceProperty.getPrimary());
    return multipleRoutingDataSource;
  }

  @Bean
  @ConditionalOnMissingBean
  public AssignDataSourceMethodAspect assignDataSourceMethodAspect() {
    return new AssignDataSourceMethodAspect();
  }
}
