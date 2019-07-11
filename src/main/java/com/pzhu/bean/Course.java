package com.pzhu.bean;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
@Component
public class Course {
	private	int cid;
	private	String cname;
	private	BigDecimal score;
	private	String cdesc;
	private	String cstatus;
	private	int climitCount;
	private	int cmaxCount;
	private	int clessionCount;
	private String ctype;
	private String csortname;
	private String result;
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getCsortname() {
		return csortname;
	}
	public void setCsortname(String csortname) {
		this.csortname = csortname;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public BigDecimal getScore() {
		return score;
	}
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	public String getCdesc() {
		return cdesc;
	}
	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}
	public String getCstatus() {
		return cstatus;
	}
	public void setCstatus(String cstatus) {
		this.cstatus = cstatus;
	}
	public int getClimitCount() {
		return climitCount;
	}
	public void setClimitCount(int climitCount) {
		this.climitCount = climitCount;
	}
	public int getCmaxCount() {
		return cmaxCount;
	}
	public void setCmaxCount(int cmaxCount) {
		this.cmaxCount = cmaxCount;
	}
	public int getClessionCount() {
		return clessionCount;
	}
	public void setClessionCount(int clessionCount) {
		this.clessionCount = clessionCount;
	}
}
