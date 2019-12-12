package com.hsh.user.service;

import com.hsh.user.entity.User;
import com.hsh.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 15:23
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    public User getUser(int id){
        return userMapper.getUser(id);
    }

}