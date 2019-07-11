package com.pzhu.bean;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class MarkBean {
	private int mid;
	private int uid;
	private int cid;
	private BigDecimal scores;
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public BigDecimal getScores() {
		return scores;
	}
	public void setScores(BigDecimal scores) {
		this.scores = scores;
	}
	
}
