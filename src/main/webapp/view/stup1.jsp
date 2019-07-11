<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>stup1</title>
    <script src="../jquery-1.11.3/jquery.min.js"></script>
    <link href="../css/bootstrap-table.min.css" rel="stylesheet" type="text/css" />
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="../js/bootstrap-table.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-table-zh-CN.min.js"></script>
    <script src="../js/stup1.js"></script>
</head>
<body>
	<div id="toolbar" class="btn-group">           
            <button id="btn_reflash" type="button" class="btn btn-success" title="显示全部课程" onclick="tableRefesh()">
                <span class="glyphicon glyphicon-th" ></span>&nbsp;全部课程
            </button>
            <button id="btn_never" type="button" class="btn btn-success" title="显示未修过的课程" onclick="getNeverChoose()">
                <span class="glyphicon glyphicon-th-large" ></span>&nbsp;未修课程
            </button>
            <button id="btn_upload" type="button" class="btn btn-success" title="选择" onclick="checkChoose()">
                <span class="glyphicon glyphicon-ok" ></span>&nbsp;确认选择
            </button>
    </div>
	<table id="lesson">

	</table>
	<!-- 模态框（Modal）排课处理 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">选择上课班级</h4>
            </div>
            <div class="modal-body">
				<table id="classes">
				</table>
			</div>
            <div class="modal-footer">           
                <button type="button" class="btn btn-default " style="float:right;" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" style="float:right;" onclick="upChoose()">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>	
</body>
</html>