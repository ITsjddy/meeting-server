//demo services
(function(){
	var demoList = function($iq_http){
		var list = [];					//列表数据
		var param = {
			pg:1,						//页码
			ps:10						//页面数量
		}//add
		/**
		* 操作方法
		*/
		var removeById = function(_id){
			for(var i =0,j=0;i<list.length;i++,j++){
				if(list[i].id == _id){
					j--;
				}else{
					list[j] = list[i]
				}
			}
			list.length--;
		}
		var getById = function(_id){	
			for(var i= 0;i<list.length;i++){
				if(list[i].id==_id){
					return angular.copy(list[i]);
				}
			}
			return null;
		}
		var getIndexById = function(_id){	
			for(var i= 0;i<list.length;i++){
				if(list[i].id==_id){
					return i;
				}
			}
			return -1;
		}
		var getList = function(callback,_param){//从远程获取列表数据
			var func = {
				success:function(re){
					list = re.data;
					if(callback)
					{
						callback(angular.copy(list));// 直接返回list 还是返回list.copy
													//建议返回copy 除非需要和service关联
					}
				},
				error:function(){//error func
				}
			}
			if(_param){
				param = angular.extend(param,_param);//赋值参数
			}
			var params = {
				method:"GET",
				url:"applylist2.json",
				data:param							//带入参数查询
			}
			
			$iq_http.http(params,func);
		}
		var edit = function(id,_param,success){//将id为id的一行数据修改为param
			var params = {
				method:"POST",
				url:"../test/success.json"
			}
			$iq_http.http(params,{
				success:function(re){
					var index = getIndexById(id)
					list[index] = _param;
					if(success)
					{
						success(list);
					}
				}
			});
		}
		var add = function(param,success){
			var func = {
				success:function(re){
					list = [param].concat(list)
					if(success)
					{
						success(list);
					}
				}
			}
			var params = {
				method:"POST",
				url:"../test/success.json"
			}
			$iq_http.http(params,func);
		}
		var del = function(id,success){
			var func = {
				success:function(re){
					removeById(id)
					if(success)
					{
						success(list);
					}
				},
				error:function(){
				}
			}
			var params = {
				method:"POST",
				url:"../test/success.json"
			}
			$iq_http.http(params,func);
		}
		return {
			all:function(){
				return list;
			},
			getList:getList,
			add:add,
			edit:edit,
			del:del,
			getById:getById
		}
	}
	
	var app1 = angular.module('demo2');
	app1.service("$demo2List",demoList);
})();