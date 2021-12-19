package com.wttch.wcbs.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wttch.wcbs.web.annotations.RangeSeparator;
import com.wttch.wcbs.web.error.ErrorCode;
import com.wttch.wcbs.web.error.ErrorCodes;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 统一的响应实体类
 *
 * @param <T> 响应内部包含的实体类型
 * @author wttch
 */
@Getter
public class RespWrapper<T> {
  @NotNull private final ErrorCode errorCode;

  @JsonFormat(pattern = "yyyy-'Q'q")
  @RangeSeparator(",")
  private final T data;

  /** 一些附加的数据，比如分页信息等 */
  private final Map<String, Object> meta = new HashMap<>();

  @Nullable private String message;

  public RespWrapper(@NotNull ErrorCode errorCode, T data) {
    this.errorCode = errorCode;
    this.data = data;
  }

  public static <T> RespWrapper<T> ok(T data) {
    return new RespWrapper<>(ErrorCodes.OK, data);
  }

  /**
   * 将键值对数据保存到 meta 中
   *
   * @param key 键
   * @param value 值
   */
  public void putMeta(String key, Object value) {
    this.meta.put(key, value);
  }

  /**
   * 将map中所有的数据添加到 meta 中
   *
   * @param meta 要添加到meta的所有数据
   */
  public void putMeta(Map<String, Object> meta) {
    this.meta.putAll(meta);
  }

  /**
   * 获取错误消息，如果没有则获取 errorCode 的默认消息
   *
   * @return 错误消息，如果没有则获取 errorCode 的默认消息
   */
  public String getMessage() {
    return Optional.ofNullable(message).orElse(errorCode.errorMessage());
  }
}
