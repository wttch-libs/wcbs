package com.wttch.wcbs.web.error;

import org.jetbrains.annotations.Nullable;

/**
 * 内部系统错误
 *
 * @author wttch
 */
class SystemErrorCode extends LeveledErrorCode {

  SystemErrorCode(int topLevel, int errorCode) {
    this(topLevel, errorCode, null);
  }

  SystemErrorCode(int topLevel, int errorCode, @Nullable String errorMessage) {
    super(ErrorCodeSource.SYSTEM, topLevel, errorCode, errorMessage);
  }

  static SystemErrorCode of(int topLevel, int errorCode) {
    return new SystemErrorCode(topLevel, errorCode);
  }
}
