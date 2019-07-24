package com.kay.test;

import com.kay.dao.IAccountDao;

import com.kay.domain.Account;
import com.kay.domain.AccountUser;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 *测试mybatis的CRUD操作
 */
public class AccountTest {

    InputStream in = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    IAccountDao accountDao = null;

    /**
     * 初始化操作
     */
    @Before//用于在测试方法之前执行
    public void init() throws Exception{
        //1.读取配置文件生成字节输入流
        in  = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory对象
        factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    /**
     * 最后执行释放资源的方法
     */
    @After//用于在测试方法之后执行
    public void destory() throws Exception{
        //提交事务
        //由于初始化的时候已经sqlSession = factory.openSession(true)，
        // 设置 成为自动提交了因此在这里就不需要手动提交了
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    @Test
    public void testfindAll() throws Exception{
        List<Account> accounts = accountDao.findAll();
        for(Account account : accounts){
            System.out.println("---------一个Account的信息----------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

    /**
     * 测试查询所有账户及账户对应的用户姓名和住址
     */
    @Test
    public void testfindAllAccount() throws Exception{
        List<AccountUser> accountUsers = accountDao.findAllAccount();
        for(AccountUser accountuser : accountUsers){
            System.out.println(accountuser);
        }
    }
}
