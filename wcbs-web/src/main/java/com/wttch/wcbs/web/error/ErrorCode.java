package com.wttch.wcbs.web.error;

import com.wttch.wcbs.core.exception.FrameworkException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 简单的错误代码实现, 可以直接使用错误代码和错误描述定义错误码.
 *
 * @author wttch
 */
public class ErrorCode {
  @NotNull private final String code;
  @Nullable private final String message;

  protected static final List<ErrorCode> ERROR_CODE_LIST = new LinkedList<>();

  ErrorCode(@NotNull String code, @Nullable String message) {
    this.code = code;
    this.message = message;
    if (ERROR_CODE_LIST.contains(this)) {
      throw new FrameworkException(String.format("错误码重复，请检查并修改. %s", code));
    } else {
      ERROR_CODE_LIST.add(this);
    }
  }

  ErrorCode(@NotNull String errorCode) {
    this(errorCode, null);
  }

  @NotNull
  public String errorCode() {
    return code;
  }

  @Nullable
  public String errorMessage() {
    return message;
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }

  @Override
  public boolean equals(Object obj) {
    if (Objects.isNull(obj)) {
      return false;
    }

    if (obj instanceof ErrorCode && Objects.equals(errorCode(), ((ErrorCode) obj).errorCode())) {
      return true;
    }

    return obj instanceof String && Objects.equals(errorCode(), obj);
  }
}
