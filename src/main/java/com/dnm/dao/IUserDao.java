package com.dnm.dao;

import com.dnm.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

// 开启二级缓存
@CacheNamespace(blocking = true)
public interface IUserDao {
    /**
     * 查询所有数据
     * @return
     */
    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id=#{id}")
    User findUserById(Integer id);

    @Select("select * from user where username like #{username}")
    List<User> findUserByName(String username);

    @Insert("insert into user(username, address, sex, birthday) values(#{username}, #{address}, #{sex}, #{birthday})")
    void saveUser(User user);

    @Update("update user set username=#{username}, address=#{address}, sex=#{sex}, birthday=#{birthday} where id=${id}")
    void updateUser(User user);

    @Delete("delete from user where id=#{id}")
    void deleteUserById(Integer id);

    @Select("select count(id) from user")
    int findTotal();
}
