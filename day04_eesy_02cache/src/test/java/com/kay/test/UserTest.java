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
import java.util.List;

/**
 *测试mybatis的CRUD操作
 */
public class UserTest {

    InputStream in = null;
    SqlSessionFactory factory = null;
    SqlSession sqlSession = null;
    IUserDao userDao = null;

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
        userDao = sqlSession.getMapper(IUserDao.class);
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
    public void testfirstlevelCache(){
        User user1 = userDao.findById(41);
        System.out.println(user1);
//        sqlSession.close();
//        //再次获取sqlSession对象
//        sqlSession = factory.openSession();
        sqlSession.clearCache();//此方法可以清空缓存
        userDao = sqlSession.getMapper(IUserDao.class);
        User user2 = userDao.findById(41);
        System.out.println(user2);
        System.out.println(user1 == user2);

    }

    /**
     * 测试缓存的同步
     */
    @Test
    public void testClearCache(){
        /**
         * 一级缓存是SqlSession范围的缓存，当调用SqlSession的修改，添加，删除，commit(),close()等方法时，就会清空一级缓存
         */

        //1.根据id查询用户
        User user1 = userDao.findById(41);
        System.out.println(user1);

        //2.更新用户信息
        user1.setUsername("Update user clear cache");
        user1.setAddress("河南郑州市");
        userDao.updateUser(user1);
        //3.再次查询id为41的用户9
        User user2 = userDao.findById(41);
        System.out.println(user2);
        System.out.println(user1 == user2);

    }


}
