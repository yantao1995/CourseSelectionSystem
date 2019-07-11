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
    <script src="../js/admp5.js"></script>
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
            <button id="btn_upload" type="button" class="btn btn-success" title="设置为不可选" onclick="setUnAble()">
                <span class="glyphicon glyphicon-remove" ></span>
            </button>
            <button id="btn_upload" type="button" class="btn btn-success" title="设置为可选" onclick="setAble()">
                <span class="glyphicon glyphicon-ok" ></span>
            </button>
    </div>
	<table id="tech">

	</table>	
</body>
</html>