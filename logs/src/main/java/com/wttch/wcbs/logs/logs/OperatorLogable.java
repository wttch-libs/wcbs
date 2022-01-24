package com.wttch.wcbs.logs.logs;

import com.wttch.wcbs.logs.Logable;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * Logable的一种实现，主要配合 {@link OperatorLogTemplate}
 * 使用，他定义了一种简单的log模型：操作动作、可以自定义格式化的操作以及可以自定义格式化的具体日志内容。
 *
 * <p>该接口主要保存了要自定格式化的数据，因为该接口是作为混入对象使用，所以只能是接口。
 *
 * @author wttch
 */
public interface OperatorLogable extends Logable {
  /**
   * 获取切面实际返回的对象
   *
   * @return 切面实际返回的对象
   */
  Object result();

  /**
   * 获取日志操作的具体参数，配合 {@link OperatorLogTemplate#getOperator()} 来实现操作的格式化。
   *
   * @return 操作的具体参数
   */
  List<Object> operatorArgs();

  /**
   * 获取日志内容的具体参数，配合 {@link OperatorLogTemplate#getInfo()} 来实现格式化。
   *
   * @return 日志内容的具体参数
   */
  List<Object> infoArgs();

  /**
   * 使用给定的参数来返回一个接口默认实现的对象。
   *
   * @param result 切面函数实际返回的对象
   * @param operatorArgs 操作格式化参数
   * @param infoArgs 日志内容格式化参数
   * @return 接口的默认实现的实例对象
   */
  static DEFAULT of(Object result, List<Object> operatorArgs, List<Object> infoArgs) {
    return new DEFAULT(result, operatorArgs, infoArgs);
  }

  /** 接口的默认实现 */
  @AllArgsConstructor
  class DEFAULT implements OperatorLogable {
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
