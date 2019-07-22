package com.kay.mybatis.sqlsession;

import com.kay.mybatis.cfg.Configuration;
import com.kay.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.kay.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 用于创建一个SqlSessionFactory对象
 */
public class SqlSessionFactoryBuilder {
    /**
     *
     * 根据参数的字节输入构建一个SqlSessionFactory对象
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config){
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}
