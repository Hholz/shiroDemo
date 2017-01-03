package com.hyp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyp.mapper.UsersMapper;
import com.hyp.popj.Users;
import com.hyp.service.UserService;

@Repository
public class UserServiceImpl implements UserService{

	@Autowired
	UsersMapper usersMapper;
	public Users selectUserById(int id) {
		return usersMapper.selectUserById(id);
	}
	@Override
	public Users selectUserByUser(Users users) {
		return usersMapper.selectUserByUser(users);
	}

}
