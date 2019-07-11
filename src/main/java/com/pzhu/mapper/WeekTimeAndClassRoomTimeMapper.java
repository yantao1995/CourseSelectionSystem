package com.pzhu.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import com.pzhu.bean.WeekTimeAndClassRoomTime;
@Mapper
public interface WeekTimeAndClassRoomTimeMapper {
		@Results({
			@Result(property="wid",column="wid"),
			@Result(property="startweek",column="startweek"),
			@Result(property="endweek",column="endweek"),
			@Result(property="crtid",column="crtid"),
			@Result(property="roomid",column="roomid"),
			@Result(property="ctlocal",column="ctlocal"),
			@Result(property="timeid",column="timeid"),
			@Result(property="crtstatus",column="crtstatus"),
			@Result(property="cttime",column="cttime")
		})
		
		//查询所有的可选教室和时间(在已选教室节次区间的两边，两段区间不相交)
//		"SELECT * FROM classroomtime WHERE crtid NOT in( " + 
//				"SELECT crtid FROM weektime WHERE\r\n" + 
//				" (weektime.startweek >=#{start} AND weektime.startweek <=#{end} ) " + 
//				"OR (weektime.startweek<=#{start} AND weektime.endweek>=#{start})) " + 
//				"AND crtstatus != '不可选'"
		
		//下面的是上面查询的优化版，不仅过滤了教室时间冲突，还过滤了教师上课时间冲突
		@Select("SELECT * FROM classroomtime WHERE crtid NOT in( \r\n" + 
				"				SELECT crtid FROM weektime WHERE\r\n" + 
				"				 (weektime.startweek >=#{start} AND weektime.startweek <=#{end} )\r\n" + 
				"				OR (weektime.startweek<=#{start} AND weektime.endweek>=#{start})\r\n" + 
				"				)\r\n" + 
				"				AND  crtstatus != '不可选'				\r\n" + 
				"				-- 过滤教师上课时间冲突\r\n" + 
				"				AND  cttime NOT in(\r\n" + 
				"						SELECT  classroomtime.cttime FROM weektime INNER JOIN classroomtime ON classroomtime.crtid = weektime.crtid \r\n" + 
				"											INNER JOIN cor_ther ON 	cor_ther.wid =weektime.wid WHERE\r\n" + 
				"										 ((weektime.startweek >=#{start} AND weektime.startweek <=#{end} )\r\n" + 
				"										OR (weektime.startweek<=#{start} AND weektime.endweek>=#{start}))\r\n" + 
				"										AND cor_ther.uid =#{uid}\r\n" + 
				"					)")
		List<WeekTimeAndClassRoomTime> getTimeAndClassRoomTimes(@Param("start")int start,@Param("end")int end,@Param("uid")int uid);
		
		//获得根据教室节次信息获得crtid  
		@Select("SELECT crtid FROM classroomtime WHERE ctlocal =#{ctlocal} AND cttime = #{cttime}")
		int getCrtid(@Param("ctlocal")String ctlocal,@Param("cttime")String cttime);		
		
		//插入排课信息
		@Insert("INSERT INTO weektime(startweek,endweek,crtid) VALUES(#{start},#{end},#{crtid}) ")
		boolean insertWeekTime(@Param("start")int start,@Param("end")int end,@Param("crtid")int crtid);
		
		//获取wid
		@Select("SELECT wid FROM weektime WHERE startweek =#{start} AND endweek = #{end} AND  crtid =#{crtid} ")
		int getWid(@Param("start")int start,@Param("end")int end,@Param("crtid")int crtid);
}
