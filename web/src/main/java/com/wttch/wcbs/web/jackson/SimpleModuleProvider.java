package com.wttch.wcbs.web.jackson;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * {@link SimpleModule} 提供器，所有该类型的bean都将被注入到默认的 {@link
 * org.springframework.http.converter.json.MappingJackson2HttpMessageConverter} 中。
 *
 * @author wttch
 */
public class SimpleModuleProvider implements ModuleProvider {
  private final SimpleModule simpleModule;

  public SimpleModuleProvider(String name) {
    simpleModule = new SimpleModule(name);
  }

  public SimpleModuleProvider(SimpleModule simpleModule) {
    this.simpleModule = simpleModule;
  }

  public <T> SimpleModuleProvider addSerializer(
      Class<? extends T> clazz, JsonSerializer<T> jsonSerializer) {
    simpleModule.addSerializer(clazz, jsonSerializer);
    return this;
  }

  public <T> SimpleModuleProvider addSerializer(StdSerializer<T> jsonSerializer) {
    simpleModule.addSerializer(jsonSerializer.handledType(), jsonSerializer);
    return this;
  }

  public <T> void addDeserializer(Class<T> clazz, JsonDeserializer<? extends T> jsonDeserializer) {
    simpleModule.addDeserializer(clazz, jsonDeserializer);
  }

  public <T> SimpleModuleProvider addDeserializer(BaseJsonDeserializer<T> jsonDeserializer) {
    simpleModule.addDeserializer(jsonDeserializer.handledType(), jsonDeserializer);
    return this;
  }

  @Override
  public SimpleModule getModule() {
    return simpleModule;
  }
}
