var pushSmsPage = angular.module('pushSmsPage', ['tm.pagination']);

pageService(pushSmsPage); //初始化分页service
pushSmsPage.controller("pushSmsCtlr",
function($scope, $http, pageService) {

  var urlStr = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/smsPushTemplate/";
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
  }];
  usernameSelectd = [];
  serviceNames = [];
	insiderIds = [];
$scope.searchValue = '';
  $scope.selected = [];
  $scope.pusms = {};

  $scope.member = {
    id: 'guest'
  };
  $scope.checkAll = false;
  pageService.queryForList($scope, $http, CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appUserForWindow/memberList?type=" + 'asdf');

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

  $scope.appUserConfirmButton = function() {
    var oldValue = $('#outNames').val();
    var oldOutIds = $scope.pusms.smspGuests;
    if (oldOutIds !== undefined) {
      var asid = $scope.selected;
      for (var i = 0; i < asid.length; i++) {
        if (oldOutIds.indexOf(asid[i]) == -1) {
          oldOutIds = oldOutIds + "," + asid[i];
        }
      }
      $scope.pusms.smspGuests = oldOutIds;
    } else {
      $scope.pusms.smspGuests = $scope.selected;

    }
    if (oldValue !== "") {
      for (var i = 0; i < usernameSelectd.length; i++) {
        if (oldValue.indexOf(usernameSelectd[i]) == -1) {
          oldValue = oldValue + "," + usernameSelectd[i];
        }
      }
      $('#outNames').val(oldValue);
    } else {
      $('#outNames').val(usernameSelectd);

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

  initSavorPoint();

  /**
	 * 返回
	 */
  $scope.backPage = function() {
    window.location.href = urlStr + "openPage";
  }

  /**
	 * 删除推送人员
	 */
  $scope.deleteNames = function(id) {
    $(id).val('');
    if (id === '#outNames') {
      $scope.pusms.smspGuests = $scope.selected;
    }
  };

  /**
	 * 页面数据初始化
	 */
  function initSavorPoint() {
    var spValue = $("#spValue").val();
    $http.get(urlStr + "queryById?id=" + spValue).success(function(response) {
      var pusms = {};
      pusms.smstId = response.sptNo;
      pusms.smspTitle = response.sptTetle;
      pusms.smspText = response.sptText;
      $scope.pusms = pusms;
    });

  }

  $scope.pushSms = function() {
    $http({
      method: 'POST',
      url: urlStr + "smsPush",
      params: $scope.pusms,
    }).success(function(result) {
    	window.location.href = urlStr + "openPage";
    }).error(function(error) {
    	alert('页面加载失败！');
    });
  }

	  /**
		 * app用户添加
		 */
	$scope.outsiderButton = function(type) {
	  appUserPublic();
	  creatWindowMember(type);
	  $('#myModal').modal('show');
	
	};
	
	/**
	 *  app user 查询
	 */
	$scope.searchByParam = function() {
		creatWindowMember();
	};
	
	function appUserPublic() {
	    $scope.selected = [];
	    $scope.checkAll = false;
	    $scope.member = {
	        id: 'guest'
	    };
	    usernameSelectd = [];
	}


  $scope.creatMember = function(type) {
    creatWindowMember(type);

  };

	  /**
		 * 人员列表窗口
		 */
	function creatWindowMember(type) {
		pageService.reGetDataForList($scope, $http, CONTEXT_PATH + "/" + DISPATCHER_PATH 
				+ "/appUserForWindow/memberList?type=" + $scope.member.id +"&name=" + $scope.member.name);
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
			insiderIds.push(treeNode.id);
			serviceNames.push(treeNode.name);
		} else {
			if(serviceNames.indexOf(treeNode.name) != -1){
				serviceNames.splice(serviceNames.indexOf(treeNode.name), 1);
			}
			if(insiderIds.indexOf(treeNode.id) != -1){
				insiderIds.splice(insiderIds.indexOf(treeNode.id), 1);
			}
		}
	}
  /**
	 * 服务人员 ServicePersonnel
	 */
	$scope.insiderButton = function() {
		 insiderIds = [];
		 serviceNames = [];
		 $scope.searchValue = ''
		$scope.queryServicePersonneTree();
	  $('#insiderModal').modal('show');
	};
	
	/**
		 * 删除推送人员
		 */
	$scope.deleteNames = function(id) {
	    $(id).val('');
	    if (id === '#outNames') {
	        $scope.pusms.smspGuests = $scope.selected;
	    } else {
	    	$scope.pusms.smspStbers = insiderIds;
	    }
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
	    var oldOutIds = $scope.pusms.smspStbers;
	    if (oldOutIds !== undefined) {
	        var asid = insiderIds;
	        for (var i = 0; i < asid.length; i++) {
	            if (oldOutIds.indexOf(asid[i]) == -1) {
	                oldOutIds = oldOutIds + "," + asid[i];
	            }
	        }
	        $scope.pusms.smspStbers = oldOutIds;
	    } else {
	        $scope.pusms.smspStbers = insiderIds;

	    }
	    if (oldValue !== "") {
	        for (var i = 0; i < serviceNames.length; i++) {
	            if (oldValue.indexOf(serviceNames[i]) == -1) {
	                oldValue = oldValue + "," + serviceNames[i];
	            }
	        }
	        $('#insiderNames').val(oldValue);
	    } else {
	        $('#insiderNames').val(serviceNames);

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
	function servicePublic(){
		insiderIds = [];
		serviceNames = [];
		$('#insiderModal').modal('hide');
	}
	
	
});