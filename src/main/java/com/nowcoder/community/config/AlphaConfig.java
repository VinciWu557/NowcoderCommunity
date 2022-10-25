package com.nowcoder.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/22 - 10 - 22 - 11:01
 * @Description: com.nowcoder.community.config
 * @version: 1.0
 */

// 一般的配置类
@Configuration
public class AlphaConfig {

    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

}
