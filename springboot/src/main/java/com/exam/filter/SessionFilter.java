package com.exam.filter;

import com.exam.util.ApiResultHandler;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionFilter implements Filter {
    String[] includeUrls = new String[]{"/login", "/logout"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        boolean needFilter = isNeedFilter(uri);

        if (!needFilter) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            if (session != null && session.getAttribute(session.getId()) != null) {
                chain.doFilter(servletRequest, servletResponse);
            } else {
                response.setCharacterEncoding("utf-8");
                response.setHeader("Content-Type", "text/html;charset=UTF-8");
                Gson gson = new Gson();
                String pleaseLogin = gson.toJson(ApiResultHandler.buildApiResult(100, "请登录！", null));
                response.getWriter().write(pleaseLogin);
            }
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean isNeedFilter(String uri) {
        for (String includeUrl : includeUrls) {
            if (includeUrl.equals(uri)) {
                return false;
            }
        }
        return true;
    }
}
