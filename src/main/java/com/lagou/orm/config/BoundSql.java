package com.lagou.orm.config;

import com.lagou.orm.util.ParameterMapping;
import lombok.Data;

import java.util.List;

@Data
public class BoundSql {
  /**
   * 解析后的sql语句 格式 select * from user where id = ?
   */
  private String sqlText;

  /**
   * 解析后的入参名称 如#{id} 变为id
   */
  private List<ParameterMapping> parameterMappings;

  public BoundSql(String sqlText, List<ParameterMapping> parameterMappings){
    this.sqlText = sqlText;
    this.parameterMappings = parameterMappings;
  }
}