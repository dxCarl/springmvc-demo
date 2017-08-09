package com.springmvc.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.demo.model.Student;
import com.springmvc.demo.util.Constant;

@Controller
@RequestMapping(value="/student")
public class StudentController {

	private static List<Student> studentList=new ArrayList<Student>();
	
	static{
		studentList.add(new Student(1,"张三",11));
		studentList.add(new Student(2,"李四",12));
		studentList.add(new Student(3,"王五",13));
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView mav=new ModelAndView();
		mav.addObject("studentList", studentList);
		mav.setViewName("student/list");
		return mav;
	}
	
	@RequestMapping(value="/to_add",method=RequestMethod.GET)
	public ModelAndView toAdd(@RequestParam(value="id",required=false) String id){
		ModelAndView mav=new ModelAndView();
		mav.setViewName("student/add");			
		return mav;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Student student){
		int size = studentList.size();
		int id = 0;
		if(size == 0) {
			id = 1;
		} else {
			id = studentList.get(size-1).getId() + 1;
		}
		student.setId(id);
		studentList.add(student);
		Constant.MY_LOG.debug("添加学生：" + student);
		return "redirect:/student/list.do";
		//return "forward:/student/list.do";
	}
	
	@RequestMapping(value="/to_update",method=RequestMethod.GET)
	public ModelAndView toUpdate(@RequestParam(value="id",required=false) int id){
		ModelAndView mav=new ModelAndView();
		for(int i=0;i<studentList.size(); i++) {
			Student s = studentList.get(i);
			if(id == s.getId()) {
				mav.addObject("student", studentList.get(i) );
				break;
			}
		}
		mav.setViewName("student/update");
		return mav;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(Student student){
		int studentId = student.getId();
		for(int i=0;i<studentList.size(); i++) {
			Student s = studentList.get(i);
			if(studentId == s.getId()) {
				s.setName(student.getName());
				s.setAge(student.getAge());
				break;
			}
		}
		return "redirect:/student/list.do";
		//return "forward:/student/list.do";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("id") int id){
		for(int i=0;i<studentList.size(); i++) {
			Student s = studentList.get(i);
			if(id == s.getId()) {
				studentList.remove(i);
				break;
			}
		}
		return "redirect:/student/list.do";
		//return "forward:/student/list.do";
	}
}
