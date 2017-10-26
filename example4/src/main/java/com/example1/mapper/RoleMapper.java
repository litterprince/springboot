package com.example1.mapper;

import com.example1.po.RoleBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    @Select("SELECT * FROM t_user_role WHERE user_id = #{id}")
    List<RoleBean> getRolesByUser(String id);
}
