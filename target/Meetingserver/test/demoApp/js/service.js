//demo services
(function(){
	var basePath = "apps/demoApp/";
	var person = function($resource){
		return $resource('/iquicker_web/person/:id');
	}
	
	var demoList = function($iq_http){
		var list = [];					//列表数据
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
					return list[i];
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
		var getList = function(callback){//从远程获取列表数据
			var func = {
				success:function(re){
					list = re.data;
					if(callback)
					{
						callback(list);// 直接返回list 还是返回list.copy
					}
				}
			}
			var params = {
				method:"GET",
				url:basePath+"applylist2.json"
			}
			
			$iq_http.http(params,func);
		}
		var edit = function(id,param,success){//将id为id的一行数据修改为param
			//TODO
/*			var params = {
				method:"POST",
				url:"../test/success.json"
			}
			$iq_http.http(params,{
				success:function(re){
					var index = getIndexById(id)
					list[index] = param;
					if(success)
					{
						success(list);
					}
				}
			});
			*/
		}
		var add = function(param,success){
			//TODO

			/*var func = {
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
			$iq_http.http(params,func);*/
		}
		var del = function(id,success){
			//TODO
			/*var func = {
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
				url:"test/success.json"
			}
			$iq_http.http(params,func);
			*/
		}
		return {
			all:function(){
				return list;
			},
			getList:getList,
			add:add,
			edit:edit,
			del:del,
		}
	}
	
	var app1 = angular.module('demo');
	app1.service("$iqa_demo_List",demoList);
	app1.service("$iqa_demo_Person",['$resource',person]);
})();