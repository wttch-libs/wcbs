package com.wttch.wcbs.data.mybatis.fields;

import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class QueryFields {
  private QueryFields() {}

  private static final Map<QueryParamType, Class<QueryableField>> queryFieldMap = new HashMap<>();

  public static Class<QueryableField> getQueryField(QueryParamType paramType) {
    return queryFieldMap.get(paramType);
  }

  static {
    var provider = new ClassPathScanningCandidateComponentProvider(false);
    provider.addIncludeFilter(new AssignableTypeFilter(QueryField.class));
    provider
        .findCandidateComponents(QueryFields.class.getPackageName())
        .forEach(
            beanDefinition -> {
              if (!QueryField.class.getName().equals(beanDefinition.getBeanClassName())) {
                try {
                  var queryFieldClass =
                      (Class<QueryableField>) Class.forName(beanDefinition.getBeanClassName());
                  var queryField = (QueryField<?>) (queryFieldClass.getConstructor().newInstance());
                  queryFieldMap.put(queryField.queryParamType(), queryFieldClass);
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
}
