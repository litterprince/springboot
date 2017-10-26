package com.example1.mapper;

import com.example1.po.UserBean;

import java.util.List;

public interface UserBeanMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserBean record);

    int insertSelective(UserBean record);

    UserBean selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserBean record);

    int updateByPrimaryKey(UserBean record);

    //以下手动增加
    List<UserBean> selectUsersSelective(UserBean user);

    List<UserBean> selectAll();

    int getCount();

    void batchDeleteUsers(List<String> list);
}