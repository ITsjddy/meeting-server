//var app = angular.module('testApp', []);

var conHallApp = angular.module('conHallApp', ['tm.pagination']);
pageService(conHallApp);//初始化分页service
conHallApp.controller("conHallController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/conHall/list");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+"/"+DISPATCHER_PATH+"/conHall/list");
	};
	$scope.deleteItem = function(uniqueCode){
		var param = {uniqueCode:uniqueCode};
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/conHall/deleteByUniqueCode";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/conHall/toList";
		delnoty(delurl, reurl,param,"删除",$http);
	}
});
