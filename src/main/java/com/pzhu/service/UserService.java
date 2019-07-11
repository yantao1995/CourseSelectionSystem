package com.pzhu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.pzhu.bean.User;
import com.pzhu.mapper.CourseTeacherMapper;
import com.pzhu.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CourseTeacherMapper courserTeacherMapper;
	
	@Transactional
	public String deleteStuInfo(String listStr) {
		com.alibaba.fastjson.JSONArray list;
		list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
		for (int i = 0; i < list.size(); i++) { //获取主键 uid
			JSONObject signin = list.getJSONObject(i);
			int uid =Integer.parseInt(signin.getString("uid")); 
			User user = userMapper.getStu(uid);
			if (user!=null) { //用户存在，删除选课表和用户表
				courserTeacherMapper.deleteStuAndCt_stuInfo(uid);
				userMapper.deleteStuInfo(uid);
			}else {
				return "{\"msg\":\"false\"}";//消息显示				
			}									
		}
		return "{\"msg\":\"true\"}";//消息显示
	}
	
	public String upDateStuInfo(String listStr) {
		com.alibaba.fastjson.JSONArray list;
		list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
		for (int i = 0; i < list.size(); i++) { //获取主键 uid
			JSONObject signin = list.getJSONObject(i);
			try {
				int uid =Integer.parseInt(signin.getString("uid")); 
				String uname = (String) signin.get("uname");
				String sex = (String) signin.get("sex"); 
				String upassword = (String) signin.get("upassword");
				int ugrade = Integer.parseInt(signin.getString("ugrade"));
				String upartment = (String) signin.get("upartment");
				int utelephone = Integer.parseInt(signin.getString("utelephone"));
				User user = userMapper.getStu(uid);
				User user2 = userMapper.getTher(uid); //不能和老师的重复
				User user3 = userMapper.getAdm(uid);
				if (user2!=null) {
					return "{\"msg\": \"请核对填写的id是否为已有教师id，后续操作失效\"}";
				}
				if (user3!=null) {
					return "{\"msg\": \"请核对填写的id是否为已有管理员id，后续操作失效\"}";
				}
				if (user!=null) { //用户存在则先删除
					userMapper.deleteStuInfo(uid);
				}
				userMapper.insertStuInfo(uid, uname, sex, upassword, ugrade, upartment, utelephone);
				System.out.println(uid);
			} catch (Exception e) {
				e.printStackTrace();
				return "{\"msg\": \"请核对填写的信息是否准确，后续操作失效\"}";
			}						
		}
		return "{\"msg\":\"true\"}";//消息显示
	}
	
	@Transactional
	public String deleteTechInfo(String listStr) {
		com.alibaba.fastjson.JSONArray list;
		list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
		for (int i = 0; i < list.size(); i++) { //获取主键 uid
			JSONObject signin = list.getJSONObject(i);			
			int uid =Integer.parseInt(signin.getString("uid")); 
			User user = userMapper.getTher(uid);
			if (user!=null) { //用户存在，删除
				if (courserTeacherMapper.getTeacherCourseAndClass(uid)>0) {
					throw new RuntimeException("删除失败！教师编号："+uid+"已经进入排课管理，无法删除");			
				}else {
					userMapper.deleteTechInfo(uid);
				}				
			}else {
				return "{\"msg\":\"false\"}";//消息显示				
			}											
		}
		return "{\"msg\":\"true\"}";//消息显示
	}
	
	public String upDateTechInfo(String listStr) {
		com.alibaba.fastjson.JSONArray list;
		list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
		for (int i = 0; i < list.size(); i++) { //获取主键 uid
			JSONObject signin = list.getJSONObject(i);
			try {
				int uid =Integer.parseInt(signin.getString("uid")); 
				String uname = (String) signin.get("uname");
				String sex = (String) signin.get("sex"); 
				String upassword = (String) signin.get("upassword");
				String upartment = (String) signin.get("upartment");
				int utelephone = Integer.parseInt(signin.getString("utelephone"));
				User user = userMapper.getTher(uid);
				User user2 = userMapper.getStu(uid); //不能和老师的重复
				User user3 = userMapper.getAdm(uid);
				if (user2!=null) {
					return "{\"msg\": \"请核对填写的id是否为已有学生id，后续操作失效\"}";
				}
				if (user3!=null) {
					return "{\"msg\": \"请核对填写的id是否为已有管理员id，后续操作失效\"}";
				}
				if (user!=null) { //用户存在则先删除
					userMapper.deleteTechInfo(uid);
				}
				userMapper.insertTechInfo(uid, uname, sex, upassword, upartment, utelephone);
				System.out.println(uid);
			} catch (Exception e) {
				e.printStackTrace();
				return "{\"msg\": \"请核对填写的信息是否准确\"}";
			}						
		}
		return "{\"msg\":\"true\"}";//消息显示
	}
	
	public List<User> getTechALL() {
		return userMapper.getTechAll();
	}
	
	public List<User> getStuALL() {
		return userMapper.getStuAll();
	}
	
	public User getStu(int uid){
		return userMapper.getStu(uid);
	}
	
	public User getTher(int uid){
		return userMapper.getTher(uid);
	}
	
	public User getAdm(int uid){
		return userMapper.getAdm(uid);
	}
	
	public Boolean upDateStu(String upassword,int uid,int utelephone) {
		return userMapper.upDateStu(upassword,uid, utelephone);
	}
	
	public Boolean upDateAdm(String upassword,int uid,int utelephone) {
		return userMapper.upDateAdm(upassword,uid, utelephone);
	}
	
	public Boolean upDateTher(String upassword,int uid,int utelephone) {
		return userMapper.upDateTher(upassword,uid, utelephone);
	}
	
	//获得所选班级的学生
	public List<User> getmyClassesperson(int cid,int classsort){
		return userMapper.getmyClassesperson(cid, classsort);
	}
}
