var guestApp = angular.module('servicePersonnelApp', ['tm.pagination']);
pageService(guestApp);
guestApp.controller("servicePersonnelController", function($scope,$http,pageService) {
	pageService.queryForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/pageQueryForGroup?groupid="+$('#groupid').val());
	$scope.searchByParam = function(){
		pageService.reGetDataForList($scope,$http,CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/pageQueryForGroup?groupid="+$('#groupid').val());
	};
	$scope.guestDelete = function(id){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/deleteById";
		$http({
			method : 'POST',
			url : url,
			params : {id:id},
		}).success(function(resultMap) {
			if(null != resultMap && resultMap.success == true){
				window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/servicePersonnel/index";
			}else{
			}
		}).error(function(error) {
			if('403' == error.status){
			}
		});
	}
});
