function tobeBig() {
    $("#d1").css("z-index","1");
    $("h2").css("z-index","1");
    $("a").css("z-index","1");
    $("#reset").css("z-index","11");
    $("#reset").css("transition","all 1s");
}

function tobeSmall() {
    $("#d1").css("z-index","11");
    $("h2").css("z-index","11");
    $("a").css("z-index","11");
    $("#d1").css("transition","all 0.2s");
    $("h2").css("transition","all 0.2s");
    $("a").css("transition","all 0.2s");
    $("#reset").css("z-index","9");
    $("#reset").css("transition","all 0.3s");
}

function changeImg1() {
	var obj = document.getElementById("yzm");
	obj.src = "http://localhost:8080/yzmlogin?"+Math.random() ;
}

function changeImg2() {
	var obj = document.getElementById("yzm2");
	obj.src = "http://localhost:8080/yzmreset?"+Math.random() ;
}

function check1() {
	var t1 = $("input[name='username']").val();
	var t2 = $("input[name='password']").val();
	var t3 = $("input[name='yzm']").val();
	var flag = false;
    if (t1 =="" || t2==""|| t3 ==""){
        $("#sp1").html("请填写正确信息");
        return false;
    }else {
    	$.ajax({
    		async:false,
            type: "post",
            url: "http://localhost:8080/login",
            data: { "username": t1, "password": t2, "yzm": t3},
            success: function(data) {
            	if (data != "true") {
            		 $("#sp1").html(data);
                     flag = false;
				}else {
					flag = true;
				}             
            }
        });    	
    }
    return flag;
}

function check2() {
	var t1 = $("input[name='username2']").val();
	var t2 = $("input[name='telphone2']").val();
	var t3 = $("input[name='newpassword2']").val();
	var t4 = $("input[name='againpassword2']").val();
	var t5 = $("input[name='yzm22']").val();
    var flag = false;
    if (t1 =="" ||t2 ==""||t5 ==""){
    	$("#sp2").html("信息不全");
        return false;
    }else if (t3 != t4) {
    	$("#sp2").html("密码不一致");
		return false;
	}else{
		$.ajax({
    		async:false,
            type: "post",
            url: "http://localhost:8080/reset",
            data: { "username": t1, "telephone": t2, "p1": t3, "p2": t4, "yzm": t5},
            success: function(data) {
            	if (data != "true") {
            		 $("#sp2").html(data);
                     flag = false;
				}else {
					flag = true;
				}             
            }
        }); 
        return flag;
    }
}
