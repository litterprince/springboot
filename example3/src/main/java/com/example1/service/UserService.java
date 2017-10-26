package com.example1.service;

import com.example1.mapper.UserMapper;
import com.example1.mapper.UserXmlMapper;
import com.example1.po.UserBean;
import com.example1.tools.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserXmlMapper userXmlMapper;

    public List<UserBean> getAll(){
        return userMapper.getAll();
    }

    public int getCount(){
        return userMapper.getCount();
    }

    public UserBean getOne(String id){
        return  userMapper.getOne(id);
    }

    public void insert(UserBean user){
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        userMapper.insert(user);
    }

    public void update(UserBean user){
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        userMapper.update(user);
    }

    public void delete(String id){
        userMapper.delete(id);
    }

    public void batchDeleteUsers(String ids) {
        String[] arr = ids.split(",");
        List<String> list = Arrays.asList(arr);
        userXmlMapper.batchDeleteUsers(list);
    }

    public List<UserBean> getUsersByParam(UserBean user){
        if(user.getPassword() != null) {
            user.setPassword(MD5Util.getMD5(user.getPassword()));
        }
        return  userXmlMapper.getUsersByParam(user);
    }
}
