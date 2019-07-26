package com.kay.dao;

import com.kay.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有用户
     * @return
     *
     * 在mybatis中针对CRUD一共有四个注解
     * @Select @Insert @Update @Delete
     */
    @Select(value = "select * from user")
    List<User> findAll();

    /**
     * 保存用户
     * @param user
     */
    @Insert(value = "insert into user(username,address,sex,birthday) values(#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    @Update("update user set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} where id=#{id}")
    void updateUser(User user);

    /**
     * 删除用户
     * @param userId
     */
    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer userId);

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Select("select * from user where id=#{id}")
    User findById(Integer userId);

    /**
     * 根据用户名称模糊查询
     * @param username
     * @return
     */
    @Select("select * from user where username like #{username}")
    // @Select("select * from user where username like %${value}%")这样的话测试时就不用再添加%号了，
    // value时规定的值不能更改，直接再数据库中进行了匹配，上边利用的是字符串的拼接
    List<User> findUserByName(String username);

    /**
     * 查询总用户数量
     * @return
     */
    @Select("select count(*) from user")
    int findTotalUser();
}
