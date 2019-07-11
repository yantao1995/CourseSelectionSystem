package com.pzhu.bean;

import org.springframework.stereotype.Component;

@Component
public class WeekTimeAndClassRoomTime {
	private int wid;
	private int startweek;
	private int endweek;
	private int crtid;
	private int roomid;
	private String ctlocal;
	private int timeid;
	private String cttime;
	private String crtstatus;
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getStartweek() {
		return startweek;
	}
	public void setStartweek(int startweek) {
		this.startweek = startweek;
	}
	public int getEndweek() {
		return endweek;
	}
	public void setEndweek(int endweek) {
		this.endweek = endweek;
	}
	public int getCrtid() {
		return crtid;
	}
	public void setCrtid(int crtid) {
		this.crtid = crtid;
	}
	public int getRoomid() {
		return roomid;
	}
	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}
	public String getCtlocal() {
		return ctlocal;
	}
	public void setCtlocal(String ctlocal) {
		this.ctlocal = ctlocal;
	}
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
	public String getCrtstatus() {
		return crtstatus;
	}
	public void setCrtstatus(String crtstatus) {
		this.crtstatus = crtstatus;
	}
	
}
