package com.hyp.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hyp.mapper.UsersMapper;
import com.hyp.popj.Users;
import com.hyp.service.StudentService;
import com.hyp.service.UserService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext-*.xml")
public class TestUserSelect {

	@Autowired
	UsersMapper usersMapper;
	
	@Autowired
	UserService userService;
	
	@Test
	public void test(){
		Users user = usersMapper.selectUserById(1);
		Users user2 = userService.selectUserById(1);
		System.out.println(user.toString()+user2.toString());
	}
}