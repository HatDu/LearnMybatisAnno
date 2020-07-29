package com.dnm.dao;

import com.dnm.domain.Account;
import com.dnm.domain.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Properties;

public interface IUserAccountsDao {

    @Select("select * from user")
    @Results(id = "UserAccountMapper", value = {
            @Result(id=true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "address", column = "address"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "accounts", column = "id", many = @Many(fetchType = FetchType.LAZY, select = "com.dnm.dao.IAccountDao.findAccountByUid"))
    })
    List<User> findAllUserAccounts();
}
