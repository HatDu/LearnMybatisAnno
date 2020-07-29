package com.dnm.test;

import com.dnm.dao.IUserBadDao;
import com.dnm.dao.IUserDao;
import com.dnm.domain.User;
import com.dnm.domain.UserBad;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class UserBadTest {
    SqlSession session = null;
    IUserBadDao userDao = null;

    @Before
    public void init(){
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
            session = new SqlSessionFactoryBuilder().build(in).openSession(true);
            userDao = session.getMapper(IUserBadDao.class);
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
        List<UserBad> users = userDao.findAll();
        for(UserBad user : users){
            System.out.println(user);
        }
    }

    @Test
    public void saveUser(){
        UserBad user = new UserBad();
        user.setUserName("8比特");
        user.setUserAddress("美国硅谷");
        user.setUserSex("男");
        user.setUserBirthday(new Date());

        userDao.saveUser(user);
        findAll();
    }
    @Test
    public void dindUserByName(){
        List<UserBad> users = userDao.findUserByName("%特%");
        for(UserBad user : users){
            System.out.println(user);
        }
    }

    @Test
    public void findUserById(){
        UserBad user = userDao.findUserById(15);
        System.out.println(user);
    }

    @Test
    public void updateUser(){
        UserBad user = userDao.findUserById(15);
        user.setUserBirthday(new Date());

        userDao.updateUser(user);
        UserBad newUser = userDao.findUserById(user.getUserId());
        System.out.println(newUser);
    }

    @Test
    public void deleteUserById(){
        findAll();
        userDao.deleteUserById(16);
        findAll();
    }

    @Test
    public void findTotal(){
        int count = userDao.findTotal();
        System.out.println("共有"+count+"条数据。");
    }
}
