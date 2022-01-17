package com.wttch.wcbs.logs.entity;

import java.util.List;

/**
 * 标识可以进行日志记录（混入日志相关的数据信息）的。
 *
 * <p>可以实现该接口来标识返回结果可以被日志解析，需要配合{@link com.wttch.wcbs.logs.annotations.Log}注解使用，
 * 切面是在注解层面，同时返回值必须为该类型的实现类才可以。
 *
 * <p>一般项目函数都有自己特定的返回值，为了不影响正常的返回类型，提供了混入工具，来混入日志对象。例如 可以使用{@link
 * com.wttch.wcbs.logs.util.LogUtils#minxinLogable(Object, List, List)}来混入{@link
 * com.wttch.wcbs.logs.simple.DefaultLogable.DEFAULT}，这样切面就可以只关心{@link Logable}相关的信息而不影响实际的函数返回类型。
 *
 * <p>通过该接口和{@link LogTemplate}来生成具体的日志信息对象 {@link LogInfo}, 让你的日志完全独立并且可以很好的扩展。
 *
 * @author wttch
 */
public interface Logable {}
