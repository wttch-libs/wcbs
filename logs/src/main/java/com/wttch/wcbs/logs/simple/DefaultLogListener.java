package com.wttch.wcbs.logs.simple;

import com.wttch.wcbs.logs.LogListener;
import com.wttch.wcbs.logs.entity.LogInfo;
import com.wttch.wcbs.logs.entity.LogTemplate;
import com.wttch.wcbs.logs.entity.Logable;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.helpers.MessageFormatter;

@Slf4j
public class DefaultLogListener implements LogListener<DefaultLogable, DefaultLogTemplate> {
  @Override
  public Class<DefaultLogable> logEntityClass() {
    return DefaultLogable.class;
  }

  @Override
  public Class<DefaultLogTemplate> logTemplateClass() {
    return DefaultLogTemplate.class;
  }

  @Override
  public void consume(LogInfo<Logable, LogTemplate> logInfo) {
    DefaultLogable logable = (DefaultLogable) logInfo.getLogable();
    DefaultLogTemplate logTemplate1 = (DefaultLogTemplate) logInfo.getTemplate();

    log.info(
        String.format(
            "【%s】(%s):%s",
            logTemplate1.getAction(),
            MessageFormatter.format(logTemplate1.getOperatorObject(), logable.operatorArgs()).getMessage(),
            MessageFormatter.format(logTemplate1.getInfo(), logable.infoArgs()).getMessage()));
  }
}
