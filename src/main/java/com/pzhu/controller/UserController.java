package com.pzhu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pzhu.bean.User;
import com.pzhu.service.UserService;

@Controller
public class UserController{
	@Autowired
	private UserService userService;
	
	//登录验证
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String getlogin(@RequestParam("username") String sid,
			@RequestParam("password") String upassword,
			@RequestParam("yzm") String yzm	,
			HttpSession session
			) {	
		String yzmString = (String) session.getAttribute("yzmlogin");
		int uid;
		try {
			uid = Integer.parseInt(sid);
		} catch (Exception e) {
			return "用户名格式不正确";
		}
		if (upassword !="" && yzm != "") {
			yzm=yzm.toUpperCase(); 
			User user = userService.getStu(uid);
			if (user != null) {
				return	checkAndLogin(uid,upassword,yzmString,session,yzm,user);
			}else if ((user = userService.getTher(uid))!=null) {
				return	checkAndLogin(uid,upassword,yzmString,session,yzm,user);
			}else if ((user = userService.getAdm(uid))!=null) {
				return	checkAndLogin(uid,upassword,yzmString,session,yzm,user);
			}else {
				return "该用户不存在";
			}
		}
		return "用户名或者密码不正确";		
	}
	
	//修改密码验证
	@ResponseBody
	@RequestMapping(value="/reset",method=RequestMethod.POST)
	public String getReset(@RequestParam("username") String sid,
			@RequestParam("telephone") String stelephone,
			@RequestParam("p1") String up1,
			@RequestParam("p2") String up2,
			@RequestParam("yzm") String yzm	,
			HttpSession session
			) {	
		String yzmString = (String) session.getAttribute("yzmreset");
		int uid,utelephone;
		boolean flag =false;
		try {
			uid = Integer.parseInt(sid);
			utelephone = Integer.parseInt(stelephone);
		} catch (Exception e) {
			return "填写格式错误";
		}
		if (yzm != "" && up1 !="" &&up2 !="" && yzm!="" && up1.equals(up2)) {
			yzm=yzm.toUpperCase(); 
			User user = userService.getStu(uid);
			String resetType;
			if (user !=null) {
				resetType = "学生";
				return checkAndReset(flag,yzmString,yzm,uid,user,utelephone,up1,session,resetType);
			}else if ((user = userService.getTher(uid))!=null) {
				resetType = "教师";
				return checkAndReset(flag,yzmString,yzm,uid,user,utelephone,up1,session,resetType);
			}else if ((user = userService.getAdm(uid))!=null) {
				resetType = "教务处";
				return checkAndReset(flag,yzmString,yzm,uid,user,utelephone,up1,session,resetType);
			}else {
				return "用户不存在";
			}
		}
		return "重置密码失败";
	}
	
	@RequestMapping(value="/oauth")
	public String getMainPage(HttpSession session) {
		String type;
		try {			
			type =(String) session.getAttribute("type");
			if (type.equals("学生")) {
				return "mainStudent";
			}
			else if (type.equals("教师")) {
				return "mainTeacher";
			}
			else if (type.equals("教务处")) {
				return "mainAdmin";
			}else {
				System.out.println("意外的访问2");
				return "error";
			}
		} catch (Exception e) {
			System.out.println("意外的访问");
			return "error";
		}					
	}
	
	public String checkAndLogin(int uid,String upassword,String yzmString,HttpSession session,String yzm,User user) {
		// 验证 用户名密码验证码
		if (uid==user.getUid() && user.getUpassword().equals(upassword)) {
			if (yzm.equals(yzmString)) {
				session.setAttribute("id", user.getUid());
				session.setAttribute("password", user.getUpassword());
				session.setAttribute("type", user.getType());
				session.setAttribute("partment", user.getUpartment());
				session.setAttribute("name", user.getUname());
				if (user.getUgrade()!=0) {
					session.setAttribute("grade", user.getUgrade());
				}		
				return "true";
			}else {
				return "验证码不正确";
			}					
		}else {
			return "密码不正确";
		}
	}
	
	public String checkAndReset(boolean flag,String yzmString,String yzm,int uid,
			User user,int utelephone,String up1,HttpSession session,String resetType) {
		if (yzm.equals(yzmString)) {
			if (uid == user.getUid() && utelephone == user.getUtelephone()) {
				boolean isStu=false;
				//写入密码
				try {
					if (resetType.equals("学生")) {
						flag=userService.upDateStu(up1, uid, utelephone);
						isStu=true;
						System.out.println("学生正在更改密码");
					} else if (resetType.equals("教务处")) {
						flag=userService.upDateAdm(up1, uid, utelephone);
						System.out.println("教务处正在更改密码");
					}else {
						flag=userService.upDateTher(up1, uid, utelephone);
						System.out.println("教师正在更改密码");
					}
				} catch (Exception e) {
					e.printStackTrace();
					return "写入密码失败";
				}
				if (flag) {
					session.setAttribute("id", user.getUid());
					session.setAttribute("name", user.getUname());
					if (isStu) {
						session.setAttribute("grade", user.getUgrade());
					}
					session.setAttribute("partment", user.getUpartment());
					session.setAttribute("password", user.getUpassword());
					session.setAttribute("type", user.getType());
					return "true";
				}else {
					return "更改密码失败";
				}
			}else {
				return "密保手机错误";
			}
		}else {
			return "验证码不正确";
		}
	}
}
