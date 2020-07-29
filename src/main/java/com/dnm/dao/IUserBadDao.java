package com.dnm.dao;

import com.dnm.domain.User;
import com.dnm.domain.UserBad;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserBadDao {
    @Select("select * from user")
    @Results(id="userMapper", value = {
            @Result(id=true, property = "userId", column = "id"),
            @Result(property = "userName", column = "username"),
            @Result(property = "userSex", column = "sex"),
            @Result(property = "userAddress", column = "address"),
            @Result(property = "userBirthday", column = "birthday")
    })
    List<UserBad> findAll();

    @Select("select * from user where id=#{id}")
    @ResultMap(value = {"userMapper"})
    UserBad findUserById(Integer id);

    @Select("select * from user where username like #{username}")
    List<UserBad> findUserByName(String username);

    @Insert("insert into user(username, address, sex, birthday) values(#{username}, #{address}, #{sex}, #{birthday})")
    void saveUser(UserBad user);

    @Update("update user set username=#{username}, address=#{address}, sex=#{sex}, birthday=#{birthday} where id=${id}")
    void updateUser(UserBad user);

    @Delete("delete from user where id=#{id}")
    void deleteUserById(Integer id);

    @Select("select count(id) from user")
    int findTotal();
}
