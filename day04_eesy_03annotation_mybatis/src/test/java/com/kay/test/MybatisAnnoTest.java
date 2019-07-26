package com.kay.test;

import com.kay.dao.IUserDao;
import com.kay.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;
import java.util.List;

public class MybatisAnnoTest {
    /**
     * 测试基于注解的mybatis的使用
     * @param args
     */
    public static void main(String[] args) throws Exception {
        //1.获取字节输入流
        //InputStream in = Resources.class.getClassLoader().getResourceAsStream("sqlMapConfig.xml");
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        //2.根据字节输入流构建SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.根据SqlSessionFactory生产一个SqlSession
        SqlSession sqlSession = factory.openSession();
        //4.使用SqlSession获取Dao的代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        //5.执行Dao方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
        //6.释放资源;
        sqlSession.close();
        in.close();
    }
}
