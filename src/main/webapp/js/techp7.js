	 $(document).ready(function() {
         //调用函数，初始化表格 
         var urlStr = "http://localhost:8080/myClasses";
         TableInit(urlStr);
         $('body').css("background-color","transparent");
         $('.search').css("opacity","0.7");
         $('.search').children().css("opacity","0.7");
	 });
	 
	 function tableRefesh() {
		 window.location.reload();
	} 	 
	 window.operateEvents = {
	          'click .btn-success': function (e, value, row, index) {           
	                $("#myModal").modal('show');
	                $('#person').bootstrapTable('destroy');
	                var urlStr = ("http://localhost:8080/myClassesperson/"+row.cid+"/"+row.classsort);
	                TableInit2(urlStr);
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
	                     field: 'cid',
	                     title: '课程编号',
	                     align: 'center'
	                 }, {
	                     field: 'cname',
	                     title: '课程名',
	                     align: 'center'
	                 },{
	                     field: 'classsort',
	                     title: '班级号',
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
	                     field: 'operate',
	                     title: '查看班级成员',
	                     align: 'center',
	                     events: operateEvents,
	                     formatter: operateFormatter	                    
	                 }],

	             });
		}
	 
	 function operateFormatter(value, row, index) {
	       return [
	       '<button type="button" class="btn btn-success">查看</button>',
	        ].join('');
	    }
	 
	 function TableInit2(urlStr) {	                  
         console.log(urlStr);
         $('#person').bootstrapTable({
             url: urlStr,         //请求后台的URL（*）
             method: 'get',                      //请求方式（*）
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
                 field: 'uid',
                 title: '学号',
                 align: 'center'
             }, {
                 field: 'uname',
                 title: '姓名',
                 align: 'center'
             },{
                 field: 'sex',
                 title: '性别',
                 align: 'center'
             },{
                 field: 'ugrade',
                 title: '年级',
                 align: 'center'
             },{
                 field: 'upartment',
                 title: '学院班级',
                 align: 'center'
             },{
            	 field: 'utelephone',
                 title: '联系电话',
                 align: 'center'
             }],

         });
}
