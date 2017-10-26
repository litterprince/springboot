package com.example1.mappercluster;

import com.example1.po.UserBean;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/*@Mapper Application类里使用了注解扫描*/
public interface UserMapperC {

    @Select("SELECT * FROM users ORDER BY createTime desc")
    List<UserBean> getAll();

    @Select("SELECT count(1) FROM users")
    int getCount();

    @Select("SELECT * FROM users WHERE id = #{id}")
    UserBean getOne(String id);

    @Insert("INSERT INTO users(id,userName,password,realName,sex,createTime) " +
        "VALUES(#{id}, #{userName}, #{password}, #{realName}, #{sex}, #{createTime})")
    void insert(UserBean user);

    @Update("UPDATE users SET userName=#{userName},password=#{password},realName=#{realName}," +
            "sex=#{sex},createTime=#{createTime} WHERE id =#{id}")
    void update(UserBean user);

    @Delete("DELETE FROM users WHERE id =#{id}")
    void delete(String id);
}