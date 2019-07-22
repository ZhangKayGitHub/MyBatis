package com.kay.dao;

import com.kay.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;
    public UserDaoImpl(SqlSessionFactory factory){
        this.factory = factory;
    }


    public List<User> findAll() {
        //1.根据factory 获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession 中的方法，实现查询列表
        List<User> users = session.selectList("com.kay.dao.IUserDao.findAll");//参数就是能获取配置信息的key,类的映射文件的命名空间后面再接方法id
        //3.释放资源
        session.close();
        return users;
    }

    public void saveUser(User user) {
        //1.根据factory 获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession 中的方法，实现保存
        session.insert("com.kay.dao.IUserDao.saveUser",user);
        session.commit();
        //3.释放资源
        session.close();
    }

    public void updateUser(User user) {
        //1.根据factory 获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession 中的方法，实现更新
        session.update("com.kay.dao.IUserDao.saveUser",user);
        session.commit();
        //3.释放资源
        session.close();
    }

    public void deleteUser(Integer userId) {
        //1.根据factory 获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession 中的方法，实现删除
        session.update("com.kay.dao.IUserDao.deleteUser",userId);
        session.commit();
        //3.释放资源
        session.close();
    }

    public User findById(Integer userId) {

        //1.根据factory 获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession 中的方法，实现查询列表
        User user = session.selectOne("com.kay.dao.IUserDao.findById",userId);
        //3.释放资源
        session.close();
        return user;
    }

    public List<User> findByName(String username) {
        //1.根据factory 获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession 中的方法，实现查询列表
        List<User> users = session.selectList("com.kay.dao.IUserDao.findByName",username);
        //3.释放资源
        session.close();
        return users;
    }

    public int findTotal() {
        //1.根据factory 获取SqlSession对象
        SqlSession session = factory.openSession();
        //2.调用SqlSession 中的方法，实现查询列表
        Integer count  = session.selectOne("com.kay.dao.IUserDao.findTotal");//参数就是能获取配置信息的key,类的映射文件的命名空间后面再接方法id
        //3.释放资源
        session.close();
        return count;
    }
}
