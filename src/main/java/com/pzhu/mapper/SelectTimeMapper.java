package com.pzhu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pzhu.bean.SelectTime;

@Mapper
public interface SelectTimeMapper {
	@Results({
		@Result(property="sid",column="sid"),
		@Result(property="starttime",column="starttime"),
		@Result(property="endtime",column="endtime")
	})
	
	@Select("select * from select_time")
	SelectTime getSelectTime();
	
	@Update("UPDATE `select_time` SET `starttime`=#{starttime}, `endtime`=#{endtime} WHERE (`sid`='1')")
	boolean setSelectTime(@Param("starttime")java.sql.Date starttime,@Param("endtime")java.sql.Date endtime);
}
