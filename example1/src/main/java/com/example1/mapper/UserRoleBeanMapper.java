package com.example1.mapper;

import com.example1.po.UserRoleBean;

public interface UserRoleBeanMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserRoleBean record);

    int insertSelective(UserRoleBean record);

    UserRoleBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRoleBean record);

    int updateByPrimaryKey(UserRoleBean record);
}