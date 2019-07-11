package com.pzhu.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;
import com.pzhu.bean.ClassRoomTime;
import com.pzhu.bean.ClassTable;
import com.pzhu.bean.CourseTeacher;
import com.pzhu.bean.MarkBean;
import com.pzhu.bean.WeekTimeAndClassRoomTime;
import com.pzhu.mapper.ClassRoomTimeMapper;
import com.pzhu.mapper.CourseTeacherMapper;
import com.pzhu.mapper.MarkMapper;

@Service
public class CourseTeacherService {

	@Autowired
	private CourseTeacherMapper courseTeacherMapper;
	@Autowired
	private MarkMapper markMapper;
	@Autowired
	private ClassRoomTimeMapper classRoomTimeMapper;
	
	//获得未审核的课程
	public List<CourseTeacher> getHandleCourse(){
		return courseTeacherMapper.getHandleCourse();
	}
	
	//获得新增未排课的班级
	public List<CourseTeacher> getNewClasses() {
		return courseTeacherMapper.getNewClasses();
	}
	
	//设置新增班级状态为不通过
	public boolean setNoPassCTStatus(int ctid) {
		return courseTeacherMapper.setNoPassCTStatus(ctid);
	}
	
	//查询所有课程
	public List<CourseTeacher> getCourseTeacher() {
		return courseTeacherMapper.getAllCourse();
	}
	
	public int getMaxClassSort(int cid) {
		return courseTeacherMapper.getMaxClassSort(cid);
	}
	
	public boolean insertNewClass(int cid,int uid,int classsort) {
		return courseTeacherMapper.insertNewClass(cid, uid, classsort);
	}
	
	public List<CourseTeacher> getMyClasses(int uid) {
		return courseTeacherMapper.getMyClasses(uid);
	}
	
	public List<CourseTeacher> getNoPassClasses(int uid) {
		return courseTeacherMapper.getNoPassClasses(uid);
	}
	
	public List<CourseTeacher> myclosedClasses(int uid) {
		return courseTeacherMapper.myclosedClasses(uid);
	}
	
	//获取某cid的所有班的教室排课信息
	public List<CourseTeacher> getAllClassInfoByCourseId(int cid) {
		return courseTeacherMapper.getAllClassInfoByCourseId(cid);
	}
	
	//获取未选修过的课程
	public List<CourseTeacher> getNeverLearnCourse(int uid){
		return courseTeacherMapper.getNeverLearnCourse(uid);
	}
	
	//选课提交处理
	@Transactional
	public String stuChooseCourse(int uid,int cid,int classsort,String scor) {
		String returnString="";CourseTeacher ct_stu;
		//检查加上本次提交，该班级的最大人数有没有超过最大开班人数
		int maxCount = courseTeacherMapper.getMaxCountByCourse(cid);
		int currentCount = courseTeacherMapper.getCurrentCountByCourseAndClasssort(cid, classsort);
		if (currentCount>=maxCount) {
			return "该班人数已满!";
		}
		
		//检查 前置 课程是否已经 过 60分
		CourseTeacher courseTeacher = courseTeacherMapper.getCSortCid(cid); 
		if (courseTeacher!=null) {  //有前置课程
			int cidBefore = courseTeacher.getCid();
			MarkBean mkb = markMapper.getScoresByUidCid(uid, cidBefore);
			if (mkb==null) {
				return "该课程设置了先修序列,请先选修:"+courseTeacher.getCname();
			}else {
				double scoreBefore =Double.parseDouble(mkb.getScores().toString());
				if (scoreBefore<=60.0) {
					return "选课失败：该课程的前置课程_"+courseTeacher.getCname()+",之前选修的分数为: "+scoreBefore;
				}
				returnString +=("前置课程_"+courseTeacher.getCname()+",之前选修的分数为:  "+scoreBefore);
			}
			
		}
		
		//检查总学分加上本次课程有没有超过32分
		if ((courseTeacherMapper.getALLScore(uid))!=null) {
			double scores = Double.parseDouble(courseTeacherMapper.getALLScore(uid).toString());
			double score = Double.parseDouble(scor);
			if ((scores+score)>32.0) {
				return "本学期所选课程学分为："+scores+",\n加上本次课程学分为："+(scores+score)+",\n总分数大于32分！\n该课程无法选择！";
			}else {
				returnString += ("本学期所选课程学分为："+scores+",\n加上本次课程后,本学期总学分为："+(scores+score));
			}
		}
			
		//检查以前的学期有没有选修过
		if (courseTeacherMapper.getScoresByCidUid(cid, uid) != null) {
			returnString +=("\n该课程的过往成绩为："+courseTeacherMapper.getScoresByCidUid(cid, uid));
		}
		
		//检查该课程是否已经选择了，如果选择了不同班级就更新班级信息
		ct_stu = courseTeacherMapper.checkCourseHasChoosed(uid, cid);	
		if (ct_stu!=null) { // 之前选过。
			int classsort2 = ct_stu.getClasssort();
			if (classsort == classsort2) {
				return "你目前已经在该课程的该班级，无需重新提交";
			}else {
				if (checkCttimeStartEndWeek(cid, uid, classsort)) { //检查该学生选择是否存在周节次 冲突 (cttime,startWeek,endWeek)
					return "\n选课失败！\n选择该班级会导致上课时间冲突,建议查看课表再选择";
				}else {
					if (courseTeacherMapper.updateCT_StuClasssort(uid, cid, classsort)) {
						returnString += ("\n已经成功切换班级到："+classsort+"班");
					}else {
						return "该课程已选择其他班级，切换班级失败";
					}
				}							
			}
		}else {		//之前没选过
			if (checkCttimeStartEndWeek(cid, uid, classsort)) { //检查该学生选择是否存在周节次 冲突 (cttime,startWeek,endWeek)
				return "\n选课失败！\n选择该班级会导致上课时间冲突,建议查看课表再选择";
			}else { //插入选择数据
				courseTeacherMapper.insertCT_STU(uid, cid, classsort);
				returnString +="\n选课成功!";
			}			
		}			
		return returnString;
	}
	
