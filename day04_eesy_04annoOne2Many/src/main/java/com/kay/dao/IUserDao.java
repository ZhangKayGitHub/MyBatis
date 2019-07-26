package com.kay.dao;

import com.kay.domain.Account;
import com.kay.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@CacheNamespace(blocking = true)//开启二级缓存的使用
public interface IUserDao {
    /**
     * 查询所有用户
     *
     * @return 在mybatis中针对CRUD一共有四个注解
     * @Select @Insert @Update @Delete
     */
    @Select(value = "select * from user")
    @Results(id="userMap", value = {//这里的id是提供的唯一标志，目的是人可以让其他的方法调用id="userMap"
            @Result(id=true,column = "id",property = "userId"),//id的默认值是false，所以下边的都不用写了
            @Result(id=false,column = "userName",property = "userName"),
            @Result(column = "address",property = "userAddress"),
            @Result(column = "sex",property = "userSex"),
            @Result(column = "birthday",property = "userBirthday"),
            @Result(property = "accounts", column = "id",many=@Many(select="com.kay.dao.IAccountDao.findAccountByUid",fetchType = FetchType.EAGER))
    })
    List<User> findAll();

    /**
     * 根据id查询用户
     *
     * @param userId
     * @return
     */
    @Select("select * from user where id=#{id}")
    @ResultMap(value={"userMap"})//该value是一个数组，如果value只有一个值value是可以省略的，如果数组中只有一个值大括号{}也可以省略
    User findById(Integer userId);

    /**
     * 根据用户名称模糊查询
     *
     * @param username
     * @return
     */
    @Select("select * from user where username like #{username}")
    // @Select("select * from user where username like %${value}%")这样的话测试时就不用再添加%号了，
    // value时规定的值不能更改，直接再数据库中进行了匹配，上边利用的是字符串的拼接
    @ResultMap("userMap")
    List<User> findUserByName(String username);

}