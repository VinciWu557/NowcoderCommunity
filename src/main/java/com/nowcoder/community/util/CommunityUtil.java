package com.nowcoder.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/29 - 10 - 29 - 9:38
 * @Description: com.nowcoder.community.util
 * @version: 1.0
 */
public class CommunityUtil {
    // 生成随机字符串
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    // MD5加密 (无法解密)
    // 每次加密结果相同 hello >> abc123456
    // 再加上随机字符串, 提高安全性 hello + 随机字符串 >> asdsaddasdsa1231453
    public static String md5(String key){
        if (StringUtils.isBlank(key)){
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
