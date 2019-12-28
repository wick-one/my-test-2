package com.zzq.web.servlet;

import com.zzq.domain.User;
import com.zzq.service.IUserService;
import com.zzq.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //调用service完成查询，获取查询结果集的封装对象
        IUserService service=new UserServiceImpl();
        List<User> users = service.findAll();
        //将数据存入request域中
        request.setAttribute("users",users);
        //转发到list.jsp页面
        request.getRequestDispatcher("/list.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
