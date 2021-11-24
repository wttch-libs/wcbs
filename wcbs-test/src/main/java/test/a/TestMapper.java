package test.a;

import com.wttch.wcbs.data.jdbc.annotations.AssignDataSource;
import com.wttch.wcbs.data.mybatis.QueryRequest;

public interface TestMapper {
  @AssignDataSource(value = "iam2")
  String test(QueryRequest items);
}
