package test.a;

import com.wttch.wcbs.logs.logs.OperatorLogTemplate;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LogInfo implements OperatorLogTemplate {
  /** 测试 */
  TEST("test", "测试", "测试{}", "测试log「{}」-「{}」");

  private String key;
  private String action;
  private String operatorObject;
  private String info;

  @Override
  public String getKey() {
    return key;
  }

  @Override
  public void setKey(String key) {}

  @Override
  public String getAction() {
    return action;
  }

  @Override
  public String getOperator() {
    return operatorObject;
  }

  @Override
  public String getInfo() {
    return info;
  }
}
