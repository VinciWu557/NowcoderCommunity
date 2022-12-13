package com.nowcoder.community.dao;

import com.nowcoder.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 根据实体来查询(分页查询)
    List<Comment> selectCommentByEntity(int entityType, int entityId, int offset, int limit);

    // 查询数据的条目数
    int selectCountByEntity(int entityType, int entityId);

    // 增加评论
    int insertComment(Comment comment);

    // 根据 id 查询 comment
    Comment selectCommentById(int id);
}
