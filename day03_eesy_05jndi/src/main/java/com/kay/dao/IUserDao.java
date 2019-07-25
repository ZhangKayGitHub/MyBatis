package com.kay.dao;

import com.kay.domain.QueryVo;
import com.kay.domain.User;

import java.util.List;
import java.util.Queue;

/**
 *
 *用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有的用户
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 根据id查找用户
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据名称模糊查询用户信息
     * @param username
     * @return
     */
    List<User> findByName(String username);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();

    /**
     * 根据queryVo中的条件查询用户
     * @param vo
     * @return
     */
    //其实我们还是要使用模糊查询，使用名称进行查询，只是这个名称被封装到一个对象中，
    // 这个对象就是QueryVo,在这个QueryVo对象中封装了User对象作为标识符，
    // 并提供了User的get()和set()方法
    List<User> findUserByVo(QueryVo vo);

}
