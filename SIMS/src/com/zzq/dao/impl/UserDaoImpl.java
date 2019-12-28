package com.zzq.dao.impl;

import com.zzq.dao.IUserDao;
import com.zzq.domain.User;
import com.zzq.util.JdbcUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * 实现UserDao的数据库访问方法
 */
public class UserDaoImpl implements IUserDao {
    //通过Jdbc工具类获取池对象，将池传参给JdbcTemplate来构造对象
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcUtils.getDataSource());

    //实现查询所有方法
    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        //BeanPropertyRowMapper指定结果封装的类型为User类型
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    //实现查询总记录数
    @Override
    public long findTotalCount(Map<String, String[]> condition) {
        //定义模板sql
        String sql = "SELECT COUNT(id) FROM user WHERE 1=1 ";
        //遍历condition，取出条件拼接sql语句
        StringBuilder stringBuilder = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        Set<Map.Entry<String, String[]>> entries = condition.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            //获取entry中的key和value
            String key = entry.getKey();
            String value = entry.getValue()[0]; //细节
            //过滤 当前页码 和 每页行数 的请求参数
            if (!"currentPageNum".equals(key) && !"rows".equals(key)) {
                //判断是否有值，非null且不是空串
                if (value != null && !"".equals(value)) {
                    //拼接sql语句
                    stringBuilder.append(" AND " + key + " LIKE ? ");
                    //将值存入参数集合中
                    params.add("%" + value + "%");
                }
            }
        }

        System.out.println(stringBuilder.toString());
        System.out.println(params);

        //将查询的结果数字以Long类型返回
        Long count = jdbcTemplate.queryForObject(stringBuilder.toString(), Long.class, params.toArray());
        return count;
    }

    //实现分页查询
    @Override
    public List<User> findByPaging(int start, int rows, Map<String, String[]> condition) {
        //定义模板sql
        String sql = "SELECT * FROM user WHERE 1=1 ";
        //遍历condition，取出条件拼接sql语句
        StringBuilder stringBuilder = new StringBuilder(sql);
        List<Object> params = new ArrayList<>();
        Set<Map.Entry<String, String[]>> entries = condition.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            //获取entry中的key和value
            String key = entry.getKey();
            String value = entry.getValue()[0]; //细节
            //过滤 当前页码 和 每页行数 的请求参数
            if (!"currentPageNum".equals(key) && !"rows".equals(key)) {
                //判断是否有值，非null且不是空串
                if (value != null && !"".equals(value)) {
                    //拼接sql语句
                    stringBuilder.append(" AND " + key + " LIKE ? ");
                    //将值存入参数集合中
                    params.add("%" + value + "%");
                }
            }
        }
        //添加分页查询
        stringBuilder.append(" LIMIT ?,? ");
        //添加分页查询条件参数
        params.add(start);
        params.add(rows);
        return jdbcTemplate.query(stringBuilder.toString(), new BeanPropertyRowMapper<>(User.class), params.toArray());
    }

    //实现查询登录用户的信息
    @Override
    public User findLogin(User loginUser) {
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        try {
            User user = jdbcTemplate.queryForObject(
                    sql, new BeanPropertyRowMapper<>(User.class),
                    loginUser.getUsername(), loginUser.getPassword()
            );
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //实现添加用户方法
    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO user(id,name,gender,age,address,qq,email,username,password) " +
                "VALUES(null,?,?,?,?,?,?,/*?,*/'zzq123')";
        jdbcTemplate.update(
                sql,
                user.getName(),
                user.getGender(),
                user.getAge(),
                user.getAddress(),
                user.getQq(),
                user.getEmail()
                //user.getUsername()
        );
    }

    //实现删除用户信息方法
    @Override
    public void delUser(int id) {
        jdbcTemplate.update("DELETE FROM user WHERE id=?", id);
    }

    //实现查询用户信息
    @Override
    public User findUser(int id) {
        String sql = "SELECT * FROM user WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    //实现修改用户信息方法
    @Override
    public void updateUser(User user) {
        String sql = "UPDATE user SET name=?,gender=?,age=?,address=?,qq=?,email=? WHERE id=?";
        jdbcTemplate.update(
                sql,
                user.getName(),
                user.getGender(),
                user.getAge(),
                user.getAddress(),
                user.getQq(),
                user.getEmail(),
                user.getId()
        );
    }
}
