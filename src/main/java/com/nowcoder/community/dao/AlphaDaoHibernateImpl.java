package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/22 - 10 - 22 - 10:14
 * @Description: com.nowcoder.community.dao
 * @version: 1.0
 */
// 给 Bean 起别名
@Repository("alphaHibernate")
public class AlphaDaoHibernateImpl implements AlphaDao{
    @Override
    public String select() {
        return "Hibernate";
    }
}
