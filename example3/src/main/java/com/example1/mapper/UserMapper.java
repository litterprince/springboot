package com.example1.mapper;

import com.example1.po.UserBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

/*@Mapper Application类里使用了注解扫描*/
public interface UserMapper {
    final String colunms = "id,user_name as userName,password,real_name as realName,sex,create_time as createTime";

    @Select("SELECT " + colunms + " FROM t_user ORDER BY create_time desc")
    List<UserBean> getAll();

    @Select("SELECT count(1) FROM t_user")
    int getCount();

    @Select("SELECT " + colunms + " FROM t_user WHERE id = #{id}")
    UserBean getOne(String id);

    @Insert("INSERT INTO t_user(id,user_name,password,real_name,sex,create_time) " +
        "VALUES(#{id}, #{userName}, #{password}, #{realName}, #{sex}, #{createTime})")
    void insert(UserBean user);

    @Update("UPDATE t_user SET user_name=#{userName},password=#{password},real_name=#{realName}," +
            "sex=#{sex},create_time=#{createTime} WHERE id =#{id}")
    void update(UserBean user);

    @Delete("DELETE FROM t_user WHERE id =#{id}")
    void delete(String id);
}