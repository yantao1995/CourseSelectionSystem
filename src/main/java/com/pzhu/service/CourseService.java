package com.pzhu.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pzhu.bean.Course;
import com.pzhu.mapper.CourseMapper;


@Service
public class CourseService{
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Transactional
	public void insertNewLesson(int uid,String cname,BigDecimal score,String csortname,String ctype,
			int climitCount,int cmaxCount,int clessionCount,String cdesc) throws Exception {
		courseMapper.insertNewLesson(cname, score, cdesc, climitCount, cmaxCount, clessionCount, csortname, ctype);
		courseMapper.insertNewLessonAndTech(cname,cdesc,uid);
	}
	
	public boolean getCname(String cname) {
		Course course = courseMapper.getCname(cname);
		if (course!=null) {
			return true; //存在了
		}
			return false;
	}
	
	public Course getOnlyCourse(){
		return courseMapper.getOnlyCourse();
	}
	
	public List<Course> getMyCourses(int uid){
		return courseMapper.getMyCourse(uid);
	}
	
	public List<Course> getMyPassCourse(int uid){
		return courseMapper.getMyPassCourse(uid);
	}
	
	public List<Course> getMyProcessCourse(int uid){
		return courseMapper.getMyProcessCourse(uid);
	}
	
	public List<Course> getMyNoPassCourse(int uid){
		return courseMapper.getMyNoPassCourse(uid);
	}
	
	public List<Course> getPassedCourseName() {
		return courseMapper.getPassedCourseName();
	}
	
	public Course getCourseByName(String cname) {
		return courseMapper.getCourseByName(cname);
	}
	
	//设置课程未通过
	public boolean setCstatus(int cid) {
		return courseMapper.setCstatus(cid);
	}
	//填写审批意见
	public boolean setResult(String result,int cid) {
		return courseMapper.setResult(result, cid);
	}
}
