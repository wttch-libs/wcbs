package com.wttch.wcbs.data.mybatis;

import com.wttch.wcbs.data.mybatis.fields.QueryableField;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 实际查询使用的传参，实现{@link QueryRequest} 的请求载体类，可以直接通过调用 {@link QueryRequest#queryItems()}来对 查询参数进行装配。
 *
 * @author wttch
 */
@AllArgsConstructor
@Getter
public class QueryItems {
  /** 所有通用查询的字段 */
  private List<QueryableField> items;
}
