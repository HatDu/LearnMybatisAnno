package com.dnm.test;

import com.dnm.dao.IUserDao;
import com.dnm.domain.User;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class CacheTest {
    SqlSessionFactory factory;

    @Before
    public void init(){
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
            factory = new SqlSessionFactoryBuilder().build(in);

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

    /**
     * 测试一级缓存
     */
    @Test
    public void firstLevelCacheTest(){
        SqlSession session = factory.openSession(true);
        IUserDao userDao = session.getMapper(IUserDao.class);
        User user1 = userDao.findUserById(15);
        // 清除缓存
        // session1.clearCache();
        User user2 = userDao.findUserById(15);
        System.out.println(user1 == user2);

        session.close();
    }

    /**
     * 测试二级缓存
     */
    @Test
    public void secondLevelCacheTest(){
        SqlSession session1 = factory.openSession(true);
        IUserDao userDao1 = session1.getMapper(IUserDao.class);
        User user1 = userDao1.findUserById(15);
        session1.close();

        SqlSession session2 = factory.openSession(true);
        IUserDao userDao2 = session2.getMapper(IUserDao.class);
        User user2 = userDao2.findUserById(15);
        session2.close();

        System.out.println(user1 == user2);
    }
}
