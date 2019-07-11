/**
 * Created by YT on 2019/3/30.
 */

$(document).ready(function () {
    //调用函数，初始化表格
    var urlStr = "http://localhost:8080/getPassCourseName";
    TableInit(urlStr);
    $('body').css("background-color","transparent");
});

function checkLesson() {
	var t = $("#listname  option:checked ").val();	
    if (t ==""){
        alert("请选择一个已通过审核的课程");    
    }else {
    	$.ajax({
    		async:false,
            type: "post",
            url: "http://localhost:8080/insertNewClass",
            data: { "cname": t},
            success: function(data) {
            	alert(data);   
            	window.location.reload();
            }
        }); 
    }
}


function TableInit(urlStr) {
    console.log(urlStr);
    $.ajax({
    	async: false,
        url: urlStr,
        type: "get",
        success: function (data) {
        	var str = "<label for='listname' class='col-sm-2 control-label'>已通过审核的课程:</label>";
        	str += "<select id='listname' class='selectpicker col-sm-3'  data-live-search='true' >";
		    str += "	<option value='无'>无</option>";
            for (var i = 0; i < data.length; i++) {
            	var a= "<option value='"+data[i].cname+"'>"+data[i].cname+"</option>";
                str +=a;
            }
            str += "</select>";
            console.log(str);
           $('#s1').append(str);
        },
        error: function (data) {
            alert("查询课程列表失败" + data);
        }
    });
}

