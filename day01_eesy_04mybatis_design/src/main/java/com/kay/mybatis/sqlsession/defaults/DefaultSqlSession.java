package com.kay.mybatis.sqlsession.defaults;

import com.kay.mybatis.cfg.Configuration;
import com.kay.mybatis.sqlsession.SqlSession;
import com.kay.mybatis.sqlsession.proxy.MapperProxy;
import com.kay.mybatis.utils.DataSourceUtil;

import javax.sql.DataSource;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * SqlSession接口的实现类
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;
    private  Connection connection;

    public DefaultSqlSession(Configuration cfg){
        this.cfg = cfg;
        connection = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 用于创建代理对象
     * @param daoInterfaceClass     dao的接口字节码
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        //代理对象
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),connection));

    }

    /**
     * 用于释放资源
     */
    public void close() {
        if(connection != null)
        try {
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
