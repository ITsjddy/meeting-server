var groupAdministratorApp = angular.module('groupAdministratorApp', ['tm.pagination']);
pageService(groupAdministratorApp);//初始化分页service
groupAdministratorApp.controller("groupAdministratorController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/groupAdministrator/pageQuery?groupid="+$('#groupid').val());
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/groupAdministrator/pageQuery?groupid="+$('#groupid').val());
	};
	$scope.groupAdministratorDelete = function(id){
		var delurl=CONTEXT_PATH+DISPATCHER_PATH+"/groupAdministrator/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/department/indexdetial?groupid="+$('#groupid').val();	
		var param = {'id':id};
		delnoty(delurl, reurl,param,"删除",$http);	
	}
});
