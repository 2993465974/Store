package com.jizhi.phonemall.controller;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //通过请求对象 获取 URI路径
        String uri = request.getRequestURI();
        if(uri.contains("login") || uri.contains("Login") || uri.contains("logout")){
            return true;
        }
        HttpSession session = request.getSession();
        Object admin = session.getAttribute("ADMIN");
//        判断admin既不是null内容也不为空
        if(Objects.nonNull(admin) && !admin.toString().trim().isEmpty()){
                //管理员存在
            return true;
        }
        response.sendRedirect("login.jsp");
        return false;

    }
}
