package com.wttch.wcbs.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wttch.wcbs.core.entity.DateRange;

import java.io.IOException;

public class DateRangeSerializer extends JsonSerializer<DateRange> {

  @Override
  public void serialize(DateRange value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {}

  @Override
  public Class<DateRange> handledType() {
    return DateRange.class;
  }
}
