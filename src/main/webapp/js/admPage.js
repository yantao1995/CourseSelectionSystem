/**
 * Created by YT on 2019/3/8.
 */

function bttime(){
	$('#myModal').modal({
        show: true,
        backdrop:'static'
    });	
	$('#sandbox-container .input-daterange').datepicker({
        format: "yyyy-mm-dd",
        lauguage: "zh-CN"
    })		
}

function subtime(){
	var a = $('#stime').val();
	var b = $('#etime').val();
	$.ajax({
        type: "post",
        url: "http://localhost:8080/setSelectTime",
        data:{"a":a,"b":b},
        success: function(data) {
        	alert(data);
        	window.location.reload();
        }
    });
}


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
            url: "http://localhost:8080/admp1",
            success: function(data) {
            	$('iframe').attr('src','admp1');        
            }
        });
    });
    
    $("#pg2").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/admp2",
            success: function(data) {
            	$('iframe').attr('src','admp2');          
            }
        });
    });
    
    $("#pg3").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/admp3",
            success: function(data) {
            	$('iframe').attr('src','admp3');          
            }
        });
    });
    $("#pg4").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/admp4",
            success: function(data) {
            	$('iframe').attr('src','admp4');          
            }
        });
    });
    $("#pg5").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/admp5",
            success: function(data) {
            	$('iframe').attr('src','admp5');          
            }
        });
    });
    $("#pg6").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/admp6",
            success: function(data) {
            	$('iframe').attr('src','admp6');          
            }
        });
    });
    $("#pg7").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/admp7",
            success: function(data) {
            	$('iframe').attr('src','admp7');          
            }
        });
    });
})

