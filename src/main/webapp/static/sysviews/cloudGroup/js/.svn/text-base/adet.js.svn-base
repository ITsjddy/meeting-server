var adetcloudGroup = angular.module('adetcloudGroup', ['tm.pagination', 'ngMessages']);

pageService(adetcloudGroup);

adetcloudGroup.controller("adetcgCller", function($scope, $http, pageService) {
  var mainUrl = CONTEXT_PATH + DISPATCHER_PATH + "/cloudGroup/";
  usernameSelectd = [];
  serviceNames = [];
  insiderIds = [];
  $scope.selected = [];
  $scope.searchValue = '';
  $scope.checkAll = false;
  $scope.member = {
    id: 'guest'
  };
  $scope.cloudGroup = {};
  $scope.personForTpye = {};
  $scope.personType = [{
    id: 'guest',
    value: '嘉宾'
  },
  {
    id: 'exhibitors',
    value: '展商'
  },
  {
    id: 'media',
    value: '媒体'
  },
  {
    id: 'audience',
    value: '观众'
  },
  ];

  initCloudGroup();
  pageService.queryForList($scope, $http, CONTEXT_PATH + "/" +
		  			DISPATCHER_PATH + "/appUserForWindow/memberList?type=" + 'asdf');
  /**
	 * 全选按钮窗口
	 */
  $scope.checkAllItem = function($event) {
    var checkBox = $event.target;
    if (checkBox.checked) {
      angular.forEach($scope.items,
      function(data, index, array) {
        updateSelected("add", data.member.memberId, data.name);
      });
    } else {
      angular.forEach($scope.items,
      function(data, index, array) {
        updateSelected("remove", data.member.memberId, data.name);
      });
    }
  };

  function updateSelected(action, id, name) {
    if (action == 'add' && $scope.selected.indexOf(id) == -1) {
      $scope.selected.push(id);
      usernameSelectd.push(name);
    }
    if (action == 'remove' && $scope.selected.indexOf(id) != -1) {
      var idx = $scope.selected.indexOf(id);
      $scope.selected.splice(idx, 1);
      usernameSelectd.splice(usernameSelectd.indexOf(name), 1);
    }
    $('input[name="checkIt"]').each(function() {
      if (!$(this).prop("checked")) {
        $scope.checkAll = false;
        return false;
      } else {
        $scope.checkAll = true;
      }
    });
  }

  $scope.isSelected = function(id) {
    return $scope.selected.indexOf(id) >= 0;
  }

  $scope.updateSelection = function($event, id, name) {
    var checkbox = $event.target;
    var action = (checkbox.checked ? 'add': 'remove');
    updateSelected(action, id, name);
  }

  /**
	 *  返回首页
	 */
  $scope.pageBackButton = function() {
    window.location.href = mainUrl + "index"
  }

  $scope.addAndEditButton = function() {
    $http({
      method: 'POST',
      url: mainUrl + "saveUpdate",
      params: $scope.cloudGroup,
    }).success(function(result) {
      $scope.result = result.success;
      if (result.success) {
        $scope.pageBackButton();
      }
    }).error(function(error) {
      alert('操作失败！');
    });
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

  /**
	 * 服务人员 ServicePersonnel
	 */
  $scope.insiderButton = function() {
    insiderIds = [];
    serviceNames = [];
    $scope.searchValue = '';
    $scope.queryServicePersonneTree();
    $('#insiderModal').modal('show');
  };

  $scope.queryServicePersonneTree = function() {
    var url = CONTEXT_PATH + DISPATCHER_PATH + "/appUserForWindow/queryServicePersonneTree";
    $http({
      method: 'POST',
      url: url,
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
      }
    }).success(function(data) {
      zNodes = data;
      $.fn.zTree.init($("#treeDemo"), setting, zNodes);
      var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    }).error(function(error) {
      alert(error);
      if ('403' == error.status) {
        alert('无权限');
      }
    });
  }

  /**
		 * 删除推送人员
		 */
  $scope.deleteNames = function(id) {
    $(id).val('');
    if (id === '#outNames') {
      $scope.cloudGroup.fgServPersonIds = $scope.selected;
    } else {
      $scope.cloudGroup.fgAppUserIds = insiderIds;
    }
  };

  /**
	 * app用户添加
	 */
  $scope.outsiderButton = function(type) {
    appUserPublic();
    creatWindowMember(type);
    $('#myModal').modal('show');

  };

  /**
 * 人员列表窗口
 */
  function creatWindowMember(type) {
    pageService.reGetDataForList($scope, $http, CONTEXT_PATH + "/" + DISPATCHER_PATH
    		+ "/appUserForWindow/memberList?type=" + $scope.member.id
    		+ "&name=" + $scope.member.name);
  }

  function appUserPublic() {
    $scope.selected = [];
    $scope.checkAll = false;
    $scope.member = {
      id: 'guest'
    };
    usernameSelectd = [];
  }

  /**
 *  app user 查询
 */
  $scope.searchByParam = function() {
    creatWindowMember();
  };
  /**
 * app user  确认
 */
  $scope.appUserConfirmButton = function() {
    var oldValue = $('#outNames').val();
    var oldOutIds = $scope.cloudGroup.fgAppUserIds;
    if (oldOutIds !== undefined && oldOutIds !== null) {
      var asid = $scope.selected;
      for (var i = 0; i < asid.length; i++) {
        if (oldOutIds.indexOf(asid[i]) == -1) {
          oldOutIds = oldOutIds + "," + asid[i];
        }
      }
      $scope.cloudGroup.fgAppUserIds = oldOutIds;
    } else {
      $scope.cloudGroup.fgAppUserIds = $scope.selected;

    }
    if (oldValue !== "") {
      for (var i = 0; i < usernameSelectd.length; i++) {
        if (oldValue.indexOf(usernameSelectd[i]) == -1) {
          oldValue = oldValue + "," + usernameSelectd[i];
        }
      }
      $('#outNames').val(oldValue);
      $scope.cloudGroup.fgAppUserNames = oldValue;
    } else {
      $('#outNames').val(usernameSelectd);
      $scope.cloudGroup.fgAppUserNames = usernameSelectd;

    }
    $('#myModal').modal('hide');
    appUserPublic();
  }
  /**
 * app user 取消按钮
 */
  $scope.appUserCloseButton = function() {
    $('#myModal').modal('hide');
    appUserPublic();
  }
  /**
 * 服务人员查询
 */
  $scope.searchForService = function(type) {
    searchForService(type);

  };

  function searchForService(type) {
    var url = CONTEXT_PATH + DISPATCHER_PATH + "/appUserForWindow/findServicePersonnelForName?name=" + $scope.searchValue;
    $http({
      method: 'POST',
      url: url,
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'
      }
    }).success(function(data) {
      zNodes = data;
      $.fn.zTree.init($("#treeDemo"), setting, zNodes);
      var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    }).error(function(error) {
      alert(error);
      if ('403' == error.status) {
        alert('无权限');
      }
    });
  }

  /**
 *  服务人员窗口 确认按钮
 */
  $scope.serviceConfirmButton = function() {
    var oldValue = $('#insiderNames').val();
    var oldOutIds = $scope.cloudGroup.fgServPersonIds;
    if (oldOutIds !== undefined && oldOutIds !== null) {
      var asid = insiderIds;
      for (var i = 0; i < asid.length; i++) {
        if (oldOutIds.indexOf(asid[i]) == -1) {
          oldOutIds = oldOutIds + "," + asid[i];
        }
      }
      $scope.cloudGroup.fgServPersonIds = oldOutIds;
    } else {
      $scope.cloudGroup.fgServPersonIds = insiderIds;

    }
    if (oldValue !== "") {
      for (var i = 0; i < serviceNames.length; i++) {
        if (oldValue.indexOf(serviceNames[i]) == -1) {
          oldValue = oldValue + "," + serviceNames[i];
        }
      }
      $('#insiderNames').val(oldValue);
      $scope.cloudGroup.fgServPersonNames = oldValue;
    } else {
      $('#insiderNames').val(serviceNames);
      $scope.cloudGroup.fgServPersonNames = serviceNames;

    }
    servicePublic();
  }

  /**
 * 服务人员窗口 取消按钮
 */
  $scope.serviceCloseButton = function() {
    servicePublic();
  }

  /**
 * 服务人员窗口 公共
 */
  function servicePublic() {
    insiderIds = [];
    serviceNames = [];
    $('#insiderModal').modal('hide');
  }

  var zNodes;
  var setting = {
    check: {
      enable: true,
    },
    data: {
      key: {
        name: "name"
      },
      simpleData: {
        enable: true,
        idKey: "id",
        pIdKey: "pid"
      }
    },
    callback: {
      onCheck: zTreeOnClick,
    }
  };

  function zTreeOnClick(event, treeId, treeNode) {
    if (treeNode.checked) {
      console.info(treeNode);
      insiderIds.push(treeNode.id);
      serviceNames.push(treeNode.name);
    } else {
      if (serviceNames.indexOf(treeNode.name) != -1) {
        serviceNames.splice(serviceNames.indexOf(treeNode.name), 1);
      }
      if (insiderIds.indexOf(treeNode.id) != -1) {
        insiderIds.splice(insiderIds.indexOf(treeNode.id), 1);
      }
    }
  }

})
.directive('ngTames', function($http) {
	return {
		restrict : "EACM",
		require: '?ngModel',
		link : function(scope, elements, attrs, controller) {
			elements[0].onblur = function() {
				var url = CONTEXT_PATH + DISPATCHER_PATH + "/cloudGroup/checkName";
				$http({
					method : 'POST',
					url : url,
					params : {fgName:this.value},
				}).success(function(resultMap) {
					if(resultMap == 'false'){
						fgName.setCustomValidity("此名称已存在，请重新输入！");
						return false;
					} else {
						return true;
					}
				}).error(function(error) {
					fgName.setCustomValidity("此名称已存在，请重新输入！");
					return false;
				});
			}
		}
	};
});