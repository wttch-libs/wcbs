package com.wttch.wcbs.mybatis;

import com.wttch.wcbs.mybatis.property.MultipleDataSourceProperty;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({MultipleDataSourceProperty.class})
@AutoConfigureBefore({DataSourceAutoConfiguration.class})
public class MultipleDataSourceAutoConfiguration {}
