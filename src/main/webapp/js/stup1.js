	 $(document).ready(function() {
         //调用函数，初始化表格 
         var urlStr = "http://localhost:8080/stucourse";
         TableInit(urlStr);
         $('body').css("background-color","transparent");
         $('.search').css("opacity","0.7");
         $('.search').children().css("opacity","0.7");
	 });
	 
	 function tableRefesh() {
		 window.location.reload();
	}
	 function getNeverChoose(){
		 var urlStr = "http://localhost:8080/stunevercourse";
		 $('#lesson').bootstrapTable('destroy');
		 TableInit(urlStr);
	 }
	 
	 function checkChoose() {
		 var a = $('#lesson').bootstrapTable('getSelections');
		 if(a.length<=0){
			 alert("请选择一门课程");			 
		}else {
			$('#myModal').modal({
	            show: true,
	            backdrop:'static'
	        });
			$('#classes').bootstrapTable('destroy');
			$('#classes').bootstrapTable({
                url: "http://localhost:8080/getclassesByCourse/"+a[0].cid,         //请求后台的URL（*）
                method: 'get',                      //请求方式（*）             
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                sortable: false,                     //是否启用排序
                pageNumber: 1,                       //初始化加载第一页，默认第一页
                showLoading: 'none',
                pageSize: 5,                       //每页的记录行数（*）
                pageList: [10, 15, 20, 25],        //可供选择的每页的行数（*）
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
                }, {
                    field: 'classsort',
                    title: '班级',
                    align: 'center'
                },{
                    field: 'uname',
                    title: '开班教师',
                    align: 'center'
                },{
                    field: 'ctlocal',
                    title: '[上课周次] 上课地点_上课时间',
                    align: 'center'
                }, {
                    field: 'currentCount',
                    title: '当前已选人数',
                    align: 'center'
                }, {
                    field: 'cmaxCount',
                    title: '人数上限',
                    align: 'center'
                }]
            });
		}
	}
	 
	 function upChoose() {
		 var a = $('#lesson').bootstrapTable('getSelections');
		 var c = $('#classes').bootstrapTable('getSelections');
		 if(c.length<=0){
			 alert("请选择一个班级");
		}else{
			var b=JSON.stringify(a);
			var d=JSON.stringify(c);
			  var url="http://localhost:8080/stuChooseCourse";
	  		    $.ajax({
	  		        dataType: "json",
	  		        async: "false",
	  		        traditional:true,//这使json格式的字符不会被转码
	  		        data: {"datalist":b,"datalist2":d},
	  		        type: "post", 
	  		        url: url,
	  		        success : function (data) {
	  		        	alert(data);
	  		        	window.location.reload();
	  		        },
	  		        error : function (data){
	  		            alert(data.responseText);
	  		        }
	  		    });
			} 

	}
	 
	 function TableInit(urlStr) {	                  
	             console.log(urlStr);
	             $('#lesson').bootstrapTable({
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
	                 }, {
	                     field: 'cid',
	                     title: '课程编号',
	                     align: 'center'
	                 }, {
	                     field: 'cname',
	                     title: '课程名',
	                     align: 'center'
	                 }, {
	                     field: 'uname',
	                     title: '开课教师',
	                     align: 'center'
	                 }, {
	                     field: 'sex',
	                     title: '性别',
	                     align: 'center'
	                 },  {
	                     field: 'score',
	                     title: '学分',
	                     align: 'center'
	                 }, {
	                     field: 'clessionCount',
	                     title: '学时',
	                     align: 'center'
	                 },{
	                     field: 'ctype',
	                     title: '课程分类',
	                     align: 'center'
	                 },{
	                     field: 'cdesc',
	                     title: '课程描述',
	                     align: 'center'
	                 }]
	             });
		}		
