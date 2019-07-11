package com.pzhu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.pzhu.bean.MarkBean;

@Mapper
public interface MarkMapper {
	@Results({
		@Result(property="mid",column="mid"),
		@Result(property="uid",column="uid"),
		@Result(property="cid",column="cid"),
		@Result(property="scores",column="scores"),
	})

	//获得该uid，的cid的分数
	@Select("SELECT * FROM mark WHERE uid=#{uid} AND cid =#{cid}")
	MarkBean getScoresByUidCid(@Param("uid")int uid,@Param("cid")int cid);
}