	//检查学生提交的班级是否存在时间冲突 ----------------
	public boolean checkCttimeStartEndWeek(int cid,int uid,int classsort) {
		boolean flag = false;
		List<CourseTeacher> ct1 = courseTeacherMapper.getCttimeStartEndWeek(uid);
		List<CourseTeacher> ct2 = courseTeacherMapper.getCurrentChooseCttimeStartEndWeek(cid, classsort);
		int s1,s2,e1,e2;String ctime1,ctime2;
		if (ct1!=null) {
			for (int i = 0; i < ct1.size(); i++) {
				for (int j = 0; j < ct2.size(); j++) {
					s1 = ct1.get(i).getStartWeek();
					s2 = ct2.get(j).getStartWeek();
					e1 = ct1.get(i).getEndWeek();
					e2 = ct2 .get(j).getEndWeek();
					if (((s2>=s1 && s2<=e1)||(s2<=s1 && e2>=s1))) { //存在相交区间
						ctime1 = ct1.get(i).getCttime();
						ctime2 = ct2.get(j).getCttime();
						if (ctime1.equals(ctime2)) {  //上课时间相同
							flag = true;
						}					
					}
				}
			}
		}		
		return flag;
	}
	
	//退选查看 已选列表
	public  List<CourseTeacher> getMyChooseCourse(int uid){
		return courseTeacherMapper.getMyChooseCourse(uid);
	}
	
	//退选课程
	@Transactional
	public boolean deleteChooseCourse(int uid,String listStr) {
		com.alibaba.fastjson.JSONArray list;
		list = com.alibaba.fastjson.JSONArray.parseArray(listStr);
		for (int i = 0; i < list.size(); i++) {
			JSONObject signin = list.getJSONObject(i);
			int cid = (int) signin.get("cid"); 
			int classsort = (int) signin.get("classsort");
			courseTeacherMapper.deleteChooseCourse(uid, cid, classsort);
		}
		return true;
	}
	
