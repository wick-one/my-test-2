package com.zzq.service.impl;

import com.zzq.dao.IUserDao;
import com.zzq.dao.impl.UserDaoImpl;
import com.zzq.domain.PageBean;
import com.zzq.domain.User;
import com.zzq.service.IUserService;

import java.util.List;
import java.util.Map;

/**
 * 实现UserService的业务逻辑方法
 */
public class UserServiceImpl implements IUserService {
    //业务层调用dao层对象
    private IUserDao userDao = new UserDaoImpl();

    //调用dao的查询所有
    @Override
    public List<User> findAll() {
        //调用dao
        return userDao.findAll();
    }

    //调用dao的查询登录
    @Override
    public User findLogin(User loginUser) {
        return userDao.findLogin(loginUser);
    }

    //调用dao的添加用户
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    //调用dao的删除指定id的用户
    @Override
    public void delUser(int id) {
        userDao.delUser(id);
    }

    //实现分页查询，封装数据，返回pagebean对象
    @Override
    public PageBean<User> findUserByPaging(int currentPageNum, int rows, Map<String, String[]> condition) {
        //创建封装数据对象PageBean
        PageBean<User> pageBean = new PageBean<>();
        //注入当前页码
        pageBean.setCurrentPageNum(currentPageNum);
        //注入每页规定显示的行数
        pageBean.setRows(rows);
        //注入总记录数
        //--查询总记录数
        long totalCount = userDao.findTotalCount(condition);
        pageBean.setTotalCount(totalCount);
        //注入总页数
        //--计算总页数
        int totalPageNum = (int) (totalCount % rows == 0 ? totalCount / rows : totalCount / rows + 1);
        pageBean.setTotalPageNum(totalPageNum);
        //注入分页数据
        //--计算分页查询的起始索引
        int start = (currentPageNum - 1) * rows;
        //--获取查询的分页数据
        List<User> list = userDao.findByPaging(start, rows,condition);
        pageBean.setList(list);
        //返回PageBean对象
        return pageBean;
    }

    //批量删除用户
    @Override
    public void delSelectedUsers(String[] uids) {
        for (String uid : uids) {
            //调用dao的删除用户方法
            userDao.delUser(Integer.parseInt(uid));
        }
    }

    //调用dao的查询用户
    @Override
    public User findUser(int id) {
        return userDao.findUser(id);
    }

    //调用dao的修改方法
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
}
