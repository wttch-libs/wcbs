package test.a;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wttch.wcbs.core.entity.DateRange;
import com.wttch.wcbs.data.mybatis.annotations.*;
import com.wttch.wcbs.web.annotations.RangeSeparator;
import java.util.List;
import lombok.Data;

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

  @QueryIntegerColumn(columnName = "X")
  private Integer testInt;

  @QueryIntegerListColumn(columnName = "X")
  private List<Integer> testIntList;

  @QueryDateRangeColumn(columnName = "Y")
  @RangeSeparator("/")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private DateRange testDateRange;
}
