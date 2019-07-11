	 $(document).ready(function() {
         //调用函数，初始化表格 
         var urlStr = "http://localhost:8080/getHandleClasses";
         TableInit(urlStr);
         $('body').css("background-color","transparent");
         $('.search').css("opacity","0.7");
         $('.search').children().css("opacity","0.7");
       //关闭时清空节点绑定事件
    	 $('#myModal').on('hide.bs.modal', function () {
            $('.addDiv').remove();
        });
	 });
	 
	 function checkChoose() {
		 var a = $('#tech').bootstrapTable('getSelections');
		 if(a.length<=0){
			 alert("请至少选择一项");			 
		}else {
			$('#myModal').modal({
	            show: true,
	            backdrop:'static'
	        });	
			$('#myModalLabel2').text('总课时:'+a[0].clessionCount);
			$('#addClassRoom').removeAttr("disabled");
		}
	}
	 
	 
	 function tableRefesh() {
		 window.location.reload();
	} 
	 
	 function setNoPass() { //审核不通过
		 var a = $('#tech').bootstrapTable('getSelections');
		 var result = $("#result2").val();
		 if(a.length<=0){
			 alert("课程中选择一项");
		}else{  
			var b=JSON.stringify(a);
			  var url="http://localhost:8080/setNoPassed2";
	  		    $.ajax({
	  		        dataType: "json",
	  		        async: "false",
	  		        traditional:true,//这使json格式的字符不会被转码
	  		        data: {"datalist":b},
	  		        type: "post", 
	  		        url: url,
	  		        success : function (data) { 
	  		        	if (data.msg == "true") {
	  		        		alert("操作成功！");
	  		        		window.location.reload();
						}else{
							alert(data.msg);
						}
	  		            
	  		        },
	  		        error : function (data){
	  		            alert(data.responseText);
	  		        }
	  		    });
			} 

	}
	 
	 function setPass() { //排课提交
		 var a = $('#tech').bootstrapTable('getSelections');
		 var result = $("#result").val();
		 if(a.length<=0){
			 alert("课程中选择一项");
		}else if ($(".searchcrt").length<1) {
			alert("请点击+号进行排课");
		}else{  //提交周次节次信息
			var startWeek= new Array();
			var endWeek= new Array();
			var classRoomTime= new Array();
			
			$(".addDiv").each(function(i,n){ 	
				if ($(this).find(".searchcrt").prop("disabled")) {
					startWeek[i] = $(this).find(".startWeek").val();
					endWeek[i] = $(this).find(".endWeek").val();
					classRoomTime[i] = $(this).find(".dropdown-toggle").attr("title");	 
				}																	 						
	 		});
			var b=JSON.stringify(a);
			  var url="http://localhost:8080/setPassed2";
	  		    $.ajax({
	  		        dataType: "json",
	  		        async: "false",
	  		        traditional:true,//这使json格式的字符不会被转码
	  		        data: {"datalist":b,"startWeek":startWeek,"endWeek":endWeek,"classRoomTime":classRoomTime,"result":result},
	  		        type: "post", 
	  		        url: url,
	  		        success : function (data) { 
	  		        	if (data.msg == "true") {
	  		        		alert("排课成功！");
	  		        		window.location.reload();
						}else{
							alert(data.msg);
						}
	  		            
	  		        },
	  		        error : function (data){
	  		            alert(data.responseText);
	  		        }
	  		    });
			} 

	}
	 
	 function TableInit(urlStr) {	                  
	             console.log(urlStr);
	             $('#tech').bootstrapTable({
	                 url: urlStr,         //请求后台的URL（*）
	                 method: 'get',                      //请求方式（*）
	                 toolbar: '#toolbar',    	//工具按钮用哪个容器
	                 cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	                 pagination: true,                   //是否显示分页（*）
	                 sortable: false,                     //是否启用排序
	                 pageNumber: 1,                       //初始化加载第一页，默认第一页
	                 showLoading: 'none',
	                 pageSize: 5,                       //每页的记录行数（*）
	                 pageList: [10, 15, 20, 25],        //可供选择的每页的行数（*）
	                 search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端
	                 strictSearch: false,
	                 singleSelect: true,
	                 showColumns: false,                  //是否显示所有的列
	                 showRefresh: false,                  //是否显示刷新按钮
	                 minimumCountColumns: 3,             //最少允许的列数
	                 clickToSelect: true,                //是否启用点击选中行
	                 uniqueId: 'tid',                     //每一行的唯一标识，一般为主键列
	                 showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
	                 cardView: false,                    //是否显示详细视图
	                 detailView: false,                   //是否显示父子表
	                 sidePagination: 'client',
	                 columns: [{
	                	 field: 'tid',
	                     checkbox: true
	                 },{
	                     field: 'cid',
	                     title: '课程编号',
	                     align: 'center'
	                 },{
	                     field: 'cname',
	                     title: '课程名',
	                     align: 'center'
	                 },{
	                     field: 'classsort',
	                     title: '开课班级',
	                     align: 'center'
	                 },{
	                     field: 'uname',
	                     title: '教师姓名',
	                     align: 'center'
	                 },{
	                     field: 'score',
	                     title: '学分',
	                     align: 'center'
	                 },{
	                     field: 'clessionCount',
	                     title: '学时',
	                     align: 'center'
	                 },{
	                     field: 'ctype',
	                     title: '课程分类',
	                     align: 'center'
	                 },{
	                     field: 'csortname',
	                     title: '前置课程',
	                     align: 'center'
	                 },{
	                     field: 'cdesc',
	                     title: '课程描述',
	                     align: 'center'
	                 }]
	             });
		}
	 
     function addWeek(obj){
        var html = '<div class="input-group addDiv"  >'+
                 '<input type="text"  placeholder="起始周" style="float:left;height:35px;width:70px;" class="form-control startWeek" >'+
                 '<input type="text" placeholder="结束周" style="float:left;height:35px;width:70px;" class="form-control endWeek">'+
                 '<button class="btn btn-success searchcrt" style="float:left;height:35px;width:65px;"   title="查询可选教室节次" ><span class="glyphicon glyphicon-search"></span>查询</button>'+
                 '<input  style="float:left;height:35px;width:50px;" value="周节次:" disabled="disabled">'+
                 '<select class="selectpicker classroom" style="float:left;height:35px;width:305px;"  data-live-search="true">'+
                 '</select>'+              
                 '<span class="input-group-btn style="float:left;height:35px;width:40px;">'+
                 '<button class="btn btn-info delWeek" type="button" data-toggle="tooltip" title="删除" ><span class="glyphicon glyphicon-minus"></span></button>'+
                 '</span>'+
                 '</div>';
         obj.insertAdjacentHTML('beforebegin',html);
         $('#addClassRoom').attr('disabled','disabled');
         
     }
     
     $(document).on('click','.delWeek',function(){
         var el = this.parentNode.parentNode;        
         if (confirm("确定删除该项吗？")){
             el.parentNode.removeChild(el);
         }
         $('#addClassRoom').removeAttr("disabled");
     })
	 
     function checkLesson(s1,e1) { //排课周次有相交的话过滤掉已选周节次
    	 var list = new Array();
    	 $(".addDiv").each(function(i,n){ // 排课时对冲突进行过滤
 			if ($(".addDiv").length==1) { //第一个不检查
 				return null;
 			}else {
 				if (i!=($(".addDiv").length-1)) {
 					var start = $(this).find(".startWeek").val();
 					var end = $(this).find(".endWeek").val();
 					var selected = $(this).find(".dropdown-toggle").attr("title");
 					if (((start>=s1 && start<=e1)||(start<=s1 && end>=s1))) {
						list.push(selected);
					}	
 				}											
 			}			
 		});
    	 return list;
	}
     
     
	 $(document).on('click','.searchcrt',function(){
		 if ($(this).next().next().children().length>0) { //重新查询的时候删除原bootstrap生成的所有节点
			 $(this).next().next().replaceWith('<select class="selectpicker classroom" style="float:left;height:35px;width:305px;"  data-live-search="true"></select>');
		}
		$('#addClassRoom').removeAttr("disabled"); 
		var a = $('#tech').bootstrapTable('getSelections');
		var flag = false;
		var flagalert = false;
		var str1 ="";
		var str2 = "";
		var start = $(this).prev().prev().val();
		var end = $(this).prev().val();
		var s1 = parseInt(start);//如果变量val是字符类型的数则转换为int类型 如果不是则ival为NaN
		var e1 = parseInt(end);
	    if (start==""||end=="") {
			alert("请先输入起始周和结束周");
		}else if(isNaN(s1)||isNaN(e1)){
	        alert("检查输入的是否为大于0的整数");
	    }else if(s1>e1){
	        alert("起始周不能大于结束周");
	    }else if (s1<=0) {
			alert("输入有误");
		}else {
			var b=JSON.stringify(a);
			var list = checkLesson(s1,e1);
		    $.ajax({
		    	async: false,
		    	data: {"start":start,"end":end,"datalist":b},
			    type: "post", 
		        url: "http://localhost:8080/getClassRoomTimeSelect",
		        success: function (data) {
		        	if (data==null||data=="") {
		        		 alert("该方案有误,或者无满足教室");
					}else{
						var str ="";
						var flag2;
			            for (var i = 0; i < data.length; i++) {
			            	flag2=true;
			            	if (list.length>0) {
			            		for (var j = 0; j < list.length; j++) {
									if ((data[i].ctlocal+"_"+data[i].cttime)==list[j] || list[j].match(data[i].cttime)) {									
										flag2 = false;
										flagalert=true;
										str2+=(data[i].ctlocal+"_"+data[i].cttime+"  ");
									}								
								}
							}		            	
			            	if (flag2) {
				            	var a= "<option value='"+data[i].ctlocal+"_"+data[i].cttime+"'>"+data[i].ctlocal+"_"+data[i].cttime+"</option>";
				                str +=a;
							}			            	
			            }
			            flag=true;
			           str1=str;   
					}	        	 	         
		        },
		        error: function (data) {
		            alert("请求错误，查询教室失败" + data);
		        }
		    });
		    if (flagalert) {
		    	alert("该列表已经过滤了和上面已选周次的冲突教室节次:"+list+"以及所有会冲突的时间节次:"+str2);
			}
		    if (flag) {
		    	$(this).next().next().append(str1);
		        $(this).next().next().selectpicker('refresh');
		        $(this).next().next().selectpicker('render');
			}
		    $(this).prev().prev().attr("disabled","disabled");
		    $(this).prev().attr("disabled","disabled");
		    $(this).attr("disabled","disabled");
		}
	 })
	 
	 
