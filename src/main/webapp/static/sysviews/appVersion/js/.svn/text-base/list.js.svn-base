var appVersionApp = angular.module('appVersionApp', ['tm.pagination']);

pageService(appVersionApp); 

checkboxDeleteService(appVersionApp);

appVersionApp.controller("appVersionController", function($scope, $http, pageService, checkboxDeleteService) {
	
  pageService.queryForList($scope, $http, CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/pageQuery");

  $scope.searchByParam = function() {
    pageService.reGetDataForList($scope, $http, CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/pageQuery");
  };

  $scope.deleteById = function(appVersion) {
    $http({
      method: 'POST',
      url: CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/deleteById",
      params: appVersion,
    }).success(function(resultMap) {
      if (resultMap.success) {
        window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/index";
      } else {
        alert('保存失败');
      }
    }).error(function(error) {
      if ('403' == error.status) {
        alert('无权限');
      }
    });
  };
  
  /** 多选删除 **/
	$scope.idSelect = function(id,select){
      return checkboxDeleteService.idSelect($scope, id, select);
  };
  
  $scope.checkAll = function(c) {
      return checkboxDeleteService.checkAll($scope,c);
  };
  
  $scope.deletes = function () {
  	var delurl = CONTEXT_PATH + DISPATCHER_PATH + "/appVersion/deleteList";
 		var reurl = CONTEXT_PATH + DISPATCHER_PATH + "/appVersion/index";
      return checkboxDeleteService.deletes($scope,$http,delurl,reurl);
  };
});