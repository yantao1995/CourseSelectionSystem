package com.pzhu.bean;

import org.springframework.stereotype.Component;

@Component
public class ClassTime {
	private int timeid;
	private String cttime;
	public int getTimeid() {
		return timeid;
	}
	public void setTimeid(int timeid) {
		this.timeid = timeid;
	}
	public String getCttime() {
		return cttime;
	}
	public void setCttime(String cttime) {
		this.cttime = cttime;
	}
	
}
