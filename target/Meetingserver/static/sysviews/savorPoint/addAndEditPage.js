var addSavorPoint = angular.module('addSavorPoint', []);

addSavorPoint.controller("spAddCtlr", function($scope, $http) {

	var urlStr = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/savorPoint/";

	initSavorPoint();

	/**
	 * 添加
	 */
	$scope.addSavorPoint = function() {
		$http({
			method : 'POST',
			url : urlStr + "save",
			params : $scope.savorPoint,
		}).success(function(result) {
			if (result.success) {
				window.location.href = urlStr + "openPage";
			} else {
				alert('页面加载失败！');
			}
		}).error(function(error) {
			if ('403' == error.status) {
				alert('无权限');
			}
		});
	};

	/**
	 * 返回
	 */
	$scope.backPage = function() {
		window.location.href = urlStr + "openPage";
	}

	/**
	 * 页面数据初始化
	 */
	function initSavorPoint() {

		/**
		 * 兴趣点类型
		 */
		$scope.spTypes = [{name : 'QAZXSW',
						   age : '123'},
						  {name : 'PLMNKO',
							age : '4321'} ];

		var spValue = $("#spValue").val();
		$http.get(urlStr + "queryById?id=" + spValue).success(
				function(response) {
					$scope.savorPoint = response;
				});

	}
});
