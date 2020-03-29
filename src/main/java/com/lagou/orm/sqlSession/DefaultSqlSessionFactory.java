package com.lagou.orm.sqlSession;

import com.lagou.orm.config.Configuration;

public class DefaultSqlSessionFactory implements SqlSessionFactory {
  private Configuration configuration;

  public DefaultSqlSessionFactory(Configuration configuration) {
    this.configuration = configuration;
  }

  @Override
  public SqlSession openSession()
  {
    return new DefaultSqlSession(this.configuration);
  }
}