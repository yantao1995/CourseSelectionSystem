package com.pzhu.bean;

import org.springframework.stereotype.Component;

@Component
public class ClassRoom {
	private int roomid;
	private String ctlocal;
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
	
}
