package com.wttch.wcbs.core.config;

import com.wttch.wcbs.core.util.SpringContextHelper;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;

/** @author wttch */
@Configuration
public class WcbsCoreAutoConfiguration {

  public WcbsCoreAutoConfiguration(ConfigurableApplicationContext applicationContext) {
    SpringContextHelper.setContext(applicationContext);
  }
}
