var sysUserApp = angular.module('sysUserApp', ['tm.pagination']);
pageService(sysUserApp);//初始化分页service
checkboxDeleteService(sysUserApp);//初始化多选删除service
sysUserApp.controller("sysUserController", function($scope,$http,pageService,checkboxDeleteService) {
	
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/sysUser/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/sysUser/pageQuery");
	};
	
	$scope.sysUserDelete = function(id){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysUser/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/sysUser/index";
		var param = {id:id};
		delnoty(delurl, reurl,param,"删除",$http);
	};
	
	$scope.editPassword = function(id, userName){
		$scope.puserName = userName;
		$scope.pid = id;
	};
	
	$scope.savePassword = function(){
		var saveurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysUser/savePassword";
		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysUser/index";
		var param = {id:$scope.pid, password:$scope.ppassword};
		delnoty(saveurl, reurl, param, "密码修改成功", $http);
	};
	
	$scope.queryAllSysRole = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/sysUser/queryAllSysRole";
		$http({
			method : 'POST',
			url : url,
		}).success(function(resultMap) {
			$scope.lSysRole = resultMap;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	
	/** 多选删除 **/
	$scope.idSelect = function(id,select){
        return checkboxDeleteService.idSelect($scope,id,select);
    };
    $scope.checkAll = function(c) {
        return checkboxDeleteService.checkAll($scope,c);
    };
    $scope.deletes = function () {
    	var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysUser/deleteList";
   		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysUser/index";
        return checkboxDeleteService.deletes($scope,$http,delurl,reurl);
    };
    
    $scope.queryAllSysRole();
	
});
