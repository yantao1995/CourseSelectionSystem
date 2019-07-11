package com.pzhu.controller;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.pzhu.bean.ClassRoomTime;
import com.pzhu.bean.CourseTeacher;
import com.pzhu.bean.SelectTime;
import com.pzhu.bean.User;
import com.pzhu.bean.WeekTimeAndClassRoomTime;
import com.pzhu.service.ClassRoomTimeService;
import com.pzhu.service.CourseService;
import com.pzhu.service.CourseTeacherService;
import com.pzhu.service.SelectTimeService;
import com.pzhu.service.UserService;
import com.pzhu.service.WeekTimeAndClassRoomTimeService;

@Controller
public class AdmController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ClassRoomTimeService classRoomTimeService;
	@Autowired
	private CourseTeacherService courseTecherService;
	@Autowired
	private WeekTimeAndClassRoomTimeService weekTimeAndClassRoomTimeService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private SelectTimeService selectTimeService;
	
	//转发admPage分页的页面
	@RequestMapping(value="/admp1")
	public String getAdmp1(HttpSession session) {
		if (!"教务处".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else {
			return "admp1";
		}
	}
	
	@RequestMapping(value="/admp2")
	public String getAdmp2(HttpSession session) {
		if (!"教务处".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else {
			return "admp2";
		}
	}
	
	@RequestMapping(value="/admp3")
	public String getAdmp3(HttpSession session) {
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
		if (!"教务处".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else if (currentDate.before(selectTimeService.getSelectTime().getStarttime())||currentDate.after(selectTimeService.getSelectTime().getEndtime())) {
			return "error2";
		}else {
			return "admp3";
		}
	}
	
	@RequestMapping(value="/admp4")
	public String getAdmp4(HttpSession session) {
		if (!"教务处".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else {
			return "admp4";
		}
	}
	
	@RequestMapping(value="/admp5")
	public String getAdmp5(HttpSession session) {
		if (!"教务处".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else {
			return "admp5";
		}	
	}
	
	@RequestMapping(value="/admp6")
	public String getAdmp6(HttpSession session) {
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
		if (!"教务处".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else if (currentDate.before(selectTimeService.getSelectTime().getStarttime())||currentDate.after(selectTimeService.getSelectTime().getEndtime())) {
			return "error2";
		}else {
			return "admp6";
		}
	}
	
	@RequestMapping(value="/admp7")
	public String getAdmp7(HttpSession session) {
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
		if (!"教务处".equals(session.getAttribute("type"))) {
			return "nopermission";
		}else if (currentDate.before(selectTimeService.getSelectTime().getStarttime())||currentDate.after(selectTimeService.getSelectTime().getEndtime())) {
			return "error2";
		}else {
			return "admp7";
		}
	}
	
	//获得选课时间
	@RequestMapping(value="/getSelectTime")
	@ResponseBody
	public SelectTime getSelectTime() {
		return selectTimeService.getSelectTime();
	}
	
	//设置选课时间
	@RequestMapping(value="/setSelectTime")
	@ResponseBody
	public String setSelectTime(@RequestParam("a")java.sql.Date a,
			@RequestParam("b")java.sql.Date b) {
		if (selectTimeService.setSelectTime(a, b)) {
			return "设置成功";
		}else {
			return "设置失败";
		}
		
	}
	
	//查询所有学生信息
	@RequestMapping(value="/allStu")
	@ResponseBody
	public List<User> getStuAll() {
		return userService.getStuALL();	
	}
	
	//提交更新学生信息
	@RequestMapping(value="/updateStu")
	@ResponseBody
	public String stuUpdate(@RequestParam("datalist") String listStr) { 
		return userService.upDateStuInfo(listStr);
	}
	
	//删除学生
	@RequestMapping(value="/deleteStu")
	@ResponseBody
	public String stuDelete(@RequestParam("datalist") String listStr) { 
		return userService.deleteStuInfo(listStr);
	}
	
	
	//查询所有老师信息
	@RequestMapping(value="/allTech")
	@ResponseBody
	public List<User> getTechAll() {
		return  userService.getTechALL();
	}
	
	//提交更新老师信息
	@RequestMapping(value="/updateTech")
	@ResponseBody
	public String techUpdate(@RequestParam("datalist") String listStr) { 
		return userService.upDateTechInfo(listStr);
	}
	
	//删除老师
	@RequestMapping(value="/deleteTech")
	@ResponseBody
	public String techDelete(@RequestParam("datalist") String listStr) { 
		return userService.deleteTechInfo(listStr);
	}
	
	//查询所有教室信息
	@RequestMapping(value="/getAllClassRoom")
	@ResponseBody
	public List<ClassRoomTime> getAllClassRoom() {
		return classRoomTimeService.getAllClassRoom();	 
	}
	
	//更新增加教室信息
	@RequestMapping(value="/updateClassRoom")
	@ResponseBody
	public String classRoomUpdate(@RequestParam("datalist") String listStr) { 
		return classRoomTimeService.classRoomUpdate(listStr);
	}
	
	//删除教室
	@RequestMapping(value="/deleteClassRoom")
	@ResponseBody
	public String classRoomDelete(@RequestParam("datalist") String listStr) { 
		return classRoomTimeService.classRoomDelete(listStr);
	}
	
	//获得所有教室课时节次信息
	@RequestMapping(value="/getAllClassRoomTime")
	@ResponseBody
	public List<ClassRoomTime> getAllClassRoomTime() {
		return classRoomTimeService.getAllClassRoomTime();	 
	}
	
	//设置教室课时节次为可选
	@RequestMapping(value="/setcrtAble")
	@ResponseBody
	public String setcrtAble(@RequestParam("datalist") String listStr) {
		return classRoomTimeService.setcrtAble(listStr);	 
	}
	
	//设置教室课时节次为不可选
	@RequestMapping(value="/setcrtUnable")
	@ResponseBody
	public String setcrtUnAble(@RequestParam("datalist") String listStr) {
		return classRoomTimeService.setcrtUnAble(listStr);	 
	}
	
	//获得所有未审核课程
	@RequestMapping(value="/getHandleCourse")
	@ResponseBody
	public List<CourseTeacher> getHandleCourse() {
		return courseTecherService.getHandleCourse();	 
	}
	
	//获得所有新增的未排课的班级
	@RequestMapping(value="/getHandleClasses")
	@ResponseBody
	public List<CourseTeacher> getNewClasses() {
		return courseTecherService.getNewClasses();	 
	}
	
	
	//课程审核通过及教室排课
	@RequestMapping(value="/setPassed")
	@ResponseBody
	public String setCoursePass(@RequestParam("datalist")String listStr,
			@RequestParam("startWeek")String[] startWeek,
			@RequestParam("endWeek")String[] endWeek,
			@RequestParam("classRoomTime")String[] classRoomTime,
			@RequestParam("result")String result) {
		com.alibaba.fastjson.JSONArray list;
		list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
			JSONObject signin = list.getJSONObject(0);
			int cid = (int) signin.get("cid");
			int uid = (int) signin.get("uid");
			int classsort = (int) signin.get("classsort");
			int clessonCount = (int) signin.get("clessionCount"); 
			int count=0;
			for (int i = 0; i < endWeek.length; i++) {  //计算总课时是否准确
				try {
					int start = Integer.parseInt(startWeek[i]);
					int end =Integer.parseInt(endWeek[i]);
					count +=(end-start+1);
				} catch (Exception e) {
					return "请输入数字";
				}								
			}
			if (clessonCount!=count) {
				return "排课总课时与课程所需总课时不一致； \r\n"
						+ "课程所需总课时："+clessonCount+"，当前排课总课时:"+count;
			}
		    boolean flag= weekTimeAndClassRoomTimeService.setCourseStatusPassed(cid,uid,classsort,startWeek, endWeek, classRoomTime,result);									
			if (flag) {
				return "{\"msg\": \"true\"}";
			}
		    return "{\"msg\": \"排课失败：请按顺序设置周节次\"}";	 
	}
	
	//获得classroom和time的select下拉菜单列表
	@RequestMapping(value="/getClassRoomTimeSelect",method=RequestMethod.POST)
	@ResponseBody
	public List<WeekTimeAndClassRoomTime> getClassSelectOption(@RequestParam("datalist")String listStr,
			@RequestParam("start") String start1,
			@RequestParam("end") String end1) {
		com.alibaba.fastjson.JSONArray list;
		list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
		JSONObject signin = list.getJSONObject(0);
		int uid = (int) signin.get("uid");
		try {
			int start = Integer.parseInt(start1);
			int end = Integer.parseInt(end1);
			if (start<=0 || start>end) {
				return null;
			}
			return weekTimeAndClassRoomTimeService.getTimeAndClassRoomTimes(start,end,uid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;			
		}	 
	}
	

	//审核不通过
	@RequestMapping(value="/setNoPassed")
	@ResponseBody
	public String setCoursePass(@RequestParam("datalist")String listStr,
			@RequestParam("result")String result) {
		com.alibaba.fastjson.JSONArray list;
		list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
		JSONObject signin = list.getJSONObject(0);
		int cid = (int) signin.get("cid");
		if (courseService.setCstatus(cid)&&courseService.setResult(result, cid)) {
			return "{\"msg\": \"true\"}";
		}
	    return "{\"msg\": \"操作失败\"}";	 
	}
	
	
	//新增班级排课处理
	@RequestMapping(value="/setPassed2")
	@ResponseBody
	public String setClassPass(@RequestParam("datalist")String listStr,
			@RequestParam("startWeek")String[] startWeek,
			@RequestParam("endWeek")String[] endWeek,
			@RequestParam("classRoomTime")String[] classRoomTime) {
		com.alibaba.fastjson.JSONArray list;
		list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
			JSONObject signin = list.getJSONObject(0);
			int cid = (int) signin.get("cid");
			int uid = (int) signin.get("uid");
			int classsort = (int) signin.get("classsort");
			int clessonCount = (int) signin.get("clessionCount"); 
			int count=0;
			for (int i = 0; i < endWeek.length; i++) {  //计算总课时是否准确
				try {
					int start = Integer.parseInt(startWeek[i]);
					int end =Integer.parseInt(endWeek[i]);
					count +=(end-start+1);
				} catch (Exception e) {
					return "请输入数字";
				}								
			}
			if (clessonCount!=count) {
				return "排课总课时与课程所需总课时不一致； \r\n"
						+ "课程所需总课时："+clessonCount+"，当前排课总课时:"+count;
			}
		    boolean flag= weekTimeAndClassRoomTimeService.setClassStatusPassed(cid, uid, classsort, startWeek, endWeek, classRoomTime);							
			if (flag) {
				return "{\"msg\": \"true\"}";
			}
		    return "{\"msg\": \"排课失败：请按顺序设置周节次\"}";	 
	}
	
	//新增班级审核不通过
	@RequestMapping(value="/setNoPassed2")
	@ResponseBody
	public String setClasssPass(@RequestParam("datalist")String listStr) {
		com.alibaba.fastjson.JSONArray list;
		list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
		JSONObject signin = list.getJSONObject(0);
		int ctid = (int) signin.get("ctid");
		if (courseTecherService.setNoPassCTStatus(ctid)) {
			return "{\"msg\": \"true\"}";
		}
	    return "{\"msg\": \"操作失败\"}";	 
	}
	
	//查看所有班级
	@RequestMapping(value="getAllTeacherClasses")
	@ResponseBody
	public List<CourseTeacher> getAllTeacherClasses(){
		return courseTecherService.getAllTeacherClasses();
	}
	
	//查看开班人数不足的班级
	@RequestMapping(value="getlimitTeacherClasses")
	@ResponseBody
	public List<CourseTeacher> getlimitTeacherClasses(){
		return courseTecherService.getlimitTeacherClasses();
	}
	
	//关闭班级
	@RequestMapping(value="closeclass")
	@ResponseBody
	public String closeclass(@RequestParam("cid")int cid,
			@RequestParam("classsort")int classsort){
		if (courseTecherService.closeclass(cid, classsort)) {
			return "班级关闭成功！";
		}else {
			return "班级 关闭失败！";
		}
	}

}
