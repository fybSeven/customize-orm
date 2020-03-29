package com.lagou.orm.sqlSession;

import com.lagou.orm.config.Configuration;
import com.lagou.orm.config.MapperStatement;
import java.sql.SQLException;
import java.util.List;

public interface Executor {
  <T> List<T> query(Configuration paramConfiguration, MapperStatement paramMapperStatement, Object... param) throws SQLException, Exception;
}