package test.a;

import com.wttch.wcbs.data.jdbc.annotations.AssignDataSource;

public interface TestMapper {
  @AssignDataSource(value = "iam2")
  String test(Payload items, Integer x);

  @AssignDataSource(value = "iam1")
  String test2(Payload items, Integer x);
}
