package com.wttch.wcbs.mybatis;

import com.wttch.wcbs.mybatis.property.MultipleDataSourceProperty;
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
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
public class MultipleDataSourceAutoConfiguration {
  @Resource private MultipleDataSourceProperty multipleDataSourceProperty;

  @Bean
  @ConditionalOnMissingBean
  public MultipleDataSourceProvider multipleDataSourceProvider() {
    return new YamlMultipleDataSourceProvider(multipleDataSourceProperty);
  }

  @Bean
  @Primary
  public MultipleRoutingDataSource multipleRoutingDataSource(MultipleDataSourceProvider provider) {
    var multipleRoutingDataSource = new MultipleRoutingDataSource();
    multipleRoutingDataSource.setProvider(provider);
    multipleRoutingDataSource.setPrimary(multipleDataSourceProperty.getPrimary());
    return multipleRoutingDataSource;
  }
}
