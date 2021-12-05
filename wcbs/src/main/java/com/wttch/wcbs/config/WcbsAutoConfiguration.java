package com.wttch.wcbs.config;

import com.wttch.wcbs.json.DateRangeDeserializer;
import com.wttch.wcbs.json.DateRangeSerializer;
import com.wttch.wcbs.web.jackson.SimpleModuleProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * wcbs 整体的自动配置
 *
 * @author wttch
 */
@Slf4j
@Configuration
public class WcbsAutoConfiguration {

  @Bean
  public SimpleModuleProvider coreSimpleModuleProvider() {
    var provider = new SimpleModuleProvider("CoreEntityModule");
    provider.addSerializer(new DateRangeSerializer());
    provider.addDeserializer(new DateRangeDeserializer());
    return provider;
  }
}
