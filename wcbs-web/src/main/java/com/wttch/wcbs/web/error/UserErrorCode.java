package com.wttch.wcbs.web.error;

import org.jetbrains.annotations.Nullable;

/**
 * 前端用户错误
 *
 * @author wttch
 */
class UserErrorCode extends LeveledErrorCode {
  UserErrorCode(int topLevel, int errorCode) {
    this(topLevel, errorCode, null);
  }

  UserErrorCode(int topLevel, int errorCode, @Nullable String errorMessage) {
    super(ErrorCodeSource.USER, topLevel, errorCode, errorMessage);
  }

  static UserErrorCode of(int topLevel, int errorCode) {
    return new UserErrorCode(topLevel, errorCode);
  }
}
