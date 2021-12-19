package com.wttch.wcbs.web.jackson.serializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.wttch.wcbs.core.entity.Range;
import com.wttch.wcbs.web.annotations.RangeSeparator;
import com.wttch.wcbs.web.jackson.BaseJsonDeserializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * 范围的序列化器和反序列器的基类
 *
 * @author wttch
 */
public class RangeJsonSerializer {
  private RangeJsonSerializer() {}
  /** 范围默认的分隔符 */
  public static final String DEFAULT_SEPARATOR = "/";

  /**
   * 范围的序列化器基类
   *
   * @param <T> 范围类型
   * @param <V> 范围内属性的类型
   */
  public abstract static class RangeSerializer<T extends Range<V>, V> extends StdSerializer<T>
      implements ContextualSerializer {
    /** 序列化字段上的 RangeSeparator 注解 */
    @Nullable protected transient RangeSeparator separator;
    /** 序列化字段上的 JsonFormat 注解 */
    @Nullable protected transient JsonFormat jsonFormat;

    /**
     * 构造范围的序列化器
     *
     * @param valueClass 序列化器处理的字段类型
     */
    protected RangeSerializer(Class<T> valueClass) {
      super(valueClass);
    }

    /**
     * 构造范围的序列化器
     *
     * <p>使用字段属性来获取属性上的相关格式的定制注解
     *
     * @param valueClass 序列化器处理的字段类型
     * @param serializerProvider 序列化器提供器
     * @param property 序列化字段的字段定义
     */
    protected RangeSerializer(
        Class<T> valueClass, SerializerProvider serializerProvider, BeanProperty property) {
      super(valueClass);
      separator =
          Optional.ofNullable(property)
              .map(p -> p.getAnnotation(RangeSeparator.class))
              .orElse(null);
      jsonFormat =
          Optional.ofNullable(property).map(p -> p.getAnnotation(JsonFormat.class)).orElse(null);
    }

    /**
     * 获取范围的分隔符, 如果字段上存在注解 {@link RangeSeparator} 则使用 {@link RangeSeparator#value()} 作为分隔符, 否则使用
     * {@link #DEFAULT_SEPARATOR}
     *
     * @return 范围的分隔符
     */
    @NotNull
    protected String getSeparator() {
      return Optional.ofNullable(separator).map(RangeSeparator::value).orElse(DEFAULT_SEPARATOR);
    }

    /**
     * 格式化范围的方法, 格式化的是范围的开始/结束字段的类型
     *
     * @param value 要格式的字段, 范围的开始或结束
     * @return 序列化为字符串类型的范围开始/结束
     */
    protected abstract String format(V value);

    @Override
    public void serialize(T value, JsonGenerator gen, SerializerProvider serializers)
        throws IOException {
      gen.writeString(
          String.format(
              "%s%s%s",
              Optional.ofNullable(value.getBegin()).map(this::format).orElse(""),
              getSeparator(),
              Optional.ofNullable(value.getEnd()).map(this::format).orElse("")));
    }

    /**
     * 使用需要序列化的字段的相关定义来初始化序列化器
     *
     * @param provider 序列化器提供器
     * @param property 字段定义
     * @return 序列化的字段的相关定义来初始化序列化器
     */
    protected abstract RangeSerializer<T, V> initContextual(
        SerializerProvider provider, BeanProperty property);

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
        throws JsonMappingException {
      return initContextual(prov, property);
    }
  }

  /**
   * 范围的反序列化器基类
   *
   * @param <T> 范围类型
   * @param <V> 范围内属性的类型
   */
  public abstract static class RangeDeserializer<T extends Range<V>, V>
      extends BaseJsonDeserializer<T> implements ContextualDeserializer {

    /** 序列化字段上的 RangeSeparator 注解 */
    @Nullable protected RangeSeparator separator;
    /** 序列化字段上的 JsonFormat 注解 */
    @Nullable protected JsonFormat jsonFormat;

    @Nullable protected Pattern pattern;

    /**
     * 构造范围的反序列化器
     *
     * @param valueClass 反序列化器处理的字段类型
     */
    protected RangeDeserializer(Class<T> valueClass) {
      super(valueClass);
    }

    /**
     * 构造范围的反序列化器
     *
     * <p>使用字段属性来获取属性上的相关格式的定制注解
     *
     * @param valueClass 反序列化器处理的字段类型
     * @param ctxt 反序列化器上下文
     * @param property 反序列化字段的字段定义
     */
    protected RangeDeserializer(
        Class<T> valueClass, DeserializationContext ctxt, BeanProperty property) {
      super(valueClass);
      separator =
          Optional.ofNullable(property)
              .map(p -> p.getAnnotation(RangeSeparator.class))
              .orElse(null);
      jsonFormat =
          Optional.ofNullable(property).map(p -> p.getAnnotation(JsonFormat.class)).orElse(null);
      pattern = Pattern.compile("^(.*?)" + getSeparator() + "(.*)$");
    }

    /**
     * 获取范围的分隔符, 如果字段上存在注解 {@link RangeSeparator} 则使用 {@link RangeSeparator#value()} 作为分隔符, 否则使用
     * {@link #DEFAULT_SEPARATOR}
     *
     * @return 范围的分隔符
     */
    @NotNull
    protected String getSeparator() {
      return Optional.ofNullable(separator).map(RangeSeparator::value).orElse(DEFAULT_SEPARATOR);
    }
    /**
     * 解析范围的方法, 解析的是范围的开始/结束字段的类型
     *
     * @param value 要解析的字段, 范围的开始或结束
     * @return 反序列化为字符串类型的范围开始/结束
     */
    protected abstract V parse(String value);

    /**
     * 使用解析后的字段拼装范围
     *
     * @param start 范围开始
     * @param end 范围结束
     * @return 拼装的范围
     */
    protected abstract T range(V start, V end);

    /**
     * 使用需要反序列化的字段的相关定义来初始化反序列化器
     *
     * @param ctxt 反序列化器上下文
     * @param property 字段定义
     * @return 反序列化的字段的相关定义来初始化反序列化器
     */
    protected abstract RangeDeserializer<T, V> initContextual(
        DeserializationContext ctxt, BeanProperty property);

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
      String value = p.getValueAsString();
      if (Objects.isNull(value) || Objects.isNull(pattern)) {
        return null;
      }
      var matcher = pattern.matcher(value);
      if (matcher.find()) {
        return range(parse(matcher.group(1)), parse(matcher.group(2)));
      }
      return null;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
        throws JsonMappingException {
      return initContextual(ctxt, property);
    }
  }
}
