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
    // orderMode 0 -> 按照时间排序 1 -> 按照热度排序
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit, int orderMode);

    // 查询帖子的行数
    // @Param 给参数取别名
    // 当前参数只有一个, 且在动态标签 <if> 里使用, 必须要用 @Param
    int selectDiscussPostRows(@Param("userId") int userId);

    // 发布帖子
    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(int id);

    // 更新帖子的回复数量
    int updateCommentCount(int id, int commentCount);

    // 置顶 加精 删除
    int updateType(int id, int type);

    int updateStatus(int id, int status);

    int updateScore(int id, double score);

}
