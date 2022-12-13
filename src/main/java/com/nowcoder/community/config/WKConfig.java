package com.nowcoder.community.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;

@Configuration
public class WKConfig {

    private static final Logger logger = LoggerFactory.getLogger(WKConfig.class);

    @Value("${wk.image.storage}")
    private String wkImageStorage;

    // 在服务启动时, 检查目录是否存在
    @PostConstruct
    public void init(){
        // 创建WK图片目录
        File file = new File(wkImageStorage);
        if (!file.exists()){
            file.mkdir();
            logger.info("创建WK图片目录:" + wkImageStorage);
        }


    }

}
