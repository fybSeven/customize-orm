package com.lagou.orm.sqlSession;

import java.sql.SQLException;
import java.util.List;

public interface SqlSession {

  <T> List<T> findAll(String paramString, Object[] paramArrayOfObject) throws SQLException, Exception;

  <T> T findOne(String paramString, Object[] paramArrayOfObject) throws Exception;
}