var addJpushEntiy = angular.module('addJpushEntiy', ['tm.pagination']);

pageService(addJpushEntiy);

addJpushEntiy.controller("jpAddCtlr", function($scope, $http, pageService) {

  var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/jpushEntiy/",
  usernameSelectd = [];
  serviceNames = [];
  insiderIds = [];
  $scope.selected = [];
  $scope.searchValue = '';
  $scope.jpushEntiy = {};
  $scope.checkAll = false;
  $scope.fchat = new Object();
  $scope.member = {
    id: 'guest'
  };
  
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
  initLanguage();
  
  
  function initLanguage(){
	  /*多语言*/
	    $http.get(url + "languageType?languageType="+'languagetype').success(function(response) {
	    	 $scope.topicTypesData=response;
	    });
  }
  /* 语言外层循环索引保存*/
  $scope.topicMark = 0;

  $scope.personForTpye = {};

  /*初始化语言*/
  $scope.spAddressNames = [{}];

  /* 初始化时由于只有一个语言，所以不允许删除*/
  $scope.fchat.canDescTopic = false;

  $scope.fchat.canIncrTopic = true;

  /*初始化时由于只有一个选项，所以不允许删除*/
  $scope.fchat.canDescOptions = [[false]];

  /* 根据语言类型改变，显示选项*/
  $scope.fchat.topicOnChange = function($index, type) {
    var topicType = document.getElementById("topicType" + ($index));
    var optionsDiv = document.getElementById("optionsDiv" + ($index));
    for (var i = 1; i < $index + 2; i++) {
      if (i !== $index) {
        var id = '#topicType' + i;
        var value = $(id).val();
        if (type === value) {
          alert("语言类型已存在，请重新选择！");
          var id = '#topicType' + $index;
          $(id).val(ltype[0].id);
          break;
        }

      }
    }
    optionsDiv.style.display = topicType.value !== '' ? 'block': 'none';
  }

  /* 增加语言数*/
  $scope.fchat.incrTopic = function() {
    var topicLength = $scope.spAddressNames.length;
    $scope.spAddressNames.splice(topicLength, 0, {
      languageId: '',
      jpTitle: '',
      jpText: ''
    });
    $scope.fchat.canDescTopic = true;

    $scope.fchat.canDescOptions[topicLength] = [false];
    if (topicLength == $scope.topicTypesData.length - 2) {
      $scope.fchat.canIncrTopic = false;
    }
  }

  /*减少语言数*/
  $scope.fchat.decrTopic = function($index) {
    if ($scope.spAddressNames.length > 1) {
      $scope.fchat.canIncrTopic = true;
      $scope.spAddressNames.splice($index, 1);
      $scope.fchat.canDescOptions.splice($index, 1);
    }
    if ($scope.spAddressNames.length == 1) {
      $scope.fchat.canDescTopic = false;
      $scope.fchat.canIncrTopic = true;
    }
  }

  /**
		 * 消息推送
		 */
  $scope.addJpushEntiy = function() {
    var jpushEntiy = $scope.jpushEntiy;
    var parsms = {
      languages: $scope.spAddressNames,
      broadCast: (jpushEntiy.broadCast == true ? true: false),
      insiderIds: jpushEntiy.insiderIds,
      jpUniteId: jpushEntiy.jpUniteId,
      jpText: jpushEntiy.jpText,
      jpTitle: jpushEntiy.jpTitle,
      languageId: 'zh',
      outsiderCheck: (jpushEntiy.outsiderCheck),
      insiderCheck: jpushEntiy.insiderCheck,
      outsiderIds: jpushEntiy.outsiderIds
    }
    $http({
      method: 'POST',
      url: url + "saveList",
      params: parsms,
    }).success(function(result) {
      $scope.result = result.success;
      if (result.success) {
        window.location.href = url + "openPage";
        alert('推送成功！');
      }
    }).error(function(error) {
      alert('推送失败！');
    });
  }

  /**
		 * 获取语言种类
		 */
  function initLangua() {
    $http({
      method: 'GET',
      url: url + "initLangua",
    }).success(function(result) {
      $scope.jpushEntiy = result;
    }).error(function(error) {
      if ('403' == error.status) {
        alert('无权限');
      }
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

  $scope.creatMember = function(type) {
    creatWindowMember(type);

  };

  $scope.searchForService = function(type) {
    searchForService(type);

  };

  /**
		 * 人员列表窗口
		 */
  function creatWindowMember(type) {
    pageService.reGetDataForList($scope, $http, CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appUserForWindow/memberList?type=" + $scope.member.id + "&name=" + $scope.member.name);
  }

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
   *
   * 服务人员 ServicePersonnel
   */
  $scope.insiderButton = function() {
    insiderIds = [];
    serviceNames = [];
    $scope.searchValue = '';
    $scope.queryServicePersonneTree();
    $('#insiderModal').modal('show');
  };

  /**
	* 删除推送人员
	*/
  $scope.deleteNames = function(id) {
    $(id).val('');
    if (id === '#outNames') {
      $scope.jpushEntiy.outsiderIds = $scope.selected;
    } else {
      $scope.jpushEntiy.insiderIds = insiderIds;
    }
  };

  $scope.isSelected = function(id) {
    return $scope.selected.indexOf(id) >= 0;
  }

  $scope.updateSelection = function($event, id, name) {
    var checkbox = $event.target;
    var action = (checkbox.checked ? 'add': 'remove');
    updateSelected(action, id, name);
  }

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
    var oldOutIds = $scope.jpushEntiy.outsiderIds;
    if (oldOutIds !== undefined) {
      var asid = $scope.selected;
      for (var i = 0; i < asid.length; i++) {
        if (oldOutIds.indexOf(asid[i]) == -1) {
          oldOutIds = oldOutIds + "," + asid[i];
        }
      }
      $scope.jpushEntiy.outsiderIds = oldOutIds;
    } else {
      $scope.jpushEntiy.outsiderIds = $scope.selected;

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

  function appUserPublic() {
    $scope.selected = [];
    $scope.checkAll = false;
    $scope.member = {
      id: 'guest'
    };
    usernameSelectd = [];
  }

  /*	服务人员窗口  相关操作信息*/
  /**
	 *  服务人员窗口 确认按钮
	 */
  $scope.serviceConfirmButton = function() {
    var oldValue = $('#insiderNames').val();
    var oldOutIds = $scope.jpushEntiy.insiderIds;
    if (oldOutIds !== undefined) {
      var asid = insiderIds;
      for (var i = 0; i < asid.length; i++) {
        if (oldOutIds.indexOf(asid[i]) == -1) {
          oldOutIds = oldOutIds + "," + asid[i];
        }
      }
      $scope.jpushEntiy.insiderIds = oldOutIds;
    } else {
      $scope.jpushEntiy.insiderIds = insiderIds;

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
});
