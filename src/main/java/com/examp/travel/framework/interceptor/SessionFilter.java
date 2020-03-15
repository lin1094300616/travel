package com.examp.travel.framework.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.examp.travel.framework.entity.Response;
import com.examp.travel.framework.entity.StatusEnum;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Class {@code Object} is .
 *
 * @author MSI
 * @version 1.0
 */
@WebFilter
public class SessionFilter implements Filter {

    private static final String[] includeUrls = new String[]{
            "/user/list",
            "/user/register",
            "/user/login"};

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        //当前请求的url
        String uri = request.getRequestURI();

        System.out.println("filter url:"+uri);
        //判断url是否需要过滤
        if (!isNeedFilter(uri)) { //不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        } else { //需要过滤器
            // session中包含user对象,则是登录状态
            if(null != session && null != session.getAttribute("user")){
                filterChain.doFilter(request, response);
            }else{
                Response res = Response.factoryResponse(StatusEnum.SYSTEM_ERROR_9001.getCode(),StatusEnum.SYSTEM_ERROR_9001.getData());
                response.setContentType("text/html; charset=utf-8");
                response.getWriter().write(JSONObject.toJSONString(res));

            }

        }
    }

    /**
     * @Author: wdd
     * @Description: 是否需要过滤
     * @Date: 2019-02-21 13:20:54
     * @param uri
     */
    public boolean isNeedFilter(String uri) {

        for (String includeUrl : includeUrls) {
            if (includeUrl.equals(uri)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
