package com.wttch.wcbs.logs.simple;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wttch.wcbs.logs.AbstractLogTemplateManager;
import com.wttch.wcbs.logs.entity.LogTemplate;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class JsonEnumDefaultLogTemplateManager extends AbstractLogTemplateManager {
  private Resource resource;

  public JsonEnumDefaultLogTemplateManager(Resource resource) {
    this.resource = resource;
    loadData();
  }

  @SneakyThrows
  @Override
  public Map<String, LogTemplate> load() {
    ObjectMapper objectMapper = new ObjectMapper();
    var root = objectMapper.readTree(resource.getFile());
    List<LogTemplate> logTemplateList = new LinkedList<>();
    var fields = root.fields();
    while (fields.hasNext()) {
      var field = fields.next();
      var node = objectMapper.treeToValue(field.getValue(), DefaultLogTemplate.DEFAULT.class);
      node.setKey(field.getKey());
      logTemplateList.add(node);
    }
    return logTemplateList.stream()
        .collect(Collectors.toMap(LogTemplate::getKey, Function.identity()));
  }
}
