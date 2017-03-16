var sysRoleApp = angular.module('sysRoleApp', ['tm.pagination']);
pageService(sysRoleApp);//初始化分页service
checkboxDeleteService(sysRoleApp);//初始化多选删除service
sysRoleApp.controller("sysRoleController", function($scope,$http,pageService,checkboxDeleteService) {
	
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/pageQuery");
	};
	
	$scope.sysRoleDelete = function(id){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/index";
		var param = {id:id};
		delnoty(delurl, reurl,param,"删除",$http);
	};
	
	/** 多选删除 **/
	$scope.idSelect = function(id,select){
        return checkboxDeleteService.idSelect($scope,id,select);
    };
    $scope.checkAll = function(c) {
        return checkboxDeleteService.checkAll($scope,c);
    };
    $scope.deletes = function () {
    	var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/deleteList";
   		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysRole/index";
        return checkboxDeleteService.deletes($scope,$http,delurl,reurl);
    };
	
});
