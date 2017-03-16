
var folderApp = angular.module('folderApp', []);
folderApp.controller("folderController", function($scope,$http) {
	$scope.folderDetailById = function(){
		var url = CONTEXT_PATH+"/"+DISPATCHER_PATH+"/folder/folderDetailById";
		var id = $("#folderid").val();
		var param = {id:id};
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			$scope.folder = resultMap;
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.folderDetailById();
});

