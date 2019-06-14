package cn.huang.cake.web;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: Yaking
 * @Date: 2019/6/14 20:03
 * @Describe: 字符集过滤器的实现
 */
public class CharsetEncodingFilter implements Filter {
    private String encoding;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoding);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
