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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置获取请求数据的编码utf-8，因为是post方法
        request.setCharacterEncoding("UTF-8");
        //获取session
        HttpSession session = request.getSession();
        //校验输入的验证码
        String verifyCode = request.getParameter("verifyCode");
        String vCode = (String) session.getAttribute("vCode");
        //实现验证码的一次性
        session.removeAttribute("vCode");
        if (!verifyCode.equalsIgnoreCase(vCode)) {
            //如果验证码不正确，返回错误信息并返回登录页面
            request.setAttribute("loginMsg", "验证码不正确！");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            //下面语句没必要执行
            return;
        }

        //获取登录参数并封装User对象
        User loginUser = new User();
        try {
            BeanUtils.populate(loginUser, request.getParameterMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建业务层service对象，调用业务层方法
        IUserService service = new UserServiceImpl();
        //查询登录的用户是否存在
        User user = service.findLogin(loginUser);
        if (user != null) {
            //用户存在
            //将user对象存入session
            session.setAttribute("user", user);
            //跳转页面
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            //用户不存在
            //存入错误信息
            request.setAttribute("loginMsg", "用户名或密码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
