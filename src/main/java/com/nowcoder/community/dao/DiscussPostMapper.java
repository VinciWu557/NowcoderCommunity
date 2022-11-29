package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/24 - 10 - 24 - 14:57
 * @Description: com.nowcoder.community.dao
 * @version: 1.0
 */
@Mapper
public interface DiscussPostMapper {
    // 分页查询
    // 当 userId 为空, 则不拼入 SQL (动态 SQL)
    // offset 每页起始行号
    // limit 每页最多显示多少数据
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // 查询帖子的行数
    // @Param 给参数取别名
    // 当前参数只有一个, 且在动态标签 <if> 里使用, 必须要用 @Param
    int selectDiscussPostRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);

}
