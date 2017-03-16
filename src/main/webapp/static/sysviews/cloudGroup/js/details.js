var detailscloudGroup = angular.module('detailscloudGroup', []);
detailscloudGroup.controller("detailsCller", function($scope, $http) {
  var mainUrl = CONTEXT_PATH + DISPATCHER_PATH + "/cloudGroup/"

  initCloudGroup();

  /**
	 *  返回首页
	 */
  $scope.pageBackButton = function() {
    window.location.href = mainUrl + "index"
  }

  /**
	 * 页面数据初始化
	 */
  function initCloudGroup() {
    var spValue = $("#spValue").val();
    $http.get(mainUrl + "queryById?id=" + spValue).success(function(response) {
      $scope.cloudGroup = response;
    });

  }

});