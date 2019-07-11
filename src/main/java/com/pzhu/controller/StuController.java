package com.pzhu.controller;


import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pzhu.bean.ClassTable;
import com.pzhu.bean.CourseTeacher;
import com.pzhu.service.CourseTeacherService;
import com.pzhu.service.SelectTimeService;

@Controller
public class StuController {
	@Autowired
	private CourseTeacherService courseTeacherService;
	@Autowired
	private SelectTimeService selectTimeService;
	
	//转发stuPage分页的页面
	@RequestMapping(value="/stup1")
	public String getStdp1(HttpSession session) {
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
		if (!"学生".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else if (currentDate.before(selectTimeService.getSelectTime().getStarttime())||currentDate.after(selectTimeService.getSelectTime().getEndtime())) {
			return "error2";
		}else {
			return "stup1";
		}		
	}
	
	@RequestMapping(value="/stup2")
	public String getStdp2(HttpSession session) {
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
		if (!"学生".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else if (currentDate.before(selectTimeService.getSelectTime().getStarttime())||currentDate.after(selectTimeService.getSelectTime().getEndtime())) {
			return "error2";
		}else {
			return "stup2";
		}
	}
	
	@RequestMapping(value="/stup3")
	public String getStdp3(HttpSession session) {
		if (!"学生".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else {
			return "stup3";
		}
	}
	
	//全部课程
	@RequestMapping(value="/stucourse")
	@ResponseBody
	public List<CourseTeacher> getStuCourse() {		
		return courseTeacherService.getCourseTeacher();
	}
	
	//获取某cid的所有班的教室排课信息
	@RequestMapping(value="/getclassesByCourse/{cid}")
	@ResponseBody
	public List<CourseTeacher> getAllClassInfoByCourseId(@PathVariable("cid") int cid) {	
		return courseTeacherService.getAllClassInfoByCourseId(cid);
	}
	
	//查询未选修过的课程
	@RequestMapping(value="/stunevercourse")
	@ResponseBody
	public List<CourseTeacher> getNeverLearnCourse(HttpSession session){
		int uid = (int) session.getAttribute("id");		
		return courseTeacherService.getNeverLearnCourse(uid);
	}
	
	//选课选班级提交
	@RequestMapping(value="/stuChooseCourse")
	@ResponseBody
	public synchronized String stuChooseCourse(@RequestParam("datalist") String listStr,
			@RequestParam("datalist2") String listStr2,
			HttpSession session) { //插入选课表数据
		int uid = (int) session.getAttribute("id");
		com.alibaba.fastjson.JSONArray list,list2;
		list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
		JSONObject signin = list.getJSONObject(0);
		int cid = (int) signin.get("cid"); 
		Object obj = (Object) signin.get("score");
		String score = obj.toString();
		list2 = com.alibaba.fastjson.JSONArray.parseArray(listStr2);
		JSONObject signin2 = list2.getJSONObject(0);
		int classsort = (int) signin2.get("classsort"); 
		return courseTeacherService.stuChooseCourse(uid, cid, classsort,score);
	}
	
	//退选课程列表查询
	@RequestMapping(value="getMyChooseCourse")
	@ResponseBody
	public List<CourseTeacher> getMyChooseCourse(HttpSession session){
		int uid = (int) session.getAttribute("id");
		return courseTeacherService.getMyChooseCourse(uid);
	}
	
	//退选提交
	@RequestMapping(value="getBackCourse")
	@ResponseBody
	public String getBackCourse(@RequestParam("datalist")String listStr,
			HttpSession session) {
		int uid = (int) session.getAttribute("id");
		if (courseTeacherService.deleteChooseCourse(uid, listStr)) {
			return "退选成功!";
		}else {
			return "退选失败!";
		}
	}
	
	//查看课表
	@RequestMapping(value="getClassTable")
	@ResponseBody
	public List<ClassTable> getClassTable(HttpSession session){
		int uid = (int) session.getAttribute("id");
		return courseTeacherService.getClassTables(uid);
	}
}
