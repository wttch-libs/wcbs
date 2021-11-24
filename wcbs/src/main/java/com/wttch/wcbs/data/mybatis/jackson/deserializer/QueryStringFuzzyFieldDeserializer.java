package com.wttch.wcbs.data.mybatis.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.wttch.wcbs.data.mybatis.fields.QueryStringFuzzyField;
import com.wttch.wcbs.web.jackson.BaseJsonDeserializer;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;

/**
 * {@link QueryStringFuzzyField} 的反序列化器，直接将字符串转换为 {@link QueryStringFuzzyField} 类型。
 *
 * @author wttch
 */
@JsonComponent
public class QueryStringFuzzyFieldDeserializer extends BaseJsonDeserializer<QueryStringFuzzyField> {
  @Override
  public QueryStringFuzzyField deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException {
    return new QueryStringFuzzyField(p.getText());
  }

  @Override
  public Class<? super QueryStringFuzzyField> handledType() {
    return QueryStringFuzzyField.class;
  }
}
