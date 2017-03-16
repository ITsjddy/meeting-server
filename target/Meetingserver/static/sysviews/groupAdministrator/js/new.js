var guestApp = angular.module('groupAdministratorApp', []);
guestApp.controller("groupAdministratorController", function($scope,$http) {
	$scope.guestSave = function(){
		var groupId = $('#id').val();
		$scope.guest.groupId = groupId;
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/groupAdministrator/save";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/department/indexdetial?groupid="+$('#id').val();
		var param = $scope.guest;
		delnoty(delurl, reurl,param,"保存",$http); 
	}
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/department/indexdetial?groupid="+$('#id').val();
	}
});
