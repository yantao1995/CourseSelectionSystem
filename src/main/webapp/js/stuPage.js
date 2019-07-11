/**
 * Created by YT on 2019/3/8.
 */
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
            url: "http://localhost:8080/stup1",
            success: function(data) {
            	$('iframe').attr('src','stup1');        
            }
        });
    });
    
    $("#pg2").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/stup2",
            success: function(data) {
            	$('iframe').attr('src','stup2');          
            }
        });
    });
    
    $("#pg3").click(function () {
    	$.ajax({
            type: "post",
            url: "http://localhost:8080/stup3",
            success: function(data) {
            	$('iframe').attr('src','stup3');          
            }
        });
    });
})

