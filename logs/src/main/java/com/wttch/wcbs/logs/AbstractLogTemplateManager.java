package com.wttch.wcbs.logs;

import com.wttch.wcbs.logs.entity.LogTemplate;

import java.util.Map;

public abstract class AbstractLogTemplateManager implements LogTemplateManager{

    private Map<String, LogTemplate> logTemplateMap;

    protected void loadData(){
        logTemplateMap = load();
    }

    @Override
    public LogTemplate getLogTemplate(String key) {
        return logTemplateMap.get(key);
    }
}
