var sysGroupApp = angular.module('sysGroupApp', []);
sysGroupApp.controller("sysGroupController", function($scope,$http) {
	$scope.sysGroupQueryById = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/queryById";
		var id = $("#id").val();
		var param = {id:id};
		$http({
			method : 'POST',
			url : url,
			params :param,
		}).success(function(resultMap) {
			$scope.sysGroup = resultMap;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	$scope.sysRoleQueryAll = function(){
		var url = CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/sysRoleQueryAll";
		$http({
			method : 'POST',
			url : url,
			/*params :param,*/
		}).success(function(resultMap) {
			$scope.lsysRole = resultMap;
		}).error(function(error) {
			alert(error);
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	 $scope.yhctlModel = [];
	$scope.yhctlModelOptions = [ 
        {id: 1, label: '限定用户'}, 
        {id: 2, label: "限定商家"}, 
        {id: 3, label: "限定使用次数"},
        {id: 4, label: "限定药品"}, 
        {id: 5, label: "与其它优惠共享"}];
	
	$scope.sysGroupSave = function(){
		var delurl = CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/update";
		var reurl=CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/index";
		var param = $scope.sysGroup;
		delnoty(delurl, reurl,param,"修改",$http);
	}
	$scope.index = function(){
		window.location.href = CONTEXT_PATH+DISPATCHER_PATH+"/sysGroup/index";
	}
	$scope.sysGroupQueryById();
	$scope.sysRoleQueryAll();
});
