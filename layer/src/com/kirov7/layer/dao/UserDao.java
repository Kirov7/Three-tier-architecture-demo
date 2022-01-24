package com.kirov7.layer.dao;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-01-24 21:40
 */

import com.kirov7.layer.model.User;

/**
 * 数据访问层接口
 */
public interface UserDao {

    int saveUser(String username, String password, String salt);

    User getUserByUsername(String username);
}
