package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/22 - 10 - 22 - 10:26
 * @Description: com.nowcoder.community.service
 * @version: 1.0
 */

@Service
// 更改为多例
// @Scope("prototype")
public class AlphaService {

    // 处理请求时, Service 调用 Dao
    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化AlphaService");
    };

    // 在构造器之后调用该方法
    @PostConstruct
    public void init(){
        System.out.println("初始化AlphaService");
    }

    // 在销毁之前调用该方法
    @PreDestroy
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

    // 查询业务
    public String find(){
        return alphaDao.select();
    }
}
