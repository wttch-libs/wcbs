package com.wttch.wcbs.data.mybatis.item;

import com.wttch.wcbs.core.exception.FrameworkException;
import com.wttch.wcbs.data.mybatis.annotations.QueryColumn;
import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.type.filter.AssignableTypeFilter;

@SuppressWarnings("unchecked")
public class QueryItems {
  private QueryItems() {}

  private static final Map<QueryParamType, Class<QueryItem<?>>> QUERY_FIELD_MAP =
      new EnumMap<>(QueryParamType.class);

  public static Class<QueryItem<?>> getQueryField(QueryParamType paramType) {
    return QUERY_FIELD_MAP.get(paramType);
  }

  static {
    var provider = new ClassPathScanningCandidateComponentProvider(false);
    provider.addIncludeFilter(new AssignableTypeFilter(QueryItem.class));
    provider
        .findCandidateComponents(QueryItems.class.getPackageName())
        .forEach(
            beanDefinition -> {
              if (!QueryItem.class.getName().equals(beanDefinition.getBeanClassName())) {
                try {
                  var queryFieldClass =
                      (Class<QueryItem<?>>) Class.forName(beanDefinition.getBeanClassName());
                  var queryField = (QueryItem<?>) (queryFieldClass.getConstructor().newInstance());
                  QUERY_FIELD_MAP.put(queryField.queryParamType(), queryFieldClass);
                } catch (ClassNotFoundException
                    | NoSuchMethodException
                    | InvocationTargetException
                    | InstantiationException
                    | IllegalAccessException e) {
                  e.printStackTrace();
                }
              }
            });
  }

  public static List<QueryItem<?>> getQueryItems(Object request) {
    var clazz = request.getClass();
    // 所有字段
    var fields = clazz.getDeclaredFields();
    AccessibleObject.setAccessible(fields, true);
    return Arrays.stream(fields)
        .map(
            field -> {
              try {
                var queryColumn =
                    AnnotatedElementUtils.findMergedAnnotation(field, QueryColumn.class);
                if (Objects.isNull(queryColumn)) {
                  return null;
                }
                var queryFieldClass =
                    Optional.ofNullable(QueryItems.getQueryField(queryColumn.type()))
                        .orElseThrow(() -> new FrameworkException("不支持的类型" + queryColumn.type()));
                var queryField = (field.get(request));

                var queryColumnT = queryFieldClass.getConstructor().newInstance();
                queryColumnT.setValue(queryField);
                var prefix =
                    queryColumn.tableName().isEmpty()
                        ? ""
                        : queryColumn.tableName() + queryColumn.delimiter();
                var key = prefix + queryColumn.columnName();
                queryColumnT.setKey(key);
                return queryColumnT;
              } catch (IllegalAccessException
                  | NoSuchMethodException
                  | InvocationTargetException
                  | InstantiationException e) {
                e.printStackTrace();
                return null;
              }
            })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }
}
