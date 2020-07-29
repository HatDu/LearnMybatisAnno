package com.dnm.test;

import com.dnm.dao.IUserDao;
import com.dnm.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sun.misc.Resource;

//import javax.annotation.Resources;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Mybatis入门案例
 */
public class UserTest {
    SqlSession session = null;
    IUserDao userDao = null;

    @Before
    public void init(){
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
            session = new SqlSessionFactoryBuilder().build(in).openSession(true);
            userDao = session.getMapper(IUserDao.class);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @After
    public void destroy(){
        session.close();
    }

    /**
     * 查询所有用户
     */
    @Test
    public void findAll() {
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }

    @Test
    public void saveUser(){
        User user = new User();
        user.setUsername("8比特");
        user.setAddress("美国硅谷");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.saveUser(user);
        findAll();
    }

    @Test
    public void findUserById(){
        User user = userDao.findUserById(15);
        System.out.println(user);
    }

    @Test
    public void updateUser(){
        User user = userDao.findUserById(15);
        user.setBirthday(new Date());

        userDao.updateUser(user);
        User newUser = userDao.findUserById(user.getId());
        System.out.println(newUser);
    }

    @Test
    public void deleteUserById(){
        findAll();
        userDao.deleteUserById(16);
        findAll();
    }
}
