<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/9/25
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        //删除指定id的用户
        function delUser(id) {
            if (confirm("您确定要删除吗？")) {
                //链接到href目标地址， ？id= 加上该位置的实际id
                location.href = "${pageContext.request.contextPath}/delUserServlet?id=" + id;
            }
        }

        //屏幕加载后的事件
        window.onload = function () {
            //删除选中的所有复选框
            //根据id获取 删除选中 按钮
            document.getElementById("delSelected").onclick = function () {
                //根据name值获取所有该name的控件
                var ckBoxs = document.getElementsByName("uid");
                //用于判断复选框中有没有被选中的
                var isChecked = false;
                //forin遍历所有的复选框
                for (var i in ckBoxs) {
                    //一旦有个复选框被选中，说明没有空选
                    if (ckBoxs[i].checked) {
                        isChecked = true;
                        //已经证明有被选中的复选框存在了，没必要接着循环了
                        break;
                    }
                }
                if (isChecked) {
                    if (confirm("您确定要删除吗？")) {
                        //根据id获取form表单并执行提交方法
                        document.getElementById("form").submit();
                    }
                } else {
                    confirm("您没有选中任何数据项！")
                }
            }
            //全选复选框单击事件
            document.getElementById("firstCkBox").onclick = function () {
                var ckBoxs = document.getElementsByName("uid");
                //遍历设置该复选框的checked状态为全选复选框的状态
                for (var i in ckBoxs) {
                    ckBoxs[i].checked = this.checked;
                }
                // for (var i = 0; i <ckBoxs.length ; i++) {
                //     ckBoxs[i].checked=this.checked;
                // }
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center;margin: 25px;">用户信息列表</h3>
    <div style="float: left;margin-bottom: 5px;">
        <form class="form-inline" action="${pageContext.request.contextPath}/findByPagingServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" name="name" class="form-control" id="exampleInputName2" value="${condition.name[0]}">
            </div>
            &nbsp;
            <div class="form-group">
                <label for="exampleInputNome2">籍贯</label>
                <input type="text" name="address" class="form-control" id="exampleInputNome2" value="${condition.address[0]}">
            </div>
            &nbsp;
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="text" name="email" class="form-control" id="exampleInputEmail2" value="${condition.email[0]}">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;margin-bottom: 5px;">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>
    </div>

    <form id="form" action="${pageContext.request.contextPath}/delSelectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <%--表头--%>
            <tr class="success">
                <td><input type="checkbox" id="firstCkBox"/></td>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>

            <%--动态显示分页查询的人员信息--%>
            <c:forEach items="${pageBean.list}" var="user" varStatus="s">
                <tr align="center" valign="middle">
                    <td><input type="checkbox" name="uid" value="${user.id}"/></td>
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td>
                        <a class="btn btn-default btn-sm"
                           href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改
                        </a>&nbsp;
                        <a class="btn btn-default btn-sm"
                           href="javascript:delUser(${user.id})">删除
                        </a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </form>

    <%--页码按钮--%>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">

                <%--上一页--%>
                <%--如果当前页码为1，则禁用上一页--%>
                <c:if test="${pageBean.currentPageNum==1}">
                <li class="disabled" style="display: none"></c:if>
                    <%--如果当前页码不为1，则不禁用上一页--%>
                    <c:if test="${pageBean.currentPageNum!=1}">
                <li></c:if>

                    <%--当前页面-1  --%>
                    <a href="${pageContext.request.contextPath}/findByPagingServlet?currentPageNum=${pageBean.currentPageNum-1}&rows=${pageBean.rows}&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}"
                       aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <%--根据总页数动态显示当前有多少页，不超过10--%>
                <%--如果总页数不超过10页，那就显示具体的页数--%>
                <c:if test="${pageBean.totalPageNum<=10}">
                    <c:forEach begin="1" end="${pageBean.totalPageNum}" var="i">
                        <%--如果该按钮是当前页，那就设置激活样式--%>
                        <c:if test="${pageBean.currentPageNum==i}">
                            <li class="active">
                                <a href="${pageContext.request.contextPath}/findByPagingServlet?currentPageNum=${i}&rows=${pageBean.rows}&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}
                                </a>
                            </li>
                        </c:if>
                        <%--如果该按钮不是当前页，那就不设置样式--%>
                        <c:if test="${pageBean.currentPageNum!=i}">
                            <li>
                                <a href="${pageContext.request.contextPath}/findByPagingServlet?currentPageNum=${i}&rows=${pageBean.rows}&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}
                                </a>
                            </li>
                        </c:if>
                    </c:forEach>
                </c:if>
                <%--如果总页数超过10页，那就显示10页--%>
                <c:if test="${pageBean.totalPageNum>10}">
                    <c:forEach begin="1" end="10" var="i">
                        <%--如果该按钮是当前页，那就设置激活样式--%>
                        <c:if test="${pageBean.currentPageNum==i}">
                            <li class="active">
                                <a href="${pageContext.request.contextPath}/findByPagingServlet?currentPageNum=${i}&rows=${pageBean.rows}&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}
                                </a>
                            </li>
                        </c:if>
                        <%--如果该按钮不是当前页，那就不设置样式--%>
                        <c:if test="${pageBean.currentPageNum!=i}">
                            <li>
                                <a href="${pageContext.request.contextPath}/findByPagingServlet?currentPageNum=${i}&rows=${pageBean.rows}&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}
                                </a>
                            </li>
                        </c:if>
                    </c:forEach>
                </c:if>

                <%--下一页--%>
                <%--如果当前页码为最后页码，则禁用下一页--%>
                <c:if test="${pageBean.currentPageNum==pageBean.totalPageNum}">
                <li class="disabled" style="display: none"></c:if>
                    <%--如果当前页码不为为最后页码，则不禁用下一页--%>
                    <c:if test="${pageBean.currentPageNum!=pageBean.totalPageNum}">
                <li></c:if>

                    <%--当前页面+1   --%>
                    <a href="${pageContext.request.contextPath}/findByPagingServlet?currentPageNum=${pageBean.currentPageNum+1}&rows=${pageBean.rows}&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>

                <span style="font-size: 25px;margin-left: 5px">
                    共${pageBean.totalCount}条数据，共${pageBean.totalPageNum}页
                </span>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
