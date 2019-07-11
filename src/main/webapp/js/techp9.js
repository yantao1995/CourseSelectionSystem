	 $(document).ready(function() {
         //调用函数，初始化表格 
         var urlStr = "http://localhost:8080/getTechClassTable";
         TableInit(urlStr);
         $('body').css("background-color","transparent");
	 });
	 
	 function tableRefesh() {
		 window.location.reload();
	}

	 function TableInit(urlStr) {	                  
         console.log(urlStr);
         $('#lesson').bootstrapTable({
             url: urlStr,         //请求后台的URL（*）
             method: 'get',                      //请求方式（*）
             cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
             pagination: true,                   //是否显示分页（*）
             sortable: false,                     //是否启用排序
             pageNumber: 1,                       //初始化加载第一页，默认第一页
             showLoading: 'none',
             pageSize: 5,                       //每页的记录行数（*）
             strictSearch: false,
             showColumns: false,                  //是否显示所有的列
             showRefresh: false,                  //是否显示刷新按钮
             minimumCountColumns: 5,             //最少允许的列数
             clickToSelect: true,                //是否启用点击选中行
             uniqueId: 'tid',                     //每一行的唯一标识，一般为主键列
             showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
             cardView: false,                    //是否显示详细视图
             detailView: false,                   //是否显示父子表
             sidePagination: 'client',
                columns: [[
                    {
                        "title": "本学期课程表",
                        "halign":"center",
                        "align":"center",
                        "colspan": 6
                    }
                ],[{
                	field: 'timeweek',
                    title: '节次\\周',
                    align: 'center',
                    width: 160
                },{
                    field: 'zhouyi',
                    title: '周一',
                    align: 'center',
                    width: 160
                },{
                    field: 'zhouer',
                    title: '周二',
                    align: 'center',
                    width: 160
                },{
                    field: 'zhousan',
                    title: '周三',
                    align: 'center',
                    width: 160
                }, {
                    field: 'zhousi',
                    title: '周四',
                    align: 'center',
                    width: 160
                },{
                    field: 'zhouwu',
                    title: '周五',
                    align: 'center',
                    width: 160
                }]
                ]
            });
	}

