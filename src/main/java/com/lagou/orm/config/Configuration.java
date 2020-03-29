package com.lagou.orm.config;

import lombok.Data;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Data
public class Configuration {

  /**
   * 将解析出来的SqlMapConfig.xml文件封装为数据源
   */
  private DataSource dataSource;

  /**
   * 每条sql解析为一个MapperStatement key为namespace.id  com.lagou.orm.userDao.findAll
   */
  Map<String, MapperStatement> mapperStatementMap = new HashMap();

  public DataSource getDataSource() {
    return this.dataSource;
  }

  public Map<String, MapperStatement> getMapperStatementMap() {
    return this.mapperStatementMap;
  }

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void setMapperStatementMap(Map<String, MapperStatement> mapperStatementMap) {
    this.mapperStatementMap = mapperStatementMap;
  }
}