	//查看课表 学生
	public List<ClassTable> getClassTables(int uid) {
		List<CourseTeacher> courseTeachers = courseTeacherMapper.getClassTable(uid); 
		//构造 ClassTable json数组 obj		
		List<ClassTable> list = new ArrayList<ClassTable>();
		ClassTable t1_2 = new ClassTable("上午 1-2");
		ClassTable t3_4 = new ClassTable("上午 3-4");
		ClassTable t5_6 = new ClassTable("下午 5-6");
		ClassTable t7_8 = new ClassTable("下午 7-8");
		ClassTable t9_10 = new ClassTable("晚上 9-10");
		for (int i = 0; i < courseTeachers.size(); i++) {
			if (courseTeachers.get(i).getCttime().contains("1-2")) {
				t1_2 = geTableString(t1_2,courseTeachers.get(i));
			}
			if (courseTeachers.get(i).getCttime().contains("3-4")) {
				t3_4 = geTableString(t3_4,courseTeachers.get(i));			
			}
			if (courseTeachers.get(i).getCttime().contains("5-6")) {
				t5_6 = geTableString(t5_6,courseTeachers.get(i));
			}
			if (courseTeachers.get(i).getCttime().contains("7-8")) {
				t7_8 = geTableString(t7_8,courseTeachers.get(i));
			}
			if (courseTeachers.get(i).getCttime().contains("9-10")) {
				t9_10 = geTableString(t9_10,courseTeachers.get(i));
			}
		}
		list.add(t1_2);
		list.add(t3_4);
		list.add(t5_6);
		list.add(t7_8);
		list.add(t9_10);
		return list;
	}
	
	
	//查看课表 老师
	public List<ClassTable> getTechClassTables(int uid) {
		List<CourseTeacher> courseTeachers = courseTeacherMapper.getTeacherClassTable(uid); 
		//构造 ClassTable json数组 obj		
		List<ClassTable> list = new ArrayList<ClassTable>();
		ClassTable t1_2 = new ClassTable("上午 1-2");
		ClassTable t3_4 = new ClassTable("上午 3-4");
		ClassTable t5_6 = new ClassTable("下午 5-6");
		ClassTable t7_8 = new ClassTable("下午 7-8");
		ClassTable t9_10 = new ClassTable("晚上 9-10");
		for (int i = 0; i < courseTeachers.size(); i++) {
			if (courseTeachers.get(i).getCttime().contains("1-2")) {
				t1_2 = geTableString(t1_2,courseTeachers.get(i));
			}
			if (courseTeachers.get(i).getCttime().contains("3-4")) {
				t3_4 = geTableString(t3_4,courseTeachers.get(i));			
			}
			if (courseTeachers.get(i).getCttime().contains("5-6")) {
				t5_6 = geTableString(t5_6,courseTeachers.get(i));
			}
			if (courseTeachers.get(i).getCttime().contains("7-8")) {
				t7_8 = geTableString(t7_8,courseTeachers.get(i));
			}
			if (courseTeachers.get(i).getCttime().contains("9-10")) {
				t9_10 = geTableString(t9_10,courseTeachers.get(i));
			}
		}
		list.add(t1_2);
		list.add(t3_4);
		list.add(t5_6);
		list.add(t7_8);
		list.add(t9_10);
		return list;
	}
	
	public ClassTable geTableString(ClassTable classTable,CourseTeacher courseTeacher) {
		if (courseTeacher.getCttime().contains("周一")) {
			//classTable.setZhouyi(courseTeacher.getCtlocal());
			classTable.setZhouyi(courseTeacher.getCdesc()); //去除周节次之后的，课表更美观
		}
		if (courseTeacher.getCttime().contains("周二")) {
			//classTable.setZhouer(courseTeacher.getCtlocal());
			classTable.setZhouer(courseTeacher.getCdesc());
		}
		if (courseTeacher.getCttime().contains("周三")) {
			//classTable.setZhousan(courseTeacher.getCtlocal());
			classTable.setZhousan(courseTeacher.getCdesc());
		}
		if (courseTeacher.getCttime().contains("周四")) {
			//classTable.setZhousi(courseTeacher.getCtlocal());
			classTable.setZhousi(courseTeacher.getCdesc());
		}
		if (courseTeacher.getCttime().contains("周五")) {
			//classTable.setZhouwu(courseTeacher.getCtlocal());
			classTable.setZhouwu(courseTeacher.getCdesc());
		}
		return classTable;
	}
	
	//查看所有班级
	public List<CourseTeacher> getAllTeacherClasses(){
		return courseTeacherMapper.getAllTeacherClasses();
	}
	
	//查看开班人数不足的班级
	public List<CourseTeacher> getlimitTeacherClasses(){
		return courseTeacherMapper.getlimitTeacherClasses();
	}
	
	//班级关闭
	@Transactional
	public boolean closeclass(int cid,int classsort) {		
		courseTeacherMapper.deleteCt_StuInfo(cid, classsort); //删除ct_stu 学生选课信息
		courseTeacherMapper.updateCor_therCstatusClose(cid, classsort); // 更新cor_ther状态为 已关闭
		List<WeekTimeAndClassRoomTime> listWid = courseTeacherMapper.getAllWidByCidClasssort(cid, classsort); //获得周排课记录 wid组
		int countCrtid;
		for (int i = 0; i < listWid.size(); i++) {
			ClassRoomTime crt =classRoomTimeMapper.getCrtidByWid(listWid.get(i).getWid()); //获得 wid 对应的 crtid 
			countCrtid = courseTeacherMapper.getCountCrtid(crt.getCrtid()); //根据crtid 获得 有几个班级 选择了该 crtid 的教室
			if (countCrtid==1) { //如果只有1个   删除 并更新crtid对应的状态为可选
				courseTeacherMapper.deleteWeekTimeByWid(listWid.get(i).getWid());
				classRoomTimeMapper.setStatus(crt.getCrtid());
			}else {
				courseTeacherMapper.deleteWeekTimeByWid(listWid.get(i).getWid()); //大于1个，就不更新crtid状态
			}
			courseTeacherMapper.deleteWeekTimeByWid(listWid.get(i).getWid()); // 删除 weektime内记录
			courseTeacherMapper.deleteCor_therCidClassInfo(cid, classsort); // 删除cor_ther内记录
		}		
		return true;
	}
}
