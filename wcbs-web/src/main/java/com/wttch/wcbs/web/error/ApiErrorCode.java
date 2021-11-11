package com.wttch.wcbs.web.error;

import org.jetbrains.annotations.Nullable;

/**
 * 第三方api调用错误
 *
 * @author wttch
 */
class ApiErrorCode extends LeveledErrorCode {
  ApiErrorCode(int topLevel, int errorCode) {
    this(topLevel, errorCode, null);
  }

  ApiErrorCode(int topLevel, int errorCode, @Nullable String errorMessage) {
    super(ErrorCodeSource.API, topLevel, errorCode, errorMessage);
  }

  static ApiErrorCode of(int topLevel, int errorCode) {
    return new ApiErrorCode(topLevel, errorCode);
  }
}
