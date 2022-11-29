package com.nowcoder.community.dao;

import com.nowcoder.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/30 - 10 - 30 - 9:32
 * @Description: com.nowcoder.community.dao
 * @version: 1.0
 */
@Mapper
@Deprecated
public interface LoginTicketMapper {
    // 插入 LoginTicket
    @Insert({
            "insert into login_ticket(user_id, ticket, status, expired) ",
            "values (#{userId}, #{ticket}, #{status}, #{expired})"
    })
    // 将 id 作为主键并实现自增
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    // 根据 ticket 查找
    @Select({
            "select id, user_id, ticket, status, expired ",
            "from login_ticket where ticket = #{ticket}"
    })
    LoginTicket selectByTicket(String ticket);

    // 修改状态, 实现删除
    @Update({
            "<script>",
            "update login_ticket set status = #{status} ",
            "where ticket = #{ticket} ",
            "<if test=\"ticket!=null\"> ",
            "and 1 = 1",
            "</if>",
            "</script>"
    })
    int updateStatus(String ticket, int status);
}
