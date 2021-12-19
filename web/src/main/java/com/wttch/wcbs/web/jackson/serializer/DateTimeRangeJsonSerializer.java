package com.wttch.wcbs.web.jackson.serializer;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.BeanProperty;
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
}
