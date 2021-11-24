package com.wttch.wcbs.data.mybatis.exception;

import com.wttch.wcbs.core.exception.WcbsException;

/**
 * mybatis 相关的异常
 *
 * @author wttch
 */
public class MybatisException extends WcbsException {
  public MybatisException() {}

  public MybatisException(String message) {
    super(message);
  }

  public MybatisException(String message, Throwable cause) {
    super(message, cause);
  }

  public MybatisException(Throwable cause) {
    super(cause);
  }

  public MybatisException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
