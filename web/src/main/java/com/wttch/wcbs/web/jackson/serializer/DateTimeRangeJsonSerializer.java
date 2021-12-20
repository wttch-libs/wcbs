package com.wttch.wcbs.web.jackson.serializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wttch.wcbs.core.entity.DateTimeRange;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * 时间范围的序列化器和反序列化器
 *
 * @author wttch
 */
public class DateTimeRangeJsonSerializer {
  private DateTimeRangeJsonSerializer() {}

  private static final DateTimeFormatter DEFAULT_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public static class DateTimeRangeSerializer
      extends RangeJsonSerializer.RangeSerializer<DateTimeRange, LocalDateTime> {
    public DateTimeRangeSerializer() {
      super(DateTimeRange.class);
    }

    public DateTimeRangeSerializer(SerializerProvider provider, BeanProperty beanProperty) {
      super(DateTimeRange.class, provider, beanProperty);
    }

    @Override
    protected String format(LocalDateTime value) {
      return Optional.ofNullable(jsonFormat)
          .map(JsonFormat::pattern)
          .map(DateTimeFormatter::ofPattern)
          .orElse(DEFAULT_FORMATTER)
          .format(value);
    }

    @Override
    protected RangeJsonSerializer.RangeSerializer<DateTimeRange, LocalDateTime> initContextual(
        SerializerProvider provider, BeanProperty property) {
      return new DateTimeRangeSerializer(provider, property);
    }
  }

  public static class DateTimeRangeDeserializer
      extends RangeJsonSerializer.RangeDeserializer<DateTimeRange, LocalDateTime> {

    /** 构造范围的反序列化器 */
    public DateTimeRangeDeserializer() {
      super(DateTimeRange.class);
    }

    /**
     * 构造范围的反序列化器
     *
     * <p>使用字段属性来获取属性上的相关格式的定制注解
     *
     * @param ctxt 反序列化器上下文
     * @param property 反序列化字段的字段定义
     */
    public DateTimeRangeDeserializer(DeserializationContext ctxt, BeanProperty property) {
      super(DateTimeRange.class, ctxt, property);
    }

    /**
     * 解析范围的方法, 解析的是范围的开始/结束字段的类型
     *
     * @param value 要解析的字段, 范围的开始或结束
     * @return 反序列化为字符串类型的范围开始/结束
     */
    @Override
    protected LocalDateTime parse(String value) {
      return LocalDateTime.parse(
          value,
          Optional.ofNullable(jsonFormat)
              .map(JsonFormat::pattern)
              .map(DateTimeFormatter::ofPattern)
              .orElse(DEFAULT_FORMATTER));
    }

    /**
     * 使用解析后的字段拼装范围
     *
     * @param start 范围开始
     * @param end 范围结束
     * @return 拼装的范围
     */
    @Override
    protected DateTimeRange range(LocalDateTime start, LocalDateTime end) {
      return new DateTimeRange(start, end);
    }

    /**
     * 使用需要反序列化的字段的相关定义来初始化反序列化器
     *
     * @param ctxt 反序列化器上下文
     * @param property 字段定义
     * @return 反序列化的字段的相关定义来初始化反序列化器
     */
    @Override
    protected RangeJsonSerializer.RangeDeserializer<DateTimeRange, LocalDateTime> initContextual(
        DeserializationContext ctxt, BeanProperty property) {
      return new DateTimeRangeDeserializer(ctxt, property);
    }
  }
}
