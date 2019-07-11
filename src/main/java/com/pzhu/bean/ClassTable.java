package com.pzhu.bean;

public class ClassTable {
	private String timeweek;
	private String zhouyi;
	private String zhouer;
	private String zhousan;
	private String zhousi;
	private String zhouwu;
	
	public ClassTable(String timeweek) {
		this.timeweek = timeweek;
		this.zhouyi ="";
		this.zhouer ="";
		this.zhousan ="";
		this.zhousi ="";
		this.zhouwu ="";
	}	
	public String getTimeweek() {
		return timeweek;
	}
	public void setTimeweek(String timeweek) {
		this.timeweek = timeweek;
	}
	public String getZhouyi() {
		return zhouyi;
	}
	public void setZhouyi(String zhouyi) {
		this.zhouyi += zhouyi;
	}
	public String getZhouer() {
		return zhouer;
	}
	public void setZhouer(String zhouer) {
		this.zhouer += zhouer;
	}
	public String getZhousan() {
		return zhousan;
	}
	public void setZhousan(String zhousan) {
		this.zhousan += zhousan;
	}
	public String getZhousi() {
		return zhousi;
	}
	public void setZhousi(String zhousi) {
		this.zhousi += zhousi;
	}
	public String getZhouwu() {
		return zhouwu;
	}
	public void setZhouwu(String zhouwu) {
		this.zhouwu += zhouwu;
	}
	
}
