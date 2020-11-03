package com.fruits.token.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fruits.token.dao.UserDao;
import com.fruits.token.entity.User;
import com.fruits.token.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByName(String username) {

        User user = userDao.selectOne(new QueryWrapper<User>().eq("username", username));

        return user;
    }
}
