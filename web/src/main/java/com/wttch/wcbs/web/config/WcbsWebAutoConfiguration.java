package com.wttch.wcbs.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wttch.wcbs.web.error.ErrorCode;
import com.wttch.wcbs.web.jackson.ModuleProvider;
import com.wttch.wcbs.web.jackson.SimpleModuleProvider;
import com.wttch.wcbs.web.jackson.serializer.DateRangeJsonSerializer;
import com.wttch.wcbs.web.jackson.serializer.DateTimeRangeJsonSerializer;
import com.wttch.wcbs.web.jackson.serializer.ErrorCodeSerializer;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.ObjectProvider;
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
  public ModuleProvider errorCodeModuleProvider() {
    return new SimpleModuleProvider("errorCodeModule")
        .addSerializer(ErrorCode.class, new ErrorCodeSerializer());
  }

  @Bean
  public ModuleProvider rangeModuleProvider() {
    return new SimpleModuleProvider("rangeModule")
        .addSerializer(new DateRangeJsonSerializer.DateRangeSerializer())
        .addDeserializer(new DateRangeJsonSerializer.DateRangeDeserializer())
        .addSerializer(new DateTimeRangeJsonSerializer.DateTimeRangeSerializer())
        .addDeserializer(new DateTimeRangeJsonSerializer.DateTimeRangeDeserializer());
  }

  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(
      ObjectProvider<List<ModuleProvider>> moduleProviders) {
    ObjectMapper objectMapper = new ObjectMapper();
    var moduleProviderList = moduleProviders.getIfAvailable();
    if (Objects.nonNull(moduleProviderList)) {
      moduleProviderList.forEach(provider -> objectMapper.registerModule(provider.getModule()));
    }
    return new MappingJackson2HttpMessageConverter(objectMapper);
  }
}
