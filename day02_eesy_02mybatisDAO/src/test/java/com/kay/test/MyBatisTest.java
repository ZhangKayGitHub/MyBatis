package com.kay.test;

import com.kay.dao.IUserDao;
import com.kay.dao.UserDaoImpl;
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

/**
 *测试mybatis的CRUD操作
 */
public class MyBatisTest {

    InputStream in = null;
    IUserDao userDao = null;

    /**
     * 初始化操作
     */
    @Before//用于在测试方法之前执行
    public void init() throws Exception{
        //1.读取配置文件生成字节输入流
        in  = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory对象
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂对象，创建dao对象
        userDao = new UserDaoImpl(factory);
    }

    /**
     * 最后执行释放资源的方法
     */
    @After//用于在测试方法之后执行
    public void destory() throws Exception{
        //释放资源
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testfindAll() throws Exception{
        //5.执行查询所有方法
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
        user.setUsername("mybatis impl save");
        user.setAddress("河南郑州管城区");
        user.setSex("男");
        user.setBirthday(new Date());
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
        user.setId(46);
        user.setUsername("mybatis update UserImpl");
        user.setAddress("河南郑州二七区");
        user.setSex("女");
        user.setBirthday(new Date());
        //5.执行保存方法
        userDao.updateUser(user);
    }

    /**
     * 测试删除操作
     */
    @Test
    public void testDelete(){
        //执行删除操作
        userDao.deleteUser(45);
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


}
