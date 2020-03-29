package com.lagou.orm.sqlSession;

import com.lagou.orm.config.Configuration;
import com.lagou.orm.config.MapperStatement;

import java.util.List;

public class DefaultSqlSession implements SqlSession {

  private Configuration configuration;

  public DefaultSqlSession(Configuration configuration)
  {
    this.configuration = configuration;
  }

  @Override
  public <T> List<T> findAll(String statementId, Object[] param) throws Exception {
    Executor executor = new SimpleExecutor();
    return executor.query(this.configuration, (MapperStatement)this.configuration.getMapperStatementMap().get(statementId), param);
  }

  @Override
  public <T> T findOne(String statementId, Object[] param) throws Exception {
    return (T) findAll(statementId, param).get(0);
  }
}