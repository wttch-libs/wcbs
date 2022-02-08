package com.wttch.cloud.iam.config;

import com.wttch.cloud.iam.Logs;
import com.wttch.wcbs.logs.EnumTemplateManager;
import com.wttch.wcbs.logs.LogTemplateManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogConfig {

  @Bean
  public LogTemplateManager logTemplateManager() {
    return new EnumTemplateManager(Logs.class);
  }
}
