package com.wttch.wcbs.core;

import java.util.List;
import org.jetbrains.annotations.Nullable;

/**
 * 通用的 crud DAO 接口，给 Mybatis Mapper 继承使用，所有的 Mapper 都应该继承该接口并编写相应的 mapper.xml 文件
 *
 * @param <T> 表关联的实体类型
 * @param <K> 主键类型
 * @author wttch
 */
public interface CrudMapper<T extends BaseEntity<K>, K> {

  /**
   * 根据主键删除，一般都是id
   *
   * @param key 要删除的实体的主键信息
   * @return 删除影响的条目数
   */
  int deleteByPrimaryKey(K key);

  /**
   * 插入实体，直接插入，会按逻辑自动生成主键
   *
   * @param entity 要存储的实体数据
   * @return 添加成功的条目数
   */
  int insert(T entity);

  /**
   * 根据主键查找，返回主键等于给定值的实体，可能会出现返回值为空的情况
   *
   * @param key 主键值
   * @return 如果主键关联的实体存在则返回该实体，否则 null
   */
  @Nullable
  T selectByPrimaryKey(K key);

  /**
   * 根据主键 id 更新数据，更新所有的字段信息，如果将某条字段设置为 null， 则会影响数据库条目，也会被 更新为 null.
   *
   * @param entity 要更新的实体信息
   * @return 更新影响的条目数
   */
  int updateByPrimaryKey(T entity);

  /**
   * 批量更新，根据id对数据进行批量更新，更新逻辑和 {@link #updateByPrimaryKey(BaseEntity)} 一致, 如果出现空则也会被直接更新替换.
   *
   * <p>注意，如果列表为空，可能拼出错误的 sql
   *
   * @param list 要更新的数据列表信息
   * @return 批量更新影响的数据条目数
   */
  int updateBatch(List<T> list);

  /**
   * 批量插入
   *
   * <p>如果列表为空，则可能拼出错误的 sql
   *
   * @param list 要插入的数据列表
   * @return 批量插入影响的数据条目数
   */
  int batchInsert(List<T> list);

  /**
   * 根据主键列表查询，查询所有主键在指定列表里的数据
   *
   * @param list 要查询的主键列表
   * @return 满足条件的实体列表
   */
  List<T> findByIds(List<K> list);
}
