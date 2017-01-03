package com.hyp.service;

import java.util.List;

import com.hyp.popj.Student;

public interface StudentService {

	    int deleteByPrimaryKey(Integer id);

	    int insert(Student record);

	    int insertSelective(Student record);

	    Student selectByPrimaryKey(Integer id);

	    int updateByPrimaryKeySelective(Student record);

	    int updateByPrimaryKey(Student record);
	    
	    List<Student> selectStudentAll();
	  //动态查询
	    List<Student> selectByDynamic(Student student);
	    
}
