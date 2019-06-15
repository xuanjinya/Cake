package cn.huang.cake.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Yaking
 * @Date: 2019/6/15 12:15
 * @Describe: 登录
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("/login.do".equals(req.getServletPath())) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            //如果用户名和密码相同且不为null，则登录成功
            if (null != username && !"".equals(username.trim()) && username.equals(password)) {
                req.getSession().setAttribute("username", username);
                req.getRequestDispatcher("/cake/list.do").forward(req, resp);
            } else {
                req.getRequestDispatcher("/loginPrompt.do").forward(req, resp);
            }
        } else if ("/loginPrompt.do".equals(req.getServletPath())) {
            //如果地址中没有【/login.do】,说明不是从登陆页面提交的请求，就重新回到登录页面
            req.getRequestDispatcher("WEB-INF/views/biz/login.jsp").forward(req, resp);
        }
    }
}
