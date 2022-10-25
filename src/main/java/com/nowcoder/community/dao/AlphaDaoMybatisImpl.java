package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/22 - 10 - 22 - 10:17
 * @Description: com.nowcoder.community.dao
 * @version: 1.0
 */
@Repository
@Primary
public class AlphaDaoMybatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "Mybatis";
    }
}
