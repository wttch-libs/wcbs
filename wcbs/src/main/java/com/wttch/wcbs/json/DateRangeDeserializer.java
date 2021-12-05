package com.wttch.wcbs.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.wttch.wcbs.core.entity.DateRange;
import com.wttch.wcbs.web.jackson.BaseJsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateRangeDeserializer extends BaseJsonDeserializer<DateRange> {

  @Override
  public DateRange deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    var values = p.getText().split("/");
    var format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return new DateRange(LocalDate.parse(values[0], format), LocalDate.parse(values[1], format));
  }

  /**
   * 获取反序列化器处理的类型的类
   *
   * @return 反序列化器处理的类型的类
   */
  @Override
  public Class<? super DateRange> handledType() {
    return DateRange.class;
  }
}
