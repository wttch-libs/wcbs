package com.wttch.wcbs.logs.config;

import com.wttch.wcbs.logs.logs.OperatorLogListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置，扫描切面和一些自定义的监听日志Bean
 *
 * @author wttch
 */
@Configuration
@ComponentScan("com.wttch.wcbs.logs.component")
public class LogAutoConfiguration {
  @Bean
  public OperatorLogListener defaultLogListener() {
    return new OperatorLogListener();
  }
}
