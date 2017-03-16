var cloudGroupList = angular.module('cloudGroupList', ['tm.pagination']);

pageService(cloudGroupList);

checkboxDeleteService(cloudGroupList);

cloudGroupList.controller("cloudGroupCller", function($scope, $http, pageService, checkboxDeleteService) {
  var mainUrl = CONTEXT_PATH + DISPATCHER_PATH + "/cloudGroup/"

  /**
	 * 初始化页面数据
	 */
  pageService.queryForList($scope, $http, mainUrl + "pageQuery");

  /**
	 * 查询数据
	 */
  $scope.searchByParam = function() {
    pageService.reGetDataForList($scope, $http, mainUrl + "pageQuery");
  };

  /**
	 * 删除
	 */
  $scope.deleteCloudGroup = function(cloudItem) {
    $http({
      method: 'POST',
      url: mainUrl + "deleteById",
      params: cloudItem,
    }).success(function(result) {
      if (result.success) {
        window.location.href = mainUrl + "index";
      } else {
        alert('删除失败！');
      }
    }).error(function(error) {
      if ('403' == error.status) {
        alert('无权限');
      }
    });
  }

  /** 多选删除 **/
  $scope.idSelect = function(id, select) {
    return checkboxDeleteService.idSelect($scope, id, select);
  };

  $scope.checkAll = function(c) {
    return checkboxDeleteService.checkAll($scope, c);
  };

  $scope.deletes = function() {
    var delurl = CONTEXT_PATH + DISPATCHER_PATH + "/cloudGroup/deleteList";
    var reurl = CONTEXT_PATH + DISPATCHER_PATH + "/cloudGroup/index";
    return checkboxDeleteService.deletes($scope, $http, delurl, reurl);
  };

});