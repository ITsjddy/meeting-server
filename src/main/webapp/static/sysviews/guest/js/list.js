var guestApp = angular.module('guestApp', ['tm.pagination']);
pageService(guestApp);//初始化分页service
checkboxDeleteService(guestApp);//初始化多选删除service
guestApp.controller("guestController", function($scope,$http,pageService,checkboxDeleteService) {
	
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/guest/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/guest/pageQuery");
	};
	
	/** 修改密码 **/
	$scope.editPassword = function(id, userName){
		$scope.passUserName = userName;
		$scope.passId = id;
	};
	$scope.savePassword = function(){
		var saveurl = CONTEXT_PATH+DISPATCHER_PATH+"/member/savePassword";
		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/guest/index";
		var param = {id:$scope.passId, password:$scope.passPassword};
		delnoty(saveurl, reurl, param, "密码修改成功", $http);
	};
	
	/** 多选删除 **/
	$scope.idSelect = function(id,select){
        return checkboxDeleteService.idSelect($scope,id,select);
    };
    $scope.checkAll = function(c) {
        return checkboxDeleteService.checkAll($scope,c);
    };
    $scope.deletes = function () {
    	var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/guest/deleteList";
   		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/guest/index";
        return checkboxDeleteService.deletes($scope,$http,delurl,reurl);
    };
	
	$scope.guestDelete = function(id){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/guest/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/guest/index";
		var param = {id:id};
		delnoty(delurl, reurl,param,"删除",$http);
	}
});
