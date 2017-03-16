var groupTokensApp = angular.module('groupTokensApp', []);
groupTokensApp.controller("groupTokensController", function($scope,$http) {
	$scope.getEntityById = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/groupTokens/getEntityById";
		var id = $("#testid").val();
		var param = {id:id};
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			$scope.testDemo = resultMap;
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.getEntityById();
	$scope.editSave = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/groupTokens/editSave";
		var param = $scope.testDemo;
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
		window.location.href = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/groupTokens/toList";
	}
	$scope.getSelectedItem = function(){
		var arrIds = new Array();
		var arrIdsInput = window.frames["selectMember"].document.getElementsByName("ids");
		if(arrIdsInput){
			for(var i=0;i<arrIdsInput.length;i++){
				arrIds.push(arrIdsInput[i].value);
			}
		}
		console.log(arrIds);
	};
});
