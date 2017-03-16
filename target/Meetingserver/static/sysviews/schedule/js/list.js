var scheduleApp = angular.module('scheduleApp', ['tm.pagination']);
pageService(scheduleApp);//初始化分页service
scheduleApp.controller("scheduleController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/list");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/schedule/list");
	};
	$scope.deleteItem = function(uniqueCode){
		var param = {uniqueCode:uniqueCode};
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/schedule/deleteByUniqueCode";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/schedule/toList";
		delnoty(delurl, reurl,param,"删除",$http);
	}
});
