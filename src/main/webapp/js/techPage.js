/**
 * Created by YT on 2019/3/8.
 */

var bt='#pg1';
$(document).ready(function () {
	$.ajax({
        type: "post",
        url: "http://localhost:8080/getSelectTime",
        success: function(data) {
        	$('#starttime').text(data.starttime.trim());
        	$('#endtime').text(data.endtime.trim());   
        }
    });
    $("#pg1").click(function () {	
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/techp1",
            success: function(data) {
            	$('iframe').attr('src','techp1');        
            }
        });
    	$(bt).css("background-color","transparent");
    	bt="#pg1";
    	$(bt).css("background-color","#dadada");   	    	
    });
    
    $("#pg2").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/techp2",
            success: function(data) {
            	$('iframe').attr('src','techp2');          
            }
        });
    	$(bt).css("background-color","transparent");
    	bt="#pg2";
    	$(bt).css("background-color","#dadada");
    });
    
    $("#pg3").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/techp3",
            success: function(data) {
            	$('iframe').attr('src','techp3');          
            }
        });
    	$(bt).css("background-color","transparent");
    	bt="#pg3";
    	$(bt).css("background-color","#dadada");
    });
    $("#pg4").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/techp4",
            success: function(data) {
            	$('iframe').attr('src','techp4');          
            }
        });
    	$(bt).css("background-color","transparent");
    	bt="#pg4";
    	$(bt).css("background-color","#dadada");
    });
    $("#pg5").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/techp5",
            success: function(data) {
            	$('iframe').attr('src','techp5');          
            }
        });
    	$(bt).css("background-color","transparent");
    	bt="#pg5";
    	$(bt).css("background-color","#dadada");
    });
    $("#pg6").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/techp6",
            success: function(data) {
            	$('iframe').attr('src','techp6');          
            }
        });
    	$(bt).css("background-color","transparent");
    	bt="#pg6";
    	$(bt).css("background-color","#dadada");
    });
    $("#pg7").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/techp7",
            success: function(data) {
            	$('iframe').attr('src','techp7');          
            }
        });
    	$(bt).css("background-color","transparent");
    	bt="#pg7";
    	$(bt).css("background-color","#dadada");
    });
    $("#pg8").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/techp8",
            success: function(data) {
            	$('iframe').attr('src','techp8');          
            }
        });
    	$(bt).css("background-color","transparent");
    	bt="#pg8";
    	$(bt).css("background-color","#dadada");
    });
    $("#pg9").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/techp9",
            success: function(data) {
            	$('iframe').attr('src','techp9');          
            }
        });
    	$(bt).css("background-color","transparent");
    	bt="#pg9";
    	$(bt).css("background-color","#dadada");
    });
    $("#pg10").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/techp10",
            success: function(data) {
            	$('iframe').attr('src','techp10');          
            }
        });
    	$(bt).css("background-color","transparent");
    	bt="#pg10";
    	$(bt).css("background-color","#dadada");
    });
    $("#collapse1").attr("aria-expanded","true"); //设置左边侧边栏默认状态
    $("#collapse1").attr("class","panel-collapse collapse in"); 
    $("#collapse2").attr("class","panel-collapse collapse"); 
    $("#collapse2").attr("aria-expanded","false");
    $("#pg1").css("background-color","#dadada");
})

