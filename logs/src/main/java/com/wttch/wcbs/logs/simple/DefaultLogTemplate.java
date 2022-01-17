package com.wttch.wcbs.logs.simple;

import com.wttch.wcbs.logs.entity.LogTemplate;

public interface DefaultLogTemplate extends LogTemplate {
  @Override
  String getKey();

  void setKey(String key);

  String getAction();

  String getOperatorObject();

  String getInfo();

  class DEFAULT implements DefaultLogTemplate {
    private String key;
    private String action;
    private String operatorObject;
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
    public String getOperatorObject() {
      return operatorObject;
    }

    @Override
    public String getInfo() {
      return info;
    }
  }
}
