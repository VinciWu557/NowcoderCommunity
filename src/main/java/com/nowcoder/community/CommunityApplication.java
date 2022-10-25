package com.nowcoder.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 程序的入口配置类
@SpringBootApplication
public class CommunityApplication {
	// 创建 Spring 容器
	// CommunityApplication 是配置文件 (有 SpringBootApplication 注解)
	// 自动扫描 配置类所在的包 以及 子包下的 Bean
	public static void main(String[] args) {
		SpringApplication.run(CommunityApplication.class, args);
	}

}
