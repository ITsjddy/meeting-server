var addJpushEntiy = angular.module('addJpushEntiy', []);

addJpushEntiy.controller("jpAddCtlr", function($scope,  $http) {
	
	var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/jpushEntiy/";
		
		$scope.languages = [{languageId:'中文'},{languageId:'日语'},{languageId:'英文'}];
	
			//	initLangua();
	
	/**
	 * 消息推送
	 */
	$scope.addJpushEntiy = function(){
		var jpushEntiy = $scope.jpushEntiy;
		var parsms = {languages : $scope.languages, 
					  broadCast: (jpushEntiy.broadCast == 'true' ? true : false),
					  insiderIds: jpushEntiy.insiderIds,
					  outsiderCheck: (jpushEntiy.outsiderCheck),
					  insiderCheck: jpushEntiy.insiderCheck,
					  outsiderIds: jpushEntiy.outsiderIds}
		$http({
			method : 'POST',
			url : url + "saveList",
			params : parsms,
		}).success(function(result) {
			$scope.result = result.success;
		}).error(function(error) {
			if ('403' == error.status) {
				alert('无权限');
			}
		});
	}
	
	/**
	 * 获取语言种类
	 */
	function initLangua(){
		$http({
			method : 'GET',
			url : url + "initLangua",
		}).success(function(result) {
			$scope.jpushEntiy = result;
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	}
	
	/**
	 * 外部人员添加
	 */
	$scope.outsiderButton = function(){
		$('#myModal').modal('show');
	};
	
	/**
	 * 内部人员添加
	 */
	$scope.insiderButton = function(){
		$('#myModal').modal('show');
	};
	
	/**
	 * 删除推送人员
	 */
	$scope.deleteNames = function(id){
		$(id).val('');
	};

});
