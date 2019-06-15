package cn.huang.cake.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: Yaking
 * @Date: 2019/6/15 12:35
 * @Describe: 处理登录和未登录情况
 */
public class UserFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if ("/login.do".equals(((HttpServletRequest) servletRequest).getServletPath()) ||
                "/loginPrompt.do".equals(((HttpServletRequest) servletRequest).getServletPath())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (null != (((HttpServletRequest) servletRequest).getSession().getAttribute("username"))) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            servletRequest.getRequestDispatcher("/loginPrompt.do").forward(servletRequest, servletResponse);
        }
    }

    public void destroy() {

    }
}
