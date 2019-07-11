<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>stup2</title>
    <script src="../jquery-1.11.3/jquery.min.js"></script>
    <link href="../css/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="../js/bootstrap-table.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-table-zh-CN.min.js"></script>
    <script src="../js/stup2.js"></script>
    <style type="text/css">
    	#lesson tr:nth-child(0){
			background: #fafafa;
		}
    </style>
</head>
<body>
	<div id="toolbar" class="btn-group">           
            <button id="btn_reflash" type="button" class="btn btn-success" title="刷新" onclick="tableRefesh()">
                <span class="glyphicon glyphicon-refresh" ></span>&nbsp;刷新
            </button>
            <button id="btn_never" type="button" class="btn btn-success" title="显示未修过的课程" onclick="getChoose()">
                <span class="glyphicon glyphicon-remove" ></span>&nbsp;退选
            </button>
    </div>
	<table id="lesson">

	</table>	
</body>
</html>