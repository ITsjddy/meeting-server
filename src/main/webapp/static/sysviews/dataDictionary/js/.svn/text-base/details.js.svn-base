var editDataDictionary = angular.module('editDataDictionary', ['ngMessages']);

editDataDictionary.controller("spAddCtlr", function($scope, $http) {

  var urlStr = CONTEXT_PATH + DISPATCHER_PATH + "/dataDictionary/";
  var typeName = $('#typeName').val();
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
  $scope.editDataDictionary = function() {
    var dataDictionary = $scope.dataDictionary;
    var form = [];
    form.push({  dDLogo: dataDictionary.dDLogo,
    			 dDType: dataDictionary.dDType,
    			 dDName: dataDictionary.dDName,
    			 dDexplanation: dataDictionary.dDexplanation,
    			 dDLanguageType:'zh' })
    var dDNameList = $scope.spAddressNames;
    for (var int = 0; int < dDNameList.length; int++) {
		var langu = dDNameList[int];
		if('' !== langu.topicType){
			form.push({dDLogo: dataDictionary.dDLogo,
				    dDType: dataDictionary.dDType,
	    			dDName: langu.dDName,
	    			dDexplanation: dataDictionary.dDexplanation,
	    			dDLanguageType:langu.topicType})
		}
	}
    var params = {dDcommId:dataDictionary.dDcommId, dataDictionary:form};
          $http({
            method: 'POST',
            url: urlStr + "saveList",
            params: params,
          }).success(function(result) {
            if (result.success) {
              window.location.href = urlStr + "index?typeName="+typeName;
            } else {
              alert('页面加载失败！');
            }
          }).error(function(error) {
            if ('403' == error.status) {
              alert('无权限');
            }
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
  $scope.fchat.incrTopic = function() {
    var topicLength = $scope.spAddressNames.length;
    $scope.spAddressNames.splice(topicLength, 0, {
      topic: '',
      topicType: '',
      dDName: ''
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
    window.location.href = urlStr + "index?typeName="+$scope.dataDictionary.dDType+"&dDTypeLogo=" + $scope.dataDictionary.dDTypeLogo;
  }

  /*页面数据初始化*/
  function initSavorPoint() {
	  /*多语言*/
	    $http.get(urlStr + "languageType?languageType="+'languagetype').success(function(response) {
	    	 $scope.topicTypesData=response;
	    });
	  var spValue = $("#idValue").val();
	    $http.get(urlStr + "queryById?id=" + spValue).success(function(response) {
	    	var as = response.languages;
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
	          $scope.spAddressNames = sa;
	        }
	        if (sa.length > 1) {
	          $scope.fchat.canDescTopic = true;
	        }
	    	$scope.dataDictionary = response;
	    });
  }
})
.directive('ngTames', function($http) {
	return {
		restrict : "EACM",
		require: '?ngModel',
		link : function(scope, elements, attrs, controller) {
			elements[0].onblur = function() {
				var url = CONTEXT_PATH + DISPATCHER_PATH + "/dataDictionary/checkName";
				$http({
					method : 'POST',
					url : url,
					params :  {dDName:this.value,languageType:elements[0].id,typeName:$('#typeName').val()},
				}).success(function(resultMap) {
					if(resultMap == 'false'){
						elements[0].setCustomValidity("此名称已存在，请重新输入！");
						return false;
					} else {
						return true;
					}
				}).error(function(error) {
					elements[0].setCustomValidity("此名称已存在，请重新输入！");
					return false;
				});
			}
		}
	};
})
.directive('ngTlogos', function($http) {
	return {
		restrict : "EACM",
		require: '?ngModel',
		link : function(scope, elements, attrs, controller) {
			elements[0].onblur = function() {
				var pattern = /^[a-z0-9]+$/;
				if(!pattern.test(this.value)){
					dDLogo.setCustomValidity("唯一标识只能由数字、小写字母组成！");
					return;
				}
				var url = CONTEXT_PATH + DISPATCHER_PATH + "/dataDictionary/checkOnlyLogo";
				$http({
					method : 'POST',
					url : url,
					params : {dDLogo:this.value,typeName:$('#typeName').val()},
				}).success(function(resultMap) {
					if(resultMap == 'false'){
						dDLogo.setCustomValidity("此唯一标识已存在，请重新输入！");
						return false;
					} else {
						return true;
					}
				}).error(function(error) {
					dDLogo.setCustomValidity("此唯一标识已存在，请重新输入！");
					return false;
				});
			}
		}
	};
});