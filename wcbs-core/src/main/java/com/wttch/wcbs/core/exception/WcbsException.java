package com.wttch.wcbs.core.exception;

/**
 * wcbs 异常的基类
 *
 * @author wttch
 */
public class WcbsException extends RuntimeException {
  public WcbsException() {}

  public WcbsException(String message) {
    super(message);
  }

  public WcbsException(String message, Throwable cause) {
    super(message, cause);
  }

  public WcbsException(Throwable cause) {
    super(cause);
  }

  public WcbsException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
