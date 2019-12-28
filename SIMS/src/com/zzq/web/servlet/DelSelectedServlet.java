package com.zzq.web.servlet;

import com.zzq.service.IUserService;
import com.zzq.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取所有id
        String[] uids = request.getParameterValues("uid");
        if (uids != null) {
            //调用service删除
            IUserService service = new UserServiceImpl();
            service.delSelectedUsers(uids);

            //跳转list页面，没有用到共享数据,所以使用重定向
            response.sendRedirect(request.getContextPath() + "/userListServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
