package com.example1.mapper;

import com.example1.po.RoleBean;

public interface RoleBeanMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleBean record);

    int insertSelective(RoleBean record);

    RoleBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleBean record);

    int updateByPrimaryKey(RoleBean record);
}