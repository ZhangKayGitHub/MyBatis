package com.kay.dao;

import com.kay.domain.User;

import java.util.List;

/**
 *
 *用户的持久层接口
 */
public interface IUserDao {





    /**
     * 查询所有的用户，同时获取到用户下所有账户的信息
     * @return
     */
    List<User> findAll();


    /**
     * 根据id查找用户
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 如果数据库的数据跟我们的缓存不一致了它是如何做到同步的呢？
     *
     * 更新用户信息
     */
    void updateUser(User user);

}
