package com.nowcoder.community.controller.interceptor;

import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CookieUtil;
import com.nowcoder.community.util.HostHolder;
import jdk.nashorn.internal.ir.IfNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.Handler;
import java.util.Date;

/**
 * @Auther: Vinci Wu
 * @Date: 2022/10/30 - 10 - 30 - 14:53
 * @Description: com.nowcoder.community.controller.interceptor
 * @version: 1.0
 */
@Component
public class LoginTicketInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    // 在 Controller 之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从 request 中的 Cookie 获取凭证
        String ticket = CookieUtil.getValue(request, "ticket");

        if (ticket != null){
            // 通过凭证找到相应 LoginTicket 对象
            LoginTicket loginTicket = userService.findLoginTicket(ticket);
            // 判断凭证是否有效 >> 不为空 + 状态有效 + 未过期
            if (loginTicket != null && loginTicket.getStatus() == 0 && loginTicket.getExpired().after(new Date())){
                // 通过凭证查询到对应的用户 user
                User user = userService.findUserById(loginTicket.getUserId());
                // 在本次请求中持有用户 (暂存到当前线程对应的对象中)
                hostHolder.setUser(user);
                // 构建用户认证的结果, 并且存入到 SecurityContext, 以便于 Security 进行授权
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        user, user.getPassword(), userService.getAuthorities(user.getId()));
                SecurityContextHolder.setContext(new SecurityContextImpl(authentication));
            }
        }
        return true;
    }

    // 在 TemplateEngine 调用之前, 将 user 存到 Model 中
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        User user = hostHolder.getUser();
        if (user != null && modelAndView != null){
            modelAndView.addObject("loginUser", user);
        }
    }

    // 在 TemplateEngine 使用结束后, 清理掉 user
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
        SecurityContextHolder.clearContext();
    }
}
