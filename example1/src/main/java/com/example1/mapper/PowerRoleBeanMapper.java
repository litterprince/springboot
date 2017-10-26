package com.example1.mapper;

import com.example1.po.PowerRoleBean;

public interface PowerRoleBeanMapper {
    int deleteByPrimaryKey(String id);

    int insert(PowerRoleBean record);

    int insertSelective(PowerRoleBean record);

    PowerRoleBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PowerRoleBean record);

    int updateByPrimaryKey(PowerRoleBean record);
}