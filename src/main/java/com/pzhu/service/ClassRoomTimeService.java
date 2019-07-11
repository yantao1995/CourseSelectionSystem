package com.pzhu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.pzhu.bean.ClassRoomTime;
import com.pzhu.bean.CourseTeacher;
import com.pzhu.mapper.ClassRoomTimeMapper;
import com.pzhu.mapper.CourseTeacherMapper;

@Service
public class ClassRoomTimeService {

		@Autowired
		private ClassRoomTimeMapper classRoomTimeMapper;
		@Autowired
		private CourseTeacherMapper courseTeacherMapper;
		
		//获得所有教室节次信息
		public List<ClassRoomTime> getAllClassRoomTime(){
			return classRoomTimeMapper.getAllClassRoomTime();
		}
		
		//设置教室课时节次为可选
		public String setcrtAble(String listStr) {
			com.alibaba.fastjson.JSONArray list;
			list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
			String returnString = "";
			boolean flag = true;
			for (int i = 0; i < list.size(); i++) { 
				JSONObject signin = list.getJSONObject(i);
				try {
					int crtid =Integer.parseInt(signin.getString("crtid")); 		
					ClassRoomTime crt = classRoomTimeMapper.getInfoByCrtid(crtid);
					if (crt.getCrtstatus().equals("已排课")) {
						flag =false;
						returnString += ("编号:"+crt.getCrtid()+"的状态为已排课,该课程无法更改___");
					}else if (crt.getCrtstatus().equals("可选")) {
						flag=false;
						returnString += ("编号:"+crt.getCrtid()+"该课程已经为可选状态，无需更改___");
					}else {
						if (classRoomTimeMapper.setStatus(crtid)) {
							returnString +=("编号:"+crt.getCrtid()+"该课程更改成功 ___");
						}else {
							returnString +=("编号:"+crt.getCrtid()+"该课程更改失败___");
						}
					}
									
				} catch (Exception e) {
					e.printStackTrace();
					return "{\"msg\": \"设置状态失败\"}";
				}						
			}
			if (flag) {
				return "{\"msg\":\"true\"}";//消息显示
			}else {
				return "{\"msg\":\""+returnString+"\"}";//消息显示
			}
			
		}
		
		//设置教室课时节次为不可选
		public String setcrtUnAble(String listStr) {
			com.alibaba.fastjson.JSONArray list;
			list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
			String returnString = "";
			boolean flag = true;
			for (int i = 0; i < list.size(); i++) { 
				JSONObject signin = list.getJSONObject(i);
				try {
					int crtid =Integer.parseInt(signin.getString("crtid")); 				
					ClassRoomTime crt = classRoomTimeMapper.getInfoByCrtid(crtid);
					if (crt.getCrtstatus().equals("已排课")) {
						flag =false;
						returnString += ("编号:"+crt.getCrtid()+"的状态为已排课,该课程无法更改___");
					}else if (crt.getCrtstatus().equals("不可选")) {
						flag=false;
						returnString += ("编号:"+crt.getCrtid()+"该课程已经为不可选状态，无需更改___");
					}else {
						if (classRoomTimeMapper.setUnStatus(crtid)) {
							returnString +=("编号:"+crt.getCrtid()+"该课程更改成功 ___");
						}else {
							returnString +=("编号:"+crt.getCrtid()+"该课程更改失败___");
						}
					}
									
				} catch (Exception e) {
					e.printStackTrace();
					return "{\"msg\": \"设置状态失败\"}";
				}						
			}
			if (flag) {
				return "{\"msg\":\"true\"}";//消息显示
			}else {
				return "{\"msg\":\""+returnString+"\"}";//消息显示
			}
			
		}
		//获得所有教室
		public List<ClassRoomTime> getAllClassRoom(){
			return classRoomTimeMapper.getAllClassRoom();
		}
		
		//更新增加教室
		public String classRoomUpdate(String listStr) {
			com.alibaba.fastjson.JSONArray list;
			list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
			for (int i = 0; i < list.size(); i++) { 
				JSONObject signin = list.getJSONObject(i);
				try {
					int roomid =Integer.parseInt(signin.getString("roomid")); 
					String ctlocal = (String) signin.get("ctlocal");					
					ClassRoomTime crt = classRoomTimeMapper.getRoomByRoomId(roomid);
					List<CourseTeacher> ctlocalUsed = courseTeacherMapper.getClassHasUesd(ctlocal);
					if (!ctlocalUsed.isEmpty()) {
						return ("教室已被使用无法更改信息:"+ctlocal);
					}else {
						if (crt!=null) { //教室存在则先删除
							classRoomTimeMapper.deleteClassRoom(roomid);
							classRoomTimeMapper.deleteClassRoomTime(roomid);
						}
						classRoomTimeMapper.insertClassRoom(roomid, ctlocal);
						classRoomTimeMapper.insertClassRoomTime(roomid);
					}					
				} catch (Exception e) {
					e.printStackTrace();
					return "{\"msg\": \"更新失败：请核对填写的信息是否准确\"}";
				}						
			}
			return "{\"msg\":\"true\"}";//消息显示
		}
		
		//删除教室
		public String classRoomDelete(String listStr) {
			com.alibaba.fastjson.JSONArray list;
			list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
			for (int i = 0; i < list.size(); i++) {
				JSONObject signin = list.getJSONObject(i);
				try {
					int roomid =Integer.parseInt(signin.getString("roomid")); 
					String ctlocal = (String) signin.get("ctlocal");					
					ClassRoomTime crt = classRoomTimeMapper.getRoomByRoomId(roomid);
					List<CourseTeacher> ctlocalUsed = courseTeacherMapper.getClassHasUesd(ctlocal);
					if (!ctlocalUsed.isEmpty()) {
						return ("教室已被使用无法删除信息:"+ctlocal);
					}else {
						if (crt!=null) { //存在才删除，不存在报错处理
							classRoomTimeMapper.deleteClassRoom(roomid);
							classRoomTimeMapper.deleteClassRoomTime(roomid);
						}else {
							return "{\"msg\": \"删除失败：该教室不存在\"}";
						}
					}					
				} catch (Exception e) {
					e.printStackTrace();
					return "{\"msg\": \"删除失败：请核对填写的信息是否准确\"}";
				}						
			}
			return "{\"msg\":\"true\"}";//消息显示
		}
}
