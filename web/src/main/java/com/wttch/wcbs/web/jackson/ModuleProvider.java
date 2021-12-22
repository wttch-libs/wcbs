package com.wttch.wcbs.web.jackson;

import com.fasterxml.jackson.databind.Module;

/**
 * Jackson module 提供器，自动配置会在加载所有该接口的实现，然后通过调用{@link #getModule()}获取 jackson module, 将所有的 module 注入
 * {@link org.springframework.http.converter.json.MappingJackson2HttpMessageConverter}
 *
 * @author wttch
 */
public interface ModuleProvider {
  /**
   * 获取提供器提供的 module
   *
   * @return 提供器提供的 module
   */
  Module getModule();
}
