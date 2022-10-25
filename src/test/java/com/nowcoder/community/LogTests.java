package com.nowcoder.community;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/25 - 10 - 25 - 10:14
 * @Description: com.nowcoder.community
 * @version: 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
// 选择测试用的配置类
@ContextConfiguration(classes = CommunityApplication.class)
public class LogTests {

    private static final Logger logger = LoggerFactory.getLogger(LogTests.class);

    @Test
    public void testLogger(){
        System.out.println(logger.getName());
        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");
    }

}
