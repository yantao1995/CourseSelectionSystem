package com.pzhu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pzhu.bean.User;
@Mapper
public interface UserMapper {
	@Results({
		@Result(property="uid",column="uid"),
		@Result(property="uname",column="uname"),
		@Result(property="upassword",column="upassword"),
		@Result(property="upartment",column="upartment"),
		@Result(property="utelephone",column="utelephone"),
		@Result(property="type",column="type"),
		@Result(property="sex",column="sex"),
		@Result(property="ugrade",column="ugrade")
	})
	
	//删除老师
	@Delete("DELETE FROM userther WHERE uid = #{uid}") 
	boolean deleteTechInfo(int uid);
	
	//添加老师INSERT INTO userstu VALUES('2015102','李三','男','1234','2015','数计学院软件工程2班','12345','学生')
	@Insert("INSERT INTO userther VALUES(#{uid},#{uname},#{sex},#{upassword},#{upartment},#{utelephone},'学生')")
	boolean insertTechInfo(@Param("uid")int uid,@Param("uname")String uname,@Param("sex")String sex,@Param("upassword")String upassword,@Param("upartment")String upartment,@Param("utelephone")int utelephone);
	
	//获取所有老师
	@Select("select * FROM userther")
	List<User> getTechAll();
	
	//删除学生
	@Delete("DELETE FROM userstu WHERE uid = #{uid}") 
	boolean deleteStuInfo(int uid);
	
	//添加学生INSERT INTO userstu VALUES('2015102','李三','男','1234','2015','数计学院软件工程2班','12345','学生')
	@Insert("INSERT INTO userstu VALUES(#{uid},#{uname},#{sex},#{upassword},#{ugrade},#{upartment},#{utelephone},'学生')")
	boolean insertStuInfo(@Param("uid")int uid,@Param("uname")String uname,@Param("sex")String sex,@Param("upassword")String upassword,@Param("ugrade")int ugrade,@Param("upartment")String upartment,@Param("utelephone")int utelephone);
	
	//获取所有学生
	@Select("select * FROM userstu")
	List<User> getStuAll();
	
	@Select("select * FROM userstu WHERE uid = #{uid}")
	User getStu(int uid);
	
	@Select("select * FROM userther WHERE uid = #{uid}")
	User getTher(int uid);
	
	@Select("select * FROM useradm WHERE uid = #{uid}")
	User getAdm(int uid);
	
	@Update("UPDATE userstu SET upassword = #{upassword} WHERE uid = #{uid} and utelephone = #{utelephone} ")
	boolean upDateStu(@Param("upassword")String upassword,@Param("uid")int uid,@Param("utelephone")int utelephone);
	
	@Update("UPDATE useradm SET upassword = #{upassword} WHERE uid = #{uid} and utelephone = #{utelephone} ")
	boolean upDateAdm(@Param("upassword")String upassword,@Param("uid")int uid,@Param("utelephone")int utelephone);
	
	@Update("UPDATE userther SET upassword = #{upassword} WHERE uid = #{uid} and utelephone = #{utelephone} ")
	boolean upDateTher(@Param("upassword")String upassword,@Param("uid")int uid,@Param("utelephone")int utelephone);
	
	//根据课程班级查看学生
	@Select("SELECT userstu.uid,uname,sex,ugrade,upartment,utelephone FROM userstu \r\n" + 
			"INNER JOIN ct_stu ON userstu.uid= ct_stu.uid \r\n" + 
			"WHERE ct_stu.cid = #{cid} AND ct_stu.classsort =#{classsort} ")
	List<User> getmyClassesperson(@Param("cid")int cid,@Param("classsort")int classsort);
}
