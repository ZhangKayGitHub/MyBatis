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

/**
 *测试mybatis的CRUD操作
 */
public class SessionLevelCacheTest {

    InputStream in = null;
    SqlSessionFactory factory = null;

    /**
     * 初始化操作
     */
    @Before//用于在测试方法之前执行
    public void init() throws Exception{
        //1.读取配置文件生成字节输入流
        in  = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory对象
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    /**
     * 最后执行释放资源的方法
     */
    @After//用于在测试方法之后执行
    public void destory() throws Exception{
        in.close();
    }

    @Test
    public void testfirstlevelCache(){
        /**
         * 二级缓存：
         *      它指的是Mybatis中SqlSessionFactory对象的缓存，是由同一个SqlSessionFactory对象创建的SqlSession共享其缓存
         *      二级缓存的使用步骤：
         *          第一步：让Mybatis框架支持二级缓存(再SqlMapConfig.xml中配置<settings>
         *         <setting name="cacheEnabled" value="true"/>
         *     </settings>)
         *          第二步：让当前的映射文件支持二级缓存（在IUserDao.xml中配置<cache/>）
         *          第三步：让当前的此操作支持二级缓存（在select标签中配置useCache="true"）
         *          <select id="findById" parameterType="INT" resultType="user" useCache="true">
         */
        SqlSession sqlSession1 = factory.openSession();
        IUserDao dao1 = sqlSession1.getMapper(IUserDao.class);
        User user1 = dao1.findById(41);
        System.out.println(user1);
        sqlSession1.close();//因为这时他会一级缓存消失

        SqlSession sqlSession2 = factory.openSession();
        IUserDao dao2 = sqlSession2.getMapper(IUserDao.class);
        User user2 = dao2.findById(41);
        System.out.println(user2);
        sqlSession2.close();
        //二级缓存中存放的是数据而不是对象因此此处为false
        System.out.println(user1 == user2);

    }


}
