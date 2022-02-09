package com.wttch.common.exception;

/** @author wttch */
public class WttchException extends RuntimeException {
  public WttchException() {}

  public WttchException(String message) {
    super(message);
  }

  public WttchException(String message, Throwable cause) {
    super(message, cause);
  }

  public WttchException(Throwable cause) {
    super(cause);
  }

  public WttchException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
