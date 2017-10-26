package com.example1.service;

import com.example1.mapper.UserXmlMapper;
import com.example1.po.UserBean;
import com.example1.tools.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {
    @Autowired
    UserXmlMapper userXmlMapper;

    public List<UserBean> getUsersByParam(UserBean user){
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        return  userXmlMapper.getUsersByParam(user);
    }
}
