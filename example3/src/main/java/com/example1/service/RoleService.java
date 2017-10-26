package com.example1.service;

import com.example1.mapper.RoleMapper;
import com.example1.po.RoleBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {
    @Autowired
    RoleMapper roleMapper;

    public List<RoleBean> getRolesByUser(String id){
        return roleMapper.getRolesByUser(id);
    }
}
