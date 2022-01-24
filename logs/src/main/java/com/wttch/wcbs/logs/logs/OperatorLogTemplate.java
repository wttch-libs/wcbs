package com.wttch.wcbs.logs.logs;

import com.wttch.wcbs.logs.LogTemplate;

/**
 * 一种打印操作的日志的模板实现，主要保存了操作动作、未经过格式化的操作模板和未经格式化的日志内容，配合 {@link OperatorLogable} 来实现日志的具体格式化。
 *
 * @author wttch
 */
public interface OperatorLogTemplate extends LogTemplate {

  /**
   * 获取模版主键
   *
   * @return 模版主键
   */
  @Override
  String getKey();

  @Override
  default Class<OperatorLogTemplate> templateClass() {
    return OperatorLogTemplate.class;
  }

  /**
   * 设置模板的主键，主键是区分模板的主要字段
   *
   * @param key 要设置的主键的值
   */
  void setKey(String key);

  /**
   * 获取操作的实际动作名称，日志打印时使用，无需格式化
   *
   * @return 操作的实际动作名称
   */
  String getAction();

  /**
   * 获取操作模板，配合{@link OperatorLogable#operatorArgs()}来格式化内容
   *
   * @return 操作模板
   */
  String getOperator();

  /**
   * 获取日志内容模板，配合{@link OperatorLogable#infoArgs()}来格式化日志内容
   *
   * @return 日志内容模板
   */
  String getInfo();

  /** 接口的默认实现类 */
  class DEFAULT implements OperatorLogTemplate {
    private String key;
    private String action;
    private String operator;
    private String info;

    @Override
    public String getKey() {
      return key;
    }

    @Override
    public void setKey(String key) {
      this.key = key;
    }

    @Override
    public String getAction() {
      return action;
    }

    @Override
    public String getOperator() {
      return operator;
    }

    @Override
    public String getInfo() {
      return info;
    }
  }
}
