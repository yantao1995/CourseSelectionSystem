package com.pzhu.bean;

import org.springframework.stereotype.Component;


@Component
public class SelectTime {
	private int sid;
	private java.sql.Date starttime;
	private java.sql.Date endtime;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public java.sql.Date getStarttime() {
		return starttime;
	}
	public void setStarttime(java.sql.Date starttime) {
		this.starttime = starttime;
	}
	public java.sql.Date getEndtime() {
		return endtime;
	}
	public void setEndtime(java.sql.Date endtime) {
		this.endtime = endtime;
	}
	
}
