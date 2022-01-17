package com.wttch.wcbs.logs.entity;

import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LogInfo<L extends Logable, T extends LogTemplate> {
  private long execTime;
  private Object extra;
  private L logable;
  private T template;
}
