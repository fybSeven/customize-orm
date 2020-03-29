package com.lagou.orm.config;

import lombok.Data;

@Data
public class MapperStatement {

  /**
   * namespace
   */
  private String namespace;

  /**
   * sqlid
   */
  private String sqlId;

  /**
   * 返回类型
   */
  private String resultType;

  /**
   * 入参类型
   */
  private String parameterType;

  /**
   * sql
   */
  private String sqlText;

}