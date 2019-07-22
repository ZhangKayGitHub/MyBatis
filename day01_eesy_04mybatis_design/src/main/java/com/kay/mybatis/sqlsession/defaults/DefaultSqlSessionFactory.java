package com.kay.mybatis.sqlsession.defaults;

import com.kay.mybatis.cfg.Configuration;
import com.kay.mybatis.sqlsession.SqlSession;
import com.kay.mybatis.sqlsession.SqlSessionFactory;

/**
 * SqlSessionFactory的实现类
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration cfg;

    public DefaultSqlSessionFactory(Configuration cfg){
        this.cfg = cfg;
    }

    /**
     * 用于创建一个操作数据库的对象
     * @return
     */
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg);
    }
}
