<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>admp7</title>
    <script src="../jquery-1.11.3/jquery.min.js"></script>
    <link href="../css/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="../js/bootstrap-table.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-table-zh-CN.min.js"></script>
    <script src="../js/admp7.js"></script>
    <style type="text/css">
    	#lesson tr:nth-child(even){
			background: #fafafa;
		}
    </style>
</head>
<body>
	<div id="toolbar" class="btn-group">
            <button id="btn_reflash" type="button" class="btn btn-success" title="人数不满足开班条件" onclick="getLimitTeacherClasses()">
                <span class="glyphicon glyphicon-equalizer" >人数不足</span>
            </button>
            <button id="btn_reflash" type="button" class="btn btn-success" title="查看所有班级" onclick="tableRefesh()">
                <span class="glyphicon glyphicon-th" >全部班级</span>
            </button>
    </div>
	<table id="tech">

	</table>
</body>
</html>