package com.zjh.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

//        放行登录页面
        if (request.getRequestURI().contains("Login")) {
            return true;
        }

        if (session.getAttribute("userLoginInfo") != null) {
            return true;
        }

//        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath()+"/user/goLogin");
        return false;
    }
}
