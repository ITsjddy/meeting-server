
var addDType = angular.module('addDType', ['ngMessages']);



addDType.controller("addDTypeCtlr", function($scope, $http) {

  var urlStr = CONTEXT_PATH + DISPATCHER_PATH + "/dataDictionaryType/";
  

  initDType();
  /**
	 * 添加
	 */
  $scope.addDType = function() {
	//校验
	/*if(!check()){
		return false;
	}*/
    $http({
      method: 'POST',
      url: urlStr + "save",
      params: $scope.dDtype,
    }).success(function(result) {
      if (result.success) {
        window.location.href = urlStr + "index";
      } else {
        alert('页面加载失败！');
      }
    }).error(function(error) {
      if ('403' == error.status) {
        alert('无权限');
      }
    });
  };
  
  function check(){
	//邀请码校验
		var dTypeNameValue = $scope.dDtype.dTypeName;
		if(checkTypeName(dTypeNameValue)){
			messagenoty('名称重复！', 'error', 'center', '1500');
			dTypeName.setCustomValidity("名称重复！");
			return false;
		}
		return  
  }
  
  function checkTypeName(dTypeNameValue){
	  var cheResult = false;
	  var url = CONTEXT_PATH + DISPATCHER_PATH + "/dataDictionaryType/checkName";
		$http({
			method : 'POST',
			url : url,
			params : {dTypeName:this.dTypeNameValue},
		}).success(function(resultMap) {
			if(resultMap == 'false'){
				cheResult = false;
			} else {
				cheResult = true;
			}
		}).error(function(error) {
			cheResult = false;
		});
	 
	  return cheResult;
  }

  /**
	 * 返回
	 */
  $scope.backPage = function() {
    window.location.href = urlStr + "index";
  }

  /**
	 * 页面数据初始化
	 */
  function initDType() {
    var spValue = $("#spValue").val();
    $http.get(urlStr + "queryById?id=" + spValue).success(function(response) {
      var dType = {};
       dType.dTypeLogo = response.dtypeLogo;
       dType.id = response.id;
       dType.dTypeName = response.dtypeName;
       dType.dTypeExplanation = response.dtypeExplanation;
      $scope.dDtype = dType;
    });

  }

})
.directive('ngTames', function($http) {
	return {
		restrict : "EACM",
		require: '?ngModel',
		link : function(scope, elements, attrs, controller) {
			elements[0].onblur = function() {
				var url = CONTEXT_PATH + DISPATCHER_PATH + "/dataDictionaryType/checkName";
				$http({
					method : 'POST',
					url : url,
					params : {dTypeName:this.value},
				}).success(function(resultMap) {
					if(resultMap == 'false'){
						dTypeName.setCustomValidity("此名称已存在，请重新输入！");
						return false;
					} else {
						return true;
					}
				}).error(function(error) {
					dTypeName.setCustomValidity("此名称已存在，请重新输入！");
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
					dTypeLogo.setCustomValidity("唯一标识只能由数字、小写字母组成！");
					return;
				}
				var url = CONTEXT_PATH + DISPATCHER_PATH + "/dataDictionaryType/checkOnlyLogo";
				$http({
					method : 'POST',
					url : url,
					params : {dTypeLogo:this.value},
				}).success(function(resultMap) {
					if(resultMap == 'false'){
						dTypeLogo.setCustomValidity("此唯一标识已存在，请重新输入！");
						return false;
					} else {
						return true;
					}
				}).error(function(error) {
					dTypeLogo.setCustomValidity("此唯一标识已存在，请重新输入！");
					return false;
				});
			}
		}
	};
});