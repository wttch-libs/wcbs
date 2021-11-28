package test.a;

import com.wttch.wcbs.data.mybatis.annotations.*;
import lombok.Data;

import java.util.List;

@Data
@QueryEntity
public class Payload {
  @QueryColumn(columnName = "X")
  private String test;

  @QueryStringColumn(columnName = "X")
  private String test1;

  @QueryStringFuzzyColumn(columnName = "X")
  private String test2;

  @QueryStringFullFuzzyColumn(columnName = "X")
  private String test3;

  @QueryStringListColumn(columnName = "X")
  private List<String> test4;
}
