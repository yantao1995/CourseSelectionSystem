<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>大学生选课系统</title>
<link href="../css/stuPage.css" rel="stylesheet" type="text/css" />
    <script src="../jquery-1.11.3/jquery.js"></script>
    <script src="../js/stuPage.js"></script>
</head>
<body>
	<div id="nav">
	    <img id="img1" src="../img/logo2.png">
	    <h2>大学生选课系统</h2>
	    <img id="yonghu" alt="用户" src="../img/userblack.png">
	    <span id="name1"> ${sessionScope.name}</span>
	    <img id="nianji" alt="年级" src="../img/nianji.png">	    	   
	    <span id="grade1"> ${sessionScope.grade}</span>
	    <img id="banji" alt="班级" src="../img/banji.png">
	     <span id="part1"> ${sessionScope.partment}</span>
	    <div id="setime">
	    	<img id="shalou" alt="时间" src="../img/shalou.png">
	    	<span id="starttime">2019-1-1</span>
	    	<span >至</span>
	    	<span id="endtime">2019-1-12</span>
	    </div>
	</div>
	<div id="left">
	    <table id="t1">
	        <tr class="tr1" id="pg1">
	            <td class="img2"><img src="../img/course1.png"></td>
	            <td class="tit">课程选修</td>
	        </tr>
	        <tr class="tr1" id="pg2">
	            <td class="img2"><img src="../img/change1.png"></td>
	            <td class="tit">课程退选</td>
	        </tr>
	        <tr class="tr1" id="pg3">
	            <td class="img2"><img src="../img/kebiao1.png"></td>
	            <td class="tit">查看课表</td>
	        </tr>
	    </table>
	</div>
	<iframe id="right" src="http://localhost:8080/stup1">
	
	</iframe>
</body>
</html>