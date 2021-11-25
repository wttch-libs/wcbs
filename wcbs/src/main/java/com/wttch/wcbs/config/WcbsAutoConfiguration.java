package com.wttch.wcbs.config;

import com.wttch.wcbs.web.jackson.BaseJsonDeserializer;
import com.wttch.wcbs.web.jackson.ModuleProvider;
import com.wttch.wcbs.web.jackson.SimpleModuleProvider;
import java.lang.reflect.InvocationTargetException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AssignableTypeFilter;

/**
 * wcbs 整体的自动配置
 *
 * @author wttch
 */
@Slf4j
@Configuration
public class WcbsAutoConfiguration {

  /**
   * 通用查询的字段的反序列化器提供器，会自动注入到 {@link
   * org.springframework.http.converter.json.MappingJackson2HttpMessageConverter} 中。
   *
   * @return 反序列化提供器
   */
  @Bean
  public ModuleProvider mybatisQueryModuleProvider() {
    var scanningProvider = new ClassPathScanningCandidateComponentProvider(false);
    scanningProvider.addIncludeFilter(new AssignableTypeFilter(BaseJsonDeserializer.class));
    var beanDefinitions =
        scanningProvider.findCandidateComponents(
            "com.wttch.wcbs.data.mybatis.jackson.deserializer");
    var moduleProvider = new SimpleModuleProvider("queryFieldModule");
    for (var beanDefinition : beanDefinitions) {
      try {
        var jsonDeserializer =
            (BaseJsonDeserializer<?>)
                Class.forName(beanDefinition.getBeanClassName()).getConstructor().newInstance();
        moduleProvider.addDeserializer(jsonDeserializer);
        log.debug(
            "已注册通用查询字段<{}>的反序列化器:{}.",
            jsonDeserializer.handledType().getSimpleName(),
            beanDefinition.getBeanClassName());
      } catch (InstantiationException
          | IllegalAccessException
          | InvocationTargetException
          | NoSuchMethodException
          | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
    return moduleProvider;
  }
}
