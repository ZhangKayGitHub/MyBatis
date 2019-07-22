package com.kay.dao;

import com.kay.domain.User;
import com.kay.mybatis.annotations.Select;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有操作
     * @return
     */
    @Select("select * from User")
    List<User> findAll();
}
