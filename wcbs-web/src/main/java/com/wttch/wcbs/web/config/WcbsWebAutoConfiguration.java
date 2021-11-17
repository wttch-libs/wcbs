package com.wttch.wcbs.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.wttch.wcbs.web.error.ErrorCode;
import com.wttch.wcbs.web.serializer.ErrorCodeSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * wcbs-web 自动配置
 *
 * @author wttch
 */
@Configuration
@EnableConfigurationProperties({WcbsWebProperty.class})
public class WcbsWebAutoConfiguration implements WebMvcConfigurer {
  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
    ObjectMapper objectMapper = new ObjectMapper();
    var module = new SimpleModule();
    module.addSerializer(ErrorCode.class, new ErrorCodeSerializer());
    objectMapper.registerModules(module);
    return new MappingJackson2HttpMessageConverter(objectMapper);
  }
}
