package com.dnm.dao;

import com.dnm.domain.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserDao {
    /**
     * 查询所有数据
     * @return
     */
    @Select("select * from user")
    List<User> findAll();
}
