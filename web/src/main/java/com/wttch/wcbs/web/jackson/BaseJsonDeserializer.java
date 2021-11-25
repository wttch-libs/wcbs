package com.wttch.wcbs.web.jackson;

import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * 反序列化器简单包装，使其可以通过 {@link #handledType()} 获取序列化处理的类，然后通过 {@link
 * com.fasterxml.jackson.databind.module.SimpleModule#addDeserializer(Class, JsonDeserializer)}
 * 方法来添加反序列化器。
 *
 * @param <T> 反序列化器处理的类型
 * @author wttch
 */
public abstract class BaseJsonDeserializer<T> extends JsonDeserializer<T> {

  /**
   * 获取反序列化器处理的类型的类
   *
   * @return 反序列化器处理的类型的类
   */
  @Override
  public abstract Class<? super T> handledType();
}
