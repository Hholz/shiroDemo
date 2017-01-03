package com.hyp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hyp.popj.Student;
import com.hyp.service.StudentService;
import com.hyp.serviceImpl.StudentServiceImpl;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	@RequestMapping("/findStudentList")
	public String findStudentList(Model model,Student student){
		List<Student> sList = new ArrayList<>();
		if(student!=null){
			sList = studentService.selectByDynamic(student);
		}else{
			sList = studentService.selectStudentAll();
		}
		model.addAttribute("sList", sList);
		model.addAttribute("student", student);
		return "StudentList";
	}
	@RequestMapping(value = "/{id}", method = { RequestMethod.GET })
	public String infoStudent(Model model,@PathVariable("id") Integer id){  
		Student student = studentService.selectByPrimaryKey(id);
		model.addAttribute("student", student);
		return "InfoStudent";
	}
	@RequestMapping(value = "/{id}", method = { RequestMethod.PUT })
	public String updateStudent(Model model,Student student){  
		studentService.updateByPrimaryKeySelective(student);
		return "redirect:findStudentList";
	}
	@RequestMapping(value = "/{id}", method = { RequestMethod.DELETE })
	public String deleteStudent(Model model,@PathVariable("id")Integer id){  
		studentService.deleteByPrimaryKey(id);
		return "redirect:findStudentList";
	}
}
