package com.example1.mapper;

import com.example1.po.PowerBean;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PowerMapper {
    @Select("SELECT * FROM t_power_role WHERE role_id = #{id}")
    List<PowerBean> getPowersByRole(String id);
}
