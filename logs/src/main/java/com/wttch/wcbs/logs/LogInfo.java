package com.wttch.wcbs.logs;

import lombok.*;

/**
 * 保存日志信息的类，包含日志相关的执行时间等，以及扩展的日志的对象，和日志模板。
 *
 * @param <L> 日志对象类型
 * @param <T> 日志模板类型
 * @author wttch
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LogInfo<L extends Logable, T extends LogTemplate> {
  /** 执行时间 */
  private long execTime;
  /** 日志实体对象 */
  private L logable;

  /** 日志模板 */
  private T template;
}
