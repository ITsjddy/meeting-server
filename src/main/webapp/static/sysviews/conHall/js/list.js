//var app = angular.module('testApp', []);

var conHallApp = angular.module('conHallApp', ['tm.pagination']);
pageService(conHallApp);//初始化分页service
checkboxDeleteService(conHallApp);//初始化多选删除service
conHallApp.controller("conHallController", function($scope,$http,pageService,checkboxDeleteService) {
	/** 多选删除 **/
	$scope.idSelect = function(id,select){
        return checkboxDeleteService.idSelect($scope,id,select);
    };
    $scope.checkAll = function(c) {
        return checkboxDeleteService.checkAll($scope,c);
    };
    $scope.deletes = function () {
    	var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/conHall/multiDelete";
   		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/conHall/toList";
        return checkboxDeleteService.deletes($scope,$http,delurl,reurl);
    };
	
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/conHall/list");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/conHall/list");
	};
	
	//=================获取会场类型数据===============
	$scope.getConHallTypeList = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/conHall/getConHallTypeList";
		$http({
			method : 'POST',
			url : url,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.conHallTypeList = resultMap.data;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getConHallTypeList();
	
	$scope.deleteItem = function(uniqueCode){
		var param = {uniqueCode:uniqueCode};
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/conHall/deleteByUniqueCode";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/conHall/toList";
		delnoty(delurl, reurl,param,"删除",$http);
	}
});
