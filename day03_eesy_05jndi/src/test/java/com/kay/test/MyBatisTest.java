package com.kay.test;

import com.kay.dao.IUserDao;
import com.kay.domain.QueryVo;
import com.kay.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 *测试mybatis的CRUD操作
 */
public class MyBatisTest {

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
        //
        //2.获取SqlSessionFactory对象
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        /**
         * mybatis中的事务：
         *      事务的四大特征ACID
         *      不考虑隔离性会产生3个问题
         *      解决办法四种隔离级别
         *
         *      它通过sqlSession对象的commit方法和rollback方法实现事务的提交和回滚
         */
        //当我们在openSession()的参数是true我们在后边就不需要再手动的提交了，mybatis会自动的提交
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

    /**
     * 测试查询所有
     */
    @Test
    public void testfindAll() throws Exception{
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }

    /**
     * 保存操作
     */
    @Test
    public void testSave(){
        User user = new User();
        user.setUsername("mybatis autoCommit");
        user.setUseraddress("河南郑州惠济区");
        user.setUsersex("男");
        user.setUserbirthday(new Date());
        //保存操作之前
        System.out.println("保存操作之前：" + user);
        //5.执行保存方法
        userDao.saveUser(user);
//      //提交事务
//        sqlSession.commit();
        System.out.println("保存操作之后：" + user);
    }

    /**
     * 测试更新操作
     */
    @Test
    public void testUpdate(){
        User user = new User();
        user.setUserid(50);
        user.setUsername("mybatis update User");
        user.setUseraddress("河南郑州二七区");
        user.setUsersex("女");
        user.setUserbirthday(new Date());
        //5.执行保存方法
        userDao.updateUser(user);
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDelete(){
        //执行删除操作
        userDao.deleteUser(48);
    }

    /**
     * 测试通过id查询一个
     */
    @Test
    public void testFindOne(){
        //执行查询一个操作
        User user = userDao.findById(50);
        System.out.println(user);
    }

    /**
     * 测试模糊查询
     */
    @Test
    public void testfindByName(){
        List<User> users = userDao.findByName("%王%");
        //由于我们在IUserDao.xml中已经在 select * from user where username like '%${value}%'
        // 提供了%号所以这里就不再需要提供了List<User> users = userDao.findByName("王");
        for (User user : users){
            System.out.println(user);
        }
    }

    /**
     * 测试聚合函数，查询数据表中的总记录数
     */
    @Test
    public void testfindTotal(){
        Integer total = userDao.findTotal();
        System.out.println(total);
    }

    /**
     * 测试使用QueryVo对象作为查询条件
     */
    @Test
    public void testfindByVo(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);
        List<User> users = userDao.findUserByVo(vo);
        //由于我们在IUserDao.xml中已经在 select * from user where username like '%${value}%'
        // 提供了%号所以这里就不再需要提供了List<User> users = userDao.findByName("王");
        for (User u : users){
            System.out.println(u);
        }
    }
}
