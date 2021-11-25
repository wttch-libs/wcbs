package com.wttch.wcbs.web.error;

import com.wttch.wcbs.core.exception.FrameworkException;
import org.jetbrains.annotations.Nullable;

class LeveledErrorCode extends ErrorCode {
  private static final int MAX_LEVEL = 100;
  private static final int CODE_STEP = 100;

  protected LeveledErrorCode(
      ErrorCodeSource source, int topLevel, int errorCode, @Nullable String errorMessage) {
    super(String.format("%c%02d%02d", source.getCode(), topLevel, errorCode), errorMessage);
  }

  static LeveledErrorCode of(
      ErrorCodeSource source, int topLevel, int errorCode, @Nullable String errorMessage) {
    if (topLevel < 0 || topLevel >= MAX_LEVEL || errorCode < 0 || errorCode >= CODE_STEP) {
      throw new FrameworkException(
          String.format("错误码格式错误: topLevel:%d,errorCode:%d", topLevel, errorCode));
    }
    return new LeveledErrorCode(source, topLevel, errorCode, errorMessage);
  }
}
