package com.pzhu.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.pzhu.bean.CourseTeacher;
import com.pzhu.bean.WeekTimeAndClassRoomTime;

@Mapper
public interface CourseTeacherMapper {
	@Results({
		@Result(property="cid",column="cid"),
		@Result(property="cname",column="cname"),
		@Result(property="ctid",column="ctid"),
		@Result(property="ctlocal",column="ctlocal"),
		@Result(property="cttime",column="cttime"),
		@Result(property="score",column="score"),
		@Result(property="cdesc",column="cdesc"),
		@Result(property="cstatus",column="cstatus"),
		@Result(property="climitCount",column="climitCount"),
		@Result(property="currentCount",column="currentCount"),
		@Result(property="cmaxCount",column="cmaxCount"),
		@Result(property="clessionCount",column="clessionCount"),
		@Result(property="csortid",column="csortid"),
		@Result(property="ctype",column="ctype"),
		@Result(property="uid",column="uid"),
		@Result(property="uname",column="uname"),
		@Result(property="upassword",column="upassword"),
		@Result(property="upartment",column="upartment"),
		@Result(property="utelephone",column="utelephone"),
		@Result(property="type",column="type"),
		@Result(property="sex",column="sex"),
		@Result(property="ugrade",column="ugrade"),
		@Result(property="classsort",column="classsort"),
		@Result(property="ctstatus",column="ctstatus"),
		@Result(property="wid",column="wid"),
		@Result(property="startWeek",column="startWeek"),
		@Result(property="endWeek",column="endWeek"),
		@Result(property="scores",column="scores")
	})
	//获取所有未审核的课程
	@Select("SELECT * FROM cor_ther JOIN course ON cor_ther.cid=course.cid JOIN userther ON cor_ther.uid = userther.uid WHERE cstatus = '未审核'")
	List<CourseTeacher> getHandleCourse();
	
	//获取新增未排课的班级
	@Select("SELECT * FROM cor_ther JOIN course ON cor_ther.cid=course.cid JOIN userther ON cor_ther.uid = userther.uid WHERE cstatus = '通过' AND ctstatus = '未排课'")
	List<CourseTeacher> getNewClasses();
	
	//查看教室是否已经排课
	@Select("SELECT * FROM cor_ther WHERE ctlocal = #{ctlocal}")
	List<CourseTeacher> getClassHasUesd(String ctlocal);
	
	//插入之前先删除教室节次信息为空的行DELETE FROM cor_ther WHERE ctlocal='' AND cttime='' and cid = #{cid} and uid =#{uid} and classsort=#{classsort} 
	//boolean deleteCor_TherCT(@Param("cid")int cid,@Param("uid")int uid,@Param("classsort")int classsort);
	
	//插入cor_ther排课教室节次信息
	@Insert("INSERT INTO cor_ther(ctlocal,cttime,classsort,cid,uid,wid) VALUES(#{ctlocal},#{cttime},#{classsort},#{cid},#{uid},#{wid})")
	boolean InsertCor_TherCT(@Param("ctlocal")String ctlocal,@Param("cttime")String cttime,@Param("classsort")int classsort,@Param("cid")int cid,@Param("uid")int uid,@Param("wid")int wid);

	
	//设置 未排课 ->已排课
	@Update("UPDATE cor_ther SET ctstatus = '已排课' WHERE cid = #{cid} AND uid = #{uid} AND classsort = #{classsort} ")
	boolean updateCTStatus(@Param("cid")int cid,@Param("uid")int uid,@Param("classsort")int classsort);
	
	//设置班级排课不通过
	@Update("UPDATE cor_ther SET ctstatus = '未通过' WHERE ctid = #{ctid} ")
	boolean setNoPassCTStatus(@Param("ctid")int ctid);
	
	//新增班级之前，查询出当前最大的开课班级号
	@Select("SELECT MAX(classsort) FROM cor_ther WHERE cid = #{cid}")
	int getMaxClassSort(int cid);
	
	//插入cor_ther的新增开课班级
	@Insert("INSERT INTO cor_ther(ctlocal,cttime,cid,uid,classsort) VALUES('','',#{cid},#{uid},#{classsort})")
	boolean insertNewClass(@Param("cid")int cid,@Param("uid")int uid,@Param("classsort")int classsort);
	
