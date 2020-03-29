package com.lagou.orm.sqlSession;

import com.lagou.orm.util.XmlConfigBuilder;
import java.io.InputStream;

public class SqlSessionFactoryBuild {

  public static SqlSessionFactory build(InputStream inputStream) throws Exception {
    return new DefaultSqlSessionFactory(XmlConfigBuilder.paresConfig(inputStream));
  }
}