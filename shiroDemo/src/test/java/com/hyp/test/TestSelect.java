package com.hyp.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hyp.popj.Student;
import com.hyp.service.StudentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext-*.xml")
public class TestSelect {

	
	@Autowired
	StudentService studentService;
	@Before
	public void setUp() throws Exception {
		
	}
	
	@Test
	public void test() { 
		Student student = studentService.selectByPrimaryKey(1);
		System.out.println(student.toString());
	}
	@Test
	public void testSelectByDynamic() { 
		Student student = new Student();
		student.setId(1);
		List<Student> list = studentService.selectByDynamic(student);
		System.out.println(list.toString());
	}
	

}
