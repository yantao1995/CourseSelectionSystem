package com.pzhu.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pzhu.bean.Course;
@Mapper
public interface CourseMapper {
		@Results({
			@Result(property="cid",column="cid"),
			@Result(property="cname",column="cname"),
			@Result(property="score",column="score"),
			@Result(property="cdesc",column="cdesc"),
			@Result(property="cstatus",column="cstatus"),
			@Result(property="climitCount",column="climitCount"),
			@Result(property="cmaxCount",column="cmaxCount"),
			@Result(property="clessionCount",column="clessionCount"),
			@Result(property="csortname",column="csortname"),
			@Result(property="ctype",column="ctype"),
			@Result(property="result",column="result")
		})
		
		//新增课程时查看该课程是否已经存在
		@Select("SELECT * from course where cname=#{cname}")
		Course getCname(String cname);
		
		//课程表新增课程
		@Insert("INSERT INTO course(cname,score,cdesc,cstatus,climitCount,cmaxCount,clessionCount,csortname,ctype,result) " + 
				"VALUES(#{cname},#{score},#{cdesc},'未审核',#{climitCount},#{cmaxCount},#{clessionCount},#{csortname},#{ctype},'' ) ")
		boolean insertNewLesson(@Param("cname")String cname,@Param("score")BigDecimal score,@Param("cdesc")String cdesc,
				@Param("climitCount")int climitCount,@Param("cmaxCount")int cmaxCount,@Param("clessionCount")int clessionCount,
				@Param("csortname")String csortname,@Param("ctype")String ctype);
		
		//课程表和教师复合表新增课程
		@Insert("INSERT INTO cor_ther(ctlocal,cttime,cid,uid) VALUES('','',(SELECT course.cid FROM course WHERE cname=#{cname} and cdesc=#{cdesc}),#{uid})")
		boolean insertNewLessonAndTech(@Param("cname")String cname,@Param("cdesc")String cdesc,@Param("uid")int uid);
		
		@Select("select * from course")
		Course getOnlyCourse();
				
		//查询当前老师的全部课程		
		@Select("SELECT DISTINCT course.cid,cname,score,clessionCount,csortname,ctype,cdesc FROM course INNER JOIN cor_ther ON cor_ther.cid = course.cid AND cor_ther.uid = #{uid}")
		List<Course> getMyCourse(int uid);
		
		//查询当前老师的已通过课程
		@Select("SELECT DISTINCT course.cid,cname,score,clessionCount,csortname,ctype,cdesc,result FROM course \r\n" + 
				"INNER JOIN cor_ther \r\n" + 
				"ON cor_ther.cid = course.cid AND cor_ther.uid = #{uid} \r\n" + 
				"WHERE course.cstatus = '通过'")
		List<Course> getMyPassCourse(int uid);
		
		//查询当前老师审核中的课程
		@Select("SELECT DISTINCT course.cid,cname,score,clessionCount,csortname,ctype,cdesc FROM course \r\n" + 
				"INNER JOIN cor_ther \r\n" + 
				"ON cor_ther.cid = course.cid AND cor_ther.uid = #{uid} \r\n" + 
				"WHERE course.cstatus = '未审核'")
		List<Course> getMyProcessCourse(int uid);
		
		//查询当前老师未通过的课程
		@Select("SELECT DISTINCT course.cid,cname,score,clessionCount,csortname,ctype,cdesc,result FROM course \r\n" + 
				"INNER JOIN cor_ther \r\n" + 
				"ON cor_ther.cid = course.cid AND cor_ther.uid = #{uid} \r\n" + 
				"WHERE course.cstatus = '未通过'")
		List<Course> getMyNoPassCourse(int uid);
		
		//查看选修序列可选课程名，已通过的课程
		@Select("SELECT cname FROM course WHERE cstatus = '通过'")
		List<Course> getPassedCourseName();
		
		//adm 设置课程为通过
		@Update("UPDATE  course SET cstatus = '通过' WHERE cid = #{cid}")
		boolean setCourseStatusPassed(int cid);
		
		//填写审批意见
		@Update("UPDATE course SET result =#{result} WHERE cid = #{cid}")
		boolean setResult(@Param("result")String result,@Param("cid")int cid);
		
		//增加班级中的已选择课程二次验证
		@Select("SELECT * FROM course WHERE cname = #{cname}")
		Course getCourseByName(String cname);
		
		//课程审核不通过
		@Update("UPDATE course SET cstatus = '未通过' WHERE cid = #{cid}")
		boolean setCstatus(int cid);
}
