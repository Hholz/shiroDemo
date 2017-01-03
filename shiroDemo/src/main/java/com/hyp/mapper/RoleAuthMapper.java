package com.hyp.mapper;

import com.hyp.popj.RoleAuth;

public interface RoleAuthMapper {
    int insert(RoleAuth record);

    int insertSelective(RoleAuth record);
}