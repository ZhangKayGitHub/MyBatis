package com.kay.test;

import com.kay.dao.IUserDao;
import com.kay.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class AnnotationCURDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao userDao;

    @Before
    public void init() throws Exception {
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
    }
    @After
    public void destory()throws Exception{
        session.commit();
        session.close();
        in.close();
    }

    @Test
    public void testfindAll(){
        List<User> users = userDao.findAll();
        for(User user: users){
            System.out.println("-----------每个用户的信息----------");
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }


    @Test
    public void testFindOne(){

        User user = userDao.findById(59);
        System.out.println(user);
    }

    @Test
    public void testFindByName(){
        List<User> users = userDao.findUserByName("%mybatis%");
        for(User user: users){
            System.out.println(user);
        }
    }

}
