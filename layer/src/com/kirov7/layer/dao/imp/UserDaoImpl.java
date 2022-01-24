package com.kirov7.layer.dao.imp;

import com.kirov7.layer.dao.UserDao;
import com.kirov7.layer.model.User;
import com.kirov7.layer.util.JdbcUtil;

import java.util.List;

/**
 * @author ：枫阿雨
 * @description：TODO
 * @date ：2022-01-24 21:43
 */

public class UserDaoImpl implements UserDao {

    @Override
    public int saveUser(String username, String password, String salt) {
        String sql ="INSERT INTO `user` (`username`, `password`, `salt`) VALUES (?, ?, ?)";
        Object[] params = {username, password, salt};
        return JdbcUtil.update(sql, params);
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT username, password, salt FROM user WHERE username = ?";
        List<User> users = JdbcUtil.query(sql, User.class, username);
        return users.size() == 0 ? null : users.get(0);
    }
}
