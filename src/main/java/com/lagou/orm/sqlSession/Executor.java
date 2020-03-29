package com.lagou.orm.sqlSession;

import com.lagou.orm.config.Configuration;
import com.lagou.orm.config.MapperStatement;

import java.util.List;

public interface Executor {
  <T> List<T> query(Configuration configuration, MapperStatement mapperStatement, Object... param) throws Exception;
}