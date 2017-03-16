var systemSettingList = angular.module('systemSettingList', ['tm.pagination']);

pageService(systemSettingList);

checkboxDeleteService(systemSettingList);

systemSettingList.controller("systemSettingListCtlr", function($scope, $http, pageService, checkboxDeleteService) {
  var commUrl =  CONTEXT_PATH + DISPATCHER_PATH ;
  var urlStr = commUrl + "/systemSetting/";
  $scope.statusList = [{id:'enabled',text:'启用'},{id:'disabled',text:'禁用'}];
  /**
	 * 初始化页面数据
	 */
  pageService.queryForList($scope, $http, urlStr + "pageQuery");

  /**
	 * 查询
	 */
  $scope.searchByParam = function() {
    pageService.reGetDataForList($scope, $http, urlStr + "pageQuery");
  };

  /**
	 * 删除
	 */
  $scope.deleteDtye = function(dDType) {
    $http({
      method: 'POST',
      url: urlStr + "deleteById",
      params: dDType,
    }).success(function(result) {
      if (result.success) {
        window.location.href = urlStr + "index";
      } else {
        alert('删除失败！');
      }
    }).error(function(error) {
      if ('403' == error.status) {
        alert('无权限');
      }
    });
  };
  /**
   * 删除
   */
  $scope.changeStatus = function(dDType) {
	  $http({
		  method: 'POST',
		  url: urlStr + "changeStatus",
		  params: dDType,
	  }).success(function(result) {
		  if (result.success) {
			  window.location.href = urlStr + "index";
		  } else {
			  alert('修改失败！');
		  }
	  }).error(function(error) {
		  if ('403' == error.status) {
			  alert('无权限');
		  }
	  });
  };
  
  /**
	 * 删除
	 */
	$scope.toDataPage = function(dTypeName) {
		window.location.href = commUrl + "/systemSetting/index";
	};

  /** 多选删除 **/
  $scope.idSelect = function(id, select) {
    return checkboxDeleteService.idSelect($scope, id, select);
  };

  $scope.checkAll = function(c) {
    return checkboxDeleteService.checkAll($scope, c);
  };

  $scope.deletes = function() {
    var delurl = commUrl + "/systemSetting/deleteList";
    var reurl = commUrl + "/systemSetting/index";
    return checkboxDeleteService.deletes($scope, $http, delurl, reurl);
  };

});