package com.lagou.orm.sqlSession;

import java.sql.SQLException;
import java.util.List;

public interface SqlSession {

  <T> List<T> findAll(String paramString, Object... param) throws SQLException, Exception;

  <T> T findOne(String paramString, Object... param) throws Exception;

  <T> T getMapper(Class<T> typeClass);
}