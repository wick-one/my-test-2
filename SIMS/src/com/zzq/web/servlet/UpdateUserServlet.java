package com.zzq.web.servlet;

import com.zzq.domain.User;
import com.zzq.service.IUserService;
import com.zzq.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/updateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        //获取参数并封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user);
        //调用service执行update方法
        IUserService service = new UserServiceImpl();
        service.updateUser(user);
        //跳转list页面
        response.sendRedirect(request.getContextPath() + "/userListServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
