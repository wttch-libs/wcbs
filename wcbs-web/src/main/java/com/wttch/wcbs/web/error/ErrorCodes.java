package com.wttch.wcbs.web.error;

/**
 * 错误代码集合
 *
 * @author wttch
 */
public class ErrorCodes {
  private ErrorCodes() {}

  public static final ErrorCode OK = new ErrorCode("00000");

  public static final ErrorCode USER_ERROR = UserErrorCode.of(0, 0);

  public static final ErrorCode REGISTER_ERROR = UserErrorCode.of(1, 0);

  public static final ErrorCode USERNAME_CHECK_ERROR = UserErrorCode.of(1, 10);

  public static final ErrorCode SYSTEM_ERROR = SystemErrorCode.of(0, 0);

  public static final ErrorCode API_ERROR = ApiErrorCode.of(0, 0);
}
