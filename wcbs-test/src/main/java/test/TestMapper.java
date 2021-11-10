package test;

import com.wttch.wcbs.mybatis.annotations.AssignDataSource;

public interface TestMapper {
  @AssignDataSource(value = "iam2")
  String test();
}
