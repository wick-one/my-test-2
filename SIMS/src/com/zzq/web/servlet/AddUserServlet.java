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

@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码
        req.setCharacterEncoding("UTF-8");
        //获取参数并封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, req.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //调用业务层方法，添加用户
        IUserService service=new UserServiceImpl();
        service.addUser(user);
        resp.sendRedirect(req.getContextPath()+"/userListServlet");
    }
}
