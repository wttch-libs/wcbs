package com.wttch.wcbs.logs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wttch.wcbs.logs.logs.OperatorLogTemplate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;

/**
 * json 类型的日志管理器的实现
 *
 * <p>从指定的资源中加载日志模板，日志模板具体字段请参见 {@code OperatorLogTemplate#DEFAULT}
 *
 * <p>TODO 做成通用，而非仅仅 OperatorLogTemplate
 *
 * @author wttch
 */
public class JsonDefaultLogTemplateManager extends AbstractLogTemplateManager {
  private Resource resource;

  public JsonDefaultLogTemplateManager(Resource resource) {
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
      var node = objectMapper.treeToValue(field.getValue(), OperatorLogTemplate.DEFAULT.class);
      node.setKey(field.getKey());
      logTemplateList.add(node);
    }
    return logTemplateList.stream()
        .collect(Collectors.toMap(LogTemplate::getKey, Function.identity()));
  }
}
