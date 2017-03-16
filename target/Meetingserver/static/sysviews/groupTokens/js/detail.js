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
});
