package com.pzhu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pzhu.bean.SelectTime;
import com.pzhu.mapper.SelectTimeMapper;

@Service
public class SelectTimeService {
	
	@Autowired
	private SelectTimeMapper selectTimeMapper;
	
	//获得选课时间
	public SelectTime getSelectTime() {
		return selectTimeMapper.getSelectTime();
	}
	
	//设置选课时间
	public boolean setSelectTime(java.sql.Date starttime,java.sql.Date endtime) {
		return selectTimeMapper.setSelectTime(starttime,endtime);
	}
}
