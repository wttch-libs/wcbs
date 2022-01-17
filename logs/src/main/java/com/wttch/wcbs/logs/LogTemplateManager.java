package com.wttch.wcbs.logs;

import com.wttch.wcbs.logs.entity.LogTemplate;

import java.util.Map;

public interface LogTemplateManager {
    Map<String, LogTemplate> load();

    LogTemplate getLogTemplate(String key);
}
