var issueApp = angular.module('issueApp', ['tm.pagination']);
pageService(issueApp);//初始化分页service
checkboxDeleteService(issueApp);//初始化多选删除service
issueApp.controller("issueController", function($scope,$http,pageService,checkboxDeleteService) {
	/** 多选删除 **/
	$scope.idSelect = function(id,select){
        return checkboxDeleteService.idSelect($scope,id,select);
    };
    $scope.checkAll = function(c) {
        return checkboxDeleteService.checkAll($scope,c);
    };
    $scope.deletes = function () {
    	var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/issue/multiDelete";
   		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/issue/toList";
        return checkboxDeleteService.deletes($scope,$http,delurl,reurl);
    };
	
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/issue/list");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/issue/list");
	};
	$scope.deleteItem = function(uniqueCode){
		var param = {uniqueCode:uniqueCode};
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/issue/deleteByUniqueCode";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/issue/toList";
		delnoty(delurl, reurl,param,"删除",$http);
	}
	//=================获取会场数据===============
	$scope.getConHallList = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/getConHallList";
		$http({
			method : 'POST',
			url : url,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.conHallList = resultMap.data;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getConHallList();
	//=================获取所有论坛数据===============
	$scope.getAllScheduleData = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/issue/getAllScheduleData";
		$http({
			method : 'POST',
			url : url,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.scheduleList = resultMap.data;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getAllScheduleData();
});
