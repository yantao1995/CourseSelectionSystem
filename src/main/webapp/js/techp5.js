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
	var t1 = $("input[id='name']").val();
	var t2 = $('input[type=radio][name=optionsRadiosinline]:checked').val()
	var t3 = $("#listname  option:checked ").val();
	var t4 = $("#listtype  option:checked ").val();
	var t5 = $("#listmincount  option:checked ").val();
	var t6 = $("#listmaxcount  option:checked ").val();
	var t7 = $("input[id='lessoncounts']").val();
	var t8 = $("#cdesc").val();
    if (t1 =="" || t2==""|| t3 ==""||t4 =="" || t5==""|| t6 ==""||t7 =="" || t8==""){
        alert("请确保信息已经填写完成");    
    }else {
    	$.ajax({
    		async:false,
            type: "post",
            url: "http://localhost:8080/insertNewLesson",
            data: { "cname": t1, "score": t2, "csortname": t3, "ctype": t4, "climitCount": t5, "cmaxCount": t6, "clessionCount": t7,"cdesc": t8},
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
        url: urlStr,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        type: "get",
        success: function (data) {
        	var str = "<label for='listname' class='col-sm-2 control-label'>前置课程</label>";
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

