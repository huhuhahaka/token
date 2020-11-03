package com.fruits.token.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fruits.token.entity.User;

public interface UserService extends IService<User> {

    User getUserByName(String username);
}
