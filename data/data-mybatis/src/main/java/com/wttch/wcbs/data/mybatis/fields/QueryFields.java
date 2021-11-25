package com.wttch.wcbs.data.mybatis.fields;

import com.wttch.wcbs.data.mybatis.enums.QueryParamType;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.lang.reflect.InvocationTargetException;
import java.util.EnumMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class QueryFields {
  private QueryFields() {}

  private static final Map<QueryParamType, Class<QueryableField>> QUERY_FIELD_MAP =
      new EnumMap<>(QueryParamType.class);

  public static Class<QueryableField> getQueryField(QueryParamType paramType) {
    return QUERY_FIELD_MAP.get(paramType);
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
}
