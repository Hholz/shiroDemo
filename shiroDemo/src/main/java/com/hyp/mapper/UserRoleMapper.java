package com.hyp.mapper;

import com.hyp.popj.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}