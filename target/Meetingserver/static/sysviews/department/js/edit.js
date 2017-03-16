var guestApp = angular.module('guestApp', []);
guestApp.controller("guestController", function($scope,$http) {
	$scope.guestQueryById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/department/queryById";
		var id = $("#id").val();
		var param = {id:id};
		$http({
			method : 'POST',
			url : url,
			params :param,
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded;charset=utf-8'
			}
		}).success(function(resultMap) {
			$scope.guest = resultMap;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.guestSave = function(){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/department/update";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/department/indexdetial?groupid="+$('#id').val();
		var param = $scope.guest;
		delnoty(delurl, reurl,param,"保存",$http);
		parent.renderTree();
	}
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/department/indexdetial?groupid="+$('#id').val();
	}
	$scope.guestQueryById();
});
