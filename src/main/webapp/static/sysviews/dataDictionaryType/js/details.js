var detailsdDType = angular.module('detailsdDType', []);
detailsdDType.controller("detailsqwCller", function($scope, $http) {
  var mainUrl = CONTEXT_PATH + DISPATCHER_PATH + "/dataDictionaryType/"

  initDtype();

  /**
	 *  返回首页
	 */
  $scope.pageBackButton = function() {
    window.location.href = mainUrl + "index"
  }

  /**
	 * 页面数据初始化
	 */
  function initDtype() {
    var spValue = $("#spValue").val();
    $http.get(mainUrl + "queryById?id=" + spValue).success(function(response) {
      $scope.dateType = response;
      console.info($scope.dateType);
    });

  }

});