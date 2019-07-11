package com.pzhu.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginMain {
	
	@RequestMapping(value = "/")
	public ModelAndView login() {
		ModelAndView mView = new ModelAndView();
		mView.setViewName("login");
		return mView;
	}
	
	@RequestMapping(value="/yzmlogin")
	public void Yzm1(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
	        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expire", 0);
	        String yzmType ="yzmlogin";
			YzmUtil yzmUtil = new YzmUtil();
			yzmUtil.getRandcode(request, response,yzmType);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}
	
	@RequestMapping(value="/yzmreset")
	public void Yzm2(HttpServletRequest request,HttpServletResponse response) {
		try {
			response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
	        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expire", 0);
	        String yzmType ="yzmreset";
			YzmUtil yzmUtil = new YzmUtil();
			yzmUtil.getRandcode(request, response,yzmType);
		} catch (Exception e) {
			System.out.println(e.getMessage().toString());
		}
	}
	
}
