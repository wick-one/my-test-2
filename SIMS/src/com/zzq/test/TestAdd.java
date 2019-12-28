package com.zzq.test;

import com.zzq.dao.IUserDao;
import com.zzq.dao.impl.UserDaoImpl;
import com.zzq.domain.User;
import org.junit.Test;

import java.util.Date;
import java.util.Random;

public class TestAdd {
    @Test
    public void testAdd() {
        IUserDao dao = new UserDaoImpl();
        User user = new User();
        for (int i = 1; i <= 30; i++) {
            user.setPassword("zzq123");
            user.setName("阿斯蒂芬");
            user.setAge(23);
            user.setGender("男");
            user.setEmail("asdf@163.com");
            user.setQq("341235421");
            user.setAddress("盐城");
            user.setUsername("s" + new Random().nextInt(100) + i);
            dao.addUser(user);
        }
    }
}
