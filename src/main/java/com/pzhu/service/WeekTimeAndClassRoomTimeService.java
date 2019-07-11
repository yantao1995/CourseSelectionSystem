package com.pzhu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pzhu.bean.WeekTimeAndClassRoomTime;
import com.pzhu.mapper.ClassRoomTimeMapper;
import com.pzhu.mapper.CourseMapper;
import com.pzhu.mapper.CourseTeacherMapper;
import com.pzhu.mapper.WeekTimeAndClassRoomTimeMapper;

@Service
public class WeekTimeAndClassRoomTimeService {

		@Autowired
		private WeekTimeAndClassRoomTimeMapper weekTimeAndClassRoomTimeMapper;
		@Autowired
		private CourseMapper courseMapper;
		@Autowired
		private ClassRoomTimeMapper classRoomTimeMapper;
		@Autowired
		private CourseTeacherMapper courseTeacherMapper;
		
		
		//获得所有教室节次信息
		public List<WeekTimeAndClassRoomTime> getTimeAndClassRoomTimes(int start,int end,int uid){
			return weekTimeAndClassRoomTimeMapper.getTimeAndClassRoomTimes(start,end,uid);
		}
		
		//提交排课后的3表更新操作week,roomtime,cor_ther
		@Transactional
		public boolean setCourseStatusPassed(int cid,int uid,int classsort,String[] startWeek,String[] endWeek,String[] classRoomTime,String result) {
			int start,end,crtid;
			//courseTeacherMapper.deleteCor_TherCT(cid, uid,classsort); 暂不删除，空的 ctlocal 和 cttime 作为教师与课程表的绑定
			for (int i = 0; i < classRoomTime.length; i++) {			
				start = Integer.parseInt(startWeek[i]);
				end =Integer.parseInt(endWeek[i]);
				String[] str  = classRoomTime[i].split("_");
				crtid = weekTimeAndClassRoomTimeMapper.getCrtid(str[0],str[1]); //获得 crtid
				classRoomTimeMapper.setAlreadyStatus(crtid); // 设置 已排课
				weekTimeAndClassRoomTimeMapper.insertWeekTime(start, end, crtid); //插入 weektime周排课表
				int wid = weekTimeAndClassRoomTimeMapper.getWid(start, end, crtid);
				courseTeacherMapper.InsertCor_TherCT(str[0],str[1],classsort,cid, uid,wid); //插入排课信息				
				courseMapper.setResult(result, cid);	//填写审批意见
			}
			courseTeacherMapper.updateCTStatus(cid, uid,classsort);
			boolean flag1= courseMapper.setCourseStatusPassed(cid);
			
			return flag1;
		}
		
		//提交排课班级
		@Transactional
		public boolean setClassStatusPassed(int cid,int uid,int classsort,String[] startWeek,String[] endWeek,String[] classRoomTime) {
			int start,end,crtid;
			for (int i = 0; i < classRoomTime.length; i++) {			
				start = Integer.parseInt(startWeek[i]);
				end =Integer.parseInt(endWeek[i]);
				String[] str  = classRoomTime[i].split("_");
				crtid = weekTimeAndClassRoomTimeMapper.getCrtid(str[0],str[1]); //获得 crtid
				classRoomTimeMapper.setAlreadyStatus(crtid); // 设置 已排课
				weekTimeAndClassRoomTimeMapper.insertWeekTime(start, end, crtid); //插入 weektime周排课表
				int wid = weekTimeAndClassRoomTimeMapper.getWid(start, end, crtid);
				courseTeacherMapper.InsertCor_TherCT(str[0],str[1],classsort,cid, uid,wid); //插入排课信息								
			}			
			boolean flag1=courseTeacherMapper.updateCTStatus(cid, uid,classsort);			
			return flag1;
		}
}
