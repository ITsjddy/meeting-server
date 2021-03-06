var addSavorPoint = angular.module('addSavorPoint', ['ngMessages']);

addSavorPoint.controller("spAddCtlr", function($scope, $http) {

  var urlStr = CONTEXT_PATH + DISPATCHER_PATH + "/savorPoint/";
  $scope.fchat = new Object();

  /* 语言外层循环索引保存*/
  $scope.topicMark = 0;

  /* 初始化时由于只有一个语言，所以不允许删除*/
  $scope.fchat.canDescTopic = false;

  $scope.fchat.canIncrTopic = true;

  /*初始化时由于只有一个选项，所以不允许删除*/
  $scope.fchat.canDescOptions = [[false]];
  initSavorPoint();

  /*添加*/
  $scope.addSavorPoint = function() {
    var savorPoint = $scope.savorPoint;
    var fd = new FormData();
    var files = document.querySelectorAll('input[type=file]');
    fd.append('small_icon', files[0].files[0]);

    $http({
      method: 'POST',
      url: urlStr + "updateLogo",
      data: fd,
      headers: {
        'Content-Type': undefined
      },
      transformRequest: angular.identity
    }).success(function(resultMap) {
      if (resultMap.success) {
        params = {
          id: savorPoint.id,
          spOnly: savorPoint.spOnly,
          spUniteId: savorPoint.spUniteId,
          spLongitude: savorPoint.spLongitude,
          spDimensions: savorPoint.spDimensions,
          spLogo: resultMap.message,
          spType: savorPoint.spType,
          spName: savorPoint.spName,
          spAddress: savorPoint.spAddress,
          spLanguageType: savorPoint.spLanguageType,
          spAddressNames: $scope.spAddressNames
        };
        $http({
          method: 'POST',
          url: urlStr + "saveList",
          params: params,
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
      } else {
        alert('保存失败');
      }

    }).error(function(error) {
      alert('无权限');
    });

  };

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
  $scope.fchat.incrTopic = function($index) {
    var topicLength = $scope.spAddressNames.length;
    $scope.spAddressNames.splice(topicLength, 0, {
      topic: '',
      topicType: '',
      spName: '',
      spAddress: ''
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

  /*返回*/
  $scope.backPage = function() {
    window.location.href = urlStr + "openPage";
  }

  /*页面数据初始化*/
  function initSavorPoint() {

	  /*兴趣点类型*/
	    $http.get(urlStr + "savorPointType?savorPointType="+'savorpointtype').success(function(response) {
	    	$scope.spTypes = response;
	      });
	    /*多语言*/
	    $http.get(urlStr + "languageType?languageType="+'languagetype').success(function(response) {
	    	 $scope.topicTypesData=response;
	    });

    var spValue = $("#spValue").val();
    $http.get(urlStr + "queryById?spUniteId=" + spValue).success(function(response) {
      var as = response.spAddressNames;
      var sa = [];
      if (as.length == 0) {
        $scope.spAddressNames = [{
          topicType: ''
        }];
      } else {
        for (var i = 0; i < as.length; i++) {
          var aa = angular.fromJson(as[i]);
          sa.push(aa);
        }
        console.info(as.length);
        $scope.spAddressNames = sa;
      }
      $scope.savorPoint = response;
      if (sa.length > 1) {
        $scope.fchat.canDescTopic = true;
      }
    });

  }
})

.directive('ngTlogos', function($http) {
	return {
		restrict : "EACM",
		require: '?ngModel',
		link : function(scope, elements, attrs, controller) {
			elements[0].onblur = function() {
				var pattern = /^[a-z0-9]+$/;
				if(!pattern.test(this.value)){
					spOnly.setCustomValidity("唯一标识只能由数字、小写字母组成！");
					return;
				}
				var url = CONTEXT_PATH + DISPATCHER_PATH + "/savorPoint/checkOnlyLogo";
				$http({
					method : 'POST',
					url : url,
					params : {spOnly:this.value},
				}).success(function(resultMap) {
					if(resultMap == 'false'){
						spOnly.setCustomValidity("此唯一标识已存在，请重新输入！");
						return false;
					} else {
						return true;
					}
				}).error(function(error) {
						spOnly.setCustomValidity("此唯一标识已存在，请重新输入！");
						return false;
				});
			}
		}
	};
});
