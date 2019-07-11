	 $(document).ready(function() {
         //调用函数，初始化表格 
         var urlStr = "http://localhost:8080/allTech";
         TableInit(urlStr);
         $('body').css("background-color","transparent");
         $('.search').css("opacity","0.7");
         $('.search').children().css("opacity","0.7");
	 });
	 
	 function tableRefesh() {
		 window.location.reload();
	} 
	 
	 function deleteTech() {
		 var a = $('#tech').bootstrapTable('getSelections');
		 if(a.length<=0){
			 alert("请至少选择一个老师");
		}else{
			var b=JSON.stringify(a);
			  var url="http://localhost:8080/deleteTech";
	  		    $.ajax({
	  		        dataType: "json",
	  		        async: "false",
	  		        traditional:true,//这使json格式的字符不会被转码
	  		        data: {"datalist":b},
	  		        type: "post", 
	  		        url: url,
	  		        success : function (data) {
	  		        	if (data.msg == "true") {
	  		        		alert("删除老师成功！");
	  		        		window.location.reload();
						}else if (data.msg == "false") {
							alert("非法操作,删除失败");
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
	 
	 
	 function addTech() {
		 $('#tech').bootstrapTable('insertRow', {
	            index: 0,
	            row: {
	                uid: '编号',
	                uname: '姓名',
	                sex: '男',
	                upassword: '密码',
	                upartment: '所属学院',
	                utelephone: '手机号'
	            }
	        });
	}
	 
	 function updateTech() { //提交
		 var a = $('#tech').bootstrapTable('getSelections');
		 if(a.length<=0){
			 alert("请至少选择一个老师");
		}else{
			var b=JSON.stringify(a);
			  var url="http://localhost:8080/updateTech";
	  		    $.ajax({
	  		        dataType: "json",
	  		        async: "false",
	  		        traditional:true,//这使json格式的字符不会被转码
	  		        data: {"datalist":b},
	  		        type: "post", 
	  		        url: url,
	  		        success : function (data) {
	  		        	if (data.msg == "true") {
	  		        		alert("更改成功！");
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
	                     field: 'uid',
	                     title: '教师编号',
	                     align: 'center'
	                 }, {
	                     field: 'uname',
	                     title: '姓名',
	                     align: 'center'
	                 }, {
	                     field: 'sex',
	                     title: '性别',
	                     align: 'center'
	                 }, {
	                     field: 'upassword',
	                     title: '密码',
	                     align: 'center'
	                 },{
	                     field: 'upartment',
	                     title: '部门',
	                     align: 'center'
	                 }, {
	                     field: 'utelephone',
	                     title: '密保手机',
	                     align: 'center'
	                 }],
	                 onClickCell: function(field, value, row, $element) {
	                     $element.attr('contenteditable', true); //可编辑
	                     $element.blur(function() {
	                         let index = $element.parent().data('index');
	                         let tdValue = $element.html();
	                         saveData(index, field, tdValue);
	                     })
	                 }

	             });
		}
	 
	 
	 function saveData(index, field, value) {
		 $('#tech').bootstrapTable('updateCell', {
	            index: index,       //行索引
	            field: field,       //列名
	            value: value        //cell值
	        })   
	    }

