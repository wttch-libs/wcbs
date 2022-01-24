package com.wttch.wcbs.logs.logs;

import com.wttch.wcbs.logs.LogInfo;
import com.wttch.wcbs.logs.LogListener;
import com.wttch.wcbs.logs.LogTemplate;
import com.wttch.wcbs.logs.Logable;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

/**
 * {@link OperatorLogable} 和 {@link OperatorLogTemplate} 默认的日志格式化和消费类
 *
 * @author wttch
 */
@Slf4j
public class OperatorLogListener implements LogListener<OperatorLogable, OperatorLogTemplate> {
  /**
   * logable 的具体类型，保存关系使用
   *
   * @return Logable 的子类型
   */
  @Override
  public Class<OperatorLogable> logEntityClass() {
    return OperatorLogable.class;
  }

  /**
   * LogTemplate 的具体类型, 保存关系使用
   *
   * @return LogTemplate 的子类型
   */
  @Override
  public Class<OperatorLogTemplate> logTemplateClass() {
    return OperatorLogTemplate.class;
  }

  /**
   * 处理和消费日志
   *
   * @param logInfo 日志的具体信息，包含具体的执行方法，时间，日志模板和参数等
   */
  @Override
  public void consume(LogInfo<Logable, LogTemplate> logInfo) {
    OperatorLogable logable = (OperatorLogable) logInfo.getLogable();
    OperatorLogTemplate logTemplate1 = (OperatorLogTemplate) logInfo.getTemplate();

    log.info(
        String.format(
            "【%s】(%s):%s",
            logTemplate1.getAction(),
            MessageFormatter.format(logTemplate1.getOperator(), logable.operatorArgs())
                .getMessage(),
            MessageFormatter.format(logTemplate1.getInfo(), logable.infoArgs()).getMessage()));
  }
}
