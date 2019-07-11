<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>admp1</title>
    <script src="../jquery-1.11.3/jquery.min.js"></script>
    <link href="../css/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="../js/bootstrap-table.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-table-zh-CN.min.js"></script>
    <script src="../js/admp1.js"></script>
    <style type="text/css">
    	#lesson tr:nth-child(even){
			background: #fafafa;
		}
    </style>
</head>
<body>
	<div id="toolbar" class="btn-group">
            <button id="btn_reflash" type="button" class="btn btn-success" title="刷新" onclick="tableRefesh()">
                <span class="glyphicon glyphicon-refresh" ></span>
            </button>
            <button id="btn_upload" type="button" class="btn btn-success" title="增加" onclick="addStu()">
                <span class="glyphicon glyphicon-plus" ></span>
            </button>
            <button id="btn_upload" type="button" class="btn btn-success" title="删除" onclick="deleteStu()">
                <span class="glyphicon glyphicon-minus" ></span>
            </button>
            <button id="btn_upload" type="button" class="btn btn-success" title="提交" onclick="updateStu()">
                <span class="glyphicon glyphicon-ok" ></span>
            </button>
    </div>
	<table id="stu">
	    <thead>
	    	<tr>
	    		<th data-field="uid">学号</th>
		        <th data-field="uname">姓名</th>
		        <th data-field="sex">性别</th>
		        <th data-field="upassword">密码</th>
		        <th data-field="ugrade">年级</th>
		        <th data-field="upartment">部门</th>
		        <th data-field="utelephone">密保手机</th>
	        </tr>
	    </thead>
	</table>	
</body>
</html>