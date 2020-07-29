package com.dnm.test;

import com.dnm.dao.IAccountDao;
import com.dnm.dao.IUserAccountsDao;
import com.dnm.domain.Account;
import com.dnm.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserAccountsTest {
    SqlSession session = null;
    IUserAccountsDao userAccountsDao = null;

    @Before
    public void init(){
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
            session = new SqlSessionFactoryBuilder().build(in).openSession(true);
            userAccountsDao = session.getMapper(IUserAccountsDao.class);
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
        List<User> users = userAccountsDao.findAllUserAccounts();
        for(User user : users){
            List<Account> accounts = user.getAccounts();
            System.out.println(user);
            for(Account account : accounts){
                System.out.println("\t|---->" + account);
            }
        }
    }
}
