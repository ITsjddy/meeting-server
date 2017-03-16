var appVersionApp = angular.module('appVersionApp', ['ngMessages']);
appVersionApp.controller("appVersionController",
function($scope, $http) {

  $scope.changeAppVersionType = function() {
    var type = $scope.appVersion.type;
    androidIos(type);
    var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/getMaxVersionNum";
    $http({
      method: 'POST',
      url: url,
      params: {
        type: type
      },
    }).success(function(resultMap) {
      $scope.appVersion.versionNum = resultMap.versionNum;
    }).error(function(error) {
      if ('403' == error.status) {
        alert('无权限');
      }
    });
  }

  $scope.appVersionFindById = function() {
    var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/appVersionFindById";
    var id = $("#id").val();
    var param = {
      id: id
    };
    $http({
      method: 'POST',
      url: url,
      params: param,
    }).success(function(resultMap) {
      $scope.appVersion = resultMap;
      androidIos($scope.appVersion.type);
    }).error(function(error) {

});
  }

  function androidIos(type) {
    var apkDiv = document.getElementById("apkDiv");
    var ipaDiv = document.getElementById("ipaDiv");
    var plistDiv = document.getElementById("plistDiv");
    var picOneDiv = document.getElementById("picOneDiv");
    var picTwoDiv = document.getElementById("picTwoDiv");
    if (null != type && "" != type && type == 2 || type == "2") {
      plistDiv.style.display = "block";
      picOneDiv.style.display = "block";
      picTwoDiv.style.display = "block";
      ipaDiv.style.display = "block";
      apkDiv.style.display = "none";
    } else if (type == 1 || type == "1") {
      plistDiv.style.display = "none";
      picOneDiv.style.display = "none";
      picTwoDiv.style.display = "none";
      ipaDiv.style.display = "none";
      apkDiv.style.display = "block";
    } else {
      plistDiv.style.display = "none";
      picOneDiv.style.display = "none";
      picTwoDiv.style.display = "none";
      ipaDiv.style.display = "none";
      apkDiv.style.display = "none";
    }
  }
  $scope.appVersionFindById();

  $scope.appVersionEditSave = function() {
    //获取到表单是否验证通过
    if (!$scope.form.$valid) {
      alert('表单没有通过验证');
      return;
    }

    var url = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/appVersionAddSave";
    var fd = new FormData();
    var files = document.querySelectorAll('input[type=file]');
    fd.append('id', $scope.appVersion.id);
    fd.append('type', $scope.appVersion.type);
    fd.append('versionName', $scope.appVersion.versionName);
    fd.append('versionNum', $scope.appVersion.versionNum);
    fd.append('remark', $scope.appVersion.remark);
    fd.append('path', files[0].files[0]);
    fd.append('qrCode', files[1].files[0]);
    fd.append('plist', files[2].files[0]);
    fd.append('picOne', files[3].files[0]);
    fd.append('picTwo', files[4].files[0]);
    $http({
      method: 'POST',
      url: url,
      data: fd,
      headers: {
        'Content-Type': undefined
      },
      transformRequest: angular.identity
    }).success(function(resultMap) {
      if (resultMap.success) {
        $scope.backPage();
      } else {
        alert('保存失败');
      }

    }).error(function(error) {
      alert('无权限');
    });
  }

  /*返回*/
  $scope.backPage = function() {
    window.location.href = CONTEXT_PATH + "/" + DISPATCHER_PATH + "/appVersion/index";
  }
});