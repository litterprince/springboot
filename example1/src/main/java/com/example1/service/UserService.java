package com.example1.service;

import com.example1.mapper.UserBeanMapper;
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
    UserBeanMapper userMapper;

    public UserBean getOne(String id){
        return  userMapper.selectByPrimaryKey(id);
    }

    public void insert(UserBean user){
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        userMapper.insert(user);
    }

    public void update(UserBean user){
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        userMapper.updateByPrimaryKey(user);
    }

    public void delete(String id){
        userMapper.deleteByPrimaryKey(id);
    }

    public List<UserBean> selectUsersSelective(UserBean user){
        if(user.getPassword() != null) {
            user.setPassword(MD5Util.getMD5(user.getPassword()));
        }
        return  userMapper.selectUsersSelective(user);
    }

    public List<UserBean> selectAll(){
        return userMapper.selectAll();
    }

    public int getCount(){
        return userMapper.getCount();
    }

    public void batchDeleteUsers(String ids) {
        String[] arr = ids.split(",");
        List<String> list = Arrays.asList(arr);
        userMapper.batchDeleteUsers(list);
    }
}
