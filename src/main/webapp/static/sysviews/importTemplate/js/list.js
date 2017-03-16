var guestApp = angular.module('guestApp', ['tm.pagination']);
pageService(guestApp);//初始化分页service
guestApp.controller("guestController", function($scope,$http,pageService) {
	
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/importTemplate/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/importTemplate/pageQuery");
	};
	$scope.guestDelete = function(id){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/importTemplate/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/importTemplate/index";
		var param = {id:id};
		delnoty(delurl, reurl,param,"删除",$http);
	}
	$scope.uniqueIdenchange = function(guest){
		console.log(guest);
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/importTemplate/update";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/importTemplate/index";
		var param = guest;
		delnoty(delurl, reurl,param,"修改",$http);
	}
});