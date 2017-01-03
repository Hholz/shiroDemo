package com.hyp.service;

import com.hyp.popj.Users;

public interface UserService {

	public Users selectUserById(int id);
	
	Users selectUserByUser(Users users);
}
