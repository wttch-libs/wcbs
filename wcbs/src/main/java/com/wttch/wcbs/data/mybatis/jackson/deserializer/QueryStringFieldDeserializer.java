package com.wttch.wcbs.data.mybatis.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.wttch.wcbs.data.mybatis.fields.QueryStringField;
import com.wttch.wcbs.web.jackson.BaseJsonDeserializer;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * {@link QueryStringField} 反序列化器，直接将前端字段转为通用查询可以使用的字段。
 *
 * @author wttch
 */
@JsonComponent
public class QueryStringFieldDeserializer extends BaseJsonDeserializer<QueryStringField> {
  @Override
  public QueryStringField deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    return new QueryStringField(p.getText());
  }

  @Override
  public Class<? super QueryStringField> handledType() {
    return QueryStringField.class;
  }
}
