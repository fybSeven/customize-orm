package com.lagou.orm.sqlSession;

import com.lagou.orm.config.Configuration;

import java.lang.reflect.*;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

  private Configuration configuration;

  public DefaultSqlSession(Configuration configuration)
  {
    this.configuration = configuration;
  }

  @Override
  public <T> List<T> findAll(String statementId, Object... param) throws Exception {
    Executor executor = new SimpleExecutor();
    return executor.query(this.configuration, this.configuration.getMapperStatementMap().get(statementId), param);
  }

  @Override
  public <T> T findOne(String statementId, Object... param) throws Exception {
    return (T) findAll(statementId, param).get(0);
  }

  @Override
  public <T> T getMapper(Class<T> typeClass) {
    System.out.println(typeClass.getName());
    Object o = Proxy.newProxyInstance(typeClass.getClassLoader(), new Class[]{typeClass}, new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String statementId = typeClass.getName() + "." + method.getName();
        System.out.println("statementId: " + statementId);
        // 准备参数2：params:args
        // 获取被调用方法的返回值类型
        Type genericReturnType = method.getGenericReturnType();
        // 判断是否进行了 泛型类型参数化
        if(genericReturnType instanceof ParameterizedType){
          List<Object> objects = findAll(statementId, args);
          return objects;
        }
        return findOne(statementId,args);
      }
    });
    return (T) o;
  }
}