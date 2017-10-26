package com.example1.mapper;

import com.example1.po.PowerBean;

public interface PowerBeanMapper {
    int deleteByPrimaryKey(String id);

    int insert(PowerBean record);

    int insertSelective(PowerBean record);

    PowerBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PowerBean record);

    int updateByPrimaryKey(PowerBean record);
}