var sysGroupApp = angular.module('sysGroupApp', ['tm.pagination']);
pageService(sysGroupApp);//初始化分页service
checkboxDeleteService(sysGroupApp);//初始化多选删除service
sysGroupApp.controller("sysGroupController", function($scope,$http,pageService,checkboxDeleteService) {
	
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/pageQuery");
	};
	
	$scope.sysGroupDelete = function(id){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/index";
		var param = {id:id};
		delnoty(delurl, reurl,param,"删除",$http);
	}
	
	/** 多选删除 **/
	$scope.idSelect = function(id,select){
        return checkboxDeleteService.idSelect($scope,id,select);
    };
    $scope.checkAll = function(c) {
        return checkboxDeleteService.checkAll($scope,c);
    };
    $scope.deletes = function () {
    	var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/deleteList";
   		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/index";
        return checkboxDeleteService.deletes($scope,$http,delurl,reurl);
    };
    
});
