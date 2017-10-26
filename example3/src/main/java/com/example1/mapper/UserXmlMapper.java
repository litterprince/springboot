package com.example1.mapper;

import com.example1.po.UserBean;

import java.util.List;

public interface UserXmlMapper {

    List<UserBean> getUsersByParam(UserBean user);

    void batchInsertUsers(List<UserBean> users);

    void batchDeleteUsers(List<String> ids);
}