	//查询老师的开班信息
	@Select("SELECT course.cid,course.cname,cor_ther.classsort,\r\n" + 
			"(SELECT COUNT(*) FROM ct_stu where ct_stu.cid=cor_ther.cid AND ct_stu.classsort = cor_ther.classsort) as currentCount,\r\n" + 
			"CAST(GROUP_CONCAT('[',startweek,'-',endweek,']',ctlocal,'_',cttime) AS char)AS ctlocal\r\n" + 
			"FROM cor_ther INNER JOIN course ON cor_ther.cid = course.cid INNER JOIN weektime ON weektime.wid=cor_ther.wid \r\n" + 
			"where cor_ther.uid= #{uid}\r\n" + 
			"GROUP BY cname,classsort")
	List<CourseTeacher> getMyClasses(int uid);
	
	//查询老师未通过的开班信息
	@Select("SELECT * FROM cor_ther INNER JOIN course ON cor_ther.cid = course.cid  where uid= #{uid} and ctstatus ='未通过'")
	List<CourseTeacher> getNoPassClasses(@Param("uid")int uid);
	
	//查询老师已关闭的班级
	@Select("SELECT * FROM cor_ther INNER JOIN course ON cor_ther.cid = course.cid  where uid= #{uid} and ctstatus ='已关闭'")
	List<CourseTeacher> myclosedClasses(@Param("uid")int uid);
	
	//查询所有课程
	@Select("SELECT course.cid,cname,GROUP_CONCAT(DISTINCT uname) as uname,sex,score,GROUP_CONCAT(ctlocal,'_',cttime)as ctlocal,clessionCount,ctype,cdesc \r\n" + 
			"FROM cor_ther INNER JOIN course ON cor_ther.cid = course.cid INNER JOIN userther ON userther.uid = cor_ther.uid \r\n" + 
			"WHERE course.cstatus='通过' AND cor_ther.ctstatus='已排课' AND cor_ther.ctlocal!=''\r\n" + 
			"GROUP BY cid")
	List<CourseTeacher> getAllCourse();
	
	//查询未选修过的课程
	@Select("SELECT course.cid,cname,uname,sex,score,GROUP_CONCAT(ctlocal,'_',cttime)as ctlocal,clessionCount,ctype,cdesc \r\n" + 
			"FROM cor_ther INNER JOIN course ON cor_ther.cid = course.cid INNER JOIN userther ON userther.uid = cor_ther.uid \r\n" + 
			"WHERE course.cstatus='通过' AND cor_ther.ctstatus='已排课' AND cor_ther.ctlocal!=''\r\n" + 
			"AND course.cid NOT IN (SELECT cid FROM mark WHERE uid = #{uid})\r\n" + 
			"GROUP BY cid")
	List<CourseTeacher> getNeverLearnCourse(int uid);
		
	//查询该课程的所有班级和上课周次地点
	@Select("SELECT ctid,cid,classsort,userther.uname,CAST(GROUP_CONCAT('[',startWeek,'-',endWeek,']',ctlocal,'_',cttime) AS char) as ctlocal,\r\n" + 
			"(SELECT COUNT(*) FROM ct_stu WHERE ct_stu.cid = #{cid} AND ct_stu.classsort = cor_ther.classsort) as currentCount,"+
			"(SELECT cMaxCount FROM course WHERE course.cid = #{cid}) as cmaxCount "+
			"FROM cor_ther INNER JOIN weektime ON weektime.wid = cor_ther.wid\r\n" + 
			"INNER JOIN userther ON userther.uid=cor_ther.uid\r\n" +
			"WHERE cid = #{cid} AND ctstatus='已排课' AND cor_ther.ctlocal !=''\r\n" + 
			"GROUP BY classsort")
	List<CourseTeacher>  getAllClassInfoByCourseId(int cid);
	
	//查询过往的学生选修的cid的成绩
	@Select("SELECT scores FROM mark WHERE uid = #{uid} AND cid = #{cid}")
	BigDecimal getScoresByCidUid(@Param("cid")int cid,@Param("uid")int uid);
	
	//查看所选课程是否已经选过了.
	@Select("SELECT * FROM ct_stu WHERE uid = #{uid} AND cid = #{cid}")
	CourseTeacher checkCourseHasChoosed(@Param("uid") int uid,@Param("cid")int cid);
	
	//上面的，该课程选过了就切换到目前选的班级
	@Update("UPDATE ct_stu SET classsort = #{classsort} WHERE uid = #{uid} AND cid = #{cid}")
	boolean updateCT_StuClasssort(@Param("uid") int uid,@Param("cid")int cid,@Param("classsort")int classsort);
	
	//获得本学期已选课程的总学分
	@Select("SELECT SUM(score) FROM course INNER JOIN ct_stu ON course.cid = ct_stu.cid WHERE ct_stu.uid = #{uid}")
	BigDecimal getALLScore(int uid);
	
	//获得该课程的最大开班人数
	@Select("SELECT cmaxCount FROM course WHERE cid = #{cid}")
	int getMaxCountByCourse(int cid);
	
