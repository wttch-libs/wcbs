package com.wttch.wcbs.logs.simple;

import com.wttch.wcbs.logs.entity.Logable;
import lombok.AllArgsConstructor;

import java.util.List;

public interface DefaultLogable extends Logable {
  Object result();

  List<Object> operatorArgs();

  List<Object> infoArgs();

  static DEFAULT of(Object result, List<Object> opeartorArgs, List<Object> infoArgs) {
    return new DEFAULT(result, opeartorArgs, infoArgs);
  }

  @AllArgsConstructor
  class DEFAULT implements DefaultLogable {
    private Object result;
    private List<Object> operatorArgs;
    private List<Object> infoArgs;

    @Override
    public Object result() {
      return result;
    }

    @Override
    public List<Object> operatorArgs() {
      return operatorArgs;
    }

    @Override
    public List<Object> infoArgs() {
      return infoArgs;
    }
  }
}
