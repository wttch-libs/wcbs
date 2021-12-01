package com.wttch.wcbs.data.mybatis.interceptor;

import com.wttch.wcbs.data.mybatis.annotations.QueryEntity;
import com.wttch.wcbs.data.mybatis.exception.MybatisException;
import com.wttch.wcbs.data.mybatis.item.QueryItems;
import java.util.stream.Collectors;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * 可以使用通用API字段查询的mybatis拦截器
 *
 * <p>拦截 {@link Executor#query(MappedStatement, Object, RowBounds, ResultHandler)} 和 {@link
 * Executor#query(MappedStatement, Object, RowBounds, ResultHandler, CacheKey, BoundSql)}
 * 两个方法，对参数进行确认，如果包含可以进行通用查询的字段，则对其进行通用查询的sql拼装。
 *
 * @author wttch
 */
@Intercepts({
  @Signature(
      type = Executor.class,
      method = "query",
      args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
})
public class QueryFieldInterceptor implements Interceptor {

  @SuppressWarnings("rawtypes")
  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    Object[] args = invocation.getArgs();
    MappedStatement ms = (MappedStatement) args[0];
    Object parameter = args[1];
    RowBounds rowBounds = (RowBounds) args[2];
    var resultHandler = (ResultHandler) args[3];
    Executor executor = (Executor) invocation.getTarget();
    CacheKey cacheKey = null;
    BoundSql boundSql;
    Object queryParam = null;
    // 由于逻辑关系，只会进入一次
    // 4 个参数时
    if (!(parameter instanceof MapperMethod.ParamMap)
        && parameter.getClass().isAnnotationPresent(QueryEntity.class)) {
      boundSql = ms.getBoundSql(null);
      queryParam = parameter;
    } else {
      var queryParams =
          ((MapperMethod.ParamMap<?>) parameter)
              .values().stream()
                  .filter(p -> p.getClass().isAnnotationPresent(QueryEntity.class))
                  .collect(Collectors.toSet());
      if (queryParams.size() > 1) {
        throw new MybatisException("参数多于两个 QueryItem");
      }
      if (queryParams.size() == 1) {
        queryParam = queryParams.iterator().next();
      }
      boundSql = ms.getBoundSql(parameter);
    }
    if (queryParam != null) {
      var sql = new StringBuilder(boundSql.getSql());
      var cnt = 1;
      // parameterMappings 参数过多
      var parameterMappings = boundSql.getParameterMappings();
      var parameterMap = (MapperMethod.ParamMap<Object>) parameter;
      for (var field : QueryItems.getQueryItems(queryParam)) {
        sql.append(" and ").append(field.queryExpression());
        var params = field.parameters();
        for (var param : params) {
          var property = "tmp" + cnt;
          // 不能一直往里面添加，暂时没找到好的方法
          if (parameterMappings.stream().noneMatch(t -> t.getProperty().equals(property))) {
            parameterMappings.add(
                new ParameterMapping.Builder(
                        ms.getConfiguration(), property, param.getMappingClass())
                    .build());
          }
          parameterMap.put(property, param.getValue());
          cnt++;
        }
      }
      boundSql = new BoundSql(ms.getConfiguration(), sql.toString(), parameterMappings, parameter);
      cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
      return executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
    } else {
      return invocation.proceed();
    }
  }
}