	//获得当前课程的已选人数
	@Select("SELECT COUNT(*) FROM ct_stu WHERE cid = #{cid} AND classsort = #{classsort}")
	int getCurrentCountByCourseAndClasssort(@Param("cid")int cid,@Param("classsort")int classsort);
	
	//获得该cid课程的前置课程的cid和cname
	@Select("SELECT cid,cname FROM course WHERE  cname = (SELECT csortname FROM course WHERE cid = #{cid})")
	CourseTeacher getCSortCid(int cid);
	
	//解决选课冲突，查询所有该uid学生已选课程班级的 周节次信息
	@Select("SELECT cttime,startweek,endweek FROM ct_stu \r\n" + 
			"INNER JOIN cor_ther ON (ct_stu.cid = cor_ther.cid AND ct_stu.classsort = cor_ther.classsort) \r\n" + 
			"INNER JOIN weektime ON cor_ther.wid = weektime.wid\r\n" + 
			"WHERE ctlocal!= ''  AND ct_stu.uid= #{uid}")
	List<CourseTeacher> getCttimeStartEndWeek(int uid);
	
	//查询当前班级选项的开课周节次信息
	@Select("SELECT cor_ther.cttime,weektime.startweek,weektime.endweek FROM cor_ther \r\n" + 
			"INNER JOIN weektime ON cor_ther.wid = weektime.wid\r\n" + 
			"WHERE cor_ther.cid = #{cid} AND cor_ther.classsort = #{classsort} AND cor_ther.ctlocal != ''")
	List<CourseTeacher> getCurrentChooseCttimeStartEndWeek(@Param("cid")int cid,@Param("classsort")int classsort);
	
	//选课提交
	@Insert("INSERT into  ct_stu(uid,cid,classsort) VALUES(#{uid},#{cid},#{classsort})")
	boolean insertCT_STU(@Param("uid")int uid,@Param("cid")int cid,@Param("classsort")int classsort);	
	
	//退选课程中查询所有已选课程
	@Select("SELECT course.cid,course.cname,cor_ther.classsort,course.score,CAST(GROUP_CONCAT('[',startWeek,'-',endWeek,']',ctlocal,'_',cttime) AS char) as ctlocal,clessionCount,ctype,cdesc  \r\n" + 
			"FROM ct_stu \r\n" + 
			"INNER JOIN cor_ther ON (ct_stu.cid = cor_ther.cid AND ct_stu.classsort=cor_ther.classsort)\r\n" + 
			"INNER JOIN course ON cor_ther.cid = course.cid \r\n" + 
			"INNER JOIN weektime ON weektime.wid = cor_ther.wid\r\n" + 
			"WHERE ctlocal != '' AND ct_stu.uid = #{uid}\r\n"+
			"GROUP BY cor_ther.cid")
	List<CourseTeacher> getMyChooseCourse(int uid);
	
	//学生删除时，删除ct_stu里的所有记录
	@Delete("DELETE FROM ct_stu WHERE uid = #{uid}")
	boolean deleteStuAndCt_stuInfo(@Param("uid")int uid);
	
	//删除老师的时候查看老师有没有排课
	@Select("SELECT COUNT(*) FROM cor_ther WHERE uid = #{uid}")
	int getTeacherCourseAndClass(int uid);
	
	//退选课程提交
	@Delete("DELETE FROM ct_stu WHERE uid = #{uid} AND cid= #{cid} and classsort = #{classsort}")
	boolean deleteChooseCourse(@Param("uid")int uid,@Param("cid")int cid,@Param("classsort")int classsort);
	
	//查看课表 学生
	@Select("SELECT cttime,CAST(GROUP_CONCAT('{(',course.cname,')','[',startWeek,'-',endWeek,']',ctlocal,'_',cttime,'}') AS char) as ctlocal  \r\n" + 
			",CAST(GROUP_CONCAT('[(',course.cname,')','[',startWeek,'-',endWeek,'](',ctlocal,')]') AS char) as cdesc " + //为了去除周节次添加了，课表更美观
			"FROM ct_stu \r\n" + 
			"INNER JOIN cor_ther ON (ct_stu.cid = cor_ther.cid AND ct_stu.classsort=cor_ther.classsort)\r\n" + 
			"INNER JOIN course ON cor_ther.cid = course.cid \r\n" + 
			"INNER JOIN weektime ON weektime.wid = cor_ther.wid\r\n" + 
			"WHERE ctlocal != '' AND ct_stu.uid = #{uid}\r\n" + 
			"GROUP BY cor_ther.ctid")
	List<CourseTeacher> getClassTable(int uid);
	
