package com.zzq.service;

import com.zzq.domain.PageBean;
import com.zzq.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户业务逻辑接口
 */
public interface IUserService {
    //查询所有
    List<User> findAll();

    //查询登录
    User findLogin(User user);

    //添加用户
    void addUser(User user);

    //删除用户
    void delUser(int id);

    //分页条件查询
    PageBean<User> findUserByPaging(int currentPageNum, int rows, Map<String, String[]> condition);

    //批量删除用户
    void delSelectedUsers(String[] uids);

    //查询用户信息
    User findUser(int id);

    //修改用户信息
    void updateUser(User user);
}
