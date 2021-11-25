package com.wttch.wcbs.data.mybatis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wttch.wcbs.core.exception.FrameworkException;
import com.wttch.wcbs.data.mybatis.annotations.OrderByColumns;
import com.wttch.wcbs.data.mybatis.annotations.QueryColumn;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.wttch.wcbs.data.mybatis.fields.QueryFields;
import org.jetbrains.annotations.Nullable;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * 通用查询的请求字段类型都必须实现该接口
 *
 * @author wttch
 */
public interface QueryRequest {

  default QueryItems queryItems() {
    var clazz = getClass();
    // 所有字段
    var fields = clazz.getDeclaredFields();
    AccessibleObject.setAccessible(fields, true);
    var t =
        Arrays.stream(fields)
            .map(
                field -> {
                  try {
                    var queryColumn =
                        AnnotatedElementUtils.findMergedAnnotation(field, QueryColumn.class);
                    if (Objects.isNull(queryColumn)) {
                      return null;
                    }
                    var queryFieldClass =
                        Optional.ofNullable(QueryFields.getQueryField(queryColumn.type()))
                            .orElseThrow(
                                () -> new FrameworkException("不支持的类型" + queryColumn.type()));
                    var queryField = (field.get(this));

                    try {
                      var queryColumnT =
                          queryFieldClass
                              .getConstructor(queryField.getClass())
                              .newInstance(queryField);

                      var prefix =
                          queryColumn.tableName().isEmpty()
                              ? ""
                              : queryColumn.tableName() + queryColumn.delimiter();
                      var key = prefix + queryColumn.columnName();
                      queryColumnT.setKey(key);
                      return queryColumnT;
                    } catch (InstantiationException
                        | InvocationTargetException
                        | NoSuchMethodException e) {
                      e.printStackTrace();
                      return null;
                    }
                  } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    return null;
                  }
                })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    return new QueryItems(t);
  }

  /**
   * 获取排序方式字符串，在mybatis中会使用 order by ${orderByStr} 形式注入，所以该字段必须进行可使用校验，即使用{@link
   * OrderByColumns}注解进行声明。
   *
   * @return 如果排序字段是声明的可排序字段则返回排序信息, 否则 {@code null}
   */
  @Nullable
  @JsonIgnore
  default String orderByStr() {
    var orderByColumns = AnnotationUtils.getAnnotation(this.getClass(), OrderByColumns.class);
    var allowedOrderByColumns = new HashSet<String>();
    if (Objects.nonNull(orderByColumns)) {
      for (var column : orderByColumns.value()) {
        allowedOrderByColumns.add(column.value());
      }
      allowedOrderByColumns.addAll(Arrays.asList(orderByColumns.columnNames()));
    }
    if (Objects.nonNull(sortColumn()) && allowedOrderByColumns.contains(sortColumn())) {
      return String.format(
          "%s %s", sortColumn(), ASC.equalsIgnoreCase(sortDirection()) ? ASC : DESC);
    }
    return null;
  }

  String ASC = "asc";
  String DESC = "desc";
  /**
   * 排序字段
   *
   * @return 获取排序字段
   */
  @Nullable
  default String sortColumn() {
    return null;
  }

  /**
   * 获取排序方式
   *
   * @return 排序方式
   */
  default String sortDirection() {
    return ASC;
  }
}
