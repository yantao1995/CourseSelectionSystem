<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>techp4</title>
    <script src="../jquery-1.11.3/jquery.min.js"></script>
    <link href="../css/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="../js/bootstrap-table.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-table-zh-CN.min.js"></script>
    <script src="../js/techp4.js"></script>
</head>
<body>
	<div id="toolbar" class="btn-group">
            <button id="btn_reflash" type="button" class="btn btn-success" title="刷新" onclick="tableRefesh()">
                <span class="glyphicon glyphicon-refresh" ></span>
            </button>
    </div>
	<table id="tech">
	    <thead>
	    	<tr>
	    		<th data-field="cid">课程编号</th>
		        <th data-field="cname">课程名</th>
		        <th data-field="score">学分</th>
		        <th data-field="clessionCount">学时</th>
		        <th data-field="csortname">先修序列</th>
		        <th data-field="ctype">课程分类</th>
		        <th data-field="cdesc">描述</th>
		        <th data-field="result">驳回原因</th>
	        </tr>
	    </thead>
	</table>	
</body>
</html>