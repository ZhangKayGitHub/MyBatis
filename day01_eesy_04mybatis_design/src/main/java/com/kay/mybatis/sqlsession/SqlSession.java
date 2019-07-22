package com.kay.mybatis.sqlsession;

/**
 * 自定义Mybatis 和数据库交互的核心类
 * 它里面可以创建dao接口的代理对象
 */
public interface SqlSession {


    /**
     * 根究参数创建一个代理对象
     * @param daoInterfaceClass     dao的接口字节码
     * @param <T>
     * @return
     *泛型要求时先声明在使用所以在 T 的前面又添加了一个 <T>
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 释放资源
     */
    void close();
}
