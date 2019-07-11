 package com.pzhu.bean;

import org.springframework.stereotype.Component;

@Component
public class User {
	private	int uid;
	private	int utelephone;
	private	String uname;
	private	String upartment;
	private	String upassword;
	private	String type;
	private	String sex;
	private	int ugrade;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getUtelephone() {
		return utelephone;
	}
	public void setUtelephone(int utelephone) {
		this.utelephone = utelephone;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpartment() {
		return upartment;
	}
	public void setUpartment(String upartment) {
		this.upartment = upartment;
	}
	public String getUpassword() {
		return upassword;
	}
	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public int getUgrade() {
		return ugrade;
	}
	public void setUgrade(int ugrade) {
		this.ugrade = ugrade;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getUid()+getUname()+getUpassword()+getType();
	}
	
}
