var guestApp = angular.module('guestApp', []);
guestApp.controller("guestController", function($scope,$http) {
	$scope.guestSave = function(){
		$scope.guest.parentid=$('#groupid').val();
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/department/save";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/department/indexinclude?groupid="+$('#id').val();
		var param = $scope.guest;
		delnoty(delurl, reurl,param,"保存",$http);
	}
	$scope.guestDelete = function(id){
		var delurl=CONTEXT_PATH+DISPATCHER_PATH+"/department/deleteById";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/indexinclude?groupid="+$('#id').val();
		var param = {'id':id};
		delnoty(delurl, reurl,param,"删除",$http);	
	}
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/department/indexinclude?groupid="+$('#id').val();
	}
});
