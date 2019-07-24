package com.kay.dao;

import com.kay.domain.Account;
import com.kay.domain.AccountUser;
import com.kay.domain.User;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有账户，需求:同时还要获取到当前用户账户的有属用户信息
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有账户同时包含用户名和地址信息
     * @return
     */
    List<AccountUser> findAllAccount();


}
