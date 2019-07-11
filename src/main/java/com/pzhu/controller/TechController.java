package com.pzhu.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pzhu.bean.ClassTable;
import com.pzhu.bean.Course;
import com.pzhu.bean.CourseTeacher;
import com.pzhu.bean.User;
import com.pzhu.service.CourseService;
import com.pzhu.service.CourseTeacherService;
import com.pzhu.service.SelectTimeService;
import com.pzhu.service.UserService;

@Controller
public class TechController {
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseTeacherService courseTeacherService;
	@Autowired
	private UserService userService;
	@Autowired
	private SelectTimeService selectTimeService;
	
	//转发techPage分页的页面
	@RequestMapping(value="/techp1")
	public String getTechp1(HttpSession session) {
		if (!"教师".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else {
			return "techp1";
		}
	}
	
	@RequestMapping(value="/techp2")
	public String getTechp2(HttpSession session) {
		if (!"教师".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else {
			return "techp2";
		}
	}
	
	@RequestMapping(value="/techp3")
	public String getTechp3(HttpSession session) {
		if (!"教师".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else {
			return "techp3";
		}
	}
	
	@RequestMapping(value="/techp4")
	public String getTechp4(HttpSession session) {
		if (!"教师".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else {
			return "techp4";
		}
	}
	
	@RequestMapping(value="/techp5")
	public String getTechp5(HttpSession session) {
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
		if (!"教师".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else if (currentDate.before(selectTimeService.getSelectTime().getStarttime())||currentDate.after(selectTimeService.getSelectTime().getEndtime())) {
			return "error2";
		}else {
			return "techp5";
		}
	}
	
	@RequestMapping(value="/techp6")
	public String getTechp6(HttpSession session) {
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
		if (!"教师".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else if (currentDate.before(selectTimeService.getSelectTime().getStarttime())||currentDate.after(selectTimeService.getSelectTime().getEndtime())) {
			return "error2";
		}else {
			return "techp6";
		}
	}
	
	@RequestMapping(value="/techp7")
	public String getTechp7(HttpSession session) {
		if (!"教师".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else {
			return "techp7";
		}
	}
	
	@RequestMapping(value="/techp8")
	public String getTechp8(HttpSession session) {
		if (!"教师".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else {
			return "techp8";
		}
	}
	
	@RequestMapping(value="/techp9")
	public String getTechp9(HttpSession session) {
		if (!"教师".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else {
			return "techp9";
		}
	}
	
	@RequestMapping(value="/techp10")
	public String getTechp10(HttpSession session) {
		if (!"教师".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else {
			return "techp10";
		}
	}
	
	//新增课程
	@RequestMapping(value="insertNewLesson")
	@ResponseBody
	public String insertNewLesson(@RequestParam("cname") String cname,
			@RequestParam("score") String scoreString	,
			@RequestParam("csortname") String csortname,
			@RequestParam("ctype") String ctype	,
			@RequestParam("climitCount") String climitCountString,
			@RequestParam("cmaxCount") String cmaxCountsString	,
			@RequestParam("clessionCount") String clessionCountsString,
			@RequestParam("cdesc") String cdesc,
			HttpSession session) {
		BigDecimal score;
		int climitCount,cmaxCount,clessionCount,uid;
		try {
			score = new BigDecimal(scoreString);
			climitCount = Integer.parseInt(climitCountString);
			cmaxCount = Integer.parseInt(cmaxCountsString);
			clessionCount = Integer.parseInt(clessionCountsString);
			uid = (int) session.getAttribute("id");
			if (courseService.getCname(cname)) {
				return "该课程已经存在，请勿重复添加";
			}else {
				courseService.insertNewLesson(uid, cname, score, csortname, ctype, climitCount, cmaxCount, clessionCount, cdesc);
			}
		} catch (ClassCastException e) {
			return "请准确填写课程信息,或者权限不足";
		}catch (Exception e) {
			return "新增课程失败";
		}
		
		return "新增课程已提交审核";
	}	
	
	//新增课程中查看可选课程
	@RequestMapping(value="/getPassCourseName")
	@ResponseBody
	public List<Course> getPassedCourseName(){
		return courseService.getPassedCourseName();
	}
	
	@RequestMapping(value="/myCourse")
	@ResponseBody
	public List<Course> getMyCourse(HttpSession session) {
		int uid = (int) session.getAttribute("id");
		return courseService.getMyCourses(uid);
	}
	
	@RequestMapping(value="/myPassCourse")
	@ResponseBody
	public List<Course> getmyPassCourse(HttpSession session) {
		int uid = (int) session.getAttribute("id");
		return courseService.getMyPassCourse(uid);
	}
	
	@RequestMapping(value="/myProcessCourse")
	@ResponseBody
	public List<Course> getProcessCourse(HttpSession session) {
		int uid = (int) session.getAttribute("id");
		return courseService.getMyProcessCourse(uid);
	}
	
	@RequestMapping(value="/myNoPassCourse")
	@ResponseBody
	public List<Course> getNoPassCourse(HttpSession session) {
		int uid = (int) session.getAttribute("id");
		return courseService.getMyNoPassCourse(uid);
	}
	
	@RequestMapping(value="insertNewClass") //增加班级
	@ResponseBody
	public String insertNewClass(@RequestParam("cname")String cname,
			HttpSession session) {
		int uid = (int) session.getAttribute("id");
		Course course = courseService.getCourseByName(cname);
		if (course==null) {
			return "课程不存在,操作不合法。";
		}
		int cid = course.getCid();
		int maxSort= courseTeacherService.getMaxClassSort(cid);
		maxSort++;
		if (courseTeacherService.insertNewClass(cid, uid, maxSort)) {
			return "新增班级请求提交审核!";
		}else {
			return "新增班级请求提交失败!";
		}		
	}
	
	@RequestMapping(value="myClasses")
	@ResponseBody
	public List<CourseTeacher> getMyClasses(HttpSession session) {
		int uid = (int) session.getAttribute("id");
		return courseTeacherService.getMyClasses(uid);
	}
	
	//获得所选班级的学生
	@RequestMapping(value="myClassesperson/{cid}/{classsort}")
	@ResponseBody
	public List<User> getmyClassesperson(@PathVariable("cid") int cid,
			@PathVariable("classsort") int classsort){
		return userService.getmyClassesperson(cid, classsort);
	}
	
	//开班未通过
	@RequestMapping(value="myNoPassClasses")
	@ResponseBody
	public List<CourseTeacher> myNoPassClasses(HttpSession session) {
		int uid = (int) session.getAttribute("id");
		return courseTeacherService.getNoPassClasses(uid);
	}
	
	//班级已关闭
	@RequestMapping(value="myclosedClasses")
	@ResponseBody
	public List<CourseTeacher> myclosedClasses(HttpSession session) {
		int uid = (int) session.getAttribute("id");
		return courseTeacherService.myclosedClasses(uid);
	}
	
	//查看课表 老师
	@RequestMapping(value="getTechClassTable")
	@ResponseBody
	public List<ClassTable> getTechClassTable(HttpSession session){
		int uid = (int) session.getAttribute("id");
		return courseTeacherService.getTechClassTables(uid);
	}
	
	//查看已经关闭的开班
}
