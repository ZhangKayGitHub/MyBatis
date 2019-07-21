package com.kay;

import com.kay.dao.IUserDao;
import com.kay.dao.impl.UserDaoImpl;
import com.kay.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * mybatis入门案例
 */
public class MybatisTest {
    public static void main(String[] args) throws Exception {
        //1.读取配置文件
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = factoryBuilder.build(in);
        //3.使用工厂生产SqlSession对象
        //（3.）在测试类里面就没有必要自己拿session了
        //SqlSession session = factory.openSession();

        //3.因此在这里要使用工厂来创建Dao对象
        IUserDao userDao = new UserDaoImpl(factory);//由于该实现了的默认参数被覆盖了因此，需要使用定义的构造函数初始化
        //由于是自己手动实现对IUserDao接口的实现，因此下面代理对象的创建就不需要了
        //4.使用SqlSession创建Dao接口的代理对象
        //IUserDao userDao = session.getMapper(IUserDao.class);

        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users){
            System.out.println(user);
        }
        //6.释放资源
        //由于自己实现Dao的实现类没有用到session因此也就没有session的关闭
        //session.close();
        in.close();
    }

}
