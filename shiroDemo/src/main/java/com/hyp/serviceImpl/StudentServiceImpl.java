package com.hyp.serviceImpl;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyp.mapper.StudentMapper;
import com.hyp.popj.Student;
import com.hyp.service.StudentService;

/**
 * 注解开发，加入@Repository可以使spring注入SqlFactory和SqlSession
 * 继承SqlSessionDaoSupport ，通过this.getSqlSession()来获取SqlSession
 * @author HYP
 *
 */
@Repository
public class StudentServiceImpl  implements StudentService{

	@Autowired
	StudentMapper studentMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return studentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Student record) {
		return studentMapper.insert(record);
	}

	@Override
	public int insertSelective(Student record) {
		return studentMapper.insertSelective(record);
	}

	@Override
	public Student selectByPrimaryKey(Integer id) {
		return studentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Student record) {
		return studentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Student record) {
		return studentMapper.updateByPrimaryKey(record);
	}
	@Override
	public List<Student> selectStudentAll() {
		return studentMapper.selectStudentAll();
	}

	@Override
	public List<Student> selectByDynamic(Student student) {
		return studentMapper.selectByDynamic(student);
	}
	

	
}
