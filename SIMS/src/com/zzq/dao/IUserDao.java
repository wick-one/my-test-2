package com.zzq.dao;

import com.zzq.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户数据访问对象接口
 */
public interface IUserDao {
    //查询所有
    List<User> findAll();

    //查询登录名和密码
    User findLogin(User user);

    //分页查询
    List<User> findByPaging(int start, int rows, Map<String, String[]> condition);

    //查询总记录数
    long findTotalCount(Map<String, String[]> condition);

    //添加用户
    void addUser(User user);

    //删除用户
    void delUser(int id);

    //查询用户信息
    User findUser(int id);

    //修改用户信息
    void updateUser(User user);
}
