package test.a;

import com.wttch.wcbs.data.mybatis.QueryRequest;
import com.wttch.wcbs.data.mybatis.annotations.QueryColumn;
import com.wttch.wcbs.data.mybatis.annotations.QueryStringColumn;
import com.wttch.wcbs.data.mybatis.annotations.QueryStringFullFuzzyColumn;
import com.wttch.wcbs.data.mybatis.annotations.QueryStringFuzzyColumn;
import lombok.Data;

@Data
public class Payload implements QueryRequest {
  @QueryColumn(columnName = "X")
  private String test;

  @QueryStringColumn(columnName = "X")
  private String test1;

  @QueryStringFuzzyColumn(columnName = "X")
  private String test2;

  @QueryStringFullFuzzyColumn(columnName = "X")
  private String test3;
}
