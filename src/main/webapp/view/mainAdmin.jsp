<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>大学生选课系统</title>
<link href="../css/admPage.css" rel="stylesheet" type="text/css" />
    <script src="../jquery-1.11.3/jquery.js"></script>
    <script src="../js/admPage.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="../js/bootstrap.min.js"></script>
    <link href="../dist/css/bootstrap-datepicker.min.css" rel="stylesheet" type="text/css" />
    <script src="../dist/js/bootstrap-datepicker.min.js"></script>
</head>
<body>
	<div id="nav">
	    <img id="img1" src="../img/logo2.png">
	    <h2>教务处后台管理</h2>
	    <img id="yonghu" alt="用户" src="../img/userblack.png">
	    <span id="name1">&nbsp;${sessionScope.name}</span>
	    <div id="setime">
	    	<img id="shalou" alt="时间" src="../img/shalou.png">
	    	<span id="starttime">2019-1-1</span>
	    	<span >至</span>
	    	<span id="endtime">2019-1-12</span>
	    	<button type="button" onclick="bttime()" id="bttime" class="btn btn-default btn-sm">更改</button>
	    </div>
	</div>
	<div id="left">
	    <table id="t1">
	        <tr class="tr1" id="pg1">
	            <td class="img2"><img src="../img/admstu.png"></td>
	            <td class="tit">学生信息维护</td>
	        </tr>
	        <tr class="tr1" id="pg2">
	            <td class="img2"><img src="../img/admtech.png"></td>
	            <td class="tit">教师信息维护</td>
	        </tr>	 
	        <tr class="tr1" id="pg3">
	            <td class="img2"><img src="../img/admsp.png"></td>
	            <td class="tit">开课审批排课</td>
	        </tr>
	        <tr class="tr1" id="pg6">
	            <td class="img2"><img src="../img/admsp.png"></td>
	            <td class="tit">新增开班审批</td>
	        </tr>
	        <tr class="tr1" id="pg4">
	            <td class="img2"><img src="../img/jiaoshi.png"></td>
	            <td class="tit">教室信息维护</td>
	        </tr>
	        <tr class="tr1" id="pg5">
	            <td class="img2"><img src="../img/shijian.png"></td>
	            <td class="tit">教室课时维护</td>
	        </tr>
	        <tr class="tr1" id="pg7">
	            <td class="img2"><img src="../img/bjgl.png"></td>
	            <td class="tit">班级管理</td>
	        </tr>
	    </table>
	</div>
	<iframe id="right" src="http://localhost:8080/admp1">
	
	</iframe>
	
<!-- 模态框（Modal）选课时间段设置 -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
               <h4 class="modal-title" id="myModalLabel">选课时间段设置</h4>             
            </div>
            <div class="modal-body">
				<div class="row">
			        <div id="sandbox-container">
			            <div class="input-daterange input-group" id="datepicker">
			                <span class="input-group-addon">起止时间:</span>
			                <input type="text" placeholder="开始时间" class="input-sm form-control" id="stime"/>
			                <span class="input-group-addon">→</span>
			                <input type="text" placeholder="结束时间" class="input-sm form-control" id="etime"/>
			            </div>
			        </div>
			    </div>
			</div>
            <div class="modal-footer">          	
                <button type="button" class="btn btn-default " data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="subtime()">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>