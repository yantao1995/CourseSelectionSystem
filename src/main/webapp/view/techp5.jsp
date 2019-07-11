<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>techp5</title>
    <script src="../jquery-1.11.3/jquery.min.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="../css/bootstrap-select.min.css" rel="stylesheet" type="text/css" />
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-select.min.js"></script>
    <script src="../js/techp5.js"></script>
</head>
<body>
<div class="container-fluid">
	<form class="form-horizontal"  onsubmit="return false"><br/>
	    <div class="form-group">
	        <label for="name" class="col-sm-2 control-label">课程名</label>
	        <div class="col-sm-2">
	            <input type="text" class="form-control" id="name" placeholder="课程名">
	        </div>
	    </div>
	    <div class="form-group">
	        <label class="col-sm-2 control-label">设置学分</label>
	        <div class="col-sm-8">
		        <label class="radio-inline">
		            <input type="radio" name="optionsRadiosinline" id="score1" value="1.0" checked>1.0
		        </label>
		        <label class="radio-inline">
		            <input type="radio" name="optionsRadiosinline" id="score2"  value="1.5">1.5
		        </label>
		        <label class="radio-inline" >
		            <input type="radio" name="optionsRadiosinline" id="score3"  value="2.0">2.0
		        </label>
		        </label>
		        <label class="radio-inline" >
		            <input type="radio" name="optionsRadiosinline" id="score4"  value="2.5">2.5
		        </label>
		        <label class="radio-inline">
		            <input type="radio" name="optionsRadiosinline" id="score5"  value="3.0">3.0
		        </label>
		        <label class="radio-inline" >
		            <input type="radio" name="optionsRadiosinline" id="score6"  value="3.5">3.5
		        </label>
		        <label class="radio-inline" >
		            <input type="radio" name="optionsRadiosinline" id="score7"  value="3.5">4.0
		        </label>
	        </div>
	    </div>
	    <div class="form-group" id="s1">
	   
	   
	    </div>
	    <div class="form-group">
	    	<label for="listtype" class="col-sm-2 control-label">课程分类</label>
		    <select id="listtype" class="selectpicker col-sm-2"  data-live-search="true" >
		    	<option value="文史类">文史类</option>
		    	<option value="自然科学">自然科学</option>
		    	<option value="语言类">语言类</option>
		    	<option value="艺术类">艺术类</option>
		    	<option value="创业创新类">创业创新类</option>
		    	<option value="实践类">实践类</option>
		    	<option value="慕课类">慕课类</option>
		    </select>
	    </div>
	    <div class="form-group">
	    	<label for="listmincount" class="col-sm-2 control-label">开课最少人数</label>
		    <select id="listmincount" class="selectpicker col-sm-2"  data-live-search="true" >
		    	<option value="15">15</option>
		    	<option value="16">16</option>
		    	<option value="17">17</option>
		    	<option value="18">18</option>
		    	<option value="19">19</option>
		    	<option value="20">20</option>
		    	<option value="25">25</option>
		    </select>
	    </div>
	    <div class="form-group">
	    	<label for="listmaxcount" class="col-sm-2 control-label">开课人数上限</label>
		    <select id="listmaxcount" class="selectpicker col-sm-2"  data-live-search="true" >
		    	<option value="40">40</option>
		    	<option value="41">41</option>
		    	<option value="42">42</option>
		    	<option value="43">43</option>
		    	<option value="44">44</option>
		    	<option value="45">45</option>
		    	<option value="46">46</option>
		    	<option value="47">47</option>
		    	<option value="48">48</option>
		    	<option value="49">49</option>
		    	<option value="50">50</option>
		    </select>
	    </div>
	    
	    <div class="form-group">
	    	<label for="lessoncount" class="col-sm-2 control-label">学时</label>
	    	<div id="lessoncount" class="col-sm-2">  
			    <input type="number" id="lessoncounts" class="form-control" onkeyup="this.value=this.value.replace(/\D|^0/g,'')" onafterpaste="this.value=this.value.replace(/\D|^0/g,'')" placeholder="整数">  			    
		  	</div>  
	    </div>
	    
	    <div class="form-group">
	        <label for="cdesc" class="col-sm-2 control-label">课程描述</label>
	        <div class="col-sm-6">
	            <textarea class="comments" rows="3" cols="100" id="cdesc"> </textarea>
	        </div>
	    </div>
	    
	    <button  class="btn btn-default col-md-offset-2 col-sm-1 " onclick="checkLesson()">提交</button>
	</form>
</div>
</body>
</html>