package com.dnm.dao;

import com.dnm.domain.Account;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {
    /**
     * 查询所有账户，包括其对应的用户信息，一对一查询
     * @return
     */
    @Select("select * from account")
    @Results(id = "AccountUserMapper", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "uid", column = "uid"),
            @Result(property = "money", column = "money"),
            // 一对一连接 one = @One
            // 立即查询fetchType = FetchType.EAGER
            @Result(property = "user", column = "uid", one = @One(select = "com.dnm.dao.IUserDao.findUserById", fetchType = FetchType.EAGER))
    })
    List<Account> findAllAccountUser();


    @Select("select * from account where id=#{id}")
    Account findAccountById(Integer id);

    @Select("select * from account where uid=#{uid}")
    List<Account> findAccountByUid(Integer uid);
}
