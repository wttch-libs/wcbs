package com.wttch.wcbs.web.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wttch.wcbs.web.error.ErrorCode;
import java.io.IOException;

/**
 * 错误码的序列化器
 *
 * @author wttch
 */
public class ErrorCodeSerializer extends JsonSerializer<ErrorCode> {

  @Override
  public void serialize(ErrorCode value, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    gen.writeString(value.errorCode());
  }
}
