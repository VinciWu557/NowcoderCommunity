package com.nowcoder.community.util;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/29 - 10 - 29 - 10:49
 * @Description: com.nowcoder.community.util
 * @version: 1.0
 */
public interface CommunityConstant {
    /**
     * 激活成功
     */
    int ACTIVATION_SUCCESS = 0;

    /**
     * 重复激活
     */
    int ACTIVATION_REPEAT = 1;

    /**
     * 激活失败
     */
    int ACTIVATION_FAILURE = 2;

    /**
     * 默认状态的登陆凭证的超时时间
     */
    int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    /**
     * 勾选记住我状态的登陆凭证的超时时间
     */
    int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;

    /**
     * 实体类型:
     * 帖子 >> 1
     * 评论 >> 2
     * 用户 >> 3
     */
    int ENTITY_TYPE_POST = 1;
    int ENTITY_TYPE_COMMENT = 2;

    int ENTITY_TYPE_USER = 3;

    /**
     * 事件主题
     * 主题: 评论 comment
     * 主题: 点赞 like
     * 主题: 关注 follow
     * 主题: 发帖 publish
     * 主题: 删帖 delete
     */
    String TOPIC_COMMENT = "comment";
    String TOPIC_LIKE = "like";
    String TOPIC_FOLLOW = "follow";
    String TOPIC_PUBLISH = "publish";
    String TOPIC_DELETE = "delete";
    String TOPIC_SHARE = "share";


    /**
     * 系统用户ID
     */
    int SYSTEM_USER_ID = 1;

    /**
     * 权限: 普通用户
     */
    String AUTHORITY_USER = "user";

    /**
     * 权限: 管理员
     */
    String AUTHORITY_ADMIN = "admin";

    /**
     * 权限: 版主
     */
    String AUTHORITY_MODERATOR = "moderator";


}
