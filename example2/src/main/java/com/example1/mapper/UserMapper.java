package com.example1.mapper;

import com.example1.po.UserBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {

    @Select("SELECT * FROM users_1 ORDER BY create_time desc")
    List<UserBean> getAll();

    @Select("SELECT count(1) FROM users_1")
    int getCount();

    @Select("SELECT * FROM users_1 WHERE id = #{id}")
    UserBean getOne(String id);

    @Insert("INSERT INTO users_1(id,user_name,password,real_name,sex,create_time) " +
        "VALUES(#{id}, #{userName}, #{password}, #{realName}, #{sex}, #{createTime})")
    void insert(UserBean user);

    @Update("UPDATE users_1 SET user_name=#{userName},password=#{password},real_name=#{realName}," +
            "sex=#{sex},create_time=#{createTime} WHERE id =#{id}")
    void update(UserBean user);

    @Delete("DELETE FROM users_1 WHERE id =#{id}")
    void delete(String id);
}