package com.wttch.wcbs.web.jackson.serializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wttch.wcbs.core.entity.DateRange;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;

/**
 * 日期范围序列化器和反序列化器
 *
 * @author wttch
 */
public class DateRangeJsonSerializer {
  private DateRangeJsonSerializer() {}

  /** 日期范围默认的分隔符 */
  public static final String DEFAULT_SEPARATOR = "/";

  /**
   * 日期范围中日期的默认格式.
   *
   * <p>和 {@link #DEFAULT_SEPARATOR} 共同作用, 则可将 "yyyy-MM-dd/yyyy-MM-dd" 字符串转换为一个日期范围
   */
  public static final DateTimeFormatter DEFAULT_FORMATTER =
      DateTimeFormatter.ofPattern("yyyy-MM-dd");

  /** 日期的序列化器 */
  public static class DateRangeSerializer
      extends RangeJsonSerializer.RangeSerializer<DateRange, LocalDate> {

    public DateRangeSerializer() {
      super(DateRange.class);
    }

    public DateRangeSerializer(SerializerProvider provider, BeanProperty property) {
      super(DateRange.class, provider, property);
    }

    /**
     * 格式化范围的方法, 格式化的是范围的开始/结束字段的类型
     *
     * @param value 要格式的字段, 范围的开始或结束
     * @return 序列化为字符串类型的范围开始/结束
     */
    @Override
    protected String format(LocalDate value) {
      return Optional.ofNullable(jsonFormat)
          .map(JsonFormat::pattern)
          .map(DateTimeFormatter::ofPattern)
          .orElse(DEFAULT_FORMATTER)
          .format(value);
    }

    /**
     * 使用需要序列化的字段的相关定义来初始化序列化器
     *
     * @param provider 序列化器提供器
     * @param property 字段定义
     * @return 序列化的字段的相关定义来初始化序列化器
     */
    @Override
    protected RangeJsonSerializer.RangeSerializer<DateRange, LocalDate> initContextual(
        SerializerProvider provider, BeanProperty property) {
      return new DateRangeSerializer(provider, property);
    }
  }

  /** 日期范围的反序列化器 */
  public static class DateRangeDeserializer
      extends RangeJsonSerializer.RangeDeserializer<DateRange, LocalDate> {

    public DateRangeDeserializer() {
      super(DateRange.class);
    }

    /**
     * 构造范围的反序列化器
     *
     * <p>使用字段属性来获取属性上的相关格式的定制注解
     *
     * @param ctxt 反序列化器上下文
     * @param property 反序列化字段的字段定义
     */
    protected DateRangeDeserializer(DeserializationContext ctxt, BeanProperty property) {
      super(DateRange.class, ctxt, property);
    }

    /**
     * 解析范围的方法, 解析的是范围的开始/结束字段的类型
     *
     * @param value 要解析的字段, 范围的开始或结束
     * @return 反序列化为字符串类型的范围开始/结束
     */
    @Override
    protected LocalDate parse(String value) {
      if (Objects.isNull(value) || value.isEmpty()) {
        return null;
      }
      return LocalDate.parse(
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
    protected DateRange range(LocalDate start, LocalDate end) {
      return DateRange.of(start, end);
    }

    /**
     * 使用需要反序列化的字段的相关定义来初始化反序列化器
     *
     * @param ctxt 反序列化器上下文
     * @param property 字段定义
     * @return 反序列化的字段的相关定义来初始化反序列化器
     */
    @Override
    protected RangeJsonSerializer.RangeDeserializer<DateRange, LocalDate> initContextual(
        DeserializationContext ctxt, BeanProperty property) {
      return new DateRangeDeserializer(ctxt, property);
    }
  }
}
