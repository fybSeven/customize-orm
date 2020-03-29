package com.lagou.orm.sqlSession;

import com.lagou.orm.config.BoundSql;
import com.lagou.orm.config.Configuration;
import com.lagou.orm.config.MapperStatement;
import com.lagou.orm.util.GenericTokenParser;
import com.lagou.orm.util.ParameterMapping;
import com.lagou.orm.util.ParameterMappingTokenHandler;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class SimpleExecutor implements Executor {

  @Override
  public <T> List<T> query(Configuration configuration, MapperStatement mapperStatement, Object... param) throws Exception{
    Connection connection = configuration.getDataSource().getConnection();
    //解析sql语句
    BoundSql boundSql = parseSql(mapperStatement.getSqlText());
    PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getSqlText());
    //获取入参对象类型
    Class<?> classType = getClassType(mapperStatement.getParameterType());
    List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
    //绑定入参
    if (classType != null) {
      for (int i = 0; i < parameterMappings.size(); i++) {
        Field field = classType.getDeclaredField((parameterMappings.get(i)).getContent());
        field.setAccessible(true);
        Object o = field.get(param[0]);
        preparedStatement.setObject(i + 1, o);
      }
    }
    ResultSet resultSet = preparedStatement.executeQuery();
    //获取返回对象类型
    Class<?> resultClass = getClassType(mapperStatement.getResultType());
    if (resultClass != null) {
      List list = new ArrayList();
      while (resultSet.next()) {
        Object o = resultClass.newInstance();
        ResultSetMetaData metaData = resultSet.getMetaData();
        for (int i = 1; i <= metaData.getColumnCount(); i++)
        {
          String columnName = metaData.getColumnName(i);
          Object value = resultSet.getObject(columnName);
          PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultClass);
          Method writeMethod = propertyDescriptor.getWriteMethod();
          writeMethod.invoke(o, value);
        }
        list.add(o);
      }
      return list;
    }
    return null;
  }

  private Class<?> getClassType(String parameterType) throws ClassNotFoundException {
    System.out.println("入参类型" + parameterType);
    return parameterType == null ? null : Class.forName(parameterType);
  }

  private BoundSql parseSql(String sql)
  {
    ParameterMappingTokenHandler handler = new ParameterMappingTokenHandler();
    GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", handler);
    String sqlText = genericTokenParser.parse(sql);
    System.out.println(String.format("解析出的sql语句为:[%s]", new Object[] { sqlText }));
    return new BoundSql(sqlText, handler.getParameterMappings());
  }
}