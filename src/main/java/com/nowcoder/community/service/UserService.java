package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/24 - 10 - 24 - 15:43
 * @Description: com.nowcoder.community.service
 * @version: 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id){
        return  userMapper.selectById(id);
    }

}
