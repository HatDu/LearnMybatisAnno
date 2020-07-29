package com.dnm.test;

import com.dnm.dao.IAccountDao;
import com.dnm.dao.IUserBadDao;
import com.dnm.domain.Account;
import com.dnm.domain.UserBad;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AccountTest {
    SqlSession session = null;
    IAccountDao accountDao = null;

    @Before
    public void init(){
        InputStream in = null;
        try {
            in = Resources.getResourceAsStream("SqlMapConfig.xml");
            session = new SqlSessionFactoryBuilder().build(in).openSession(true);
            accountDao = session.getMapper(IAccountDao.class);
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
        List<Account> accounts = accountDao.findAllAccountUser();
        for(Account account : accounts){
            System.out.println(account);
            System.out.println("\t|---->" + account.getUser());
        }
    }
}
