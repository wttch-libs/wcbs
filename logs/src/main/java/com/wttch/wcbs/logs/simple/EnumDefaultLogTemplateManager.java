package com.wttch.wcbs.logs.simple;

import com.wttch.wcbs.logs.AbstractLogTemplateManager;
import com.wttch.wcbs.logs.entity.LogTemplate;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EnumDefaultLogTemplateManager extends AbstractLogTemplateManager {
  private Class<? extends DefaultLogTemplate> clazz;

  public EnumDefaultLogTemplateManager(Class<? extends DefaultLogTemplate> clazz) {
    this.clazz = clazz;
    loadData();
  }

  @Override
  public Map<String, LogTemplate> load() {
    return Arrays.stream(clazz.getEnumConstants())
        .collect(Collectors.toMap(DefaultLogTemplate::getKey, Function.identity()));
  }
}
