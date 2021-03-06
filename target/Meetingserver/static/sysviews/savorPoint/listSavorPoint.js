var savorPointList = angular.module('savorPointList', [ 'tm.pagination' ]);

pageService(savorPointList);

savorPointList.controller("spCtlr", function($scope, $http, pageService) {
	
	var urlStr = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/savorPoint/";
	/**
	 * 初始化页面数据
	 */
	pageService.queryForList($scope, $http, urlStr + "queryAll");
	
	/**
	 * 查询
	 */
	$scope.searchByParam = function() {
		pageService.reGetDataForList($scope, $http, urlStr + "queryAll" );
	};
	
	/**
	 * 删除
	 */
	$scope.deleteSavorPoint = function(savorPoint){
		$http({
			method : 'POST',
			url : urlStr + "deleteById",
			params : savorPoint,
		}).success(function(result) {
			if (result.success) {
				window.location.href = urlStr + "openPage";
			} else {
				alert('删除失败！');
			}
		}).error(function(error) {
			if('403' == error.status){
				alert('无权限');
			}
		});
	};
	
});
