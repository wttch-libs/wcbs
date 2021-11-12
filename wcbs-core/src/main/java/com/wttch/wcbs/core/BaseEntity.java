package com.wttch.wcbs.core;

import java.time.LocalDateTime;
import lombok.*;

/**
 * 所有应用实体的基类。
 *
 * <p>包含了主键id，创建时间更新时间等字段。
 *
 * @param <K> 实体的主键id类型
 * @author wttch
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseEntity<K> {
  /** 主键id */
  private K id;

  /** 创建时间 */
  private LocalDateTime createTime;
  /** 更新时间 */
  private LocalDateTime updateTime;

  public void preUpdate() {
    this.updateTime = LocalDateTime.now();
  }
}
