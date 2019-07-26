package com.kay.test;

import com.kay.dao.IAccountDao;
import com.kay.dao.IUserDao;
import com.kay.domain.Account;
import com.kay.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class Accountest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IAccountDao accountDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        accountDao = session.getMapper(IAccountDao.class);
    }
    @After
    public void destory()throws Exception{
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testfindAll(){
        List<Account> accounts = accountDao.findAll();
        for(Account account: accounts){
            System.out.println("-----------每个账户的信息-------------");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

}
