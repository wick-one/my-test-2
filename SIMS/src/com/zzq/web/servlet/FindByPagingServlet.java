package com.zzq.web.servlet;

import com.zzq.domain.PageBean;
import com.zzq.domain.User;
import com.zzq.service.IUserService;
import com.zzq.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/findByPagingServlet")
public class FindByPagingServlet extends HttpServlet {
    /**
     * 分析：
     * 分页查询展示的页面是list.jsp，
     * 页面中需要的元素：当前页码，总记录数，总页数（总页数=总记录数/每页显示的行数），每页显示的行数，查询出的数据
     * 将所有散列的元素信息封装到一个Bean容器中，方便转发数据到页面展示；
     */
    //测试：localhost:8080/sims/findByPagingServlet?currentPageNum=2&rows=3
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");

        //接收请求参数：当前所在页码，每页要显示的行数
        String currentPageNum = request.getParameter("currentPageNum");
        String rows = request.getParameter("rows");

        //null处理
        if (currentPageNum == null) currentPageNum = "1";
        if (rows == null) rows = "5";

        //获取条件查询参数
        Map<String, String[]> condition = request.getParameterMap();

        //调用service查询，返回PageBean封装结果
        IUserService service = new UserServiceImpl();
        PageBean<User> pageBean = service.findUserByPaging(Integer.parseInt(currentPageNum), Integer.parseInt(rows), condition);
        //转发pagebean数据到list.jsp页面展示
        System.out.println(pageBean);
        request.setAttribute("pageBean", pageBean);
        request.setAttribute("condition", condition);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
