package com.kay.dao.impl;

import com.kay.dao.IUserDao;
import com.kay.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    //定义一个能拿到session对象的工厂
    private SqlSessionFactory factory;
    //怎么确保factory有值呢？只需要在该类被创建的时候传值就可以（也就是在构造方法中），
    // 把默认构造函数给覆盖掉，在我们用的时候它就一定会给我们传一个工厂进来
    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }
    public List<User> findAll() {
        //使用工厂创建SqlSession对象
        SqlSession session = factory.openSession();
        //2.使用session执行查询所有的方法,在下面的selectList()方法中可以传入一些sql语句，
        // 但是传入sql语句后，IUserDao.xml映射配置文件中配置的查询语句不就没有意义了吗？
        //如果在selectList()方法的参数中传入IUserDao.xml映射配置文件中配置的id(也就是：findALL)，
        // 问题是因为我们现在只有一个Dao的配置，所以只有一个id，但是以后有很多Dao的配置，
        // 它们的方法的查询名字都是findALL，所以就无法精确的定位了，因此，Dao的映射配置文件中的namespaces就起作用了
        //我们可以将命名空间的namespaces 与 id 组合式用
        //组合如下：com.kay.dao.IUserDao.findAll
        List<User> users = session.selectList("com.kay.dao.IUserDao.findAll");//selecList()方法返回的就是我们想要的结果集
        //用完之后要记得关闭
        session.close();
        //3.返回查询结果
        return users;
    }
}
