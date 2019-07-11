<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>techp7</title>
    <script src="../jquery-1.11.3/jquery.min.js"></script>
    <link href="../css/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="../js/bootstrap-table.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-table-zh-CN.min.js"></script>
    <script src="../js/techp7.js"></script>
</head>
<body>
	<div id="toolbar" class="btn-group">
            <button id="btn_reflash" type="button" class="btn btn-success" title="刷新" onclick="tableRefesh()">
                <span class="glyphicon glyphicon-refresh" ></span>
            </button>
    </div>
	<table id="tech">

	</table>
	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">班级成员</h4>
            </div>
            <div class="modal-body">
				<table id="person">
				</table>
			</div>
            <div class="modal-footer">           	
                <button type="button" class="btn btn-default " data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->	
</body>
</html>