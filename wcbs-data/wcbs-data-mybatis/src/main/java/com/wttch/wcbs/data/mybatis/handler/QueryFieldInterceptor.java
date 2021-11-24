package com.wttch.wcbs.data.mybatis.handler;

import com.wttch.wcbs.data.mybatis.QueryRequest;
import com.wttch.wcbs.data.mybatis.exception.MybatisException;
import java.util.stream.Collectors;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
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
  @Signature(
      type = Executor.class,
      method = "query",
      args = {
        MappedStatement.class,
        Object.class,
        RowBounds.class,
        ResultHandler.class,
        CacheKey.class,
        BoundSql.class
      }),
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
    CacheKey cacheKey;
    BoundSql boundSql;
    QueryRequest queryParam = null;
    // 由于逻辑关系，只会进入一次
    if (args.length == 4) {
      // 4 个参数时
      if (parameter instanceof QueryRequest) {
        boundSql = ms.getBoundSql(null);
        queryParam = (QueryRequest) parameter;
      } else {
        var queryParams =
            ((MapperMethod.ParamMap<?>) parameter)
                .values().stream()
                    .filter(QueryRequest.class::isInstance)
                    .map(QueryRequest.class::cast)
                    .collect(Collectors.toSet());
        if (queryParams.size() > 1) {
          throw new MybatisException("参数多于两个 QueryItem");
        }
        if (queryParams.size() == 1) {
          queryParam = queryParams.iterator().next();
        }
        boundSql = ms.getBoundSql(parameter);
      }
      cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
    } else {
      // 6 个参数时
      cacheKey = (CacheKey) args[4];
      boundSql = (BoundSql) args[5];
    }
    if (queryParam != null) {
      var sql = new StringBuilder(boundSql.getSql());
      queryParam
          .queryItems()
          .getItems()
          .forEach(field -> sql.append(" and ").append(field.queryExpression()));
      boundSql =
          new BoundSql(
              ms.getConfiguration(), sql.toString(), boundSql.getParameterMappings(), parameter);
      return executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
    } else {
      return invocation.proceed();
    }
  }
}