	//查看课表 老师
	@Select("SELECT cttime,CAST(GROUP_CONCAT('((',course.cname,')','[',startWeek,'-',endWeek,']',ctlocal,'_',cttime,')') AS char) as ctlocal \r\n" + 
			",CAST(GROUP_CONCAT('[(',course.cname,')','[',startWeek,'-',endWeek,'](',ctlocal,')]') AS char) as cdesc \r\n" + 
			"FROM  cor_ther\r\n" + 
			"INNER JOIN course ON cor_ther.cid = course.cid \r\n" + 
			"INNER JOIN weektime ON weektime.wid = cor_ther.wid\r\n" + 
			"WHERE ctlocal != '' AND cor_ther.uid = #{uid}\r\n" + 
			"GROUP BY cor_ther.ctid")
	List<CourseTeacher> getTeacherClassTable(int uid);
	
	//查看所有班级
	@Select("SELECT course.cid,cor_ther.ctid,course.climitCount,course.cname,cor_ther.classsort,\r\n" + 
			"(SELECT COUNT(*) FROM ct_stu where ct_stu.cid=cor_ther.cid AND ct_stu.classsort = cor_ther.classsort) as currentCount,\r\n" + 
			"CAST(GROUP_CONCAT('[',startweek,'-',endweek,']',ctlocal,'_',cttime) AS char)AS ctlocal\r\n" + 
			"FROM cor_ther INNER JOIN course ON cor_ther.cid = course.cid INNER JOIN weektime ON weektime.wid=cor_ther.wid \r\n" + 
			"where(SELECT COUNT(*) FROM ct_stu where ct_stu.cid=cor_ther.cid AND ct_stu.classsort = cor_ther.classsort)>=0\r\n" + 
			"GROUP BY cname,classsort")
	List<CourseTeacher> getAllTeacherClasses();
	
	//查看人数不足班级
	@Select("SELECT course.cid,cor_ther.ctid,course.climitCount,course.cname,cor_ther.classsort,\r\n" + 
			"(SELECT COUNT(*) FROM ct_stu where ct_stu.cid=cor_ther.cid AND ct_stu.classsort = cor_ther.classsort) as currentCount,\r\n" + 
			"CAST(GROUP_CONCAT('[',startweek,'-',endweek,']',ctlocal,'_',cttime) AS char)AS ctlocal\r\n" + 
			"FROM cor_ther INNER JOIN course ON cor_ther.cid = course.cid INNER JOIN weektime ON weektime.wid=cor_ther.wid \r\n" + 
			"where(SELECT COUNT(*) FROM ct_stu where ct_stu.cid=cor_ther.cid AND ct_stu.classsort = cor_ther.classsort)<=course.climitCount\r\n" + 
			"GROUP BY cname,classsort")
	List<CourseTeacher> getlimitTeacherClasses();
	
	//班级关闭 第1步 清除 ct_stu 表内学生选课记录
	@Delete("DELETE FROM ct_stu WHERE cid=#{cid} AND classsort =#{classsort}")
	boolean deleteCt_StuInfo(@Param("cid")int cid,@Param("classsort")int classsort);
	
	//班级关闭 第2步 设置cor_ther所有cid,classsort的班级状态为  '已关闭'
	@Update("UPDATE cor_ther SET ctstatus = '已关闭' WHERE cid = #{cid} AND classsort =#{classsort} ")
	boolean updateCor_therCstatusClose(@Param("cid")int cid,@Param("classsort")int classsort);
	
	//班级关闭 第3步 获取待关闭的wid编号组
	@Select("SELECT wid FROM cor_ther WHERE cid = #{cid} AND classsort =#{classsort} AND ctlocal !=''")
	List<WeekTimeAndClassRoomTime> getAllWidByCidClasssort(@Param("cid")int cid,@Param("classsort")int classsort);	
	
	// ----- 对 weektime内的crtid进行统计。如果个数为1就直接删除并更新  classroomtime内状态为可选，否则只删除不更新
	@Select("SELECT COUNT(*) FROM  weektime WHERE crtid = #{crtid} ")
	int getCountCrtid(int crtid);
	
	//班级关闭 第4步 清除 weektime 排课周节次记录
	@Delete("DELETE FROM weektime WHERE wid =#{wid} ")
	boolean deleteWeekTimeByWid(int wid);	
	
	//班级关闭 第5步 清除 cor_ther 内的排课记录
	@Delete("DELETE FROM cor_ther WHERE cid=#{cid} AND classsort =#{classsort} AND ctlocal !='' ")
	boolean deleteCor_therCidClassInfo(@Param("cid")int cid,@Param("classsort")int classsort);

}


