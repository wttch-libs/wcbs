package test.a;

import com.wttch.wcbs.data.mybatis.QueryRequest;
import com.wttch.wcbs.data.mybatis.annotations.QueryColumn;
import com.wttch.wcbs.data.mybatis.fields.QueryStringField;
import com.wttch.wcbs.data.mybatis.fields.QueryStringFuzzyField;
import lombok.Data;

@Data
public class Payload implements QueryRequest {
  @QueryColumn("X")
  private QueryStringField test;

  @QueryColumn("X")
  private QueryStringFuzzyField test1;
}
