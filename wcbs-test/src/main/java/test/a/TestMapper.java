package test.a;

import com.wttch.wcbs.data.jdbc.annotations.AssignDataSource;

public interface TestMapper {
  @AssignDataSource(value = "iam2")
  String test();
}
