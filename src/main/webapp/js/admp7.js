	 $(document).ready(function() {
         //调用函数，初始化表格 
         var urlStr = "http://localhost:8080/getAllTeacherClasses";       
         TableInit(urlStr);
         $('body').css("background-color","transparent");
         $('.search').css("opacity","0.7");
         $('.search').children().css("opacity","0.7");
	 });
	 
	 function getLimitTeacherClasses() {
		 var urlStr2 = "http://localhost:8080/getlimitTeacherClasses";
		 $('#tech').bootstrapTable('destroy');
		 TableInit(urlStr2);
	}
	 
	 function tableRefesh() {
		 window.location.reload();
	} 	 
	 window.operateEvents = {
          'click .btn-success': function (e, value, row, index) { 
        	  if (confirm("确定关闭吗？该操作无法恢复！")){
                var url = "http://localhost:8080/closeclass";
                $.ajax({
	  		        dataType: "json",
	  		        async: "false",
	  		        traditional:true,//这使json格式的字符不会被转码
	  		        data: {"cid":row.cid,"classsort":row.classsort},
	  		        type: "post", 
	  		        url: url,
	  		        success : function (data) {
	  		        	alert(data);	  		            
	  		        },
	  		        error : function (data){
	  		            alert(data.responseText);
	  		        }
	  		    });
        	  }
           }
	  };
	 
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
	                 showColumns: false,                  //是否显示所有的列
	                 showRefresh: false,                  //是否显示刷新按钮
	                 minimumCountColumns: 3,             //最少允许的列数
	                 clickToSelect: true,                //是否启用点击选中行
	                 uniqueId: 'tid',                     //每一行的唯一标识，一般为主键列
	                 showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
	                 cardView: false,                    //是否显示详细视图
	                 detailView: false,                   //是否显示父子表
	                 sidePagination: 'client',
	                 columns: [ {
	                     field: 'ctid',
	                     title: '班级编号',
	                     align: 'center'
	                 },{
	                     field: 'cname',
	                     title: '课程名',
	                     align: 'center'
	                 },{
	                     field: 'classsort',
	                     title: '班级',
	                     align: 'center'
	                 },{
	                     field: 'ctlocal',
	                     title: '[上课周次]地点_时间',
	                     align: 'center'
	                 },{
	                	 field: 'currentCount',
	                     title: '已选人数',
	                     align: 'center'
	                 },{
	                	 field: 'climitCount',
	                     title: '开班人数',
	                     align: 'center'
	                 },{
	                     field: 'operate',
	                     title: '班级操作',
	                     align: 'center',
	                     events: operateEvents,
	                     formatter: operateFormatter	                    
	                 }],

	             });
		}
	 
	 function operateFormatter(value, row, index) {
	       return [
	       '<button type="button" class="btn btn-success">关闭班级</button>',
	        ].join('');
	    }
	 
	 