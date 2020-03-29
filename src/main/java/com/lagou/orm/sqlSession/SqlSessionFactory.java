package com.lagou.orm.sqlSession;

public interface SqlSessionFactory {

  public abstract SqlSession openSession();
}