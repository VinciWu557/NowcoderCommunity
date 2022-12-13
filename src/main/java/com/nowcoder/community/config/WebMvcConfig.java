package com.nowcoder.community.config;

import com.nowcoder.community.controller.interceptor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/30 - 10 - 30 - 14:27
 * @Description: com.nowcoder.community.config
 * @version: 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AlphaInterceptor alphaInterceptor;

    @Autowired
    private LoginTicketInterceptor loginTicketInterceptor;

    //@Autowired
    //private LoginRequiredInterceptor loginRequiredInterceptor;

    @Autowired
    private MessageInterceptor messageInterceptor;

    @Autowired
    private DataInterceptor dataInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(alphaInterceptor)
                // 排除对静态资源拦截的限制
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.jpg", "/**/*.png", "/**/*.jpeg")
                // 明确需要拦截的路径
                .addPathPatterns("/register", "/login");

        registry.addInterceptor(loginTicketInterceptor)
                // 排除对静态资源拦截的限制
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.jpg", "/**/*.png", "/**/*.jpeg");

        //registry.addInterceptor(loginRequiredInterceptor)
                // 排除对静态资源拦截的限制
                //.excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.jpg", "/**/*.png", "/**/*.jpeg");
        registry.addInterceptor(messageInterceptor)
                // 排除对静态资源拦截的限制
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.jpg", "/**/*.png", "/**/*.jpeg");

        registry.addInterceptor(dataInterceptor)
                // 排除对静态资源拦截的限制
                // 注意拦截器的位置, 默认按顺序执行拦截器的preHandle方法
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.jpg", "/**/*.png", "/**/*.jpeg");
    }
}
