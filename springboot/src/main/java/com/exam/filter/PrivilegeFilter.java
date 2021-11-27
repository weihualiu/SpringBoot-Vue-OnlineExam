package com.exam.filter;

import com.exam.entity.User;
import com.exam.util.ApiResultHandler;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 权限过滤器
 *  根据请求接口路径和用户角色进行过滤
 */
public class PrivilegeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 控制考试用户请求教师或者管理员接口
        HttpServletRequest servletRequest = (HttpServletRequest)request;
        HttpServletResponse servletResponse = (HttpServletResponse)response;
        HttpSession session = servletRequest.getSession();
        User user = (User)session.getAttribute(session.getId());
        if(user != null && user.getRole().equals("2")) {
            String uri = servletRequest.getRequestURI();
            if(uri.indexOf("/exam1*") >= 0) {
                servletResponse.setCharacterEncoding("utf-8");
                servletResponse.setHeader("Content-Type", "text/html;charset=UTF-8");
                Gson gson = new Gson();
                String content = gson.toJson(ApiResultHandler.buildApiResult(101, "无此访问权限！", null));
                servletResponse.getWriter().write(content);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
