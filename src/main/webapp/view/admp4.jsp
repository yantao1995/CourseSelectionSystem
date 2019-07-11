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
    <script src="../js/admp4.js"></script>
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
            <button id="btn_upload" type="button" class="btn btn-success" title="增加" onclick="addTech()">
                <span class="glyphicon glyphicon-plus" ></span>
            </button>
            <button id="btn_upload" type="button" class="btn btn-success" title="删除" onclick="deleteTech()">
                <span class="glyphicon glyphicon-minus" ></span>
            </button>
            <button id="btn_upload" type="button" class="btn btn-success" title="提交" onclick="updateTech()">
                <span class="glyphicon glyphicon-ok" ></span>
            </button>
    </div>
	<table id="tech">
	    <thead>
	    	<tr>
	    		<th data-field="roomid">教室编号</th>
		        <th data-field="ctlocal">教室位置</th>
	        </tr>
	    </thead>
	</table>	
</body>
</html>