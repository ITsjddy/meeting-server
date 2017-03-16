var sysPrivilegeApp = angular.module('sysPrivilegeApp', ['tm.pagination']);
pageService(sysPrivilegeApp);//初始化分页service
checkboxDeleteService(sysPrivilegeApp);//初始化多选删除service
sysPrivilegeApp.controller("sysPrivilegeController", function($scope,$http,pageService,checkboxDeleteService) {
	
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/sysPrivilege/pageQuery");
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/sysPrivilege/pageQuery");
	};
	
	$scope.sysPrivilegeDelete = function(id){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysPrivilege/deleteById";
		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysPrivilege/index";
		var param = {id:id};
		delnoty(delurl, reurl,param,"删除",$http);
	};
	
	/** tree **/
	$scope.delParent = function(){
        $scope.searchParam.sysPrivilege_parentName = '';
        $scope.searchParam.sysPrivilege_parentId = '';
        $('#parentidId').val('');
        $('#parentnameId').val('');
	};
	window.addEventListener('message',function(e){
        var data = e.data;
        $scope.getTreeClickItem(data);
        $("#mySysPrivilege").modal('hide');
    },false);
	$scope.getTreeClickItem = function(data){
		var parentId = data.parentId;
		var parentName = data.parentName;
		
		$scope.searchParam.sysPrivilege_parentId = parentId;
        $scope.searchParam.sysPrivilege_parentName = parentName;
        $('#parentidId').val(parentId);
        $('#parentnameId').val(parentName);
	};
	
	/** 多选删除 **/
	$scope.idSelect = function(id,select){
        return checkboxDeleteService.idSelect($scope,id,select);
    };
    $scope.checkAll = function(c) {
        return checkboxDeleteService.checkAll($scope,c);
    };
    $scope.deletes = function () {
    	var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysPrivilege/deleteList";
   		var reurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysPrivilege/index";
        return checkboxDeleteService.deletes($scope,$http,delurl,reurl);
    };
    
});
