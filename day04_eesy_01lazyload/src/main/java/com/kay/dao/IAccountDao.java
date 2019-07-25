package com.kay.dao;

import com.kay.domain.Account;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有账户，需求:同时还要获取到当前用户账户的有属用户信息
     * @return
     */
    List<Account> findAll();

    /**
     * 根据用户id查询账户信息
     * @param uid
     * @return
     */
    List<Account> findAccountByUid(Integer uid);

}
