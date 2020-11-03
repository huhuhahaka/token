package com.fruits.token.interceptor;

import com.fruits.token.annotations.Auth;
import com.fruits.token.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            Auth auth = hm.getMethodAnnotation(Auth.class);
            if (auth != null) {
                String token = request.getHeader("JWT-TOKEN");
                if (StringUtils.isBlank(token) || !JwtUtil.verifyToken(token)) {
                    String result = "验证失败！";
                    response.setHeader("Content-type", "text/html;charset=UTF-8");
                    response.getOutputStream().write(result.getBytes("UTF-8"));
                    response.getOutputStream().flush();

                    return false;
                }
            }
        }
        return true;
    }
}
