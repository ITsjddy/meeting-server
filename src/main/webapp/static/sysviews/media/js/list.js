var mediaApp = angular.module('mediaApp', ['tm.pagination']);
pageService(mediaApp);//初始化分页service
checkboxDeleteService(mediaApp);//初始化多选删除service
mediaApp.controller("mediaController", function($scope,$http,pageService,checkboxDeleteService) {
	
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/media/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/media/pageQuery");
	};
	
	/** 多选删除 **/
	$scope.idSelect = function(id,select){
        return checkboxDeleteService.idSelect($scope,id,select);
    };
    $scope.checkAll = function(c) {
        return checkboxDeleteService.checkAll($scope,c);
    };
    $scope.deletes = function () {
    	var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/media/deleteList";
   		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/media/index";
        return checkboxDeleteService.deletes($scope,$http,delurl,reurl);
    };
	
	$scope.mediaDelete = function(id){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/media/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/media/index";
		var param = {id:id};
		delnoty(delurl, reurl,param,"删除",$http);
	}
	
	/** 修改密码 **/
	$scope.editPassword = function(id, userName){
//		alert(id+" "+userName);
		$scope.passUserName = userName;
		$scope.passId = id;
	};
	$scope.savePassword = function(){
		var saveurl = CONTEXT_PATH+DISPATCHER_PATH+"/member/savePassword";
		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/media/index";
		var param = {id:$scope.passId, password:$scope.passPassword};
		delnoty(saveurl, reurl, param, "密码修改成功", $http);
	};
});
