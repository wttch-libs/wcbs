package com.wttch.wcbs.core;

import com.wttch.common.util.CollectionUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

/**
 * curd 通用的抽象 service， 会自动注入指定的 DAO/Mapper 信息，但是该 DAO/Mapper 必须继承 {@link CrudDAO}. 同时为了方便操作，
 * 实体对象也必须要继承 {@link BaseEntity}.
 *
 * <p>此类主要是对dao层的一个简单的封装。
 *
 * @param <DAO> service 关联的 DAO/Mapper 类型
 * @param <T> service/DAO/Mapper 关联的实体类型
 * @param <ID> service/DAO/Mapper 关联的实体的主键类型
 */
public abstract class CrudService<DAO extends CrudDAO<T, ID>, T extends BaseEntity<T, ID>, ID> {

  /** dao/Mapper 对象 */
  @Resource protected DAO dao;

  /**
   * 根据主键 id 删除
   *
   * @param id 要删除的实体的主键id信息
   * @return 删除影响的条目数
   */
  public int delete(ID id) {
    return dao.deleteByPrimaryKey(id);
  }

  /**
   * 删除实体，获取实体的主键 id , 然后根据主键 id 进行删除.
   *
   * @param entity 要删除的实体信息
   * @return 删除影响的数据条目数
   */
  public int delete(T entity) {
    return dao.deleteByPrimaryKey(entity.getId());
  }

  /**
   * 插入实体，直接插入，会按逻辑自动生成主键
   *
   * @param entity 要存储的实体数据
   * @return 添加成功的条目数
   */
  public int insert(T entity) {
    return dao.insert(entity);
  }

  /**
   * 根据主键 id 查询, 由于查询结果可能为空, 所以将返回值包装成 Optional 方便进行判断.
   *
   * @param id 要查询的主键 id
   * @return 查询实体结果的 Optional 形式
   */
  public Optional<T> findById(ID id) {
    return Optional.ofNullable(dao.selectByPrimaryKey(id));
  }

  /**
   * 根据主键 id, 更新实体信息, 为空的字段将会被一起更新到数据库中.
   *
   * @param entity 要更新实体信息
   * @return 更新操作影响的实际条目数量
   */
  public int update(T entity) {
    return dao.updateByPrimaryKey(entity);
  }

  /**
   * 批量更新, 对dao的批量更新进行简单的包装，如果数据为空则直接返回, 防止拼接出错误的 sql.
   *
   * @param list 要更新的数据列表
   * @return 更新影响的实际条目数量
   */
  public int updateAll(List<T> list) {
    if (CollectionUtils.isEmpty(list)) {
      return 0;
    }
    return dao.updateBatch(list);
  }

  /**
   * 批量插入, 对 dao 的批量插入进行简单的包装, 如果数据为空则直接返回, 防止拼接出错误的 sql.
   *
   * @param list 要批量插入的数据列表
   * @return 插入操作实际影响的数据条目数量
   */
  public int insertAll(List<T> list) {
    if (CollectionUtils.isEmpty(list)) {
      return 0;
    }
    return dao.batchInsert(list);
  }

  /**
   * 添加或者更新, 如果实体已经存在则更新, 否则插入
   *
   * @param entity 要保存或者更新的实体信息
   */
  public void saveOrUpdate(T entity) {
    if (findById(entity.getId()).isPresent()) {
      // 更新
      dao.updateByPrimaryKey(entity);
    } else {
      // 新增
      dao.insert(entity);
    }
  }

  /**
   * 批量添加或者更新, 如果实体已经存在则更新, 否则插入
   *
   * @param entities 要保存或者更新的实体列表信息
   */
  @Transactional
  public void saveOrUpdateAll(List<T> entities) {
    var existsIds = entities.stream().map(T::getId).collect(Collectors.toList());
    var existsEntities = dao.findByIds(existsIds);
    entities.removeAll(existsEntities);
    updateAll(existsEntities);
    insertAll(entities);
  }
}
