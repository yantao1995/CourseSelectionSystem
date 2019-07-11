<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>admp6</title>
    <script src="../jquery-1.11.3/jquery.min.js"></script>
    <link href="../css/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="../js/bootstrap-table.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-table-zh-CN.min.js"></script>
    <link href="../css/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
    <script src="../js/bootstrap-select.min.js"></script>
    <script src="../js/admp6.js"></script>
    <style type="text/css">
    	#lesson tr:nth-child(even){
			background: #fafafa;
		}
    </style>
</head>
<body>
	<div id="toolbar" class="btn-group">
            <button id="btn_reflash"  class="btn btn-success" title="刷新" onclick="tableRefesh()">
                <span class="glyphicon glyphicon-refresh" ></span>
            </button>
            <button id="btn_upload"  class="btn btn-success"  title="审核通过" onclick="checkChoose()">
                <span class="glyphicon glyphicon-ok" ></span>
            </button>
            <button id="btn_upload" class="btn btn-success" title="审核不通过" onclick="setNoPass()">
                <span class="glyphicon glyphicon-remove" ></span>
            </button>
    </div>
	<table id="tech">

	</table>
<!-- 模态框（Modal）排课处理 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">排课管理</h4>
                <h5 class="modal-title" id="myModalLabel2">排课管理</h5>
            </div>
            <div class="modal-body">
				<div class="input-group" id="checkweektime">
			        <label class="input-group-addon">周次教室节次</label>
			        <button class="btn btn-info" type="button" data-toggle="tooltip" title="增加教室" id="addClassRoom" onclick="addWeek(this)" ><span class="glyphicon glyphicon-plus"></span></button>
			    </div>
			</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default " style="float:right;" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" style="float:right;" onclick="setPass()">通过</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>