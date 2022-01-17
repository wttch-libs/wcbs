package com.wttch.wcbs.logs;

import com.wttch.wcbs.logs.entity.LogInfo;
import com.wttch.wcbs.logs.entity.LogTemplate;
import com.wttch.wcbs.logs.entity.Logable;

public interface LogListener<T extends Logable, R extends LogTemplate> {
    Class<T> logEntityClass();

    Class<R> logTemplateClass();

    void consume(LogInfo<Logable, LogTemplate> logInfo);
}
