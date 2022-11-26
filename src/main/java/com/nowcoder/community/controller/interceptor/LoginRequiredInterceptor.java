package com.nowcoder.community.controller.interceptor;

import com.nowcoder.community.annotation.LoginRequired;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/30 - 10 - 30 - 21:35
 * @Description: com.nowcoder.community.controller.interceptor
 * @version: 1.0
 */
@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {
    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果拦截到的是方法
        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // 获取当前拦截的方法的注解
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
            // loginRequired 不为空 >> 该方法需要判断是否登录
            // hostHolder.getUser() >> 检查当前是否有登录用户
            if (loginRequired != null && hostHolder.getUser() == null){
                // 没有登录用户 >> 强制重定向
                response.sendRedirect(request.getContextPath() + "/login");
                return false;
            }
        }
        return true;
    }
}
