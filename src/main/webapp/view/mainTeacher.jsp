<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>大学生选课系统</title>
<link href="../css/techPage.css" rel="stylesheet" type="text/css" />
    <script src="../jquery-1.11.3/jquery.js"></script>
    <script src="../js/techPage.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
	<div id="nav">
	    <img id="img1" src="../img/logo2.png">
	    <h2>教师课程管理</h2>
	    <img id="yonghu" alt="用户" src="../img/userblack.png">
	    <span id="name1">&nbsp;&nbsp;${sessionScope.name}</span>
	    <img id="bumen" alt="用户" src="../img/bumen.png">
	    <span id="part1">&nbsp;&nbsp;${sessionScope.partment}</span>
	    <div id="setime">
	    	<img id="shalou" alt="时间" src="../img/shalou.png">
	    	<span id="starttime">2019-1-1</span>
	    	<span >至</span>
	    	<span id="endtime">2019-1-12</span>
	    </div>
	</div>
	<div id="left">
		<div class="panel-group" id="accordion">
		
		    <div class="panel panel-default">
		        <div class="panel-heading tit" >
		            <h4 class="panel-title">
		                <a data-toggle="collapse" data-parent="#accordion"
		                   href="#collapse1">
		                   <img src="../img/kcgl.png">&nbsp;&nbsp;开课管理
		                </a>
		            </h4>
		        </div>
		        <div id="collapse1" class="panel-collapse collapse in">
		            <div class="panel-body">
		                <ul class="list-group">
		                    <li class="list-group-item" id="pg1"><img src="../img/quanbu.png">&nbsp;&nbsp;所有开课</li>
		                    <li class="list-group-item" id="pg2"><img src="../img/tongguo.png">&nbsp;&nbsp;开课已通过</li>
		                    <li class="list-group-item" id="pg3"><img src="../img/admsp.png">&nbsp;&nbsp;开课审核中</li>
		                    <li class="list-group-item" id="pg4"><img src="../img/weitongguo.png">&nbsp;&nbsp;开课未通过</li>
		                    <li class="list-group-item" id="pg5"><img src="../img/xinzengbanji.png">&nbsp;&nbsp;增加课程</li>
		                </ul>
		            </div>
		        </div>
		    </div>
		    
		    <div class="panel panel-default">
		        <div class="panel-heading tit" >
		            <h4 class="panel-title">
		                <a data-toggle="collapse" data-parent="#accordion"
		                   href="#collapse2">
		                   <img src="../img/bjgl22.png">&nbsp;&nbsp;开班管理
		                </a>
		            </h4>
		        </div>
		        <div id="collapse2" class="panel-collapse collapse in">
		            <div class="panel-body">
		                <ul class="list-group">
		                    <li class="list-group-item" id="pg7"><img src="../img/mybg.png">&nbsp;&nbsp;通过的开班</li>
		                    <li class="list-group-item" id="pg8"><img src="../img/weitongguo.png">&nbsp;&nbsp;未通过的开班</li>
		                    <li class="list-group-item" id="pg10"><img src="../img/kbygb.png">&nbsp;&nbsp;已关闭的开班</li>
		                    <li class="list-group-item" id="pg6"><img src="../img/xinzengbanji.png">&nbsp;&nbsp;增开班级</li>
		                </ul>
		            </div>
		        </div>
		    </div>
		    
		    
			 <div class="panel panel-default">
		        <div class="panel-heading tit" >
		            <h4 class="panel-title">
		                <div id="pg9">
		                   <img src="../img/kebiao1.png">&nbsp;&nbsp;查看课表
		                </div>
		            </h4>
		        </div>
		        
		    </div>
		</div>
	</div>
	<iframe id="right" src="http://localhost:8080/techp1">
	
	</iframe>
</body>
</html>