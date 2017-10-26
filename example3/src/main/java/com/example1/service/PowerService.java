package com.example1.service;

import com.example1.mapper.PowerMapper;
import com.example1.po.PowerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PowerService {
    @Autowired
    PowerMapper powerMapper;

    public List<PowerBean> getPowersByRole(String id){
        return powerMapper.getPowersByRole(id);
    }
}
