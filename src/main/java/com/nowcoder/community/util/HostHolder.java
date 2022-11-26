package com.nowcoder.community.util;

import com.nowcoder.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/30 - 10 - 30 - 15:06
 * @Description: com.nowcoder.community.util
 * @version: 1.0
 */

/**
 * 持有用户信息, 用于代替 session 对象
 */

@Component
public class HostHolder {
    // 实现线程隔离
    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user){
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }
    // 清理
    public void clear(){
        users.remove();
    }

}
